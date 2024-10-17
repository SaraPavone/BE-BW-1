package sarapavo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sarapavo.dao.DaoAbbonamenti;
import sarapavo.dao.DaoPE;
import sarapavo.dao.GenericDao;
import sarapavo.entities.*;
import sarapavo.entities.enums.TipiAbbonamento;
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
                            String dataInizio2 = scanner.nextLine();
                            LocalDate dataInizioF2 = LocalDate.parse(dataInizio2);
                            System.out.println("Inserisci la data di fine ( AAAA-MM-GG  ");
                            String dataFine2 = scanner.nextLine();
                            LocalDate dataFineF2 = LocalDate.parse(dataFine2);
                            System.out.println(dataInizioF2 + " " + dataFineF2);
                            break;
                    }
            }
        } else {
            System.out.println("Cosa vuoi fare? ");
            switch (Autogestionale.menuSelezione(scanner, "Vidimare,Acquistare,Verifica abbonamento ")) {
                //vidimare
                case 1:
                    switch (Autogestionale.menuSelezione(scanner, "Hai il biglietto?\nsi,no ")) {

                        case 1:
                            System.out.println("Inserisci il numero del biglietto: ");
                            long numBiglietto = Long.parseLong(scanner.nextLine());
                            Biglietto bigliettoFound = dao.getElementById(Biglietto.class, numBiglietto);
                            bigliettoFound.setData_vidimazione(LocalDate.now());
                            dao.update(Biglietto.class, "data_vidimazione", bigliettoFound, "id", dao.getElementById(Biglietto.class, numBiglietto).getId());
                            System.out.println("Biglietto vidimato con successo! ");
                            break;
                        case 2:
                            System.out.println("Inserisci l'ID del punto vendita dal quale stai acquistando il biglietto: ");
                            long idPE = Long.parseLong(scanner.nextLine());
                            PuntoEmissione puntoEmissioneF = dao.getElementById(PuntoEmissione.class, idPE);
                            System.out.println("Inserisci l'ID del mezzo su cui vuoi salire: ");
                            System.out.println("Elenco tratte + id mezzo ____ DA COMPLETARE");
                            long idMezzo = Long.parseLong(scanner.nextLine());
                            Mezzo mezzoF = dao.getElementById(Mezzo.class, idMezzo);
                            Biglietto bigliettoAcquistato = new Biglietto(puntoEmissioneF, mezzoF);
                            dao.save(bigliettoAcquistato);
                            System.out.println("Biglietto acquistato con successo! ");
                            System.out.println("Inserisci il numero del biglietto: ");
                            long numBigliett = Long.parseLong(scanner.nextLine());
                            Biglietto bigliettoF = dao.getElementById(Biglietto.class, numBigliett);
                            bigliettoF.setData_vidimazione(LocalDate.now());
                            dao.update(Biglietto.class, "data_vidimazione", bigliettoF, "id", dao.getElementById(Biglietto.class, numBigliett).getId());
                            System.out.println("Biglietto vidimato con successo! ");
                            break;
                        default:
                            System.out.println("Sei un Errore!!");
                            break;
                    }
                    //acquistare
                case 2:
                    System.out.println("Inserisci l'ID del punto vendita dal quale stai acquistando il biglietto: ");
                    long idPE = Long.parseLong(scanner.nextLine());
                    PuntoEmissione puntoEmissioneF = dao.getElementById(PuntoEmissione.class, idPE);
                    System.out.println("Cosa vuoi acquistare? ");
                    switch (Autogestionale.menuSelezione(scanner, "Biglietto,Abbonamento ")) {
                        //acquistare biglietto
                        case 1:
                            System.out.println("Inserisci l'ID del mezzo su cui vuoi salire: ");
                            System.out.println("Elenco tratte + id mezzo ____ DA COMPLETARE");
                            long idMezzo = Long.parseLong(scanner.nextLine());
                            Mezzo mezzoF = dao.getElementById(Mezzo.class, idMezzo);
                            Biglietto bigliettoAcquistato = new Biglietto(puntoEmissioneF, mezzoF);
                            dao.save(bigliettoAcquistato);
                            System.out.println("Biglietto acquistato con successo! ");
                            System.out.println("Vuoi vidimare il biglietto?\ns/n ");
                            char sn = scanner.nextLine().charAt(0);
                            if (sn == 's') {
                                System.out.println("Inserisci il numero del biglietto: ");
                                long numBigliett = Long.parseLong(scanner.nextLine());
                                Biglietto bigliettoF = dao.getElementById(Biglietto.class, numBigliett);
                                bigliettoF.setData_vidimazione(LocalDate.now());
                                dao.update(Biglietto.class, "data_vidimazione", bigliettoF, "id", dao.getElementById(Biglietto.class, numBigliett).getId());
                                System.out.println("Biglietto vidimato con successo! ");
                            } else {
                                System.out.println("Esci");
                            }
                            break;
                        //acquistare abbonamento
                        case 2:
                            if (userFound.getTessera() != null) {
                                System.out.println(userFound.getTessera());
                                // tessera scaduta
                                if (userFound.getTessera().getData_scadenza().isBefore(LocalDate.now())) {
                                    System.out.println("La tua tessera é scaduta, vuoi rinnovarla?\ns/n ");
                                    char sno = scanner.nextLine().charAt(0);
                                    if (sno == 's') {
                                        Tessera t = new Tessera(userFound);
                                        dao.save(t);
                                        System.out.println("Tessera " + t + " rinnovata con successo! ");

                                        System.out.println("Vuoi acquistare un abbonamento?\ns/n ");
                                        char sino = scanner.nextLine().charAt(0);
                                        if (sino == 's') {
                                            TipiAbbonamento tipo = switch (Autogestionale.menuSelezione(scanner, "Scegli il tipo di abbonamento:\nSettimanale,Mensile,Annuale ")) {
                                                case 2 -> TipiAbbonamento.MENSILE;
                                                case 3 -> TipiAbbonamento.ANNUALE;
                                                default -> TipiAbbonamento.SETTIMANALE;
                                            };
                                            Abbonamento newAbb = new Abbonamento(t, tipo, puntoEmissioneF);
                                            dao.save(newAbb);
                                            System.out.println("Abbonamento " + newAbb + " creato con successo!");
                                        } else {
                                            System.out.println("Uscito dal sistema!");
                                        }
                                    } else {
                                        System.out.println("Uscito dal sistema!");
                                    }
                                } // ho la tessera ma l'abbonamento é scaduto
                                else if (userFound.getTessera().getAbbonamento().getData_scadenza().isBefore(LocalDate.now())) {
                                    System.out.println("Il tuo abbonamento é scaduto, vuoi rinnovarlo?\ns/n ");
                                    char asino = scanner.nextLine().charAt(0);
                                    if (asino == 's') {
                                        TipiAbbonamento tipo = switch (Autogestionale.menuSelezione(scanner, "Scegli il tipo di abbonamento:\nSettimanale,Mensile,Annuale ")) {
                                            case 2 -> TipiAbbonamento.MENSILE;
                                            case 3 -> TipiAbbonamento.ANNUALE;
                                            default -> TipiAbbonamento.SETTIMANALE;
                                        };
                                        Abbonamento newAbb = new Abbonamento(userFound.getTessera(), tipo, puntoEmissioneF);
                                        dao.save(newAbb);
                                        System.out.println("Abbonamento " + newAbb + " creato con successo!");
                                    } else {
                                        System.out.println("Uscito dal sistema!");
                                    }
                                } // ho la tessera e l'abbonamento é ancora valido
                                else if (userFound.getTessera().getAbbonamento().getData_scadenza().isAfter(LocalDate.now())) {
                                    System.out.println("Il tuo abbonamento é ancora valido!");
                                }// ho la tessera ma non ho ancora mai fatto un abbonamento
                                else if (userFound.getTessera().getAbbonamento() == null) {
                                    System.out.println("Vuoi acquistare il tuo primo abbonamento?\ns/n");
                                    char asino = scanner.nextLine().charAt(0);
                                    if (asino == 's') {
                                        TipiAbbonamento tipo = switch (Autogestionale.menuSelezione(scanner, "Scegli il tipo di abbonamento:\nSettimanale,Mensile,Annuale ")) {
                                            case 2 -> TipiAbbonamento.MENSILE;
                                            case 3 -> TipiAbbonamento.ANNUALE;
                                            default -> TipiAbbonamento.SETTIMANALE;
                                        };
                                        Abbonamento newAbb = new Abbonamento(userFound.getTessera(), tipo, puntoEmissioneF);
                                        dao.save(newAbb);
                                        System.out.println("Abbonamento " + newAbb + " creato con successo!");
                                    } else {
                                        System.out.println("Uscito dal sistema!");
                                    }
                                }
                            } else // non ho la tessera
                            {
                                System.out.println("Vuoi creare una tessera?\ns/n ");
                                char asino = scanner.nextLine().charAt(0);
                                if (asino == 's') {
                                    Tessera t = new Tessera(userFound);
                                    dao.save(t);
                                    System.out.println("Tessera " + t + " creata con successo! ");

                                    System.out.println("Vuoi acquistare un abbonamento?\ns/n ");
                                    char sino = scanner.nextLine().charAt(0);
                                    if (sino == 's') {
                                        TipiAbbonamento tipo = switch (Autogestionale.menuSelezione(scanner, "Scegli il tipo di abbonamento:\nSettimanale,Mensile,Annuale ")) {
                                            case 2 -> TipiAbbonamento.MENSILE;
                                            case 3 -> TipiAbbonamento.ANNUALE;
                                            default -> TipiAbbonamento.SETTIMANALE;
                                        };
                                        Abbonamento newAbb = new Abbonamento(t, tipo, puntoEmissioneF);
                                        dao.save(newAbb);
                                        System.out.println("Abbonamento " + newAbb + " creato con successo!");
                                    } else {
                                        System.out.println("Uscito dal sistema!");
                                    }
                                } else {
                                    System.out.println("Uscito dal sistema!");
                                }

                            }
                            break;
                        //verifica abbonamento
                        case 3:
                            if (userFound.getTessera() == null) {
                                System.out.println("Non hai la tessera, vuoi crearla?\ns/n ");
                                char sino = scanner.nextLine().charAt(0);
                                if (sino == 's') {
                                    Tessera t = new Tessera(userFound);
                                    dao.save(t);
                                    System.out.println("Tessera " + t + " creata con successo! ");
                                } else {
                                    System.out.println("Uscito dal sistema!");
                                }
                            } else if (userFound.getTessera().getAbbonamento() != null) {
                                if (userFound.getTessera().getAbbonamento().getData_scadenza().isBefore(LocalDate.now())) {
                                    System.out.println("Il tuo abbonamento é scaduto, vuoi rinnovarlo?\ns/n ");
                                    char asino = scanner.nextLine().charAt(0);
                                    if (asino == 's') {
                                        TipiAbbonamento tipo = switch (Autogestionale.menuSelezione(scanner, "Scegli il tipo di abbonamento:\nSettimanale,Mensile,Annuale ")) {
                                            case 2 -> TipiAbbonamento.MENSILE;
                                            case 3 -> TipiAbbonamento.ANNUALE;
                                            default -> TipiAbbonamento.SETTIMANALE;
                                        };
                                        Abbonamento newAbb = new Abbonamento(userFound.getTessera(), tipo, puntoEmissioneF);
                                        dao.save(newAbb);
                                        System.out.println("Abbonamento " + newAbb + " creato con successo!");
                                    } else {
                                        System.out.println("Uscito dal sistema!");
                                    }

                                } else {
                                    System.out.println(userFound.getTessera().getAbbonamento());
                                }

                            }
                            break;

                    }
            }
        }

    }


}

