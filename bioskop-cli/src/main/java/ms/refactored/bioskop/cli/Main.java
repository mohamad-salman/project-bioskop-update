package ms.refactored.bioskop.cli;

import ms.refactored.bioskop.cli.menu.Menu;
import static ms.refactored.bioskop.cli.util.InputUtil.konfirmasi;

public class Main {

    public static void main(String[] args) {
        boolean lanjud = true;

        do {
            var menuUtama = new Menu();
            menuUtama.tampilkan();

            var noMenu = menuUtama.getNoMenuDiPilih();
            var menuItem = menuUtama.createMenuItem(noMenu);
            menuItem.run();

            lanjud = konfirmasi("\nApakah anda ingin melanjudkan???");

            if (!lanjud) {
                System.out.println("\nTerimakasih");
            }

        } while (lanjud);
    }

}
