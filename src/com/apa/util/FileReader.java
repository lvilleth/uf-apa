package com.apa.util;

import com.apa.model.ProblemInfo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public static List<String> read(String filename) throws Exception{
        return Files.readAllLines(Paths.get(filename));
    }

    public static ProblemInfo readScannerToInfo(String filename) throws Exception{
        Scanner scan = new Scanner(Paths.get(filename));
        ProblemInfo problemInfo = new ProblemInfo();

        scan.next(); // NAME:
        problemInfo.name = scan.next();
        System.out.println("NAME: "+problemInfo.name);

        scan.next(); // DIMENSION
        problemInfo.dimension = scan.nextInt();
        System.out.println("DIMENSION: "+problemInfo.dimension);

        scan.next(); // CAPACITY
        problemInfo.capacity = scan.nextInt();
        System.out.println("CAPACITY: "+problemInfo.capacity);

        scan.next(); // DEMAND_SECTION
        for (int i = 0; i < problemInfo.dimension ; i++) {
            problemInfo.demand.put(scan.nextInt(), scan.nextInt());
        }

        scan.next(); // EDGE_WEIGHT_SECTION
        List costList;
        for (int i = 0; i < problemInfo.dimension ; i++) {
            for (int j = 0; j < problemInfo.dimension ; j++) {
                costList = problemInfo.costMatrix.get(i);
                if(costList == null){
                    costList = new ArrayList(problemInfo.dimension);
                    problemInfo.costMatrix.put(i, costList);
                }
                costList.add(scan.nextInt());
            }
        }

        return problemInfo;
    }

}
