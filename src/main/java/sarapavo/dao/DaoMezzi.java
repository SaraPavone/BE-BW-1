package sarapavo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import sarapavo.entities.Mezzo;
import sarapavo.entities.enums.TipoMezzi;

import java.time.LocalDate;
import java.util.List;

public class DaoMezzi {
    private final EntityManager em;

    public DaoMezzi(EntityManager em) {
        this.em = em;
    }

    public void conteggioBigliettiVidimatiPerMezzo() {
        try {
            TypedQuery<Object[]> query = em.createNamedQuery("Mezzo.countValidatedTickets", Object[].class);
            List<Object[]> risultati = query.getResultList();

            if (risultati.isEmpty()) {
                System.out.println("Nessun biglietto vidimato trovato.");
                return;
            }

            for (Object[] risultato : risultati) {
                String mezzo = (String) risultato[0];
                Long numBigliettiVidimati = (Long) risultato[1];
                System.out.println("Mezzo: " + mezzo + ", Numero Biglietti Vidimati: " + numBigliettiVidimati);
            }
        } catch (Exception e) {
            System.err.println("Errore durante il recupero dei biglietti vidimati: " + e.getMessage());
        }
    }

    public void periodiDiManutenzioneeServizio() {
        try {
            TypedQuery<Object[]> query = em.createNamedQuery("Mezzo.maintenancePeriods", Object[].class);
            List<Object[]> risultati = query.getResultList();

            if (risultati.isEmpty()) {
                System.out.println("Nessun periodo di manutenzione trovato.");
                return;
            }

            for (Object[] risultato : risultati) {
                TipoMezzi mezzo = (TipoMezzi) risultato[0];
                LocalDate dataInizio = (LocalDate) risultato[1];
                LocalDate dataFine = (LocalDate) risultato[2];
                Boolean isManutenzione = (Boolean) risultato[3];
                System.out.println("Mezzo: " + mezzo +
                        ", Data Inizio: " + dataInizio +
                        ", Data Fine: " + dataFine +
                        ", In Manutenzione: " + isManutenzione);
            }
        } catch (Exception e) {
            System.err.println("Errore durante il recupero dei periodi di manutenzione: " + e.getMessage());
        }
    }

    public void conteggioBigliettiVidimatiPerLassoDiTempo(LocalDate startDate, LocalDate endDate) {
        try {
            TypedQuery<Object[]> query = em.createNamedQuery("Mezzo.countValidatedTicketsInTimeRange", Object[].class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            List<Object[]> risultati = query.getResultList();

            if (risultati.isEmpty()) {
                System.out.println("Nessun biglietto vidimato trovato nel lasso di tempo specificato.");
                return;
            }

            for (Object[] risultato : risultati) {
                Mezzo mezzo = (Mezzo) risultato[0];
                Long count = (Long) risultato[1];

                System.out.println("Mezzo: " + mezzo.getId() + ", Numero di biglietti vidimati: " + count);
            }
        } catch (Exception e) {
            System.err.println("Errore durante il recupero dei biglietti vidimati: " + e.getMessage());
        }
    }
}
