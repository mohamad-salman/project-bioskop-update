package ms.refactored.bioskop.cli.menu;

import java.util.Scanner;
import static ms.refactored.bioskop.cli.util.InputUtil.konfirmasi;
import ms.refactored.bioskop.domain.service.BioskopService;

public final class MenuItemDaftarPelanggan implements MenuItem {

    private final boolean dariPemesananTiket;

    public MenuItemDaftarPelanggan() {
        dariPemesananTiket = false;
    }

    public MenuItemDaftarPelanggan(boolean dariPemesananTiket) {
        this.dariPemesananTiket = dariPemesananTiket;
    }

    @Override
    public void run() {
        //      Mengambil input dari user
        String nama = getInputNama();
        String jenisKelamin = getInputJenisKelamin();
        String tlp = getInputTelp();

        boolean daftar = konfirmasiDataYangDiInput(nama, jenisKelamin, tlp);
        if (daftar) {
            simpanData(nama, jenisKelamin, tlp);

            System.out.println("\nTerimakasih telah mendaftar...\nDan silahkan memesan tiketnya...\n");

            if (!dariPemesananTiket) {
                pemesananTiket();
            }
        } else {
            System.out.println("\nTerimakasih atas partisipasinya");
        }

    }

    private String getInputNama() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukan User name       : ");
        String nama = input.nextLine();

        while (nama.isEmpty()) {
            System.err.println("Wajib mengisi nama");
            System.out.print("\nMasukan User name       : ");
            nama = input.nextLine();
        }

        return nama;
    }

    private String getInputJenisKelamin() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukan jenis kelamin (Pria / Wanita) : ");
        String kelamin = input.nextLine();

        while (!kelamin.equalsIgnoreCase("pria") && !kelamin.equalsIgnoreCase("wanita")) {
            System.err.println("Maaf masukan sesuai intruksi (Pria / Wanita)");
            System.out.print("\nMasukan jenis kelamin (Pria / Wanita) : ");
            kelamin = input.nextLine();
        }

        return kelamin;
    }

    private String getInputTelp() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukan nomor Tpl       : ");
        String tlp = input.nextLine();

        while (!tlp.chars().allMatch(Character::isDigit) || tlp.trim().length() == 0) {
            System.err.println("Nomor telphon hanya menerima angka saja");
            System.out.print("\nMasukan nomor Tpl       : ");
            tlp = input.nextLine();
        }

        return tlp;
    }

    private boolean konfirmasiDataYangDiInput(String nama, String jenisKelamin, String tlp) {
        System.out.println("\n===============================================================");
        System.out.println("**************** [ DATA YANG ANDA MASUKAN ] ****************");
        System.out.println("===============================================================");
        System.out.println("User name             : " + nama);
        System.out.println("jenis kelamin kelamin : " + jenisKelamin);
        System.out.println("Nomor telphon         : " + tlp);
        System.out.println("===============================================================");

        return konfirmasi("\nApakah anda yakin akan mendaftar???");
    }

    private void simpanData(String nama, String jenisKelamin, String tlp) {
        var pelangganService = BioskopService.createPelangganService();
        pelangganService.save(nama, jenisKelamin, tlp);
    }

    private void pemesananTiket() {
        new MenuItemPemesananTiket().run();
    }

}
