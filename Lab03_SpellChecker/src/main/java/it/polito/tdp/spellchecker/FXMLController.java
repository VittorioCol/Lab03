package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import it.polito.tdp.spellchecker.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> chooseTheLanguage;

    @FXML
    private TextArea frase;

    @FXML
    private Button spellCheck;

    @FXML
    private TextArea errori;

    @FXML
    private Label numeroErrori;

    @FXML
    private Button clearText;

    @FXML
    private Label tempoDiProcesso;

    @FXML
    void doChooseTheLanguage(ActionEvent event) {
        	if (chooseTheLanguage.getValue() !=null) {
        		
        		frase.setDisable(false);
    			errori.setDisable(false);
    			spellCheck.setDisable(false);
    			clearText.setDisable(false);
    			frase.clear();
    			errori.clear();
        		
        	}else {
        		
    			frase.setDisable(true);
    			errori.setDisable(true);
    			spellCheck.setDisable(true);
    			clearText.setDisable(true);
    			frase.setText("Seleziona una lingua!");
        		
        	}
    }

    @FXML
    void doClearText(ActionEvent event) {
    	frase.clear();;
    	errori.clear();;
    	numeroErrori.setText("numero errori");
    	tempoDiProcesso.setText("tempo di processo");
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	String fraseDaTradurre=frase.getText();
    	fraseDaTradurre=fraseDaTradurre.replaceAll("\n", " ");
    	fraseDaTradurre=fraseDaTradurre.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]", " ");
    	String lingua=chooseTheLanguage.getValue();
    	List<String> parole= new ArrayList<>();
    	StringTokenizer st= new StringTokenizer(fraseDaTradurre, " ");
    	while (st.hasMoreTokens()) {
            parole.add(st.nextToken());
        }
    	List<String> paroleErrate=model.leggiDizionario(lingua,parole);
    	for(String pe:paroleErrate)
    	{
    		errori.setText(pe+"\n");
    	}
    	numeroErrori.setText("Errori: "+ model.getCounter());
    }

    @FXML
    void initialize() {
        assert chooseTheLanguage != null : "fx:id=\"chooseTheLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert frase != null : "fx:id=\"frase\" was not injected: check your FXML file 'Scene.fxml'.";
        assert spellCheck != null : "fx:id=\"spellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert errori != null : "fx:id=\"errori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert numeroErrori != null : "fx:id=\"numeroErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clearText != null : "fx:id=\"clearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tempoDiProcesso != null : "fx:id=\"tempoDiProcesso\" was not injected: check your FXML file 'Scene.fxml'.";
    }

	public void setModel(Model model) {
		
		frase.setDisable(true);
    	frase.setText("Selezionare una lingua");
    	
    	errori.setDisable(true);
    	chooseTheLanguage.getItems().addAll("English","Italian");
    	
    	spellCheck.setDisable(true);
    	clearText.setDisable(true);
 	
    	
    	this.model = model;
    }
    
}



