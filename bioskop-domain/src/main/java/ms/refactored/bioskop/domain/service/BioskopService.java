package ms.refactored.bioskop.domain.service;

import ms.refactored.bioskop.domain.service.impl.JadwalServiceImpl;
import ms.refactored.bioskop.domain.service.impl.PelangganServiceImpl;

public class BioskopService {

    public static JadwalService createJadwalService() {
        return new JadwalServiceImpl();
    }
    
    public static PelangganService createPelangganService() {
        return new PelangganServiceImpl();
    }
    
}
