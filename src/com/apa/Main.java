package com.apa;

import com.apa.algoritm.AlgoritmoVizinhoProximo;
import com.apa.algoritm.VND;
import com.apa.model.ProblemInfo;
import com.apa.model.Solucao;
import com.apa.util.FileReader;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {

    static final String RESOURCE_FOLDER = "/resources/";
    static final String DEFAULT_FILENAME = "P-n45-k5.txt";

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

            VND vnd = new VND(problemInfo);
            Solucao novaSolucao = vnd.execute(S);
            System.out.println("--------- NOVA SOLUCAO -----------------");
            System.out.println(novaSolucao);

        } catch (FileNotFoundException e){
	        System.out.println("Arquivo nao encontrado: "+ filename);
        } catch (Exception e){
	        e.printStackTrace();
        }

    }
}
