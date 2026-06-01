package ms.bioskop.domain.data;

import java.util.List;
import ms.bioskop.domain.model.Pelanggan;

public interface PelangganDAO {
    public void save(Pelanggan pelanggan);
    public List<Pelanggan> get();
}
