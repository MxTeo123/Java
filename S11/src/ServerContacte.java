import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerContacte {
    static final int PORT = 7654;

    public static List<Contact> contacte =new ArrayList(Arrays.asList(new Contact[]{
        new Contact(1,"Ion","93728"),
        new Contact(2,"Maria","97389"),
    }));

    public static void main(String[] args) throws Exception {
        try(var serverSocket = new ServerSocket(PORT);) {
            while (true){
                System.out.println("Asteptam o conexiune");
                var socket = serverSocket.accept();
                procesareConexiune(socket);
            }
        }
    }

    static void procesareConexiune(Socket paramSocket){
        try(var socket = paramSocket;
            var in = new ObjectInputStream(socket.getInputStream());
            var out = new ObjectOutputStream(socket.getOutputStream());){
            System.out.println("S a deschis conexiunea");
    while(true) {
        var comanda = (Comanda) in.readObject();
        System.out.println("Am primit comanda " + comanda);

        out.writeObject(new Comanda("raspuns", "test"));
        System.out.println("Am trimis raspunsul");
    }
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }
}



















