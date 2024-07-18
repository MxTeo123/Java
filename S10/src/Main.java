import javax.management.DescriptorRead;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

class Tranzactie
{
    private final int codPordus;
    private final String denumire;
    private final double pret;
    private final int cantitate;

    Tranzactie(int codPordus, String denumire, double pret, int cantitate) {
        this.codPordus = codPordus;
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
    }

    public int getCodPordus() {
        return codPordus;
    }

    public String getDenumire() {
        return denumire;
    }

    public double getPret() {
        return pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    @Override
    public String toString() {
        return "Tranzactie{" +
                "codPordus=" + codPordus +
                ", denumire='" + denumire + '\'' +
                ", pret=" + pret +
                ", cantitate=" + cantitate +
                '}';
    }
}


public class Main {
    static final String url="jdbc:sqlite:gestiune.db";

    static void genereaza() throws SQLException {
        String[] denumiri=new String[]{
                "Stafide 200g",
                "Seminte de pin 300g",
                "Bulion Topoloveana 190g",
                "Paine neagra Frontera",
                "Ceai verde Lipton"
        };
        try(var conexiune= DriverManager.getConnection(url);
        var comanda=conexiune.createStatement()){
        comanda.executeUpdate("CREATE TABLE IF NOT EXISTS Tranzactii ("+"CodProdus INTEGER, "+"DenumireProdus String(100), "+
                "Pret REAL, "+"Cantitate INTEGER)");
            System.out.println("Am facut tabelul");
            int nr=comanda.executeUpdate("DELETE FROM Tranzactii");
            System.out.println("Am sters "+nr+" tranzactii");
            }
        List<Tranzactie> tranzactii=new ArrayList<>();
        var random=new Random(42);
        for(int i=0; i<23;i++)
        {
            var tranzactie=new Tranzactie(100+i,denumiri[random.nextInt(denumiri.length)],
                    1+random.nextDouble()*10,1+random.nextInt(20));
            tranzactii.add(tranzactie);
        }
        try(var conexiune=DriverManager.getConnection(url);
        var comanda=conexiune.prepareStatement("INSERT INTO Tranzactii VALUES(?,?,?,?)")
        ){
            for(var tranzactie:tranzactii)
            {
                comanda.setInt(1,tranzactie.getCodPordus());
                comanda.setString(2,tranzactie.getDenumire());
                comanda.setDouble(3,tranzactie.getPret());
                comanda.setInt(4,tranzactie.getCantitate());
                comanda.executeUpdate();
            }
        }
        tranzactii.stream().forEach(System.out::println);

    }

    static List<Tranzactie> citireTrnazactii(){
        var tranzactii=new ArrayList<Tranzactie>();
        try(var conexiune= DriverManager.getConnection(url); var comanda=conexiune.createStatement();
        var cursor=comanda.executeQuery("SELECT * FROM Tranzactii")){
            while(cursor.next())
            {
                var tranzactie=new Tranzactie(cursor.getInt(1),cursor.getString(2),cursor.getDouble(3),cursor.getInt("Cantitate"));
                tranzactii.add(tranzactie);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return tranzactii;
    }

    static void afisare()
    {
        var tranzactii=citireTrnazactii();
        tranzactii.stream().forEach(System.out::println);
        var date=tranzactii.stream().collect(Collectors.groupingBy(Tranzactie::getDenumire));
        for(var produs: date.keySet()){
            var tProdus=date.get(produs);
            var stoc=tProdus.stream().mapToInt(Tranzactie::getCantitate).sum();
            var temp=tProdus.stream().mapToDouble(t->t.getCantitate()*t.getPret()).sum();
            System.out.printf("%-30s %3d @ %5.2f RON%n",produs,stoc,temp/stoc);
        }
    }


    public static void main(String[] args) throws SQLException{
    genereaza();
    afisare();

    }
}