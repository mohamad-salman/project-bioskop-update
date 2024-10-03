package ms.refactored.bioskop.domain.model;

public record Pelanggan(long id, String nama, String telp, String jenisKelamin) {

    public Pelanggan(String nama, String telp, String jenisKelamin) {
        this(0, nama, telp, jenisKelamin);
    }

}
