import java.io.IOException;

public class BombExploder {

    public static void main ( String[] args) throws IOException {

        try {
            String path = args[0].toString().trim();;
            Program start = new Program(path);
        } catch (Exception e){
            System.out.println("Veuillez mettre le path du fichier d'entrée");
            //System.out.println("Donnée du fichier original:");
            //Program start = new Program("src/sample.txt");
        }

    }


}
