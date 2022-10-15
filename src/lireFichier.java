import java.io.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.ArrayList;

public class lireFichier{
    public static void main(String[] args) {
        new readSave();
            //System.out.println(Integer.parseInt(nbr));
        }//


        /*try{
            FileInputStream fin = new FileInputStream("src/sample.txt");
            DataInputStream reader = new DataInputStream(fin);
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(reader));
            String strTmp;
            while((strTmp = buffReader.readLine())!=null){
                System.out.println(strTmp);
            }
            buffReader.close();
        } catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }*/

    }
/*
    public static ArrayList<> readLine(){
        /*try (FileReader f = new FileReader("src.sample.txt")) {
            StringBuffer sb = new StringBuffer();
            while (f.ready()) {
                char c = (char) f.read();
                if (c == '\n') {
                    result.add(sb.toString());
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
            if (sb.length() > 0) {
                result.add(sb.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }*/



