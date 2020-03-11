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

    public void addVertice(Integer vertice, int custoVertice, int demandaVertice){
        vertices.add(vertice);
        capacidadeAtual += demandaVertice;
        custoAtual += custoVertice;
    }

    public void removeVertice(Integer vertice, int custoVertice, int demandaVertice){
        vertices.remove(vertice);
        capacidadeAtual -= demandaVertice;
        custoAtual -= custoVertice;
    }

    public void addCusto(int custo){
        this.custoAtual += custo;
    }

    public void addDemanda(int demanda){
        this.capacidadeAtual += demanda;
    }

    public void subCusto(int custo){
        this.custoAtual -= custo;
    }

    public void subDemanda(int demanda){
        this.capacidadeAtual -= demanda;
    }

    public void setVertices(List<Integer> vertices){
        this.vertices = vertices;
    }

    public int getCustoAtual(){
        return custoAtual;
    }

    public int getCapacidadeAtual(){
        return capacidadeAtual;
    }

    public List<Integer> getVertices(){ return vertices; }

    @Override
    public String toString() {
        return "\nRota{" +
                "vertices=" + vertices +
                ", capacidade=" + capacidadeAtual +
                ", custo=" + custoAtual +
                '}';
    }
}
