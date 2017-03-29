
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Base64;

import javax.crypto.Cipher;
/**
 * Created by Veda Gaddam on 5/1/2016.
 */
public class Client {

    public static void main(String[] args) throws Exception
    {
        System.setProperty("javax.net.ssl.trustStore","Bobpublickeystore1024");
        Socket socket= ((SSLSocketFactory)SSLSocketFactory.getDefault()).createSocket("localhost",4444);
        BufferedReader socketBufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printWriter=new PrintWriter(socket.getOutputStream(),true);
        BufferedReader commandPromptBufferedReader=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the connection message to the server: ");
        printWriter.println(commandPromptBufferedReader.readLine());
        String message=null, message1=null;
        String secret="secret";
        long startencryptime,endencryptime,startdecryptime, enddecryptime;
        // Public Keystore
        FileInputStream publicKeyFile = new FileInputStream(
                "Bobpublickeystore1024");
        // Read the public keystore and get public key
        KeyStore publicKeyStore = KeyStore.getInstance(KeyStore
                .getDefaultType());
        System.out.println("Type of public key: "
                + publicKeyStore.getDefaultType());
        publicKeyStore.load(publicKeyFile, "bob123".toCharArray());
        Certificate publicCertificate = publicKeyStore
                .getCertificate("Bobpublic");
        PublicKey publicKey = publicCertificate.getPublicKey();
        //System.out.println("Enter the connection message to server");
        //message=commandPromptBufferedReader.readLine();
        //if(message.equals("quit"))
        /*{
            printWriter.println(message);
            socket.close();
        }
        printWriter.println(message);*/
        System.out.print("Reply Connection message from Server: ");
        String msg = socketBufferedReader.readLine();
        System.out.println(msg);
        Boolean auth=false;
        if(msg.contains("certificate"))
        {
            System.out.println("certificate received");
            System.out.println("Connection Authenticated");
            auth=true;
        }
        if(auth==true)
        {
            System.out.println("Enter the secret key");
            secret=commandPromptBufferedReader.readLine();
            String secondString = secret;
            Cipher publicEncryptCipher = Cipher.getInstance("RSA");
            publicEncryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedSecondString = publicEncryptCipher
                    .doFinal(secondString.getBytes());
            String encodedEncryptedSecondString = Base64.getEncoder()
                    .encodeToString(encryptedSecondString);
            System.out.println("Encoded encrypted String for: "
                    + encodedEncryptedSecondString);
            String connexit="conncetion exited";
            if(secret.equals("quit"))
            {
                printWriter.println(connexit);
                System.out.println("conncetion exited");
                socket.close();
            }
            printWriter.println(encodedEncryptedSecondString);
           // System.out.println(secret);
            publicKeyFile.close();
            System.out.println(socketBufferedReader.readLine());
            while(true) {
                System.out.println("Enter data to be sent to the server");
                startencryptime=System.nanoTime( );
                printWriter.println(encrypt(commandPromptBufferedReader.readLine(),secret));
                endencryptime=System. nanoTime();
                System.out.println("Time took to encrypt is "+(endencryptime-startencryptime)+"nanoseconds");
                System.out.println("Waiting for the server data");
                String encrymsgser=socketBufferedReader.readLine();
                System.out.println("Encrypted data recieved from the server "+encrymsgser);
                startdecryptime=System.nanoTime( );
                String decrymsgser=decrypt(encrymsgser,secret);
                enddecryptime=System. nanoTime( );
                System.out.println("Decrypted data recieved from the server "+decrymsgser);
                System.out.println("Time took to decrypt is "+(enddecryptime-startdecryptime)+"nanoseconds");
            }
        }
        else
        {
            System.out.println("Connection failed");
            socket.close();
        }
        }
    private static String encrypt(String message, String secret) {
        StringBuffer output = new StringBuffer();
        if(message == null){
            return "";
        }
        for(char c: message.toCharArray()){
            output.append(c + secret);
        }

        return output.toString();
    }
    private static String decrypt(String inputLine, String secret) {
        return inputLine.replaceAll(secret, "");
    }
    }


