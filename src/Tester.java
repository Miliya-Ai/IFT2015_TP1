import java.util.ArrayList;

public class Tester {
    private int iteration = 0;
    private boolean contientBombeInactive = false;

    private String bombeActive = "2";
    private String bombeInactive = "1";
    private String pasBombe = "0";
    private String bombeActiveExplose = "_2_";
    private String bombeDejaExplose = "*2*";

    private int longueurMatrice = 0;
    private int largeurMatrice = 0;
    private int nbrElementsMatrice = 0;

    ArrayQueue<Integer> bombesAdjacentes = new ArrayQueue();
    private LireFichier fichier;

    String look;

    //constructeur
    public Tester(String path) {

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

    public void algoBegin(ArrayList<String> matrice){
        initialiserBombes(matrice);
        while (!isDone(matrice)) {  //continuer a chercher tous les bombes
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
    public Boolean isDone(ArrayList<String> matrice){
        Boolean isDone = true;
        for (int i = 0; i < nbrElementsMatrice; i ++){
            look = matrice.get(i);
            if (look.equals(bombeActiveExplose)){
                return false; //si on retourne tout de suite ici, le f du ArrayQueue != 0
            }
        }
        contientBombeInactive(matrice);
        //bombesAdjacentes.clear();
        return true;
    }
    public boolean contientBombeInactive(ArrayList<String> matrice) {

        for (int i = 0; i < matrice.size(); i++) {
            look = matrice.get(i);
            if (look.equals(bombeInactive)) {
                return (contientBombeInactive = true);
            }
        }
        return (contientBombeInactive = false);
    }

    /***
     *                      "2" ---> _2_
     *
     * @param matrice
     *
     */
    public void initialiserBombes(ArrayList<String> matrice) {
        ArrayQueue<Integer> chercher2 = new ArrayQueue();
        int idx;
        for (int i= 0; i< nbrElementsMatrice; i++){
            look = matrice.get(i);
            if (look.equals("2")){
                chercher2.enqueue(i);
            }
        }

        while (!chercher2.isEmpty()){
            idx = chercher2.dequeue();
            matrice.set(idx, "_2_");
        }
    }
    //----------------------------------------- Boucle --------------------------------------------------------- //

    /**
     * Lorsqu'on rencontre "_2_", on cherche les bombes adjacentes et incremente 1 à 2
     * et finalement, "_2_" ---> "*2*"
     *
     * @param matrice
     *
     */
    public void activeBombeInactive(ArrayList<String> matrice){
        ArrayQueue<Integer> chercherBombe = new ArrayQueue<>();
        int idx;
        for (int i = 0; i < matrice.size(); i++) {
            look = matrice.get(i);
            if (look.equals("_2_")) {
                chercherBombe.enqueue(i);
                bombesAdjacentes(i, matrice);    //verifie les bombes adjacentes a chaque fois qu'on voit "_2_"
            } else {
                ;//matrice.enqueue(look);
            }
        }

        incrementerBombesAdjacentes(matrice); //incrementer 1 à 2

        while (! chercherBombe.isEmpty()){
            idx = chercherBombe.dequeue();
            matrice.set(idx, "2");
        }
    }

    public void incrementerBombesAdjacentes(ArrayList<String> matrice) {
        int idx;
        while (!bombesAdjacentes.isEmpty()){
            idx = bombesAdjacentes.dequeue();
            matrice.set(idx, "_2_");
        }


    }

    public void bombesAdjacentes(int indexBombeActiveExplose, ArrayList<String> matrice){
        // bombesAdjacentes = [haut, gauche, droite, bas]
        int haut = indexBombeActiveExplose - largeurMatrice;
        int gauche = indexBombeActiveExplose - 1;
        int droite = indexBombeActiveExplose + 1;
        int bas = indexBombeActiveExplose + largeurMatrice;

        //L'ordre est important
        addIndexBombeIncrementer(haut, matrice);
        addIndexBombeIncrementer(gauche, matrice);
        addIndexBombeIncrementer(droite, matrice);
        addIndexBombeIncrementer(bas, matrice);

    }

    public void addIndexBombeIncrementer(int position, ArrayList<String> matrice ) {
        if ((position >= 0 && position < nbrElementsMatrice)) {
            if (matrice.get(position) == "1") {
                bombesAdjacentes.enqueue(position);
            }

        }
    }

    // ------------------------ creation de la matrice queue et l'imprimer sur la console -------------------------//

    public ArrayList<String> arrayList2queue(){
        findMatriceSize();
        nbrElementsMatrice = longueurMatrice * largeurMatrice;

        int indexPremierElement = 0;
        int indexDernierElement = nbrElementsMatrice;
        ArrayList<String> matrice = new ArrayList();

        for (int i = indexPremierElement; i < indexDernierElement; i++) {
            matrice.add(fichier.getGrillesBombes().get(0));
            fichier.getGrillesBombes().remove(0);
        }
        return matrice;

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
            System.out.println("La matrice ne contient pas un nombre ");
            System.exit(1);
        }
        return -1;
    }
}
