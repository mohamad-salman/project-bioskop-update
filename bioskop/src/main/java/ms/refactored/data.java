package ms.refactored;

public abstract class data {

    protected String id;
    protected String nama;
    protected String noTlp;
    protected String kelamin;

    public String getId() {
        return this.id;
    }

    public String getNama() {
        return this.nama;
    }

    public String getnoTlp() {
        return this.noTlp;
    }

    public String getKelamin() {
        return kelamin;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setnoTlp(String noTlp) {
        this.noTlp = noTlp;
    }

    public void setKelamin(String kelamin) {
        this.kelamin = kelamin;
    }

}
