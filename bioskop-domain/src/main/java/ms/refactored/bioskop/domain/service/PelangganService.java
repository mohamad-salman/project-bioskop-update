package ms.refactored.bioskop.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import ms.refactored.bioskop.domain.model.Pelanggan;

public interface PelangganService {
    public void save(String nama, String jenisKelamin, String telp);
    public long getTotalJumlahPelanggan();
    public Optional<Pelanggan> getPelangganById(long id);
    public Set<Long> getIdPelangganYangTerdaftar();
    public List<Pelanggan> getDataPelanggan();
}
