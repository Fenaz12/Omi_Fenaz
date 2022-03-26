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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameRunner extends Application {
	
	@Override
	public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);

 Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	
	static Robot shuffler = new Robot();	
	
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
	static TrumpSelector trumpSelector = new TrumpSelector();
	
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
		while(true) {
		System.out.println();
		System.out.println("OMI GAME");
		System.out.println();
		
		System.out.println("1 - Start the game \n2 - Show ScoreBoard\n3 - Exit");
		Scanner myObj = new Scanner(System.in);
		int intOption = 0;
		String goBack = null;


		while(true) {
			System.out.print("Select An Option : ");
			String option = myObj.next();	
			try {
				intOption = Integer.parseInt(option);
			}catch(NumberFormatException e) {
			}
			if(!(intOption > 0 && intOption < 4)) {
				System.out.println("Please enter a valid option");
				continue;
			}
			break;
		}
	

		if(intOption == 1) {
			
		ArrayList<String> robot1Cards = shuffler.getRobot1();
		ArrayList<String> robot2Cards = shuffler.getRobot2();
		ArrayList<String> robot3Cards = shuffler.getRobot3();
		ArrayList<String> userCards = shuffler.getUser();
			
		List<String> firstJoin = Stream.concat(robot1Cards.stream(), robot2Cards.stream()).collect(Collectors.toList()); //Joining the shuffled cards to recreate the All cards
		List<String> secondJoin = Stream.concat(firstJoin.stream(), robot3Cards.stream()).collect(Collectors.toList());
		List<String> thirdJoinList = Stream.concat(secondJoin.stream(), userCards.stream()).collect(Collectors.toList());
		ArrayList<String> allCards = new ArrayList<>(thirdJoinList);
		
		System.out.println(allCards);
		
		
		
		ArrayList<String>  playingOrder = getplayingOrder();
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

		}
	
		else if(intOption == 2) {
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
		    System.out.println();
			System.out.print("Do you want to go back to menu ?  (y/n) :");

			while(true) {
				goBack = myObj.nextLine();
				if(!(goBack.equals("y") || goBack.equals("n"))){
					continue;
				}
				break;
			}
			if(goBack.equals("y")) {
				continue;
			}
			else {
				System.exit(0);
			}
		}
		
		else if(intOption ==3) {
			System.out.println("Exiting the Game");
			System.exit(0);

		}
	
	}
	}
}

