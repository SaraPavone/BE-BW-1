package sarapavo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import sarapavo.entities.Abbonamento;
import sarapavo.entities.PuntoEmissione;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class DaoAbbonamenti {
    private final EntityManager em;

    public DaoAbbonamenti(EntityManager em) {
        this.em = em;
    }

    public void conteggioAbbonamentiPE() {
        try {
            TypedQuery<Object[]> query = em.createNamedQuery("countAbbonamentiP", Object[].class);
            List<Object[]> risultati = query.getResultList();

            if (risultati.isEmpty()) {
                System.out.println("Nessun abbonamento trovato.");
                return;
            }

            for (Object[] risultato : risultati) {
                PuntoEmissione puntoEmissione = (PuntoEmissione) risultato[0];
                Long count = (Long) risultato[1];

                System.out.println("Punto di emissione: " + puntoEmissione.getNome_punto_emissione());
                System.out.println("Numero di abbonamenti emessi: " + count);
            }
        } catch (Exception e) {
            System.err.println("Errore durante il recupero degli abbonamenti: " + e.getMessage());
        }
    }



}
