package br.unp.five.controllers;

import br.unp.five.models.Favorit;
import br.unp.five.controllers.util.JsfUtil;
import br.unp.five.controllers.util.PaginationHelper;
import br.unp.five.facade.FavoritFacade;
import br.unp.five.models.Periodicos;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

@Named("favoritController")
@SessionScoped
public class FavoritController implements Serializable {

    private Favorit current;
    private DataModel items = null;
    @EJB
    private br.unp.five.facade.FavoritFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @EJB
    private br.unp.five.facade.PeriodicosFacade ejbPeriodicosFacade;
    
    private HttpSession sessionLogin = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    private List<Periodicos> favoritos;
    private Integer idPeriodico;
    private Periodicos periodico;

    public Periodicos getPeriodico() {
        if(idPeriodico != null) {
            return ejbPeriodicosFacade.find(idPeriodico);
        }
        return null;
    }

    public void setPeriodico(Periodicos periodico) {
        this.periodico = periodico;
    }

    public Integer getIdPeriodico() {
        return idPeriodico;
    }

    public void setIdPeriodico(Integer idPeriodico) {
        this.idPeriodico = idPeriodico;
    }

    
    
    
    public List<Periodicos> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Periodicos> favoritos) {
        this.favoritos = favoritos;
    }
    
    public FavoritController() {
    }

    public Favorit getSelected() {
        if (current == null) {
            current = new Favorit();
            selectedItemIndex = -1;
        }
        return current;
    }

    private FavoritFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Favorit) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Favorit();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("FavoritCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Favorit) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("FavoritUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Favorit) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }
    
   public String listFavoritos() {
        
        
        setFavoritos(listarFavoritos());
        return "ListFavoritos";
    }
    
    private List<Periodicos> listarFavoritos() {
        return getFacade().findFavoritos(getUserID());
    }
    
    
    public void removerFavorito() {
        
        Integer idUser = getUserID();
        Integer idPeri = getIdPeriodico();
        
        int delete = getFacade().removeFavoritos(idPeri, idUser);
        if(delete > 0 ){
            
            JsfUtil.addSuccessMessage("Registro excluido com sucesso!");
            

       } else {
            JsfUtil.addErrorMessage("Registro não encontrado!");
        } 
      
    }
    
    public String confirmacao(Integer idPeriodico) {
       
        setIdPeriodico(idPeriodico);
        
        
        
        return "/faces/private/favorit/Confirmacao";
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("FavoritDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Favorit getFavorit(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    private Integer getUserID() {
     return (Integer) sessionLogin.getAttribute("idUser");
    }

    @FacesConverter(forClass = Favorit.class)
    public static class FavoritControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FavoritController controller = (FavoritController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "favoritController");
            return controller.getFavorit(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Favorit) {
                Favorit o = (Favorit) object;
                return getStringKey(o.getIdUsuario());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Favorit.class.getName());
            }
        }

    }

}
