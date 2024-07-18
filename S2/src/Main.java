import java.util.Scanner;

public class Main {

    static Scanner scanner=new Scanner(System.in);

    static Student[] citireStudenti()
    {
        var nrStudenti=Integer.parseInt(scanner.nextLine());
        var studenti=new Student[nrStudenti];
        for(var i=0; i<nrStudenti;i++)
        {
            var linie=scanner.nextLine().split(",");

            var student=new Student(
                    Integer.parseInt(linie[0]),
                            linie[1],
                            linie[2],
                            Integer.parseInt(linie[3])

            );
            studenti[i]=student;
            linie=scanner.nextLine().split(",");
            for(int j=0;j<linie.length;j+=2 )
            {
                var denumire=linie[j];
                var valoare=Integer.parseInt(linie[j+1]);
                student.add(new Nota(denumire, valoare));
            }
        }
        return studenti;

    }
    static void afisareStudenti(Student[] studenti, String mesaj){
        System.out.println(mesaj+"");
        for( var student : studenti){
            System.out.println(student);
        }
        System.out.println();
    }


    public static void main(String[] args) {
        System.out.println(new Nota("Java", 10));
        Student s1 = new Student(1, "Ion", "1011A", 3);
        System.out.println(s1);
        var studenti = citireStudenti();
        afisareStudenti(studenti, "Dupa citire");
    }
}