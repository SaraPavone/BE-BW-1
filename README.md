# MyTransport - Gestionale di Trasporti

## Descrizione

MyTransport è un'applicazione gestionale sviluppata in Java che consente la gestione di mezzi di trasporto, biglietti e abbonamenti. Utilizza Hibernate per la gestione della persistenza dei dati con un database PostgreSQL e Maven come sistema di gestione delle dipendenze.

## Tecnologie Utilizzate

- **Java**: Linguaggio di programmazione.
- **Hibernate**: Framework ORM per la gestione della persistenza dei dati.
- **PostgreSQL**: Database relazionale utilizzato per memorizzare i dati.
- **Maven**: Strumento di gestione delle dipendenze e build.

## Requisiti

- Java 11 o superiore
- PostgreSQL
- Maven

## Installazione

1. **Clona il repository**:
   ```bash
   git clone https://github.com/tuo-username/MyTransport.git
   cd MyTransport

2. **Configura il database**:
   ```bash 
   Crea un database PostgreSQL chiamato be_bw_1;
   Modifica il file `persistence.xml` per configurare l URL,l username e la password del tuo database PostgreSQL.

3. **Compila il progetto**:
   ```bash
   mvn clean install

4. **Esegui l'applicazione**:
   ```bash
   mvn exec:java -Dexec.mainClass="Application"

## Utilizzo

1. **Accesso**:
   - Inserisci il tuo ID utente. Se sei un amministratore, avrai accesso a funzionalità di gestione dei mezzi e statistiche.
   - Gli utenti normali possono vidimare biglietti, acquistare biglietti e abbonamenti e verificare lo stato della loro tessera.
2. **Funzionalità**:
- Gestione mezzi: Gli amministratori possono visualizzare e gestire lo stato dei mezzi.
- Statistiche: Gli amministratori possono visualizzare statistiche sui biglietti e abbonamenti emessi.
- Acquisto e vidimazione biglietti: Gli utenti possono acquistare biglietti e vidimarli.
- Gestione abbonamenti: Gli utenti possono acquistare e rinnovare abbonamenti.

## Struttura del Codice
- `sarapavo.entities`: Contiene le entità JPA per il modello di dominio.
- `sarapavo.entities.enums`: Contiene le enumerazioni per i tipi di abbonamento.
- `sarapavo.utils`: Contiene utility e metodi di supporto per l'applicazione.
- `Application`: Classe principale che gestisce l'interazione con l'utente e il flusso dell'applicazione.

## Esempi di Comandi
- Gestione mezzi:
    - Visualizza tutti i mezzi e gestisci il loro stato.
- Statistiche:
    - Visualizza il numero di biglietti emessi e vidimati.
- Acquisto Biglietto:
    - Inserisci l'ID del punto vendita e del mezzo per acquistare un biglietto.

## Contributi
**A questo progetto hanno contribuito**:
- Sara Pavone
- Luca Viganò
- Antonio Costantini
- Manuel Barone


