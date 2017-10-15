import java.util.Scanner;

public class SocialNetworkConnectivity {
    public static void main(String[] args) {
        int numberOfPeople;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of people: ");
        numberOfPeople = in.nextInt();
        System.out.println("Number of logs");
        int end = in.nextInt();
        UnionFind uf = new UnionFind(numberOfPeople);
        while (end-- > 0) {
            uf.printUF();
            int from = in.nextInt();
            int to = in.nextInt();
            uf.union(from, to);
            if (uf.numberOfParents == 1) {
                System.out.println("All are friends!");
                break;
            }
        }
    }
}

