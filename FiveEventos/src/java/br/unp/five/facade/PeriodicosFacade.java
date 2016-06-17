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

/**
 *
 * @author Cleiton França
 */
@Stateless
public class PeriodicosFacade extends AbstractFacade<Periodicos> {

    @PersistenceContext(unitName = "FiveEventosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodicosFacade() {
        super(Periodicos.class);
    }

    public List<Periodicos> findFavoritos(Integer idUser) {
        //List<Periodicos> p = null;
        
        return getEntityManager().createNativeQuery("SELECT p.* FROM Periodicos p INNER JOIN "
                + "Favorit f ON p.idPeriodico = f.idPeriodico INNER JOIN "
                + "Usuario u ON f.idUsuario = u.idUsuario WHERE u.idUsuario = "+idUser+"", Periodicos.class)
                .getResultList();
        //return p;
    }
    
    public List<Periodicos> findFavoritos(Integer idUser, Integer idPeriodico) {
        //List<Periodicos> p = null;
        
        return getEntityManager().createNativeQuery("SELECT p.* FROM Periodicos p INNER JOIN "
                + "Favorit f ON p.idPeriodico = f.idPeriodico INNER JOIN "
                + "Usuario u ON f.idUsuario = u.idUsuario WHERE f.idPeriodico = "+idPeriodico+"", Periodicos.class)
                .getResultList();
        //return p;
    }

    
    
}
