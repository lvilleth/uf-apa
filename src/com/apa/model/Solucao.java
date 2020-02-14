package com.apa.model;

import java.util.ArrayList;
import java.util.List;

public class Solucao {

    public List<Rota> rotas;
    public int custoTotal;

    public Solucao(List<Rota> rotas){
        this.rotas = rotas;
        if(rotas != null && rotas.size() > 0) {
            this.custoTotal = rotas.stream().mapToInt(Rota::getCustoAtual).sum();
        } else {
            this.custoTotal = 0;
        }
    }
    public Solucao(){
        this(new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Solucao{" +
                " custoTotal=" + custoTotal +
                ", rotas=" + rotas +
                '}';
    }
}