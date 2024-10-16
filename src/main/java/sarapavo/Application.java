package sarapavo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sarapavo.dao.DaoAbbonamenti;
import sarapavo.dao.DaoPE;
import sarapavo.dao.GenericDao;
import sarapavo.entities.*;
import sarapavo.entities.enums.TipiAbbonamento;
import sarapavo.entities.enums.TipoMezzi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("be_bw_1");
    public static void main(String[] args) throws Exception {

        EntityManager em =  emf.createEntityManager();
        GenericDao dao  = new GenericDao(em);
        DaoPE daope = new DaoPE(em);
        DaoAbbonamenti abbonamentidao = new DaoAbbonamenti(em);


    }
}