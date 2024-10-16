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

    public void conteggioAbbonamentiPE(){
        TypedQuery<Object[]> query = em.createNamedQuery("countAbbonamentiP", Object[].class);
        List<Object[]> risultati = query.getResultList();

        // Processa e stampa i risultati
        for (Object[] risultato : risultati) {
            PuntoEmissione puntoEmissione = (PuntoEmissione) risultato[0];
            Long count = (Long) risultato[1];

            System.out.println("Punto di emissione: " + puntoEmissione.getNome_punto_emissione());
            System.out.println("Numero di abbonamenti emessi: " + count);
        }
    }



}
