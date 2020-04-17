package com.mpollente.algorithms.unionfind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by mpollente on 04/14/2020
 */
public class UFClient {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("tinyUF.txt");
        Scanner scanner = new Scanner(file);
        int N = scanner.nextInt();
        UF uf = new WeightedQuickUnionUF(N);
        while (scanner.hasNextLine()) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                System.out.println(p + " " + q);
            }
        }
    }
}
