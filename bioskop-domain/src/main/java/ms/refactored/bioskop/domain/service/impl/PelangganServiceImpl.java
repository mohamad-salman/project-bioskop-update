package ms.refactored.bioskop.domain.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import ms.refactored.bioskop.domain.data.BioskopDataSource;
import ms.refactored.bioskop.domain.data.PelangganDAO;
import ms.refactored.bioskop.domain.model.Pelanggan;
import ms.refactored.bioskop.domain.service.PelangganService;

public class PelangganServiceImpl implements PelangganService {

    private final PelangganDAO pelangganDAO;

    public PelangganServiceImpl() {
        pelangganDAO = BioskopDataSource.getPelangganDAO();
    }

    @Override
    public void save(String nama, String jenisKelamin, String telp) {
        var newPelanggan = new Pelanggan(nama, telp, jenisKelamin);

        pelangganDAO.save(newPelanggan);
    }

    @Override
    public long getTotalJumlahPelanggan() {
        return pelangganDAO.get().size();
    }

    @Override
    public Optional<Pelanggan> getPelangganById(long id) {
        return pelangganDAO.get().stream()
                .filter(p -> id == p.id())
                .findAny();
    }

    @Override
    public Set<Long> getIdPelangganYangTerdaftar() {
        return pelangganDAO.get().stream()
                .map(p -> p.id())
                .collect(Collectors.toSet());
    }

    @Override
    public List<Pelanggan> getDataPelanggan() {
        return pelangganDAO.get();
    }
    
}
