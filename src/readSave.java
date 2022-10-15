import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class readSave {
    private ArrayList<String> result = new ArrayList<>();


    // https://www.baeldung.com/java-file-to-arraylist?fbclid=IwAR2TNy0zwX8q_RlbNgtOw5UN4fuO8aIRaaQ1_-FrNJD1pmNw7TRRpEkYjHE
    public void read(){
        try (FileReader fichier = new FileReader("src/sample.txt")) {
            StringBuffer sb = new StringBuffer();
            String bombe;
            while (fichier.ready()) {
                char c = (char) fichier.read();
                if (c == ' ' || c == '\n') {
                    bombe = sb.toString().trim();
                    result.add(bombe);
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
            if (sb.length() > 0) {
                result.add(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        for(String nbr:result) {
            System.out.println(nbr);
        }
*/
        //System.out.println((toInt(result.get(1))));

    }

    public void arrayList2queue(){
        //matrice longeur * largeur
        int longeur = toInt(result.get(0));
        int largeur = toInt(result.get(1));
        result.remove(0); result.remove(0);
        int nbrElementsMatrice = longeur * largeur;
        int indexPremierElement = 0;
        int indexDernierElement = indexPremierElement + nbrElementsMatrice;
        ArrayQueue queue = new ArrayQueue<>(nbrElementsMatrice);

        for (int i = indexPremierElement; i < indexDernierElement; i++){
            queue.enqueue(result.get(0));
            result.remove(0);
        }

        /*
        for(String nbr:result) {

            System.out.println(nbr);
        }

         */
        System.out.println(queue.showAllElements());

    }

    public int toInt(String string){
        int i = 0;
        try {
            i = Integer.parseInt(string);
            return i;

        } catch(NumberFormatException e) {
            System.out.println("Not a number");
        }
        return i;
    }
}
