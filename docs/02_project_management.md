# Project Management

## Software Life Cycle
Il nostro team seguirà RAD (Rapid Application Development).
La decisione di utilizzare il RAD è dettata dalla necessità di avere un prodotto funzionante e testabile entro la scadenza del progetto, mitigando totalmente il rischio di arrivare alla consegna "a mani vuote". Avendo a disposizione un team di quattro persone, un approccio puramente sequenziale (come il Waterfall) rallenterebbe inutilmente la fase di implementazione a causa di un'eccessiva documentazione preventiva.
Il RAD, al contrario, ci permette di:
* Sviluppare in modo iterativo, concentrando gli sforzi per avere una *core build* dell'applicativo (il motore a blocchi di Baton) funzionante fin dalle prime settimane.
* Testare e integrare il codice continuamente, garantendo che ogni nuova feature aggiunta non rompa quanto già implementato.
* Adattare i requisiti in base al tempo effettivamente rimanente prima dell'esame, avendo sempre in mano una versione stabile e presentabile del software.

**Motivazione della scelta di MoSCoW:**
Dovendo consegnare un progetto complesso in pochi mesi, il metodo MoSCoW è il metodo probabilmente migliore (*Must have, Should have, Could have, Won't have*). Il team dedicherà il 100% dell'effort iniziale esclusivamente ai requisiti *Must Have* (es. esecuzione dei blocchi, UI di base, salvataggio progetti). Solo quando questi saranno perfettamente funzionanti e integrati nel branch `main`, i 4 sviluppatori verranno riassegnati allo sviluppo dei requisiti *Should Have* e *Could Have*.

## Configuration Management
Per la gestione della configurazione, il team adotta un approccio rigoroso basato su Git e GitHub, integrato con strumenti di tracciamento e comunicazione per garantire il controllo delle versioni e la collaborazione. 

### 1. Strumenti Utilizzati
* **Versioning e Repository:** GitHub, utilizzato tramite riga di comando o client desktop per le operazioni di `git add`, `git commit`, `git push`, `git fetch` e `git merge`.
* **Project Board:** GitHub Projects (Kanban) per il tracciamento visivo degli Issue e l'avanzamento dei task.
* **Comunicazione:** Discord per il coordinamento rapido, i meeting del team e il supporto durante le fasi di code review.

### 2. Ruoli e Responsabilità nel Configuration Management
Seguendo le linee guida di gestione della configurazione (riferimento alla struttura del Capitolo 4), è fondamentale definire chi ha l'autorità di approvare le modifiche al codice sorgente. In questo progetto, Nicolò Spampatti assume il ruolo di **Configuration Manager / Direttore Lavori**.

| Ruolo | Assegnatario | Responsabilità |
| :--- | :--- | :--- |
| **Configuration Manager (Capo Progetto)** | Nicolò Spampatti (Spampa) | Definizione delle regole di branch, supervisione della repository, approvazione finale delle Pull Request critiche su `main`, risoluzione dei conflitti complessi e merge finale. |
| **Sviluppatore** | Magri Marco | Sviluppo feature assegnate, creazione di branch dedicati, scrittura dei commit secondo le convenzioni e apertura delle Pull Request. |
| **Sviluppatore** | Giovanni Ghilardi | Implementazione del codice, gestione del proprio workspace Eclipse, partecipazione attiva alle Code Review dei colleghi. |
| **Sviluppatore** | Matteo Piffari | Aggiornamento dello stato dei task sulla board Kanban, segnalazione bug tramite Issue, sviluppo delle funzionalità richieste. |

### 3. Workflow, Branching e Pull Request
Il processo di sviluppo e integrazione del codice è strutturato per evitare conflitti e garantire codice sempre funzionante:
* **Gestione Branch:** Il branch `main` contiene esclusivamente codice stabile e testato. Ogni sviluppatore deve creare un branch separato per ogni nuova funzionalità o bug fix (es. `feature/blocco-trigger`, `fix/ui-rendering`).
* **Issue Tracking:** Ogni nuovo requisito (es. quelli definiti con MoSCoW) o bug viene formalizzato tramite l'apertura di un Issue su GitHub. Questo Issue viene poi collegato alla Project Board Kanban per monitorarne lo stato (To Do, In Progress, Review, Done).
* **Pull Request e Code Review:** Prima che il codice di un branch possa essere unito al `main`, lo sviluppatore deve aprire una Pull Request (PR). La PR richiede obbligatoriamente una Code Review da parte di un altro membro del team. L'approvazione definitiva spetta al Configuration Manager (Spampa), che funge da "gatekeeper" per la qualità del codice.

### 4. Regole di Tracciamento e Workspace
* **Gestione del `.gitignore`:** Vengono tassativamente esclusi dal tracciamento i file compilati (`.class`) e la cartella `target` generata da Maven. 
* **Condivisione configurazioni Eclipse:** Il file `.project` (e `.classpath`) deve invece essere obbligatoriamente versionato e caricato su GitHub per permettere la corretta e immediata importazione dei progetti da parte di tutti i membri del team e, successivamente, dei docenti in fase di valutazione. Non devono essere inseriti riferimenti a percorsi assoluti locali.

## People Management and Organization

### Funzionamento del Team SWAT
Il team adotta un'organizzazione di tipo **SWAT (Skilled Workers with Advanced Tools)** combinata con il metodo di prioritizzazione **MoSCoW** per la selezione dei requisiti.

**Motivazione della scelta del Team SWAT:**
La struttura SWAT è ideale per un gruppo ristretto (4 persone) in cui tutti i membri sono programmatori capaci e operativi. Invece di avere ruoli puramente gestionali o di sola analisi che disperderebbero forza lavoro, l'approccio SWAT permette a tutti di "mettere le mani nel codice". La presenza di Spampatti come *Direttore dei Lavori* garantisce la coesione tecnica: agisce come punto di riferimento architetturale per evitare che il lavoro parallelo dei 4 programmatori degeneri in moduli incompatibili tra loro, prendendo le decisioni tecniche finali per sbloccare rapidamente eventuali stalli.


