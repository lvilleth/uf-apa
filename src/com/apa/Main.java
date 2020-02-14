package com.apa;

import com.apa.algoritm.AlgoritmoVizinhoProximo;
import com.apa.model.ProblemInfo;
import com.apa.util.FileReader;

public class Main {

    static final String RESOURCE_FOLDER = "/resources/";

    public static void main(String[] args) {
	    String filename = "P-n16-k8.txt";
	    String filepath = Main.class.getResource(RESOURCE_FOLDER.concat(filename)).getPath();

	    try {
			ProblemInfo problemInfo = FileReader.readScannerToInfo(filepath);
            AlgoritmoVizinhoProximo algoritmoVizinhoProximo = new AlgoritmoVizinhoProximo(problemInfo);
            algoritmoVizinhoProximo.process();
        } catch (Exception e){
	        e.printStackTrace();
        }

    }
}
