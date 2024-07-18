import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

enum TipTranzactie{
    INTRARE(1),
    IESIRE(-1);
    int semn;

    TipTranzactie(int semn) {
        this.semn = semn;
    }

    @Override
    public String toString() {
        return "TipTranzactie{" +
                "semn=" + semn +
                '}';
    }
}

class Tranzactie{
    private final TipTranzactie tip;
    private final int cantitate;

    Tranzactie(TipTranzactie tip, int cantitate) {
        this.tip = tip;
        this.cantitate = cantitate;
    }
}
final class Produs{
    private final int cod;
    private final String denumire;
    private final List<Tranzactie> tranzactii;

    Produs(int cod, String denumire, List<Tranzactie> tranzactii) {
        this.cod = cod;
        this.denumire = denumire;
        this.tranzactii = tranzactii;
    }


    public int getCod() {
        return cod;
    }

    public String getDenumire() {
        return denumire;
    }

    public List<Tranzactie> getTranzactii() {
        return tranzactii;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "cod=" + cod +
                ", denumire='" + denumire + '\'' +
                ", tranzactii=" + tranzactii +
                '}';
    }
}


public class Main {
    static List<Produs> citireXml(String caleFisierXml) throws Exception{
        var factory= DocumentBuilderFactory.newInstance();
        var builder=factory.newDocumentBuilder();

        ArrayList<Produs> produse=new ArrayList<>();
        try(var fisier=new FileInputStream(caleFisierXml)){
            var document=builder.parse(fisier);
            var nodProduse=document.getDocumentElement();
            var noduriProduse=nodProduse.getElementsByTagName("produs");

            for(var i=0; i<noduriProduse.getLength();i++){
                var nodProdus=(Element)noduriProduse.item(i);
                var cod=Integer.parseInt(nodProdus.getElementsByTagName("cod").item(0).getTextContent());
                var denumire=nodProduse.getElementsByTagName("denumire").item(0).getTextContent();
                var tranzactii=new ArrayList<Tranzactie>();
                var nodTranzactii=(Element)nodProdus.getElementsByTagName("tranzactii").item(0);
                var noduriTranzactie=nodTranzactii.getElementsByTagName("tranzactie");
                for(var j=0; j<noduriTranzactie.getLength();j++){
                    var nodTranzactie=(Element)noduriTranzactie.item(j);
                    System.out.println(nodTranzactie.getAttribute("tip"));
                    tranzactii.add(new Tranzactie(
                            TipTranzactie.valueOf(nodTranzactie.getAttribute("tip").toUpperCase()),
                            Integer.parseInt(nodTranzactie.getAttribute("cantitate"))
                    ));
                }
                produse.add(new Produs(cod,denumire,tranzactii));
            }
        }

        return produse;
    }

    static void salvareJson(String caleFisier, List<Produs> produse) throws Exception{
        var jsonProduse=new JSONArray();
        for(var produs: produse){
            var jsonProdus=new JSONObject();
            jsonProduse.put("cod",produs.getCod());
            jsonProduse.put("denumire",produs.getDenumire());
            jsonProduse.put(jsonProdus);
            var jsonTranzactii=new JSONArray();
            jsonProdus.put("tranzactii",jsonTranzactii);
            for(var tranzactie:produs.getTranzactii()){
                var jsonTranzactie=new JSONObject();
                jsonTranzactie.put("tip",tranzactie.getTip());
                jsonTranzactie.put("cantitate",tranzactie.getCantitate());
                jsonTranzactii.put(jsonTranzactie);
            }
        }
        try(var fisier=new FileWriter(caleFisier)){
            jsonProduse.write(fisier,3,0);
        }
    }

    public static void main(String[] args) throws Exception {
    var produse=citireXml("date\\produse.xml");
//    produse.stream().forEach(System.out::println);
    salvareJson("date\\produse.json",produse);
    }

}