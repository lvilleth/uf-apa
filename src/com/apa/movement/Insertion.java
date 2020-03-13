package com.apa.movement;

import com.apa.model.ProblemInfo;
import com.apa.model.Rota;

import java.util.ArrayList;
import java.util.List;

public class Insertion extends Movement {

    public Insertion(ProblemInfo problemInfo) {
        super(problemInfo);
    }

    public Rota execute(Rota rotaOriginal){
        Rota melhorRota = rotaOriginal;
        List<Integer> vertices = rotaOriginal.getVertices();

        int[] melhorMovimento = new int[2];
        boolean rotaEncontrada = false;
        int melhorCusto = rotaOriginal.getCustoAtual();
        int custoVariacao;
        int novoCusto;
        for (int i = 1; i < vertices.size() - 1; i++) {
            for (int j = 1; j < vertices.size(); j++) {
                if((i == j) || (j - i == 1)){
                    continue;
                }
                custoVariacao = testaMudancaCusto(i, j, vertices);
                novoCusto = rotaOriginal.getCustoAtual() + custoVariacao;
                if(novoCusto < melhorCusto) {
                    melhorCusto = novoCusto;

                    melhorMovimento[0] = i;
                    melhorMovimento[1] = j;

                    rotaEncontrada = true;
                }
            }
        }

        if(rotaEncontrada){
            melhorRota = new Rota();
            melhorRota.addDemanda(rotaOriginal.getCapacidadeAtual());
            melhorRota.addCusto(melhorCusto);
            melhorRota.setVertices(insert(melhorMovimento[0], melhorMovimento[1], rotaOriginal.getVertices()));
        }

        return melhorRota;
    }

    private int testaMudancaCusto(int inicio, int fim, List<Integer> vertices){
        int custoAntes = 0, custoDepois = 0;

        custoAntes += custo(vertices.get(fim - 1), vertices.get(fim));
        custoAntes += custo(vertices.get(inicio - 1), vertices.get(inicio));
        custoAntes += custo(vertices.get(inicio), vertices.get(inicio + 1));

        custoDepois += custo(vertices.get(fim - 1), vertices.get(inicio));
        custoDepois += custo(vertices.get(inicio), vertices.get(fim));
        custoDepois += custo(vertices.get(inicio - 1), vertices.get(inicio + 1));


        return (custoDepois - custoAntes);
    }

    private <T extends Object> List<T> insert(int i, int j, List<T> vertices){
        List<T> novaLista = new ArrayList<>(vertices.size());

        for (int k = 0; k < vertices.size(); k++) {
            if(k == i) continue;
            if(k == j){
                novaLista.add(vertices.get(i));
                novaLista.add(vertices.get(j));
                continue;
            }
            novaLista.add(vertices.get(k));
        }
        return novaLista;
    }
}
