package com.apa;

import com.apa.algoritm.AlgoritmoVizinhoProximo;
import com.apa.model.ProblemInfo;
import com.apa.model.Rota;
import com.apa.model.Solucao;
import com.apa.movement.DoisOpt;
import com.apa.movement.Insertion;
import com.apa.movement.Swap;
import com.apa.util.FileReader;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {

    static final String RESOURCE_FOLDER = "/resources/";
    static final String DEFAULT_FILENAME = "P-n55-k7.txt";

    public static void main(String[] args) {
	    String filename;
	    boolean fileFromSystem = false;

	    if(args.length == 0){
	        filename = RESOURCE_FOLDER.concat(DEFAULT_FILENAME);
        }else {
	        filename = args[0];
	        fileFromSystem = true;
        }

	    try {
            ProblemInfo problemInfo;

            if(fileFromSystem){
	            problemInfo = FileReader.readFileToInfo(filename);
            }else {
                InputStream fileStream = filename.getClass().getResourceAsStream(filename);
                problemInfo = FileReader.readFileToInfo(fileStream);
            }

            AlgoritmoVizinhoProximo algoritmoVizinhoProximo = new AlgoritmoVizinhoProximo(problemInfo);
            Solucao S = algoritmoVizinhoProximo.process();

            System.out.println("------------------------------------------------------------");

            DoisOpt opt2 = new DoisOpt(problemInfo);
            Insertion insertion = new Insertion(problemInfo);
            Swap swap = new Swap(problemInfo);
            for (Rota r : S.rotas){
                System.out.println("Original: "+ r);
                System.out.println("Swap:" + swap.execute(r));
                System.out.println("2 - OPT:" + opt2.execute(r));
                System.out.println("Insertion:" + insertion.execute(r));
                System.out.println("--- ###### --- \n");
            }

        } catch (FileNotFoundException e){
	        System.out.println("Arquivo nao encontrado: "+ filename);
        } catch (Exception e){
	        e.printStackTrace();
        }

    }
}
