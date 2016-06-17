/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unp.five.facade;

import br.unp.five.models.Favorit;
import br.unp.five.models.Periodicos;
import br.unp.five.models.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Cleiton França
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void createFavoritos(Favorit entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public Usuario findUser(Object id) {
        return getEntityManager().find(Usuario.class, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<Periodicos> findByAreaAndClassificacao(Integer area, Integer classificacao, Integer idPeso, String titulo, String issn) {
        try {

            String sql = "SELECT * FROM Periodicos p ";

            if (area != null) {
                sql += "WHERE p.idArea = " + area + " ";
            }
            if (classificacao != null) {
                sql += "AND p.idClassificacao = " + classificacao + " ";
            }
            
            if (idPeso != null) {
                sql += "AND p.idPeso = " + idPeso + " ";
            }
            
            if (titulo.length() > 0) {
                sql += "AND p.titulo like '%" + titulo + "%' ";
            }
            
            if (issn.length() > 0) {
                sql = "SELECT * FROM Periodicos p WHERE p.issn = " + issn;
            }

            Query query = getEntityManager()
                    .createNativeQuery(sql, Periodicos.class);
            return query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public T find(String n, String s) {
        try {
            return (T) getEntityManager()
                    .createQuery("from Usuario u where u.nome = :nome and u.senha = :senha")
                    .setParameter("nome", n)
                    .setParameter("senha", s)
                    .getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
            return null;
        }

    }

}
