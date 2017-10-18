import java.util.Arrays;

class UnionFind {
    protected int[] parents;
    protected int[] rank;
    int numberOfParents;

    UnionFind(int size) {
        this.numberOfParents = size;
        this.parents = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < size; i++) make(i);
    }

    public int getNumberOfParents() {
        return this.numberOfParents;
    }

    void printUF() {
        System.out.println(Arrays.toString(this.parents));
    }

    public void make(int i) {
        parents[i] = i;
        rank[i] = 0;
    }

    public int find(int node) {
        if (parents[node] != node)
            parents[node] = find(parents[node]);
        return parents[node];
    }

    void union(int x, int y) {
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
        numberOfParents--;
    }
}
