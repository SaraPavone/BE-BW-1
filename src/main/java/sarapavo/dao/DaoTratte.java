package sarapavo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DaoTratte {
    private final EntityManager em;

    public DaoTratte(EntityManager em) {
        this.em = em;
    }

    public void getTempoMedioTratta(String zonaPartenza, String capolinea) {
        if (zonaPartenza == null || zonaPartenza.isEmpty() || capolinea == null || capolinea.isEmpty()) {
            System.out.println("Zona di partenza e capolinea devono essere forniti.");
            return;
        }

        try {
            TypedQuery<Double> query = em.createNamedQuery("Tratta.averageTravelTimeForSpecificRoute", Double.class);
            query.setParameter("zonaPartenza", zonaPartenza);
            query.setParameter("capolinea", capolinea);

            Double avgTempoPrevisto = query.getSingleResult();

            if (avgTempoPrevisto != null) {
                System.out.println("Tempo medio previsto per la tratta " + zonaPartenza + " - " + capolinea + ": " + avgTempoPrevisto + " minuti");
            } else {
                System.out.println("Non ci sono dati per questa tratta");
            }
        } catch (NullPointerException e) {
            System.out.println("Nessun risultato trovato per la tratta " + zonaPartenza + " - " + capolinea);
        } catch (Exception e) {
            System.err.println("Errore durante il recupero del tempo medio: " + e.getMessage());
        }
    }
}
