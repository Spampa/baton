# Specifiche dei Requisiti

## 1. Introduzione

### 1.1 Scopo del documento
Questo documento è rivolto al team e ai docenti. Descrive i requisiti di `Baton` e i criteri con cui sono state scelte le sue funzionalità, e costituisce la base per le fasi di design, sviluppo e testing. Seguire il documento ci garantisce di portare a termine il progetto in maniera corretta, coerente e funzionante.

### 1.2 Ambito del prodotto
Baton è un software sviluppato in Java per creare automazioni basate su blocchi. Baton si ispira ai più noti progetti come [n8n](https://n8n.io/) oppure [Node-RED](https://nodered.org/). Lo sviluppo punta a realizzare un prodotto portabile, affidabile e manutenibile, dotato di un motore di esecuzione facilmente estendibile e di un'interfaccia per collegare visualmente i vari blocchi.

Baton è un'applicazione desktop che esegue le automazioni sul computer dell'utente, per un singolo utente e in modo sequenziale: l'esecuzione parallela, la gestione multiutente e le integrazioni con login OAuth sono escluse da questa versione (vedi sezione 3.2, Won't Have).

### 1.3 Glossario

#### Definizioni
| Termine | Definizione |
|---|---|
| **Blocco** | Unità elementare di un flow chart che svolge una singola operazione. È costituito da porte di ingresso, porte di uscita e un'elaborazione dei dati. |
| **Porta** | Punto di ingresso o di uscita di un blocco, associato a un tipo di dato. Due blocchi possono essere collegati solo se le rispettive porte sono compatibili. |
| **Collegamento** | Linea che unisce la porta di uscita di un blocco alla porta di ingresso di un altro blocco, definendo l'ordine di esecuzione e il passaggio dei dati. |
| **Trigger** | Blocco che avvia l'esecuzione di un flow chart (es. Trigger manuale, Trigger programmato). |
| **Blocco condizione** | Blocco che valuta un predicato booleano e indirizza l'esecuzione su uno dei suoi rami di uscita. |
| **Diramazione** | Punto in cui il flusso di esecuzione si divide in più rami. Nei requisiti Must Have le diramazioni sono possibili solo tramite il blocco condizione. |
| **Parametro** | Dato in ingresso a un blocco: può provenire dall'output di un altro blocco oppure essere un valore costante inserito dall'utente. |
| **Flow chart** | Insieme di blocchi e collegamenti che descrive un'automazione. Sinonimi usati nel documento: *automazione*, *diagramma di flusso*. |
| **Progetto** | Contenitore di uno o più flow chart, salvato in modo persistente. |
| **baton-engine** | Motore di esecuzione di Baton: esegue i blocchi di un flow chart seguendo i collegamenti a partire dal trigger. |
| **Testimone (Baton)** | Elemento grafico che si sposta tra i blocchi durante l'esecuzione per indicare il blocco in corso (RF-13). |

#### Acronimi e abbreviazioni
| Sigla | Significato |
|---|---|
| **SRS** | Software Requirements Specification |
| **RF / RNF** | Requisito Funzionale / Requisito Non Funzionale |
| **MoSCoW** | Must have, Should have, Could have, Won't have: metodo di prioritizzazione dei requisiti |
| **CRUD** | Create, Read, Update, Delete |
| **UI** | User Interface (interfaccia grafica) |
| **API** | Application Programming Interface |
| **HTTP** | HyperText Transfer Protocol |
| **CSV** | Comma-Separated Values |
| **JVM** | Java Virtual Machine |
| **OAuth** | Open Authorization: protocollo per autorizzare l'accesso a servizi di terze parti |

### 1.4 Riferimenti
- A. Gargantini, S. Bonfanti, *Requisiti per il progetto di ingegneria del software*, A.A. 2026/27, Università degli Studi di Bergamo.
- IEEE Std 830-1998, *IEEE Recommended Practice for Software Requirements Specifications*.
- H. van Vliet, *Software Engineering: Principles and Practice*, 3ª ed., Wiley, 2008.
- M. Seidl, M. Scholz, C. Huemer, G. Kappel, *UML @ Classroom: An Introduction to Object-Oriented Modeling*, Springer, 2015.
- n8n: https://n8n.io/
- Node-RED: https://nodered.org/
- Repository del progetto: https://github.com/Spampa/baton

### 1.5 Panoramica del documento
La sezione 2 fornisce una descrizione generale di Baton, dei suoi utenti tipo e delle assunzioni su cui si basano i requisiti. La sezione 3 riporta i requisiti specifici: il metodo di elicitazione e di prioritizzazione, i requisiti funzionali classificati secondo MoSCoW, i requisiti non funzionali, i vincoli e i casi d'uso. La sezione 4 descrive le qualità del software che hanno guidato il progetto.

## 2. Descrizione generale

### 2.1 Prospettiva del prodotto
`Baton` è un'applicazione Desktop all-in-one che non dipende da nessun server esterno per il suo funzionamento. 

### 2.2 Utenti tipo
Gli utilizzatori del prodotto sono utenti con competenze informatiche di base che vogliono creare automazioni per azioni ripetitive senza saper programmare.

### 2.3 Assunzioni e dipendenze
I requisiti si basano sulle seguenti assunzioni e dipendenze:
- Sul computer dell'utente è installato Java 21.
- Baton dispone dei permessi di lettura e scrittura sulle cartelle del file system usate dai blocchi di lettura e scrittura file.
- Il database SQLite è un file locale gestito dall'applicazione: non è richiesta l'installazione di un DBMS separato.
- La connessione a internet è necessaria solo per i blocchi che effettuano richieste HTTP (RF-08) o che usano servizi esterni (RF-12); tutte le altre funzionalità sono disponibili offline.
- I trigger programmati (RF-08) sono attivi solo mentre l'applicazione è aperta: Baton non viene eseguito come servizio in background.

## 3. Requisiti specifici

### 3.1 Elicitazione e prioritizzazione
Il team assegna le priorità dei requisiti basandosi sul criterio **MoSCoW**. L'elicitazione dei requisiti si basa sulla nostra esperienza nell'uso di programmi simili come [n8n](https://n8n.io/) oppure [Node-RED](https://nodered.org/), ai quali ci ispiriamo anche per estrarne le funzionalità principali e necessarie per la realizzazione di un progetto ottimale e funzionante.

### 3.2 Requisiti funzionali

#### Must Have
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

#### Should Have
- **RF-08** Blocchi:
  - Richieste HTTP GET: blocco in grado di eseguire richieste HTTP di tipo GET con eventuali header come *Authorization* a qualsiasi URL passato in input.
  - Trigger Programmato (Timer): blocco di esecuzione del diagramma di flusso che esegue l'automazione in maniera programmata a un determinato orario della giornata (attivo solo con l'applicazione aperta).
  - Ritardo di N millisecondi: blocco che applica un **delay** nell'esecuzione del diagramma di flusso.
- **RF-09** Esportazione / Caricamento progetto: permettere all'utente di esportare e caricare progetti così da garantirne l'esecuzione su dispositivi diversi senza perdere dati.
- **RF-10** Creazione di cicli for e while: permette ai blocchi di creare cicli basandosi su condizioni oppure su un numero di iterazioni prestabilito. L'engine dovrà essere in grado di gestirli.
- **RF-11** Stile dello sfondo blueprint: lo sfondo della UI deve richiamare la classica griglia da *blueprint*.

#### Could Have
- **RF-12** Blocchi:
  - Lettura metadati dei file: lettura di campi come nome, peso ed estensione del file.
  - Trigger nuovo file in cartella: blocco esecuzione che avvia l'automazione se un file viene creato in una cartella.
  - Blocco ChatGPT (API Key): blocco che fa una chiamata API a ChatGPT, è richiesto l’inserimento di una API KEY. Richiede un blocco HTTP generico che supporti anche metodi diversi dal GET (es. POST), di cui il blocco ChatGPT è una specializzazione.
  - Trigger Telegram: blocco esecuzione basato sulla rilevazione di un determinato messaggio su Telegram.
  - Esecuzione comando da terminale: blocco che permette di eseguire un determinato comando su terminale e che dà in output il risultato dell'esecuzione del comando con rilevamento errore di esecuzione.
- **RF-13** Il testimone che si muove tra i blocchi (il Baton): icona per la UI del testimone che si sposta tra un blocco e l'altro.
- **RF-14** Esecuzione di più diramazioni: un'uscita di un blocco può essere collegata a più flussi ma con un'esecuzione sempre seriale, mai parallela.

#### Won't Have
- Esecuzione parallela blocchi: non permettiamo la diramazione di un'automazione che dia la possibilità di eseguire flussi in parallelo.
- Multiutente: non vogliamo permettere di accedere alla stessa app con utenti differenti.
- Connessione blocchi con Login OAuth su altre piattaforme (es: Gmail): troppo complesso da gestire, perché richiederebbe la registrazione dell'applicazione su ogni piattaforma esterna e la gestione dei token di accesso; inoltre le linee guida del corso ammettono servizi esterni solo se concordati con il docente.

### 3.3 Requisiti non funzionali
- **RNF-01 Portabilità**: Windows, macOS, Debian; Java è particolarmente adatto alla portabilità perché usa bytecode con JVM.
- **RNF-02 Affidabilità**: un blocco in errore non fa crashare l'app, controllo dei cicli infiniti se implementati.
- **RNF-03 Manutenibilità**: motore facilmente estendibile con nuovi blocchi semplicemente andando ad implementare nuove classi senza toccare il core.
- **RNF-04 Usabilità**: basso tempo di onboarding da parte degli utenti all'applicazione (es. creare ed eseguire una prima automazione in meno di 5 minuti).

### 3.4 Vincoli
- Linguaggio Java 21, dipendenze gestite con Maven, progetti importabili in Eclipse
- Database embedded SQLite
- Libreria UI: da definire (proposta: JavaFX)

### 3.5 Casi d'uso
<!-- da completare: use case diagram e descrizione dei casi d'uso principali -->

## 4. Qualità del software
<!-- da completare -->