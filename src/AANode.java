/**
 * Created by vladi on 05.04.2017.
 */
public class AANode {
    private AANode left;
    private AANode right;
    private Comparable value;
    private int level;
    private static AANode nullNode;
    
    static {
        nullNode = new AANode(null);
        nullNode.setRight(nullNode);
        nullNode.setLeft(nullNode);
        nullNode.setLevel(0);
    }

    public AANode(Comparable x) {
        left = nullNode;
        right = nullNode;
        level = 1;
        value = x;
    }

    public static AANode getNullNode() {
        return nullNode;
    }

    public AANode getLeft() {
        return left;
    }

    public void setLeft(AANode left) {
        this.left = left;
    }

    public AANode getRight() {
        return right;
    }

    public void setRight(AANode right) {
        this.right = right;
    }

    public Comparable getValue() {
        return value;
    }

    public void setValue(Comparable value) {
        this.value = value;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
