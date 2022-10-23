import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Comparer {
      static ArrayList< Integer > bombeAdeIndex = new ArrayList<>();
      static ArrayList< Integer > bombeAdeVraiChiffre = new ArrayList<>();
      String look;
    public static void main ( String[] args) {

       bombeAdeIndex.add(1);
       bombeAdeIndex.add(3);
       bombeAdeIndex.add(0);
       bombeAdeIndex.add(2);
       bombeAdeIndex.add(4);
       bombeAdeIndex.add(5);
       bombeAdeIndex.add(7);

       // jusqu'a ici, juste construire un Arraylist bombeAdeIndex = [2,2,1,1,1,0,1,1,2]

       bombeAdeVraiChiffre.add(2);
       bombeAdeVraiChiffre.add(1);
       bombeAdeVraiChiffre.add(2);
       bombeAdeVraiChiffre.add(1);
       bombeAdeVraiChiffre.add(1);
       bombeAdeVraiChiffre.add(0);
       bombeAdeVraiChiffre.add(1);
       bombeAdeVraiChiffre.add(1);
       bombeAdeVraiChiffre.add(2);

       // bombeAdeVraiChiffre = [2,1,2,1,1,0,1]
        Incremental bombeAdeVraiChiffre = new Incremental();
        bombeAdeVraiChiffre.incremental(bombeAdeVraiChiffre);
        Collections.sort(bombeAdeIndex, new Comparator< Integer >() {

    public int compare(Integer m, Integer n) {
        if ( m > n ) {
            return 1;
             } else {
            return -1;
            }
          }
         });
         System.out.println("en ordre：" + bombeAdeIndex);
         bombeAdeVraiChiffre.incremental(bombeAdeVraiChiffre);
            }

    public void remmetre(LinkedQueue matrice) {
       for (int i = 0; i < matrice.size(); i++) {
           look = (String) matrice.dequeue();
           if (i == bombeAdeIndex.get(i)) {
               look = String.valueOf(bombeAdeVraiChiffre.get(i));
           }
        }
       matrice.enqueue(look);
       System.out.println(matrice);
    }
}
    // matrice = [2,2,1,1,1,0,1,1,2]



