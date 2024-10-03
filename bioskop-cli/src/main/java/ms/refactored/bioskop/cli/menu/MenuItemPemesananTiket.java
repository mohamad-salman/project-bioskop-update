package ms.refactored.bioskop.cli.menu;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import ms.refactored.bioskop.cli.util.InputUtil;
import ms.refactored.bioskop.domain.model.Jadwal;
import ms.refactored.bioskop.domain.model.Pelanggan;
import ms.refactored.bioskop.domain.service.BioskopService;
import ms.refactored.bioskop.domain.service.PelangganService;

public final class MenuItemPemesananTiket implements MenuItem {

    private final PelangganService pelangganService;
    private final MenuJadwalBioskop menuJadwalBioskop;

    public MenuItemPemesananTiket() {
        pelangganService = BioskopService.createPelangganService();
        menuJadwalBioskop = new MenuJadwalBioskop();
    }

    @Override

    public void run() {
        if (dataPelangganBelumAda()) {
            System.out.println("\nINFO: Data pelangan belum ada, buat terlebih dahulu...\n");
            tambahPelangganBaru();
        } 

        System.out.println("\n=========================================================");
        System.out.println("************[ PEMESANAN TIKET BIOSKOP ]************");
        System.out.println("=========================================================");

        var penonton = getInputPelanggan();
        var jadwalFilm = getInputJadwalFilm();

        var pesan = InputUtil.konfirmasi("\nApakah anda yakin akan memesan tiket bioskop");
        if (pesan) {
            System.out.println("\n----------------------------------------------------------");
            System.out.println("*****************[ TIKET PESANAN ANDA ]*****************");
            System.out.println("----------------------------------------------------------");
            System.out.println("| Id                   : " + penonton.id());
            System.out.println("| Telpon               : " + penonton.telp());
            System.out.println("| User name            : " + penonton.nama());
            System.out.println("| Jenis kelamin        : " + penonton.jenisKelamin());
            System.out.println("----------------------------------------------------------");
            System.out.println("| Film                 : " + jadwalFilm.film().judul());
            System.out.println("| Harga                : Rp." + jadwalFilm.film().harga());
            System.out.println("| Kode                 : " + jadwalFilm.film().kode());
            System.out.println("----------------------------------------------------------");
            System.out.println("| Waktu transaksi      : " + formatJam(jadwalFilm.jam()));
            System.out.println("----------------------------------------------------------");
        } else {
            System.out.println("\nTerimakasih atas pertisipasinya...");
        }

    }

    private boolean dataPelangganBelumAda() {
        return pelangganService.getDataPelanggan().isEmpty();
    }

    private void tambahPelangganBaru() {
        new MenuItemDaftarPelanggan(true).run();
    }

    private Pelanggan getInputPelanggan() {

        var idPelanggan = getInputIdPelanggan();
        var pelanggan = pelangganService.getPelangganById(idPelanggan);

        return pelanggan.get();
    }

    private long getInputIdPelanggan() {
        var promptMsg = "\nMasukkan id pelanggan: ";
        var kodeYangTerdaftar = pelangganService.getIdPelangganYangTerdaftar();

        return InputUtil.getInputLong(promptMsg, kodeYangTerdaftar);
    }

    private Jadwal getInputJadwalFilm() {
        var idFilm = getIdFilm();
        var jadwalFilm = menuJadwalBioskop.getFilmByKode(idFilm);

        return jadwalFilm.get();
    }

    private String getIdFilm() {
        var promptMsg = "Masukkan id film: ";
        var idYangTerdaftar = menuJadwalBioskop.getIdFilmYangDiJadwalkan();

        return InputUtil.getInputString(promptMsg, idYangTerdaftar);
    }

    private String formatJam(LocalTime waktu) {
        var timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        return waktu.format(timeFormatter);
    }

}
