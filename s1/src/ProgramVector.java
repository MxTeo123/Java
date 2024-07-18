public class ProgramVector {
    private static void afisare(int[] vector, String mesaj)
    {
        System.out.printf("%s(%d elemente): ", mesaj,vector.length);
        for(int valoare: vector)
        {
            System.out.print(valoare + " ");
        }
    }

    static void incrementare(int[] vector)
    {
    for(int i=0; i< vector.length; i++)
    {
        vector[i]++;
    }
    }

    static int[] inserare(int[]vector, int n)
    {
        int[] aux=new int[vector.length+1];
        for(var i=0; i<vector.length;i++)
        {
            aux[i]=vector[i];
        }
        aux[vector.length]=n;

        return aux;
    }

    public static void main(String[] args) {
//        System.out.printf("nr param: %d%n", args.length);
//        for(String valoare: args)
//        {
//            System.out.println(valoare);
//        }

        var valori=args[0].split(",");
        var v=new int[valori.length];
        for(var i=0; i<valori.length;i++)
        {
            v[i]=Integer.parseInt(valori[i]);
        }
        afisare(v,"initial");
        incrementare(v);
        afisare(v,"final");

       // v=inserare(13,v);
        afisare(v, "dupa inserare");

        v=inserare(v,7);
        afisare(v,"afisare dupa inserare");

    }

}

//tema o functie de inserare

