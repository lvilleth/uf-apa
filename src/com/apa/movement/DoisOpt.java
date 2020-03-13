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
        Rota novaRota;
        List<Integer> vertices = new ArrayList<>(rotaOriginal.getVertices());

        int custoVariacao;
        int novoCusto;
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

                    if(novoCusto < melhorRota.getCustoAtual()){
                        janela = new ArrayList<>(vertices.subList(inicio, fim));
                        Collections.reverse(janela);

                        novaRota = new Rota();
                        novaRota.addDemanda(rotaOriginal.getCapacidadeAtual());
                        novaRota.getVertices().addAll(vertices.subList(0, inicio));
                        novaRota.getVertices().addAll(janela); // invertido
                        novaRota.getVertices().addAll(vertices.subList(fim, vertices.size()));
                        novaRota.addCusto(novoCusto);

                        melhorRota = novaRota;
                    }

                }

            }
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

