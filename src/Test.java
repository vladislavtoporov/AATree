/**
 * Created by vladi on 05.04.2017.
 */
public class Test {
    public static void main(String[] args) {
        AATree t = new AATree();
        final int N = 100;

        for (int i = 0; i < N; i++)
            t.insert(i);
        //t.printTree();
        System.out.println("Inserts complete");

        if ((Integer) (t.findMin()) != 0 ||
                (Integer) (t.findMax()) != N - 1)
            System.out.println("FindMin or FindMax error!");

        for (int i = 1; i < N; i += 2)
            t.remove(i);
        //t.printTree();
        System.out.println("Removes complete");

        for (int i = 0; i < N; i += 2)
            if ((Integer) t.find(i) != i)
                System.out.println("Error: find fails for " + i);

        for (int i = 1; i < N; i += 2)
            if (t.find(i) != null)
                System.out.println("Error: Found deleted item " + i);
    }
}
