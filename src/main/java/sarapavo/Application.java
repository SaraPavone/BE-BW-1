package sarapavo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sarapavo.dao.*;
import sarapavo.entities.User;
import sarapavo.utils.Autogestionale;
import sarapavo.utils.Scannerini;

import java.util.Scanner;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("be_bw_1");

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();
        GenericDao dao = new GenericDao(em);
        DaoPE daope = new DaoPE(em);
        DaoAbbonamenti abbonamentidao = new DaoAbbonamenti(em);
        DaoMezzi mezziDao = new DaoMezzi(em);
        DaoTratte tratteDao = new DaoTratte(em);

        System.out.println("Benvenuti in MyTransport!");

        long id;
        User userFound;
        do {
            System.out.println("Inserisci il tuo ID:");
            id = Long.parseLong(scanner.nextLine());
            userFound = dao.getElementById(User.class, id);
            if (userFound == null) {
                System.out.println("ID non valido, riprova.");
            }
        } while (userFound == null);

        // Menu principale
        while (true) {
            if (userFound.getAdmin()) {
                // Menu per amministratori
                int scelta;
                do {
                    scelta = Autogestionale.menuSelezione(scanner, "Gestione mezzi,Statistiche");
                    switch (scelta) {
                        case 1:
                            Scannerini.gestisciMezzi(scanner, dao);
                            break;
                        case 2:
                            Scannerini.visualizzaStatistiche(scanner, daope, mezziDao, dao);
                            break;
                        case 3:
                            System.out.println("Uscita dal menu amministratore.");
                            break;
                        default:
                            System.out.println("Scelta non valida, riprova.");
                    }
                } while (scelta != 3);
            } else {
                // Menu per utenti normali
                int sceltaUtente;
                boolean flag = false;
                do {
                    sceltaUtente = Autogestionale.menuSelezione(scanner, "Vidimare,Acquistare biglietto,Verifica abbonamento,Acquista tessera,Acquista Abbonamento");
                    switch (sceltaUtente) {
                        case 1:
                            Scannerini.vidimareBiglietto(scanner, dao);
                            break;
                        case 2:
                            Scannerini.acquistareBiglietto(scanner, dao, userFound);
                            System.out.println("ciao");
                            break;
                        case 3:
                            Scannerini.verificaAbbonamento(scanner, dao, userFound);
                            break;
                        case 4:
                            Scannerini.acquistoTessera(scanner, dao, userFound);
                            break;
                        case 5:
                            Scannerini.acquistareAbbonamento(scanner, dao, userFound);
                            break;
                        default:
                            System.out.println("Scelta non valida, riprova.");
                    }
                } while (sceltaUtente != 6);
            }
        }
    }
}