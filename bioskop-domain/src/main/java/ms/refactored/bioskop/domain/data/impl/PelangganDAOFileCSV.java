package ms.refactored.bioskop.domain.data.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import ms.refactored.bioskop.domain.data.PelangganDAO;
import ms.refactored.bioskop.domain.model.Pelanggan;

public class PelangganDAOFileCSV implements PelangganDAO {

    private final Path filePelanggan;

    public PelangganDAOFileCSV() {
        filePelanggan = createFilePelanggan();
    }

    private Path createFilePelanggan() {
        var resourceDir = createDataDirectory();
        var resourceFile = createResourceFile(resourceDir);

        if (!Files.exists(resourceFile)) {
            System.out.println("INFO: Database pelanggan tidak ditemukan, membuat yang baru...");
            try {
                Files.createFile(resourceFile);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        return resourceFile;
    }

    private String createDataDirectory() {
        var resourceDir = "data/csv";
        var dirPath = Paths.get(resourceDir);

        if (!Files.exists(dirPath)) {
            try {
                System.out.println("INFO: Direktori data belum ada, membuat yang baru...");
                Files.createDirectories(dirPath);
            } catch (IOException ex) {
                System.err.println("exc: " + ex);
            }
        }
        return resourceDir;
    }

    private Path createResourceFile(String resourceDir) {
        var resourceFile = new StringBuilder(resourceDir)
                .append("/pelanggan.csv")
                .toString();

        return Paths.get(resourceFile);
    }

    @Override
    public void save(Pelanggan pelangganWithoutId) {
        var pelanggan = createPelangganWithId(pelangganWithoutId);
        try {
            var csv = toCSV(pelanggan);
            Files.writeString(filePelanggan, csv, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.err.println("exc: " + ex);
        }
    }

    private Pelanggan createPelangganWithId(Pelanggan pelanggan) {
        var nextId = getNextId();
        var nama = pelanggan.nama();
        var jenisKelamin = pelanggan.jenisKelamin();
        var telp = pelanggan.telp();

        return new Pelanggan(nextId, nama, telp, jenisKelamin);
    }

    private long getNextId() {
        final long firstId = 1;
        Optional<Long> lastId = get().stream()
                .sorted(Comparator.comparingLong(Pelanggan::id).reversed())
                .map(p -> p.id())
                .findFirst();

        return lastId.isPresent() ? (lastId.get() + 1) : firstId;
    }

    @Override
    public List<Pelanggan> get() {
        List<Pelanggan> list = new ArrayList<>();
        try {
            Files.readAllLines(filePelanggan)
                    .forEach(csv -> list.add(toPelanggan(csv)));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return Collections.unmodifiableList(list);
    }

    private Pelanggan toPelanggan(String csv) {
        String[] csvArr = csv.split(",");

        int id = Integer.valueOf(csvArr[0]);
        String nama = csvArr[1];
        String jenisKelamin = csvArr[2];
        String telp = csvArr[3];

        return new Pelanggan(id, nama, telp, jenisKelamin);
    }

    private String toCSV(Pelanggan pelanggan) {
        String tuple = String.join(",",
                String.valueOf(pelanggan.id()),
                pelanggan.nama(),
                pelanggan.jenisKelamin(),
                pelanggan.telp()
        );

        return new StringBuilder(tuple)
                .append(System.lineSeparator())
                .toString();
    }

}
