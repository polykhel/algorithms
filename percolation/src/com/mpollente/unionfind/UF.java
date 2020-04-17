package com.mpollente.algorithms.unionfind;

/**
 * Created by mpollente on 04/13/2020
 */
public interface UF {

    boolean connected(int p, int q);

    void union(int p, int q);
}
