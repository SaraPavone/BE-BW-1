package sarapavo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sarapavo.dao.DaoAbbonamenti;
import sarapavo.dao.DaoPE;
import sarapavo.dao.GenericDao;
import sarapavo.entities.Biglietto;
import sarapavo.entities.Mezzo;
import sarapavo.entities.User;
import sarapavo.utils.Autogestionale;

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

        System.out.println("Benvenuti in MyTransport!");
        System.out.println("Inserisci il tuo ID:");
        long id = Long.parseLong(scanner.nextLine());
        User userFound = dao.getElementById(User.class, id);

        if (userFound.getAdmin()) {
            switch (Autogestionale.menuSelezione(scanner, "Gestione mezzi,Statistiche")) {
                case 1:
                    System.out.println("Lista mezzi ____ DA COMPLETARE");

                    System.out.println("Inserisci l'ID del mezzo per gestire: ");
                    long idMezzo = Long.parseLong(scanner.nextLine());
                    Mezzo mezzoFound = dao.getElementById(Mezzo.class, idMezzo);

                    System.out.println("Dettagli Mezzo selezionato ____ DA COMPLETARE");

                    boolean ciccio = mezzoFound.isManutenzione();
                    System.out.println(ciccio ? ("Vuoi rimuovere il mezzo dalla manutenzione? s/n?") :
                            ("Vuoi mettere il mezzo in manutenzione? s/n?"));

                    char sn = scanner.nextLine().charAt(0);
                    if ((sn == 's')) {
                        mezzoFound.setManutenzione(!mezzoFound.isManutenzione());
                        System.out.println("Stato del mezzo aggiornato!");
                    } else {
                        mezzoFound.setManutenzione(mezzoFound.isManutenzione());
                        System.out.println("Stato del mezzo non aggiornato!");
                    }
                    break;
                case 2:
                    System.out.println("Statistiche generali ____ DA COMPLETARE");
                    System.out.println("Statistiche per lasso di tempo: ");
                    switch (Autogestionale.menuSelezione(scanner, "Numero biglietti vidimati,Numero biglietti e abbonamenti emessi ")) {
                        case 1:
                            System.out.println("Inserisci il periodo da verificare: ");
                            System.out.println("Inserisci la data di inizio ( AAAA-MM-GG ): ");
                            String dataInizio = scanner.nextLine();
                            LocalDate dataInizioF = LocalDate.parse(dataInizio);
                            System.out.println("Inserisci la data di fine ( AAAA-MM-GG  ");
                            String dataFine = scanner.nextLine();
                            LocalDate dataFineF = LocalDate.parse(dataFine);
                            System.out.println(dataInizioF + " " + dataFineF);
                            break;
                        case 2:
                            System.out.println("Inserisci il periodo da verificare: ");
                            System.out.println("Inserisci la data di inizio ( AAAA-MM-GG ): ");
                            dataInizio = scanner.nextLine();
                            dataInizioF = LocalDate.parse(dataInizio);
                            System.out.println("Inserisci la data di fine ( AAAA-MM-GG  ");
                            dataFine = scanner.nextLine();
                            dataFineF = LocalDate.parse(dataFine);
                            System.out.println(dataInizioF + " " + dataFineF);
                            break;
                    }
            }
        } else if (!userFound.getAdmin()) {
            System.out.println("Cosa vuoi fare? ");
            switch (Autogestionale.menuSelezione(scanner, "Vidimare,Acquistare,Verifica abbonamento ")) {
                case 1:
                    switch (Autogestionale.menuSelezione(scanner, "Hai il biglietto?\nsi,no ")) {

                        case 1:
                            System.out.println("Inserisci il numero del biglietto: ");
                            long numBiglietto = Long.parseLong(scanner.nextLine());
                            Biglietto bigliettoFound = dao.getElementById(Biglietto.class, numBiglietto);
                            System.out.println("Elenco tratte ____ DA COMPLETARE");
                            System.out.println("Seleziona una tratta: ");
                            bigliettoFound.setData_vidimazione(LocalDate.now());
                            //SIAMO ARRIVATI QUI, DOMANI ANDIAMO AVANTI, DOBBIAMO SOSTITUIRE IL SETDATA CON IL GENERICS UPDATE

                    }
            }
        }

    }


}


//Scanner in  = new Scanner(System.in);
//String opzioni = "Aggiungi utente,Compra droga,Vendi droga";
//        switch(Autogestionale.menuSelezione(in,opzioni)){
//        case 1:
//        System.out.println("vaffanculo");break;
//default:
//        System.out.println("fuck");
//       }