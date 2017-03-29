import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
public class Server {

    public static void main(String[] args) throws IOException
    {
        System.setProperty("javax.net.ssl.keyStore","Bobprivatekeystore1024");
        System.setProperty("javax.net.ssl.keyStorePassword","bob123");
        ServerSocket serverSocket= ((SSLServerSocketFactory)SSLServerSocketFactory.getDefault()).createServerSocket(4444);
        System.out.println("Server up and and ready for connection...");
        while(true)
            new ServerThread(serverSocket.accept()).start();

    }



}
