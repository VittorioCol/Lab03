package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Model {
	FileReader file;
	List<String> dizionario=new ArrayList<String>();
	int counter;
	public Model() {
	}
	
	public List<String> leggiDizionario(String lingua, List<String> parole) {
		try
		{
			file = new FileReader("src/main/resources/" + lingua + ".txt"); 
			BufferedReader reader=new BufferedReader(file);
			String parola= reader.readLine();
			while(parola!=null) {
				dizionario.add(parola);
				parola= reader.readLine();
			}
			List<String> paroleErrate= new ArrayList<>();
			counter=0;
			for(String p:parole) {
	    		if(dizionario.contains(p)) {
	    			
	    		}else {
	    			paroleErrate.add(p);
	    			counter++;
	    		}
	    	}
			return paroleErrate;
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("Lingua non conosciuta");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println("Errore");
			e.printStackTrace();
			return null;
		}
	}

	public int getCounter() {
		return counter;
	}
	
}
