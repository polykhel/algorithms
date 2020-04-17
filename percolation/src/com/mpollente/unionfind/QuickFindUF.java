package com.mpollente.algorithms.unionfind;

import com.mpollente.algorithms.unionfind.UF;

/**
 * Implementation of Quick Find algorithm (eager approach of Union Find)
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
 *         <td>N</td>
 *         <td>1</td>
 *     </tbody>
 * </table>
 *
 * <p>Defects:
 * <ul>
 *     <li>Union too expensive (N array accesses)</li>
 *     <li>Trees are flat, but too expensive to keep them flat.</li>
 * </ul>
 * </p>
 */
public class QuickFindUF implements UF {

    private int[] id;

    /**
     * Creates an array of components with length N
     *
     * @param length length of components
     */
    public QuickFindUF(int length) {
        id = new int[length];
        // set id of each object to itself (N array accesses)
        for (int i = 0; i < length; i++) {
            id[i] = i;
        }
    }

    /**
     * Checks if p and q have the same id
     */
    public boolean connected(int p, int q) {
        // check whether p and q are in the same component (2 array accesses)
        return id[p] == id[q];
    }

    /**
     * To merge components containing p and q, change all entries
     * whose id equals id[p] to id[q].
     * @param p id of p
     * @param q id of q
     */
    public void union(int p, int q) {
        int pId = id[p];
        int qId = id[q];
        // change all entries with id[p] to id[q] (at most 2N + 2 array accesses)
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) id[i] = qId;
        }
    }
}
