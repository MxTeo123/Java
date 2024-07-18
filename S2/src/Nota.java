public class Nota {
    String numeDisciplina;
    int nota;

    public Nota(String numeDisciplina, int nota) {
        this.numeDisciplina = numeDisciplina;
        this.nota = nota;
    }

    public String getNumeDisciplina() {
        return numeDisciplina;
    }

    public void setNumeDisciplina(String numeDisciplina) {
        this.numeDisciplina = numeDisciplina;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return String.format("%-18s %2d", numeDisciplina, nota);
    }
}
