package ms.refactored.bioskop.domain.model;

import java.time.LocalTime;

public record Jadwal(Film film, LocalTime jam) {

}
