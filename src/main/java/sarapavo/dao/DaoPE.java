package sarapavo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

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
}
