# Project Plan

Progetto svolto da Nicolò Spampatti (Matr. 1099040), Marco Magri (Matr. 1100084), Giovanni Ghilardi (Matr. 1099052), Matteo Piffari (Matr. 1099051)

## 1. Introduzione
Baton è un software sviluppato in Java per creare automazioni basate su blocchi. Baton si ispira ai più noti progetti come [n8n](https://n8n.io/) oppure [Node-RED](https://nodered.org/). Lo sviluppo punta a realizzare un prodotto portabile, affidabile e manutenibile, dotato di un motore di esecuzione facilmente estendibile e di un'interfaccia per collegare visualmente i vari blocchi. Il modo di operare adottato è incrementale, nello specifico secondo il modello RAD (Rapid Application Development).

## 2. Modello di Processo
Il team seguirà un ciclo di vita incrementale basato sul modello RAD (Rapid Application Development). Il lavoro sarà organizzato in timebox di due settimane, ciascuno dei quali produrrà un incremento funzionante da integrare e verificare. Le fasi RAD saranno rappresentate da milestone: Requirements Planning (6 novembre 2026), un unico ciclo User Design & Construction (20 dicembre 2026) e Cutover (6 gennaio 2027). Per non spostare la scadenza, a ogni incremento si applicherà MoSCoW: se il tempo residuo non è sufficiente, si ridurrà lo scope rinviando o escludendo le funzionalità Could Have e Won't Have. Il calendario complessivo va dal 2 novembre 2026 al 6 gennaio 2027; i dettagli dei timebox sono descritti nel documento di Project Management (sezione 1.2).

## 3. Organizzazione del Progetto
Il gruppo si auto-organizzerà adottando la struttura di un Team SWAT. I ruoli saranno fluidi in base alle necessità del momento. Il coordinamento avverrà tramite la Kanban board di GitHub, le pull request per la validazione del lavoro e l'uso di Discord per la comunicazione rapida.

## 4. Standard, linee guida, procedure
Lo sviluppo sarà effettuato con Java 21 e il gestore di dipendenze Maven. I progetti dovranno essere importabili in Eclipse, senza imporre l'uso esclusivo di questo IDE. Tutto il lavoro (codice e documentazione) sarà tracciato su GitHub mediante commit regolari, uso mirato dei branch e file `.gitignore` per escludere binari e target. Si seguiranno le convenzioni di programmazione Java standard.

## 5. Attività di gestione
L'avanzamento dei lavori sarà guidato dalla gestione delle issue su GitHub e dall'impiego della metodologia MoSCoW (Must Have, Should Have, Could Have, Won't Have) per definire la priorità dei requisiti implementativi.

## 6. Rischi
I rischi principali individuati e le relative mitigazioni sono:
* **Complessità dell'editor a grafo:** la gestione di collegamenti, posizionamento e validazione dei blocchi può richiedere più tempo del previsto. Si svilupperà prima un editor minimo, limitando lo scope alle funzionalità prioritarie secondo MoSCoW e verificandolo con test e scenari d'uso a ogni incremento.
* **Integrazione tra le aree:** interfaccia, motore di esecuzione, persistenza e blocchi possono risultare incompatibili durante l'integrazione. Si definiranno interfacce stabili, si integrerà frequentemente tramite Pull Request e si manterranno test automatici per i contratti tra moduli.
* **Gestione degli errori dei blocchi:** un errore in un blocco potrebbe causare il crash dell'intera applicazione. Il motore dovrà isolare l'errore, interrompere l'automazione in modo controllato e mostrare all'utente il blocco e la causa del fallimento; il comportamento sarà verificato con test JUnit.
* **Conflitti di codice:** modifiche parallele possono causare conflitti durante i merge su GitHub. Si useranno branch brevi, Pull Request frequenti e code review obbligatorie.
* **Tecnologia UI ancora da definire:** la scelta della libreria UI può introdurre vincoli di integrazione o di portabilità. La decisione sarà verificata con un prototipo dell'editor e registrata nell'SRS prima di vincolare l'implementazione.
L'utilizzo di un database embedded (SQLite) mitiga i rischi legati alla configurazione di server esterni.

## 7. Personale
Il team è costituito da quattro sviluppatori (Spampatti, Magri, Ghilardi, Piffari). È stimato un impiego medio di 30-40 ore a persona per coprire tutte le fasi, dalla modellazione UML alla codifica fino al collaudo finale.

## 8. Metodi e tecniche
Per l'ingegneria del software verrà ampiamente utilizzato StarUML (versione 5) per tracciare Use Case, Class Diagram (con successiva autogenerazione del codice scheletro), State Machine, Sequence, Communication o Timing Diagram, Activity, Component e Package Diagram. Il testing verrà effettuato tramite casi di test JUnit mantenuti all'interno di una directory dedicata del progetto Maven (`src/test/java/`). Per il logging applicativo si valuterà Log4j, indicandolo come libreria esterna Maven nel `pom.xml` qualora venga adottato.

## 9. Garanzia di qualità
La qualità del codice sarà garantita dal rispetto delle logiche dell'Information Hiding, dall'utilizzo coerente del polimorfismo, dal basso accoppiamento tra i package e dall'applicazione di design pattern mirati. Ogni Pull Request dovrà ricevere una review obbligatoria prima dell'integrazione. I test JUnit relativi al codice modificato dovranno essere inclusi nella stessa Pull Request del codice. L'affidabilità in fase di runtime (gestione blocchi in errore) sarà validata dai test di unità.

## 10. Pacchetti di lavoro (workpackages)
Le macro-attività (o workpackages) individuate sono:
* **WP1**: Elicitazione e raffinamento dei requisiti.
* **WP2**: Progettazione e generazione dell'architettura UML.
* **WP3**: Creazione della base dati (SQLite) e logica di salvataggio/esportazione dei progetti.
* **WP4**: Sviluppo del motore di esecuzione interno e dei blocchi (Trigger, Lettura, Calcolo, Scrittura).
* **WP5**: Creazione della UI grafica e logica di connessione dei blocchi.
* **WP6**: Sviluppo dei test JUnit e stesura della documentazione finale.

## 11. Risorse
Oltre alle postazioni hardware personali dei membri, le risorse primarie includono gli ambienti di sviluppo (Eclipse), tool di modellazione UML (StarUML), strumenti Java (Java 21, Maven e JUnit), una libreria UI ancora da definire, il DB (SQLite) e il repository (GitHub).

## 12. Budget e programma
Il progetto non prevede alcun budget economico. Le milestone del programma sono Requirements Planning il 6 novembre 2026, User Design & Construction il 20 dicembre 2026 e Cutover il 6 gennaio 2027. Il lavoro è organizzato in timebox di due settimane; il project plan deve essere approvato tramite Issue entro il 14 dicembre 2026 e l'esame è previsto per il 14 gennaio 2027. Lo sviluppo e il commit finale devono concludersi entro il 6 gennaio 2027.

## 13. Cambiamenti
Data l'adozione del paradigma RAD, le modifiche in corso d'opera sono previste. Ogni cambiamento sostanziale ad architettura o requisiti dovrà essere discusso dal team e formalizzato all'interno di una specifica Pull Request o Issue su GitHub per poterne tenere traccia nella history.

## 14. Consegna
La delivery coinciderà con il repository GitHub aggiornato. Questo conterrà:
* La documentazione in formato PDF o Markdown organizzata in capitoli.
* I file/cartelle del progetto Eclipse importabili e funzionanti (compresi i file `.project`, `.classpath` e `pom.xml`) senza dipendenze assolute dal file system locale.
* I modelli UML realizzati con StarUML.