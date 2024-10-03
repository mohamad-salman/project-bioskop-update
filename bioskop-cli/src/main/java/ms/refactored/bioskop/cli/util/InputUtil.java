package ms.refactored.bioskop.cli.util;

import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;

public class InputUtil {

    public static boolean konfirmasi(String promptMsg) {
        Predicate<String> isYes = s -> s.equalsIgnoreCase("y");
        Predicate<String> isNo = s -> s.equalsIgnoreCase("n");

        var scanner = new Scanner(System.in);

        do {
            System.out.print(promptMsg + " (y/n): ");
            var input = scanner.next();

            if (isYes.test(input)) {
                return true;
            } else if (isNo.test(input)) {
                return false;
            } else {
                System.err.println("Maaf input tidak di kenali\n");
            }
        } while (true);
    }

    public static long getInputLong(String promptMsg, long max) {
        final long NOL = 0;
        var scanner = new Scanner(System.in);

        do {
            System.out.print(promptMsg);
            long angka = scanner.nextLong();

            if (angka > NOL && angka <= max) {
                return angka;
            } else {
                System.err.println("Hanya menerima angka 1 s/d " + max + "\n");
            }

        } while (true);
    }
    
    public static long getInputLong(String promptMsg, Set<Long> acceptedNumbers) {
        var scanner = new Scanner(System.in);

        do {
            System.out.print(promptMsg);
            long angka = scanner.nextLong();

            var num = acceptedNumbers.stream()
                    .filter(n -> angka == n)
                    .findAny();
            
            if (num.isPresent()) {
                return angka;
            } else {
                System.err.println(angka + " tidak terdaftar\n");
            }

        } while (true);
    }

    public static String getInputString(String msg, Set<String> acceptedStrings) {
        var scanner = new Scanner(System.in);

        do {
            System.out.print(msg);
            var input = scanner.nextLine();

            var str = acceptedStrings.stream()
                    .filter(s -> input.equalsIgnoreCase(s))
                    .findAny();

            if (str.isPresent()) {
                return str.get();
            } else {
                System.err.println(input + " tidak dikenal\n");
            }
        } while (true);
    }  

}
