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


    public void update (Tessera t, Abbonamento abb) throws Exception{

        EntityTransaction transaction = geppetto.getTransaction(); // Siccome non stiamo facendo una semplice lettura ma stiamo modificando le tabelle dobbiamo usare le transactions
        transaction.begin();
        Query query = geppetto.createQuery("UPDATE Tessera a SET a.abbonamento = :newName WHERE a.numero_tessera = :oldName"); // UPDATE animals SET name = 'Nuovonome' WHERE name = 'Vecchionome'
        query.setParameter("newName", abb);
        query.setParameter("oldName", t.getNumero_tessera());

        int numModificati = query.executeUpdate(); // esegue le query di tipo UPDATE e DELETE

        transaction.commit();

        System.out.println(numModificati + " elementi sono stati modificati con successo");
    }



}
