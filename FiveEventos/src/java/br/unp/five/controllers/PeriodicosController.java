package br.unp.five.controllers;

import br.unp.five.models.Periodicos;
import br.unp.five.controllers.util.JsfUtil;
import br.unp.five.controllers.util.PaginationHelper;
import br.unp.five.facade.PeriodicosFacade;
import br.unp.five.models.Area;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("periodicosController")
@SessionScoped
public class PeriodicosController implements Serializable {

    private Periodicos current;
    private DataModel items = null;
    @EJB
    private br.unp.five.facade.PeriodicosFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    private String area;
    private String classificacao;
    private String peso;
    private Integer userID;
    private List<Periodicos> favoritos;

    public List<Periodicos> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Periodicos> favoritos) {
        this.favoritos = favoritos;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }



    public String getPeso() {
        this.peso = this.current.getIdPeso().getDescricao();
        return peso;
    }
    
    
    public String getArea() {
        this.area = this.current.getIdArea().getDescricao();
        return area;
    }
    
    public String getClassificacao() {
        this.classificacao = this.current.getIdClassificacao().getDescricao();
        return classificacao;
    }
    
    public PeriodicosController() {
    }

    public Periodicos getSelected() {
        if (current == null) {
            current = new Periodicos();
            selectedItemIndex = -1;
        }
        return current;
    }

    private PeriodicosFacade getFacade() {
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
        current = (Periodicos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Periodicos();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            
            if(current.getIdPeriodico() == null){
                current.setIdPeriodico(0);
            }
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("PeriodicosCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Periodicos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("PeriodicosUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Periodicos) getItems().getRowData();
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

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/resources/Bundle").getString("PeriodicosDeleted"));
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
    
    public String listFavoritos(Integer idUser) {
        
        setUserID(idUser);
        setFavoritos(listarFavoritos());
        return "/faces/private/periodicos/ListFavoritos";
    }
    
    private List<Periodicos> listarFavoritos() {
        return getFacade().findFavoritos(getUserID());
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

    public Periodicos getPeriodicos(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Periodicos.class)
    public static class PeriodicosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PeriodicosController controller = (PeriodicosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "periodicosController");
            return controller.getPeriodicos(getKey(value));
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
            if (object instanceof Periodicos) {
                Periodicos o = (Periodicos) object;
                return getStringKey(o.getIdPeriodico());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Periodicos.class.getName());
            }
        }

    }

}
