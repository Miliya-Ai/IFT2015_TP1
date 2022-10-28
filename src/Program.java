import java.util.ArrayList;

public class Program {
    private int iteration = 0;
    private boolean contientBombeInactive = false;

    private final String bombeActive = "2";
    private final String bombeInactive = "1";
    private final String pasBombe = "0";
    private final String bombeActiveExplose = "_2_";

    private int longueurMatrice = 0;
    private int largeurMatrice = 0;
    private int nbrElementsMatrice = 0;

    private final ArrayQueue<Integer> bombesAdjacentes = new ArrayQueue();
    private final  LireFichier fichier;

    private String look;

    //constructeur
    public Program(String path) {

        this.fichier = new LireFichier(path);
        start();

    }

    // ------------------------------------------------------------------------------------------------------- //

    /**
     * Print le nombre d'iterations ou -1, s'il reste des bombes inactives
     */
    private void start(){
        while (!isDone()) { //on itere sur la prochaine grille dans le fichier
            algoBegin(extraireMatrice());
            if (contientBombeInactive){
                System.out.println(-1);
            } else {
                System.out.println(iteration);
            }
        }
    }

    /**
     * Compte les iterations
     * @param matrice matrice extrait de la grillesBombes qui contient tous les matrices du fichier
     */
    private void algoBegin(ArrayList<String> matrice){
        initialiserBombes(matrice);
        while (!isDone(matrice)) {  //continuer a chercher tous les bombes
            activeBombeInactive(matrice);
            iteration++;
        }
    }

    /**
     *
     * @return true si on a traverse tous les matrices du fichier
     */
    private Boolean isDone(){
        if (fichier.getGrillesBombes().isEmpty()) {
            return true;
        }
        iteration = 0;
        return false;
    }

    /**
     *
     * @param matrice une matrice extrait de fichier
     * @return true s'il reste plus de bombes a exploser
     */

    private Boolean isDone(ArrayList<String> matrice){

        for (int i = 0; i < nbrElementsMatrice; i ++){
            look = matrice.get(i);
            if (look.equals(bombeActiveExplose)){
                return false; //si on retourne tout de suite ici, le f du ArrayQueue != 0
            }
        }
        contientBombeInactive(matrice);
        return true;
    }

    /**
     *
     * @param matrice une matrice extrait de fichier
     * @return true s'il reste une bombe inactive
     */
    private boolean contientBombeInactive(ArrayList<String> matrice) {

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
     * Au depart, on tranforme tous les "2" en "_2_"
     * @param matrice une matrice extrait de fichier
     *
     */
    private void initialiserBombes(ArrayList<String> matrice) {

        ArrayQueue<Integer> chercher2 = new ArrayQueue();
        for (int i= 0; i< nbrElementsMatrice; i++){
            look = matrice.get(i);
            if (look.equals("2")){
                chercher2.enqueue(i);
            }
        }
        changerElementMatrice(matrice, bombeActiveExplose, chercher2 );
    }
    //----------------------------------------- Boucle --------------------------------------------------------- //

    /**
     * Lorsqu'on rencontre "_2_", on cherche les bombes adjacentes et incremente 1 à 2
     * et finalement, "_2_" ---> "2"
     *
     * @param matrice une matrice extrait de fichier
     *
     */
    private void activeBombeInactive(ArrayList<String> matrice){
        ArrayQueue<Integer> chercherBombe = new ArrayQueue<>();

        for (int i = 0; i < matrice.size(); i++) {
            look = matrice.get(i);
            if (look.equals(bombeActiveExplose)) {
                chercherBombe.enqueue(i);
                bombesAdjacentes(i, matrice);    //verifie les bombes adjacentes a chaque fois qu'on voit "_2_"
            }
        }

        incrementerBombesAdjacentes(matrice); //incrementer 1 à 2
        changerElementMatrice(matrice, bombeActive, chercherBombe);
    }

    /**
     * Tous les "1" adjacents de la bombe qui a explose devient des "_2_"
     * @param matrice une matrice extrait de fichier
     */
    private void incrementerBombesAdjacentes(ArrayList<String> matrice) {
        changerElementMatrice(matrice, bombeActiveExplose, bombesAdjacentes);
    }

    /**
     * Mettre tous les index des positions adjacentes de "_2_" dans un queue
     * @param indexBombeActiveExplose index des bombes adjacents de "_2_"
     * @param matrice une matrice extrait de fichier
     */
    private void bombesAdjacentes(int indexBombeActiveExplose, ArrayList<String> matrice){

        int haut = indexBombeActiveExplose - largeurMatrice;
        int gauche = indexBombeActiveExplose - 1;
        int droite = indexBombeActiveExplose + 1;
        int bas = indexBombeActiveExplose + largeurMatrice;

        addIndexBombeIncrementer(haut, matrice );
        addIndexBombeIncrementer(gauche, matrice );
        addIndexBombeIncrementer(droite, matrice);
        addIndexBombeIncrementer(bas, matrice);

    }

    /**
     * Si ce n'est pas un 1, on n'ajoute pas la position dans le queue
     * @param position index des positions adjacentes de "_2_"
     * @param matrice une matrice extrait de fichier
     */
    private void addIndexBombeIncrementer(int position, ArrayList<String> matrice ) {

        if ((position >= 0 && position < nbrElementsMatrice)) {
            look = matrice.get(position);
            if (look.equals( "1")) {
                bombesAdjacentes.enqueue(position);
            }
        }

    }

    // ---------------creation de la matrice arrayList et modifier les elements de la matrice-------------------------//

    /**
     *
     * @param matrice une matrice extrait de fichier
     * @param nouveauElement convertir un element a un nouveau element
     * @param queue contient les index des elements a changer
     */
    private void changerElementMatrice(ArrayList<String> matrice, String nouveauElement, ArrayQueue<Integer> queue ){
        int idx;
        while (!queue.isEmpty()){
            idx = queue.dequeue();
            matrice.set(idx, nouveauElement);
        }
    }

    /**
     *
     * @return une matrice extrait de fichier
     */
    private ArrayList<String> extraireMatrice(){
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

    /**
     * calcule la taille de la matrice a l'aide du la largeur et longeur de la matrice
     */
    private void findMatriceSize(){
        longueurMatrice = toInt(fichier.getGrillesBombes().get(0));
        largeurMatrice = toInt(fichier.getGrillesBombes().get(1));

        //enleve le string de longeur et largeur dans l'arrayList
        fichier.getGrillesBombes().remove(0); fichier.getGrillesBombes().remove(0);
    }

    /**
     *
     * @param string longeur et largeur de la matrice
     * @return le string de la longeur et largeur en int
     */
    private int toInt(String string){
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
