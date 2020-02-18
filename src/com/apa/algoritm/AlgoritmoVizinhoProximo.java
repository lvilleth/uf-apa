package com.apa.algoritm;

import com.apa.model.ProblemInfo;
import com.apa.model.Rota;
import com.apa.model.Solucao;

import java.util.*;

public class AlgoritmoVizinhoProximo {

    private ProblemInfo problema;
    private Set<Integer> verticesParaVisitar;


    public AlgoritmoVizinhoProximo(ProblemInfo problemInfo){
        this.problema = problemInfo;
        this.verticesParaVisitar = new HashSet<>(problema.costMatrix.keySet());
    }

    public Solucao process(){
        Solucao solucao;

        List<Rota> rotas = new ArrayList<>();
        Rota proxRota;

        while (verticesParaVisitar.size() > 0){
            proxRota = calcularNovaRota(0, problema.capacity);
            rotas.add(proxRota);
        }
        solucao = new Solucao(rotas);
        System.out.println(solucao);

        return solucao;
    }

    private Rota calcularNovaRota(int verticeInical, int capacidadeMax){
        int capacidadeAtual = capacidadeMax;
        int proxVertice;
        int verticeAtual = verticeInical;
        Rota rota = new Rota();

        verticesParaVisitar.remove(verticeInical);

        rota.addVertice(verticeInical,
                        problema.costMatrix.get(verticeInical).get(verticeInical),
                        0);
        while (capacidadeAtual > 0){
            proxVertice = findVerticeWithMinimalCostFrom(verticeAtual, capacidadeAtual);
            if(proxVertice == -1){ // Nenhum vertice encontrado
                break;
            }
            rota.addVertice(proxVertice,
                            problema.costMatrix.get(verticeAtual).get(proxVertice),
                            problema.demand.get(proxVertice));

            verticesParaVisitar.remove(proxVertice);

            capacidadeAtual -= problema.demand.get(proxVertice);
            verticeAtual = proxVertice;
        }
        rota.addVertice(verticeInical,
                        problema.costMatrix.get(verticeAtual).get(verticeInical),
                        0);

        return rota;
    }

    private Integer findVerticeWithMinimalCostFrom(int x, int capacidadeAtual){
        int minimalcost = Integer.MAX_VALUE;
        int verticeWithMinimalCost = -1;
        int cost;

        for(int v: verticesParaVisitar){
            cost = problema.costMatrix.get(x).get(v);
            if(cost < minimalcost && !isDemandtOverCapacity(v, capacidadeAtual)){
                minimalcost = cost;
                verticeWithMinimalCost = v;
            }
        }

        return verticeWithMinimalCost;
    }

    private boolean isDemandtOverCapacity(int x, int capacidadeAtual){
        return capacidadeAtual - problema.demand.get(x) < 0;
    }


}
