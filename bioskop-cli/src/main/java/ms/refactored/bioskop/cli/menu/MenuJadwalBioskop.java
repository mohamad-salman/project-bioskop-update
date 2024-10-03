package ms.refactored.bioskop.cli.menu;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import ms.refactored.bioskop.domain.model.Jadwal;
import ms.refactored.bioskop.domain.service.BioskopService;
import ms.refactored.bioskop.domain.service.JadwalService;

public class MenuJadwalBioskop {

    private final JadwalService jadwalService;
    private final Map<Long, Jadwal> menuJadwalBioskop;

    public MenuJadwalBioskop() {
        jadwalService = BioskopService.createJadwalService();
        menuJadwalBioskop = createJadwal();
    }

    private Map<Long, Jadwal> createJadwal() {
        Map<Long, Jadwal> _menuJadwalBioskop = new HashMap<>();
        var no = new AtomicLong(1);
        for (Jadwal jadwal : jadwalService.get()) {
            _menuJadwalBioskop.put(no.get(), jadwal);
            no.getAndIncrement();
        }
        return _menuJadwalBioskop;
    }

    public void tampilkan() {
        System.out.println("\n| No |       judul film       |   Jam tayang   |");
        System.out.println("-----------------------------------------------");

        var timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        menuJadwalBioskop
                .forEach((no, j)
                        -> System.out.printf("  %-5s %-26s %s\n",
                        no, j.film().judul(), j.jam().format(timeFormatter))
                );
        System.out.println("-----------------------------------------------");
    }

    public Set<String> getIdFilmYangDiJadwalkan() {
        return menuJadwalBioskop.values().stream()
                .map(j -> j.film().kode())
                .collect(Collectors.toSet());
    }
    
    public Optional<Jadwal> getFilmByKode(String kode) {
        return menuJadwalBioskop.values().stream()
                .filter(j -> kode.equalsIgnoreCase(j.film().kode()))
                .findAny();
    }
}
