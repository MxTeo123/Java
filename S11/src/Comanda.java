import java.io.Serializable;

public final class Comanda implements Serializable {
    private final String denumire;
    private final Object parametru;

    public Comanda(String denumire, Object parametru) {
        this.denumire = denumire;
        this.parametru = parametru;
    }

    public String getDenumire() {
        return denumire;
    }

    public Object getParametru() {
        return parametru;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "denumire='" + denumire + '\'' +
                ", parametru=" + parametru +
                '}';
    }
}
