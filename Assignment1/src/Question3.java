
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;;
/**
 * Assignment 1, Question 3
 * 
 * Program that create a triangle and a circle within it, the points of the triangle
 * Can be dragged around the circle to display a new triangle with angles
 * 
 * @author Vital Golub, 100664269
 *
 */
public class Question3 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Create new pane and scene size variables
		Pane pane = new Pane();
		double width = 400;
		double height = 400;
		
		//Create main circle in the center of the screen
		Circle circle = new Circle(width / 2, height / 2, 100);
		pane.getChildren().add(circle);

		circle.setFill(Color.TRANSPARENT);
		circle.setStroke(Color.BLACK);
		
		//Create 3 arrays to keep track of lines, points, and the angles
		Circle[] points = new Circle[3];
		Line[] line = new Line[3];
		Text[] angles = new Text[3];
		
		//Loop through all 3 arrays and define inital values
		for (int i = 0; i < points.length; i++) {
			//Lines and angles both have empty Line and Text objects respectively
			line[i] = new Line();
			angles[i] = new Text();
			
			//Points need to be created as small red circles
			points[i] = new Circle(0, 0, 5);
			points[i].setFill(Color.RED);
			points[i].setStroke(Color.RED);
			
			//Place points in random spots on the circle to start
			setRandomLocation(points[i], circle);
			
			//Create effectively final index to be used in lamba function
			final int index = i;

			points[i].setOnMouseDragged(e -> {
				//Convert to polar coordinates, find the angle
				double radians = Math.atan2(e.getY() - circle.getCenterY(), e.getX() - circle.getCenterX());
				
				//Convert found angle to new components with our circle radius
				double x = circle.getCenterX() + circle.getRadius() * Math.cos(radians);
				double y = circle.getCenterY() + circle.getRadius() * Math.sin(radians);
				
				//Update point locations
				points[index].setCenterX(x);
				points[index].setCenterY(y);
				
				//Update line locations
				updateLineLocations(line, points, angles);
			});
		}
		
		//Update line locations after initial points are chosen
		updateLineLocations(line, points, angles);
		
		//Add all nodes to pane
		pane.getChildren().addAll(line);
		pane.getChildren().addAll(angles);
		pane.getChildren().addAll(points);
		primaryStage.setScene(new Scene(pane, width, height));
		primaryStage.setTitle("Question3");
		primaryStage.show();
	}
	
	/**
	 * 
	 * Function that connects the points with lines and displays current angles
	 * 
	 * @param lines the lines that are being modified
	 * @param points the points that are given
	 * @param angles the text displaying the angles that is being modified
	 */
	private void updateLineLocations(Line[] lines, Circle[] points, Text[] angles) {
		//Connect the points with lines
		lines[0].setStartX(points[0].getCenterX());
		lines[0].setStartY(points[0].getCenterY());
		lines[0].setEndX(points[1].getCenterX());
		lines[0].setEndY(points[1].getCenterY());
		lines[1].setStartX(points[0].getCenterX());
		lines[1].setStartY(points[0].getCenterY());
		lines[1].setEndX(points[2].getCenterX());
		lines[1].setEndY(points[2].getCenterY());
		lines[2].setStartX(points[1].getCenterX());
		lines[2].setStartY(points[1].getCenterY());
		lines[2].setEndX(points[2].getCenterX());
		lines[2].setEndY(points[2].getCenterY());

		//For loop that updates the angle text fields 
		for (int i = 0; i < lines.length; i++) {
			angles[i].setX(points[i].getCenterX() + 5);
			angles[i].setY(points[i].getCenterY() + 5);
			
			//Calculate length of lines for formula
			double a = calcLineLength(lines[0]);
			double b = calcLineLength(lines[1]);
			double c = calcLineLength(lines[2]);

			double A = Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
			angles[2].setText(String.format("%.2f", A));

			double B = Math.toDegrees(Math.acos((b * b - a * a - c * c) / (-2 * a * c)));
			angles[0].setText(String.format("%.2f", B));

			double C = Math.toDegrees(Math.acos((c * c - b * b - a * a) / (-2 * a * b)));
			angles[1].setText(String.format("%.2f", C));

		}
	}
	
	/**
	 * 
	 * Function that selects a random location on a circle for a point to be placed
	 * 
	 * @param point the random point being selected
	 * @param circle the circle which the point has to lie on
	 */
	public void setRandomLocation(Circle point, Circle circle) {
		double angle = Math.random() * 360;
		double x = circle.getCenterX() + circle.getRadius() * Math.cos(Math.toRadians(angle));
		double y = circle.getCenterY() + circle.getRadius() * Math.sin(Math.toRadians(angle));

		point.setCenterX(x);
		point.setCenterY(y);
	}
	
	/**
	 * Calculates the length of a given line
	 * 
	 * @param line given line object
	 * @return length of the line
	 */
	private static double calcLineLength(Line line) {
		return Math.sqrt((line.getStartX() - line.getEndX()) * (line.getStartX() - line.getEndX())
				+ (line.getStartY() - line.getEndY()) * (line.getStartY() - line.getEndY()));
	}

	public static void main(String[] args) {
		launch(args);
	}

}
