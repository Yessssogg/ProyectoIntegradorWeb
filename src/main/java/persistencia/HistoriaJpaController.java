/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Detalles;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Historia;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Nesto
 */
public class HistoriaJpaController implements Serializable {

    public HistoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
public HistoriaJpaController() {
        emf = Persistence.createEntityManagerFactory("ConsultorioClinico_PU");
    }
    public void create(Historia historia) {
        if (historia.getDetallesAtencion() == null) {
            historia.setDetallesAtencion(new ArrayList<Detalles>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detalles> attachedDetallesAtencion = new ArrayList<Detalles>();
            for (Detalles detallesAtencionDetallesToAttach : historia.getDetallesAtencion()) {
                detallesAtencionDetallesToAttach = em.getReference(detallesAtencionDetallesToAttach.getClass(), detallesAtencionDetallesToAttach.getId_detalles());
                attachedDetallesAtencion.add(detallesAtencionDetallesToAttach);
            }
            historia.setDetallesAtencion(attachedDetallesAtencion);
            em.persist(historia);
            for (Detalles detallesAtencionDetalles : historia.getDetallesAtencion()) {
                Historia oldHistoriaClinicaOfDetallesAtencionDetalles = detallesAtencionDetalles.getHistoriaClinica();
                detallesAtencionDetalles.setHistoriaClinica(historia);
                detallesAtencionDetalles = em.merge(detallesAtencionDetalles);
                if (oldHistoriaClinicaOfDetallesAtencionDetalles != null) {
                    oldHistoriaClinicaOfDetallesAtencionDetalles.getDetallesAtencion().remove(detallesAtencionDetalles);
                    oldHistoriaClinicaOfDetallesAtencionDetalles = em.merge(oldHistoriaClinicaOfDetallesAtencionDetalles);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historia historia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historia persistentHistoria = em.find(Historia.class, historia.getId_historia());
            List<Detalles> detallesAtencionOld = persistentHistoria.getDetallesAtencion();
            List<Detalles> detallesAtencionNew = historia.getDetallesAtencion();
            List<Detalles> attachedDetallesAtencionNew = new ArrayList<Detalles>();
            for (Detalles detallesAtencionNewDetallesToAttach : detallesAtencionNew) {
                detallesAtencionNewDetallesToAttach = em.getReference(detallesAtencionNewDetallesToAttach.getClass(), detallesAtencionNewDetallesToAttach.getId_detalles());
                attachedDetallesAtencionNew.add(detallesAtencionNewDetallesToAttach);
            }
            detallesAtencionNew = attachedDetallesAtencionNew;
            historia.setDetallesAtencion(detallesAtencionNew);
            historia = em.merge(historia);
            for (Detalles detallesAtencionOldDetalles : detallesAtencionOld) {
                if (!detallesAtencionNew.contains(detallesAtencionOldDetalles)) {
                    detallesAtencionOldDetalles.setHistoriaClinica(null);
                    detallesAtencionOldDetalles = em.merge(detallesAtencionOldDetalles);
                }
            }
            for (Detalles detallesAtencionNewDetalles : detallesAtencionNew) {
                if (!detallesAtencionOld.contains(detallesAtencionNewDetalles)) {
                    Historia oldHistoriaClinicaOfDetallesAtencionNewDetalles = detallesAtencionNewDetalles.getHistoriaClinica();
                    detallesAtencionNewDetalles.setHistoriaClinica(historia);
                    detallesAtencionNewDetalles = em.merge(detallesAtencionNewDetalles);
                    if (oldHistoriaClinicaOfDetallesAtencionNewDetalles != null && !oldHistoriaClinicaOfDetallesAtencionNewDetalles.equals(historia)) {
                        oldHistoriaClinicaOfDetallesAtencionNewDetalles.getDetallesAtencion().remove(detallesAtencionNewDetalles);
                        oldHistoriaClinicaOfDetallesAtencionNewDetalles = em.merge(oldHistoriaClinicaOfDetallesAtencionNewDetalles);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = historia.getId_historia();
                if (findHistoria(id) == null) {
                    throw new NonexistentEntityException("The historia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historia historia;
            try {
                historia = em.getReference(Historia.class, id);
                historia.getId_historia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historia with id " + id + " no longer exists.", enfe);
            }
            List<Detalles> detallesAtencion = historia.getDetallesAtencion();
            for (Detalles detallesAtencionDetalles : detallesAtencion) {
                detallesAtencionDetalles.setHistoriaClinica(null);
                detallesAtencionDetalles = em.merge(detallesAtencionDetalles);
            }
            em.remove(historia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historia> findHistoriaEntities() {
        return findHistoriaEntities(true, -1, -1);
    }

    public List<Historia> findHistoriaEntities(int maxResults, int firstResult) {
        return findHistoriaEntities(false, maxResults, firstResult);
    }

    private List<Historia> findHistoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historia.class));
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

    public Historia findHistoria(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historia.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historia> rt = cq.from(Historia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
