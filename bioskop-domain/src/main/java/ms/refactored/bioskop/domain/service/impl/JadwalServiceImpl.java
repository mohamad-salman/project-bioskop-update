package ms.refactored.bioskop.domain.service.impl;

import java.time.LocalTime;
import java.util.List;
import ms.refactored.bioskop.domain.model.Film;
import ms.refactored.bioskop.domain.model.Jadwal;
import ms.refactored.bioskop.domain.service.JadwalService;

public class JadwalServiceImpl implements JadwalService {

    @Override
    public List<Jadwal> get() {
        return List.of(
                new Jadwal(new Film("001", "Steve Jobs", 55_000), LocalTime.now()),
                new Jadwal(new Film("002", "The Social Network", 60_000), LocalTime.now()),
                new Jadwal(new Film("003", "Takedown", 55_000), LocalTime.now()),
                new Jadwal(new Film("004", "Hackers", 55_000), LocalTime.now()),
                new Jadwal(new Film("005", "The Matrix", 50_000), LocalTime.now())
        );
    }

}
