package sarapavo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DaoPE {

    private final EntityManager penocchio;
    public DaoPE(EntityManager em){
        this.penocchio = em;
    }

    public List<Object[]> getNumeroBigliettoAndAbbonamenti(){
        TypedQuery<Object[]> query = penocchio.createNamedQuery("countBandB", Object[].class);
        return query.getResultList();
    }
}
