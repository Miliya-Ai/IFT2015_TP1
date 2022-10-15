import java.lang.reflect.Array;
import java.util.ArrayList;

public class BombExploder {
    public static void main ( String[] args) {
        readSave premiereMatrice = new readSave();
        premiereMatrice.read();
        premiereMatrice.arrayList2queue();
    }
}
