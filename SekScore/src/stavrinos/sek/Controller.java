package stavrinos.sek;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import stavrinos.sek.helpers.Row;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller implements Initializable{

	private ObservableList<Row> data = FXCollections.observableArrayList();
	private boolean firstTime = true;
	public static final Semaphore available = new Semaphore(1);
	
	@FXML private ImageView img;
    @FXML private TableView<Row> tableView;
    @FXML private TableColumn<Row, String> UserName;
    @FXML private TableColumn<Row, Integer> Score;
    @FXML private TableColumn<Row, String> Time;
    @FXML private TableColumn<Row, Integer> Obstacles;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	img.setImage(new Image(new File("sek.png").toURI().toString()));
    	UserName.setCellValueFactory(new PropertyValueFactory<Row, String>("name"));
    	Score.setCellValueFactory(new PropertyValueFactory<Row, Integer>("score"));
    	Time.setCellValueFactory(new PropertyValueFactory<Row, String>("time"));
    	Obstacles.setCellValueFactory(new PropertyValueFactory<Row, Integer>("obstacles"));
        Score.setSortType(TableColumn.SortType.DESCENDING);
        tableView.setItems(data);
        new Thread() {
            public void run() {
                try {
                    while(true){
                    	available.acquire();
                    	if(!firstTime){
                    		data.clear();
                    		BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
                    		String dataInString = null;
                    		while ((dataInString = reader.readLine()) != null) {
                    			String[] s = dataInString.split("\\s+");
                    			if(s.length == 5){
                    				data.add(new Row(s[0] + " " + s[1],Integer.parseInt(s[2]),s[3],Integer.parseInt(s[4])));
                    			}	
                    		}
                    		Platform.runLater(new Runnable() {
                    			@Override
                    			public void run() {
                    				tableView.setItems(data);
                    				if(data.size()>0){
                    					tableView.getSortOrder().add(Score);
                    				}
                    			}
                    		});
                    		reader.close();
                    	}
                    	else{
                    		firstTime = false;
                    	}
                    }
                } 
                catch(Exception e) {
                    System.out.println("Controller\n"+e);
                }
            }  
        }.start();
    }
    
    
}
