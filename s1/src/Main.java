import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner=new Scanner(System.in);
        var nume=scanner.nextLine();
        //var nume=scanner.next(); pana la primul spatiu
        System.out.printf("Hello %s!%n", nume);

        System.out.print("a= ");
        int a=Integer.parseInt(scanner.nextLine());

        System.out.print("b= ");
        int b=scanner.nextInt();

        System.out.println(a);
        System.out.println(b);

        System.out.printf("%d + %d= %d%n", a,b,a+b);


    }
}