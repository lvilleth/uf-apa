package com.apa.util;

import com.apa.model.ProblemInfo;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public static List<String> read(String filename) throws Exception{
        return Files.readAllLines(Paths.get(filename));
    }

    public static ProblemInfo readFileToInfo(String filename) throws Exception{
        return readFromScanner(new Scanner(Paths.get(filename)));
    }

    public static ProblemInfo readFileToInfo(InputStream fileStream) {
        return readFromScanner(new Scanner(fileStream));
    }

    private static ProblemInfo readFromScanner(Scanner scan){
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
//                System.out.println(String.format("(%d,%d)= %d",i,j,problemInfo.costMatrix.get(i).get(j)));
            }
        }

        return problemInfo;
    }

}
