package ms.refactored.bioskop.cli.menu;

import ms.refactored.bioskop.cli.util.InputUtil;

public class Menu {

    private static final long MENU_DAFTAR_PELANGGAN = 1;
    private static final long MENU_PEMESANAN_TIKET = 2;

    private final MenuJadwalBioskop menuJadwalBioskop;

    public Menu() {
        menuJadwalBioskop = new MenuJadwalBioskop();
    }

    public void tampilkan() {
        System.out.println("\n\n\n==========================================================");
        System.out.println("****************Welcome to Movimax bioskop****************");
        System.out.println("==========================================================");

        menuJadwalBioskop.tampilkan();

        System.out.println("\n*****************$ Warning!!! $*****************"
                + "\nDaftar terlebih dahulu sebelum memesan tiket"
                + "\nHanya yang telah terdaftar yang dapat memesan tiket");
        System.out.println("====================================================");

        System.out.println("\n1.Daftar terlebih dahulu...");
        System.out.println("2.Memesan tiket bioskop....");
        System.out.println("====================================================");
    }

    public long getNoMenuDiPilih() {
        String promptMsg = "\nPilih: ";
        long jumlahMenuYangBisaDiPilih = 2; // daftar pelanggan dan pesan tiket

        return InputUtil.getInputLong(promptMsg, jumlahMenuYangBisaDiPilih);
    }

    public MenuItem createMenuItem(long noMenu) {
        if (noMenu == MENU_DAFTAR_PELANGGAN) {
            return new MenuItemDaftarPelanggan();
        } else if (noMenu == MENU_PEMESANAN_TIKET) {
            return new MenuItemPemesananTiket();
        }
        return null;
    }
}
