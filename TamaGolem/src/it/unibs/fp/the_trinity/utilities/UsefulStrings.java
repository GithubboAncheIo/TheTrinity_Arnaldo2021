/*
 * Copyright (c) 2021, The Trinity and/or its affiliates. All rights reserved.
 * THE TRINITY PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package it.unibs.fp.the_trinity.utilities;

/**
 * @author Iannella Simone
 */
public class UsefulStrings {
    public static final String FRAME = "‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗‗";
    public static final String DOUBLE_LINE = FRAME + "\n" + FRAME + "\n";

    public static final String TAMAGOLEM_NAME = "Tamagolem ";
    public static final String ELEMENTS_VS = """
            "%s VS %s"
            """;
    public static final String TAMAGOLEM_DAMAGE = "Il Tamagolem di %s ha inflitto %d danni al Tamagolem di %s.";
    public static final String TAMAGOLEM_LIFE = "Al %s di %s rimangono %d/%d punti vita.";
    public static final String NULL_INTERACTION = "Interazione nulla! Nessun TamaGolem danneggiato.";
    public static final String END_QUESTION = "Volete giocare una nuova partita?(S/N)";
    public static final String WINNER = "\nHA VINTO IL PLAYER %s! COMPLIMENTI\n\n";



    public static final String SELECT_LEVEL = """

            =================================================
             Scegli il livello di difficolta' della partita:
            =================================================
             » Facile (1)
             » Normale (2)
             » Difficile (3)
            """;

    public static final String PLAYER_1_NAME_REQUEST = """
            =======================================
             Inserire il nome del Primo Giocatore:
            =======================================\n """;

    public static final String PLAYER_2_NAME_REQUEST = """
            =========================================
             Inserire il nome del Secondo Giocatore:
            =========================================\n """;

    public static final String PLAYER_2_SECOND_NAME_REQUEST = """
            =============================================================
             Inserire un nome (diverso da \"%s\") per il Secondo Giocatore:
            =============================================================
            """;
    public static final String HOMONYM_MESSAGE = """
            E' stato inserito lo stesso identificativo per entrambi i giocatori.
                
            ========================================================================
             Vuoi che al Secondo Giocatore venga assegnato un nome d'ufficio? (S/N)
            ========================================================================
            """;
    public static final String HOMONYM_FIXED_MESSAGE = "Congratulazioni Giocatore 2! \nIl tuo nuovo nome ora è \"";
    public static final String HOW_MANY_TAMAGOLEMS = "\n •Ogni giocatore schiera %d Tamagolem.\n";
    public static final String ONE_TAMAGOLEM = "\n •Ogni giocatore schiera un Tamagolem.\n";
    public static final String HOW_MANY_ELEMENTS = " •Sono stati estratti %d elementi.\n";
    public static final String HOW_MANY_STONES = " •Il numero di pietre da assegnare è %d.\n";

    public static final String SETTING_ELEMENTS = "\n%s, ora devi scegliere quali pietre dare al tuo Tamagolem.\n";

    public static final String CHOOSE_ELEMENT_NAME = """
                
            =================================================
             Inserire il nome (o l'iniziale) di un elemento:
            =================================================
            """;

    //public static final String SETTING_STONES_NUMBER_FOR_ELEMENT = "Inserire quante pietre assegnare all'elemento %s:\n";

    public static final String START_FIGHT_MESSAGE = "\n%s e %s, state per cominciare la battaglia. Inizieremo con l'evocazione dei Tamagolem." +
            "\nLa procedura di evocazione di un Tamagolem è una sottofase che ricorre più volte durante lo scontro. In generale," +
            "\nl’evocazione interrompe lo nscontro ogni qualvolta sia necessario schierare in campo un nuovo Tamagolem da parte" +
            "\ndi un giocatore, evento che si verifica quando il Tamagolem precedente del giocatore è eliminato dallo scontro," +
            "\novvero perde tutti i punti vita: %d." +
            "\nL’evocazione si verifica solo se il giocatore ha ancora Tamagolem disponibili, ossia il numero di Tamagolem" +
            "\neliminati è strettamente inferiore a %d. In caso affermativo:" +
            "\n1. Un nuovo Tamagolem viene selezionato per la fase dell’evocazione; la sua vita viene inizializzata a %d." +
            "\n2. Il giocatore seleziona esattamente %d pietre degli elementi da far mangiare al Tamagolem, fra quelle a sua" +
            "\n\tdisposizione. Le pietre selezionate verranno scagliate ciclicamente finché il Tamagolem resterà in vita." +
            "\n3. Il Tamagolem viene schierato in campo, l’evocazione termina e lo scontro può procedere.\n";

    public static final String WELCOME_MESSAGE =
            """
                                        
                    +=============================================================================================================================+
                    |{>/-----------------------------------------------------------------------------------------------------------------------\\<}|
                    |:|     █▄▄ █▀▀ █▄ █ █ █ █▀▀ █▄ █ █ █ ▀█▀ █▀█   █ █▄ █                                                                      |:|
                    |:|     █▄█ ██▄ █ ▀█ ▀▄▀ ██▄ █ ▀█ █▄█  █  █▄█   █ █ ▀█                                                                      |:|
                    |:|                                                                                                                         |:|
                    |:|      ███        ▄████████   ▄▄▄▄███▄▄▄▄      ▄████████    ▄██████▄   ▄██████▄   ▄█          ▄████████   ▄▄▄▄███▄▄▄▄     |:|
                    |:|  ▀█████████▄   ███    ███ ▄██▀▀▀███▀▀▀██▄   ███    ███   ███    ███ ███    ███ ███         ███    ███ ▄██▀▀▀███▀▀▀██▄   |:|
                    |:|     ▀███▀▀██   ███    ███ ███   ███   ███   ███    ███   ███    █▀  ███    ███ ███         ███    █▀  ███   ███   ███   |:|
                    |:|      ███   ▀   ███    ███ ███   ███   ███   ███    ███  ▄███        ███    ███ ███        ▄███▄▄▄     ███   ███   ███   |:|
                    |:|      ███     ▀███████████ ███   ███   ███ ▀███████████ ▀▀███ ████▄  ███    ███ ███       ▀▀███▀▀▀     ███   ███   ███   |:|
                    |:|      ███       ███    ███ ███   ███   ███   ███    ███   ███    ███ ███    ███ ███         ███    █▄  ███   ███   ███   |:|
                    |:|      ███       ███    ███ ███   ███   ███   ███    ███   ███    ███ ███    ███ ███▌    ▄   ███    ███ ███   ███   ███   |:|
                    |:|     ▄████▀     ███    █▀   ▀█   ███   █▀    ███    █▀    ████████▀   ▀██████▀  █████▄▄██   ██████████  ▀█   ███   █▀    |:|
                    |{>\\-----------------------------------------------------------------------------------------------------------------------/<}|
                    +=============================================================================================================================+
                    """;

         /*
         """
         █▄▄ █▀▀ █▄ █ █ █ █▀▀ █▄ █ █ █ ▀█▀ █▀█   █ █▄ █
         █▄█ ██▄ █ ▀█ ▀▄▀ ██▄ █ ▀█ █▄█  █  █▄█   █ █ ▀█

           ███        ▄████████   ▄▄▄▄███▄▄▄▄      ▄████████    ▄██████▄   ▄██████▄   ▄█          ▄████████   ▄▄▄▄███▄▄▄▄
       ▀█████████▄   ███    ███ ▄██▀▀▀███▀▀▀██▄   ███    ███   ███    ███ ███    ███ ███         ███    ███ ▄██▀▀▀███▀▀▀██▄
          ▀███▀▀██   ███    ███ ███   ███   ███   ███    ███   ███    █▀  ███    ███ ███         ███    █▀  ███   ███   ███
           ███   ▀   ███    ███ ███   ███   ███   ███    ███  ▄███        ███    ███ ███        ▄███▄▄▄     ███   ███   ███
           ███     ▀███████████ ███   ███   ███ ▀███████████ ▀▀███ ████▄  ███    ███ ███       ▀▀███▀▀▀     ███   ███   ███
           ███       ███    ███ ███   ███   ███   ███    ███   ███    ███ ███    ███ ███         ███    █▄  ███   ███   ███
           ███       ███    ███ ███   ███   ███   ███    ███   ███    ███ ███    ███ ███▌    ▄   ███    ███ ███   ███   ███
          ▄████▀     ███    █▀   ▀█   ███   █▀    ███    █▀    ████████▀   ▀██████▀  █████▄▄██   ██████████  ▀█   ███   █▀ """; */

                /*"  _____                         _          _       \n" +
                " | __  |___ ___ _ _ ___ ___ _ _| |_ ___   |_|___   \n" +
                " | __ -| -_|   | | | -_|   | | |  _| . |  | |   |  \n" +
                " |_____|___|_|_|\\_/|___|_|_|___|_| |___|  |_|_|_|";

 "  ▄▄▄▄· ▄▄▄ . ▐ ▄  ▌ ▐·▄▄▄ . ▐ ▄ ▄• ▄▌▄▄▄▄▄          ▪   ▐ ▄ \n" +
 " ▐█ ▀█▪▀▄.▀·•█▌▐█▪█·█▌▀▄.▀·•█▌▐██▪██▌•██  ▪         ██ •█▌▐█ \n" +
 " ▐█▀▀█▄▐▀▀▪▄▐█▐▐▌▐█▐█•▐▀▀▪▄▐█▐▐▌█▌▐█▌ ▐█.▪ ▄█▀▄     ▐█·▐█▐▐▌ \n" +
 " ██▄▪▐█▐█▄▄▌██▐█▌ ███ ▐█▄▄▌██▐█▌▐█▄█▌ ▐█▌·▐█▌.▐▌    ▐█▌██▐█▌ \n" +
 " ·▀▀▀▀  ▀▀▀ ▀▀ █▪. ▀   ▀▀▀ ▀▀ █▪ ▀▀▀  ▀▀▀  ▀█▄▀▪    ▀▀▀▀▀ █▪";

    ██████╗░███████╗███╗░░██╗██╗░░░██╗███████╗███╗░░██╗██╗░░░██╗████████╗░█████╗░       ██╗███╗░░██╗
    ██╔══██╗██╔════╝████╗░██║██║░░░██║██╔════╝████╗░██║██║░░░██║╚══██╔══╝██╔══██╗       ██║████╗░██║
    ██████╦╝█████╗░░██╔██╗██║╚██╗░██╔╝█████╗░░██╔██╗██║██║░░░██║░░░██║░░░██║░░██║       ██║██╔██╗██║
    ██╔══██╗██╔══╝░░██║╚████║░╚████╔╝░██╔══╝░░██║╚████║██║░░░██║░░░██║░░░██║░░██║       ██║██║╚████║
    ██████╦╝███████╗██║░╚███║░░╚██╔╝░░███████╗██║░╚███║╚██████╔╝░░░██║░░░╚█████╔╝       ██║██║░╚███║
    ╚═════╝░╚══════╝╚═╝░░╚══╝░░░╚═╝░░░╚══════╝╚═╝░░╚══╝░╚═════╝░░░░╚═╝░░░░╚════╝░       ╚═╝╚═╝░░╚══╝*/

              /*" ______   ____  ___ ___   ____   ____   ___   _        ___  ___ ___   \n" +
                "|      | /    ||   |   | /    | /    | /   \\ | |      /  _]|   |   | \n" +
                "|      ||  o  || _   _ ||  o  ||   __||     || |     /  [_ | _   _ |  \n" +
                "|_|  |_||     ||  \\_/  ||     ||  |  ||  O  || |___ |    _]|  \\_/  |\n" +
                "  |  |  |  _  ||   |   ||  _  ||  |_ ||     ||     ||   [_ |   |   |  \n" +
                "  |  |  |  |  ||   |   ||  |  ||     ||     ||     ||     ||   |   |  \n" +
                "  |__|  |__|__||___|___||__|__||___,_| \\___/ |_____||_____||___|___| \n";*/

    public static final String[] HOMONYMY_NAMES = {
            "Franco",
            "Vincenzo",
            "Lagrange",
            "Robber",
            "TetoShfonda",
            "Keyshan",
            "Palombaro Procopio",
            "Barry Bee Benson",
            "ZebA99",
            "Shaggy all'1%",
            "La testa di Art Attack",
            "Christian 'Ice'",
            "Papa Bergoglio II",
            "La Winx che non si caga nessuno",
            "Paolo Brosio",
            "Terry A. Davis",
            "Emilio è Peggio",
            "Emilio Credenza",
            "Teo Mammupoveri",
            "Gerry Freddie",
            "Enrico Daddy",
            "THE_REAL_JOSH"
    };

    public static final String[] DEATH_MESSAGES = {
            "Il Tamagolem di %s e' morto male.\n",
            "Il Tamagolem di %s e' morto bene.\n",
            "Il Tamagolem di %s ci ha abbandonati e mai piu' tornera'.\n",
            "Il Tamagolem di %s e' a Lavandonia invece che in un centro Pokemon.\n",
            "Il Tamagolem di %s e' morto da eroe, le sue gesta verranno ricordate.\n",
            "E' gia' stato predisposto il funerale del Tamagolem di %s.\n",
            "Il Tamagolem di %s vivra' nel ricordo dei suoi cari. Che non aveva.\n",
            "Il Tamagolem di %s vivra' nel ricordo dei suoi cari.\n"
    };

    public static final String[] CONDOLENCE_MESSAGE = {
            "Siamo colpiti per la grave perdita e vi siamo vicini in questa circostanza di dolore.\n",
            "Anche se le parole sono ben poca cosa in queste circostanze, vogliate accettare le mie piu' sincere condoglianze.\n",
            "Vi siamo vicini in un momento così difficile e ci uniamo al vostro dolore.\n",
            "Ci uniamo al vostro dolore. Sentite condoglianze.\n",
            "Condoglianze per il lutto che ha colpito la vostra famiglia.\n",
            "Non riusciamo ad esprimere con le parole il dolore avuto nell’apprendere la triste notizia, ma siamo partecipi al vostro cordoglio.\n",
            "Profondamente rattristati per la disgrazia, porgiamo le nostre più sentite condoglianze.\n",
            "Vi siamo vicini in questo triste giorno.\n",
            "Preghiamo Dio che vi dia la forza di superare questo triste momento.\n",
            "Sapendo bene il grande amore che vi univa, prego Dio affinche' ti aiuti a superare questo dolore.\n",
            "Il tempo lenira' il dolore, ma il vivo ricordo del piccolo Tamagolem ci accompagnera' per sempre.\n",
            "Ci stringiamo a voi nel ricordo e nel dolore per una persona speciale che e' venuta a mancare troppo presto.\n",
            "Colleghi e amici sono vicini al vostro dolore. Vogliate accettare le nostre piu' sentite condoglianze.\n",
            "Abbiamo appreso con ritardo la tragica notizia, ma ci uniamo al vostro dolore esprimendovi un sincero cordoglio.\n",
            "Piango la prematura scomparsa del piccolo Tamagolem. Il vostro dolore e' anche il mio.\n",
            "Ho appreso la notizia con un immenso dolore. Che il ricordo dei bei momenti passati insieme possa lenire il tuo dolore.\n",
            "Sinceramente addolorati per questo triste evento, Vi porgiamo le nostre piu' sentite condoglianze e Vi offriamo tutto il nostro sostegno.\n",
            "Raggiunti dalla notizia della tragica scomparsa del piccolo Tamagolem, vi abbracciamo forte e offriamo il nostro sostegno per qualsiasi cosa.\n",
            "Mi unisco al vostro dolore per la grande perdita che vi ha colpiti. Le mie più sincere condoglianze.\n",
            "Ci uniamo alla vostra famiglia in questo doloroso momento con commozione e affetto.\n"
    };

    public static final String[] ERROR_STRINGS = {
            "Scappellamento a destra come se fosse Antani.",
            "Errore di inserimento. That's what she said.",
            "Si e' verificato un problema. Reinstalla il Sistema Operativo.",
            "Non dovresti non inserire un dato non invalido.",
            "Inserire un dato valido.",
            "ERROR 404 (NOT FOUND)",
            "Si e' verificato un errore al momento del tuo concepimento.",
            "ERRORE ERRORE ERRORE",
            "Se rilevi problemi nel leggere le istruzioni, attiva la Sintesi Vocale (consigliato il TTS \"Inglese Indiano\")." +
                    "\nAh beh tanto non potrai leggere neanche questo messaggio. \nGolden Shower.",
            "How dare you. Don't mess with the Pietro.",
            "Greta Thunberg non sarebbe contenta nel vederti sprecare energia.",
            "Mark Zuckerberg non e' contento nello spiare cose che non gli servono.",
            "Non arrenderti! \nAnche una scimmia che digita caratteri casuali per l'eternita' puo' scrivere perfettamente ogni testo mai concepito.",
            "I comandi sono quelli. Non puoi sbagliare. \nO forse si'. \nNon dovresti poter sbagliare. Ecco.",
            "Cadiamo solo per imparare a rialzarci.",
            "Il Conte Dracula Vlad diceva: \"Sbagliando si impala\""
    };

    public static final String[] GOODBYE_STRINGS = {
            "Torni presto da noi. Se stanno operando coercizione su di lei, faccia \"No\" con la testa. Non si preoccupi, la stiamo osservando dalla Webcam.",
            "Se hai rilevato qualche bug, inviaci una mail all'indirizzo movetotrash@tmpmail.com",
            "Torni presto da noi. Dai nostri dati statistici risulta che la maggior parte dei nostri utenti soffre di depressione." +
                    "\nSe e' il Suo caso contatti il Numero Verde a Pagamento contenuto nella documentazione." +
                    "\nSe invece non soffre di depressione e vuole provare, riapra questo programma finche' non produce effetti.",
            "Addioo, addiooo, aamici aaddio \nNoi ciiiiiiiii dobbiamo laasciaaaree \nMa ehi, io dico, che e' oookkeey!",
            "See You Space Cowboy.",
            "E' stato un bell'esame, a prostata.",
            "ARI ARI ARI ARI ARI ARI Arrivederci!"
    };

    public static String getHomonymName() {
        return HOMONYMY_NAMES[(int) (Math.random() * HOMONYMY_NAMES.length)];
    }

    public static String getErrorString() {
        return ERROR_STRINGS[(int) (Math.random() * ERROR_STRINGS.length)];
    }

    public static String getGoodbyeString() {
        return GOODBYE_STRINGS[(int) (Math.random() * GOODBYE_STRINGS.length)];
    }

    public static String getCondolenceMessage() {
        return CONDOLENCE_MESSAGE[(int) (Math.random() * CONDOLENCE_MESSAGE.length)];
    }

    public static String getDeathMessage() {
        return DEATH_MESSAGES[(int) (Math.random() * DEATH_MESSAGES.length)];
    }

    public static String player2SecondNameRequest(String player1Name) {
        return String.format(PLAYER_2_SECOND_NAME_REQUEST, player1Name);
    }

    public static String getStartFightMessage(String player1, String player2, int nGolems, int energy, int nStones) {
        return String.format(START_FIGHT_MESSAGE, player1, player2, energy, nGolems, energy, nStones);
    }

    public static String getElementsVs(String elementA, String elementB) {
        return String.format(ELEMENTS_VS, elementA, elementB);
    }

    public static String getTamagolemDamage(String matchWinner, int damage, String matchLoser) {
        return String.format(TAMAGOLEM_DAMAGE, matchWinner, damage, matchLoser);
    }

    public static String getTamagolemLife(String tamaName, String player, int life, int energy) {
        return String.format(TAMAGOLEM_LIFE, tamaName, player, life, energy);
    }

    public static String getWinner(String winner) {
        return String.format(WINNER, winner);
    }
}