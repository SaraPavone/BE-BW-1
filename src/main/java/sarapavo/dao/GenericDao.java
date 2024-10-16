package sarapavo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sarapavo.entities.*;
import sarapavo.entities.enums.TipiAbbonamento;
import sarapavo.entities.enums.TipoMezzi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    public <T> T getElementById (Class<T> entityClass, long id) throws Exception{
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

    public <E, V> void update (E entity, String attributeName, V newValue, String field, Object value) throws Exception{
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

    public void populate() {
        User user1 = new User("Mario", "Rossi", LocalDate.of(1985, 5, 15), false);
        User user2 = new User("Luca", "Bianchi", LocalDate.of(1990, 3, 25), false);
        save(user1);
        save(user2);

        Tessera tessera1 = new Tessera(user1);
        Tessera tessera2 = new Tessera(user2);
        save(tessera1);
        save(tessera2);


        PuntoEmissione rivenditore = new Rivenditore("Rivenditore Centrale");
        DistributoreAutomatico distributore = new DistributoreAutomatico("Distributore Automatico 1", false);
        save(rivenditore);
        save(distributore);

        Abbonamento abbonamento1 = new Abbonamento(tessera1, TipiAbbonamento.MENSILE, rivenditore);
        Abbonamento abbonamento2 = new Abbonamento(tessera2, TipiAbbonamento.SETTIMANALE, distributore);
        save(abbonamento1);
        save(abbonamento2);

        Mezzo mezzo1 = new Mezzo(TipoMezzi.AUTOBUS, false, new ArrayList<>(), null, new ArrayList<>());
        Mezzo mezzo2 = new Mezzo(TipoMezzi.TRAM, false, new ArrayList<>(), null, new ArrayList<>());
        save(mezzo1);
        save(mezzo2);

        Tratta tratta1 = new Tratta(mezzo1, 30, "Stazione Centrale", "Piazza Roma");
        Tratta tratta2 = new Tratta(mezzo2, 20, "Capolinea 1", "Capolinea 2");
        save(tratta1);
        save(tratta2);

        Periodo periodo1 = new Periodo(mezzo1);
        Periodo periodo2 = new Periodo(mezzo2);
        save(periodo1);
        save(periodo2);

        ParcoMezzi parcoMezzi = new ParcoMezzi();
        List<Mezzo> mezziList = new ArrayList<>();
        mezziList.add(mezzo1);
        mezziList.add(mezzo2);
        parcoMezzi.setMezzi(mezziList);
        save(parcoMezzi);

        mezzo1.setParcomezzi(parcoMezzi);
        mezzo2.setParcomezzi(parcoMezzi);
        save(mezzo1);
        save(mezzo2);

        Biglietto biglietto1 = new Biglietto(LocalDate.now(), rivenditore, mezzo1);
        Biglietto biglietto2 = new Biglietto(LocalDate.now(), distributore, mezzo2);
        save(biglietto1);
        save(biglietto2);

    }




}
