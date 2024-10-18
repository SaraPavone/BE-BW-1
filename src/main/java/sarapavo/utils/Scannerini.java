package sarapavo.utils;

import sarapavo.dao.DaoMezzi;
import sarapavo.dao.DaoPE;
import sarapavo.dao.GenericDao;
import sarapavo.entities.*;
import sarapavo.entities.enums.TipiAbbonamento;

import java.time.LocalDate;
import java.util.Scanner;

public class Scannerini {

    public static void gestisciMezzi(Scanner scanner, GenericDao dao) {
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
        System.out.println("Vuoi cambiare lo stato del mezzo?");
        switch (Autogestionale.menuSelezione(scanner, "si,no")) {
            case 1:
                mezzoFound.setManutenzione(!mezzoFound.isManutenzione());
                dao.update(mezzoFound, "manutenzione", !mezzoFound.isManutenzione(), "id", mezzoFound.getId());
                Periodo p = new Periodo(mezzoFound, mezzoFound.isManutenzione());
                dao.save(p);
                System.out.println("Stato del mezzo aggiornato!");
                break;
            case 2:
                System.out.println("Stato del mezzo non aggiornato!");
                break;
        }
    }


    public static void visualizzaStatistiche(Scanner scanner, DaoPE daope, DaoMezzi mezziDao, GenericDao dao) {
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
    }


    public static void vidimareBiglietto(Scanner scanner, GenericDao dao) {
        int sceltaBiglietto;
        do {
            System.out.println("Hai il biglietto? ");
            sceltaBiglietto = Autogestionale.menuSelezione(scanner, "si,no ");
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
                        System.out.println("Vuoi vidimare il biglietto? ");
                        sceltaVidimazione = Autogestionale.menuSelezione(scanner, "si,no ");
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
    }

    public static void acquistoTessera(Scanner scanner, GenericDao dao, User userFound) {
        Tessera tessera = new Tessera(userFound);
        dao.save(tessera);
        System.out.println("Tessera " + tessera + " salvata con successo!");
    }

    public static void acquistareAbbonamento(Scanner scanner, GenericDao dao, User userFound) {

        if (userFound.getTessera() == null) {
            System.out.println("Non hai una tessera. Devi prima acquistarne una.");
            return;
        }

        TipiAbbonamento tipo = switch (Autogestionale.menuSelezione(scanner, "Scegli il tipo di abbonamento:\nSettimanale,Mensile,Annuale ")) {
            case 2 -> TipiAbbonamento.MENSILE;
            case 3 -> TipiAbbonamento.ANNUALE;
            default -> TipiAbbonamento.SETTIMANALE;
        };

        Abbonamento nuovoAbbonamento = new Abbonamento(userFound.getTessera(), tipo, null); // null per il punto di emissione, se necessario
        dao.save(nuovoAbbonamento);
        dao.update(userFound.getTessera(), "abbonamento", nuovoAbbonamento, "numero_tessera", userFound.getTessera().getNumero_tessera());
        System.out.println("Abbonamento " + nuovoAbbonamento + " creato con successo!");
    }


    public static void acquistareBiglietto(Scanner scanner, GenericDao dao, User userFound) {
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

        // Procedi con l'acquisto del biglietto
        long idMezzo;
        Mezzo mezzoF;
        do {
            for (Tratta i : dao.findAll(Tratta.class)) {
                System.out.println(i.toString());
            }
            System.out.println("Inserisci l'ID del mezzo su cui vuoi salire: ");
            idMezzo = Long.parseLong(scanner.nextLine());
            mezzoF = dao.getElementById(Mezzo.class, idMezzo);
            if (mezzoF == null) {
                System.out.println("ID del mezzo non valido, riprova.");
            }
        } while (mezzoF == null);
        Biglietto bigliettoAcquistato = new Biglietto(puntoEmissioneF, mezzoF);
        dao.save(bigliettoAcquistato);
        System.out.println("Biglietto n: " + bigliettoAcquistato.getId() + " acquistato con successo!");

        // Chiedi se l'utente vuole vidimare il biglietto
        int sceltaVidimazione;
        do {
            System.out.println("Vuoi vidimare il biglietto? ");
            sceltaVidimazione = Autogestionale.menuSelezione(scanner, "si,no ");
            switch (sceltaVidimazione) {
                case 1:
                    bigliettoAcquistato.setData_vidimazione(LocalDate.now());
                    dao.update(bigliettoAcquistato, "data_vidimazione", bigliettoAcquistato.getData_vidimazione(), "id", bigliettoAcquistato.getId());
                    System.out.println("Biglietto vidimato con successo! ");
                    break;
                case 2:
                    System.out.println("Biglietto non vidimato.");
                    break;
            }
        } while (sceltaVidimazione < 1 || sceltaVidimazione > 2);
    }


    public static void verificaAbbonamento(Scanner scanner, GenericDao dao, User userFound) {
        if (userFound.getTessera() == null) {
            int sceltaCreazioneTessera;
            do {
                System.out.println("Non hai la tessera, vuoi crearla? ");
                sceltaCreazioneTessera = Autogestionale.menuSelezione(scanner, "si,no ");
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
                    System.out.println("Il tuo abbonamento é scaduto, vuoi rinnovarlo? ");
                    sceltaRinnovoAbbonamento = Autogestionale.menuSelezione(scanner, "si,no ");
                    switch (sceltaRinnovoAbbonamento) {
                        case 1:
                            System.out.println("Scegli il tipo di abbonamento:");
                            TipiAbbonamento tipo = switch (Autogestionale.menuSelezione(scanner, "Settimanale,Mensile,Annuale ")) {
                                case 2 -> TipiAbbonamento.MENSILE;
                                case 3 -> TipiAbbonamento.ANNUALE;
                                default -> TipiAbbonamento.SETTIMANALE;
                            };
                            long idPE = Long.parseLong(scanner.nextLine());
                            PuntoEmissione puntoEmissioneF = dao.getElementById(PuntoEmissione.class, idPE);
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
    }


}
