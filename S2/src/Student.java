import java.util.Arrays;

public class Student {
    int idStudent;
    String nume;
    String grupa;
    int an;
    Nota[] note;

    public Student(int idStudent, String nume, String grupa, int an) {
        this.idStudent = idStudent;
        this.nume = nume;
        this.grupa = grupa;
        this.an = an;
        this.note = new Nota[0];
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", nume='" + nume + '\'' +
                ", grupa='" + grupa + '\'' +
                ", an=" + an +
                ", note=" + Arrays.toString(note) +
                '}';
    }
    public void add(Nota nota)
    {
        for(var notaExistenta : note)
        {
            if(notaExistenta.getNumeDisciplina().equals(nota.getNumeDisciplina()))
            {
                notaExistenta.setNota(nota.getNota());
                return;
            }
        }
        note=Arrays.copyOf(note,note.length+1);
        note[note.length-1]=nota;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public Nota[] getNote() {
        return note;
    }

    public void setNote(Nota[] note) {
        this.note = note;
    }
}
