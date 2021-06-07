package cop4534.assignment3;

/**
 * Iterator class, allows traversal of the set of neighbors
 * of given permutation. There will be n(n-1)/2 neighbors,
 * obtained by swapping all pairs of elements in the permutation.
 *
 * @author Antonio Hernandez
 */
/*
Name: Jacob Williams
Section: COP 4534 - U01
Panther ID: 6182345
 */
public class PermutationNeighborhood {

    private final int[] p; //permutation
    private final int SIZE; //size of permutation
    private int loc1; //loc1 and loc2 are the locations of
    private int loc2; //p that will be swapped next

    /**
     * Initializes permutation of this object and locations
     * whose values will be swapped.
     *
     * @param a permutation whose neighborhood is to be generated
     */
    public PermutationNeighborhood(int[] a) {
        SIZE = a.length;
        p = new int[SIZE];
        System.arraycopy(a, 0, p, 0, SIZE);

        loc1 = 0;
        loc2 = 1;
    }

    /**
     * Returns true if at least a neighbor remains to be generated; false
     * otherwise.
     */
    public boolean hasNext() {
        return loc1 != SIZE - 1;
    }

    /**
     * Returns next permutation neighbor.
     *
     * @return next permutation neighbor, if one exists; null otherwise
     */
    public int[] next() {
        if (hasNext()) {
            //copy p to a
            int[] a = new int[SIZE];
            System.arraycopy(p, 0, a, 0, SIZE);

            //exchange elements at locations loc1 and loc2
            a[loc1] = p[loc2];
            a[loc2] = p[loc1];

            //advance loc1 and loc2
            if (loc2 == SIZE - 1) {
                loc1++;
                loc2 = loc1 + 1;
            } else
                loc2++;

            return a;
        } else
            return null;
    }
}
