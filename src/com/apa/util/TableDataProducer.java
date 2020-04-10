package com.apa.util;

import com.apa.algoritm.AlgoritmoVizinhoProximo;
import com.apa.algoritm.VND;
import com.apa.model.ProblemInfo;
import com.apa.model.Solucao;
import com.apa.model.TableData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;

public class TableDataProducer {

    private final String RESOURCE_PATH = "/resources";
    private final int N_EXECUCOES = 10;
    private final HashMap<String, Float> otimoValues = new OtimoValueMap();

    public void run(){
        Queue<String> fileNames = readData();

        while (fileNames.size() > 0){
            String filename = fileNames.remove();
            ProblemInfo instancia = readFile(filename);

            TableData dataConstr = produceTableData(instancia, this::heuristicaConstrutivista);
            TableData dataVND = produceTableData(instancia, this::heuristicaConstrutivistaAndVND);

            System.out.print("Heur. Constr.: ");
            System.out.println(dataConstr);
            System.out.print("Heur. Constr. e VND: ");
            System.out.println(dataVND);
            System.out.println();
        }

    }

    private Solucao heuristicaConstrutivista(ProblemInfo problemInfo){
        AlgoritmoVizinhoProximo algoritmoVizinhoProximo = new AlgoritmoVizinhoProximo(problemInfo);
        return algoritmoVizinhoProximo.process();
    }

    private Solucao heuristicaConstrutivistaAndVND(ProblemInfo problemInfo){
        VND vnd = new VND(problemInfo);
        return vnd.execute(heuristicaConstrutivista(problemInfo));
    }

    private ProblemInfo readFile(String filename){
        InputStream fileStream = filename.getClass().getResourceAsStream(RESOURCE_PATH + "/" + filename);
        return FileReader.readFileToInfo(fileStream);
    }

    private TableData produceTableData(ProblemInfo instancia, Function<ProblemInfo, Solucao> funcao){
        // 10 execucoes para cada instancia
        TableData data = new TableData();
        float somatempoExecucao = 0;
        long antes;
        float mediaTempo;
        float melhorSolucao = -1;
        int solucaoAtual;
        float somaSolucao = 0;
        float mediaSolucao;
        for (int i = 0; i < N_EXECUCOES; i++) {
            antes = System.currentTimeMillis();
            Solucao s = funcao.apply(instancia);
            somatempoExecucao += System.currentTimeMillis() - antes;

            solucaoAtual = s.custoTotal;
            melhorSolucao = Math.max(solucaoAtual, melhorSolucao);
            somaSolucao += solucaoAtual;
        }
        mediaTempo = somatempoExecucao / N_EXECUCOES;
        mediaSolucao = somaSolucao / N_EXECUCOES;
        data.setMediaTempo(mediaTempo);
        data.setMediaSolucao(mediaSolucao);
        data.setMelhorSolucao(melhorSolucao);
        data.setOtimo(otimoValues.get(instancia.name));

        return data;
    }

    private Queue<String> readData(){
        Queue<String> filenames = new LinkedList<>();

        try(InputStream fileStream = this.getClass().getResourceAsStream(RESOURCE_PATH);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileStream))){
            String file ;
            while ((file = br.readLine()) != null){
                filenames.add(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filenames;
    }



}
