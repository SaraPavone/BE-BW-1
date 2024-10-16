package sarapavo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sarapavo.dao.DaoMezzi;
import sarapavo.dao.DaoPE;
import sarapavo.dao.DaoTratte;
import sarapavo.dao.GenericDao;
import sarapavo.entities.Mezzo;
import sarapavo.entities.Periodo;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("giovanni");


    public static void main(String[] args) throws Exception {

        EntityManager em =  emf.createEntityManager();
        GenericDao dao  = new GenericDao(em);
        DaoPE daope = new DaoPE(em);
        DaoMezzi daomezzi = new DaoMezzi(em);
        DaoTratte daotratte = new DaoTratte(em);
//        dao.populate();
        daope.getNumeroAbbonamenti();
        daomezzi.periodiDiManutenzioneeServizio();
        daotratte.getTempoMedioTratta("Piazza Roma","Stazione Centrale");

    }
}