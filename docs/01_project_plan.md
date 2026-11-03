# Project Plan

Progetto svolto da Spampatti Nicolò (Matr. 1099040), Magri Marco (Matr. 1100084), Giovanni Ghilardi (Matr. 1099052), Matteo Piffari (Matr. 1099051)

## 1. Introduzione
Baton è un software sviluppato in Java per creare automazioni basate su nodi. Baton si ispira ai più noti progetti come [n8n](https://n8n.io/) oppure [Node-RED(https://nodered.org/)]. Lo sviluppo punta a realizzare un prodotto portabile, affidabile e manutenibile, dotato di un motore di esecuzione facilmente estendibile e di una interfaccia per collegare visualmente i vari blocchi.

## 2. Modello di Processo
Il team seguirà un ciclo di vita basato sul modello RAD (Rapid Application Development). Questo approccio permetterà di adattarsi agilmente durante lo sviluppo e di arrivare rapidamente a prototipi funzionanti da testare in circa 60-90 giorni.

## 3. Organizzazione del Progetto
Il gruppo si auto-organizzerà adottando la struttura di un Team SWAT. I ruoli saranno fluidi in base alle necessità del momento. Il coordinamento avverrà tramite la Kanban board di GitHub, le pull request per la validazione del lavoro e l'uso di Discord per la comunicazione rapida.

## 4. Standard, linee guida, procedure
Lo sviluppo sarà effettuato obbligatoriamente su IDE Eclipse, utilizzando Java  e un gestore di dipendenze Maven, . Tutto il lavoro (codice e documentazione) sarà tracciato su GitHub mediante commit regolari, uso mirato dei branch e file `.gitignore` per escludere binari e target. Si seguiranno le convenzioni di programmazione Java standard.

## 5. Attività di gestione
L'avanzamento dei lavori sarà guidato dalla gestione delle issue su GitHub e dall'impiego della metodologia MoSCoW (Must Have, Should Have, Could Have, Won't Have) per definire la priorità dei requisiti implementativi.

## 6. Rischi
I rischi principali individuati risiedono nella potenziale instabilità della libreria UI (JavaFX o alternative), nella gestione fluida degli errori dei blocchi per evitare il crash totale dell'app e nella gestione di eventuali conflitti di codice durante i merge su GitHub. L'utilizzo di un database embedded (SQLite) mitiga i rischi legati alla configurazione di server esterni.

## 7. Personale
Il team è costituito da quattro sviluppatori (Spampatti, Magri, Ghilardi, Piffari). È stimato un impiego medio di 30-40 ore a persona per coprire tutte le fasi, dalla modellazione UML alla codifica fino al collaudo finale.

## 8. Metodi e tecniche
Per l'ingegneria del software verrà ampiamente utilizzato StarUML (versione 5) per tracciare Use Case, Class Diagram (con successiva autogenerazione del codice scheletro), State Machine, Sequence, Activity, Component e Package Diagram. Il testing verrà effettuato tramite casi di test JUnit mantenuti all'interno di una directory dedicata del progetto Maven (`/test/java/`).

## 9. Garanzia di qualità
La qualità del codice sarà garantita dal rispetto delle logiche dell'Information Hiding, dall'utilizzo coerente del polimorfismo, dal basso accoppiamento tra i package e dall'applicazione di design pattern mirati. L'affidabilità in fase di runtime (gestione nodi in errore) sarà validata dai test di unità. 

## 10. Pacchetti di lavoro (workpackages)
Le macro-attività (o workpackages) individuate sono:
* **WP**: Elicitazione e raffinamento dei Requisiti.
* **WP**: Progettazione e generazione architettura UML.
* **WP**: Creazione base dati (SQLite) e logica di salvataggio/esportazione progetti.
* **WP**: Sviluppo del motore di esecuzione interno e dei nodi (Trigger, Lettura, Calcolo, Scrittura).
* **WP**: Creazione UI grafica e logica di connessione blocchi.
* **WP**: Sviluppo test JUnit e stesura della documentazione finale, 0.

## 11. Risorse
Oltre alle postazioni hardware personali dei membri, le risorse primarie includono gli ambienti di sviluppo (Eclipse), tool di modellazione UML (StarUML), framework Java (JavaFX, Maven, JUnit), DB (SQLite) e repository (GitHub).

## 12. Budget e programma
Il progetto non prevede alcun budget economico. Il project plan deve essere formalizzato tramite approvazione via Issue almeno un mese prima dell'appello d'esame. Lo sviluppo e il commit finale devono concludersi 5 giorni prima della data di presentazione.

## 13. Cambiamenti
Data l'adozione del paradigma RAD, le modifiche in corso d'opera sono previste. Ogni cambiamento sostanziale ad architettura o requisiti dovrà essere discusso dal team e formalizzato all'interno di una specifica Pull Request o Issue su GitHub per poterne tenere traccia nella history.

## 14. Consegna
La delivery coinciderà con il repository GitHub aggiornato. Questo conterrà:
* La documentazione in formato PDF o Markdown organizzata in capitoli.
* I file/cartelle del progetto Eclipse importabili e funzionanti (compreso il file `.project` e `pom.xml`) senza dipendenze assolute dal file system locale.