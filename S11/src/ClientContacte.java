import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientContacte {
    static final int PORT = 7654;
    public static void main(String[] args) throws  Exception {
        try(var socket = new Socket("localhost", PORT);){
            var out = new ObjectOutputStream(socket.getOutputStream());
            var in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Am deschis conexiunea");
            Comanda comanda = new Comanda("test", null);
            out.writeObject(comanda);
            System.out.println("Am trimis comanda");

            Comanda raspuns = (Comanda) in.readObject();
            System.out.println("Am primit ca raspuns: " + raspuns);
        }
    }
}








