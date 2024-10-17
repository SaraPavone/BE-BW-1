package sarapavo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import sarapavo.entities.enums.TipoMezzi;

import java.time.LocalDate;

public class DaoMezzi {
    private final EntityManager em;

    public DaoMezzi(EntityManager em) {
        this.em = em;
    }

    public void conteggioBigliettiVidimatiPerMezzo() {
        TypedQuery<Object[]> query = em.createNamedQuery("Mezzo.countValidatedTickets", Object[].class);
        for (Object[] risultato : query.getResultList()) {
            String mezzo = (String) risultato[0];
            Long numBigliettiVidimati = (Long) risultato[1];
            System.out.println("Mezzo: " + mezzo + ", Numero Biglietti Vidimati: " + numBigliettiVidimati);
        }
    }

    public void periodiDiManutenzioneeServizio() {
        TypedQuery<Object[]> query = em.createNamedQuery("Mezzo.maintenancePeriods", Object[].class);
        for (Object[] risultato : query.getResultList()) {
            TipoMezzi mezzo = (TipoMezzi) risultato[0];
            LocalDate dataInizio = (LocalDate) risultato[1];
            LocalDate dataFine = (LocalDate) risultato[2];
            Boolean isManutenzione = (Boolean) risultato[3];
            System.out.println("Mezzo: " + mezzo +
                    ", Data Inizio: " + dataInizio +
                    ", Data Fine: " + dataFine +
                    ", In Manutenzione: " + isManutenzione);
        }

    }
}
