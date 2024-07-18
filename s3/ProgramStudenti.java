import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ProgramStudenti {

    private static Student[] centralizator = new Student[0];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        afisare("Inițial");
        citireStudenti();
        afisare("După citire studenți");

        citireCatalog();
        afisare("După citire catalog SD");

        citireCatalog();
        afisare("După citire catalog Java");

         //afisareCatalog("Structuri de date");
         afisareCatalog("Programare Java");
    }

    static void afisareCatalog(String disciplina) {
        var numarStudenti=0;
        for(var student : centralizator)
        {
            if(student.getNota(disciplina)!=Nota.NotaInvalida)
            {
                numarStudenti++;
            }
        }

        class ElementCatalog implements Comparable{
            public String nume;
            public int nota;

            public ElementCatalog(String nume, int nota) {
                this.nume = nume;
                this.nota = nota;
            }

            @Override
            public int compareTo(Object o) {
                return -Integer.compare(nota, ((ElementCatalog)o).nota);
            }
        }

        var catalog=new Student[numarStudenti];
        int indexCatalog=0;
        for(var student: centralizator)
        {
            if(student.getNota(disciplina)!=Nota.NotaInvalida)
            {
            catalog[indexCatalog]=student;
            indexCatalog=indexCatalog+1;
            }
        }

       // Arrays.sort(catalog, "Programare Java");

        System.out.println("----"+disciplina+"("+numarStudenti+" studenti) -----");
//        System.out.println("numar studenti: "+numarStudenti);
        for(var student: catalog)
        {
            System.out.printf("%-20s %2d%n",
                    student.getNume(), student
                            .getNota(disciplina));
        }
    }

    static void afisare(String mesaj) {
        System.out.println(mesaj + ":\n");
        for (var student : centralizator) {
            System.out.println(student);
        }
        System.out.println("--------------------------------------------------------------");
    }


    static void citireStudenti() {

        int numarStudenti = Integer.parseInt(scanner.nextLine());

        centralizator = new Student[numarStudenti];

        for (int index = 0; index < numarStudenti; index++) {

            String linieStudent = scanner.nextLine();
            String linieNote = scanner.nextLine();

            var student = new Student(
                    Integer.parseInt(linieStudent.split(",")[0]),
                    linieStudent.split(",")[1],
                    Integer.parseInt(linieStudent.split(",")[2]),
                    Integer.parseInt(linieStudent.split(",")[3])
            );

            var elementeNote = linieNote.split(",");
            for (int indexNota = 1; indexNota < elementeNote.length; indexNota += 2) {
                var disciplina = elementeNote[indexNota - 1];
                var nota = Integer.parseInt(elementeNote[indexNota]);

                student.add(new Nota(disciplina, nota));
            }

            centralizator[index] = student;
        }

    }

    static void citireCatalog() {

        var disciplina = scanner.nextLine();
        var numarNote = Integer.parseInt(scanner.nextLine());

        buclaStudent:
        for (int index = 0; index < numarNote; index++) {
            var linieNota = scanner.nextLine();
            int idStudent = Integer.parseInt(linieNota.split(",")[0]);
            int nota = Integer.parseInt(linieNota.split(",")[1]);

            for (var student : centralizator) {
                if (student.getIdStudent() == idStudent) {
                    student.add(new Nota(disciplina, nota));
                    continue buclaStudent;
                }
            }

            System.err.println("Cod student invalid: #" + idStudent);
        }
    }
}

class ComparatorNota implements Comparator
        {
            String disciplina;

            public ComparatorNota(String disciplina)
            {
                this.disciplina=disciplina;
            }

            @Override
            public int compare(Object o1, Object o2) {
                var nota1=((Student)o1).getNota(disciplina);
                var nota2=((Student)o2).getNota(disciplina);
                return -Integer.compare(nota1, nota2);
            }
        }