package com.apa.movement;

import com.apa.model.ProblemInfo;
import com.apa.model.Rota;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VizinhancaSwap {

    private Swap swapMove;
    private List<Rota> vizinhanca;
    private Rota rotaAtual;

    public VizinhancaSwap(ProblemInfo problemInfo){
        this.swapMove = new Swap(problemInfo);
        vizinhanca = new ArrayList<>();
    }

    public List<Rota> createVizinhanca(Rota rotaOriginal){
        if((rotaAtual == rotaOriginal) && (vizinhanca.size() > 0)){
            return vizinhanca;
        }
        rotaAtual = rotaOriginal;

        vizinhanca.clear();
        Rota vizinho;
        for (int i = 0; i < rotaOriginal.getVertices().size(); i++) {
            for (int j = 0; j < rotaOriginal.getVertices().size(); j++) {
                vizinho = swapMove.execute(rotaOriginal, i,j);
                if(vizinho != null) {
                    vizinhanca.add(vizinho);
                }
            }
        }
        return vizinhanca;
    }

    public Rota getVizinhoMenorCusto(Rota rotaOriginal){
        return createVizinhanca(rotaOriginal).stream()
                .min(Comparator.comparing(Rota::getCustoAtual))
                .orElse(rotaOriginal);
    }

}
