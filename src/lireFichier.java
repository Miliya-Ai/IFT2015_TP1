import java.io.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public  class lireFichier {
    private final String filename;

    public lireFichier(String fileName) {
        this.filename = fileName;
    }

    public static List<String> LireFichier(String nom) {
        ArrayList<String> result = new ArrayList<>();

        try {
            FileInputStream fin = new FileInputStream(nom);
            DataInputStream reader = new DataInputStream(fin);
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(reader));
            String strTmp;
            StringBuffer sb = new StringBuffer();
            while ((strTmp = buffReader.readLine()) != null) {
                for (int i = 0; i < strTmp.length(); i++) {
                    if (Character.isWhitespace(strTmp.charAt(i))) {
                        continue;
                    } else {
                        result.add(String.valueOf(strTmp.charAt(i)));
                        //sb.append(strTmp.charAt(i));
                    }
                }
                //System.out.println(sb);
                //System.out.println(strTmp);
            }
            //System.out.println(strTmp);
            buffReader.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return result;
    }
}
