package stavrinos.sek;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ScoreCalculator {

	@FXML TextField time;
	@FXML TextField obstacles;
	@FXML TextField score;
	
	@FXML
	public void calculate(){
		int timeNum = Integer.parseInt(time.getText());
		int obstaclesNum = Integer.parseInt(obstacles.getText());
		score.setText(40000 - (timeNum+obstaclesNum*1000)+"");
	}

}
