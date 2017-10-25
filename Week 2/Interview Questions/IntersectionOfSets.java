public class IntersectionOfSets {
    private static int findMatching(Point[] one, Point[] two) {
        int onePointer = 0;
        int twoPointer = 0;
        int answer = 0;
        while (onePointer < one.length && twoPointer < two.length) {
            if (one[onePointer].x.doubleValue() == two[twoPointer].x.doubleValue()) {
                while (one[onePointer].x.doubleValue() == two[twoPointer].x.doubleValue()) {
                    if (one[onePointer].y.doubleValue() == two[twoPointer].y.doubleValue()) {
                        answer++;
                    }
                    twoPointer++;
                    if (onePointer >= one.length || twoPointer >= two.length) break;
                }
            } else {
                if (one[onePointer].x.doubleValue() < two[twoPointer].x.doubleValue()) {
                    onePointer++;
                } else {
                    twoPointer++;
                }
            }
        }
        return answer;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Point[] one = {new Point(-1, 2), new Point(-12, 2), new Point(6, 2)};
        Point[] two = {new Point(-1, 2), new Point(-12, 2), new Point(6, 2)};
        Insertion.sort(one);
        Insertion.sort(two);
        System.out.println(findMatching(one, two));
    }

    static class Point<T extends Number> implements Comparable<Point> {
        T x;
        T y;

        Point(T x, T y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return (int) (this.x.doubleValue() - o.x.doubleValue());
        }

        public String toString() {
            return "(" + this.x + ", " + this.y + ")";
        }
    }
}
