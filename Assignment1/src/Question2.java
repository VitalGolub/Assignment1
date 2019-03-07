import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Assignment 1, Question 2
 * 
 * Program  that allows use to enter their investment, the number of years
 * And the interest rate and returns the future value
 * 
 * @author Vital Golub, 100664269
 *
 */
public class Question2 extends Application {

	@Override
	public void start(Stage primaryStage) {
		// Create 4 new text fields
		TextField investment = new TextField();
		TextField years = new TextField();
		TextField interestRate = new TextField();
		TextField futureValue = new TextField();
		// Make sure Future Value field is light gray and uneditable
		futureValue.setStyle("-fx-background-color: LIGHTGRAY;");
		futureValue.setEditable(false);

		// Create new calculate button
		Button calcButton = new Button("Calculate");

		GridPane pane = new GridPane();
		pane.setHgap(10);

		// Add 4 new labels into the 0th column
		pane.addColumn(0, new Label("Investment Amount"), new Label("Number of Years"),
				new Label("Annual Interest Rate"), new Label("Future value"));

		// Add the 4 textfields and button into the 1th column
		pane.addColumn(1, investment, years, interestRate, futureValue, calcButton);

		// Adjust alignment of text and button
		investment.setAlignment(Pos.BOTTOM_RIGHT);
		years.setAlignment(Pos.BOTTOM_RIGHT);
		interestRate.setAlignment(Pos.BOTTOM_RIGHT);
		futureValue.setAlignment(Pos.BOTTOM_RIGHT);
		GridPane.setHalignment(calcButton, HPos.RIGHT);
		
		//Create button event to execute future value formula and add it to futureValue textfield
		calcButton.setOnAction(e -> {
			double future = Double.parseDouble(investment.getText())
					* Math.pow((1 + (Double.parseDouble(interestRate.getText()) / 1200)),
							(Integer.parseInt(years.getText()) * 12));

			futureValue.setText(String.format("%.2f", future));
		});
		
		//Add small padding to match format on assignment
		pane.setPadding(new Insets(10, 10, 10, 10));
		
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Question2");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}