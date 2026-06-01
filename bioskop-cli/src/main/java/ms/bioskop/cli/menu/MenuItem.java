package ms.bioskop.cli.menu;

public sealed interface MenuItem
        permits MenuItemDaftarPelanggan, MenuItemPemesananTiket {

    public void run();

}
