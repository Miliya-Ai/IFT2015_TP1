import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class readSave {

    public void read(){
        ArrayList<String> result = new ArrayList<>();
        try (FileReader f = new FileReader("src/sample.txt")) {
            StringBuffer sb = new StringBuffer();
            while (f.ready()) {
                char c = (char) f.read();
                if (c == ' ' || c == '\n') {
                    result.add(sb.toString());
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
        //for(String nbr:result) {
        //System.out.println(nbr);
    }

}
