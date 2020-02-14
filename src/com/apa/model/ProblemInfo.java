package com.apa.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProblemInfo {

    public String name;
    public int dimension;
    public int capacity;
    public Map<Integer, Integer> demand;
    public Map<Integer, List<Integer>> costMatrix;

    public ProblemInfo(){
        this.costMatrix = new HashMap<>();
        this.demand = new HashMap<>();
    }

}
