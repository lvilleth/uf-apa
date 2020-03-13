package com.apa.movement;

import com.apa.model.ProblemInfo;

import java.util.List;
import java.util.Map;

public abstract class Movement {

    protected Map<Integer, List<Integer>> costMatrix;
    protected Map<Integer, Integer> demand;

    public Movement(ProblemInfo problemInfo){
        this.costMatrix = problemInfo.costMatrix;
        this.demand = problemInfo.demand;
    }

    protected int custo(int verticeA, int verticeB){
        return costMatrix.get(Math.max(0, verticeA)).get(verticeB);
    }

}
