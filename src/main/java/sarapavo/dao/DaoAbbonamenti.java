package sarapavo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import sarapavo.entities.Abbonamento;

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

    public Map<Abbonamento,Integer> conteggioAbbonamentiPE(){
        TypedQuery<Object[]> query =
                em.createNamedQuery("countAbbonamentiP", Object[].class);
        List<Object[]> risultati = query.getResultList();
        Map<Abbonamento, Integer> abbonamentiCount = new HashMap<>();
        for(Object[] result:risultati){
            Abbonamento abbonamento = (Abbonamento)result[0];
            Long count = (Long)result[1];
            abbonamentiCount.put(abbonamento, count.intValue());
        }
        return abbonamentiCount;
    }



}
