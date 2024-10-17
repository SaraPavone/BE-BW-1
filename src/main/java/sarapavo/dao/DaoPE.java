package sarapavo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import sarapavo.entities.PuntoEmissione;

import java.time.LocalDate;
import java.util.List;

public class DaoPE {

    private final EntityManager em;
    public DaoPE(EntityManager em){
        this.em = em;
    }
    public void getNumeroAbbonamenti() {
        try {
            TypedQuery<Object[]> query = em.createNamedQuery("PuntoEmissione.countTicketsAndSubscriptions", Object[].class);
            List<Object[]> risultati = query.getResultList();

            if (risultati.isEmpty()) {
                System.out.println("Nessun punto di emissione trovato.");
                return;
            }

            for (Object[] risultato : risultati) {
                String nomePuntoEmissione = (String) risultato[0];
                Long numeroAbbonamenti = (Long) risultato[1];
                System.out.println("Punto Emissione: " + nomePuntoEmissione +
                        ", Numero Abbonamenti: " + numeroAbbonamenti);
            }
        } catch (Exception e) {
            System.err.println("Errore durante il recupero dei dati: " + e.getMessage());
        }
    }

    public void conteggioBigliettiEmessiPerPE() {
        try {
            TypedQuery<Object[]> query = em.createNamedQuery("PuntoEmissione.countIssuedTickets", Object[].class);
            List<Object[]> risultati = query.getResultList();

            if (risultati.isEmpty()) {
                System.out.println("Nessun biglietto emesso trovato.");
                return;
            }

            for (Object[] risultato : risultati) {
                PuntoEmissione puntoEmissione = (PuntoEmissione) risultato[0];
                Long count = (Long) risultato[1];

                System.out.println("Punto di emissione: " + puntoEmissione.getNome_punto_emissione());
                System.out.println("Numero di biglietti emessi: " + count);
            }
        } catch (Exception e) {
            System.err.println("Errore durante il recupero dei biglietti emessi: " + e.getMessage());
        }
    }

    public void conteggioBigliettiEAbbonamentiPerLassoDiTempo(LocalDate startDate, LocalDate endDate) {
        try {
            TypedQuery<Object[]> query = em.createNamedQuery("countTicketsAndSubscriptionsInTimeRange", Object[].class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            List<Object[]> risultati = query.getResultList();

            if (risultati.isEmpty()) {
                System.out.println("Nessun biglietto o abbonamento trovato nel lasso di tempo specificato.");
                return;
            }

            for (Object[] risultato : risultati) {
                PuntoEmissione puntoEmissione = (PuntoEmissione) risultato[0];
                Long countTickets = (Long) risultato[1];
                Long countSubscriptions = (Long) risultato[2];

                System.out.println("Punto di emissione: " + puntoEmissione.getNome_punto_emissione());
                System.out.println("Numero di biglietti emessi: " + countTickets);
                System.out.println("Numero di abbonamenti emessi: " + countSubscriptions);
            }
        } catch (Exception e) {
            System.err.println("Errore durante il recupero dei biglietti e abbonamenti: " + e.getMessage());
        }
    }
}
