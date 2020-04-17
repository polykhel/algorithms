package com.mpollente.algorithms.unionfind;

/**
 * Implementation of Quick Union algorithm (lazy approach of Union Find)
 * plus weighting
 *
 * <h3>Number of array accesses (for read or write)</h3>
 * <table>
 *     <thead>
 *         <th>initialize</th>
 *         <th>union</th>
 *         <th>find</th>
 *     </thead>
 *     <tbody>
 *         <td>N</td>
 *         <td>log N (includes cost of finding roots)</td>
 *         <td>log N</td>
 *     </tbody>
 * </table>
 *
 * <p>Running time:
 * <ul>
 *     <li>Find: takes time proportional to depth of p and q.</li>
 *     <li>Union: takes constant time, given roots.</li>
 * </ul>
 * </p>
 */
public class WeightedQuickUnionUF implements UF {

    private int[] id;
    private int[] sz;

    /**
     * Creates an array of components with length N
     * and an array to hold the size of objects in the tree
     *
     * @param length length of components
     */
    public WeightedQuickUnionUF(int length) {
        id = new int[length];
        sz = new int[length];
        for (int i = 0; i < length; i++) {
            id[i] = i;
            sz[i] = 0;
        }
    }

    private int root(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }

    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    @Override
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]) {
            id[i] = j; sz[j] += sz[i];
        } else {
            id[j] = i; sz[i] += sz[j];
        }
    }
}
