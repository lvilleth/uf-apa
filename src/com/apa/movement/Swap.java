package com.apa.movement;

import com.apa.model.ProblemInfo;
import com.apa.model.Rota;

import java.util.ArrayList;
import java.util.List;

public class Swap extends Movement {

    public Swap(ProblemInfo problemInfo) {
        super(problemInfo);
    }

    public Rota execute(Rota rota, int i, int j){
        int maxIndex = rota.getVertices().size() - 1;
        if( (i == 0 || j == 0) || (i >= maxIndex || j >= maxIndex ) || (i == j)) {
            return null; // origem e destino nao pode ser alterada
        }
        Rota novaRota = new Rota();
        novaRota.addCusto(rota.getCustoAtual());
        novaRota.addDemanda(rota.getCapacidadeAtual());

        List<Integer> vertices = new ArrayList<>(rota.getVertices());

        // subtrai (antigos)
        novaRota.subCusto(calcCusto(vertices,i,j));

        Integer temp = vertices.get(i);
        vertices.set(i, vertices.get(j));
        vertices.set(j, temp);

        // adiciona (novos)
        novaRota.addCusto(calcCusto(vertices, i, j));

        novaRota.setVertices(vertices);
        return novaRota;
    }

    private int calcCusto(List<Integer> vertices, int i, int j){
        int custo = 0;
        int verticeAnterior;
        int verticeAtual;
        int verticeProximo;

        verticeAnterior = vertices.get(i - 1);
        verticeAtual = vertices.get(i);
        verticeProximo = vertices.get(i + 1);

        custo += costMatrix.get(verticeAnterior).get(verticeAtual) +
                costMatrix.get(verticeAtual).get(verticeProximo);

        verticeAnterior = vertices.get(j - 1);
        verticeAtual = vertices.get(j);
        verticeProximo = vertices.get(j + 1);

        if(Math.abs(i - j) > 1){
            custo += costMatrix.get(verticeAnterior).get(verticeAtual) +
                    costMatrix.get(verticeAtual).get(verticeProximo);
        } else if (j > i) {
            custo += costMatrix.get(verticeAtual).get(verticeProximo);
        } else {
            custo += costMatrix.get(verticeAnterior).get(verticeAtual);
        }

        return custo;
    }

}
