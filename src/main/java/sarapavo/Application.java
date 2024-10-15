package sarapavo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sarapavo.dao.GenericDao;
import sarapavo.entities.*;
import sarapavo.entities.enums.TipiAbbonamento;
import sarapavo.entities.enums.TipoMezzi;

import java.time.LocalDate;
import java.util.ArrayList;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("be_bw_1");
    public static void main(String[] args) {

        EntityManager em =  emf.createEntityManager();
        GenericDao dao  = new GenericDao(em);


//        dao.save(new User("Maria", "Rossi", LocalDate.of(1985, 12, 10), false));
//        dao.save(new User("Luca", "Bianchi", LocalDate.of(1991, 6, 22), false));
//        dao.save(new User("Marco", "Verdi", LocalDate.of(1989, 11, 17), true));
//        dao.save(new User("Giulia", "Neri", LocalDate.of(1993, 8, 5), false));
//        dao.save(new User("Andrea", "Gialli", LocalDate.of(2000, 3, 30), true));

//        User user1 = new User("Giovanni", "Rosa", LocalDate.of(1990, 4, 12), false);
//        dao.save(user1);
//        User user2 = new User("Anna", "Blu", LocalDate.of(1980, 7, 9), false);
//        dao.save(user2);
//        User user3 = new User("Giorgia", "Verdi", LocalDate.of(1975, 11, 22), false);
//        dao.save(user3);
//        User user4 = new User("Sandro", "Giallo", LocalDate.of(1999, 8, 15), true);
//        dao.save(user4);
//        User user5 = new User("Franco", "Nero", LocalDate.of(1985, 1, 30), false);
//        dao.save(user5);
//        dao.save(new Tessera(user1));
//        dao.save(new Tessera(user2));
//        dao.save(new Tessera(user3));
//        dao.save(new Tessera(user4));
//        dao.save(new Tessera(user5));

//        dao.save(new DistributoreAutomatico("Punto K",false));
//        dao.save(new Rivenditore("Punto L"));
//        dao.save(new DistributoreAutomatico("Punto M",true));
//        dao.save(new Rivenditore("Punto N"));
//        dao.save(new DistributoreAutomatico("Punto O",false));

//        dao.save(new DistributoreAutomatico("Distributore 1", false));
//        dao.save(new DistributoreAutomatico("Distributore 2", true));
//        dao.save(new DistributoreAutomatico("Distributore 3", false));
//        dao.save(new DistributoreAutomatico("Distributore 4", true));
//        dao.save(new DistributoreAutomatico("Distributore 5", false));

//        dao.save(new ParcoMezzi(new ArrayList<>()));

//        dao.save(new Periodo(new Mezzo(TipoMezzi.TRAM, false, new ArrayList<>(), null, new ArrayList<>())));
//        dao.save(new Periodo(new Mezzo(TipoMezzi.AUTOBUS, true, new ArrayList<>(), null, new ArrayList<>())));
//        dao.save(new Periodo(new Mezzo(TipoMezzi.TRAM, false, new ArrayList<>(), null, new ArrayList<>())));
//        dao.save(new Periodo(new Mezzo(TipoMezzi.AUTOBUS, true, new ArrayList<>(), null, new ArrayList<>())));
//        dao.save(new Periodo(new Mezzo(TipoMezzi.TRAM, false, new ArrayList<>(), null, new ArrayList<>())));
//
//        dao.save(new Mezzo(TipoMezzi.TRAM, false, new ArrayList<>(), null, new ArrayList<>()));
//        dao.save(new Mezzo(TipoMezzi.AUTOBUS, true, new ArrayList<>(), null, new ArrayList<>()));
//        dao.save(new Mezzo(TipoMezzi.TRAM, false, new ArrayList<>(), null, new ArrayList<>()));
//        dao.save(new Mezzo(TipoMezzi.AUTOBUS, true, new ArrayList<>(), null, new ArrayList<>()));
//        dao.save(new Mezzo(TipoMezzi.TRAM, false, new ArrayList<>(), null, new ArrayList<>()));
//
//        dao.save(new Tratta(new Mezzo(TipoMezzi.TRAM, false, new ArrayList<>(), null, new ArrayList<>()), 30, "Capolinea A", "Zona 1"));
//        dao.save(new Tratta(new Mezzo(TipoMezzi.AUTOBUS, true, new ArrayList<>(), null, new ArrayList<>()), 45, "Capolinea B", "Zona 2"));
//        dao.save(new Tratta(new Mezzo(TipoMezzi.TRAM, false, new ArrayList<>(), null, new ArrayList<>()), 25, "Capolinea C", "Zona 3"));
//        dao.save(new Tratta(new Mezzo(TipoMezzi.AUTOBUS, true, new ArrayList<>(), null, new ArrayList<>()), 50, "Capolinea D", "Zona 4"));
//        dao.save(new Tratta(new Mezzo(TipoMezzi.TRAM, false, new ArrayList<>(), null, new ArrayList<>()), 35, "Capolinea E", "Zona 5"));
//
//        dao.save(new Biglietto(LocalDate.of(2024, 10, 10), new DistributoreAutomatico("Punto F",true), new Mezzo(TipoMezzi.TRAM, false, null, null, null)));
//        dao.save(new Biglietto(LocalDate.of(2024, 10, 11), new Rivenditore("Punto G"), new Mezzo(TipoMezzi.AUTOBUS, true, null, null, null)));
//        dao.save(new Biglietto(LocalDate.of(2024, 10, 12), new DistributoreAutomatico("Punto H",true), new Mezzo(TipoMezzi.TRAM, false, null, null, null)));
//        dao.save(new Biglietto(LocalDate.of(2024, 10, 13), new Rivenditore("Punto I"), new Mezzo(TipoMezzi.AUTOBUS, false, null, null, null)));
//        dao.save(new Biglietto(LocalDate.of(2024, 10, 14), new DistributoreAutomatico("Punto J",false), new Mezzo(TipoMezzi.TRAM, true, null, null, null)));
//
//        dao.save(new Abbonamento(new Tessera(new User("Mario", "Rossi", LocalDate.of(1985, 5, 20), false)), TipiAbbonamento.SETTIMANALE, new DistributoreAutomatico("Punto A",true)));
//        dao.save(new Abbonamento(new Tessera(new User("Lucia", "Bianchi", LocalDate.of(1990, 3, 10), false)), TipiAbbonamento.MENSILE, new Rivenditore("Punto B")));
//        dao.save(new Abbonamento(new Tessera(new User("Paolo", "Verdi", LocalDate.of(2000, 7, 15), false)), TipiAbbonamento.ANNUALE, new DistributoreAutomatico("Punto C",false)));
//        dao.save(new Abbonamento(new Tessera(new User("Gina", "Neri", LocalDate.of(1995, 1, 25), false)), TipiAbbonamento.SETTIMANALE, new Rivenditore("Punto D")));
//        dao.save(new Abbonamento(new Tessera(new User("Luca", "Gialli", LocalDate.of(1988, 12, 5), false)), TipiAbbonamento.MENSILE, new DistributoreAutomatico("Punto E",true)));

        Mezzo tram1 = new Mezzo(TipoMezzi.TRAM, false, new ArrayList<>(), null, new ArrayList<>());
        Mezzo autobus1 = new Mezzo(TipoMezzi.AUTOBUS, true, new ArrayList<>(), null, new ArrayList<>());
        dao.save(new Periodo(tram1));
        dao.save(new Periodo(autobus1));
        dao.save(new Periodo(tram1));
        dao.save(new Periodo(autobus1));
        dao.save(new Periodo(tram1));
        dao.save(tram1);
        dao.save(autobus1);
        dao.save(tram1);
        dao.save(autobus1);
        dao.save(tram1);

        Tratta tratta1 = new Tratta(tram1, 30, "Capolinea A", "Zona 1");
        Tratta tratta2 = new Tratta(autobus1, 45, "Capolinea B", "Zona 2");
        Tratta tratta3 = new Tratta(tram1, 25, "Capolinea C", "Zona 3");
        Tratta tratta4 = new Tratta(autobus1, 50, "Capolinea D", "Zona 4");
        Tratta tratta5 = new Tratta(tram1, 35, "Capolinea E", "Zona 5");
        dao.save(tratta1);
        dao.save(tratta2);
        dao.save(tratta3);
        dao.save(tratta4);
        dao.save(tratta5);

        Biglietto biglietto1 = new Biglietto(LocalDate.of(2024, 10, 10), new DistributoreAutomatico("Punto F", true), tram1);
        Biglietto biglietto2 = new Biglietto(LocalDate.of(2024, 10, 11), new Rivenditore("Punto G"), autobus1);
        Biglietto biglietto3 = new Biglietto(LocalDate.of(2024, 10, 12), new DistributoreAutomatico("Punto H", true), tram1);
        Biglietto biglietto4 = new Biglietto(LocalDate.of(2024, 10, 13), new Rivenditore("Punto I"), autobus1);
        Biglietto biglietto5 = new Biglietto(LocalDate.of(2024, 10, 14), new DistributoreAutomatico("Punto J", false), tram1);
        dao.save(biglietto1);
        dao.save(biglietto2);
        dao.save(biglietto3);
        dao.save(biglietto4);
        dao.save(biglietto5);

        Abbonamento abbonamento1 = new Abbonamento(new Tessera(new User("Mario", "Rossi", LocalDate.of(1985, 5, 20), false)), TipiAbbonamento.SETTIMANALE, new DistributoreAutomatico("Punto A", true));
        Abbonamento abbonamento2 = new Abbonamento(new Tessera(new User("Lucia", "Bianchi", LocalDate.of(1990, 3, 10), false)), TipiAbbonamento.MENSILE, new Rivenditore("Punto B"));
        Abbonamento abbonamento3 = new Abbonamento(new Tessera(new User("Paolo", "Verdi", LocalDate.of(2000, 7, 15), false)), TipiAbbonamento.ANNUALE, new DistributoreAutomatico("Punto C", false));
        Abbonamento abbonamento4 = new Abbonamento(new Tessera(new User("Gina", "Neri", LocalDate.of(1995, 1, 25), false)), TipiAbbonamento.SETTIMANALE, new Rivenditore("Punto D"));
        Abbonamento abbonamento5 = new Abbonamento(new Tessera(new User("Luca", "Gialli", LocalDate.of(1988, 12, 5), false)), TipiAbbonamento.MENSILE, new DistributoreAutomatico("Punto E", true));
        dao.save(abbonamento1);
        dao.save(abbonamento2);
        dao.save(abbonamento3);
        dao.save(abbonamento4);
        dao.save(abbonamento5);
    }
}