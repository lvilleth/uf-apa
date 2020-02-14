package com.apa.model;

import java.util.ArrayList;
import java.util.List;

public class Rota {

    private List<Integer> vertices;
    private int capacidadeAtual;
    private int custoAtual;

    public Rota(){
        this.vertices = new ArrayList<>();
        this.capacidadeAtual = 0;
        this.custoAtual = 0;
    }

    public void addVertice(int vertice, int custoVertice, int demandaVertice){
        vertices.add(vertice);
        capacidadeAtual += demandaVertice;
        custoAtual += custoVertice;
    }

    public int getCustoAtual(){
        return custoAtual;
    }

    public int getCapacidadeAtual(){
        return capacidadeAtual;
    }

    @Override
    public String toString() {
        return "\nRota{" +
                "vertices=" + vertices +
                ", capacidade=" + capacidadeAtual +
                ", custo=" + custoAtual +
                '}';
    }
}
