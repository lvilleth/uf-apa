package com.apa.model;

public class TableData {

    private Float otimo;
    private Float mediaSolucao;
    private Float melhorSolucao;
    private Float mediaTempo;
    private Float gap;

    @Override
    public String toString() {
        return "TableData{"+
        "otimo="+getOtimo()+
        ", media_solucao="+getMediaSolucao()+
        ", melhor_solucao="+getMelhorSolucao()+
        ", media_tempo="+getMediaTempo()+
        ", gap="+getGap()+
        "}";
    }

    public Float getOtimo() {
        return otimo;
    }

    public void setOtimo(Float otimo) {
        this.otimo = otimo;
    }

    public Float getMediaSolucao() {
        return mediaSolucao;
    }

    public void setMediaSolucao(Float mediaSolucao) {
        this.mediaSolucao = mediaSolucao;
    }

    public Float getMelhorSolucao() {
        return melhorSolucao;
    }

    public void setMelhorSolucao(Float melhorSolucao) {
        this.melhorSolucao = melhorSolucao;
    }

    public Float getMediaTempo() {
        return mediaTempo;
    }

    public void setMediaTempo(Float mediaTempo) {
        this.mediaTempo = mediaTempo;
    }

    public Float getGap() {
        return gap;
    }

    public void setGap(Float gap) {
        this.gap = gap;
    }
}
