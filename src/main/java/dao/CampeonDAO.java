/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import dao.exceptions.RollbackFailureException;
import entities.Campeon;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author yorsh
 */
public class CampeonDAO implements Serializable {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");

    public CampeonDAO( EntityManagerFactory emf) {
        this.emf = emf;
    }
    public CampeonDAO(){
}
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Campeon campeon) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(campeon);
            em.getTransaction().commit();
        } catch (IllegalStateException | SecurityException  ex) {
            if (findCampeon(campeon.getNombre()) != null) {
                throw new PreexistingEntityException("Campeon " + campeon + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Campeon campeon) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            campeon = em.merge(campeon);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = campeon.getNombre();
                if (findCampeon(id) == null) {
                    throw new NonexistentEntityException("The campeon with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Campeon campeon;
            try {
                campeon = em.getReference(Campeon.class, id);
                campeon.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The campeon with id " + id + " no longer exists.", enfe);
            }
            em.remove(campeon);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Campeon> findCampeonEntities() {
        return findCampeonEntities(true, -1, -1);
    }

    public List<Campeon> findCampeonEntities(int maxResults, int firstResult) {
        return findCampeonEntities(false, maxResults, firstResult);
    }

    private List<Campeon> findCampeonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Campeon.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Campeon findCampeon(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Campeon.class, id);
        } finally {
            em.close();
        }
    }

    public int getCampeonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Campeon> rt = cq.from(Campeon.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
