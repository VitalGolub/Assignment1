import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Assignment 1, Question 4
 * 
 * Program that creates a histogram from the letter occurances in a file
 * 
 * @author Vital Golub, 100664269
 *
 */
public class Question4 extends Application {
	
	//Hashmap to store occurrences of letters
	static HashMap<Character, Integer> values = new HashMap<Character, Integer>();
	Pane graphPane = new Pane();
	//Largest occurrence of a letter
	static int maxValue = 0;
	//Letters that we are keeping track of
	static List<Character> letters = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

	@Override
	public void start(Stage primaryStage) {
		//Create a new label, textfield and button for viewing a file
		Label fileNameLabel = new Label("Filename");
		fileNameLabel.setContentDisplay(ContentDisplay.RIGHT);
		TextField fileName = new TextField();
		//Make the textfield large
		fileName.setPrefColumnCount(38);
		Button viewButton = new Button("View");
		
		//Draw an empty histogram when the program is first run
		drawHistogram();
		
		//If the button is click, read through the file, fill the values and draw the histogram again
		viewButton.setOnAction(e -> {
			fillValues(fileName.getText());
			drawHistogram();
		});
		
		//Create a new HBox to go under the graph with
		HBox controlBox = new HBox(10);
		//Add view button as well as file label and textfield
		controlBox.getChildren().addAll(fileNameLabel, fileName, viewButton);
		
		//Create new BorderPane to organize panes
		BorderPane pane = new BorderPane();
		//Set graph as top
		pane.setTop(graphPane);
		//Set controls as bottom
		pane.setBottom(controlBox);
		BorderPane.setMargin(graphPane, new Insets(20));

		Scene scene = new Scene(pane);
		primaryStage.setTitle("Question4");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	/**
	 * Read through a file and fill the values Hashmap with letter occurrences and find which
	 * letter occurs most often
	 * 
	 * @param path file path to scan through
	 */
	public static void fillValues(String path) {
		//Create a file scanner and scan through file
		File file = new File(path.trim());
		if (file.exists()) {
			try {
				//For every line in the file
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine()) {
					//Read the line character by character
					for (char c : scanner.nextLine().toUpperCase().toCharArray()) {
						
						//If the character is not part of our predefined characters move to next character
						if(!letters.contains(c))
							continue;
						
						//Modify hashmap value if character is found
						if (values.containsKey(c)) {
							values.put(c, values.get(c) + 1);
						} else {
							values.put(c, 1);
						}
					}
				}
				
				//If the hashmap isn't empty, in case no letters found
				if (!values.isEmpty())
					//Find the maxValue in the hashmap using stream
					maxValue = values.entrySet().stream()
							.max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getValue();
				
				scanner.close();
			} catch (FileNotFoundException e) {
				System.out.println("File not found!");
			}

		}
	}
	
	/**
	 * Draw a histogram created from 26 rectangles
	 */
	public void drawHistogram() {
		//Clear previous instance of the histogram
		graphPane.getChildren().clear();
		
		//Loop through all the predefined letters
		for (int i = 0, x = 0; i < letters.size(); i++, x += 25) {
			//Define rectangle height based on the max height from the hashmap
			double rHeight = (double) values.getOrDefault(letters.get(i), 0) / maxValue * 400;
			Rectangle bar = new Rectangle(20, rHeight);
			bar.setX(x);
			bar.setY(400 - rHeight);
			bar.setFill(Color.WHITE);
			bar.setStroke(Color.BLACK);
			Text barLabel = new Text(letters.get(i) + "");
			barLabel.setX(x + 5);
			barLabel.setY(400 + 15);
			graphPane.getChildren().addAll(bar, barLabel);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
