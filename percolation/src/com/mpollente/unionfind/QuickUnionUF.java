package com.mpollente.algorithms.unionfind;

/**
 * Implementation of Quick Union algorithm (lazy approach of Union Find)
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
 *         <td>N (includes cost of finding roots)</td>
 *         <td>N</td>
 *     </tbody>
 * </table>
 *
 * <p>Defects:
 * <ul>
 *     <li>Trees can get tall.</li>
 *     <li>Find too expensive (could be N array accesses).</li>
 * </ul>
 * </p>
 */
public class QuickUnionUF implements UF {

    private int[] id;

    /**
     * Creates an array of components with length N
     *
     * @param length length of components
     */
    public QuickUnionUF(int length) {
        id = new int[length];
        for (int i = 0; i < length; i++) id[i] = i;
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
        id[i] = j;
    }
}
