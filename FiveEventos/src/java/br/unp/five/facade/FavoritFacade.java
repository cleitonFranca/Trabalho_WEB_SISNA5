/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unp.five.facade;

import br.unp.five.models.Favorit;
import br.unp.five.models.Periodicos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Cleiton França
 */
@Stateless
public class FavoritFacade extends AbstractFacade<Favorit> {

    @PersistenceContext(unitName = "FiveEventosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FavoritFacade() {
        super(Favorit.class);
    }

    public int removeFavoritos(Integer idPeriodico, Integer idUser) {
           
    Query query = getEntityManager().createNativeQuery("DELETE FROM Favorit WHERE idUsuario = ? AND "
            + "idPeriodico = ?");
    query.setParameter(1, idUser);
    query.setParameter(2, idPeriodico);
   
    return query.executeUpdate();
        
    }

     public List<Periodicos> findFavoritos(Integer idUser) {
        //List<Periodicos> p = null;
        
        return getEntityManager().createNativeQuery("SELECT p.* FROM Periodicos p INNER JOIN "
                + "Favorit f ON p.idPeriodico = f.idPeriodico INNER JOIN "
                + "Usuario u ON f.idUsuario = u.idUsuario WHERE u.idUsuario = "+idUser+"", Periodicos.class)
                .getResultList();
        //return p;
    }
    
}
