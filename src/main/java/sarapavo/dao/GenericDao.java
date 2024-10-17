package sarapavo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import sarapavo.entities.*;
import sarapavo.entities.enums.TipiAbbonamento;
import sarapavo.entities.enums.TipoMezzi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class GenericDao {

    private final EntityManager em;

    public GenericDao(EntityManager em){
        this.em = em;
    }

    public <T> void save(T obj) {
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(obj);
            t.commit();
            System.out.println(obj + " Saved!!");
        } catch (Exception e) {
            t.rollback();
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public <T> T getElementById (Class<T> entityClass, long id) throws Exception{
        T found = em.find(entityClass, id);
        if(found==null) throw new Exception("Not found");
        return found;
    }

    public <T> void delete (Class <T> entityClass, long id) throws Exception{
        T obj = this.getElementById(entityClass,id);
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(obj);
        t.commit();
        System.out.println(obj + " Deleted!");
    }

    public <E, V> void update(E entity, String attributeName, V newValue, String field, Object value) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            String entityName = entity.getClass().getSimpleName();
            String queryString = "UPDATE " + entityName + " e SET e." + attributeName + " = :newValue WHERE e." + field + " = :value";
            Query query = em.createQuery(queryString);
            query.setParameter("newValue", newValue);
            query.setParameter("value", value);
            int numModificati = query.executeUpdate();
            transaction.commit();
            System.out.println(numModificati + " elementi sono stati modificati con successo");
        } catch (Exception e) {
            transaction.rollback();
            System.err.println("Error updating: " + e.getMessage());
        }
    }

    public <T> List<T> findAll(Class<T> entityClass){
        List<T> risultati = new ArrayList<>();
        try{
            String query = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            TypedQuery<T> queryAll = em.createQuery(query,entityClass);
            risultati = queryAll.getResultList();
            System.out.println("Trovati "+ risultati.size() + "elementi di tipo "+ entityClass.getSimpleName());
        }catch(Exception e){
            System.out.println("Errore " + e.getMessage());
        }
        return risultati;
    }
    


}
