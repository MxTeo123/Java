import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

final class Factura{
    String denumire;
    LocalDate dataEmitere;
    List<Linie> linii;
    static final class  Linie{
        private final String produs;
        private final double pret;
        private final int cantitate;

        public Linie(String produs, double pret, int cantitate) {
            this.produs = produs;
            this.pret = pret;
            this.cantitate = cantitate;
        }

        public String getProdus() {
            return produs;
        }

        public double getPret() {
            return pret;
        }

        public int getCantitate() {
            return cantitate;
        }

        @Override
        public String toString() {
            return "Linie{" +
                    "produs='" + produs + '\'' +
                    ", pret=" + pret +
                    ", cantitate=" + cantitate +
                    '}';
        }
    }

    public Factura(String denumire, LocalDate dataEmitere) {
        this.denumire = denumire;
        this.dataEmitere = dataEmitere;
        this.linii=new ArrayList<>();
    }

    public String getDenumire() {
        return denumire;
    }

    public LocalDate getDataEmitere() {
        return dataEmitere;
    }

    public int getNrLinii()
    {
        return linii.size();
    }
    public Linie getLinie(int index)
    {
        return linii.get(index);
    }
    public void addLinie(Linie linie)
    {
        linii.add(linie);
    }

    @Override
    public String toString() {
        var rezultat=new StringBuilder();
        rezultat.append(String.format("%s din %s%n", denumire,dataEmitere));
        for(var linie:linii)
        {
            rezultat.append("    "+linie+"\n");
        }
        return rezultat.toString();
    }
}
public class
Main {
    static List<Factura> generareListaFacturi(int nrfacturi, LocalDate dataMinima)
    {
        String[] denumiriClienti = new String[]{
                "ALCOR CONSTRUCT SRL",
                "SC DOMINO COSTI SRL",
                "SC TRANSCRIPT SRL",
                "SIBLANY SRL",
                "INTERFLOOR SYSTEM SRL",
                "MERCURY  IMPEX  2000  SRL",
                "ALEXANDER SRL",
                "METAL INOX IMPORT EXPOSRT SRL",
                "EURIAL BROKER DE ASIGURARE SRL"
        };

        String[] denumiriProduse = new String[]{
                "Stafide 200g",
                "Seminte de pin 300g",
                "Bulion Topoloveana 190g",
                "Paine neagra Frontera",
                "Ceai verde Lipton"

        };

        double[] preturiProduse = new double[]{
                5.20,
                12.99,
                6.29,
                4.08,
                8.99
        };
        var facturi =new ArrayList<Factura>();
        var random=new Random(42);
        for(var indexFactura=0; indexFactura<nrfacturi;indexFactura++)
        {
            var factura =new Factura(
                    denumiriClienti[random.nextInt(denumiriClienti.length)],dataMinima.plusDays(random.nextInt(100)));
            var nrLinii=1+random.nextInt(10);
            for(var indexLinie=0; indexLinie<nrLinii; indexLinie++)
            {
                int indexProdus=random.nextInt(denumiriProduse.length);

                factura.addLinie
                        (new Factura.Linie(
                        denumiriProduse[indexProdus],
                                preturiProduse[indexProdus],
                                1+random.nextInt(20)));
            }
            facturi.add(factura);
        }
        return facturi;

    }
    static void salvareFacturi(String caleFisier, List<Factura>facturi)
    {
       try (var fisier=new DataOutputStream(new FileOutputStream(caleFisier)))
       {
           for(var factura : facturi) {
               fisier.writeUTF(factura.getDenumire());
               fisier.writeInt(factura.getDataEmitere().getYear());
               fisier.writeInt(factura.getDataEmitere().getMonthValue());
               fisier.writeInt(factura.getDataEmitere().getDayOfMonth());
               fisier.writeInt(factura.getNrLinii());
               for(int i=0; i<factura.getNrLinii(); i++)
               {
                   var linie=factura.getLinie(i);
                   fisier.writeUTF(linie.getProdus());
                   fisier.writeDouble(linie.getPret());
                   fisier.writeInt(linie.getCantitate());
               }
           }

       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }
    static List<Factura> incarcareFacturi(String caleFisier)
    {
        var facturi=new ArrayList<Factura>();
        try(var fisier= new DataInputStream(new FileInputStream(caleFisier)))
        {
            while(true)
            {
                var factura=new Factura(fisier.readUTF(),LocalDate.of(fisier.readInt(),fisier.readInt(),fisier.readInt()));
                facturi.add(factura);
            }
        }
        catch (EOFException exception)
        {
            //do nothing
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return facturi;
    }
    public static void main(String[] args) {

        var facturi=generareListaFacturi(7,LocalDate.of(2024,1,1));
        salvareFacturi("facturi.dat",facturi);
        for(var factura: facturi)
        {
            System.out.println(factura);
        }









//        var factura=new Factura("Test", LocalDate.now());
//        factura.addLinie(new Factura.Linie("Mare",4.56,7));
//        factura.addLinie(new Factura.Linie("Pere",9.56,3));
//        System.out.println(factura);
    }
}

//folosim string builder cand nu stim nr de obiecte