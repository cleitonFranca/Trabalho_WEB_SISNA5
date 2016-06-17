/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unp.five.facade;

import br.unp.five.models.Classificacao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cleiton França
 */
@Stateless
public class ClassificacaoFacade extends AbstractFacade<Classificacao> {

    @PersistenceContext(unitName = "FiveEventosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassificacaoFacade() {
        super(Classificacao.class);
    }
    
}
