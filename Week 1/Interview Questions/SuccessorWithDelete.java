public class SuccessorWithDelete {
    public static void main(String[] args) {
        QuickFindUnion qfu = new QuickFindUnion(10);
        qfu.remove(3);
        qfu.remove(4);
        qfu.remove(5);
        qfu.remove(7);
        System.out.println(qfu.successor(5));
        System.out.println(qfu.successor(2));
    }
}

class QuickFindUnion extends UnionFind {

    QuickFindUnion(int size) {
        super(size);
    }

    void remove(int x) {
        this.union(x, x + 1);
    }

    int successor(int x) {
        return super.find(x);
    }

    @Override
    public void union(int x, int y) {
        int parx = find(x);
        int pary = find(y);
        if (parx == pary) return;
        parents[parx] = y;
        numberOfParents--;
    }
}