package ms.refactored;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean lanjud = true;

        while (lanjud) {

            System.out.println("\n\n\n==========================================================");
            System.out.println("****************Welcome to Movimax bioskop****************");
            System.out.println("==========================================================");

            System.out.println("\nDaftar film : ");
            lihat();

            System.out.println("\n*****************$ Warning!!! $*****************"
                    + "\nDaftar terlebih dahulu sebelum memesan tiket"
                    + "\nHanya yang telah terdaftar yang dapat memesan tiket");
            System.out.println("====================================================");

            System.out.println("\n1.Daftar terlebih dahulu...");
            System.out.println("2.Memesan tiket bioskop....");

            String pilih = cekPilih();

            if (pilih.equalsIgnoreCase("1")) {
//          ======================= 
                daftar();
//          =======================
            } else if (pilih.equalsIgnoreCase("2")) {
//          =======================
                pemesananTiket();
//          ======================= 
            }

            lanjud = ulang("Apakah anda ingin melanjudkan???");

            if (!lanjud) {
                System.out.println("\nTerimakasih");
            }

        }
    }

    private static String cekId() {

        Scanner input = new Scanner(System.in);

        System.out.print("\nMasukan nomor Id        : ");
        String id = input.nextLine();

        while (id.length() > 4 || id.length() < 4) {
            System.err.println("id tidak valit masukan id sebanyak ( **** )");
            System.out.print("\nMasukan nomor Id        : ");
            id = input.nextLine();
        }

        return id;

    }

    private static String cekTlp() {

        Scanner input = new Scanner(System.in);

        System.out.print("Masukan nomor Tpl       : ");
        String tlp = input.nextLine();

        while (!tlp.chars().allMatch(Character::isDigit) || tlp.trim().length() == 0 || tlp.length() > 13 || tlp.length() < 12) {
            System.err.println("Nomor telphon tidak sesuai");
            System.out.print("\nMasukan nomor Tpl       : ");
            tlp = input.nextLine();

        }

        return tlp;

    }

    private static String cekNama() {

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

    private static String cekKelamin() {
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

    private static String cekPilih() {

        Scanner input = new Scanner(System.in);

        System.out.print("\nPilih : ");
        String pilih = input.nextLine();

        while (!pilih.chars().allMatch(Character::isDigit) || pilih.trim().length() == 0 || !pilih.equalsIgnoreCase("1") && !pilih.equalsIgnoreCase("2")) {
            System.err.println("Pilihan tidak sesuai");
            System.out.print("\nPilih : ");
            pilih = input.nextLine();

        }

        return pilih;

    }

    private static void daftar() throws IOException {

        FileWriter data;
        BufferedWriter file = null;
        try {
            data = new FileWriter("DataUserBioskop.txt", true);
            file = new BufferedWriter(data);
        } catch (Exception e) {
            System.err.println("Maaf DATABASE tidak di temukan"
                    + "Silahkan membuat DATABASE terlebih dahulu");
        }
        tiket tiket = new tiket();

        boolean islanjud2 = true;
        int nomor = 1;

        while (islanjud2) {
            nomor++;
//      Mengambil input dari user
            String id = cekId().toUpperCase();
            String tlp = cekTlp();
            String nama = cekNama();
            String kelamin = cekKelamin();

//      Memberikan nilai pada setter
            tiket.setId(id);
            tiket.setnoTlp(tlp);
            tiket.setNama(nama);
            tiket.setKelamin(kelamin);

            boolean periksa_id = priksaId(id, false);

            if (!periksa_id) {

                System.out.println("\n===============================================================");
                System.out.println("**************** [ DATA YANG ANDA MASUKAN ] ****************");
                System.out.println("===============================================================");
                System.out.println("Id                    : " + id);
                System.out.println("Nomor telphon         : " + tlp);
                System.out.println("User name             : " + nama);
                System.out.println("jenis kelamin kelamin : " + kelamin);
                System.out.println("===============================================================");

                boolean daftar = ulang("Apakah anda yakin akan mendaftar???");
                if (daftar) {
                    file.write(tiket.getId().toUpperCase() + "," + tiket.getnoTlp() + "," + tiket.getNama().toLowerCase() + "," + tiket.getKelamin().toLowerCase());
                    file.newLine();
                    file.flush();

                    System.out.println("\nTerimakasih telah mendaftar...\nDan silahkan memesan tiketnya...\n");

                    pemesananTiket();

                } else {
                    System.out.println("\nTerimakasih atas partisipasinya");
                }

            } else {
                System.out.println("\n\nDengan id [ " + id + " ] telah tersedia di databases\nDan tidak dapat menambahkan id yang sama");
                System.out.println("\nDengan data berikut : ");
                priksaId(id, true);
                System.out.println("\nMasukan input yang ke [ " + nomor + " ] X !!!");
            }

            islanjud2 = periksa_id;

        }

    }

    private static void pemesananTiket() throws IOException {

//      Memberi waktu
        Date dataWaktu = new Date();
        SimpleDateFormat waktu = new SimpleDateFormat("dd.MMMM.yyyy");

//      Menjadikan sebuah Objek
        Scanner input = new Scanner(System.in);
        tiket tiket = new tiket();

        String namaFilm = null;
        String film = null;
        int kode = 0;
        int harga = 0;

        boolean daftarDulu = false;
        boolean islanjud = true;

        while (islanjud) {
            System.out.println("\n=========================================================");
            System.out.println("************[ PEMESANAN TIKET BIOSKOP ]************");
            System.out.println("=========================================================");
//      Mengambil input dari user
            String id2 = cekId();
            String tlp2 = cekTlp();
            String nama2 = cekNama();
            String kelamin2 = cekKelamin();

//      Memberikan nilai pada setter
            tiket.setId(id2);
            tiket.setnoTlp(tlp2);
            tiket.setNama(nama2);
            tiket.setKelamin(kelamin2);

//      Memeriksa data pada database txt
            String[] periksa = {id2 + "," + tlp2 + "," + nama2 + "," + kelamin2};
            boolean isPeriksa = priksaId(periksa);

            if (isPeriksa) {

//      Melihat data film
                lihat();

//      Memilih film
                System.out.print("Masukan film kesukaan anda : ");
                film = input.next();

                while (!film.equalsIgnoreCase("1") && !film.equalsIgnoreCase("2") && !film.equalsIgnoreCase("3") && !film.equalsIgnoreCase("4") && !film.equalsIgnoreCase("5")) {
                    System.err.println("Masukan input sesuai daftar");
                    System.out.print("\nMasukan film kesukaan anda : ");
                    film = input.next();
                }

                switch (film) {
                    case "1":
                        namaFilm = "Stive jobs";
                        harga = 55000;
                        kode = 001;
                        break;
                    case "2":
                        namaFilm = "The social network ";
                        harga = 60000;
                        kode = 002;
                        break;
                    case "3":
                        namaFilm = "Takedown";
                        harga = 55000;
                        kode = 003;
                        break;
                    case "4":
                        namaFilm = "Hackers";
                        harga = 55000;
                        kode = 004;
                        break;
                    case "5":
                        namaFilm = "The matrix";
                        harga = 50000;
                        kode = 005;
                        break;
                    default:
                        System.err.println("Maaf input tidak di temukan");
                        break;
                }

                tiket.setHarga(harga);
                tiket.setKode(kode);

                boolean pesan = ulang("Apakah anda yakin akan memesan tiket bioskop");

                if (pesan) {

                    System.out.println("\n----------------------------------------------------------");
                    System.out.println("*****************[ TIKET PESANAN ANDA ]*****************");
                    System.out.println("----------------------------------------------------------");
                    System.out.println("| Id                   : " + tiket.getId().toUpperCase());
                    System.out.println("| Telpon               : " + tiket.getnoTlp());
                    System.out.println("| User name            : " + tiket.getNama().toLowerCase());
                    System.out.println("| Jenis kelamin        : " + tiket.getKelamin().toLowerCase());
                    System.out.println("----------------------------------------------------------");
                    System.out.println("| Film                 : " + namaFilm.toLowerCase());
                    System.out.println("| Harga                : Rp." + tiket.getHarga());
                    System.out.println("| Kode                 : " + tiket.getKode());
                    System.out.println("----------------------------------------------------------");
                    System.out.println("| Waktu transaksi      : " + waktu.format(dataWaktu));
                    System.out.println("----------------------------------------------------------");
                    islanjud = false;

                } else if (!pesan) {
                    System.out.println("\nTerimakasih atas pertisipasinya...");
                    islanjud = false;
                }

            } else {
                System.out.println("\n*****************$ Warning!!! $*****************");
                System.out.println("Akun anda tidak di temukan\nMungkin anda salah memasukan input atau belum mendaftar\nSilahkan mendaftar untuk memesan tiketnya...");
                System.out.println("================================================");

                daftarDulu = ulang("Apakah anda ingin mendaftar???");

                if (daftarDulu) {
                    daftar();
                    islanjud = false;
                } else if (!daftarDulu) {
                    islanjud = true;
                }

            }

        }

    }

    private static void lihat() throws IOException {

        FileReader reader = new FileReader("adakah.txt");
        BufferedReader file = new BufferedReader(reader);

        String data = file.readLine();
        System.out.println("\n| No |       judul film       |   Jam tayang   |");
        System.out.println("-----------------------------------------------");
        int nomor = 0;

        while (data != null) {
            nomor++;
            StringTokenizer token = new StringTokenizer(data, ",");
            System.out.printf("|%3d ", nomor);
            System.out.printf("|\t%-21s ", token.nextToken());
            System.out.printf("|\t  %-8s ", token.nextToken());
            System.out.print("\n");
            data = file.readLine();

        }

        System.out.println("-----------------------------------------------");

    }

    private static boolean priksaId(String[] keywords) throws IOException {

        FileReader reader;
        BufferedReader file = null;

        try {
            reader = new FileReader("DataUserBioskop.txt");
            file = new BufferedReader(reader);
        } catch (Exception e) {
            System.err.println("Database tidak tersedia\nSilahkan membuat databases terlebihdahulu");
        }

        String data = file.readLine();

        boolean isexis = false;
        while (data != null) {

            isexis = true;
            for (String keyword : keywords) {
                isexis = isexis && data.toLowerCase().contains(keyword.toLowerCase());
            }

            if (isexis) {
                break;
            }

            data = file.readLine();
        }

        return isexis;
    }

    private static boolean priksaId(String id, boolean isUlang) throws IOException {

        FileReader reader;
        BufferedReader file = null;

        try {
            reader = new FileReader("DataUserBioskop.txt");
            file = new BufferedReader(reader);
        } catch (Exception e) {
            System.err.println("Database tidak tersedia\nSilahkan membuat databases terlebihdahulu");
        }

        String data = file.readLine();

        if (isUlang) {
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.println("|  Id   |\tNomor telephon         |\tUser name                 |\tJenis kelamin              |");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        }

        boolean isexis = false;
        int nomor = 0;
        while (data != null) {

            isexis = true;
            for (int i = 0; i < id.length(); i++) {
                isexis = isexis && data.toLowerCase().contains(id.toLowerCase());
            }

            if (isexis) {

                if (isUlang) {
                    nomor++;
                    StringTokenizer token = new StringTokenizer(data, ",");
                    System.out.printf("|  %s ", token.nextToken());
                    System.out.printf("|\t%-23s", token.nextToken());
                    System.out.printf("|\t%-26s", token.nextToken());
                    System.out.printf("|\t%-20s", token.nextToken());
                    System.out.print("\n");
                } else {
                    break;
                }
            }

            data = file.readLine();
        }

        if (isUlang) {
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        }

        return isexis;
    }

    private static boolean ulang(String masage) {
        Scanner input = new Scanner(System.in);
        System.out.print("\n" + masage + "(y/n) : ");
        String data = input.next();

        while (!data.equalsIgnoreCase("y") && !data.equalsIgnoreCase("n")) {
            System.err.println("Maaf input tidak di kenali");
            System.out.print("\n" + masage + "(y/n) : ");
            data = input.next();
        }

        return data.equalsIgnoreCase("y");

    }
}
