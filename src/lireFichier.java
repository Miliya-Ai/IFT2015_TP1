import java.io.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;

public class lireFichier{
    public static void main(String args[]) {
        try{
            FileInputStream fin = new FileInputStream("src/1.txt");
            DataInputStream reader = new DataInputStream(fin);
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(reader));
            String strTmp;
            while((strTmp = buffReader.readLine())!=null){
                System.out.println(strTmp);
            }
            buffReader.close();
        } catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
}

