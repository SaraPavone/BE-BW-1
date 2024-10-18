package sarapavo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sarapavo.dao.GenericDao;
import sarapavo.entities.Mezzo;
import sarapavo.entities.ParcoMezzi;
import sarapavo.entities.Tratta;
import sarapavo.entities.enums.TipoMezzi;

public class App {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("be_bw_1");

    public static void main(String[] args) throws Exception {
        EntityManager em = emf.createEntityManager();
        GenericDao dao = new GenericDao(em);


        Mezzo m1 = new Mezzo(TipoMezzi.AUTOBUS, false, dao.getElementById(ParcoMezzi.class, 1L));
        dao.save(m1);
        Tratta t1 = new Tratta(dao.getElementById(Mezzo.class, 2L), 69, "Tokyo", "New York");
        dao.save(t1);
    }
}
