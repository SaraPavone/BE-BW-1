package sarapavo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sarapavo.dao.*;
import sarapavo.entities.*;
import sarapavo.entities.enums.TipiAbbonamento;
import sarapavo.utils.Autogestionale;
import sarapavo.utils.Scannerini;

import java.time.LocalDate;
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
                    scelta = Autogestionale.menuSelezione(scanner, "Gestione mezzi,Statistiche,Esci");
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
                do {
                    sceltaUtente = Autogestionale.menuSelezione(scanner, "Vidimare,Acquistare,Verifica abbonamento,Esci");
                    switch (sceltaUtente) {
                        case 1:
                            Scannerini.vidimareBiglietto(scanner, dao);
                            break;
                        case 2:
                            Scannerini.acquistareBiglietto(scanner, dao, userFound);
                            break;
                        case 3:
                            Scannerini.verificaAbbonamento(scanner, dao, userFound);
                            break;
                        case 4:
                            System.out.println("Uscita dal menu utente.");
                            break;
                        default:
                            System.out.println("Scelta non valida, riprova.");
                    }
                } while (sceltaUtente != 4);
            }
        }
    }
}