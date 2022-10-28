public class BombExploder {
    /**
     * @author Kim Trinh (20215539)
     * @author Miliya Ai (20180783)
     * @param args source du fichier d'entree
     */
    public static void main ( String[] args) {


        try {
            String path = args[0].trim();
            new Program(path);
        } catch (Exception e){
            System.out.println("Veuillez mettre le path du fichier d'entrée");

        }


    }


}
