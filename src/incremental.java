import java.util.ArrayList;

public class incremental {
    // suppose queue ici c'est  bombeAdeVraiChiffre = [2,1,2,1,1,0,1]
    String look;


    public void incremental (LinkedQueue matrice) {
        for (int j = 0; j < matrice.size(); j++) {
            look = (String) matrice.dequeue();
            if (look == String.valueOf(1)) {
                look += 1;
            }
        }
    }
}