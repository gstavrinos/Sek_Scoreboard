package stavrinos.sek;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
	
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        stage.setTitle("The Sek Challenge");
        stage.setFullScreen(true);
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
        Parent root2 = FXMLLoader.load(getClass().getResource("editor.fxml"));
        Stage stage2 = new Stage();
        stage2.setTitle("Editor");
        stage2.setScene(new Scene(root2, 300, 275));
        stage2.setResizable(false);
        stage2.setMaxHeight(489.0);
        stage2.setMaxWidth(525.0);
        stage2.setMinHeight(489.0);
        stage2.setMinWidth(525.0);
        stage2.show();
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        
        stage2.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
    
}
