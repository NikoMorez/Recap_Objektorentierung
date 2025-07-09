package org.example;

public class PrintOut {

    private static boolean printService = true;
    private static boolean printServiceTest = true;

    public static void Print(String message) {
        if(printService){
            System.out.println(message);
        }

    }

    public static void PrintGreen(String message) {
        if(printService){
            System.out.println(ConsoleColor.GREEN + message + ConsoleColor.RESET);
        }

    }

    public static void PrintYellow(String message) {
        if(printService){
            System.out.println(ConsoleColor.YELLOW + message + ConsoleColor.RESET);
        }

    }

    public static void PrintWhiteGreen(String message) {
        if(printService){
            System.out.println(ConsoleColor.BG_WHITE + ConsoleColor.GREEN +  message + ConsoleColor.RESET);
        }

    }

    public static void PrintError(String message) {
        if(printService){
            System.out.println(ConsoleColor.BG_RED  + ConsoleColor.BLACK+ message + ConsoleColor.RESET);
        }
    }

    public static void PrintRed(String message) {
        if(printService){
            System.out.println( ConsoleColor.RED +  message + ConsoleColor.RESET);
        }
    }

    public static void PrintLog(String message) {
        if(printService){
            System.out.println( ConsoleColor.BLUE +  message + ConsoleColor.RESET);
        }
    }


    public static void TestPrint(String message) {
        if(printServiceTest){
            System.out.println(message);
        }

    }



}

class ConsoleColor {

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_PURPLE = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";

}

