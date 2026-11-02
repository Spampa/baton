# Specifiche dei Requisiti

Il team sceglie la priorità dei requisiti basandosi sul criterio di MoSCoW e basandosi sui software già esistenti, la nostra esperienza nell'uso per trarne i requisiti.

## Requisiti Funzionali

### Must Have
- **RF-01** Configurazione Blocchi
- **RF-02** Gestione errori nelle connessioni e validazione, se un blocco fallisce mostra a schermo il motivo (senza crash)
- **RF-03** Database embedded: bisogna salvare i progetti, ordine di esecuzione, i blocchi e la configurazione di ognuno
- **RF-04** UI: collegamenti blocchi con linee, rendering blocchi e configurazione, più diramazioni
- **RF-05** Gestione di più progetti
- **RF-06** Motore di esecuzione dei flow chart
- **RF-07** Blocchi fondamentali da sviluppare:
  - Trigger Manuale
  - Lettura File (es come CSV)
  - Trasformazione testo (elaborazione stringhe)
  - Blocco di Calcolo
  - Blocco condizione
  - Scrittura su file

### Should Have
- **RF-08** Blocchi:
  - Richieste HTTP GET
  - Trigger Programmato (Timer)
  - Ritardo di N secondi
- **RF-09** Esportazione / Caricamento progetto
- **RF-10** Creazione di cicli for e while
- **RF-11** Stile dello sfondo blueprint

### Could Have
- **RF-12** Blocchi:
  - Lettura metadati file immagine
  - Trigger nuovo file in cartella
  - Nodo ChatGPT (API Key)
  - Trigger Telegram
  - Nodo Notion
  - Esecuzione comando da terminale
- **RF-13** Il testimone che si muove tra i nodi (il Baton)

### Won't Have
- Esecuzione parallela nodi
- Multiutente
- Connessione blocchi con Login OAuth su altre piattaforme (es: Gmail)

## Requisiti Non Funzionali
- **RNF-01 Portabilità**: Windows, MacOS, Debian; Java è particolarmente adatto alla portabilità perché usa bytecode con JVM
- **RNF-02 Affidabilità**: un blocco in errore non fa crashare l'app, controllo di cicli infiniti
- **RNF-03 Manutenibilità**: motore facilmente estendibile con nuovi nodi semplicemente andando ad implementare nuove classi senza toccare il core
- **RNF-04 Usabilità**: basso tempo di onboarding

## Vincoli
- Linguaggio Java 21, dipendenze gestite con Maven, progetti importabili in Eclipse
- Database embedded SQLite
- Libreria UI: da definire (proposta: JavaFX)