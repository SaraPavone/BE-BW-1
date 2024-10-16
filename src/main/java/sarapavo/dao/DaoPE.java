package sarapavo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DaoPE {

    private final EntityManager em;
    public DaoPE(EntityManager em){
        this.em = em;
    }

    public void getNumeroAbbonamenti(){
        TypedQuery<Object[]> query = em.createNamedQuery("PuntoEmissione.countTicketsAndSubscriptions", Object[].class);
        for (Object[] risultato : query.getResultList()) {
            String nomePuntoEmissione = (String) risultato[0];
            Long numeroBiglietti = (Long) risultato[1];
            System.out.println("Punto Emissione: " + nomePuntoEmissione +
                    ", Numero Abbonamenti: " + numeroBiglietti);
        }
    }


}
