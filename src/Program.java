import java.util.ArrayList;
import java.util.Objects;

public class Program {
    private int iteration = 0;
    private boolean contientBombeInactive;

    private String bombeActive = "2";
    private String bombeInactive = "1";
    private String pasBombe = "0";
    private String bombeActiveExplose = "_2_"; //underline 2 '\u0332' + "2"
    private String bombeDejaExplose = "*2*";

    private int longueurMatrice = 0;
    private int largeurMatrice = 0;
    private int nbrElementsMatrice = 0;

    ArrayList<Integer> bombesAdjacentes = new ArrayList();
    ArrayList matriceCopie = new ArrayList();

    private LireFichier fichier;

    String look;

    //constructeur
    public Program(String path) {

       this.fichier = new LireFichier(path);
       start();


    }

    // ------------------------------------------------------------------------------------------------------- //
    public void start(){
        while (!isDone()) { //on itere sur la prochaine grille dans le fichier
            algoBegin(arrayList2queue());
            if (contientBombeInactive){
                System.out.println(-1);
            } else {
                System.out.println(iteration);
            }
        }

    }

    public void algoBegin(ArrayQueue matrice){
        while (!isDone(matrice)) {  //continuer a chercher tous les bombes
            initialiserBombes(matrice);
            activeBombeInactive(matrice);
            iteration++;
        }

    }
    //pour le fichier complet
    public Boolean isDone(){
        if (fichier.getGrillesBombes().isEmpty()) {
            return true;
        }
        iteration = 0;
        return false;
    }
    //pour la meme grille
    public Boolean isDone(ArrayQueue matrice){
        Boolean isDone = true;
        for (int i = 0; i < nbrElementsMatrice; i ++){
            look = (String) matrice.dequeue();
            if (look.equals(bombeActive)||look.equals(bombeActiveExplose)){
                matrice.enqueue(look);
                isDone = false; //si on retourne tout de suite ici, le f du ArrayQueue != 0
            } else {
                matrice.enqueue(look);
            }
        }
        contientBombeInactive(matrice);
        bombesAdjacentes.clear();

        return isDone;
    }
    public boolean contientBombeInactive(ArrayQueue matrice) {
        contientBombeInactive = false;
        for (int i = 0; i < matrice.size(); i++) {
            look = (String) matrice.dequeue();
            if (look.equals(bombeInactive)) {
                matrice.enqueue(look);
                contientBombeInactive = true;
            } else{
                matrice.enqueue(look);
            }
        }
        return contientBombeInactive;
    }
    //---------------------- Boucle --------------------------- //

    /***
     *                      "2" ---> _2_
     *
     * @param matrice
     *
     */
    public void initialiserBombes(ArrayQueue matrice) {

        for (int i = 0; i < matrice.size(); i++) {
            look = (String) matrice.dequeue();
            if (look.equals("2")) {
                look = bombeActiveExplose;
                //matriceCopie.set(i, bombeActiveExplose);
                matrice.enqueue(look);
            } else {
                matrice.enqueue(look);
            }
        }

        out("Dans initialiserBombes", matrice);

    }

    /**
     * Lorsqu'on rencontre "_2_", on cherche les bombes adjacentes et incremente 1 à 2
     * et finalement, "_2_" ---> "*2*"
     *
     * @param matrice
     * @see #bombesAdjacentes(int)
     * @see #incrementerBombesAdjacentes(ArrayQueue)
     */
    public void activeBombeInactive(ArrayQueue matrice){

        for (int i = 0; i < matrice.size(); i++) {
            look = (String) matrice.dequeue();
            if (look.equals(bombeActiveExplose)) {
                bombesAdjacentes(i);    //verifie les bombes adjacentes a chaque fois qu'on voit "_2_"
                look = bombeDejaExplose;    //"_2_" ---> "*2*"
                //matriceCopie.set(i, bombeDejaExplose);
                matrice.enqueue(look);
            } else {
                matrice.enqueue(look);
            }
        }
        incrementerBombesAdjacentes(matrice); //incrementer 1 à 2
        out("Dans activeBombeInactive", matrice);
    }

    // TODO: 2022-10-22  Trouver meilleur facon de incrementer seulement les positions adjacentes = 1
    // Logique du code ici ne fonctionne pas: arrive pas a trouver les bonnes positions
    public void incrementerBombesAdjacentes(ArrayQueue matrice) {
            int j = 0;
            int index2increment;
            for (int i = 0; i < nbrElementsMatrice; i++){
                index2increment = bombesAdjacentes.get(j);
                look = (String) matrice.dequeue();
                if (index2increment == i && look.equals(bombeInactive)){
                    look = bombeActive;
                    matrice.enqueue(look);
                    j++;
                } else{
                    matrice.enqueue(look);
                }
            }


    }

    public void bombesAdjacentes(int indexBombeActiveExplose){
        // bombesAdjacentes = [haut, gauche, droite, bas]
        int haut = indexBombeActiveExplose - largeurMatrice;
        int gauche = indexBombeActiveExplose - 1;
        int droite = indexBombeActiveExplose + 1;
        int bas = indexBombeActiveExplose + largeurMatrice;

        //L'ordre est important
        addIndexBombeIncrementer(haut);
        addIndexBombeIncrementer(gauche);
        addIndexBombeIncrementer(droite);
        addIndexBombeIncrementer(bas);

    }

    public void addIndexBombeIncrementer(int position ){
        if (position < 0 || position >= nbrElementsMatrice ){
            ;   //do nothing
        } else if (bombesAdjacentes.contains(position)){
            ;   //do nothing
        } else{
            bombesAdjacentes.add(position);
        }

    }

    // ------------------------ creation de la matrice queue et l'imprimer sur la console -------------------------//

    public ArrayQueue arrayList2queue(){
        findMatriceSize();
        nbrElementsMatrice = longueurMatrice * largeurMatrice;

        int indexPremierElement = 0;
        int indexDernierElement = nbrElementsMatrice;
        ArrayQueue matrice = new ArrayQueue(nbrElementsMatrice);
        //LinkedQueue matrice = new LinkedQueue();


        for (int i = indexPremierElement; i < indexDernierElement; i++) {
            matrice.enqueue(fichier.getGrillesBombes().get(0));
            //matriceCopie.add(fichier.getGrillesBombes().get(0));
            fichier.getGrillesBombes().remove(0);
        }
        return matrice;
    /*
        for(String nbr:result) {
            System.out.println(nbr);
    }

     */
        //out("Dans arrayList2queue", matrice);

    }

    public void findMatriceSize(){
        longueurMatrice = toInt(fichier.getGrillesBombes().get(0));
        largeurMatrice = toInt(fichier.getGrillesBombes().get(1));

        //enleve le string de longeur et largeur dans l'arrayList
        fichier.getGrillesBombes().remove(0); fichier.getGrillesBombes().remove(0);
    }

    public int toInt(String string){
        int i = 0;
        try {
            i = Integer.parseInt(string);
            return i;

        } catch(NumberFormatException e) {
            System.out.println("Not a number");
        }
        return -1;
    }

    public void out(String message, ArrayQueue matrice){
        System.out.print(message + " : ");
        System.out.println(matrice.showAllElements());
    }

}
