import java.lang.reflect.Array;
import java.util.ArrayList;

public class BombExploder {
    public static void main ( String[] args) {
        System.out.println("hello world");
        ArrayQueue test = new ArrayQueue<>();
        test.enqueue(1);
        ArrayList wut = null;
        wut.add(test.dequeue());
        System.out.println(wut.get(0));



    }
}
