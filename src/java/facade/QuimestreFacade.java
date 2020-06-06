/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import Persis.Quimestre;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Persis.Quimestre_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Persis.Registro;
import java.util.Collection;

/**
 *
 * @author userAT
 */
@Stateless
public class QuimestreFacade extends AbstractFacade<Quimestre> {

    @PersistenceContext(unitName = "AppWebEduControlPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuimestreFacade() {
        super(Quimestre.class);
    }

    public boolean isRegistroCollectionEmpty(Quimestre entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Quimestre> quimestre = cq.from(Quimestre.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(quimestre, entity), cb.isNotEmpty(quimestre.get(Quimestre_.registroCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Registro> findRegistroCollection(Quimestre entity) {
        Quimestre mergedEntity = this.getMergedEntity(entity);
        Collection<Registro> registroCollection = mergedEntity.getRegistroCollection();
        registroCollection.size();
        return registroCollection;
    }
    
}
