package com.mpollente.algorithms.unionfind;

public class WeightedQuickUnionFindWithPathCompressionUF implements UF {

    private int[] id;
    private int[] sz;

    /**
     * Creates an array of components with length N
     * and an array to hold the size of objects in the tree
     *
     * @param length length of components
     */
    public WeightedQuickUnionFindWithPathCompressionUF(int length) {
        id = new int[length];
        sz = new int[length];
        for (int i = 0; i < length; i++) {
            id[i] = i;
            sz[i] = 0;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
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
