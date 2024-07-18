import java.util.Objects;

public final class Produs {
    private final int cod;
    private final String deumire;

    public Produs(int cod, String deumire) {
        this.cod = cod;
        this.deumire = deumire;
    }

    public Produs(int cod) {
        this(cod, "-");
    }

    public int getCod() {
        return cod;
    }

    public String getDeumire() {
        return deumire;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "cod=" + cod +
                ", deumire='" + deumire + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produs produs = (Produs) o;
        return cod == produs.cod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cod);
    }
}
