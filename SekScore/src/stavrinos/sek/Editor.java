package stavrinos.sek;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Editor implements Initializable {
	
	@FXML TextArea text;
	@FXML Button submit;
	@FXML Label message;
		
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		message.setText("Input Format: Name Surname Score Time(mm:ss:ff) Obstacles");
    }
	
	@FXML
	public void sendData(){
		String dataInString = text.getText();
		try {
			boolean error = false;
			Scanner sc = new Scanner(dataInString);
			String st = null;
			while (sc.hasNextLine()) {
				st = sc.nextLine();
    			String[] s = st.split("\\s+");
    			if(s.length != 5){
    				error = true;
    				message.setText("Error on your input! Check that every line consists of 5 items!");
    			}	
    		}
			if(!error){
				PrintWriter writer = new PrintWriter("scores.txt", "UTF-8");
		   		writer.println(dataInString);
				writer.close();
				Controller.available.release();
				message.setText("Data was added successfully!");
			}
			sc.close();
		} 
		catch (Exception e) {
			message.setText(e.toString());
		}
	}
	
	@FXML
	public void saveCopy(){
		File savedScores = new File("savedScores.txt");
		int i = 0;
		while(savedScores.exists()){
			i++;
			savedScores = new File("savedScores"+i+".txt");
		}
		try {
			PrintWriter out = new PrintWriter(new FileWriter(savedScores));
			out.write(text.getText());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void openCalculator(){
		Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("calculator.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Score Calculator");
            stage.setScene(new Scene(root, 450, 450));
            stage.setResizable(false);
            stage.setMaxHeight(250.0);
            stage.setMaxWidth(293);
            stage.setMinHeight(250.0);
            stage.setMinWidth(293);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
