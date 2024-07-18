import java.io.Serializable;

public final class Contact implements Serializable {
    private final int cod;
    private final String nume;
    private final String telefon;

    public Contact(int cod, String nume, String telefon) {
        this.cod = cod;
        this.nume = nume;
        this.telefon = telefon;
    }

    public int getCod() {
        return cod;
    }

    public String getNume() {
        return nume;
    }

    public String getTelefon() {
        return telefon;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "cod=" + cod +
                ", nume='" + nume + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}
