package omi;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameRunner extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        
        primaryStage.setTitle("Omi Game by Fenaz");
        primaryStage.setScene(new Scene(root, 640, 400));
        primaryStage.show();
    }

	
	static RobotCards shuffler = new RobotCards();	
	
	public ArrayList<String> robot1First4Cards;
	public ArrayList<String> robot2First4Cards;
	public ArrayList<String> robot3First4Cards;
	public ArrayList<String> userFirst4Cards;
	public static ArrayList<String> playingOrder;
	public ArrayList<String> robot1Cards;
	public ArrayList<String> robot2Cards;
	public ArrayList<String> robot3Cards;
	public ArrayList<String> userCards;

	
	public GameRunner(){
		
		this.robot1First4Cards = robot1First4CardsMethod();
		this.robot2First4Cards = robot2First4CardsMethod();
		this.robot3First4Cards = robot3First4CardsMethod();
		this.userFirst4Cards   = userFirst4CardsMethod();
		this.playingOrder = firstPlayerMethod();
		this.robot1Cards = robot1CardsMethod();
		this.robot2Cards = robot2CardsMethod();
		this.robot3Cards = robot3CardsMethod();
		this.userCards = userCardsMethod();
		
	}
	static Game trumpSelector = new Game();
	
	  public ArrayList<String> getrobot1First4Cards() {
		    return robot1First4Cards;
		  }
	  public ArrayList<String> getrobot2First4Cards() {
		    return robot2First4Cards;
		  }
	  public ArrayList<String> getrobot3First4Cards() {
		    return robot3First4Cards;
		  }
	  public ArrayList<String> getuserFirst4Cards() {
		    return userFirst4Cards;
		  }
	  public static ArrayList<String> getplayingOrder() {
		  return playingOrder;
	  }
	  
	  public ArrayList<String> getrobot1Cards() {
		    return robot1Cards;
	  }
	  public ArrayList<String> getrobot2Cards() {
		    return robot2Cards;
	  }
	  public ArrayList<String> getrobot3Cards() {
		    return robot3Cards;
	  }
	  public ArrayList<String> getUserCards() {
		    return userCards;
	  }


	  public ArrayList<String> robot1CardsMethod (){
		 ArrayList<String> robot1Cards = shuffler.getRobot1();
		 return robot1Cards;
	 }
	 
	 public ArrayList<String> robot2CardsMethod (){
		 ArrayList<String> robot2Cards = shuffler.getRobot2();
		 return robot2Cards;
	 }
	 
	 public ArrayList<String> robot3CardsMethod (){
		 ArrayList<String> robot3Cards = shuffler.getRobot3();
		 return robot3Cards;
	 }
	 
	 public ArrayList<String> userCardsMethod (){
		 ArrayList<String> userCards = shuffler.getUser();
		 return userCards;
	 }
	
	
	 
	public static ArrayList<String> firstPlayerMethod() {
		List<String> playingOrderList = List.of("User","Robot1","Robot2","Robot3");
		ArrayList<String> playingOrder = new ArrayList<>(playingOrderList);
		Collections.shuffle(playingOrder);
		return playingOrder;
	}
		
		
		
		public ArrayList<String> robot1First4CardsMethod(){
			List<String> robot1First4CardsList = robot1CardsMethod().subList(0, 4);
			ArrayList<String> robot1First4Cards = new ArrayList<>(robot1First4CardsList);
			return robot1First4Cards;
		}
		
		public ArrayList<String> robot2First4CardsMethod(){
			List<String> robot2First4CardsList = robot2CardsMethod().subList(0, 4);
			ArrayList<String> robot2First4Cards = new ArrayList<>(robot2First4CardsList);
			return robot2First4Cards;
		}
		
		public ArrayList<String> robot3First4CardsMethod(){
			List<String> robot3First4CardsList = robot3CardsMethod().subList(0, 4);
			ArrayList<String> robot3First4Cards = new ArrayList<>(robot3First4CardsList);
			return robot3First4Cards;
		}
		
		public ArrayList<String> userFirst4CardsMethod(){
			List<String> userFirst4CardsList = userCardsMethod().subList(0, 4);
			ArrayList<String> userFirst4Cards = new ArrayList<>(userFirst4CardsList);
			return userFirst4Cards;
		}


	public static void main(String[] args) {
		launch(args);

	}
}

