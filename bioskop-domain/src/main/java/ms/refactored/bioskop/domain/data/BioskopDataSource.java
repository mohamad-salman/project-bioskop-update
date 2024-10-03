package ms.refactored.bioskop.domain.data;

import ms.refactored.bioskop.domain.data.impl.PelangganDAOFileCSV;

public class BioskopDataSource {

    public static PelangganDAO getPelangganDAO() {
        return new PelangganDAOFileCSV();
    }
    
}
