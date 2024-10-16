package sarapavo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sarapavo.entities.Abbonamento;
import sarapavo.entities.Tessera;

import java.util.Objects;
import java.util.UUID;

public class GenericDao {

    private final EntityManager geppetto;

    public GenericDao(EntityManager em){
        this.geppetto = em;
    }

    public <T> void save (T obj){
        EntityTransaction t = geppetto.getTransaction();
        t.begin();
        geppetto.persist(obj);
        t.commit();
        System.out.println(obj + "Saved!!");
    }

    public <T> T getElementById(Class<T> entityClass, long id) throws Exception{
        T found = geppetto.find(entityClass, id);
        if(found==null) throw new Exception("Not found");
        return found;
    }

    public <T> void delete (Class <T> entityClass, long id) throws Exception{
        T obj = this.getElementById(entityClass,id);
        EntityTransaction t = geppetto.getTransaction();
        t.begin();
        geppetto.remove(obj);
        t.commit();
        System.out.println(obj + " Deleted!");
    }


    public <E,V> void update(E entity, String attributeName, V newValue, String field, Object value) throws Exception{
        EntityTransaction transaction = geppetto.getTransaction();
        transaction.begin();

        String entityName = entity.getClass().getSimpleName();
        String queryString = "UPDATE" + entity + "e SET e." + attributeName + " = :newValue WHERE e." + field + " = :value";
        Query query = geppetto.createQuery((queryString));

        query.setParameter("newValue", newValue);
        query.setParameter("value",value);

        int numModificati = query.executeUpdate();
        transaction.commit();

        System.out.println(numModificati + " elementi sono stati modificati con successo");
    }




}
