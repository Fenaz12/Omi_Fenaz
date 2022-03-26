package omi;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Controller {
	@FXML private javafx.scene.control.Button closeButton;
	static RobotCards shuffler = new RobotCards();
	static Game trumpSelector = new Game();


	@FXML
	public void buttonSettings() {
		
		ArrayList<String> robot1Cards = shuffler.getRobot1();
		ArrayList<String> robot2Cards = shuffler.getRobot2();
		ArrayList<String> robot3Cards = shuffler.getRobot3();
		ArrayList<String> userCards = shuffler.getUser();
		
		
		List<String> firstJoin = Stream.concat(robot1Cards.stream(), robot2Cards.stream()).collect(Collectors.toList()); //Joining the shuffled cards to recreate the All cards
		List<String> secondJoin = Stream.concat(firstJoin.stream(), robot3Cards.stream()).collect(Collectors.toList());
		List<String> thirdJoinList = Stream.concat(secondJoin.stream(), userCards.stream()).collect(Collectors.toList());
		ArrayList<String> allCards = new ArrayList<>(thirdJoinList);
		
		//System.out.println(allCards);
		
		
		
		ArrayList<String> playingOrder = GameRunner.getplayingOrder();
		String firstPlayer = playingOrder.get(0);
		
	
		if(firstPlayer.equals("User") ) {
			System.out.println("User Chooses The Trump");
		}
		else if(firstPlayer.equals("Robot1") ) {
			System.out.println("Robot1 Chooses The Trump");
		}
		else if(firstPlayer.equals("Robot2") ) {
			System.out.println("Robot2 Chooses The Trump");
		}
		else if(firstPlayer.equals("Robot3") ) {
			System.out.println("Robot3 Chooses The Trump");
		}
		

		 
		trumpSelector.trumpSelectingRobot();
		trumpSelector.placeCard();
		
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	
		
	}
	
	public void showScore() {
		System.out.println("---ScoreBoard---");
	    try {
	        File reader = new File("scoreBoard.txt");
	        Scanner myReader = new Scanner(reader);
	        while (myReader.hasNextLine()) {
	          String data = myReader.nextLine();
	          System.out.println(data);
	        }
	        myReader.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
		
	}
	
	public void exitGame() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
		System.exit(0);
	}

}
