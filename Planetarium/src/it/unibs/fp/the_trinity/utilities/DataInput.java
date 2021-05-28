package it.unibs.fp.the_trinity.utilities;

import java.util.*;

public class DataInput {
    private static final Scanner reader = createScanner();

    private final static String FORMAT_ERROR = "Attenzione: il dato inserito non e' nel formato corretto";
    private final static String MINIMUM_ERROR = "Attenzione: e' richiesto un valore maggiore a ";
    private final static String EMPTY_STRING_ERROR = "Attenzione: non hai inserito alcun carattere";
    private final static String MAXIMUM_ERROR = "Attenzione: e' richiesto un valore minore o uguale a ";
    private final static String ALLOWED_CHARS = "Attenzione: i caratteri ammissibili sono: ";

    private final static char YES = 'S';
    private final static char NO = 'N';


    private static Scanner createScanner() {
        Scanner created = new Scanner(System.in);
        created.useDelimiter(System.lineSeparator() + "|\n");
        return created;
    }

    public static String readString(String message) {
        System.out.print(message);
        return reader.next();
    }

    public static String readNotEmptyString(String message) {
        boolean ended = false;
        String read = null;
        do {
            read = readString(message);
            read = read.trim();
            if (read.length() > 0)
                ended = true;
            else
                System.out.println(EMPTY_STRING_ERROR);
        } while (!ended);

        return read;
    }

    public static char readChar(String message) {
        boolean ended = false;
        char readValue = '\0';
        do {
            System.out.print(message);
            String reading = reader.next();
            if (reading.length() > 0) {
                readValue = reading.charAt(0);
                ended = true;
            } else {
                System.out.println(EMPTY_STRING_ERROR);
            }
        } while (!ended);
        return readValue;
    }

    public static char readUpperChar(String read, String allowed) {
        boolean ended = false;
        char readValue = '\0';
        do {
            readValue = readChar(read);
            readValue = Character.toUpperCase(readValue);
            if (allowed.indexOf(readValue) != -1)
                ended = true;
            else
                System.out.println(ALLOWED_CHARS + allowed);
        } while (!ended);
        return readValue;
    }


    public static int readInt(String message) {
        boolean ended = false;
        int readValue = 0;
        do {
            System.out.print(message);
            try {
                readValue = reader.nextInt();
                ended = true;
            } catch (InputMismatchException e) {
                System.out.println(FORMAT_ERROR);
                String moveToTrash = reader.next();
            }
        } while (!ended);
        return readValue;
    }

    public static int readPositiveInt(String message) {
        return readIntWithMinimum(message, 1);
    }

    public static int readNonNegativeInt(String message) {
        return readIntWithMinimum(message, 0);
    }


    public static int readIntWithMinimum(String message, int minimum) {
        boolean ended = false;
        int readValue = 0;
        do {
            readValue = readInt(message);
            if (readValue >= minimum)
                ended = true;
            else
                System.out.println(MINIMUM_ERROR + minimum);
        } while (!ended);

        return readValue;
    }

    public static int readIntWIthMaxAndMin(String message, int minimum, int maximum) {
        boolean finito = false;
        int readValue = 0;
        do {
            readValue = readInt(message);
            if (readValue >= minimum && readValue <= maximum)
                finito = true;
            else if (readValue < minimum)
                System.out.println(MINIMUM_ERROR + minimum);
            else
                System.out.println(MAXIMUM_ERROR + maximum);
        } while (!finito);

        return readValue;
    }


    public static double readDouble(String message) {
        boolean ended = false;
        double readValue = 0;
        do {
            System.out.print(message);
            try {
                readValue = reader.nextDouble();
                ended = true;
            } catch (InputMismatchException e) {
                System.out.println(FORMAT_ERROR);
                String moveToTrash = reader.next();
            }
        } while (!ended);
        return readValue;
    }

    public static double readPositiveDouble(String message) {
        return readDoubleWithMinimum(message, 0);
    }

    public static double readDoubleWithMinimum(String message, double minimum) {
        boolean finito = false;
        double readValue = 0;
        do {
            readValue = readDouble(message);
            if (readValue > minimum)
                finito = true;
            else
                System.out.println(MINIMUM_ERROR + minimum);
        } while (!finito);

        return readValue;
    }


    public static boolean yesOrNo(String message) {
        String myMessage = message + "(" + YES + "/" + NO + ")";
        char readValue = readUpperChar(myMessage, String.valueOf(YES) + String.valueOf(NO));

        if (readValue == YES)
            return true;
        else
            return false;
    }

}
