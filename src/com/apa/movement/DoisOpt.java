package com.apa.movement;

import com.apa.model.ProblemInfo;
import com.apa.model.Rota;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoisOpt extends Movement {

    public DoisOpt(ProblemInfo problemInfo) {
        super(problemInfo);
    }

    public Rota execute(Rota rotaOriginal){
        Rota melhorRota = rotaOriginal;
        List<Integer> vertices = new ArrayList<>(rotaOriginal.getVertices());

        int custoVariacao;
        int novoCusto;
        int[] movDoisOpt = new int[2];
        int melhorCusto = rotaOriginal.getCustoAtual();

        List<Integer> janela;
        for (int inicio = 1; inicio < vertices.size() - 2; inicio++) {
            for (int fim = inicio + 1; fim < vertices.size(); fim++) {
                if((fim - inicio) == 1){
                    continue;
                }
                // posicao fim nao inclusiva
                custoVariacao = testaMudancaCusto(inicio, fim - 1, vertices);

                if(custoVariacao < 0){
                    // calcula novo custo
                    novoCusto = rotaOriginal.getCustoAtual() + custoVariacao;

                    if(novoCusto < melhorCusto){
                        movDoisOpt[0] = inicio;
                        movDoisOpt[1] = fim;

                        melhorCusto = novoCusto;
                    }
                }
            }
        }

        if(melhorCusto != rotaOriginal.getCustoAtual()){
            int inicio  = movDoisOpt[0];
            int fim     = movDoisOpt[1];

            janela = new ArrayList<>(vertices.subList(inicio, fim));
            Collections.reverse(janela);

            melhorRota = new Rota();
            melhorRota.addDemanda(rotaOriginal.getCapacidadeAtual());
            melhorRota.getVertices().addAll(vertices.subList(0, inicio));
            melhorRota.getVertices().addAll(janela); // invertido
            melhorRota.getVertices().addAll(vertices.subList(fim, vertices.size()));
            melhorRota.addCusto(melhorCusto);
        }
        return melhorRota;
    }

    private int testaMudancaCusto(int inicio, int fim, List<Integer> vertices){
        int custoAntes = 0, custoDepois = 0;

        custoAntes += custo(vertices.get(inicio - 1), vertices.get(inicio));
        custoAntes += custo(vertices.get(fim), vertices.get(fim + 1));

        custoDepois += custo(vertices.get(inicio - 1), vertices.get(fim));
        custoDepois += custo(vertices.get(inicio), vertices.get(fim + 1));

        return (custoDepois - custoAntes);
    }


}

