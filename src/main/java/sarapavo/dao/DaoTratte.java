package sarapavo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import sarapavo.entities.Mezzo;

import java.util.List;

public class DaoTratte {
    private final EntityManager em;

    public DaoTratte(EntityManager em) {
        this.em = em;
    }

    public void getTempoMedioTratta(String zonaPartenza, String capolinea){
        TypedQuery<Double> query = em.createNamedQuery("Tratta.averageTravelTimeForSpecificRoute", Double.class);
        query.setParameter("zonaPartenza", zonaPartenza);
        query.setParameter("capolinea",capolinea);

        Double avgTempoPrevisto = query.getSingleResult();

        if(avgTempoPrevisto != null){
            System.out.println("Tempo medio previsto per la tratta " + zonaPartenza + " - " + capolinea + ": " + avgTempoPrevisto + " minuti");
        }else{
            System.out.println("Non ci sono dati per questa tratta");
        }
    }
}
