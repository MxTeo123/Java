import java.util.ArrayList;

class Cont extends Thread{
    private int sold=0;
    Object mirel=new Object();
    public void depune(int suma){
        synchronized (mirel)
        {
            sold=sold+suma;
        }

    }
    public int getSold() {
        return sold;
    }
}

class FirExecutie extends Thread{
    int i;
    static volatile long idFirCastigator=0;

    @Override
    public void run(){
        for(i=1;i<=100;i++){
            try {
                Thread.sleep((long) (1+Math.random()*100));
            } catch (InterruptedException e) {}
            if(idFirCastigator > 0){
                System.out.printf("T#%-3d - Am pierdut (am ajuns la %3d)%n", getId(),i);
                return;
            }
            System.out.printf("T#%-3d - %3d%n", getId(),i);
        }
        idFirCastigator=getId();
    }
}
public class Main {
    public static void main(String[] args) throws Exception{
        var cont = new Cont();
        var fire = new ArrayList<Thread>();
        for(int i = 0;i<10;i++) {
            var fir = new Thread(()->{
                for(int j=0;j<1000;j++){
                    cont.depune(1);
                }
            });
            fir.start();
            fire.add(fir);

        }
        for(var fir:fire){
            fir.join();
        }
        System.out.println("Soldul final este: "+ cont.getSold());
    }
}