package ms.refactored.bioskop.cli.menu;

public sealed interface MenuItem
        permits MenuItemDaftarPelanggan, MenuItemPemesananTiket {

    public void run();

}
