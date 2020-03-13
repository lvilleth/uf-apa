package com.apa.movement;

import com.apa.model.ProblemInfo;
import com.apa.model.Rota;

import java.util.ArrayList;
import java.util.List;

public class Swap extends Movement {

    public Swap(ProblemInfo problemInfo) {
        super(problemInfo);
    }

    public Rota execute(Rota rotaOriginal){
        Rota melhorRota = rotaOriginal;
        List<Integer> vertices = new ArrayList<>(rotaOriginal.getVertices());
        int melhorCusto = rotaOriginal.getCustoAtual();
        int custoVariacao, custoAntes, custoDepois, novoCusto;
        int[] movSwap = new int[2];

        int maxIndex = rotaOriginal.getVertices().size() - 1;
        for (int i = 1; i < maxIndex; i++) { // primeiro e ultimo nao pode ser alterado
            for (int j = i + 1; j < maxIndex; j++) {
                if(i == j) {
                    continue;
                }

                // Antes do movimento
                custoAntes = calcCusto(vertices, i, j);

                swap(i, j, vertices);

                // Depois do movimento
                custoDepois = calcCusto(vertices, i, j);

                custoVariacao = custoDepois - custoAntes;
                novoCusto = rotaOriginal.getCustoAtual() + custoVariacao;

                if(novoCusto < melhorCusto){
                    melhorCusto = novoCusto;
                    movSwap[0] = i;
                    movSwap[1] = j;
                }

                // desfaz movimento (volta estado original)
                swap(i, j, vertices);
            }
        }
        if(melhorCusto != rotaOriginal.getCustoAtual()){
            melhorRota = new Rota();
            melhorRota.addCusto(melhorCusto);
            melhorRota.addDemanda(rotaOriginal.getCapacidadeAtual());
            melhorRota.setVertices(swap(movSwap[0], movSwap[1], new ArrayList<>(rotaOriginal.getVertices())));
        }

        return melhorRota;
    }

    private <T extends Object> List<T> swap(int i, int j, List<T> vertices){
        T temp = vertices.get(i);
        vertices.set(i, vertices.get(j));
        vertices.set(j, temp);
        return vertices;
    }

    private int calcCusto(List<Integer> vertices, int i, int j){
        int custo = 0;
        int verticeAnterior;
        int verticeAtual;
        int verticeProximo;

        verticeAnterior = vertices.get(i - 1);
        verticeAtual = vertices.get(i);
        verticeProximo = vertices.get(i + 1);

        custo += custo(verticeAnterior, verticeAtual) + custo(verticeAtual, verticeProximo);

        verticeAnterior = vertices.get(j - 1);
        verticeAtual = vertices.get(j);
        verticeProximo = vertices.get(j + 1);

        if(Math.abs(i - j) > 1){
            custo += custo(verticeAnterior, verticeAtual) + custo(verticeAtual, verticeProximo);
        } else if (j > i) {
            custo += custo(verticeAtual, verticeProximo);
        } else {
            custo += custo(verticeAnterior, verticeAtual);
        }

        return custo;
    }

}
