// AATree class
// ******************PUBLIC OPERATIONS*********************
// void insert(x)
// void remove(x)
// Comparable find(x)
// Comparable findMin()
// Comparable findMax()
// boolean isEmpty()
// void clear()
// void printTree()
// ******************ERRORS********************************
// Exceptions are thrown by insert and remove if warranted

/**
 * @author Vladislav Toporov
 */

public class AATree {

    private AANode root;
    private AANode nullNode;
    private AANode deletedNode;
    private AANode lastNode;

    public AATree() {
        nullNode = AANode.getNullNode();
        root = nullNode;
    }

    public void insert(Comparable x) {
        root = insert(x, root);
    }

    private AANode insert(Comparable x, AANode t) {
        if (t == nullNode) {
            t = new AANode(x);
        } else if (x.compareTo(t.getValue()) < 0)
            t.setLeft(insert(x, t.getLeft()));
        else if (x.compareTo(t.getValue()) > 0)
            t.setRight(insert(x, t.getRight()));
        else
            throw new DuplicateItemException(x.toString());
        t = skew(t);
        t = split(t);
        return t;
    }

    public void remove(Comparable x) {
        deletedNode = nullNode;
        root = remove(x, root);
    }

    private AANode remove(Comparable x, AANode t) {
        if (t != nullNode) {
            lastNode = t;
            if (x.compareTo(t.getValue()) < 0)
                t.setLeft(remove(x, t.getLeft()));
            else {
                deletedNode = t;
                t.setRight(remove(x, t.getRight()));
            }

            if (t == lastNode) {
                if (deletedNode == nullNode || x.compareTo(deletedNode.getValue()) != 0)
                    throw new ItemNotFoundException(x.toString());
                deletedNode.setValue(t.getValue());
                t = t.getRight();
            }

            else if (t.getLeft().getLevel() < t.getLevel() - 1 ||
                    t.getRight().getLevel() < t.getLevel() - 1) {

                if (t.getRight().getLevel() > t.getLevel() - 1) {
                    t.setLevel(t.getLevel() - 1);
                    AANode tmp = t.getRight();
                    tmp.setLevel(t.getLevel());
                }
                t = skew(t);
                t.setRight(skew(t.getRight()));
                AANode tmp = t.getRight();
                tmp.setRight(skew(tmp.getRight()));
                t = split(t);
                t.setRight(split(t.getRight()));

            }
        }
        return t;
    }

    private AANode skew(AANode t) {
        if (t.getLeft().getLevel() == t.getLevel())
            t = rotateWithLeftChild(t);
        return t;
    }

    private AANode split(AANode t) {
        if (t.getRight().getRight().getLevel() == t.getLevel()) {
            t = rotateWithRightChild(t);
            t.setLevel(t.getLevel() + 1);
        }
        return t;
    }

    private AANode rotateWithLeftChild(AANode k2) {
        AANode k1 = k2.getLeft();
        k2.setLeft(k1.getRight());
        k1.setRight(k2);
        return k1;
    }

    private AANode rotateWithRightChild(AANode k1) {
        AANode k2 = k1.getRight();
        k1.setRight(k2.getLeft());
        k2.setLeft(k1);
        return k2;
    }

    public Comparable findMin() {
        if (isEmpty())
            return null;
        AANode ptr = root;

        while (ptr.getLeft() != nullNode) {
            ptr = ptr.getLeft();
        }
        return ptr.getValue();
    }

    public Comparable findMax() {
        if (isEmpty())
            return null;
        AANode ptr = root;

        while (ptr.getRight() != nullNode) {
            ptr = ptr.getRight();
        }
        return ptr.getValue();
    }

    public Comparable find(Comparable x) {
        AANode current = root;
        nullNode.setValue(x);
        for (; ; ) {
            if (x.compareTo(current.getValue()) < 0)
                current = current.getLeft();
            else if (x.compareTo(current.getValue()) > 0)
                current = current.getRight();
            else if (current != nullNode)
                return current.getValue();
            else
                return null;
        }
    }

    public void clear() {
        root = nullNode;
    }

    public boolean isEmpty() {
        return (root == nullNode);
    }

    public void printTree() {
        printNode(root, 0);
    }

    private static void printNode(AANode root, int h) {
        if (root != AANode.getNullNode()) {
            printNode(root.getRight(), h + 1);
            for (int i = 0; i <= h; i++)
                System.out.print("  ");
            System.out.println(root.getValue());
            printNode(root.getLeft(), h + 1);


        }
    }
}
