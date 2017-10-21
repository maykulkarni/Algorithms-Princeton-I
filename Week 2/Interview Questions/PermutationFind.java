public class PermutationFind {
    public static void main(String[] args) {
        Character[] one = {'a', 'b', 'c', 'd', 'e', 'f'};
        Character[] two = {'c', 'a', 'd', 'b', 'f', 'e'};
        Shell.sort(one);
        Shell.sort(two);
        boolean notPermutation = false;
        for (int i = 0; i < one.length; i++) {
            if (!one[i].equals(two[i])) {
                notPermutation = true;
                System.out.println("Not permutation");
            }
        }
        if (!notPermutation) System.out.println("Permutation");
    }
}
