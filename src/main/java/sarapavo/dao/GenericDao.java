package sarapavo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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

}
