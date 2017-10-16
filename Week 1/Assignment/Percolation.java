import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */

public class Percolation {

    private final int gridSize;         // size of the gird is always gridSize x gridSize
    private int[][] grid;               // 2D Grid to speed up operations
    private WeightedQuickUnionUF uf;    // UnionFind with weight
    private int numberOfOpen;           // Number of boxes that are open

    public Percolation(int gridSize) {
        grid = new int[gridSize][gridSize];
        this.gridSize = gridSize;
        numberOfOpen = 0;
        this.uf = new WeightedQuickUnionUF(gridSize * gridSize + 2);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(4);
        p.open(1, 1);
        p.open(2, 1);
        p.open(3, 1);
        p.open(4, 1);
        System.out.println(p.percolates());
    }

    /**
     * Returns the number of boxes opened.
     *
     * @return number of open boxes
     */
    public int numberOfOpenSites() {
        return numberOfOpen;
    }

    /**
     * Range check.
     *
     * @param row row
     * @param col column
     * @return if the row, col is in range
     */
    private boolean rangeCheck(int row, int col) {
        return row > 0 && row <= this.gridSize
                && col > 0 && col <= this.gridSize;
    }

    /**
     * Opens a box given by row, column.
     *
     * @param row row
     * @param col column
     */
    public void open(int row, int col) {
        if (!rangeCheck(row, col)) {
            throw new IllegalArgumentException();
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[row - 1][col - 1] = 1;
        if (row == 1) {
            uf.union(0, col);
        }
        if (row == gridSize) {
            uf.union(gridSize * gridSize + 1, to1d(gridSize - 1, col - 1));
        }

        if (rangeCheck(row - 1, col) && isOpen(row - 1, col)) {
            uf.union(to1d(row - 1, col - 1), to1d(row - 2, col - 1));
        }
        if (rangeCheck(row + 1, col) && isOpen(row + 1, col)) {
            uf.union(to1d(row - 1, col - 1), to1d(row, col - 1));
        }
        if (rangeCheck(row, col - 1) && isOpen(row, col - 1)) {
            uf.union(to1d(row - 1, col - 1), to1d(row - 1, col - 2));
        }
        if (rangeCheck(row, col + 1) && isOpen(row, col + 1)) {
            uf.union(to1d(row - 1, col - 1), to1d(row - 1, col));
        }
        this.numberOfOpen++;
    }

    /**
     * Returns if the box is open.
     *
     * @param row row
     * @param col column
     * @return true if box is open, else false
     */
    public boolean isOpen(int row, int col) {
        if (!rangeCheck(row, col)) throw new IllegalArgumentException();
        return grid[row - 1][col - 1] == 1;
    }

    /**
     * Converts 2D point (row, column) to 1D as it is saved internally
     * in WeightedQuickUnionUF.
     *
     * @param row row
     * @param col column
     * @return 1D address of the 2D point
     */
    private int to1d(int row, int col) {
        return (row * this.gridSize) + col + 1;
    }

    /**
     * Indicates if the boxes is full.
     *
     * @param row row
     * @param col col
     * @return true if it is full, else false
     */
    public boolean isFull(int row, int col) {
        if (!rangeCheck(row, col)) {
            throw new IllegalArgumentException();
        }
        return uf.connected(0, to1d(row - 1, col - 1));
    }

    /**
     * Indicates if the system is percolated at this moment.
     *
     * @return true if it has percolated, else false
     */
    public boolean percolates() {
        return uf.connected(0, gridSize * gridSize + 1);
    }
}
