# Project Management

## 1. Software Life Cycle

### 1.1 Modello di processo

Per lo sviluppo di Baton il team adotta un processo **RAD (Rapid Application Development)**, organizzato in iterazioni brevi e in incrementi funzionanti del prodotto. La scelta è coerente con la natura del progetto: Baton è un'applicazione interattiva e il comportamento della UI, del motore di esecuzione e dei blocchi deve essere verificato frequentemente attraverso prototipi eseguibili.

Il RAD non elimina la necessità di analisi e progettazione, ma distribuisce queste attività lungo il ciclo di sviluppo. Ogni incremento comprende, in misura adeguata alla funzionalità, analisi dei requisiti, progettazione, implementazione, integrazione e verifica. In questo modo il team mantiene una versione funzionante e presentabile dell'applicazione anche durante lo sviluppo e può rivedere le funzionalità meno prioritarie in base al tempo residuo e ai risultati dei test.

Il processo è guidato dai seguenti principi:

* costruire prima una **core build** funzionante, costituita dal motore a blocchi, dai blocchi essenziali e dall'interfaccia minima;
* integrare frequentemente il codice tramite Pull Request e code review;
* verificare ogni incremento con test mirati e scenari d'uso;
* mantenere separata la parte stabile del prodotto dalle funzionalità ancora in sviluppo;
* usare la priorità MoSCoW definita nel documento dei requisiti per decidere quali funzionalità sviluppare per prime.

La scelta del RAD riduce il rischio di concentrare l'integrazione e la verifica solo alla fine del progetto. Non elimina completamente i rischi tecnici: instabilità della libreria UI, incompatibilità tra moduli, errori nel motore di esecuzione e ritardi possono ancora richiedere una revisione del piano.

### 1.2 Timebox e incremento del prodotto

Il calendario è allineato alle fasi del RAD e alle milestone del progetto su GitHub. Ogni timebox corrisponde a un'iterazione del GitHub Project e contribuisce alla milestone della fase in cui ricade; la scadenza della milestone è la data entro cui i deliverable della fase devono essere completati.

| Timebox | Periodo | Milestone (scadenza) | Obiettivo principale | Attività e deliverable |
| :--- | :--- | :--- | :--- | :--- |
| TB0 | 02/11/2026 – 08/11/2026 | Requirements Planning (08/11/2026) | Avvio e consolidamento dei requisiti | Stesura e raffinamento dell'SRS, glossario, casi d'uso iniziali, definizione delle priorità MoSCoW, piano di progetto. |
| TB1 | 09/11/2026 – 22/11/2026 | User Design & Construction (20/12/2026) | Progettazione e core build del motore | Diagrammi UML (class, package/component diagram), generazione dello scheletro Java, modello dei blocchi, porte e collegamenti, validazione dei tipi, gestione degli errori e prima esecuzione seriale. |
| TB2 | 23/11/2026 – 06/12/2026 | User Design & Construction (20/12/2026) | Persistenza, blocchi Must Have e interfaccia | Integrazione SQLite, CRUD dei flow chart, trigger manuale, blocchi di lettura, trasformazione, calcolo e scrittura su file, editor grafico e integrazione con `baton-engine`. |
| TB3 | 07/12/2026 – 20/12/2026 | User Design & Construction (20/12/2026) | Verifica e funzionalità successive | Test JUnit, correzione dei difetti, eventuale sviluppo di requisiti Should Have compatibili con il tempo residuo. |
| TB4 | 21/12/2026 – 06/01/2027 | Cutover (06/01/2027) | Stabilizzazione e consegna | Code review complessiva, test di regressione, congelamento delle funzionalità, completamento della documentazione, verifica dell'importazione in Eclipse e preparazione della presentazione. |

Alla fine di ogni timebox il team valuta l'incremento ottenuto, i difetti aperti e il tempo rimanente. Le funzionalità non completate possono essere rinviate, ridimensionate o escluse secondo la priorità già definita nell'SRS; ogni modifica sostanziale ai requisiti o all'architettura viene registrata tramite Issue o Pull Request.

### 1.3 Uso dell'approccio MDA

Il progetto utilizza una parte dell'approccio **MDA (Model-Driven Architecture)**. I modelli UML non sono usati solo come documentazione, ma anche come base per la prima struttura del codice:

1. il team definisce in StarUML il class diagram del dominio e delle principali componenti;
2. dal class diagram viene generata con StarUML una prima versione dello scheletro Java;
3. gli sviluppatori completano manualmente implementazioni, vincoli, gestione degli errori, persistenza e logica della UI;
4. il modello UML e il codice vengono mantenuti coerenti nelle successive revisioni.

La generazione automatica non sostituisce la progettazione o la verifica del codice: produce una base iniziale che deve essere integrata, testata e corretta dal team.

## 2. Configuration Management

### 2.1 Strumenti e configurazione del progetto

Il configuration management è basato su Git e GitHub. Il repository principale del progetto è [Spampa/baton](https://github.com/Spampa/baton), il cui branch stabile è `main`.

Gli strumenti utilizzati sono:

* **Git e GitHub** per versionare codice, documentazione e modelli;
* **GitHub Issues** per requisiti, attività, difetti e change request;
* **GitHub Projects – Baton Development** per visualizzare lo stato del lavoro;
* **Pull Request** per integrare le modifiche nel branch stabile;
* **code review** per verificare le modifiche prima dell'integrazione;
* **Discord** per la comunicazione rapida e il coordinamento del team;
* **Eclipse, Maven, Java 21, JUnit e StarUML** per sviluppo, gestione delle dipendenze, test e modellazione.

Nel repository vengono versionati il codice sorgente, la documentazione, i modelli UML, `pom.xml`, `.project` e `.classpath` quando necessari all'importazione in Eclipse. Il file `.gitignore` esclude i prodotti generati, tra cui i file `.class` e la directory `target/`, oltre ad altri file locali non necessari alla consegna. Non devono essere presenti percorsi assoluti dipendenti dal computer di un singolo componente.

### 2.2 Ruoli e autorità

Nel progetto il ruolo viene indicato con un'unica denominazione coerente: **Nicolò Spampatti è il Configuration Manager**, che svolge anche la funzione di Direttore dei Lavori e Capo Progetto.

| Ruolo | Assegnatario | Responsabilità |
| :--- | :--- | :--- |
| **Configuration Manager / Capo Progetto** | Nicolò Spampatti | Definizione delle regole di configurazione, supervisione del repository, controllo dell'integrazione, approvazione finale delle PR destinate a `main`, gestione dei conflitti complessi e coordinamento delle decisioni tecniche. |
| **Sviluppatore** | Marco Magri | Implementazione delle funzionalità assegnate, creazione dei branch, commit tracciabili, apertura delle PR e partecipazione alle review. |
| **Sviluppatore** | Giovanni Ghilardi | Implementazione e integrazione del codice, gestione del workspace Eclipse, aggiornamento dei modelli e partecipazione alle review. |
| **Sviluppatore** | Matteo Piffari | Sviluppo delle funzionalità assegnate, segnalazione e verifica dei bug, aggiornamento degli Issue e collaborazione nelle review. |

Gli incarichi operativi possono cambiare in base al carico di lavoro, ma l'autorità di integrazione sul branch `main` rimane associata al Configuration Manager.

### 2.3 Issue, Project Board e tracciabilità

Ogni attività significativa viene rappresentata da un Issue o da una Pull Request collegata a un Issue. La Project Board **Baton Development** usa le seguenti colonne di stato:

| Colonna GitHub Projects | Significato nel workflow |
| :--- | :--- |
| **Backlog** | Attività individuate ma non ancora pronte per essere svolte. |
| **Ready** | Attività analizzata, definita e pronta per essere assegnata o iniziata. |
| **In progress** | Attività attualmente implementata o verificata da uno sviluppatore. |
| **In review** | Modifica completata e sottoposta a Pull Request e code review. |
| **Done** | Attività integrata, verificata e chiusa. |

Il passaggio a **Done** non avviene solo quando il codice è stato scritto: la PR deve essere stata esaminata, i test rilevanti devono essere stati eseguiti e l'Issue deve avere una descrizione coerente con il risultato ottenuto.

### 2.4 Branching e Pull Request

Il branch `main` contiene esclusivamente una versione stabile e integrata. Ogni modifica viene sviluppata in un branch dedicato, creato a partire da `main`, con una convenzione che rende riconoscibile lo scopo:

* `feature/<nome>` per una nuova funzionalità;
* `fix/<nome>` per la correzione di un difetto;
* `docs/<nome>` per la documentazione;
* `refactor/<nome>` per una ristrutturazione interna senza modifica intenzionale del comportamento.

Il flusso di integrazione è il seguente:

1. l'attività viene descritta in un Issue e inserita nella Project Board;
2. lo sviluppatore crea il branch dedicato e realizza modifiche piccole e tracciabili;
3. i commit vengono pubblicati sul repository e il branch viene aggiornato rispetto a `main` quando necessario;
4. lo sviluppatore apre una Pull Request collegata all'Issue, descrivendo obiettivo, modifiche e verifiche effettuate;
5. almeno un altro membro del team esegue la code review e segnala eventuali correzioni;
6. dopo la risoluzione dei commenti, Nicolò Spampatti, in qualità di Configuration Manager, approva la PR destinata a `main`;
7. il merge viene eseguito solo quando la review è conclusa, i controlli previsti hanno esito positivo e non risultano conflitti irrisolti;
8. dopo il merge, l'Issue e la card della Project Board vengono aggiornati.

La code review di un collega è necessaria per il controllo tecnico, ma non sostituisce l'approvazione finale del Configuration Manager. Quest'ultimo può richiedere ulteriori verifiche o rifiutare l'integrazione se la modifica non è coerente con l'architettura, la documentazione o la qualità attesa.

### 2.5 Modifiche e baseline

Una versione integrata su `main` costituisce una baseline del prodotto. Le modifiche successive devono essere introdotte tramite il normale workflow di Issue, branch, Pull Request e review. In caso di conflitto Git, i componenti coinvolti risolvono il conflitto verificando il comportamento risultante; per i conflitti che riguardano architettura, API pubbliche o file condivisi interviene il Configuration Manager.

## 3. People Management and Team Organization

### 3.1 Struttura del team

Il team è composto da quattro sviluppatori che collaborano con una struttura **SWAT (Skilled Workers with Advanced Tools)**. La scelta è adatta a un gruppo piccolo e tecnico: ogni membro contribuisce direttamente ad analisi, progettazione, implementazione, test e documentazione, invece di essere confinato a un solo ruolo specialistico.

La struttura non significa assenza di coordinamento. Il Configuration Manager mantiene la visione complessiva del prodotto, facilita le decisioni tecniche e interviene quando il lavoro parallelo rischia di produrre moduli incompatibili. Le responsabilità operative sono distribuite, mentre le decisioni di integrazione seguono il processo definito nella sezione di Configuration Management.

### 3.2 Organizzazione del lavoro

Il lavoro viene organizzato per Issue e per incremento del prodotto:

* ogni Issue ha un obiettivo verificabile, un assegnatario e una priorità;
* le attività vengono scomposte in unità abbastanza piccole da poter essere sviluppate e revisionate in un branch dedicato;
* il team coordina periodicamente lo stato delle attività, i blocchi e le dipendenze;
* chi completa un'attività aggiorna la Issue, la Project Board e la Pull Request;
* prima dell'integrazione, almeno un altro componente esamina il lavoro;
* le conoscenze sui moduli non devono rimanere concentrate in una sola persona: la review e la collaborazione servono anche a condividere il contesto tecnico.

La distribuzione concreta delle attività può cambiare durante i timebox. Per esempio, un componente può occuparsi principalmente del motore in una fase e collaborare alla UI o ai test in una fase successiva. Questo mantiene la flessibilità del team senza perdere responsabilità e tracciabilità.

### 3.3 Comunicazione e gestione dei problemi

GitHub è la fonte di verità per codice, Issue, Pull Request, review e stato delle attività. Discord viene utilizzato per comunicazioni rapide, riunioni e chiarimenti, ma le decisioni che modificano requisiti, architettura o configurazione devono essere riportate anche in un Issue o in una Pull Request.

Quando un componente incontra un blocco:

1. documenta il problema nell'Issue o nella Pull Request interessata;
2. informa il team indicando quale decisione o quale supporto è necessario;
3. valuta se il blocco dipende da una dipendenza tecnica, da un requisito non chiaro o da un problema di integrazione;
4. coinvolge il Configuration Manager quando il problema può influire sull'architettura, sulla baseline o sulla consegna.

Questo processo evita che le decisioni rilevanti rimangano solo nella comunicazione informale e permette a tutti i membri di ricostruire la storia del progetto.
