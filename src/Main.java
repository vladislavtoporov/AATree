import java.util.Random;

/**
 * Created by vladi on 29.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        final int N = 63;
        Random r = new Random();
        AATree aa = new AATree();
        for (int i = 0; i < N; i++) {
            int x = r.nextInt(500);
            if (aa.find(x) == null)
                aa.insert(x);
        }
        aa.printTree();
    }
}
