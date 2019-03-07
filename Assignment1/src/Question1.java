import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 * Assignment 1, Question 1
 * 
 * Program that displays 3 random cards from the image/card folder
 * 
 * @author Vital Golub, 100664269
 *
 */
public class Question1 extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		//Create new HBox
		HBox pane = new HBox();
		pane.setAlignment(Pos.TOP_LEFT);
		
		//Add 3 ImageView nodes of random cards from 1 to 54
		pane.getChildren().addAll(new ImageView("file:image/card/" + (int) (Math.random() * 54 + 1) + ".png"),
				new ImageView("file:image/card/" + (int) (Math.random() * 54 + 1) + ".png"),
				new ImageView("file:image/card/" + (int) (Math.random() * 54 + 1) + ".png"));

		Scene scene = new Scene(pane);
		primaryStage.setTitle("Question1");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}