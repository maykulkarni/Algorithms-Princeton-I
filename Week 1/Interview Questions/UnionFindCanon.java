import java.util.Arrays;

public class UnionFindCanon extends UnionFind {
    public int[] largest;

    UnionFindCanon(int size) {
        super(size);
        largest = new int[size];
        for (int i = 0; i < size; i++) largest[i] = i;
    }

    public static void main(String[] args) {
        UnionFindCanon ufc = new UnionFindCanon(10);
        ufc.union(0, 1);
        ufc.union(2, 3);
        ufc.union(4, 5);
        ufc.union(6, 7);
        ufc.union(7, 8);
        ufc.union(8, 9);
        ufc.union(3, 4);
        ufc.union(5, 9);
        for (int i = 0; i < 10; i++) {
            System.out.print(ufc.findLargest(i) + " ");
        }
    }

    public void printParents() {
        System.out.println(Arrays.toString(this.largest));
    }

    @Override
    public void union(int x, int y) {
        int parx = find(x);
        int pary = find(y);
        if (parx == pary) return;
        if (rank[parx] > rank[pary]) {
            parents[pary] = parx;
            rank[parx]++;
        } else {
            parents[parx] = pary;
            rank[pary]++;
        }
        if (largest[parx] > largest[pary])
            largest[pary] = parx;
        else
            largest[parx] = pary;
        numberOfParents--;
    }

    public int findLargest(int node) {
        return largest[find(node)];
    }
}
