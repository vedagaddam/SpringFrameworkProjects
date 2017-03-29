import java.io.*;
import java.net.Socket;

/**
 * Created by Veda Gaddam on 5/1/2016.
 */
import java.net.Socket;
import java.io.FileInputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import static java.lang.System.setProperty;
public class ServerThread extends Thread
{
    Socket socket;
    String secret="secret";
    long startencryptime,endencryptime,startdecryptime, enddecryptime;
    ServerThread(Socket socket)
    {
        this.socket=socket;
    }

    public void run() {
        try {
            PrintWriter printwriter=new PrintWriter(socket.getOutputStream(),true);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader commandPromptBufferedReader=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("connecting to the server");
            System.out.println(""+bufferedReader.readLine()+" is new conncetion request");
            setProperty("javax.net.ssl.keyStore","Bobprivatekeystore1024");
            setProperty("javax.net.ssl.keyStorePassword","bob123");
            printwriter.println("certificate is private.cer");
            // Private Keystore
            FileInputStream privateKeyFile = new FileInputStream(
                    "Bobprivatekeystore1024");
            // Read the private keystore and get Private Key
            KeyStore privateKeyStore = KeyStore.getInstance(KeyStore
                    .getDefaultType());
            System.out.println("Type of private key: "
                    + privateKeyStore.getDefaultType());
            privateKeyStore.load(privateKeyFile, "bob123".toCharArray());
            PrivateKey privateKey = (PrivateKey) privateKeyStore.getKey(
                    "Bobprivate", "bob123".toCharArray());
            String  encodedEncryptedSecondString;
           // while(true) {
              //  String msgfromclient=bufferedReader.readLine();
                //System.out.println("Decrypted message from Client");
                encodedEncryptedSecondString=bufferedReader.readLine();
                byte[] decodedEncryptedSecondString = Base64.getDecoder().decode(
                        encodedEncryptedSecondString.getBytes());
                Cipher privateDecryptCipher = Cipher.getInstance("RSA");
                privateDecryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
                byte[] decryptedSecondStringByte = privateDecryptCipher
                        .doFinal(decodedEncryptedSecondString);
               // System.out.println("Decrypted String "+ new String(decryptedSecondStringByte));
                secret=new String(decryptedSecondStringByte);
            //printwriter.println("");
            //System.out.println("the stored secret is"+secret);
                privateKeyFile.close();
                System.out.println("Key exchanged");
                printwriter.println("Key exchanged");
                while(true) {
                    String encrymsgcli=bufferedReader.readLine();
                    System.out.println("Encrypted data recieved from client "+encrymsgcli);
                    startdecryptime=System.nanoTime( );
                    String decrymsgcli=decrypt(encrymsgcli,secret);
                    enddecryptime=System. nanoTime( );
                    System.out.println("Decrypted data from client "+decrymsgcli);
                    System.out.println("Time took to decrypt is "+(enddecryptime-startdecryptime)+"nanoseconds");
                    System.out.println("Enter data to be sent to the client");
                    startencryptime=System.nanoTime();
                    printwriter.println(encrypt(commandPromptBufferedReader.readLine(),secret));
                    endencryptime=System.nanoTime( );
                    System.out.println("Time took to encrypt is "+(endencryptime-startencryptime)+"nanoseconds");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String encrypt(String message, String secret) {
        StringBuffer output = new StringBuffer();
        if(message == null){
            return "";
        }
        for(char c: message.toCharArray()){
            output.append(c + secret);
        }

        return output.toString();
    }
    private String decrypt(String inputLine, String secret) {
        return inputLine.replaceAll(secret, "");
    }




}
