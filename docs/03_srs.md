# Specifiche dei Requisiti

Il team assegna le priorità dei requisiti basandosi sul criterio **MoSCoW**. L'elicitazione dei requisiti si basa sulla nostra esperienza nell'uso di programmi simili come [n8n](https://n8n.io/) oppure [Node-RED](https://nodered.org/), ai quali ci ispiriamo anche per estrarne le funzionalità principali e necessarie per la realizzazione di un progetto ottimale e funzionante.

## Requisiti Funzionali

### Must Have
- **RF-01** Gestione dei Blocchi di automazione: l'utente può aggiungere ed eliminare blocchi e modificarne i dati descrittivi, come nome, icona e descrizione (opzionale). Ogni blocco è costituito da ingressi, uscite e un'elaborazione dei dati.
- **RF-02** Validazione e gestione errori: sviluppo di criteri di validazione che permettono a 2 diversi blocchi di collegarsi in serie solo se le rispettive porte d'uscita e ingresso sono compatibili. Nel caso in cui non lo fossero bisogna gestire l'errore mostrando a schermo un alert e non mandando in crash l'applicazione.
- **RF-03** Database embedded: per salvare in modo persistente le automazioni create dagli utenti è necessario integrare un modulo che sia in grado di interfacciarsi sia con le funzionalità dell'app sia con il database embedded. Esso deve permettere le operazioni di CRUD sui flow chart.
- **RF-04** Interfaccia Grafica: deve garantire all'utente un'esperienza fluida e semplice nella creazione delle sue automazioni garantendo per ogni blocco la possibilità di connettersi ad altri blocchi, modificarne i parametri di esecuzione, la posizione nell'interfaccia ed eventuali diramazioni date dai blocchi condizione.
- **RF-05** Gestione di più progetti: l'applicazione deve permettere all'utente di creare ed eseguire più flow chart appartenenti a progetti differenti.
- **RF-06** Motore di esecuzione dei flow chart: il **baton-engine** deve essere in grado di gestire l'esecuzione dei blocchi seguendo i collegamenti a partire dal trigger, deve validare i collegamenti e i parametri che vengono passati per ogni blocco. L'engine deve garantire due tipi di parametri: quelli che provengono dall'output di un blocco oppure valori costanti inseriti dall'utente. Se un blocco fallisce durante l'esecuzione, il motore interrompe l'automazione e mostra quale blocco ha fallito e il motivo per cui è successo. I collegamenti inizialmente supportati sono solo collegamenti tra blocchi in maniera seriale, nessuna diramazione tranne per i blocchi condizione.
- **RF-07** Blocchi principali per garantire un'esperienza minima sul software **baton**:
  - Trigger Manuale: pulsante che al *click* esegue l'automazione in maniera manuale.
  - Lettura File (es. CSV): lettura di file presenti sul computer e parsing dati provenienti da file CSV.
  - Trasformazione testo (elaborazione stringhe): blocchi operazionali base in grado di elaborare testi e produrre in output un testo trasformato o numeri interi che esprimono il numero di occorrenze di parole o caratteri.
  - Blocco di Calcolo: blocco operazionale di base in grado di eseguire le operazioni matematiche di base (+, -, *, /).
  - Blocco condizione: blocco **if** in grado di creare diramazioni basate su predicati booleani basati sull'output di un blocco. Può anche essere usato per filtrare l'esecuzione, ossia rispondere alla frase *eseguo solo se la condizione è vera*.
  - Scrittura su file: scrittura di dati in ingresso sul file system del dispositivo su cui baton è in esecuzione.

### Should Have
- **RF-08** Blocchi:
  - Richieste HTTP GET: blocco in grado di eseguire richieste HTTP di tipo GET con eventuali header come *Authorization* a qualsiasi URL passato in input.
  - Trigger Programmato (Timer): blocco di esecuzione del diagramma di flusso che esegue l'automazione in maniera programmata a un determinato orario della giornata (attivo solo con l'applicazione aperta).
  - Ritardo di N millisecondi: blocco che applica un **delay** nell'esecuzione del diagramma di flusso.
- **RF-09** Esportazione / Caricamento progetto: permettere all'utente di esportare e caricare progetti così da garantirne l'esecuzione su dispositivi diversi senza perdere dati.
- **RF-10** Creazione di cicli for e while: permette ai blocchi di creare cicli basandosi su condizioni oppure su un numero di iterazioni prestabilito. L'engine dovrà essere in grado di gestirli.
- **RF-11** Stile dello sfondo blueprint: lo sfondo della UI deve richiamare la classica griglia da *blueprint*.

### Could Have
- **RF-12** Blocchi:
  - Lettura metadati dei file: lettura di campi come nome, peso ed estensione del file.
  - Trigger nuovo file in cartella: blocco esecuzione che avvia l'automazione se un file viene creato in una cartella.
  - Blocco ChatGPT (API Key): blocco che fa una chiamata API a ChatGPT, è richiesto l’inserimento di una API KEY. Richiede un blocco HTTP generico che supporti anche metodi diversi dal GET (es. POST), di cui il blocco ChatGPT è una specializzazione.
  - Trigger Telegram: blocco esecuzione basato sulla rilevazione di un determinato messaggio su Telegram.
  - Esecuzione comando da terminale: blocco che permette di eseguire un determinato comando su terminale e che dà in output il risultato dell'esecuzione del comando con rilevamento errore di esecuzione.
- **RF-13** Il testimone che si muove tra i blocchi (il Baton): icona per la UI del testimone che si sposta tra un blocco e l'altro.
- **RF-14** Esecuzione di più diramazioni: un'uscita di un blocco può essere collegata a più flussi ma con un'esecuzione sempre seriale, mai parallela.

### Won't Have
- Esecuzione parallela blocchi: non permettiamo la diramazione di un'automazione che dia la possibilità di eseguire flussi in parallelo.
- Multiutente: non vogliamo permettere di accedere alla stessa app con utenti differenti.
- Connessione blocchi con Login OAuth su altre piattaforme (es: Gmail): troppo complesso da gestire, perché richiederebbe la registrazione dell'applicazione su ogni piattaforma esterna e la gestione dei token di accesso; inoltre le linee guida del corso ammettono servizi esterni solo se concordati con il docente.

## Requisiti Non Funzionali
- **RNF-01 Portabilità**: Windows, macOS, Debian; Java è particolarmente adatto alla portabilità perché usa bytecode con JVM.
- **RNF-02 Affidabilità**: un blocco in errore non fa crashare l'app, controllo dei cicli infiniti se implementati.
- **RNF-03 Manutenibilità**: motore facilmente estendibile con nuovi blocchi semplicemente andando ad implementare nuove classi senza toccare il core.
- **RNF-04 Usabilità**: basso tempo di onboarding da parte degli utenti all'applicazione (es. creare ed eseguire una prima automazione in meno di 5 minuti).

## Vincoli
- Linguaggio Java 21, dipendenze gestite con Maven, progetti importabili in Eclipse
- Database embedded SQLite
- Libreria UI: da definire (proposta: JavaFX)