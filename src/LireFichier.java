import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LireFichier {
    private final ArrayList<String> grillesBombes = new ArrayList<>();

    public LireFichier(String path) {
        read(path);
    }

    public ArrayList<String> getGrillesBombes(){
        return this.grillesBombes;
    }

    // https://www.baeldung.com/java-file-to-arraylist?fbclid=IwAR2TNy0zwX8q_RlbNgtOw5UN4fuO8aIRaaQ1_-FrNJD1pmNw7TRRpEkYjHE

    /**
     * Lire le fichier et conserver toutes les grilles dans un arraylist
     * @param path source du fichier d'entree
     */
    private void read(String path) {

        try  {
            FileReader fichier = new FileReader(path);
            StringBuilder element = new StringBuilder();
            String bombe;

            while (fichier.ready()) {
                char c = (char) fichier.read();
                if (c == ' ' || c == '\n') {
                    bombe = element.toString().trim(); //enlève le '\n' à la fin 
                    grillesBombes.add(bombe);
                    element = new StringBuilder();
                } else {
                    element.append(c);
                }
            }

            if (element.length() > 0) {
                grillesBombes.add(element.toString());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Veuillez entrer le bon path.");
            System.exit(1);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
