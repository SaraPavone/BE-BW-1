package sarapavo.utils;

import java.util.Scanner;

public class Autogestionale {

    public static int menuSelezione(Scanner input, String string) {

        int inputInt = 0;

        String[] selezioni = string.split(",");

        do {
            System.out.println(menuSelezione(string));
            try {
                inputInt = Integer.parseInt(input.nextLine());
            }catch(NumberFormatException e) {
                System.out.println("Seleziona un numero da 0 a " + (selezioni.length) + "\n\n");
                inputInt = -1;
            }
        } while (inputInt<0||inputInt>selezioni.length);
        return inputInt;
    }

    public static String menuSelezione(String string) {
        String menuSelezione = "";
        String[] selezioni = string.split(",");
        for (int i = 0;i<selezioni.length;i++) {
            if (i==selezioni.length-1) menuSelezione += (i+1) + " - " + selezioni[i] + "\n0 - Esci";
            else menuSelezione += (i+1) + " - " + selezioni[i] + "\n";
        }
        return menuSelezione;
    }

}
