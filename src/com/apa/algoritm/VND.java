package com.apa.algoritm;

import com.apa.model.ProblemInfo;
import com.apa.model.Rota;
import com.apa.model.Solucao;
import com.apa.movement.DoisOpt;
import com.apa.movement.Insertion;
import com.apa.movement.Movement;
import com.apa.movement.Swap;

import java.util.ArrayList;
import java.util.List;

/**
 * Variable Neighborhood Descent
 */
public class VND {

    List<Movement> movements;

    public VND(ProblemInfo problemInfo){
        movements = new ArrayList<>();

        DoisOpt opt2 = new DoisOpt(problemInfo);
        Insertion insertion = new Insertion(problemInfo);
        Swap swap = new Swap(problemInfo);

        movements.add(opt2);
        movements.add(insertion);
        movements.add(swap);
    }

    public Solucao execute(Solucao S){
        Rota novaRota = null;
        Rota rotaTestada;
        Movement movement;

        List<Rota> novaRotaList = new ArrayList<>(S.rotas.size());
        for (Rota r : S.rotas){
            rotaTestada = r;
            for (int i = 0; i < movements.size(); ) {
                movement = movements.get(i);
                novaRota = movement.execute(rotaTestada);
                if(novaRota.getCustoAtual() < rotaTestada.getCustoAtual()){
                    rotaTestada = novaRota;
                } else {
                    i++;
                }
            }
            novaRotaList.add(novaRota);
        }
        Solucao novaSolucao = new Solucao(novaRotaList);
        return novaSolucao;
    }
}
