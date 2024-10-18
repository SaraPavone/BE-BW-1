package sarapavo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sarapavo.dao.*;
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

        if (userFound.getAdmin()) {
            int scelta;
            do {
                scelta = Autogestionale.menuSelezione(scanner, "Gestione mezzi,Statistiche");
                switch (scelta) {
                    case 1:
                        for (Mezzo m : dao.findAll(Mezzo.class)) {
                            System.out.println(m.toString());
                        }

                        long idMezzo;
                        Mezzo mezzoFound;
                        do {
                            System.out.println("Inserisci l'ID del mezzo per gestire: ");
                            idMezzo = Long.parseLong(scanner.nextLine());
                            mezzoFound = dao.getElementById(Mezzo.class, idMezzo);
                            if (mezzoFound == null) {
                                System.out.println("ID non valido, riprova.");
                            }
                        } while (mezzoFound == null);

                        System.out.println(mezzoFound.toString());
                        boolean ciccio = mezzoFound.isManutenzione();
                        String ris = (ciccio ? ("Vuoi rimuovere il mezzo dalla manutenzione?\nsi,no ") :
                                ("Vuoi mettere il mezzo in manutenzione?\nsi,no "));
                        switch (Autogestionale.menuSelezione(scanner, ris)) {
                            case 1:
                                mezzoFound.setManutenzione(!mezzoFound.isManutenzione());
                                dao.update(mezzoFound, "manutenzione", !mezzoFound.isManutenzione(), "id", mezzoFound.getId());
                                Periodo p = new Periodo(mezzoFound, mezzoFound.isManutenzione());
                                dao.save(p);
                                System.out.println("Stato del mezzo aggiornato!");
                                break;
                            case 2:
                                System.out.println("Stato del mezzo non aggiornato!");
                        }
                        break;
                    case 2:
                        System.out.println("Numero di abbonamenti emessi per punto di emissione: ");
                        daope.getNumeroAbbonamenti();
                        System.out.println("Numero di biglietti emessi per punto di emissione: ");
                        daope.conteggioBigliettiEmessiPerPE();
                        System.out.println("Numero di biglietti vidimati per mezzo: ");
                        mezziDao.conteggioBigliettiVidimatiPerMezzo();
                        System.out.println("Periodi di manutenzione o servizio dei mezzi: ");
                        mezziDao.periodiDiManutenzioneeServizio();
                        System.out.println("Tutte le tratte: ");
                        for (Tratta m : dao.findAll(Tratta.class)) {
                            System.out.println(m.toString());
                        }

                        int sceltaStatistiche;
                        do {
                            System.out.println("Statistiche per lasso di tempo: ");
                            sceltaStatistiche = Autogestionale.menuSelezione(scanner, "Numero biglietti vidimati,Numero biglietti e abbonamenti emessi ");
                            switch (sceltaStatistiche) {
                                case 1:
                                    LocalDate dataInizioF;
                                    LocalDate dataFineF;
                                    System.out.println("Inserisci il periodo da verificare: ");
                                    System.out.println("Inserisci la data di inizio ( AAAA-MM-GG ): ");
                                    String dataInizio = scanner.nextLine();
                                    dataInizioF = LocalDate.parse(dataInizio);
                                    System.out.println("Inserisci la data di fine ( AAAA-MM-GG ): ");
                                    String dataFine = scanner.nextLine();
                                    dataFineF = LocalDate.parse(dataFine);
                                    mezziDao.conteggioBigliettiVidimatiPerLassoDiTempo(dataInizioF, dataFineF);
                                    break;
                                case 2:
                                    System.out.println("Inserisci il periodo da verificare: ");
                                    System.out.println("Inserisci la data di inizio ( AAAA-MM-GG ): ");
                                    String dataInizio2 = scanner.nextLine();
                                    LocalDate dataInizioF2 = LocalDate.parse(dataInizio2);
                                    System.out.println("Inserisci la data di fine ( AAAA-MM-GG ): ");
                                    String dataFine2 = scanner.nextLine();
                                    LocalDate dataFineF2 = LocalDate.parse(dataFine2);
                                    daope.conteggioBigliettiEAbbonamentiPerLassoDiTempo(dataInizioF2, dataFineF2);
                                    break;
                            }
                        } while (sceltaStatistiche < 1 || sceltaStatistiche > 2);
                        break;
                }
            } while (scelta < 1 || scelta > 2);
        } else {
            int sceltaUtente;
            do {
                System.out.println("Cosa vuoi fare? ");
                sceltaUtente = Autogestionale.menuSelezione(scanner, "Vidimare,Acquistare,Verifica abbonamento ");
                switch (sceltaUtente) {
                    case 1:
                        int sceltaBiglietto;
                        do {
                            System.out.println("Hai il biglietto?\nsi,no ");
                            sceltaBiglietto = Autogestionale.menuSelezione(scanner, "Hai il biglietto?\nsi,no ");
                            switch (sceltaBiglietto) {
                                case 1:
                                    long numBiglietto;
                                    Biglietto bigliettoFound;
                                    do {
                                        System.out.println("Inserisci il numero del biglietto: ");
                                        numBiglietto = Long.parseLong(scanner.nextLine());
                                        bigliettoFound = dao.getElementById(Biglietto.class, numBiglietto);
                                        if (bigliettoFound == null) {
                                            System.out.println("Numero del biglietto non valido, riprova.");
                                        }
                                    } while (bigliettoFound == null);
                                    bigliettoFound.setData_vidimazione(LocalDate.now());
                                    dao.update(Biglietto.class, "data_vidimazione", bigliettoFound, "id", bigliettoFound.getId());
                                    System.out.println("Biglietto vidimato con successo! ");
                                    break;
                                case 2:
                                    long idPE;
                                    PuntoEmissione puntoEmissioneF;
                                    do {
                                        System.out.println("Inserisci l'ID del punto vendita dal quale stai acquistando il biglietto: ");
                                        idPE = Long.parseLong(scanner.nextLine());
                                        puntoEmissioneF = dao.getElementById(PuntoEmissione.class, idPE);
                                        if (puntoEmissioneF == null) {
                                            System.out.println("ID del punto vendita non valido, riprova.");
                                        }
                                    } while (puntoEmissioneF == null);
                                    long idMezzo;
                                    Mezzo mezzoF;
                                    do {
                                        System.out.println("Inserisci l'ID del mezzo su cui vuoi salire: ");
                                        idMezzo = Long.parseLong(scanner.nextLine());
                                        mezzoF = dao.getElementById(Mezzo.class, idMezzo);
                                        if (mezzoF == null) {
                                            System.out.println("ID del mezzo non valido, riprova.");
                                        }
                                    } while (mezzoF == null);
                                    Biglietto bigliettoAcquistato = new Biglietto(puntoEmissioneF, mezzoF);
                                    dao.save(bigliettoAcquistato);
                                    System.out.println("Biglietto n: " + bigliettoAcquistato.getId() + " acquistato con successo! ");
                                    int sceltaVidimazione;
                                    do {
                                        System.out.println("Vuoi vidimare il biglietto?\nsi,no ");
                                        sceltaVidimazione = Autogestionale.menuSelezione(scanner, "Vuoi vidimare il biglietto?\nsi,no ");
                                        switch (sceltaVidimazione) {
                                            case 1:
                                                bigliettoAcquistato.setData_vidimazione(LocalDate.now());
                                                dao.update(Biglietto.class, "data_vidimazione", bigliettoAcquistato, "id", bigliettoAcquistato.getId());
                                                System.out.println("Biglietto vidimato con successo! ");
                                                break;
                                            case 2:
                                                break;
                                        }
                                    } while (sceltaVidimazione < 1 || sceltaVidimazione > 2);
                                    break;
                            }
                        } while (sceltaBiglietto < 1 || sceltaBiglietto > 2);
                        break;
                    case 2:
                        long idPE;
                        PuntoEmissione puntoEmissioneF;
                        do {
                            System.out.println("Seleziona punto vendita: ");
                            for (PuntoEmissione m : dao.findAll(PuntoEmissione.class)) {
                                System.out.println(m.toString());
                            }
                            System.out.println("Inserisci l'ID del punto vendita dal quale stai acquistando il biglietto: ");
                            idPE = Long.parseLong(scanner.nextLine());
                            puntoEmissioneF = dao.getElementById(PuntoEmissione.class, idPE);
                            if (puntoEmissioneF == null) {
                                System.out.println("ID del punto vendita non valido, riprova.");
                            }
                        } while (puntoEmissioneF == null);
                        if (userFound.getTessera() != null) {
                            if (userFound.getTessera().getData_scadenza().isBefore(LocalDate.now())) {
                                int sceltaRinnovo;
                                do {
                                    System.out.println("La tua tessera é scaduta, vuoi rinnovarla?\nsi,no ");
                                    sceltaRinnovo = Autogestionale.menuSelezione(scanner, "La tua tessera é scaduta, vuoi rinnovarla?\nsi,no ");
                                    switch (sceltaRinnovo) {
                                        case 1:
                                            Tessera t = new Tessera(userFound);
                                            dao.save(t);
                                            System.out.println("Tessera " + t + " rinnovata con successo! ");
                                            int sceltaAbbonamento;
                                            do {
                                                System.out.println("Vuoi acquistare un abbonamento?\nsi,no ");
                                                sceltaAbbonamento = Autogestionale.menuSelezione(scanner, "Vuoi acquistare un abbonamento?\nsi,no ");
                                                switch (sceltaAbbonamento) {
                                                    case 1:
                                                        TipiAbbonamento tipo = switch (Autogestionale.menuSelezione(scanner, "Scegli il tipo di abbonamento:\nSettimanale,Mensile,Annuale ")) {
                                                            case 2 -> TipiAbbonamento.MENSILE;
                                                            case 3 -> TipiAbbonamento.ANNUALE;
                                                            default -> TipiAbbonamento.SETTIMANALE;
                                                        };
                                                        Abbonamento newAbb = new Abbonamento(t, tipo, puntoEmissioneF);
                                                        dao.save(newAbb);
                                                        dao.update(userFound.getTessera(), "abbonamento", newAbb, "numero_tessera", userFound.getTessera().getNumero_tessera());
                                                        System.out.println("Abbonamento " + newAbb + " creato con successo!");
                                                        break;
                                                    case 2:
                                                        System.out.println("Uscito dal sistema!");
                                                        break;
                                                }
                                            } while (sceltaAbbonamento < 1 || sceltaAbbonamento > 2);
                                            break;
                                        case 2:
                                            System.out.println("Uscito dal sistema!");
                                            break;
                                    }
                                } while (sceltaRinnovo < 1 || sceltaRinnovo > 2);
                            } else if (userFound.getTessera().getAbbonamento().getData_scadenza().isBefore(LocalDate.now())) {
                                int sceltaRinnovoAbbonamento;
                                do {
                                    System.out.println("Il tuo abbonamento é scaduto, vuoi rinnovarlo?\nsi,no ");
                                    sceltaRinnovoAbbonamento = Autogestionale.menuSelezione(scanner, "Il tuo abbonamento é scaduto, vuoi rinnovarlo?\nsi,no ");
                                    switch (sceltaRinnovoAbbonamento) {
                                        case 1:
                                            TipiAbbonamento tipo = switch (Autogestionale.menuSelezione(scanner, "Scegli il tipo di abbonamento:\nSettimanale,Mensile,Annuale ")) {
                                                case 2 -> TipiAbbonamento.MENSILE;
                                                case 3 -> TipiAbbonamento.ANNUALE;
                                                default -> TipiAbbonamento.SETTIMANALE;
                                            };
                                            Abbonamento newAbb = new Abbonamento(userFound.getTessera(), tipo, puntoEmissioneF);
                                            dao.save(newAbb);
                                            dao.update(userFound.getTessera(), "abbonamento", newAbb, "numero_tessera", userFound.getTessera().getNumero_tessera());
                                            System.out.println("Abbonamento " + newAbb + " creato con successo!");
                                            break;
                                        case 2:
                                            System.out.println("Uscito dal sistema!");
                                            break;
                                    }
                                } while (sceltaRinnovoAbbonamento < 1 || sceltaRinnovoAbbonamento > 2);
                            } else if (userFound.getTessera().getAbbonamento() == null) {
                                int sceltaAcquistoAbbonamento;
                                do {
                                    System.out.println("Vuoi acquistare il tuo primo abbonamento?\nsi,no ");
                                    sceltaAcquistoAbbonamento = Autogestionale.menuSelezione(scanner, "Vuoi acquistare il tuo primo abbonamento?\nsi,no ");
                                    switch (sceltaAcquistoAbbonamento) {
                                        case 1:
                                            TipiAbbonamento tipo = switch (Autogestionale.menuSelezione(scanner, "Scegli il tipo di abbonamento:\nSettimanale,Mensile,Annuale ")) {
                                                case 2 -> TipiAbbonamento.MENSILE;
                                                case 3 -> TipiAbbonamento.ANNUALE;
                                                default -> TipiAbbonamento.SETTIMANALE;
                                            };
                                            Abbonamento newAbb = new Abbonamento(userFound.getTessera(), tipo, puntoEmissioneF);
                                            dao.save(newAbb);
                                            dao.update(userFound.getTessera(), "abbonamento", newAbb, "numero_tessera", userFound.getTessera().getNumero_tessera());
                                            System.out.println("Abbonamento " + newAbb + " creato con successo!");
                                            break;
                                        case 2:
                                            System.out.println("Uscito dal sistema!");
                                            break;
                                    }
                                } while (sceltaAcquistoAbbonamento < 1 || sceltaAcquistoAbbonamento > 2);
                            }
                        } else {
                            int sceltaCreazioneTessera;
                            do {
                                System.out.println("Non hai la tessera, vuoi crearla?\nsi,no ");
                                sceltaCreazioneTessera = Autogestionale.menuSelezione(scanner, "Non hai la tessera, vuoi crearla?\nsi,no ");
                                switch (sceltaCreazioneTessera) {
                                    case 1:
                                        Tessera t = new Tessera(userFound);
                                        dao.save(t);
                                        System.out.println("Tessera " + t + " creata con successo! ");
                                        int sceltaAcquistoAbbonamento;
                                        do {
                                            System.out.println("Vuoi acquistare un abbonamento?\nsi,no ");
                                            sceltaAcquistoAbbonamento = Autogestionale.menuSelezione(scanner, "Vuoi acquistare un abbonamento?\nsi,no ");
                                            switch (sceltaAcquistoAbbonamento) {
                                                case 1:
                                                    TipiAbbonamento tipo = switch (Autogestionale.menuSelezione(scanner, "Scegli il tipo di abbonamento:\nSettimanale,Mensile,Annuale ")) {
                                                        case 2 -> TipiAbbonamento.MENSILE;
                                                        case 3 -> TipiAbbonamento.ANNUALE;
                                                        default -> TipiAbbonamento.SETTIMANALE;
                                                    };
                                                    Abbonamento newAbb = new Abbonamento(t, tipo, puntoEmissioneF);
                                                    dao.save(newAbb);
                                                    dao.update(userFound.getTessera(), "abbonamento", newAbb, "numero_tessera", userFound.getTessera().getNumero_tessera());
                                                    System.out.println("Abbonamento " + newAbb + " creato con successo!");
                                                    break;
                                                case 2:
                                                    System.out.println("Uscito dal sistema!");
                                                    break;
                                            }
                                        } while (sceltaAcquistoAbbonamento < 1 || sceltaAcquistoAbbonamento > 2);
                                        break;
                                    case 2:
                                        System.out.println("Uscito dal sistema!");
                                        break;
                                }
                            } while (sceltaCreazioneTessera < 1 || sceltaCreazioneTessera > 2);
                        }
                        break;
                    case 3:
                        if (userFound.getTessera() == null) {
                            int sceltaCreazioneTessera;
                            do {
                                System.out.println("Non hai la tessera, vuoi crearla?\nsi,no ");
                                sceltaCreazioneTessera = Autogestionale.menuSelezione(scanner, "Non hai la tessera, vuoi crearla?\nsi,no ");
                                switch (sceltaCreazioneTessera) {
                                    case 1:
                                        Tessera t = new Tessera(userFound);
                                        dao.save(t);
                                        System.out.println("Tessera " + t + " creata con successo! ");
                                        break;
                                    case 2:
                                        System.out.println("Uscito dal sistema!");
                                        break;
                                }
                            } while (sceltaCreazioneTessera < 1 || sceltaCreazioneTessera > 2);
                        } else if (userFound.getTessera().getAbbonamento() != null) {
                            if (userFound.getTessera().getAbbonamento().getData_scadenza().isBefore(LocalDate.now())) {
                                int sceltaRinnovoAbbonamento;
                                do {
                                    System.out.println("Il tuo abbonamento é scaduto, vuoi rinnovarlo?\nsi,no ");
                                    sceltaRinnovoAbbonamento = Autogestionale.menuSelezione(scanner, "Il tuo abbonamento é scaduto, vuoi rinnovarlo?\nsi,no ");
                                    switch (sceltaRinnovoAbbonamento) {
                                        case 1:
                                            TipiAbbonamento tipo = switch (Autogestionale.menuSelezione(scanner, "Scegli il tipo di abbonamento:\nSettimanale,Mensile,Annuale ")) {
                                                case 2 -> TipiAbbonamento.MENSILE;
                                                case 3 -> TipiAbbonamento.ANNUALE;
                                                default -> TipiAbbonamento.SETTIMANALE;
                                            };
                                            idPE = Long.parseLong(scanner.nextLine());
                                            puntoEmissioneF = dao.getElementById(PuntoEmissione.class, idPE);
                                            Abbonamento newAbb = new Abbonamento(userFound.getTessera(), tipo, puntoEmissioneF);
                                            dao.save(newAbb);
                                            dao.update(userFound.getTessera(), "abbonamento", newAbb, "numero_tessera", userFound.getTessera().getNumero_tessera());
                                            System.out.println("Abbonamento " + newAbb + " creato con successo!");
                                            break;
                                        case 2:
                                            System.out.println("Uscito dal sistema!");
                                            break;
                                    }
                                } while (sceltaRinnovoAbbonamento < 1 || sceltaRinnovoAbbonamento > 2);
                            } else {
                                System.out.println(userFound.getTessera().getAbbonamento());
                            }
                        }
                        break;
                }
            } while (sceltaUtente < 1 || sceltaUtente > 3);
        }
    }
}