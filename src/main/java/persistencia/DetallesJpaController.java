
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Detalles;
import logica.Historia;
import persistencia.exceptions.NonexistentEntityException;

  
public class DetallesJpaController implements Serializable {

    public DetallesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
public DetallesJpaController() {
        emf = Persistence.createEntityManagerFactory("ConsultorioClinico_PU");
    }
    
    
    public void create(Detalles detalles) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historia historiaClinica = detalles.getHistoriaClinica();
            if (historiaClinica != null) {
                historiaClinica = em.getReference(historiaClinica.getClass(), historiaClinica.getId_historia());
                detalles.setHistoriaClinica(historiaClinica);
            }
            em.persist(detalles);
            if (historiaClinica != null) {
                historiaClinica.getDetallesAtencion().add(detalles);
                historiaClinica = em.merge(historiaClinica);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalles detalles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalles persistentDetalles = em.find(Detalles.class, detalles.getId_detalles());
            Historia historiaClinicaOld = persistentDetalles.getHistoriaClinica();
            Historia historiaClinicaNew = detalles.getHistoriaClinica();
            if (historiaClinicaNew != null) {
                historiaClinicaNew = em.getReference(historiaClinicaNew.getClass(), historiaClinicaNew.getId_historia());
                detalles.setHistoriaClinica(historiaClinicaNew);
            }
            detalles = em.merge(detalles);
            if (historiaClinicaOld != null && !historiaClinicaOld.equals(historiaClinicaNew)) {
                historiaClinicaOld.getDetallesAtencion().remove(detalles);
                historiaClinicaOld = em.merge(historiaClinicaOld);
            }
            if (historiaClinicaNew != null && !historiaClinicaNew.equals(historiaClinicaOld)) {
                historiaClinicaNew.getDetallesAtencion().add(detalles);
                historiaClinicaNew = em.merge(historiaClinicaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = detalles.getId_detalles();
                if (findDetalles(id) == null) {
                    throw new NonexistentEntityException("The detalles with id " + id + " no longer exists.");
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
            Detalles detalles;
            try {
                detalles = em.getReference(Detalles.class, id);
                detalles.getId_detalles();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalles with id " + id + " no longer exists.", enfe);
            }
            Historia historiaClinica = detalles.getHistoriaClinica();
            if (historiaClinica != null) {
                historiaClinica.getDetallesAtencion().remove(detalles);
                historiaClinica = em.merge(historiaClinica);
            }
            em.remove(detalles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalles> findDetallesEntities() {
        return findDetallesEntities(true, -1, -1);
    }

    public List<Detalles> findDetallesEntities(int maxResults, int firstResult) {
        return findDetallesEntities(false, maxResults, firstResult);
    }

    private List<Detalles> findDetallesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalles.class));
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

    public Detalles findDetalles(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalles.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalles> rt = cq.from(Detalles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
