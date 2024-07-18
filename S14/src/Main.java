import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Produs{
    private final int cod;
    private final String denumire;
    private final double pret;
    private final List<Tranzactie> tranzactii;

    Produs(int cod, String denumire, double pret) {
        this.cod = cod;
        this.denumire = denumire;
        this.pret = pret;
        this.tranzactii=new ArrayList<>();
    }

    public int getCod() {
        return cod;
    }

    public String getDenumire() {
        return denumire;
    }

    public double getPret() {
        return pret;
    }

    public List<Tranzactie> getTranzactii() {
        return tranzactii;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "cod=" + cod +
                ", denumire='" + denumire + '\'' +
                ", pret=" + pret +
                ", tranzactii=" + tranzactii +
                '}';
    }
}

class Tranzactie{
    private final int cantitate;
    private final String tip;

    Tranzactie(int cantitate, String tip) {
        this.cantitate = cantitate;
        this.tip = tip;
    }

    public int getCantitate() {
        return cantitate;
    }

    public String getTip() {
        return tip;
    }



    @Override
    public String toString() {
        return "Tranzactie{" +
                "cantitate=" + cantitate +
                ", tip='" + tip + '\'' +
                '}';
    }
}


public class Main {
    public static void main(String[] args) throws Exception {
    Map<Integer,Produs> produse=new HashMap<>();

    try(var fisier=new BufferedReader(new FileReader("src/date/produse.txt"))){
        fisier.lines().forEach(linie->{
            var cod=Integer.parseInt(linie.split(",")[0]);
            var denumire=linie.split(",")[1];
            var pret=Double.parseDouble(linie.split(",")[2]);
            produse.put(cod, new Produs(cod,denumire,pret));
                }

        );
    }
        System.out.println(produse.size());


        System.out.println("cerinta 2: ");
        produse.values().stream().sorted((p1,p2)->p1.getDenumire().compareTo(p2.getDenumire())).forEach(System.out::println);


        try(var fisier =new FileReader("src/date/tranzactii.json")){
            var jsonTranzactii=new JSONArray(new JSONTokener(fisier));
            for(int i=0; i<jsonTranzactii.length();i++){
                var cod =jsonTranzactii.getJSONObject(i).getInt("codProdus");
                var cantitate=jsonTranzactii.getJSONObject(i).getInt("cantitate");
                var tip=jsonTranzactii.getJSONObject(i).getString("tip");
                produse.get(cod).getTranzactii().add(new Tranzactie(cantitate,tip));
            }
        }
        System.out.println(produse);




        try(var fisier=new PrintWriter("mirel.txt")){
            produse.values().stream()
                    .sorted((p1,p2)->Integer.compare(p1.getTranzactii().size(),p2.getTranzactii().size()))
                    .forEach(p-> System.out.println(p.getDenumire()+" "+p.getTranzactii().size()));
        }

       new Thread(()->{
           //impl server tcp
       }).start();

        Thread.sleep(1000);   //asteapta o sec sa porneassca sv
        //implementare client tcp

    }
}