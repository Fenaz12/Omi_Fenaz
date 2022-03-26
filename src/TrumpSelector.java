import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Scanner;

public class TrumpSelector {
	public String player;
	private static  String trump;
	GameRunner gameRunner = new GameRunner();
	Robot shuffler = new Robot();
	Random randomizer = new Random();
	Scanner myObj = new Scanner(System.in);
	
	
	
	ArrayList<String >robot1First4Cards = gameRunner.getrobot1First4Cards();
	ArrayList<String >robot2First4Cards = gameRunner.getrobot2First4Cards();
	ArrayList<String >robot3First4Cards = gameRunner.getrobot3First4Cards();
	ArrayList<String >userFirst4Cards = gameRunner.getuserFirst4Cards();
	
	ArrayList<String> robot1Cards = gameRunner.getrobot1Cards();
	ArrayList<String> robot2Cards = gameRunner.getrobot2Cards();
	ArrayList<String> robot3Cards = gameRunner.getrobot3Cards();
	ArrayList<String> userCards = gameRunner.getUserCards();
	
	static String enterName = null;
	public static String enterName() {
			Scanner myObj = new Scanner(System.in);
			while(true) {
			System.out.print("Enter Your Name to Start the Game : ");
			enterName = myObj.nextLine();		
				if(enterName.isBlank()) {
					System.out.println("Invalid Name, Enter Again");
					continue;
				}
			break;
			}
		
		return enterName;	
	 }	
	
	public static ArrayList<String> allNumbersList(ArrayList<String> listToChange) {
		listToChange.replaceAll(s -> s.equals("K") ? "13" : s);
		listToChange.replaceAll(s -> s.equals("Q") ? "12" : s);
		listToChange.replaceAll(s -> s.equals("J") ? "11" : s);
		listToChange.replaceAll(s -> s.equals("A") ? "14" : s);
		return listToChange;
	}
	
	public static String allLettersString(String stringToChange) {
	    String changedString1 = stringToChange.replace("11","J");
	    String changedString2 = changedString1.replace("12","Q");
	    String changedString3 = changedString2.replace("13","K");
	    String changeString = changedString3.replace("14","A");
		return changeString;
	}
	
	public static String separateString(String stringToSeperate) {
		String trumpSlectingCardsString = stringToSeperate.replaceAll(",", "");
		ArrayList<String> speratedList = new ArrayList<String>(Arrays.asList(trumpSlectingCardsString.split(" ")));
		String sepratedString =  speratedList.get(0);
		return sepratedString;
	}
	public static ArrayList<String> sperateLevelsAndSuits(ArrayList<String> listToChange){		
		String listString = String.join(" ", listToChange);
		String trumpSlectingCardsString = listString.replaceAll(",", "");
		List<String> speratedList = new ArrayList<String>(Arrays.asList(trumpSlectingCardsString.split(" ")));
		for(int i = 0;i < speratedList.size();i++) {
			if (speratedList.get(i).contains("-")) {
				speratedList.remove(i);
			}
		} 
		return (ArrayList<String>) speratedList;
	}
	
	public static ArrayList<Integer> createListOfLevels(ArrayList<String> listToChange){			
		ArrayList<Integer> listOfLevels = new ArrayList<Integer>();      //Creating list of levels
		for (int i = 1; i < listToChange.size(); i += 2) {
			int cardOfLevel = Integer.parseInt(listToChange.get(i)); 
			listOfLevels.add(cardOfLevel);
		}
		return listOfLevels;
	}

	public static ArrayList<String> createListOfSuits(ArrayList<String> listToChange){			
		ArrayList<String> listOfSuits = new ArrayList<String>();      //Creating list of Suits
		for (int i = 0; i < listToChange.size(); i += 2) {
			String cardOfLevel = listToChange.get(i); 
			listOfSuits.add(cardOfLevel);
		}
		return listOfSuits;
	}
	
	public String findMaximumLevelCard(ArrayList<Integer>integerList,ArrayList<String>suitList) {
		int maximumLevel = Collections.max(integerList);
		int maximumLevelIndex = integerList.indexOf(maximumLevel);
		String  maximumSuit = suitList.get(maximumLevelIndex);		
		String placingCard = maximumSuit + " - " + maximumLevel;
		return placingCard;
	}
	
	public String findMaximumSuit(ArrayList<Integer>integerList,ArrayList<String>suitList) {
		int maximumLevel = Collections.max(integerList);
		int maximumLevelIndex = integerList.indexOf(maximumLevel);
		String  maximumSuit = suitList.get(maximumLevelIndex);		
		return maximumSuit;
	}
	
	public ArrayList<String> removeLastTrickCard(ArrayList<String> lastTrickCardList,String LastCard){
		ArrayList<String> removedLastTrickCard = new ArrayList<String>(lastTrickCardList);
		for(int i = 0;i < removedLastTrickCard.size();i++) {
			if (removedLastTrickCard.get(i).contains(LastCard)) {
				removedLastTrickCard.remove(i);
			}
		} 
		return removedLastTrickCard;
	}
	
	public boolean isSameSuitExist(ArrayList<String>removedLastTrickCard, String maximumSuit) {
		boolean isSameSuitExists = true;
		for(int i = 0;i < removedLastTrickCard.size();i++) {
			if (removedLastTrickCard.get(i).contains(maximumSuit)) {		
				isSameSuitExists = true;
				break;
			}
			else {
				isSameSuitExists = false;
			}
		} 
		return isSameSuitExists;
	}
	
	public ArrayList<String> createSameSuitList(ArrayList<String>removedLastTrickCard,String maximumSuit){
		ArrayList<String> placingCardsWithSameSuit = new ArrayList<String>();  
		for (int i = 0; i < removedLastTrickCard.size(); i ++) {
			if (removedLastTrickCard.get(i).contains(maximumSuit)) {
				placingCardsWithSameSuit.add(removedLastTrickCard.get(i));
			}
		}
		return placingCardsWithSameSuit;
	}
	
	 public ArrayList<String> makeTrumpList(ArrayList<String >listToget, String trump){
		ArrayList<String> cardsWithTrump = new ArrayList<String>();  
		for(int i = 0; i < listToget.size(); i++) {
			if(listToget.get(i).contains(trump)) {
				cardsWithTrump.add(listToget.get(i));	
			}
		}
		return cardsWithTrump; 
	 }
	
	ArrayList<String> playingOrder = GameRunner.getplayingOrder();
	String firstPlayer = playingOrder.get(0);
	int pointsUser = 0;
	int pointsRobot1 = 0;
	int pointsRobot2 = 0;
	int pointsRobot3 = 0;
	String results = "NotTied";
	String finalWinner = "NotUser";
	String finalResults = null;

	public void trumpSelectingRobot() {
		
		
		ArrayList<String> trumpSelectingCards = new ArrayList<String>();
		String trumpSelector = null;
		
		if(firstPlayer.equals("User") ) {
			 trumpSelector = "User";
			trumpSelectingCards = new ArrayList<String>(userFirst4Cards);
			System.out.println("First Four Cards : " + userFirst4Cards);
		}
		else if(firstPlayer.equals("Robot1") ) {
			 trumpSelector = "Robot1";
			trumpSelectingCards = new ArrayList<String>(robot1First4Cards);
		}
		else if(firstPlayer.equals("Robot2") ) {
			 trumpSelector = "Robot2";
			trumpSelectingCards = new ArrayList<String>(robot2First4Cards);
		}
		else if(firstPlayer.equals("Robot3") ) {
			 trumpSelector = "Robot3";
			trumpSelectingCards = new ArrayList<String>(robot3First4Cards);
		}
		
		
		String listString = String.join(" ", trumpSelectingCards);
		String trumpSlectingCardsString = listString.replaceAll(",", "");

		List<String> cardFinalList = new ArrayList<String>(Arrays.asList(trumpSlectingCardsString.split(" ")));
		//System.out.println(cardFinalList + "myList");
		
		int countClub=Collections.frequency(cardFinalList, "Club");
		int countDiamond=Collections.frequency(cardFinalList, "Diamond");
		int countHeart=Collections.frequency(cardFinalList, "Heart");
		int countSpade=Collections.frequency(cardFinalList, "Spade");
		
		
		
		for(int i = 0;i < cardFinalList.size();i++) {
			if (cardFinalList.get(i).contains("-")) {
				cardFinalList.remove(i);
			}
		} 
		
		ArrayList<String> myArraList = new ArrayList<>(cardFinalList);  //Changing Letter in level to Numbers (K--> 13)
		ArrayList<String> cardAllNumberList = allNumbersList(myArraList);
		
		ArrayList<Integer> integerList = new ArrayList<Integer>();      //Creating list of levels
		for (int i = 1; i < cardAllNumberList.size(); i += 2) {
			int cardNumberFromList = Integer.parseInt(cardAllNumberList.get(i)); 
			integerList.add(cardNumberFromList);
		}
		
		ArrayList<String> suitList = new ArrayList<String>();      //Creating list of levels
		for (int i = 0; i < cardAllNumberList.size(); i += 2) {
			suitList.add(cardAllNumberList.get(i));
		}

		Map<String,Integer> map = Map.of("Club",countClub,"Diamond", countDiamond, "Heart",countHeart,"Spade",countSpade);
		Map<String,Integer> countMap = new HashMap<>(map);
		
		if (countMap.containsValue(2)) {
			if(countMap.containsValue(1)){
				for(Entry<String, Integer> entry: countMap.entrySet()) {
					if(entry.getValue() == 2) {
						trump = entry.getKey();
						//System.out.println("Trump Suit is : " +trump);
				        break;
					}
				}
			}
			else {
				if (cardFinalList.contains("A")) { 												//Removing Ace if it contains Ace
					ArrayList<String> removedAce = new ArrayList<>(cardFinalList);
					for(int i = 0;i < removedAce.size();i++) {
						if (removedAce.get(i).contains("A")) {
							removedAce.remove(i);
							removedAce.remove(i-1);
						}
					}
					
					int countClubAce=Collections.frequency(removedAce, "Club");  							 //Counting suits
					int countDiamondAce=Collections.frequency(removedAce, "Diamond");
					int countHeartAce=Collections.frequency(removedAce, "Heart");
					int countSpadeAce=Collections.frequency(removedAce, "Spade");
					//System.out.println(removedAce + "Removed Ace");
					
					Map<String,Integer> mapRemovedAce = Map.of("Club",countClubAce,"Diamond", countDiamondAce, "Heart",countHeartAce,"Spade",countSpadeAce);
					Map<String,Integer> countMapremovedAce = new HashMap<>(mapRemovedAce);
					
					for(Entry<String, Integer> entry: countMapremovedAce.entrySet()) {  					// Getting trump
						if(entry.getValue() == 2) {
							trump = entry.getKey();
							//System.out.println("Trump Suit is : " +trump);
					        break;
						}
					}
					
					
				
				}
				else {
					//If it is 2 suits and does not have any Ace, Select the suit with low level
					int minumimLevel = Collections.min(integerList);
					int minimumLevelIndex = integerList.indexOf(minumimLevel);
					String minimumSuit = suitList.get(minimumLevelIndex);
					trump = minimumSuit;
					//System.out.println("Trump Suit is : " + trump );
					
							
				}
			}
		}
		else if(countMap.containsValue(1)){                            	//If contains 3 Cards in same suit and 1 card in different suit
			if(countMap.containsValue(3)) {
				for(Entry<String, Integer> entry: countMap.entrySet()) {
					if(entry.getValue() == 3) {
						trump = entry.getKey();
						//System.out.println("Trump Suit is : " +trump);
				        break;
					}
				}
				
			}
			else {
				int minumimLevel = Collections.min(integerList);  //If contains all cards in different suits.
				int minimumLevelIndex = integerList.indexOf(minumimLevel);
				String minimumSuit = suitList.get(minimumLevelIndex);
				trump = minimumSuit;
				//System.out.println("Trump Suit is : " +trump);
			}
			
		}
		
		else {
			for(Entry<String, Integer> entry: countMap.entrySet()) { //All cards are same suit.
				if(entry.getValue() == 4) {
					trump = entry.getKey();
					//System.out.println("Trump Suit is : " +trump);
			        break;
				}
				
			}
		}
		
		if(trumpSelector.equals("User")) {
			while(true) {
			    System.out.print("Select Trump Suit : ");
			    String userTrump = myObj.nextLine();  // Read user Trump
			    if(!userTrump.equals(trump)) {
			    	System.out.println("Wrong Trump, Select Again");
			    	continue;
			    }
			    break;
			}
			
		}
		else {
			System.out.println("Trump Suit is : " +trump);

		}
	}
	
	public void placeCard () {
		
	ArrayList<String> placingOrder = new ArrayList<String>();	
		placingOrder.add(playingOrder.get(1));
		placingOrder.add(playingOrder.get(2));
		placingOrder.add(playingOrder.get(3));
		placingOrder.add(playingOrder.get(0));
	
		
		String placingCardR1 = null;
		String placingCardR2 = null;
		String placingCardR3 = null;
		String placingCardR4 = null;
		String printingCardR1 = null;
		String printingCardR2 = null;
		String printingCardR3 = null;
		String printingCardR4 = null;
		String printingWinningCard = null;
		String maximumSuit = null;
		String winningCard = null;		

		
		ArrayList<String >withoutFirstPlacingCard = null;
		ArrayList<String >withoutFirstPlacingCardR2 = null;
		ArrayList<String >withoutFirstPlacingCardR3 = null;
		ArrayList<String >withoutFirstPlacingCardR4 = null;
		
		ArrayList<String >withoutFirstPlacingCardR1T2 = null;
		ArrayList<String >withoutFirstPlacingCardR2T2 = null;
		ArrayList<String >withoutFirstPlacingCardR3T2= null;
		ArrayList<String >withoutFirstPlacingCardR4T2 = null;
		
		ArrayList<String >withoutFirstPlacingCardT3 = null;
		ArrayList<String >withoutFirstPlacingCardR2T3 = null;
		ArrayList<String >withoutFirstPlacingCardR3T3 = null;
		ArrayList<String >withoutFirstPlacingCardR4T3 = null;
		
		ArrayList<String >withoutFirstPlacingCardT4 = null;
		ArrayList<String >withoutFirstPlacingCardR2T4 = null;
		ArrayList<String >withoutFirstPlacingCardR3T4 = null;
		ArrayList<String >withoutFirstPlacingCardR4T4 = null;
		
		ArrayList<String >withoutFirstPlacingCardT5 = null;
		ArrayList<String >withoutFirstPlacingCardR2T5 = null;
		ArrayList<String >withoutFirstPlacingCardR3T5 = null;
		ArrayList<String >withoutFirstPlacingCardR4T5 = null;
		
		ArrayList<String >withoutFirstPlacingCardT6 = null;
		ArrayList<String >withoutFirstPlacingCardR2T6 = null;
		ArrayList<String >withoutFirstPlacingCardR3T6 = null;
		ArrayList<String >withoutFirstPlacingCardR4T6 = null;
		
		ArrayList<String >withoutFirstPlacingCardT7 = null;
		ArrayList<String >withoutFirstPlacingCardR2T7 = null;
		ArrayList<String >withoutFirstPlacingCardR3T7 = null;
		ArrayList<String >withoutFirstPlacingCardR4T7= null;
		

		
				
		boolean sameSuitExists = true;
		
		List<String> placingCardsListR1  = robot1Cards;
		List<String> placingCardsListR2  = robot2Cards;
		List<String> placingCardsListR3  = robot3Cards;
		List<String> placingCardsListU  = userCards;
		ArrayList<String> placingCardsR1 = new ArrayList<>(placingCardsListR1);
		ArrayList<String> placingCardsR2 = new ArrayList<>(placingCardsListR2);
		ArrayList<String> placingCardsR3 = new ArrayList<>(placingCardsListR3);
		ArrayList<String> placingCardsR4 = new ArrayList<>(placingCardsListU);
		
		String lastCardSuit = null;
		String lastCardSuitR1T2 = null;
		String lastCardSuitR2T2 = null;
		String lastCardSuitR3T2 = null;
		String lastCardSuitR4T2 =  null;
		String lastCardSuitR1T3 = null;
		String lastCardSuitR2T3 = null;
		String lastCardSuitR3T3 = null;
		String lastCardSuitR4T3 =  null;
		String lastCardSuitR1T4 = null;
		String lastCardSuitR2T4 = null;
		String lastCardSuitR3T4 = null;
		String lastCardSuitR4T4 =  null;
		String lastCardSuitR1T5 = null;
		String lastCardSuitR2T5 = null;
		String lastCardSuitR3T5 = null;
		String lastCardSuitR4T5 =  null;
		String lastCardSuitR1T6 = null;
		String lastCardSuitR2T6 = null;
		String lastCardSuitR3T6 = null;
		String lastCardSuitR4T6 = null;
		String lastCardSuitR4T7 =  null;
		String lastCardSuitR1T7 = null;
		String lastCardSuitR2T7 = null;
		String lastCardSuitR3T7 = null;
		String lastCardSuitR4T8 =  null;
		String lastCardSuitR1T8 = null;
		String lastCardSuitR2T8 = null;
		String lastCardSuitR3T8 = null;

		

		
		
//-----------------------------------------------------------------------------> First Trick <-------------------------------------------------------------------------------//		
		String name = enterName();
		System.out.println("----------First Trick----------");
		if(placingOrder.get(0).equals("User")) {
			System.out.println("Cards In Hand : " + placingCardsR1);
			if(placingOrder.get(0).equals("User")) {
				
				
				while(true) {
					System.out.print("Place The First Card : ");
					printingCardR1 = myObj.nextLine(); 
					if(!placingCardsR1.contains(printingCardR1)) {
						System.out.println("You Dont' have this card");
						continue;
					}
					break;
				}
			}
			ArrayList<String> placingCardRandomList = new ArrayList<String>();
			placingCardRandomList.add(printingCardR1);
			 
			ArrayList<String> speratedR1List = sperateLevelsAndSuits(placingCardRandomList);
			ArrayList<String> allNumberPlayingCardsR1 = allNumbersList(speratedR1List);
			placingCardR1 = allNumberPlayingCardsR1.get(0) + " - " + allNumberPlayingCardsR1.get(1);
			lastCardSuit = separateString(placingCardR1); 
			 
			withoutFirstPlacingCard = removeLastTrickCard(placingCardsR1,printingCardR1);	 //Removing placed card from First user
			//System.out.println(withoutFirstPlacingCard + "withoutFirstPlacingCard");
		}
		else {
			printingCardR1= placingCardsR1.get(randomizer.nextInt(placingCardsR1.size())); //First User Placing the Card
			ArrayList<String> placingCardRandomList = new ArrayList<String>();
				placingCardRandomList.add(printingCardR1);
			
			ArrayList<String> speratedR1List = sperateLevelsAndSuits(placingCardRandomList);
			ArrayList<String> allNumberPlayingCardsR1 = allNumbersList(speratedR1List);
			placingCardR1 = allNumberPlayingCardsR1.get(0) + " - " + allNumberPlayingCardsR1.get(1);
			 lastCardSuit = separateString(placingCardR1); 
			
			if(placingOrder.get(0).equals("Robot1")) {
				System.out.println("Robot1 Placing First Card : " +printingCardR1);
			}
			else if(placingOrder.get(0).equals("Robot2")) {
				System.out.println("Robot2 Placing First Card: " +printingCardR1);
			}
			else if(placingOrder.get(0).equals("Robot3")) {
				System.out.println("Robot3 Placing First Card : " +printingCardR1);
			}
			
			
			withoutFirstPlacingCard = removeLastTrickCard(placingCardsR1,printingCardR1);	 //Removing placed card from First user
			//System.out.println(withoutFirstPlacingCard + "withoutFirstPlacingCard");
		}
		
		//Second Card Placing
		
		if(placingOrder.get(1).equals("User")) {
			System.out.println("Cards In Hand : " + placingCardsR2);
			System.out.print("Place The Second Card : ");
			while(true) {
				printingCardR2 = myObj.nextLine(); 
				ArrayList<String> placingCardRandomList = new ArrayList<String>();
				placingCardRandomList.add(printingCardR2);
				
				if(!placingCardsR2.contains(printingCardR2)) {
					System.out.println("You Dont' have this card");
					continue;
				}
				ArrayList<String> speratedR2List = sperateLevelsAndSuits(placingCardRandomList);
				ArrayList<String> allNumberPlayingCardsR2 = allNumbersList(speratedR2List);
				placingCardR2 = allNumberPlayingCardsR2.get(0) + " - " + allNumberPlayingCardsR2.get(1);
				String lastCardSuitR2 = separateString(placingCardR2); 
				
				ArrayList<String> sepratedPlayingCardsR2 = sperateLevelsAndSuits(placingCardsR2);       //Second User Placing Card Edits
				ArrayList<String> allNumberPlayingCardsR2New = allNumbersList(sepratedPlayingCardsR2);
				ArrayList<String> placingCardsSuitsR2 = createListOfSuits(allNumberPlayingCardsR2New);
				maximumSuit = lastCardSuitR2;
				if(!placingCardsR2.contains(printingCardR2)) {
					System.out.println("You Dont' have this card");
					continue;
				}
				
				if(placingCardsSuitsR2.contains(lastCardSuitR2)) {
					if(!lastCardSuitR2.equals(lastCardSuit)) {
						System.out.println("Select The Same Suit as Last placed suit");
						continue;
					}
				}
				
				else if (placingCardsSuitsR2.contains(trump)) {
					if(!lastCardSuitR2.equals(trump)) {
						System.out.println("You can place the Trump, Place it");
						continue;
					}
				}
				else {
					break;
				}
			break;	
			}
			
			withoutFirstPlacingCardR2 = removeLastTrickCard(placingCardsR2,printingCardR2);	//Removing Place cards from R2
			//System.out.println(withoutFirstPlacingCardR2 + " withoutFirstPlacingCardR2");
			
	
		}
		
		else {
		
			sameSuitExists = isSameSuitExist(placingCardsR2,lastCardSuit);         //Checking if the second user has to place same suit
	
		
			ArrayList<String> sepratedPlayingCardsR2 = sperateLevelsAndSuits(placingCardsR2);       //Second User Placing Card Edits
			ArrayList<String> allNumberPlayingCardsR2 = allNumbersList(sepratedPlayingCardsR2);
			ArrayList<String> placingCardsSuitsR2 = createListOfSuits(allNumberPlayingCardsR2);
			//System.out.println("placingCardsSuitsR2 " + placingCardsSuitsR2);
	
			if(sameSuitExists == true) {      //Selecting Same Suit for Second Player
				
				//System.out.println("Have same Suit for Second Trick");
				ArrayList<String> placingCardsWithSameSuit = createSameSuitList(placingCardsR2,lastCardSuit);	 //Creating list of Cards which contains Same suit as first placed Card
				
				ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
				ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
				ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
				ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
				placingCardR2 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
				maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
				printingCardR2 = allLettersString(placingCardR2);
				
				//System.out.println("Second Card is : " + printingCardR2);
				
				withoutFirstPlacingCardR2 = removeLastTrickCard(placingCardsR2,printingCardR2);	//Removing Place cards from R2
				//System.out.println(withoutFirstPlacingCardR2 + " withoutFirstPlacingCardR2");
			}
			
			else if(placingCardsSuitsR2.contains(trump)){
				
				ArrayList<String>trumpCardList = makeTrumpList(placingCardsR2,trump);
				ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
				ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
				ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
				
				placingCardR2 = findMaximumLevelCard(trumpCardsInteger,trumpCardWithSameSuit);
				maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
				printingCardR2 = allLettersString(placingCardR1);
				
				//System.out.println("Second Card is : " + printingCardR2);
				
				withoutFirstPlacingCardR2 = removeLastTrickCard(placingCardsR2,printingCardR2);	//Removing Place cards from R2
				//System.out.println(withoutFirstPlacingCardR2 + " withoutFirstPlacingCardR2");
						
			}
			
			else {
				printingCardR2= placingCardsR2.get(randomizer.nextInt(placingCardsR2.size()));
				ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
					placingCardNoMatchList.add(printingCardR2);
					
			
				ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
				ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
				placingCardR2 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
				maximumSuit = separateString(placingCardR1); 			
				//System.out.println("Second Card is " +placingCardR2);
				
				withoutFirstPlacingCardR2 = removeLastTrickCard(placingCardsR2,printingCardR2);	//Removing Place cards from R2
				//System.out.println(withoutFirstPlacingCardR2 + " withoutFirstPlacingCardR2");
			}
			if(placingOrder.get(1).equals("Robot1")) {
				System.out.println("Robot1 Placing Second Card : " +printingCardR2);
			}
			else if(placingOrder.get(1).equals("Robot2")) {
				System.out.println("Robot2 Placing Second Card : " +printingCardR2);
			}
			else if(placingOrder.get(1).equals("Robot3")) {
				System.out.println("Robot3 Placing Second Card: " +printingCardR2);
			}
		}

		//Third Placing Card
		if(placingOrder.get(2).equals("User")) {
			System.out.println("Cards In Hand : " + placingCardsR3);
			System.out.print("Place The Third Card : ");
			while(true) {
				printingCardR3 = myObj.nextLine(); 
				ArrayList<String> placingCardRandomList = new ArrayList<String>();
				placingCardRandomList.add(printingCardR3);
				
				if(!placingCardsR3.contains(printingCardR3)) {
					System.out.println("You Dont' have this card");
					continue;
				}
				ArrayList<String> speratedR3List = sperateLevelsAndSuits(placingCardRandomList);
				ArrayList<String> allNumberPlayingCardsR3 = allNumbersList(speratedR3List);
				placingCardR3 = allNumberPlayingCardsR3.get(0) + " - " + allNumberPlayingCardsR3.get(1);
				String lastCardSuitR3 = separateString(placingCardR3); 
				
				ArrayList<String> sepratedPlayingCardsR3 = sperateLevelsAndSuits(placingCardsR3);       //Second User Placing Card Edits
				ArrayList<String> allNumberPlayingCardsR3New = allNumbersList(sepratedPlayingCardsR3);
				ArrayList<String> placingCardsSuitsR3 = createListOfSuits(allNumberPlayingCardsR3New);
				maximumSuit = lastCardSuitR3;
				if(!placingCardsR3.contains(printingCardR3)) {
					System.out.println("You Dont' have this card");
					continue;
				}
				
				if(placingCardsSuitsR3.contains(maximumSuit)) {
					if(!lastCardSuitR3.equals(maximumSuit)) {
						System.out.println("Select The Same Suit as Last placed suit");
						continue;
					}
				}
				
				else if (placingCardsSuitsR3.contains(trump)) {
					if(!lastCardSuitR3.equals(trump)) {
						System.out.println("You can place the Trump, Place it");
						continue;
					}
				}
				else {
					break;
				}
			break;	
			}
			
			withoutFirstPlacingCardR3 = removeLastTrickCard(placingCardsR3,printingCardR3);	//Removing Place cards from R3
			
	
		}
		else {
		//Third Card placing preparations
			sameSuitExists = isSameSuitExist(placingCardsR3,maximumSuit);         //Checking if the Third user has to place same suit
			ArrayList<String> sepratedPlayingCardsR3 = sperateLevelsAndSuits(placingCardsR3);       //Third User Placing Card Edits
			ArrayList<String> allNumberPlayingCardsR3 = allNumbersList(sepratedPlayingCardsR3);
			ArrayList<String> placingCardsSuitsR3 = createListOfSuits(allNumberPlayingCardsR3);
			
			if(sameSuitExists == true) {      //Selecting Same Suit for Third Player
				
				//System.out.println("Have same Suit for Third Trick");
				ArrayList<String> placingCardsWithSameSuit = createSameSuitList(placingCardsR3,maximumSuit);	 //Creating list of Cards which contains Same suit as Third placed Card
				
				ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
				ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
				ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
				ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
				placingCardR3 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
				maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
				printingCardR3 = allLettersString(placingCardR3);
				
				
				
				withoutFirstPlacingCardR3 = removeLastTrickCard(placingCardsR3,printingCardR3);	//Removing Place cards from R3
			}
			
			else if(placingCardsSuitsR3.contains(trump)){
				
				ArrayList<String>trumpCardList = makeTrumpList(placingCardsR3,trump);
				ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
				ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
				ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
				
				placingCardR3 = findMaximumLevelCard(trumpCardsInteger,trumpCardWithSameSuit);
				maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
				printingCardR3 = allLettersString(placingCardR3);
				
				
				
				withoutFirstPlacingCardR3 = removeLastTrickCard(placingCardsR3,printingCardR3);	//Removing Place cards from R3
				
						
			}
			
			else {
				printingCardR3= placingCardsR3.get(randomizer.nextInt(placingCardsR3.size()));
				ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
					placingCardNoMatchList.add(printingCardR3);
					
			
				ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
				ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
				placingCardR3 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
				maximumSuit = separateString(placingCardR3); 			
				
	
				withoutFirstPlacingCardR3 = removeLastTrickCard(placingCardsR3,printingCardR3);	//Removing Place cards from R3
		
			}
			if(placingOrder.get(2).equals("Robot1")) {
				System.out.println("Robot1 Placing Third Card : " +printingCardR3);
			}
			else if(placingOrder.get(2).equals("Robot2")) {
				System.out.println("Robot2 Placing  Third Card : " +printingCardR3);
			}
			else if(placingOrder.get(2).equals("Robot3")) {
				System.out.println("Robot3 Placing  Third Card : " +printingCardR3);
			}
		}


		
		
		//Fourth Card placing preparations
		if(placingOrder.get(3).equals("User")) {
			System.out.println("Cards In Hand : " + placingCardsR4);
			System.out.print("Place The Fourth Card : ");
			while(true) {
				printingCardR4 = myObj.nextLine(); 
				ArrayList<String> placingCardRandomList = new ArrayList<String>();
				placingCardRandomList.add(printingCardR4);
				
				if(!placingCardsR4.contains(printingCardR4)) {
					System.out.println("You Dont' have this card");
					continue;
				}
				ArrayList<String> speratedR4List = sperateLevelsAndSuits(placingCardRandomList);
				ArrayList<String> allNumberPlayingCardsR4 = allNumbersList(speratedR4List);
				placingCardR4 = allNumberPlayingCardsR4.get(0) + " - " + allNumberPlayingCardsR4.get(1);
				String lastCardSuitR4 = separateString(placingCardR4); 
				
				ArrayList<String> sepratedPlayingCardsR4 = sperateLevelsAndSuits(placingCardsR4);       //Second User Placing Card Edits
				ArrayList<String> allNumberPlayingCardsR4New = allNumbersList(sepratedPlayingCardsR4);
				ArrayList<String> placingCardsSuitsR4 = createListOfSuits(allNumberPlayingCardsR4New);
				maximumSuit = lastCardSuitR4;
				if(!placingCardsR4.contains(printingCardR4)) {
					System.out.println("You Dont' have this card");
					continue;
				}
				
				if(placingCardsSuitsR4.contains(lastCardSuitR4)) {
					if(!lastCardSuitR4.equals(lastCardSuit)) {
						System.out.println("Select The Same Suit as Last placed suit");
						continue;
					}
				}
				
				else if (placingCardsSuitsR4.contains(trump)) {
					if(!lastCardSuitR4.equals(trump)) {
						System.out.println("You can place the Trump, Place it");
						continue;
					}
				}
				else {
					break;
				}
			break;	
			}
			
			withoutFirstPlacingCardR4 = removeLastTrickCard(placingCardsR4,printingCardR4);	//Removing Place cards from R2
			
	
		}
		else {
			sameSuitExists = isSameSuitExist(placingCardsR4,maximumSuit);         //Checking if the Fourth user has to place same suit
			
			ArrayList<String> sepratedPlayingCardsR4 = sperateLevelsAndSuits(placingCardsR4);       //Fourth User Placing Card Edits
			ArrayList<String> allNumberPlayingCardsR4 = allNumbersList(sepratedPlayingCardsR4);
			ArrayList<String> placingCardsSuitsR4 = createListOfSuits(allNumberPlayingCardsR4);
			
			if(sameSuitExists == true) {      //Selecting Same Suit for Second Player
				
				ArrayList<String> placingCardsWithSameSuit = createSameSuitList(placingCardsR4,maximumSuit);	 //Creating list of Cards which contains Same suit as Fourth placed Card
				
				ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
				ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
				ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
				ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
				placingCardR4 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
				maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
				printingCardR4 = allLettersString(placingCardR4);
				
				
				withoutFirstPlacingCardR4 = removeLastTrickCard(placingCardsR4,printingCardR4);	//Removing Place cards from R4
			}
			
			else if(placingCardsSuitsR4.contains(trump)){
				
				ArrayList<String>trumpCardList = makeTrumpList(placingCardsR4,trump);
				ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
				ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
				ArrayList<String> trumpCardsSuits = createListOfSuits(allNumberTrumpCards);
				ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
				
				placingCardR4 = findMaximumLevelCard(trumpCardsInteger,trumpCardsSuits);
				maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
				printingCardR4 = allLettersString(placingCardR4);
				
				
				withoutFirstPlacingCardR4 = removeLastTrickCard(placingCardsR4,printingCardR4);	//Removing Place cards from R4
						
			}
			
			else {
				printingCardR4= placingCardsR4.get(randomizer.nextInt(placingCardsR4.size()));
				ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
					placingCardNoMatchList.add(printingCardR4);
					
			
				ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
				ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
				placingCardR4 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
				maximumSuit = separateString(placingCardR4); 			
		
				withoutFirstPlacingCardR4 = removeLastTrickCard(placingCardsR4,printingCardR4);	//Removing Place cards from R4
			}
			if(placingOrder.get(3).equals("Robot1")) {
				System.out.println("Robot1 Placing Fourth Card : " +printingCardR4);
			}
			else if(placingOrder.get(3).equals("Robot2")) {
				System.out.println("Robot2 Placing Fourth Card  : " +printingCardR4);
			}
			else if(placingOrder.get(3).equals("Robot3")) {
				System.out.println("Robot3 Placing Fourth Card  : " +printingCardR4);
			}
		}	
		
		//Finding the Winning Card
		ArrayList<String> winningCardsList = new ArrayList<String>();
			winningCardsList.add(placingCardR1);
			winningCardsList.add(placingCardR2);
			winningCardsList.add(placingCardR3);
			winningCardsList.add(placingCardR4);

		ArrayList<String> winningCardsAllNumbers = allNumbersList(winningCardsList);
		ArrayList<String> winningCardsEdited = sperateLevelsAndSuits(winningCardsAllNumbers);
		ArrayList<String> winningCardsSuits = createListOfSuits(winningCardsEdited);
		ArrayList<Integer> winningCardsInteger = createListOfLevels(winningCardsEdited);

		if(winningCardsEdited.contains(trump)) {			
			ArrayList<String> winningCardsTrumpList = makeTrumpList(winningCardsList,trump);		
			ArrayList<String> winningCardsTrumpEdited = sperateLevelsAndSuits(winningCardsTrumpList);
			ArrayList<String> winningCardsTrumpSuits = createListOfSuits(winningCardsTrumpEdited);
			ArrayList<Integer> winningCardsTrumpInteger = createListOfLevels(winningCardsTrumpEdited);
			
			winningCard = findMaximumLevelCard(winningCardsTrumpInteger,winningCardsTrumpSuits);	
			maximumSuit  = findMaximumSuit(winningCardsTrumpInteger,winningCardsTrumpSuits);
			printingWinningCard = allLettersString(winningCard);
			
			System.out.println("WinningCard Card is : " + printingWinningCard);
		}
		
		else {
			winningCard = findMaximumLevelCard(winningCardsInteger,winningCardsSuits);	
			maximumSuit  = findMaximumSuit(winningCardsInteger,winningCardsSuits);
			printingWinningCard = allLettersString(winningCard);
			
			System.out.println("WinningCard Card is : " + printingWinningCard);
			
		}
		
		
	int winningCardIndex = winningCardsList.indexOf(winningCard);
	String winner = placingOrder.get(winningCardIndex);
	if(winner.equals("User")) {
		pointsUser += 2;	
		System.out.println(name + " Get 2 Points");
	}
	else if(winner.equals("Robot1")) {
		pointsRobot1 += 2;	
		System.out.println("Robot1 Get 2 Points");
	}
	else if(winner.equals("Robot2")) {
		pointsRobot2 += 2;	
		System.out.println("Robot2 Get 2 Points");
	}
	else if(winner.equals("Robot3")) {
		pointsRobot3 += 2;	
		System.out.println("Robot3 Get 2 Points");
	}
 //-------------------------------------------------------------------------->Second Trick<------------------------------------------------------------------------------------//
	System.out.println();
	System.out.println("----------Second Trick----------");
	if(placingOrder.get(0).equals("User")) {
		System.out.println("Cards In Hand : " + withoutFirstPlacingCard);
		while(true) {
			System.out.print("Place The First Card : ");
			printingCardR1 = myObj.nextLine(); 
			if(!withoutFirstPlacingCard.contains(printingCardR1)) {
				System.out.println("You Dont' have this card");
				continue;
			}
			break;
		}

		ArrayList<String> placingCardRandomList = new ArrayList<String>();
		placingCardRandomList.add(printingCardR1); 
		ArrayList<String> speratedR1T2List = sperateLevelsAndSuits(placingCardRandomList);
		ArrayList<String> allNumberPlayingCardsR1T2 = allNumbersList(speratedR1T2List);
		placingCardR1 = allNumberPlayingCardsR1T2.get(0) + " - " + allNumberPlayingCardsR1T2.get(1);
		lastCardSuitR1T2 = separateString(placingCardR1); 
		 
		withoutFirstPlacingCardR1T2 = removeLastTrickCard(withoutFirstPlacingCard,printingCardR1);	 //Removing placed card from First user
	}
	else {
		printingCardR1 = withoutFirstPlacingCard.get(randomizer.nextInt(withoutFirstPlacingCard.size())); //First User Placing the Card
		ArrayList<String> placingCardRandomList = new ArrayList<String>();
		placingCardRandomList.add(printingCardR1);
		
		ArrayList<String> speratedR1T2List = sperateLevelsAndSuits(placingCardRandomList);
		ArrayList<String> allNumberPlayingCardsR1T2 = allNumbersList(speratedR1T2List);
		placingCardR1 = allNumberPlayingCardsR1T2.get(0) + " - " + allNumberPlayingCardsR1T2.get(1);
		lastCardSuitR1T2 = separateString(placingCardR1); 
		maximumSuit = lastCardSuitR1T2;
		
		if(placingOrder.get(0).equals("Robot1")) {
			System.out.println("Robot1 Placing First Card : " +printingCardR1);
		}
		else if(placingOrder.get(0).equals("Robot2")) {
			System.out.println("Robot2 Placing First Card : " +printingCardR1);
		}
		else if(placingOrder.get(0).equals("Robot3")) {
			System.out.println("Robot3 Placing First Card : " +printingCardR1);
		}
		
		withoutFirstPlacingCardR1T2 = removeLastTrickCard(withoutFirstPlacingCard,printingCardR1);	 //Removing placed card from First user
	}
	
	if(placingOrder.get(1).equals("User")) {
		System.out.println("Cards In Hand : " + withoutFirstPlacingCardR2);
		System.out.print("Place The Second Card : ");
		while(true) {
			printingCardR2 = myObj.nextLine(); 
			ArrayList<String> placingCardRandomList = new ArrayList<String>();
			placingCardRandomList.add(printingCardR2);
			
			if(!withoutFirstPlacingCardR2.contains(printingCardR2)) {
				System.out.println("You Dont' have this card");
				continue;
			}
			ArrayList<String> speratedR2List = sperateLevelsAndSuits(placingCardRandomList);
			ArrayList<String> allNumberPlayingCardsR2 = allNumbersList(speratedR2List);
			placingCardR2 = allNumberPlayingCardsR2.get(0) + " - " + allNumberPlayingCardsR2.get(1);
			lastCardSuitR2T2 = separateString(placingCardR2); 
			
			
			ArrayList<String> sepratedPlayingCardsR2 = sperateLevelsAndSuits(withoutFirstPlacingCardR2);       //Second User Placing Card Edits
			ArrayList<String> allNumberPlayingCardsR2New = allNumbersList(sepratedPlayingCardsR2);
			ArrayList<String> placingCardsSuitsR2 = createListOfSuits(allNumberPlayingCardsR2New);

			
			if(placingCardsSuitsR2.contains(maximumSuit)) {
				if(!lastCardSuitR2T2.equals(maximumSuit)) {
					System.out.println("Place Same Suit as Last placed suit");
					continue;
				}
			}
			
			else if (placingCardsSuitsR2.contains(trump)) {
				if(!lastCardSuitR2T2.equals(trump)) {
					System.out.println("You can place the Trump, Place it");
					continue;
				}
			}
			else {
				break;
			}
			
		break;	
		}
		
		withoutFirstPlacingCardR2T2 = removeLastTrickCard(withoutFirstPlacingCardR2,printingCardR2);	//Removing Place cards from R2
		maximumSuit = lastCardSuitR2T2;
		

	}
	else {	
		//Second Card Placing
		sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR2, lastCardSuitR1T2);         //Checking if the second user has to place same suit

		if(sameSuitExists == true) {      //Selecting Same Suit for Second Player
			
			ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR2,lastCardSuitR1T2);	 //Creating list of Cards which contains Same suit as first placed Card
			ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
			ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
			ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
			ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
			placingCardR2 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
			maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
			printingCardR2 = allLettersString(placingCardR2);
			
			
			withoutFirstPlacingCardR2T2 = removeLastTrickCard(withoutFirstPlacingCardR2,printingCardR2);	//Removing Place cards from R2
		}
		
		else if(withoutFirstPlacingCardR2.contains(trump)){
			ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR2,trump);
			ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
			ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
			ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
			
			placingCardR2 = findMaximumLevelCard(trumpCardsInteger,trumpCardWithSameSuit);
			maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
			printingCardR2 = allLettersString(placingCardR2);
			withoutFirstPlacingCardR2T2 = removeLastTrickCard(withoutFirstPlacingCardR2,printingCardR2);	//Removing Place cards from R2					
		}
		
		else {
			printingCardR2= withoutFirstPlacingCardR2.get(randomizer.nextInt(withoutFirstPlacingCardR2.size()));
			ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
				placingCardNoMatchList.add(printingCardR2);
				
		
			ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
			ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
			placingCardR2 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
			maximumSuit = separateString(placingCardR2); 		
			
			withoutFirstPlacingCardR2T2 = removeLastTrickCard(withoutFirstPlacingCardR2,printingCardR2);	//Removing Place cards from R2
		}
		
		if(placingOrder.get(1).equals("Robot1")) {
			System.out.println("Robot1 Placing Second Card : " +printingCardR2);
		}
		else if(placingOrder.get(1).equals("Robot2")) {
			System.out.println("Robot2 Placing Second Card : " +printingCardR2);
		}
		else if(placingOrder.get(1).equals("Robot3")) {
			System.out.println("Robot3 Placing Second Card: " +printingCardR2);
		}
		

	}		//Third Placing Card
	if(placingOrder.get(2).equals("User")) {
		System.out.println("Cards In Hand : " + withoutFirstPlacingCardR3);
		System.out.print("Place The Third Card : ");
		while(true) {
			printingCardR3 = myObj.nextLine(); 
			ArrayList<String> placingCardRandomList = new ArrayList<String>();
			placingCardRandomList.add(printingCardR3);
			
			if(!withoutFirstPlacingCardR3.contains(printingCardR3)) {
				System.out.println("You Dont' have this card");
				continue;
			}
			ArrayList<String> speratedR3List = sperateLevelsAndSuits(placingCardRandomList);
			ArrayList<String> allNumberPlayingCardsR3 = allNumbersList(speratedR3List);
			placingCardR3 = allNumberPlayingCardsR3.get(0) + " - " + allNumberPlayingCardsR3.get(1);
			lastCardSuitR3T2 = separateString(placingCardR3); 
			
			ArrayList<String> sepratedPlayingCardsR3 = sperateLevelsAndSuits(withoutFirstPlacingCardR3);       //Second User Placing Card Edits
			ArrayList<String> allNumberPlayingCardsR3New = allNumbersList(sepratedPlayingCardsR3);
			ArrayList<String> placingCardsSuitsR3 = createListOfSuits(allNumberPlayingCardsR3New);
			
			if(placingCardsSuitsR3.contains(maximumSuit)) {
				if(!lastCardSuitR3T2.equals(maximumSuit)) {
					System.out.println("Select The Same Suit as Last placed suit");
					continue;
				}
			}
			
			else if (placingCardsSuitsR3.contains(trump)) {
				if(!lastCardSuitR3T2.equals(trump)) {
					System.out.println("You can place the Trump, Place it");
					continue;
				}
			}
			else {
				break;
			}
		break;	
		}
		
		withoutFirstPlacingCardR3T2 = removeLastTrickCard(withoutFirstPlacingCardR3,printingCardR3);	//Removing Place cards from R3
		maximumSuit = lastCardSuitR3T2;

	}
	else {
		//Third Card placing preparations
		sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR3,maximumSuit);         //Checking if the Third user has to place same suit

		if(sameSuitExists == true) {      //Selecting Same Suit for Third Player
			ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR3,maximumSuit);	 //Creating list of Cards which contains Same suit as Third placed Card
			
			ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
			ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
			ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
			ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
			placingCardR3 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
			maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
			printingCardR3 = allLettersString(placingCardR3);
			
			
			withoutFirstPlacingCardR3T2 = removeLastTrickCard(withoutFirstPlacingCardR3,printingCardR3);	//Removing Place cards from R3
		}
		
		else if(withoutFirstPlacingCardR3.contains(trump)){
			
			ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR3,trump);
			ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
			ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
			ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
			
			placingCardR3 = findMaximumLevelCard(trumpCardsInteger,trumpCardWithSameSuit);
			maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
			printingCardR3 = allLettersString(placingCardR3);
			
			
			withoutFirstPlacingCardR3T2 = removeLastTrickCard(withoutFirstPlacingCardR3,printingCardR3);	//Removing Place cards from R3
					
		}
		
		else {
			printingCardR3= withoutFirstPlacingCardR3.get(randomizer.nextInt(withoutFirstPlacingCardR3.size()));
			ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
				placingCardNoMatchList.add(printingCardR3);
				
		
			ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
			ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
			placingCardR3 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
			maximumSuit = separateString(placingCardR3); 			
			withoutFirstPlacingCardR3T2 = removeLastTrickCard(withoutFirstPlacingCardR3,printingCardR3);	//Removing Place cards from R3
		}
		if(placingOrder.get(2).equals("Robot1")) {
			System.out.println("Robot1 Placing Third Card: " +printingCardR3);
		}
		else if(placingOrder.get(2).equals("Robot2")) {
			System.out.println("Robot2 Placing Third Card : " +printingCardR3);
		}
		else if(placingOrder.get(2).equals("Robot3")) {
			System.out.println("Robot3 Placing Third Card : " +printingCardR3);
		}
	}
		
		//Fourth Card placing preparations
	if(placingOrder.get(3).equals("User")) {
		System.out.println("Cards In Hand : " + withoutFirstPlacingCardR4);
		System.out.print("Place The Fourth Card : ");
		while(true) {
			printingCardR4 = myObj.nextLine(); 
			ArrayList<String> placingCardRandomList = new ArrayList<String>();
			placingCardRandomList.add(printingCardR4);
			
			if(!withoutFirstPlacingCardR4.contains(printingCardR4)) {
				System.out.println("You Dont' have this card");
				continue;
			}
			ArrayList<String> speratedR4List = sperateLevelsAndSuits(placingCardRandomList);
			ArrayList<String> allNumberPlayingCardsR4 = allNumbersList(speratedR4List);
			placingCardR4 = allNumberPlayingCardsR4.get(0) + " - " + allNumberPlayingCardsR4.get(1);
			lastCardSuitR4T2 = separateString(placingCardR4); 
			
			ArrayList<String> sepratedPlayingCardsR4 = sperateLevelsAndSuits(withoutFirstPlacingCardR4);       //Second User Placing Card Edits
			ArrayList<String> allNumberPlayingCardsR4New = allNumbersList(sepratedPlayingCardsR4);
			ArrayList<String> placingCardsSuitsR4 = createListOfSuits(allNumberPlayingCardsR4New);
			

			if(placingCardsSuitsR4.contains(maximumSuit)) {
				if(!lastCardSuitR4T2.equals(maximumSuit)) {
					System.out.println("Select The Same Suit as Last placed suit");
					continue;
				}
			}
			
			else if (placingCardsSuitsR4.contains(trump)) {
				if(!lastCardSuitR4T2.equals(trump)) {
					System.out.println("You can place the Trump, Place it");
					continue;
				}
			}
			else {
				break;
			}
		break;	
		}
		
		withoutFirstPlacingCardR4T2 = removeLastTrickCard(withoutFirstPlacingCardR4,printingCardR4);	//Removing Place cards from R2
		maximumSuit = lastCardSuitR4T2;

	}
	else {
		sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR4,maximumSuit);         //Checking if the Fourth user has to place same suit

		if(sameSuitExists == true) {      //Selecting Same Suit for Second Player
			
			ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR4,maximumSuit);	 //Creating list of Cards which contains Same suit as Fourth placed Card
			
			ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
			ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
			ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
			ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
			placingCardR4 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
			maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
			printingCardR4 = allLettersString(placingCardR4);
			withoutFirstPlacingCardR4T2 = removeLastTrickCard(withoutFirstPlacingCardR4,printingCardR4);	//Removing Place cards from R4
		}
		
		else if(withoutFirstPlacingCardR4.contains(trump)){
			
			ArrayList<String>trumpCardList = makeTrumpList(placingCardsR4,trump);
			ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
			ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
			ArrayList<String> trumpCardsSuits = createListOfSuits(allNumberTrumpCards);
			ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
			
			placingCardR4 = findMaximumLevelCard(trumpCardsInteger,trumpCardsSuits);
			maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
			printingCardR4 = allLettersString(placingCardR4);
			
			
			withoutFirstPlacingCardR4T2 = removeLastTrickCard(withoutFirstPlacingCardR4,printingCardR4);	//Removing Place cards from R4
					
		}
		
		else {
			printingCardR4= withoutFirstPlacingCardR4.get(randomizer.nextInt(withoutFirstPlacingCardR4.size()));
			ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
				placingCardNoMatchList.add(printingCardR4);
				
		
			ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
			ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
			placingCardR4 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
			maximumSuit = separateString(placingCardR4); 			
			
			withoutFirstPlacingCardR4T2 = removeLastTrickCard(withoutFirstPlacingCardR4,printingCardR4);	//Removing Place cards from R4
		}
		if(placingOrder.get(3).equals("Robot1")) {
			System.out.println("Robot1 Placing Fourth Card : " +printingCardR4);
		}
		else if(placingOrder.get(3).equals("Robot2")) {
			System.out.println("Robot2 Placing Fourth Card : " +printingCardR4);
		}
		else if(placingOrder.get(3).equals("Robot3")) {
			System.out.println("Robot3 Placing Fourth Card : " +printingCardR4);
		}
		
	}	
	
	
		//Finding the Winning Card
		winningCardsList = new ArrayList<String>();
			winningCardsList.add(placingCardR1);
			winningCardsList.add(placingCardR2);
			winningCardsList.add(placingCardR3);
			winningCardsList.add(placingCardR4);
		
		winningCardsAllNumbers = allNumbersList(winningCardsList);
		winningCardsEdited = sperateLevelsAndSuits(winningCardsAllNumbers);
		winningCardsSuits = createListOfSuits(winningCardsEdited);
		winningCardsInteger = createListOfLevels(winningCardsEdited);

		if(winningCardsEdited.contains(trump)) {
			
			ArrayList<String> winningCardsTrumpList = makeTrumpList(winningCardsList,trump);		
			ArrayList<String> winningCardsTrumpEdited = sperateLevelsAndSuits(winningCardsTrumpList);
			ArrayList<String> winningCardsTrumpSuits = createListOfSuits(winningCardsTrumpEdited);
			ArrayList<Integer> winningCardsTrumpInteger = createListOfLevels(winningCardsTrumpEdited);
			
			winningCard = findMaximumLevelCard(winningCardsTrumpInteger,winningCardsTrumpSuits);				
			maximumSuit  = findMaximumSuit(winningCardsTrumpInteger,winningCardsTrumpSuits);
			printingWinningCard = allLettersString(winningCard);
			
			System.out.println("Second Trick WinningCard Card is : " + printingWinningCard);
			

			
			
		}
		
		else {
			winningCard = findMaximumLevelCard(winningCardsInteger,winningCardsSuits);	
			maximumSuit  = findMaximumSuit(winningCardsInteger,winningCardsSuits);
			printingWinningCard = allLettersString(winningCard);
			System.out.println("Second Trick WinningCard Card is : " + printingWinningCard);
			
			
		}
		winningCardIndex = winningCardsList.indexOf(winningCard);
		winner = placingOrder.get(winningCardIndex);
		if(winner.equals("User")) {
			pointsUser += 2;	
			System.out.println(name + " Get 2 Points");
		}
		else if(winner.equals("Robot1")) {
			pointsRobot1 += 2;	
			System.out.println("Robot1 Get 2 Points");
		}
		else if(winner.equals("Robot2")) {
			pointsRobot2 += 2;	
			System.out.println("Robot2 Get 2 Points");
		}
		else if(winner.equals("Robot3")) {
			pointsRobot3 += 2;	
			System.out.println("Robot3 Get 2 Points");
		}
//---------------------------------------------------------------------------------------------->Third Trick<------------------------------------------------------------------------------------//
		System.out.println();
		System.out.println("----------Third Trick----------");
		if(placingOrder.get(0).equals("User")) {
			System.out.println("Cards In Hand : " + withoutFirstPlacingCardR1T2);
				while(true) {
					System.out.print("Place The First Card : ");
					printingCardR1 = myObj.nextLine(); 
					if(!withoutFirstPlacingCardR1T2.contains(printingCardR1)) {
						System.out.println("You Dont' have this card");
						continue;
					}
					break;
				}
			
			
			ArrayList<String> placingCardRandomList = new ArrayList<String>();
			placingCardRandomList.add(printingCardR1);
			 
			ArrayList<String> speratedList = sperateLevelsAndSuits(placingCardRandomList);
			ArrayList<String> allNumberPlayingCards = allNumbersList(speratedList);
			placingCardR1 = allNumberPlayingCards.get(0) + " - " + allNumberPlayingCards.get(1);
			lastCardSuitR1T3 = separateString(placingCardR1); 
			maximumSuit = lastCardSuitR1T3;

			 
			withoutFirstPlacingCardT3 = removeLastTrickCard(withoutFirstPlacingCardR1T2,printingCardR1);	 //Removing placed card from First user
		}
		else {
			printingCardR1 = withoutFirstPlacingCardR1T2.get(randomizer.nextInt(withoutFirstPlacingCardR1T2.size())); //First User Placing the Card
			ArrayList<String> placingCardRandomList = new ArrayList<String>();
			placingCardRandomList.add(printingCardR1);
			
			ArrayList<String> speratedList = sperateLevelsAndSuits(placingCardRandomList);
			ArrayList<String> allNumberPlayingCards = allNumbersList(speratedList);
			placingCardR1 = allNumberPlayingCards.get(0) + " - " + allNumberPlayingCards.get(1);
			lastCardSuitR1T3 = separateString(placingCardR1); 
			maximumSuit = lastCardSuitR1T3;
			
			if(placingOrder.get(0).equals("Robot1")) {
				System.out.println("Robot1 Placing First Card : " +printingCardR1);
			}
			else if(placingOrder.get(0).equals("Robot2")) {
				System.out.println("Robot2 Placing First Card : " +printingCardR1);
			}
			else if(placingOrder.get(0).equals("Robot3")) {
				System.out.println("Robot3 Placing First Card : " +printingCardR1);
			}
			
			withoutFirstPlacingCardT3 = removeLastTrickCard(withoutFirstPlacingCardR1T2,printingCardR1);	 //Removing placed card from First user
		}
		//Second Card Placing
		if(placingOrder.get(1).equals("User")) {
			System.out.println("Cards In Hand : " + withoutFirstPlacingCardR2T2);
			
			while(true) {
				System.out.print("Place The Second Card : ");
				printingCardR2 = myObj.nextLine(); 
				ArrayList<String> placingCardRandomList = new ArrayList<String>();
				placingCardRandomList.add(printingCardR2);
				
				if(!withoutFirstPlacingCardR2T2.contains(printingCardR2)) {
					System.out.println("You Dont' have this card");
					continue;
				}
				ArrayList<String> speratedR2List = sperateLevelsAndSuits(placingCardRandomList);
				ArrayList<String> allNumberPlayingCardsR2 = allNumbersList(speratedR2List);
				placingCardR2 = allNumberPlayingCardsR2.get(0) + " - " + allNumberPlayingCardsR2.get(1);
				lastCardSuitR2T3 = separateString(placingCardR2); 
				
				
				ArrayList<String> sepratedPlayingCardsR2 = sperateLevelsAndSuits(withoutFirstPlacingCardR2T2);       //Second User Placing Card Edits
				ArrayList<String> allNumberPlayingCardsR2New = allNumbersList(sepratedPlayingCardsR2);
				ArrayList<String> placingCardsSuitsR2 = createListOfSuits(allNumberPlayingCardsR2New);

				
				if(placingCardsSuitsR2.contains(maximumSuit)) {
					if(!lastCardSuitR2T3.equals(maximumSuit)) {
						System.out.println("Select The Same Suit as Last placed suit");
						continue;
					}
				}
				
				else if (placingCardsSuitsR2.contains(trump)) {
					if(!lastCardSuitR2T3.equals(trump)) {
						System.out.println("You can place the Trump, Place it");
						continue;
					}
				}
				else {
					break;
				}
			break;	
			}
			
			withoutFirstPlacingCardR2T3 = removeLastTrickCard(withoutFirstPlacingCardR2T2,printingCardR2);	//Removing Place cards from R2
			maximumSuit = lastCardSuitR2T3;
			

		}
		else {	
			//Second Card Placing
			sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR2T2, maximumSuit);         //Checking if the second user has to place same suit
		
			if(sameSuitExists == true) {      //Selecting Same Suit for Second Player
				
				ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR2T2,lastCardSuitR1T3);	 //Creating list of Cards which contains Same suit as first placed Card
				ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
				ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
				ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
				ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
				placingCardR2 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
				maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
				printingCardR2 = allLettersString(placingCardR2);
				
				
				withoutFirstPlacingCardR2T3 = removeLastTrickCard(withoutFirstPlacingCardR2T2,printingCardR2);	//Removing Place cards from R2
			}
			
			else if(withoutFirstPlacingCardR2T2.contains(trump)){
				ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR2T2,trump);
				ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
				ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
				ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
				
				placingCardR2 = findMaximumLevelCard(trumpCardsInteger,trumpCardWithSameSuit);
				maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
				printingCardR2 = allLettersString(placingCardR2);
				withoutFirstPlacingCardR2T3 = removeLastTrickCard(withoutFirstPlacingCardR2T2,printingCardR2);	//Removing Place cards from R2					
			}
			
			else {
				printingCardR2= withoutFirstPlacingCardR2T2.get(randomizer.nextInt(withoutFirstPlacingCardR2T2.size()));
				ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
					placingCardNoMatchList.add(printingCardR2);
					
			
				ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
				ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
				placingCardR2 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
				maximumSuit = separateString(placingCardR2); 		
				
				withoutFirstPlacingCardR2T3 = removeLastTrickCard(withoutFirstPlacingCardR2T2,printingCardR2);	//Removing Place cards from R2
			}
			
			if(placingOrder.get(1).equals("Robot1")) {
				System.out.println("Robot1 Placing Second Card : " +printingCardR2);
			}
			else if(placingOrder.get(1).equals("Robot2")) {
				System.out.println("Robot2 Placing Second Card : " +printingCardR2);
			}
			else if(placingOrder.get(1).equals("Robot3")) {
				System.out.println("Robot3 Placing Second Card: " +printingCardR2);
			}
			

		}		
		
		//Third Placing Card
		if(placingOrder.get(2).equals("User")) {
			System.out.println("Cards In Hand : " + withoutFirstPlacingCardR3T2);
			System.out.print("Place The Third Card : ");
			while(true) {
				printingCardR3 = myObj.nextLine(); 
				ArrayList<String> placingCardRandomList = new ArrayList<String>();
				placingCardRandomList.add(printingCardR3);
				
				if(!withoutFirstPlacingCardR3T2.contains(printingCardR3)) {
					System.out.println("You Dont' have this card");
					continue;
				}
				ArrayList<String> speratedR3List = sperateLevelsAndSuits(placingCardRandomList);
				ArrayList<String> allNumberPlayingCardsR3 = allNumbersList(speratedR3List);
				placingCardR3 = allNumberPlayingCardsR3.get(0) + " - " + allNumberPlayingCardsR3.get(1);
				lastCardSuitR3T3 = separateString(placingCardR3); 
				
				ArrayList<String> sepratedPlayingCardsR3 = sperateLevelsAndSuits(withoutFirstPlacingCardR3T2);       //Second User Placing Card Edits
				ArrayList<String> allNumberPlayingCardsR3New = allNumbersList(sepratedPlayingCardsR3);
				ArrayList<String> placingCardsSuitsR3 = createListOfSuits(allNumberPlayingCardsR3New);
				
				if(placingCardsSuitsR3.contains(maximumSuit)) {
					if(!lastCardSuitR3T3.equals(maximumSuit)) {
						System.out.println("Select The Same Suit as Last placed suit");
						continue;
					}
				}
				
				else if (placingCardsSuitsR3.contains(trump)) {
					if(!lastCardSuitR3T3.equals(trump)) {
						System.out.println("You can place the Trump, Place it");
						continue;
					}
				}
				else {
					break;
				}
			break;	
			}
			
			withoutFirstPlacingCardR3T3 = removeLastTrickCard(withoutFirstPlacingCardR3T2,printingCardR3);	//Removing Place cards from R3
			maximumSuit = lastCardSuitR3T3;

		}
		else {
			//Third Card placing preparations
			sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR3T2,maximumSuit);         //Checking if the Third user has to place same suit
			
			if(sameSuitExists == true) {      //Selecting Same Suit for Third Player
				ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR3T2,maximumSuit);	 //Creating list of Cards which contains Same suit as Third placed Card
				
				ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
				ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
				ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
				ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
				placingCardR3 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
				maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
				printingCardR3 = allLettersString(placingCardR3);
				
				
				withoutFirstPlacingCardR3T3 = removeLastTrickCard(withoutFirstPlacingCardR3T2,printingCardR3);	//Removing Place cards from R3
			}
			
			else if(withoutFirstPlacingCardR3T2.contains(trump)){
				
				ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR3T2,trump);
				ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
				ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
				ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
				
				placingCardR3 = findMaximumLevelCard(trumpCardsInteger,trumpCardWithSameSuit);
				maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
				printingCardR3 = allLettersString(placingCardR3);
				
				
				withoutFirstPlacingCardR3T3 = removeLastTrickCard(withoutFirstPlacingCardR3T2,printingCardR3);	//Removing Place cards from R3
						
			}
			
			else {
				printingCardR3= withoutFirstPlacingCardR3T2.get(randomizer.nextInt(withoutFirstPlacingCardR3T2.size()));
				ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
					placingCardNoMatchList.add(printingCardR3);
					
			
				ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
				ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
				placingCardR3 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
				maximumSuit = separateString(placingCardR3); 			
				withoutFirstPlacingCardR3T3 = removeLastTrickCard(withoutFirstPlacingCardR3T2,printingCardR3);	//Removing Place cards from R3
			}
			if(placingOrder.get(2).equals("Robot1")) {
				System.out.println("Robot1 Placing Third Card: " +printingCardR3);
			}
			else if(placingOrder.get(2).equals("Robot2")) {
				System.out.println("Robot2 Placing Third Card : " +printingCardR3);
			}
			else if(placingOrder.get(2).equals("Robot3")) {
				System.out.println("Robot3 Placing Third Card : " +printingCardR3);
			}
		}
			
			//Fourth Card placing preparations
		if(placingOrder.get(3).equals("User")) {
			System.out.println("Cards In Hand : " + withoutFirstPlacingCardR4T2);
			System.out.print("Place The Fourth Card : ");
			while(true) {
				printingCardR4 = myObj.nextLine(); 
				ArrayList<String> placingCardRandomList = new ArrayList<String>();
				placingCardRandomList.add(printingCardR4);
				
				if(!withoutFirstPlacingCardR4T2.contains(printingCardR4)) {
					System.out.println("You Dont' have this card");
					continue;
				}
				ArrayList<String> speratedR4List = sperateLevelsAndSuits(placingCardRandomList);
				ArrayList<String> allNumberPlayingCardsR4 = allNumbersList(speratedR4List);
				placingCardR4 = allNumberPlayingCardsR4.get(0) + " - " + allNumberPlayingCardsR4.get(1);
				lastCardSuitR4T3 = separateString(placingCardR4); 
				
				ArrayList<String> sepratedPlayingCardsR4 = sperateLevelsAndSuits(withoutFirstPlacingCardR4T2);       //Second User Placing Card Edits
				ArrayList<String> allNumberPlayingCardsR4New = allNumbersList(sepratedPlayingCardsR4);
				ArrayList<String> placingCardsSuitsR4 = createListOfSuits(allNumberPlayingCardsR4New);
				

				if(placingCardsSuitsR4.contains(maximumSuit)) {
					if(!lastCardSuitR4T3.equals(maximumSuit)) {
						System.out.println("Select The Same Suit as Last placed suit");
						continue;
					}
				}
				
				else if (placingCardsSuitsR4.contains(trump)) {
					if(!lastCardSuitR4T3.equals(trump)) {
						System.out.println("You can place the Trump, Place it");
						continue;
					}
				}
				else {
					break;
				}
			break;	
			}
			
			withoutFirstPlacingCardR4T3 = removeLastTrickCard(withoutFirstPlacingCardR4T2,printingCardR4);	//Removing Place cards from R2
			maximumSuit = lastCardSuitR4T3;

		}
		else {
			sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR4T2,maximumSuit);         //Checking if the Fourth user has to place same suit
			
			if(sameSuitExists == true) {      //Selecting Same Suit for Second Player
				ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR4T2,maximumSuit);	 //Creating list of Cards which contains Same suit as Fourth placed Card
				ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
				ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
				ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
				ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);

				placingCardR4 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
				maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
				printingCardR4 = allLettersString(placingCardR4);
				withoutFirstPlacingCardR4T3 = removeLastTrickCard(withoutFirstPlacingCardR4T2,printingCardR4);	//Removing Place cards from R4
			}
			
			else if(withoutFirstPlacingCardR4T2.contains(trump)){
				
				ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR4T2,trump);
				ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
				ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
				ArrayList<String> trumpCardsSuits = createListOfSuits(allNumberTrumpCards);
				ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
				
				placingCardR4 = findMaximumLevelCard(trumpCardsInteger,trumpCardsSuits);
				maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
				printingCardR4 = allLettersString(placingCardR4);
				
				
				withoutFirstPlacingCardR4T3 = removeLastTrickCard(withoutFirstPlacingCardR4T2,printingCardR4);	//Removing Place cards from R4
						
			}
			
			else {
				printingCardR4= withoutFirstPlacingCardR4T2.get(randomizer.nextInt(withoutFirstPlacingCardR4T2.size()));
				ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
					placingCardNoMatchList.add(printingCardR4);
					
			
				ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
				ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
				placingCardR4 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
				maximumSuit = separateString(placingCardR4); 			
				
				withoutFirstPlacingCardR4T3 = removeLastTrickCard(withoutFirstPlacingCardR4T2,printingCardR4);	//Removing Place cards from R4
			}
			if(placingOrder.get(3).equals("Robot1")) {
				System.out.println("Robot1 Placing Fourth Card : " +printingCardR4);
			}
			else if(placingOrder.get(3).equals("Robot2")) {
				System.out.println("Robot2 Placing Fourth Card : " +printingCardR4);
			}
			else if(placingOrder.get(3).equals("Robot3")) {
				System.out.println("Robot3 Placing Fourth Card : " +printingCardR4);
			}
			
		}	
		
		
			//Finding the Winning Card
			winningCardsList = new ArrayList<String>();
				winningCardsList.add(placingCardR1);
				winningCardsList.add(placingCardR2);
				winningCardsList.add(placingCardR3);
				winningCardsList.add(placingCardR4);
			
			winningCardsAllNumbers = allNumbersList(winningCardsList);
			winningCardsEdited = sperateLevelsAndSuits(winningCardsAllNumbers);
			winningCardsSuits = createListOfSuits(winningCardsEdited);
			winningCardsInteger = createListOfLevels(winningCardsEdited);

			if(winningCardsEdited.contains(trump)) {
				
				ArrayList<String> winningCardsTrumpList = makeTrumpList(winningCardsList,trump);		
				ArrayList<String> winningCardsTrumpEdited = sperateLevelsAndSuits(winningCardsTrumpList);
				ArrayList<String> winningCardsTrumpSuits = createListOfSuits(winningCardsTrumpEdited);
				ArrayList<Integer> winningCardsTrumpInteger = createListOfLevels(winningCardsTrumpEdited);
				
				winningCard = findMaximumLevelCard(winningCardsTrumpInteger,winningCardsTrumpSuits);				
				maximumSuit  = findMaximumSuit(winningCardsTrumpInteger,winningCardsTrumpSuits);
				printingWinningCard = allLettersString(winningCard);
				
				System.out.println("Third Trick WinningCard Card is : " + printingWinningCard);
				

				
				
			}
			
			else {
				winningCard = findMaximumLevelCard(winningCardsInteger,winningCardsSuits);	
				maximumSuit  = findMaximumSuit(winningCardsInteger,winningCardsSuits);
				printingWinningCard = allLettersString(winningCard);
				System.out.println("Third Trick WinningCard Card is : " + printingWinningCard);
				
				
			}
			winningCardIndex = winningCardsList.indexOf(winningCard);
			winner = placingOrder.get(winningCardIndex);
			if(winner.equals("User")) {
				pointsUser += 2;	
				System.out.println(name + " Get 2 Points");
			}
			else if(winner.equals("Robot1")) {
				pointsRobot1 += 2;	
				System.out.println("Robot1 Get 2 Points");
			}
			else if(winner.equals("Robot2")) {
				pointsRobot2 += 2;	
				System.out.println("Robot2 Get 2 Points");
			}
			else if(winner.equals("Robot3")) {
				pointsRobot3 += 2;	
				System.out.println("Robot3 Get 2 Points");
			}

//---------------------------------------------------------------------------------------------->Fourth Trick<------------------------------------------------------------------------------------//
			System.out.println();
			System.out.println("----------Fourth Trick----------");
			if(placingOrder.get(0).equals("User")) {
				System.out.println("Cards In Hand : " + withoutFirstPlacingCardT3);
					while(true) {
						System.out.print("Place The First Card : ");
						printingCardR1 = myObj.nextLine(); 
						if(!withoutFirstPlacingCardT3.contains(printingCardR1)) {
							System.out.println("You Dont' have this card");
							continue;
						}
						break;
					}
				
				
				ArrayList<String> placingCardRandomList = new ArrayList<String>();
				placingCardRandomList.add(printingCardR1);
				 
				ArrayList<String> speratedList = sperateLevelsAndSuits(placingCardRandomList);
				ArrayList<String> allNumberPlayingCards = allNumbersList(speratedList);
				placingCardR1 = allNumberPlayingCards.get(0) + " - " + allNumberPlayingCards.get(1);
				lastCardSuitR1T4 = separateString(placingCardR1); 
				 
				withoutFirstPlacingCardT4 = removeLastTrickCard(withoutFirstPlacingCardT3,printingCardR1);	 //Removing placed card from First user
			}
			else {
				printingCardR1 = withoutFirstPlacingCardT3.get(randomizer.nextInt(withoutFirstPlacingCardT3.size())); //First User Placing the Card
				ArrayList<String> placingCardRandomList = new ArrayList<String>();
				placingCardRandomList.add(printingCardR1);
				
				ArrayList<String> speratedList = sperateLevelsAndSuits(placingCardRandomList);
				ArrayList<String> allNumberPlayingCards = allNumbersList(speratedList);
				placingCardR1 = allNumberPlayingCards.get(0) + " - " + allNumberPlayingCards.get(1);
				lastCardSuitR1T4 = separateString(placingCardR1); 
				maximumSuit = lastCardSuitR1T4;
				
				if(placingOrder.get(0).equals("Robot1")) {
					System.out.println("Robot1 Placing First Card : " +printingCardR1);
				}
				else if(placingOrder.get(0).equals("Robot2")) {
					System.out.println("Robot2 Placing First Card : " +printingCardR1);
				}
				else if(placingOrder.get(0).equals("Robot3")) {
					System.out.println("Robot3 Placing First Card : " +printingCardR1);
				}
				
				withoutFirstPlacingCardT4 = removeLastTrickCard(withoutFirstPlacingCardT3,printingCardR1);	 //Removing placed card from First user
			}
			//Second Card Placing
			if(placingOrder.get(1).equals("User")) {
				System.out.println("Cards In Hand : " + withoutFirstPlacingCardR2T3);
				System.out.print("Place The Second Card : ");
				while(true) {
					printingCardR2 = myObj.nextLine(); 
					ArrayList<String> placingCardRandomList = new ArrayList<String>();
					placingCardRandomList.add(printingCardR2);
					
					if(!withoutFirstPlacingCardR2T3.contains(printingCardR2)) {
						System.out.println("You Dont' have this card");
						continue;
					}
					ArrayList<String> speratedR2List = sperateLevelsAndSuits(placingCardRandomList);
					ArrayList<String> allNumberPlayingCardsR2 = allNumbersList(speratedR2List);
					placingCardR2 = allNumberPlayingCardsR2.get(0) + " - " + allNumberPlayingCardsR2.get(1);
					lastCardSuitR2T4 = separateString(placingCardR2); 
					
					ArrayList<String> sepratedPlayingCardsR2 = sperateLevelsAndSuits(withoutFirstPlacingCardR2T3);       //Second User Placing Card Edits
					ArrayList<String> allNumberPlayingCardsR2New = allNumbersList(sepratedPlayingCardsR2);
					ArrayList<String> placingCardsSuitsR2 = createListOfSuits(allNumberPlayingCardsR2New);

					
					if(placingCardsSuitsR2.contains(maximumSuit)) {
						if(!lastCardSuitR2T4.equals(maximumSuit)) {
							System.out.println("Select The Same Suit as Last placed suit");
							continue;
						}
					}
					
					else if (placingCardsSuitsR2.contains(trump)) {
						if(!lastCardSuitR2T4.equals(trump)) {
							System.out.println("You can place the Trump, Place it");
							continue;
						}
					}
					else {
						break;	
					}
				break;	
				}
				
				withoutFirstPlacingCardR2T4 = removeLastTrickCard(withoutFirstPlacingCardR2T3,printingCardR2);	//Removing Place cards from R2
				maximumSuit = lastCardSuitR2T4;

				

			}
			else {	
				//Second Card Placing
				sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR2T3, maximumSuit);         //Checking if the second user has to place same suit
			
				if(sameSuitExists == true) {      //Selecting Same Suit for Second Player
					
					ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR2T3,maximumSuit);	 //Creating list of Cards which contains Same suit as first placed Card
					ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
					ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
					ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
					ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
					placingCardR2 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
					maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
					printingCardR2 = allLettersString(placingCardR2);
					
					
					withoutFirstPlacingCardR2T4 = removeLastTrickCard(withoutFirstPlacingCardR2T3,printingCardR2);	//Removing Place cards from R2
				}
				
				else if(withoutFirstPlacingCardR2T3.contains(trump)){
					ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR2T3,trump);
					ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
					ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
					ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
					
					placingCardR2 = findMaximumLevelCard(trumpCardsInteger,trumpCardWithSameSuit);
					maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
					printingCardR2 = allLettersString(placingCardR2);
					withoutFirstPlacingCardR2T4 = removeLastTrickCard(withoutFirstPlacingCardR2T3,printingCardR2);	//Removing Place cards from R2					
				}
				
				else {
					printingCardR2= withoutFirstPlacingCardR2T3.get(randomizer.nextInt(withoutFirstPlacingCardR2T3.size()));
					ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
						placingCardNoMatchList.add(printingCardR2);
						
				
					ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
					ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
					placingCardR2 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
					maximumSuit = separateString(placingCardR2); 		
					
					withoutFirstPlacingCardR2T4 = removeLastTrickCard(withoutFirstPlacingCardR2T3,printingCardR2);	//Removing Place cards from R2
				}
				
				if(placingOrder.get(1).equals("Robot1")) {
					System.out.println("Robot1 Placing Second Card : " +printingCardR2);
				}
				else if(placingOrder.get(1).equals("Robot2")) {
					System.out.println("Robot2 Placing Second Card : " +printingCardR2);
				}
				else if(placingOrder.get(1).equals("Robot3")) {
					System.out.println("Robot3 Placing Second Card: " +printingCardR2);
				}
				

			}		
			
			//Third Placing Card
			if(placingOrder.get(2).equals("User")) {
				System.out.println("Cards In Hand : " + withoutFirstPlacingCardR3T3);
				System.out.print("Place The Third Card : ");
				while(true) {
					printingCardR3 = myObj.nextLine(); 
					ArrayList<String> placingCardRandomList = new ArrayList<String>();
					placingCardRandomList.add(printingCardR3);
					
					if(!withoutFirstPlacingCardR3T3.contains(printingCardR3)) {
						System.out.println("You Dont' have this card");
						continue;
					}
					ArrayList<String> speratedR3List = sperateLevelsAndSuits(placingCardRandomList);
					ArrayList<String> allNumberPlayingCardsR3 = allNumbersList(speratedR3List);
					placingCardR3 = allNumberPlayingCardsR3.get(0) + " - " + allNumberPlayingCardsR3.get(1);
					lastCardSuitR3T4 = separateString(placingCardR3); 
					
					ArrayList<String> sepratedPlayingCardsR3 = sperateLevelsAndSuits(withoutFirstPlacingCardR3T3);       //Second User Placing Card Edits
					ArrayList<String> allNumberPlayingCardsR3New = allNumbersList(sepratedPlayingCardsR3);
					ArrayList<String> placingCardsSuitsR3 = createListOfSuits(allNumberPlayingCardsR3New);
					
					if(placingCardsSuitsR3.contains(maximumSuit)) {
						if(!lastCardSuitR3T4.equals(maximumSuit)) {
							System.out.println("Select The Same Suit as Last placed suit");
							continue;
						}
					}
					
					else if (placingCardsSuitsR3.contains(trump)) {
						if(!lastCardSuitR3T4.equals(trump)) {
							System.out.println("You can place the Trump, Place it");
							continue;
						}
					}
					else {
						break;
					}
				break;	
				}
				
				withoutFirstPlacingCardR3T4 = removeLastTrickCard(withoutFirstPlacingCardR3T3,printingCardR3);	//Removing Place cards from R3
				maximumSuit = lastCardSuitR3T4;

			}
			else {
				//Third Card placing preparations
				sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR3T3,maximumSuit);         //Checking if the Third user has to place same suit
				
				if(sameSuitExists == true) {      //Selecting Same Suit for Third Player
					ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR3T3,maximumSuit);	 //Creating list of Cards which contains Same suit as Third placed Card
					
					ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
					ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
					ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
					ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
					placingCardR3 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
					maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
					printingCardR3 = allLettersString(placingCardR3);
					
					
					withoutFirstPlacingCardR3T4 = removeLastTrickCard(withoutFirstPlacingCardR3T3,printingCardR3);	//Removing Place cards from R3
				}
				
				else if(withoutFirstPlacingCardR3T3.contains(trump)){
					
					ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR3T3,trump);
					ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
					ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
					ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
					
					placingCardR3 = findMaximumLevelCard(trumpCardsInteger,trumpCardWithSameSuit);
					maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
					printingCardR3 = allLettersString(placingCardR3);
					
					
					withoutFirstPlacingCardR3T4 = removeLastTrickCard(withoutFirstPlacingCardR3T3,printingCardR3);	//Removing Place cards from R3
							
				}
				
				else {
					printingCardR3= withoutFirstPlacingCardR3T3.get(randomizer.nextInt(withoutFirstPlacingCardR3T3.size()));
					ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
						placingCardNoMatchList.add(printingCardR3);
						
				
					ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
					ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
					placingCardR3 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
					maximumSuit = separateString(placingCardR3); 			
					withoutFirstPlacingCardR3T4 = removeLastTrickCard(withoutFirstPlacingCardR3T3,printingCardR3);	//Removing Place cards from R3
				}
				if(placingOrder.get(2).equals("Robot1")) {
					System.out.println("Robot1 Placing Third Card: " +printingCardR3);
				}
				else if(placingOrder.get(2).equals("Robot2")) {
					System.out.println("Robot2 Placing Third Card : " +printingCardR3);
				}
				else if(placingOrder.get(2).equals("Robot3")) {
					System.out.println("Robot3 Placing Third Card : " +printingCardR3);
				}
			}
				
				//Fourth Card placing preparations
			if(placingOrder.get(3).equals("User")) {
				System.out.println("Cards In Hand : " + withoutFirstPlacingCardR4T3);
				System.out.print("Place The Fourth Card : ");
				while(true) {
					printingCardR4 = myObj.nextLine(); 
					ArrayList<String> placingCardRandomList = new ArrayList<String>();
					placingCardRandomList.add(printingCardR4);
					
					if(!withoutFirstPlacingCardR4T3.contains(printingCardR4)) {
						System.out.println("You Dont' have this card");
						continue;
					}
					ArrayList<String> speratedR4List = sperateLevelsAndSuits(placingCardRandomList);
					ArrayList<String> allNumberPlayingCardsR4 = allNumbersList(speratedR4List);
					placingCardR4 = allNumberPlayingCardsR4.get(0) + " - " + allNumberPlayingCardsR4.get(1);
					lastCardSuitR4T4 = separateString(placingCardR4); 
					
					ArrayList<String> sepratedPlayingCardsR4 = sperateLevelsAndSuits(withoutFirstPlacingCardR4T3);       //Second User Placing Card Edits
					ArrayList<String> allNumberPlayingCardsR4New = allNumbersList(sepratedPlayingCardsR4);
					ArrayList<String> placingCardsSuitsR4 = createListOfSuits(allNumberPlayingCardsR4New);
					

					if(placingCardsSuitsR4.contains(maximumSuit)) {
						if(!lastCardSuitR4T4.equals(maximumSuit)) {
							System.out.println("Select The Same Suit as Last placed suit");
							continue;
						}
					}
					
					else if (placingCardsSuitsR4.contains(trump)) {
						if(!lastCardSuitR4T4.equals(trump)) {
							System.out.println("You can place the Trump, Place it");
							continue;
						}
					}
					else {
						break;
					}
				break;	
				}
				
				withoutFirstPlacingCardR4T4 = removeLastTrickCard(withoutFirstPlacingCardR4T3,printingCardR4);	//Removing Place cards from R2
				maximumSuit = lastCardSuitR4T4;

			}
			else {
				sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR4T3,maximumSuit);         //Checking if the Fourth user has to place same suit
				
				if(sameSuitExists == true) {      //Selecting Same Suit for Second Player
					ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR4T3,maximumSuit);	 //Creating list of Cards which contains Same suit as Fourth placed Card
					ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
					ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
					ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
					ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);

					placingCardR4 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
					maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
					printingCardR4 = allLettersString(placingCardR4);
					withoutFirstPlacingCardR4T4 = removeLastTrickCard(withoutFirstPlacingCardR4T3,printingCardR4);	//Removing Place cards from R4
				}
				
				else if(withoutFirstPlacingCardR4T3.contains(trump)){
					
					ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR4T3,trump);
					ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
					ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
					ArrayList<String> trumpCardsSuits = createListOfSuits(allNumberTrumpCards);
					ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
					
					placingCardR4 = findMaximumLevelCard(trumpCardsInteger,trumpCardsSuits);
					maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
					printingCardR4 = allLettersString(placingCardR4);
					
					
					withoutFirstPlacingCardR4T4 = removeLastTrickCard(withoutFirstPlacingCardR4T3,printingCardR4);	//Removing Place cards from R4
							
				}
				
				else {
					printingCardR4= withoutFirstPlacingCardR4T3.get(randomizer.nextInt(withoutFirstPlacingCardR4T3.size()));
					ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
						placingCardNoMatchList.add(printingCardR4);
						
				
					ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
					ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
					placingCardR4 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
					maximumSuit = separateString(placingCardR4); 			
					
					withoutFirstPlacingCardR4T4 = removeLastTrickCard(withoutFirstPlacingCardR4T3,printingCardR4);	//Removing Place cards from R4
				}
				if(placingOrder.get(3).equals("Robot1")) {
					System.out.println("Robot1 Placing Fourth Card : " +printingCardR4);
				}
				else if(placingOrder.get(3).equals("Robot2")) {
					System.out.println("Robot2 Placing Fourth Card : " +printingCardR4);
				}
				else if(placingOrder.get(3).equals("Robot3")) {
					System.out.println("Robot3 Placing Fourth Card : " +printingCardR4);
				}
				
			}	
			
			
				//Finding the Winning Card
				winningCardsList = new ArrayList<String>();
					winningCardsList.add(placingCardR1);
					winningCardsList.add(placingCardR2);
					winningCardsList.add(placingCardR3);
					winningCardsList.add(placingCardR4);
				
				winningCardsAllNumbers = allNumbersList(winningCardsList);
				winningCardsEdited = sperateLevelsAndSuits(winningCardsAllNumbers);
				winningCardsSuits = createListOfSuits(winningCardsEdited);
				winningCardsInteger = createListOfLevels(winningCardsEdited);

				if(winningCardsEdited.contains(trump)) {
					
					ArrayList<String> winningCardsTrumpList = makeTrumpList(winningCardsList,trump);		
					ArrayList<String> winningCardsTrumpEdited = sperateLevelsAndSuits(winningCardsTrumpList);
					ArrayList<String> winningCardsTrumpSuits = createListOfSuits(winningCardsTrumpEdited);
					ArrayList<Integer> winningCardsTrumpInteger = createListOfLevels(winningCardsTrumpEdited);
					
					winningCard = findMaximumLevelCard(winningCardsTrumpInteger,winningCardsTrumpSuits);				
					maximumSuit  = findMaximumSuit(winningCardsTrumpInteger,winningCardsTrumpSuits);
					printingWinningCard = allLettersString(winningCard);
					
					System.out.println("Fourth Trick WinningCard Card is : " + printingWinningCard);
					

					
					
				}
				
				else {
					winningCard = findMaximumLevelCard(winningCardsInteger,winningCardsSuits);	
					maximumSuit  = findMaximumSuit(winningCardsInteger,winningCardsSuits);
					printingWinningCard = allLettersString(winningCard);
					System.out.println("Fourth Trick WinningCard Card is : " + printingWinningCard);
					
					
				}
				winningCardIndex = winningCardsList.indexOf(winningCard);
				winner = placingOrder.get(winningCardIndex);
				if(winner.equals("User")) {
					pointsUser += 2;	
					System.out.println(name + " Get 2 Points");
				}
				else if(winner.equals("Robot1")) {
					pointsRobot1 += 2;	
					System.out.println("Robot1 Get 2 Points");
				}
				else if(winner.equals("Robot2")) {
					pointsRobot2 += 2;	
					System.out.println("Robot2 Get 2 Points");
				}
				else if(winner.equals("Robot3")) {
					pointsRobot3 += 2;	
					System.out.println("Robot3 Get 2 Points");
				}
				
//---------------------------------------------------------------------------------------------->Fifth Trick<------------------------------------------------------------------------------------//
		System.out.println();
		System.out.println("----------Fifth Trick----------");
			if(placingOrder.get(0).equals("User")) {
				System.out.println("Cards In Hand : " + withoutFirstPlacingCardT4);
					while(true) {
						System.out.print("Place The First Card : ");
						printingCardR1 = myObj.nextLine(); 
						if(!withoutFirstPlacingCardT4.contains(printingCardR1)) {
							System.out.println("You Dont' have this card");
							continue;
						}
						break;
					}
				
					
					ArrayList<String> placingCardRandomList = new ArrayList<String>();
					placingCardRandomList.add(printingCardR1);
					 
					ArrayList<String> speratedList = sperateLevelsAndSuits(placingCardRandomList);
					ArrayList<String> allNumberPlayingCards = allNumbersList(speratedList);
					placingCardR1 = allNumberPlayingCards.get(0) + " - " + allNumberPlayingCards.get(1);
					lastCardSuitR1T5 = separateString(placingCardR1); 
					 
					withoutFirstPlacingCardT5 = removeLastTrickCard(withoutFirstPlacingCardT4,printingCardR1);	 //Removing placed card from First user
				}
				else {
					printingCardR1 = withoutFirstPlacingCardT4.get(randomizer.nextInt(withoutFirstPlacingCardT4.size())); //First User Placing the Card
					ArrayList<String> placingCardRandomList = new ArrayList<String>();
					placingCardRandomList.add(printingCardR1);
					
					ArrayList<String> speratedList = sperateLevelsAndSuits(placingCardRandomList);
					ArrayList<String> allNumberPlayingCards = allNumbersList(speratedList);
					placingCardR1 = allNumberPlayingCards.get(0) + " - " + allNumberPlayingCards.get(1);
					lastCardSuitR1T5 = separateString(placingCardR1); 
					maximumSuit = lastCardSuitR1T5;
					
					if(placingOrder.get(0).equals("Robot1")) {
						System.out.println("Robot1 Placing First Card : " +printingCardR1);
					}
					else if(placingOrder.get(0).equals("Robot2")) {
						System.out.println("Robot2 Placing First Card : " +printingCardR1);
					}
					else if(placingOrder.get(0).equals("Robot3")) {
						System.out.println("Robot3 Placing First Card : " +printingCardR1);
					}
					
					withoutFirstPlacingCardT5 = removeLastTrickCard(withoutFirstPlacingCardT4,printingCardR1);	 //Removing placed card from First user
				}
				//Second Card Placing
				if(placingOrder.get(1).equals("User")) {
					System.out.println("Cards In Hand : " + withoutFirstPlacingCardR2T4);
					System.out.print("Place The Second Card : ");
					while(true) {
						printingCardR2 = myObj.nextLine(); 
						ArrayList<String> placingCardRandomList = new ArrayList<String>();
						placingCardRandomList.add(printingCardR2);
						
						if(!withoutFirstPlacingCardR2T4.contains(printingCardR2)) {
							System.out.println("You Dont' have this card");
							continue;
						}
						ArrayList<String> speratedR2List = sperateLevelsAndSuits(placingCardRandomList);
						ArrayList<String> allNumberPlayingCardsR2 = allNumbersList(speratedR2List);
						placingCardR2 = allNumberPlayingCardsR2.get(0) + " - " + allNumberPlayingCardsR2.get(1);
						lastCardSuitR2T5 = separateString(placingCardR2); 
						
						ArrayList<String> sepratedPlayingCardsR2 = sperateLevelsAndSuits(withoutFirstPlacingCardR2T4);       //Second User Placing Card Edits
						ArrayList<String> allNumberPlayingCardsR2New = allNumbersList(sepratedPlayingCardsR2);
						ArrayList<String> placingCardsSuitsR2 = createListOfSuits(allNumberPlayingCardsR2New);

						
						if(placingCardsSuitsR2.contains(maximumSuit)) {
							if(!lastCardSuitR2T5.equals(maximumSuit)) {
								System.out.println("Select The Same Suit as Last placed suit");
								continue;
							}
						}
						
						else if (placingCardsSuitsR2.contains(trump)) {
							if(!lastCardSuitR2T5.equals(trump)) {
								System.out.println("You can place the Trump, Place it");
								continue;
							}
						}
						else {
							break;	
						}
					break;	
					}
					
					withoutFirstPlacingCardR2T5 = removeLastTrickCard(withoutFirstPlacingCardR2T4,printingCardR2);	//Removing Place cards from R2
					maximumSuit = lastCardSuitR2T5;

					

				}
				else {	
					//Second Card Placing
					sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR2T4, maximumSuit);         //Checking if the second user has to place same suit
				
					if(sameSuitExists == true) {      //Selecting Same Suit for Second Player
						
						ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR2T4,maximumSuit);	 //Creating list of Cards which contains Same suit as first placed Card
						ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
						ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
						ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
						ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
						placingCardR2 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
						maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
						printingCardR2 = allLettersString(placingCardR2);
						
						
						withoutFirstPlacingCardR2T5 = removeLastTrickCard(withoutFirstPlacingCardR2T4,printingCardR2);	//Removing Place cards from R2
					}
					
					else if(withoutFirstPlacingCardR2T4.contains(trump)){
						ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR2T4,trump);
						ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
						ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
						ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
						
						placingCardR2 = findMaximumLevelCard(trumpCardsInteger,trumpCardWithSameSuit);
						maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
						printingCardR2 = allLettersString(placingCardR2);
						withoutFirstPlacingCardR2T5 = removeLastTrickCard(withoutFirstPlacingCardR2T4,printingCardR2);	//Removing Place cards from R2					
					}
					
					else {
						printingCardR2= withoutFirstPlacingCardR2T4.get(randomizer.nextInt(withoutFirstPlacingCardR2T4.size()));
						ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
							placingCardNoMatchList.add(printingCardR2);
							
					
						ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
						ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
						placingCardR2 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
						maximumSuit = separateString(placingCardR2); 		
						
						withoutFirstPlacingCardR2T5 = removeLastTrickCard(withoutFirstPlacingCardR2T4,printingCardR2);	//Removing Place cards from R2
					}
					
					if(placingOrder.get(1).equals("Robot1")) {
						System.out.println("Robot1 Placing Second Card : " +printingCardR2);
					}
					else if(placingOrder.get(1).equals("Robot2")) {
						System.out.println("Robot2 Placing Second Card : " +printingCardR2);
					}
					else if(placingOrder.get(1).equals("Robot3")) {
						System.out.println("Robot3 Placing Second Card: " +printingCardR2);
					}
					

				}		
				
				//Third Placing Card
				if(placingOrder.get(2).equals("User")) {
					System.out.println("Cards In Hand : " + withoutFirstPlacingCardR3T4);
					System.out.print("Place The Third Card : ");
					while(true) {
						printingCardR3 = myObj.nextLine(); 
						ArrayList<String> placingCardRandomList = new ArrayList<String>();
						placingCardRandomList.add(printingCardR3);
						
						if(!withoutFirstPlacingCardR3T4.contains(printingCardR3)) {
							System.out.println("You Dont' have this card");
							continue;
						}
						ArrayList<String> speratedR3List = sperateLevelsAndSuits(placingCardRandomList);
						ArrayList<String> allNumberPlayingCardsR3 = allNumbersList(speratedR3List);
						placingCardR3 = allNumberPlayingCardsR3.get(0) + " - " + allNumberPlayingCardsR3.get(1);
						lastCardSuitR3T5 = separateString(placingCardR3); 
						
						ArrayList<String> sepratedPlayingCardsR3 = sperateLevelsAndSuits(withoutFirstPlacingCardR3T4);       
						ArrayList<String> allNumberPlayingCardsR3New = allNumbersList(sepratedPlayingCardsR3);
						ArrayList<String> placingCardsSuitsR3 = createListOfSuits(allNumberPlayingCardsR3New);
						
						if(placingCardsSuitsR3.contains(maximumSuit)) {
							if(!lastCardSuitR3T5.equals(maximumSuit)) {
								System.out.println("Select The Same Suit as Last placed suit");
								continue;
							}
						}
						
						else if (placingCardsSuitsR3.contains(trump)) {
							if(!lastCardSuitR3T5.equals(trump)) {
								System.out.println("You can place the Trump, Place it");
								continue;
							}
						}
						else {
							break;
						}
					break;	
					}
					
					withoutFirstPlacingCardR3T5 = removeLastTrickCard(withoutFirstPlacingCardR3T4,printingCardR3);	//Removing Place cards from R3
					maximumSuit = lastCardSuitR3T5;

				}
				else {
					//Third Card placing preparations
					sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR3T4,maximumSuit);         //Checking if the Third user has to place same suit
					
					if(sameSuitExists == true) {      //Selecting Same Suit for Third Player
						ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR3T4,maximumSuit);	 //Creating list of Cards which contains Same suit as Third placed Card
						
						ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
						ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
						ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
						ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
						placingCardR3 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
						maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
						printingCardR3 = allLettersString(placingCardR3);
						
						
						withoutFirstPlacingCardR3T5 = removeLastTrickCard(withoutFirstPlacingCardR3T4,printingCardR3);	//Removing Place cards from R3
					}
					
					else if(withoutFirstPlacingCardR3T4.contains(trump)){
						
						ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR3T4,trump);
						ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
						ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
						ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
						
						placingCardR3 = findMaximumLevelCard(trumpCardsInteger,trumpCardWithSameSuit);
						maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
						printingCardR3 = allLettersString(placingCardR3);
						
						
						withoutFirstPlacingCardR3T5 = removeLastTrickCard(withoutFirstPlacingCardR3T4,printingCardR3);	//Removing Place cards from R3
								
					}
					
					else {
						printingCardR3= withoutFirstPlacingCardR3T4.get(randomizer.nextInt(withoutFirstPlacingCardR3T4.size()));
						ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
							placingCardNoMatchList.add(printingCardR3);
							
					
						ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
						ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
						placingCardR3 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
						maximumSuit = separateString(placingCardR3); 			
						withoutFirstPlacingCardR3T5 = removeLastTrickCard(withoutFirstPlacingCardR3T4,printingCardR3);	//Removing Place cards from R3
					}
					if(placingOrder.get(2).equals("Robot1")) {
						System.out.println("Robot1 Placing Third Card: " +printingCardR3);
					}
					else if(placingOrder.get(2).equals("Robot2")) {
						System.out.println("Robot2 Placing Third Card : " +printingCardR3);
					}
					else if(placingOrder.get(2).equals("Robot3")) {
						System.out.println("Robot3 Placing Third Card : " +printingCardR3);
					}
				}
					
					//Fourth Card placing preparations
				if(placingOrder.get(3).equals("User")) {
					System.out.println("Cards In Hand : " + withoutFirstPlacingCardR4T4);
					System.out.print("Place The Fourth Card : ");
					while(true) {
						printingCardR4 = myObj.nextLine(); 
						ArrayList<String> placingCardRandomList = new ArrayList<String>();
						placingCardRandomList.add(printingCardR4);
						
						if(!withoutFirstPlacingCardR4T4.contains(printingCardR4)) {
							System.out.println("You Dont' have this card");
							continue;
						}
						ArrayList<String> speratedR4List = sperateLevelsAndSuits(placingCardRandomList);
						ArrayList<String> allNumberPlayingCardsR4 = allNumbersList(speratedR4List);
						placingCardR4 = allNumberPlayingCardsR4.get(0) + " - " + allNumberPlayingCardsR4.get(1);
						lastCardSuitR4T5 = separateString(placingCardR4); 
						
						ArrayList<String> sepratedPlayingCardsR4 = sperateLevelsAndSuits(withoutFirstPlacingCardR4T4);       //Second User Placing Card Edits
						ArrayList<String> allNumberPlayingCardsR4New = allNumbersList(sepratedPlayingCardsR4);
						ArrayList<String> placingCardsSuitsR4 = createListOfSuits(allNumberPlayingCardsR4New);
						

						if(placingCardsSuitsR4.contains(maximumSuit)) {
							if(!lastCardSuitR4T5.equals(maximumSuit)) {
								System.out.println("Select The Same Suit as Last placed suit");
								continue;
							}
						}
						
						else if (placingCardsSuitsR4.contains(trump)) {
							if(!lastCardSuitR4T5.equals(trump)) {
								System.out.println("You can place the Trump, Place it");
								continue;
							}
						}
						else {
							break;
						}
					break;	
					}
					
					withoutFirstPlacingCardR4T5 = removeLastTrickCard(withoutFirstPlacingCardR4T4,printingCardR4);	//Removing Place cards from R2
					maximumSuit = lastCardSuitR4T5;

				}
				else {
					sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR4T4,maximumSuit);         //Checking if the Fourth user has to place same suit
					
					if(sameSuitExists == true) {      //Selecting Same Suit for Second Player
						ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR4T4,maximumSuit);	 //Creating list of Cards which contains Same suit as Fourth placed Card
						ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
						ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
						ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
						ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);

						placingCardR4 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
						maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
						printingCardR4 = allLettersString(placingCardR4);
						withoutFirstPlacingCardR4T5 = removeLastTrickCard(withoutFirstPlacingCardR4T4,printingCardR4);	//Removing Place cards from R4
					}
					
					else if(withoutFirstPlacingCardR4T4.contains(trump)){
						
						ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR4T4,trump);
						ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
						ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
						ArrayList<String> trumpCardsSuits = createListOfSuits(allNumberTrumpCards);
						ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
						
						placingCardR4 = findMaximumLevelCard(trumpCardsInteger,trumpCardsSuits);
						maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
						printingCardR4 = allLettersString(placingCardR4);
						
						
						withoutFirstPlacingCardR4T5 = removeLastTrickCard(withoutFirstPlacingCardR4T4,printingCardR4);	//Removing Place cards from R4
								
					}
					
					else {
						printingCardR4= withoutFirstPlacingCardR4T4.get(randomizer.nextInt(withoutFirstPlacingCardR4T4.size()));
						ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
							placingCardNoMatchList.add(printingCardR4);
							
					
						ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
						ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
						placingCardR4 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
						maximumSuit = separateString(placingCardR4); 			
						
						withoutFirstPlacingCardR4T5 = removeLastTrickCard(withoutFirstPlacingCardR4T4,printingCardR4);	//Removing Place cards from R4
					}
					if(placingOrder.get(3).equals("Robot1")) {
						System.out.println("Robot1 Placing Fourth Card : " +printingCardR4);
					}
					else if(placingOrder.get(3).equals("Robot2")) {
						System.out.println("Robot2 Placing Fourth Card : " +printingCardR4);
					}
					else if(placingOrder.get(3).equals("Robot3")) {
						System.out.println("Robot3 Placing Fourth Card : " +printingCardR4);
					}
					
				}	
				
				
					//Finding the Winning Card
					winningCardsList = new ArrayList<String>();
						winningCardsList.add(placingCardR1);
						winningCardsList.add(placingCardR2);
						winningCardsList.add(placingCardR3);
						winningCardsList.add(placingCardR4);
					
					winningCardsAllNumbers = allNumbersList(winningCardsList);
					winningCardsEdited = sperateLevelsAndSuits(winningCardsAllNumbers);
					winningCardsSuits = createListOfSuits(winningCardsEdited);
					winningCardsInteger = createListOfLevels(winningCardsEdited);

					if(winningCardsEdited.contains(trump)) {
						
						ArrayList<String> winningCardsTrumpList = makeTrumpList(winningCardsList,trump);		
						ArrayList<String> winningCardsTrumpEdited = sperateLevelsAndSuits(winningCardsTrumpList);
						ArrayList<String> winningCardsTrumpSuits = createListOfSuits(winningCardsTrumpEdited);
						ArrayList<Integer> winningCardsTrumpInteger = createListOfLevels(winningCardsTrumpEdited);
						
						winningCard = findMaximumLevelCard(winningCardsTrumpInteger,winningCardsTrumpSuits);				
						maximumSuit  = findMaximumSuit(winningCardsTrumpInteger,winningCardsTrumpSuits);
						printingWinningCard = allLettersString(winningCard);
						
						System.out.println("Fifth Trick WinningCard Card is : " + printingWinningCard);
						

						
						
					}
					
					else {
						winningCard = findMaximumLevelCard(winningCardsInteger,winningCardsSuits);	
						maximumSuit  = findMaximumSuit(winningCardsInteger,winningCardsSuits);
						printingWinningCard = allLettersString(winningCard);
						System.out.println("Fifth  Trick WinningCard Card is : " + printingWinningCard);
						
						
					}
					winningCardIndex = winningCardsList.indexOf(winningCard);
					winner = placingOrder.get(winningCardIndex);
					if(winner.equals("User")) {
						pointsUser += 2;	
						System.out.println(name + " Get 2 Points");
					}
					else if(winner.equals("Robot1")) {
						pointsRobot1 += 2;	
						System.out.println("Robot1 Get 2 Points");
					}
					else if(winner.equals("Robot2")) {
						pointsRobot2 += 2;	
						System.out.println("Robot2 Get 2 Points");
					}
					else if(winner.equals("Robot3")) {
						pointsRobot3 += 2;	
						System.out.println("Robot3 Get 2 Points");
					}

//---------------------------------------------------------------------------------------------->Sixth Trick<------------------------------------------------------------------------------------//
		System.out.println();
		System.out.println("----------Sixth Trick----------");
			if(placingOrder.get(0).equals("User")) {
				System.out.println("Cards In Hand : " + withoutFirstPlacingCardT5);
					while(true) {
						System.out.print("Place The First Card : ");
						printingCardR1 = myObj.nextLine(); 
						if(!withoutFirstPlacingCardT5.contains(printingCardR1)) {
							System.out.println("You Dont' have this card");
							continue;
						}
						break;
					}
				
					
					ArrayList<String> placingCardRandomList = new ArrayList<String>();
					placingCardRandomList.add(printingCardR1);
					 
					ArrayList<String> speratedList = sperateLevelsAndSuits(placingCardRandomList);
					ArrayList<String> allNumberPlayingCards = allNumbersList(speratedList);
					placingCardR1 = allNumberPlayingCards.get(0) + " - " + allNumberPlayingCards.get(1);
					lastCardSuitR1T6 = separateString(placingCardR1); 
					 
					withoutFirstPlacingCardT6 = removeLastTrickCard(withoutFirstPlacingCardT5,printingCardR1);	 //Removing placed card from First user
				}
				else {
					printingCardR1 = withoutFirstPlacingCardT5.get(randomizer.nextInt(withoutFirstPlacingCardT5.size())); //First User Placing the Card
					ArrayList<String> placingCardRandomList = new ArrayList<String>();
					placingCardRandomList.add(printingCardR1);
					
					ArrayList<String> speratedList = sperateLevelsAndSuits(placingCardRandomList);
					ArrayList<String> allNumberPlayingCards = allNumbersList(speratedList);
					placingCardR1 = allNumberPlayingCards.get(0) + " - " + allNumberPlayingCards.get(1);
					lastCardSuitR1T6 = separateString(placingCardR1); 
					maximumSuit = lastCardSuitR1T6;
					
					if(placingOrder.get(0).equals("Robot1")) {
						System.out.println("Robot1 Placing First Card : " +printingCardR1);
					}
					else if(placingOrder.get(0).equals("Robot2")) {
						System.out.println("Robot2 Placing First Card : " +printingCardR1);
					}
					else if(placingOrder.get(0).equals("Robot3")) {
						System.out.println("Robot3 Placing First Card : " +printingCardR1);
					}
					
					withoutFirstPlacingCardT6 = removeLastTrickCard(withoutFirstPlacingCardT5,printingCardR1);	 //Removing placed card from First user
				}
				//Second Card Placing
				if(placingOrder.get(1).equals("User")) {
					System.out.println("Cards In Hand : " + withoutFirstPlacingCardR2T5);
					System.out.print("Place The Second Card : ");
					while(true) {
						printingCardR2 = myObj.nextLine(); 
						ArrayList<String> placingCardRandomList = new ArrayList<String>();
						placingCardRandomList.add(printingCardR2);
						
						if(!withoutFirstPlacingCardR2T5.contains(printingCardR2)) {
							System.out.println("You Dont' have this card");
							continue;
						}
						ArrayList<String> speratedR2List = sperateLevelsAndSuits(placingCardRandomList);
						ArrayList<String> allNumberPlayingCardsR2 = allNumbersList(speratedR2List);
						placingCardR2 = allNumberPlayingCardsR2.get(0) + " - " + allNumberPlayingCardsR2.get(1);
						lastCardSuitR2T6 = separateString(placingCardR2); 
						
						ArrayList<String> sepratedPlayingCardsR2 = sperateLevelsAndSuits(withoutFirstPlacingCardR2T5);       //Second User Placing Card Edits
						ArrayList<String> allNumberPlayingCardsR2New = allNumbersList(sepratedPlayingCardsR2);
						ArrayList<String> placingCardsSuitsR2 = createListOfSuits(allNumberPlayingCardsR2New);

						
						if(placingCardsSuitsR2.contains(maximumSuit)) {
							if(!lastCardSuitR2T6.equals(maximumSuit)) {
								System.out.println("Select The Same Suit as Last placed suit");
								continue;
							}
						}
						
						else if (placingCardsSuitsR2.contains(trump)) {
							if(!lastCardSuitR2T6.equals(trump)) {
								System.out.println("You can place the Trump, Place it");
								continue;
							}
						}
						else {
							break;	
						}
					break;	
					}
					
					withoutFirstPlacingCardR2T6 = removeLastTrickCard(withoutFirstPlacingCardR2T5,printingCardR2);	//Removing Place cards from R2
					maximumSuit = lastCardSuitR2T6;

					

				}
				else {	
					//Second Card Placing
					sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR2T5, maximumSuit);         //Checking if the second user has to place same suit
				
					if(sameSuitExists == true) {      //Selecting Same Suit for Second Player
						
						ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR2T5,maximumSuit);	 //Creating list of Cards which contains Same suit as first placed Card
						ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
						ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
						ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
						ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
						placingCardR2 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
						maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
						printingCardR2 = allLettersString(placingCardR2);
						
						
						withoutFirstPlacingCardR2T6 = removeLastTrickCard(withoutFirstPlacingCardR2T5,printingCardR2);	//Removing Place cards from R2
					}
					
					else if(withoutFirstPlacingCardR2T5.contains(trump)){
						ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR2T5,trump);
						ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
						ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
						ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
						
						placingCardR2 = findMaximumLevelCard(trumpCardsInteger,trumpCardWithSameSuit);
						maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
						printingCardR2 = allLettersString(placingCardR2);
						withoutFirstPlacingCardR2T6 = removeLastTrickCard(withoutFirstPlacingCardR2T5,printingCardR2);	//Removing Place cards from R2					
					}
					
					else {
						printingCardR2= withoutFirstPlacingCardR2T5.get(randomizer.nextInt(withoutFirstPlacingCardR2T5.size()));
						ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
							placingCardNoMatchList.add(printingCardR2);
							
					
						ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
						ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
						placingCardR2 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
						maximumSuit = separateString(placingCardR2); 		
						
						withoutFirstPlacingCardR2T6 = removeLastTrickCard(withoutFirstPlacingCardR2T5,printingCardR2);	//Removing Place cards from R2
					}
					
					if(placingOrder.get(1).equals("Robot1")) {
						System.out.println("Robot1 Placing Second Card : " +printingCardR2);
					}
					else if(placingOrder.get(1).equals("Robot2")) {
						System.out.println("Robot2 Placing Second Card : " +printingCardR2);
					}
					else if(placingOrder.get(1).equals("Robot3")) {
						System.out.println("Robot3 Placing Second Card: " +printingCardR2);
					}
					

				}		
				
				//Third Placing Card
				if(placingOrder.get(2).equals("User")) {
					System.out.println("Cards In Hand : " + withoutFirstPlacingCardR3T5);
					System.out.print("Place The Third Card : ");
					while(true) {
						printingCardR3 = myObj.nextLine(); 
						ArrayList<String> placingCardRandomList = new ArrayList<String>();
						placingCardRandomList.add(printingCardR3);
						
						if(!withoutFirstPlacingCardR3T5.contains(printingCardR3)) {
							System.out.println("You Dont' have this card");
							continue;
						}
						ArrayList<String> speratedR3List = sperateLevelsAndSuits(placingCardRandomList);
						ArrayList<String> allNumberPlayingCardsR3 = allNumbersList(speratedR3List);
						placingCardR3 = allNumberPlayingCardsR3.get(0) + " - " + allNumberPlayingCardsR3.get(1);
						lastCardSuitR3T6 = separateString(placingCardR3); 
						
						ArrayList<String> sepratedPlayingCardsR3 = sperateLevelsAndSuits(withoutFirstPlacingCardR3T5);       
						ArrayList<String> allNumberPlayingCardsR3New = allNumbersList(sepratedPlayingCardsR3);
						ArrayList<String> placingCardsSuitsR3 = createListOfSuits(allNumberPlayingCardsR3New);
						
						if(placingCardsSuitsR3.contains(maximumSuit)) {
							if(!lastCardSuitR3T6.equals(maximumSuit)) {
								System.out.println("Select The Same Suit as Last placed suit");
								continue;
							}
						}
						
						else if (placingCardsSuitsR3.contains(trump)) {
							if(!lastCardSuitR3T6.equals(trump)) {
								System.out.println("You can place the Trump, Place it");
								continue;
							}
						}
						else {
							break;
						}
					break;	
					}
					
					withoutFirstPlacingCardR3T6 = removeLastTrickCard(withoutFirstPlacingCardR3T5,printingCardR3);	//Removing Place cards from R3
					maximumSuit = lastCardSuitR3T6;

				}
				else {
					//Third Card placing preparations
					sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR3T5,maximumSuit);         //Checking if the Third user has to place same suit
					
					if(sameSuitExists == true) {      //Selecting Same Suit for Third Player
						ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR3T5,maximumSuit);	 //Creating list of Cards which contains Same suit as Third placed Card
						
						ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
						ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
						ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
						ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
						placingCardR3 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
						maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
						printingCardR3 = allLettersString(placingCardR3);
						
						
						withoutFirstPlacingCardR3T6 = removeLastTrickCard(withoutFirstPlacingCardR3T5,printingCardR3);	//Removing Place cards from R3
					}
					
					else if(withoutFirstPlacingCardR3T5.contains(trump)){
						
						ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR3T5,trump);
						ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
						ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
						ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
						
						placingCardR3 = findMaximumLevelCard(trumpCardsInteger,trumpCardWithSameSuit);
						maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
						printingCardR3 = allLettersString(placingCardR3);
						
						
						withoutFirstPlacingCardR3T6 = removeLastTrickCard(withoutFirstPlacingCardR3T5,printingCardR3);	//Removing Place cards from R3
								
					}
					
					else {
						printingCardR3= withoutFirstPlacingCardR3T5.get(randomizer.nextInt(withoutFirstPlacingCardR3T5.size()));
						ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
							placingCardNoMatchList.add(printingCardR3);
							
					
						ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
						ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
						placingCardR3 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
						maximumSuit = separateString(placingCardR3); 			
						withoutFirstPlacingCardR3T6 = removeLastTrickCard(withoutFirstPlacingCardR3T5,printingCardR3);	//Removing Place cards from R3
					}
					if(placingOrder.get(2).equals("Robot1")) {
						System.out.println("Robot1 Placing Third Card: " +printingCardR3);
					}
					else if(placingOrder.get(2).equals("Robot2")) {
						System.out.println("Robot2 Placing Third Card : " +printingCardR3);
					}
					else if(placingOrder.get(2).equals("Robot3")) {
						System.out.println("Robot3 Placing Third Card : " +printingCardR3);
					}
				}
					
					//Fourth Card placing preparations
				if(placingOrder.get(3).equals("User")) {
					System.out.println("Cards In Hand : " + withoutFirstPlacingCardR4T5);
					System.out.print("Place The Fourth Card : ");
					while(true) {
						printingCardR4 = myObj.nextLine(); 
						ArrayList<String> placingCardRandomList = new ArrayList<String>();
						placingCardRandomList.add(printingCardR4);
						
						if(!withoutFirstPlacingCardR4T5.contains(printingCardR4)) {
							System.out.println("You Dont' have this card");
							continue;
						}
						ArrayList<String> speratedR4List = sperateLevelsAndSuits(placingCardRandomList);
						ArrayList<String> allNumberPlayingCardsR4 = allNumbersList(speratedR4List);
						placingCardR4 = allNumberPlayingCardsR4.get(0) + " - " + allNumberPlayingCardsR4.get(1);
						lastCardSuitR4T6 = separateString(placingCardR4); 
						
						ArrayList<String> sepratedPlayingCardsR4 = sperateLevelsAndSuits(withoutFirstPlacingCardR4T5);       //Second User Placing Card Edits
						ArrayList<String> allNumberPlayingCardsR4New = allNumbersList(sepratedPlayingCardsR4);
						ArrayList<String> placingCardsSuitsR4 = createListOfSuits(allNumberPlayingCardsR4New);
						

						if(placingCardsSuitsR4.contains(maximumSuit)) {
							if(!lastCardSuitR4T6.equals(maximumSuit)) {
								System.out.println("Select The Same Suit as Last placed suit");
								continue;
							}
						}
						
						else if (placingCardsSuitsR4.contains(trump)) {
							if(!lastCardSuitR4T6.equals(trump)) {
								System.out.println("You can place the Trump, Place it");
								continue;
							}
						}
						else {
							break;
						}
					break;	
					}
					
					withoutFirstPlacingCardR4T6 = removeLastTrickCard(withoutFirstPlacingCardR4T5,printingCardR4);	//Removing Place cards from R2
					maximumSuit = lastCardSuitR4T6;

				}
				else {
					sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR4T5,maximumSuit);         //Checking if the Fourth user has to place same suit
					
					if(sameSuitExists == true) {      //Selecting Same Suit for Second Player
						ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR4T5,maximumSuit);	 //Creating list of Cards which contains Same suit as Fourth placed Card
						ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
						ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
						ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
						ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);

						placingCardR4 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
						maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
						printingCardR4 = allLettersString(placingCardR4);
						withoutFirstPlacingCardR4T6 = removeLastTrickCard(withoutFirstPlacingCardR4T5,printingCardR4);	//Removing Place cards from R4
					}
					
					else if(withoutFirstPlacingCardR4T5.contains(trump)){
						
						ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR4T5,trump);
						ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
						ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
						ArrayList<String> trumpCardsSuits = createListOfSuits(allNumberTrumpCards);
						ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
						
						placingCardR4 = findMaximumLevelCard(trumpCardsInteger,trumpCardsSuits);
						maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
						printingCardR4 = allLettersString(placingCardR4);
						
						
						withoutFirstPlacingCardR4T6 = removeLastTrickCard(withoutFirstPlacingCardR4T5,printingCardR4);	//Removing Place cards from R4
								
					}
					
					else {
						printingCardR4= withoutFirstPlacingCardR4T5.get(randomizer.nextInt(withoutFirstPlacingCardR4T5.size()));
						ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
							placingCardNoMatchList.add(printingCardR4);
							
					
						ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
						ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
						placingCardR4 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
						maximumSuit = separateString(placingCardR4); 			
						
						withoutFirstPlacingCardR4T6 = removeLastTrickCard(withoutFirstPlacingCardR4T5,printingCardR4);	//Removing Place cards from R4
					}
					if(placingOrder.get(3).equals("Robot1")) {
						System.out.println("Robot1 Placing Fourth Card : " +printingCardR4);
					}
					else if(placingOrder.get(3).equals("Robot2")) {
						System.out.println("Robot2 Placing Fourth Card : " +printingCardR4);
					}
					else if(placingOrder.get(3).equals("Robot3")) {
						System.out.println("Robot3 Placing Fourth Card : " +printingCardR4);
					}
					
				}	
				
				
					//Finding the Winning Card
					winningCardsList = new ArrayList<String>();
						winningCardsList.add(placingCardR1);
						winningCardsList.add(placingCardR2);
						winningCardsList.add(placingCardR3);
						winningCardsList.add(placingCardR4);
					
					winningCardsAllNumbers = allNumbersList(winningCardsList);
					winningCardsEdited = sperateLevelsAndSuits(winningCardsAllNumbers);
					winningCardsSuits = createListOfSuits(winningCardsEdited);
					winningCardsInteger = createListOfLevels(winningCardsEdited);

					if(winningCardsEdited.contains(trump)) {
						
						ArrayList<String> winningCardsTrumpList = makeTrumpList(winningCardsList,trump);		
						ArrayList<String> winningCardsTrumpEdited = sperateLevelsAndSuits(winningCardsTrumpList);
						ArrayList<String> winningCardsTrumpSuits = createListOfSuits(winningCardsTrumpEdited);
						ArrayList<Integer> winningCardsTrumpInteger = createListOfLevels(winningCardsTrumpEdited);
						
						winningCard = findMaximumLevelCard(winningCardsTrumpInteger,winningCardsTrumpSuits);				
						maximumSuit  = findMaximumSuit(winningCardsTrumpInteger,winningCardsTrumpSuits);
						printingWinningCard = allLettersString(winningCard);
						
						System.out.println("Sixth Trick WinningCard Card is : " + printingWinningCard);
						

						
						
					}
					
					else {
						winningCard = findMaximumLevelCard(winningCardsInteger,winningCardsSuits);	
						maximumSuit  = findMaximumSuit(winningCardsInteger,winningCardsSuits);
						printingWinningCard = allLettersString(winningCard);
						System.out.println("Sixth  Trick WinningCard Card is : " + printingWinningCard);
						
						
					}
					winningCardIndex = winningCardsList.indexOf(winningCard);
					winner = placingOrder.get(winningCardIndex);
					if(winner.equals("User")) {
						pointsUser += 2;	
						System.out.println(name + " Get 2 Points");
					}
					else if(winner.equals("Robot1")) {
						pointsRobot1 += 2;	
						System.out.println("Robot1 Get 2 Points");
					}
					else if(winner.equals("Robot2")) {
						pointsRobot2 += 2;	
						System.out.println("Robot2 Get 2 Points");
					}
					else if(winner.equals("Robot3")) {
						pointsRobot3 += 2;	
						System.out.println("Robot3 Get 2 Points");
					}

					
//---------------------------------------------------------------------------------------------->Seventh Trick<------------------------------------------------------------------------------------//
	System.out.println();
	System.out.println("----------Seventh Trick----------");
		if(placingOrder.get(0).equals("User")) {
			System.out.println("Cards In Hand : " + withoutFirstPlacingCardT6);
				while(true) {
					System.out.print("Place The First Card : ");
					printingCardR1 = myObj.nextLine(); 
					if(!withoutFirstPlacingCardT6.contains(printingCardR1)) {
						System.out.println("You Dont' have this card");
						continue;
					}
					break;
				}
			
				
				ArrayList<String> placingCardRandomList = new ArrayList<String>();
				placingCardRandomList.add(printingCardR1);
				 
				ArrayList<String> speratedList = sperateLevelsAndSuits(placingCardRandomList);
				ArrayList<String> allNumberPlayingCards = allNumbersList(speratedList);
				placingCardR1 = allNumberPlayingCards.get(0) + " - " + allNumberPlayingCards.get(1);
				lastCardSuitR1T7 = separateString(placingCardR1); 
				 
				withoutFirstPlacingCardT7 = removeLastTrickCard(withoutFirstPlacingCardT6,printingCardR1);	 //Removing placed card from First user
			}
			else {
				printingCardR1 = withoutFirstPlacingCardT6.get(randomizer.nextInt(withoutFirstPlacingCardT6.size())); //First User Placing the Card
				ArrayList<String> placingCardRandomList = new ArrayList<String>();
				placingCardRandomList.add(printingCardR1);
				
				ArrayList<String> speratedList = sperateLevelsAndSuits(placingCardRandomList);
				ArrayList<String> allNumberPlayingCards = allNumbersList(speratedList);
				placingCardR1 = allNumberPlayingCards.get(0) + " - " + allNumberPlayingCards.get(1);
				lastCardSuitR1T7 = separateString(placingCardR1); 
				maximumSuit = lastCardSuitR1T7;
				
				if(placingOrder.get(0).equals("Robot1")) {
					System.out.println("Robot1 Placing First Card : " +printingCardR1);
				}
				else if(placingOrder.get(0).equals("Robot2")) {
					System.out.println("Robot2 Placing First Card : " +printingCardR1);
				}
				else if(placingOrder.get(0).equals("Robot3")) {
					System.out.println("Robot3 Placing First Card : " +printingCardR1);
				}
				
				withoutFirstPlacingCardT7 = removeLastTrickCard(withoutFirstPlacingCardT6,printingCardR1);	 //Removing placed card from First user
			}
			//Second Card Placing
			if(placingOrder.get(1).equals("User")) {
				System.out.println("Cards In Hand : " + withoutFirstPlacingCardR2T6);
				System.out.print("Place The Second Card : ");
				while(true) {
					printingCardR2 = myObj.nextLine(); 
					ArrayList<String> placingCardRandomList = new ArrayList<String>();
					placingCardRandomList.add(printingCardR2);
					
					if(!withoutFirstPlacingCardR2T6.contains(printingCardR2)) {
						System.out.println("You Dont' have this card");
						continue;
					}
					ArrayList<String> speratedR2List = sperateLevelsAndSuits(placingCardRandomList);
					ArrayList<String> allNumberPlayingCardsR2 = allNumbersList(speratedR2List);
					placingCardR2 = allNumberPlayingCardsR2.get(0) + " - " + allNumberPlayingCardsR2.get(1);
					lastCardSuitR2T7 = separateString(placingCardR2); 
					
					ArrayList<String> sepratedPlayingCardsR2 = sperateLevelsAndSuits(withoutFirstPlacingCardR2T6);       //Second User Placing Card Edits
					ArrayList<String> allNumberPlayingCardsR2New = allNumbersList(sepratedPlayingCardsR2);
					ArrayList<String> placingCardsSuitsR2 = createListOfSuits(allNumberPlayingCardsR2New);
	
					
					if(placingCardsSuitsR2.contains(maximumSuit)) {
						if(!lastCardSuitR2T7.equals(maximumSuit)) {
							System.out.println("Select The Same Suit as Last placed suit");
							continue;
						}
					}
					
					else if (placingCardsSuitsR2.contains(trump)) {
						if(!lastCardSuitR2T7.equals(trump)) {
							System.out.println("You can place the Trump, Place it");
							continue;
						}
					}
					else {
						break;	
					}
				break;	
				}
				
				withoutFirstPlacingCardR2T7 = removeLastTrickCard(withoutFirstPlacingCardR2T6,printingCardR2);	//Removing Place cards from R2
				maximumSuit = lastCardSuitR2T7;
	
				
	
			}
			else {	
				//Second Card Placing
				sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR2T6, maximumSuit);         //Checking if the second user has to place same suit
			
				if(sameSuitExists == true) {      //Selecting Same Suit for Second Player
					
					ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR2T6,maximumSuit);	 //Creating list of Cards which contains Same suit as first placed Card
					ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
					ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
					ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
					ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
					placingCardR2 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
					maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
					printingCardR2 = allLettersString(placingCardR2);
					
					
					withoutFirstPlacingCardR2T7 = removeLastTrickCard(withoutFirstPlacingCardR2T6,printingCardR2);	//Removing Place cards from R2
				}
				
				else if(withoutFirstPlacingCardR2T6.contains(trump)){
					ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR2T6,trump);
					ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
					ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
					ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
					
					placingCardR2 = findMaximumLevelCard(trumpCardsInteger,trumpCardWithSameSuit);
					maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
					printingCardR2 = allLettersString(placingCardR2);
					withoutFirstPlacingCardR2T7 = removeLastTrickCard(withoutFirstPlacingCardR2T6,printingCardR2);	//Removing Place cards from R2					
				}
				
				else {
					printingCardR2= withoutFirstPlacingCardR2T6.get(randomizer.nextInt(withoutFirstPlacingCardR2T6.size()));
					ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
						placingCardNoMatchList.add(printingCardR2);
						
				
					ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
					ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
					placingCardR2 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
					maximumSuit = separateString(placingCardR2); 		
					
					withoutFirstPlacingCardR2T7 = removeLastTrickCard(withoutFirstPlacingCardR2T6,printingCardR2);	//Removing Place cards from R2
				}
				
				if(placingOrder.get(1).equals("Robot1")) {
					System.out.println("Robot1 Placing Second Card : " +printingCardR2);
				}
				else if(placingOrder.get(1).equals("Robot2")) {
					System.out.println("Robot2 Placing Second Card : " +printingCardR2);
				}
				else if(placingOrder.get(1).equals("Robot3")) {
					System.out.println("Robot3 Placing Second Card: " +printingCardR2);
				}
				
	
			}		
			
			//Third Placing Card
			if(placingOrder.get(2).equals("User")) {
				System.out.println("Cards In Hand : " + withoutFirstPlacingCardR3T6);
				System.out.print("Place The Third Card : ");
				while(true) {
					printingCardR3 = myObj.nextLine(); 
					ArrayList<String> placingCardRandomList = new ArrayList<String>();
					placingCardRandomList.add(printingCardR3);
					
					if(!withoutFirstPlacingCardR3T6.contains(printingCardR3)) {
						System.out.println("You Dont' have this card");
						continue;
					}
					ArrayList<String> speratedR3List = sperateLevelsAndSuits(placingCardRandomList);
					ArrayList<String> allNumberPlayingCardsR3 = allNumbersList(speratedR3List);
					placingCardR3 = allNumberPlayingCardsR3.get(0) + " - " + allNumberPlayingCardsR3.get(1);
					lastCardSuitR3T7 = separateString(placingCardR3); 
					
					ArrayList<String> sepratedPlayingCardsR3 = sperateLevelsAndSuits(withoutFirstPlacingCardR3T6);       
					ArrayList<String> allNumberPlayingCardsR3New = allNumbersList(sepratedPlayingCardsR3);
					ArrayList<String> placingCardsSuitsR3 = createListOfSuits(allNumberPlayingCardsR3New);
					
					if(placingCardsSuitsR3.contains(maximumSuit)) {
						if(!lastCardSuitR3T7.equals(maximumSuit)) {
							System.out.println("Select The Same Suit as Last placed suit");
							continue;
						}
					}
					
					else if (placingCardsSuitsR3.contains(trump)) {
						if(!lastCardSuitR3T7.equals(trump)) {
							System.out.println("You can place the Trump, Place it");
							continue;
						}
					}
					else {
						break;
					}
				break;	
				}
				
				withoutFirstPlacingCardR3T7 = removeLastTrickCard(withoutFirstPlacingCardR3T6,printingCardR3);	//Removing Place cards from R3
				maximumSuit = lastCardSuitR3T7;
	
			}
			else {
				//Third Card placing preparations
				sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR3T6,maximumSuit);         //Checking if the Third user has to place same suit
				
				if(sameSuitExists == true) {      //Selecting Same Suit for Third Player
					ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR3T6,maximumSuit);	 //Creating list of Cards which contains Same suit as Third placed Card
					
					ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
					ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
					ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
					ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
					placingCardR3 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
					maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
					printingCardR3 = allLettersString(placingCardR3);
					
					
					withoutFirstPlacingCardR3T7 = removeLastTrickCard(withoutFirstPlacingCardR3T6,printingCardR3);	//Removing Place cards from R3
				}
				
				else if(withoutFirstPlacingCardR3T6.contains(trump)){
					
					ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR3T6,trump);
					ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
					ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
					ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
					
					placingCardR3 = findMaximumLevelCard(trumpCardsInteger,trumpCardWithSameSuit);
					maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
					printingCardR3 = allLettersString(placingCardR3);
					
					
					withoutFirstPlacingCardR3T7 = removeLastTrickCard(withoutFirstPlacingCardR3T6,printingCardR3);	//Removing Place cards from R3
							
				}
				
				else {
					printingCardR3= withoutFirstPlacingCardR3T6.get(randomizer.nextInt(withoutFirstPlacingCardR3T6.size()));
					ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
						placingCardNoMatchList.add(printingCardR3);
						
				
					ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
					ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
					placingCardR3 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
					maximumSuit = separateString(placingCardR3); 			
					withoutFirstPlacingCardR3T7 = removeLastTrickCard(withoutFirstPlacingCardR3T6,printingCardR3);	//Removing Place cards from R3
				}
				if(placingOrder.get(2).equals("Robot1")) {
					System.out.println("Robot1 Placing Third Card: " +printingCardR3);
				}
				else if(placingOrder.get(2).equals("Robot2")) {
					System.out.println("Robot2 Placing Third Card : " +printingCardR3);
				}
				else if(placingOrder.get(2).equals("Robot3")) {
					System.out.println("Robot3 Placing Third Card : " +printingCardR3);
				}
			}
				
				//Fourth Card placing preparations
			if(placingOrder.get(3).equals("User")) {
				System.out.println("Cards In Hand : " + withoutFirstPlacingCardR4T6);
				System.out.print("Place The Fourth Card : ");
				while(true) {
					printingCardR4 = myObj.nextLine(); 
					ArrayList<String> placingCardRandomList = new ArrayList<String>();
					placingCardRandomList.add(printingCardR4);
					
					if(!withoutFirstPlacingCardR4T6.contains(printingCardR4)) {
						System.out.println("You Dont' have this card");
						continue;
					}
					ArrayList<String> speratedR4List = sperateLevelsAndSuits(placingCardRandomList);
					ArrayList<String> allNumberPlayingCardsR4 = allNumbersList(speratedR4List);
					placingCardR4 = allNumberPlayingCardsR4.get(0) + " - " + allNumberPlayingCardsR4.get(1);
					lastCardSuitR4T7 = separateString(placingCardR4); 
					
					ArrayList<String> sepratedPlayingCardsR4 = sperateLevelsAndSuits(withoutFirstPlacingCardR4T6);       //Second User Placing Card Edits
					ArrayList<String> allNumberPlayingCardsR4New = allNumbersList(sepratedPlayingCardsR4);
					ArrayList<String> placingCardsSuitsR4 = createListOfSuits(allNumberPlayingCardsR4New);
					
	
					if(placingCardsSuitsR4.contains(maximumSuit)) {
						if(!lastCardSuitR4T7.equals(maximumSuit)) {
							System.out.println("Select The Same Suit as Last placed suit");
							continue;
						}
					}
					
					else if (placingCardsSuitsR4.contains(trump)) {
						if(!lastCardSuitR4T7.equals(trump)) {
							System.out.println("You can place the Trump, Place it");
							continue;
						}
					}
					else {
						break;
					}
				break;	
				}
				
				withoutFirstPlacingCardR4T7 = removeLastTrickCard(withoutFirstPlacingCardR4T6,printingCardR4);	//Removing Place cards from R2
				maximumSuit = lastCardSuitR4T7;
	
			}
			else {
				sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR4T6,maximumSuit);         //Checking if the Fourth user has to place same suit
				
				if(sameSuitExists == true) {      //Selecting Same Suit for Second Player
					ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR4T6,maximumSuit);	 //Creating list of Cards which contains Same suit as Fourth placed Card
					ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
					ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
					ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
					ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
	
					placingCardR4 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
					maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
					printingCardR4 = allLettersString(placingCardR4);
					withoutFirstPlacingCardR4T7 = removeLastTrickCard(withoutFirstPlacingCardR4T6,printingCardR4);	//Removing Place cards from R4
				}
				
				else if(withoutFirstPlacingCardR4T6.contains(trump)){
					
					ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR4T6,trump);
					ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
					ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
					ArrayList<String> trumpCardsSuits = createListOfSuits(allNumberTrumpCards);
					ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
					
					placingCardR4 = findMaximumLevelCard(trumpCardsInteger,trumpCardsSuits);
					maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
					printingCardR4 = allLettersString(placingCardR4);
					
					
					withoutFirstPlacingCardR4T7 = removeLastTrickCard(withoutFirstPlacingCardR4T6,printingCardR4);	//Removing Place cards from R4
							
				}
				
				else {
					printingCardR4= withoutFirstPlacingCardR4T6.get(randomizer.nextInt(withoutFirstPlacingCardR4T6.size()));
					ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
						placingCardNoMatchList.add(printingCardR4);
						
				
					ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
					ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
					placingCardR4 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
					maximumSuit = separateString(placingCardR4); 			
					
					withoutFirstPlacingCardR4T7 = removeLastTrickCard(withoutFirstPlacingCardR4T6,printingCardR4);	//Removing Place cards from R4
				}
				if(placingOrder.get(3).equals("Robot1")) {
					System.out.println("Robot1 Placing Fourth Card : " +printingCardR4);
				}
				else if(placingOrder.get(3).equals("Robot2")) {
					System.out.println("Robot2 Placing Fourth Card : " +printingCardR4);
				}
				else if(placingOrder.get(3).equals("Robot3")) {
					System.out.println("Robot3 Placing Fourth Card : " +printingCardR4);
				}
				
			}	
			
			
				//Finding the Winning Card
				winningCardsList = new ArrayList<String>();
					winningCardsList.add(placingCardR1);
					winningCardsList.add(placingCardR2);
					winningCardsList.add(placingCardR3);
					winningCardsList.add(placingCardR4);
				
				winningCardsAllNumbers = allNumbersList(winningCardsList);
				winningCardsEdited = sperateLevelsAndSuits(winningCardsAllNumbers);
				winningCardsSuits = createListOfSuits(winningCardsEdited);
				winningCardsInteger = createListOfLevels(winningCardsEdited);
	
				if(winningCardsEdited.contains(trump)) {
					
					ArrayList<String> winningCardsTrumpList = makeTrumpList(winningCardsList,trump);		
					ArrayList<String> winningCardsTrumpEdited = sperateLevelsAndSuits(winningCardsTrumpList);
					ArrayList<String> winningCardsTrumpSuits = createListOfSuits(winningCardsTrumpEdited);
					ArrayList<Integer> winningCardsTrumpInteger = createListOfLevels(winningCardsTrumpEdited);
					
					winningCard = findMaximumLevelCard(winningCardsTrumpInteger,winningCardsTrumpSuits);				
					maximumSuit  = findMaximumSuit(winningCardsTrumpInteger,winningCardsTrumpSuits);
					printingWinningCard = allLettersString(winningCard);
					
					System.out.println("Seventh Trick WinningCard Card is : " + printingWinningCard);
					
	
					
					
				}
				
				else {
					winningCard = findMaximumLevelCard(winningCardsInteger,winningCardsSuits);	
					maximumSuit  = findMaximumSuit(winningCardsInteger,winningCardsSuits);
					printingWinningCard = allLettersString(winningCard);
					System.out.println("Seventh  Trick WinningCard Card is : " + printingWinningCard);
					
					
				}
				winningCardIndex = winningCardsList.indexOf(winningCard);
				winner = placingOrder.get(winningCardIndex);
				if(winner.equals("User")) {
					pointsUser += 2;	
					System.out.println(name + " Get 2 Points");
				}
				else if(winner.equals("Robot1")) {
					pointsRobot1 += 2;	
					System.out.println("Robot1 Get 2 Points");
				}
				else if(winner.equals("Robot2")) {
					pointsRobot2 += 2;	
					System.out.println("Robot2 Get 2 Points");
				}
				else if(winner.equals("Robot3")) {
					pointsRobot3 += 2;	
					System.out.println("Robot3 Get 2 Points");
				}
//---------------------------------------------------------------------------------------------->Eighth Trick<------------------------------------------------------------------------------------//
		System.out.println();
		System.out.println("----------Eighth Trick----------");
			if(placingOrder.get(0).equals("User")) {
				System.out.println("Cards In Hand : " + withoutFirstPlacingCardT7);
					while(true) {
						System.out.print("Place The First Card : ");
						printingCardR1 = myObj.nextLine(); 
						if(!withoutFirstPlacingCardT7.contains(printingCardR1)) {
							System.out.println("You Dont' have this card");
							continue;
						}
						break;
					}
				
					
					ArrayList<String> placingCardRandomList = new ArrayList<String>();
					placingCardRandomList.add(printingCardR1);
					 
					ArrayList<String> speratedList = sperateLevelsAndSuits(placingCardRandomList);
					ArrayList<String> allNumberPlayingCards = allNumbersList(speratedList);
					placingCardR1 = allNumberPlayingCards.get(0) + " - " + allNumberPlayingCards.get(1);
					lastCardSuitR1T8 = separateString(placingCardR1); 
					 
				}
				else {
					printingCardR1 = withoutFirstPlacingCardT7.get(randomizer.nextInt(withoutFirstPlacingCardT7.size())); //First User Placing the Card
					ArrayList<String> placingCardRandomList = new ArrayList<String>();
					placingCardRandomList.add(printingCardR1);
					
					ArrayList<String> speratedList = sperateLevelsAndSuits(placingCardRandomList);
					ArrayList<String> allNumberPlayingCards = allNumbersList(speratedList);
					placingCardR1 = allNumberPlayingCards.get(0) + " - " + allNumberPlayingCards.get(1);
					lastCardSuitR1T8 = separateString(placingCardR1); 
					maximumSuit = lastCardSuitR1T8;
					
					if(placingOrder.get(0).equals("Robot1")) {
						System.out.println("Robot1 Placing First Card : " +printingCardR1);
					}
					else if(placingOrder.get(0).equals("Robot2")) {
						System.out.println("Robot2 Placing First Card : " +printingCardR1);
					}
					else if(placingOrder.get(0).equals("Robot3")) {
						System.out.println("Robot3 Placing First Card : " +printingCardR1);
					}
					
				}
				//Second Card Placing
				if(placingOrder.get(1).equals("User")) {
					System.out.println("Cards In Hand : " + withoutFirstPlacingCardR2T7);
					System.out.print("Place The Second Card : ");
					while(true) {
						printingCardR2 = myObj.nextLine(); 
						ArrayList<String> placingCardRandomList = new ArrayList<String>();
						placingCardRandomList.add(printingCardR2);
						
						if(!withoutFirstPlacingCardR2T7.contains(printingCardR2)) {
							System.out.println("You Dont' have this card");
							continue;
						}
						ArrayList<String> speratedR2List = sperateLevelsAndSuits(placingCardRandomList);
						ArrayList<String> allNumberPlayingCardsR2 = allNumbersList(speratedR2List);
						placingCardR2 = allNumberPlayingCardsR2.get(0) + " - " + allNumberPlayingCardsR2.get(1);
						lastCardSuitR2T8 = separateString(placingCardR2); 
						
						ArrayList<String> sepratedPlayingCardsR2 = sperateLevelsAndSuits(withoutFirstPlacingCardR2T7);       //Second User Placing Card Edits
						ArrayList<String> allNumberPlayingCardsR2New = allNumbersList(sepratedPlayingCardsR2);
						ArrayList<String> placingCardsSuitsR2 = createListOfSuits(allNumberPlayingCardsR2New);
		
						
						if(placingCardsSuitsR2.contains(maximumSuit)) {
							if(!lastCardSuitR2T8.equals(maximumSuit)) {
								System.out.println("Select The Same Suit as Last placed suit");
								continue;
							}
						}
						
						else if (placingCardsSuitsR2.contains(trump)) {
							if(!lastCardSuitR2T8.equals(trump)) {
								System.out.println("You can place the Trump, Place it");
								continue;
							}
						}
						else {
							break;	
						}
					break;	
					}
					
					maximumSuit = lastCardSuitR2T8;
		
					
		
				}
				else {	
					//Second Card Placing
					sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR2T7, maximumSuit);         //Checking if the second user has to place same suit
				
					if(sameSuitExists == true) {      //Selecting Same Suit for Second Player
						
						ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR2T7,maximumSuit);	 //Creating list of Cards which contains Same suit as first placed Card
						ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
						ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
						ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
						ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
						placingCardR2 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
						maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
						printingCardR2 = allLettersString(placingCardR2);
						
						
					}
					
					else if(withoutFirstPlacingCardR2T7.contains(trump)){
						ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR2T7,trump);
						ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
						ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
						ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
						
						placingCardR2 = findMaximumLevelCard(trumpCardsInteger,trumpCardWithSameSuit);
						maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
						printingCardR2 = allLettersString(placingCardR2);
					}
					
					else {
						printingCardR2= withoutFirstPlacingCardR2T7.get(randomizer.nextInt(withoutFirstPlacingCardR2T7.size()));
						ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
							placingCardNoMatchList.add(printingCardR2);
							
					
						ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
						ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
						placingCardR2 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
						maximumSuit = separateString(placingCardR2); 		
						
					}
					
					if(placingOrder.get(1).equals("Robot1")) {
						System.out.println("Robot1 Placing Second Card : " +printingCardR2);
					}
					else if(placingOrder.get(1).equals("Robot2")) {
						System.out.println("Robot2 Placing Second Card : " +printingCardR2);
					}
					else if(placingOrder.get(1).equals("Robot3")) {
						System.out.println("Robot3 Placing Second Card: " +printingCardR2);
					}
					
		
				}		
				
				//Third Placing Card
				if(placingOrder.get(2).equals("User")) {
					System.out.println("Cards In Hand : " + withoutFirstPlacingCardR3T7);
					System.out.print("Place The Third Card : ");
					while(true) {
						printingCardR3 = myObj.nextLine(); 
						ArrayList<String> placingCardRandomList = new ArrayList<String>();
						placingCardRandomList.add(printingCardR3);
						
						if(!withoutFirstPlacingCardR3T7.contains(printingCardR3)) {
							System.out.println("You Dont' have this card");
							continue;
						}
						ArrayList<String> speratedR3List = sperateLevelsAndSuits(placingCardRandomList);
						ArrayList<String> allNumberPlayingCardsR3 = allNumbersList(speratedR3List);
						placingCardR3 = allNumberPlayingCardsR3.get(0) + " - " + allNumberPlayingCardsR3.get(1);
						lastCardSuitR3T8 = separateString(placingCardR3); 
						
						ArrayList<String> sepratedPlayingCardsR3 = sperateLevelsAndSuits(withoutFirstPlacingCardR3T7);       
						ArrayList<String> allNumberPlayingCardsR3New = allNumbersList(sepratedPlayingCardsR3);
						ArrayList<String> placingCardsSuitsR3 = createListOfSuits(allNumberPlayingCardsR3New);
						
						if(placingCardsSuitsR3.contains(maximumSuit)) {
							if(!lastCardSuitR3T8.equals(maximumSuit)) {
								System.out.println("Select The Same Suit as Last placed suit");
								continue;
							}
						}
						
						else if (placingCardsSuitsR3.contains(trump)) {
							if(!lastCardSuitR3T8.equals(trump)) {
								System.out.println("You can place the Trump, Place it");
								continue;
							}
						}
						else {
							break;
						}
					break;	
					}
					
					maximumSuit = lastCardSuitR3T8;
		
				}
				else {
					//Third Card placing preparations
					sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR3T7,maximumSuit);         //Checking if the Third user has to place same suit
					
					if(sameSuitExists == true) {      //Selecting Same Suit for Third Player
						ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR3T7,maximumSuit);	 //Creating list of Cards which contains Same suit as Third placed Card
						
						ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
						ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
						ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
						ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
						placingCardR3 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
						maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
						printingCardR3 = allLettersString(placingCardR3);
						
						
					}
					
					else if(withoutFirstPlacingCardR3T7.contains(trump)){
						
						ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR3T7,trump);
						ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
						ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
						ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
						
						placingCardR3 = findMaximumLevelCard(trumpCardsInteger,trumpCardWithSameSuit);
						maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
						printingCardR3 = allLettersString(placingCardR3);
						
						
								
					}
					
					else {
						printingCardR3= withoutFirstPlacingCardR3T7.get(randomizer.nextInt(withoutFirstPlacingCardR3T7.size()));
						ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
							placingCardNoMatchList.add(printingCardR3);
							
					
						ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
						ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
						placingCardR3 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
						maximumSuit = separateString(placingCardR3); 			
					}
					if(placingOrder.get(2).equals("Robot1")) {
						System.out.println("Robot1 Placing Third Card: " +printingCardR3);
					}
					else if(placingOrder.get(2).equals("Robot2")) {
						System.out.println("Robot2 Placing Third Card : " +printingCardR3);
					}
					else if(placingOrder.get(2).equals("Robot3")) {
						System.out.println("Robot3 Placing Third Card : " +printingCardR3);
					}
				}
					
					//Fourth Card placing preparations
				if(placingOrder.get(3).equals("User")) {
					System.out.println("Cards In Hand : " + withoutFirstPlacingCardR4T7);
					System.out.print("Place The Fourth Card : ");
					while(true) {
						printingCardR4 = myObj.nextLine(); 
						ArrayList<String> placingCardRandomList = new ArrayList<String>();
						placingCardRandomList.add(printingCardR4);
						
						if(!withoutFirstPlacingCardR4T7.contains(printingCardR4)) {
							System.out.println("You Dont' have this card");
							continue;
						}
						ArrayList<String> speratedR4List = sperateLevelsAndSuits(placingCardRandomList);
						ArrayList<String> allNumberPlayingCardsR4 = allNumbersList(speratedR4List);
						placingCardR4 = allNumberPlayingCardsR4.get(0) + " - " + allNumberPlayingCardsR4.get(1);
						lastCardSuitR4T8 = separateString(placingCardR4); 
						
						ArrayList<String> sepratedPlayingCardsR4 = sperateLevelsAndSuits(withoutFirstPlacingCardR4T7);       //Second User Placing Card Edits
						ArrayList<String> allNumberPlayingCardsR4New = allNumbersList(sepratedPlayingCardsR4);
						ArrayList<String> placingCardsSuitsR4 = createListOfSuits(allNumberPlayingCardsR4New);
						
		
						if(placingCardsSuitsR4.contains(maximumSuit)) {
							if(!lastCardSuitR4T8.equals(maximumSuit)) {
								System.out.println("Select The Same Suit as Last placed suit");
								continue;
							}
						}
						
						else if (placingCardsSuitsR4.contains(trump)) {
							if(!lastCardSuitR4T8.equals(trump)) {
								System.out.println("You can place the Trump, Place it");
								continue;
							}
						}
						else {
							break;
						}
					break;	
					}
					
					maximumSuit = lastCardSuitR4T8;
		
				}
				else {
					sameSuitExists = isSameSuitExist(withoutFirstPlacingCardR4T7,maximumSuit);         //Checking if the Fourth user has to place same suit
					
					if(sameSuitExists == true) {      //Selecting Same Suit for Second Player
						ArrayList<String> placingCardsWithSameSuit = createSameSuitList(withoutFirstPlacingCardR4T7,maximumSuit);	 //Creating list of Cards which contains Same suit as Fourth placed Card
						ArrayList<String> sepratedPlayingCardsWithSameSuit = sperateLevelsAndSuits(placingCardsWithSameSuit);
						ArrayList<String> allNumberPlayingCards = allNumbersList(sepratedPlayingCardsWithSameSuit);
						ArrayList<String> placingCardsSuits = createListOfSuits(allNumberPlayingCards);
						ArrayList<Integer> placingCardsInteger = createListOfLevels(allNumberPlayingCards);
		
						placingCardR4 = findMaximumLevelCard(placingCardsInteger,placingCardsSuits);
						maximumSuit  = findMaximumSuit(placingCardsInteger,placingCardsSuits);
						printingCardR4 = allLettersString(placingCardR4);
					}
					
					else if(withoutFirstPlacingCardR4T7.contains(trump)){
						
						ArrayList<String>trumpCardList = makeTrumpList(withoutFirstPlacingCardR4T7,trump);
						ArrayList<String> trumpCardWithSameSuit = sperateLevelsAndSuits(trumpCardList);
						ArrayList<String> allNumberTrumpCards = allNumbersList(trumpCardWithSameSuit);
						ArrayList<String> trumpCardsSuits = createListOfSuits(allNumberTrumpCards);
						ArrayList<Integer> trumpCardsInteger = createListOfLevels(allNumberTrumpCards);
						
						placingCardR4 = findMaximumLevelCard(trumpCardsInteger,trumpCardsSuits);
						maximumSuit  = findMaximumSuit(trumpCardsInteger,trumpCardWithSameSuit);
						printingCardR4 = allLettersString(placingCardR4);
						
						
								
					}
					
					else {
						printingCardR4= withoutFirstPlacingCardR4T7.get(randomizer.nextInt(withoutFirstPlacingCardR4T7.size()));
						ArrayList<String> placingCardNoMatchList = new ArrayList<String>();
							placingCardNoMatchList.add(printingCardR4);
							
					
						ArrayList<String> speratedNoMatch = sperateLevelsAndSuits(placingCardNoMatchList);
						ArrayList<String> seperatedAllNumbers = allNumbersList(speratedNoMatch);
						placingCardR4 = seperatedAllNumbers.get(0) + " - " + seperatedAllNumbers.get(1);
						maximumSuit = separateString(placingCardR4); 			
					}
					if(placingOrder.get(3).equals("Robot1")) {
						System.out.println("Robot1 Placing Fourth Card : " +printingCardR4);
					}
					else if(placingOrder.get(3).equals("Robot2")) {
						System.out.println("Robot2 Placing Fourth Card : " +printingCardR4);
					}
					else if(placingOrder.get(3).equals("Robot3")) {
						System.out.println("Robot3 Placing Fourth Card : " +printingCardR4);
					}
					
				}	
				
				
					//Finding the Winning Card
					winningCardsList = new ArrayList<String>();
						winningCardsList.add(placingCardR1);
						winningCardsList.add(placingCardR2);
						winningCardsList.add(placingCardR3);
						winningCardsList.add(placingCardR4);
					
					winningCardsAllNumbers = allNumbersList(winningCardsList);
					winningCardsEdited = sperateLevelsAndSuits(winningCardsAllNumbers);
					winningCardsSuits = createListOfSuits(winningCardsEdited);
					winningCardsInteger = createListOfLevels(winningCardsEdited);
		
					if(winningCardsEdited.contains(trump)) {
						
						ArrayList<String> winningCardsTrumpList = makeTrumpList(winningCardsList,trump);		
						ArrayList<String> winningCardsTrumpEdited = sperateLevelsAndSuits(winningCardsTrumpList);
						ArrayList<String> winningCardsTrumpSuits = createListOfSuits(winningCardsTrumpEdited);
						ArrayList<Integer> winningCardsTrumpInteger = createListOfLevels(winningCardsTrumpEdited);
						
						winningCard = findMaximumLevelCard(winningCardsTrumpInteger,winningCardsTrumpSuits);				
						maximumSuit  = findMaximumSuit(winningCardsTrumpInteger,winningCardsTrumpSuits);
						printingWinningCard = allLettersString(winningCard);
						
						System.out.println("Eighth Trick WinningCard Card is : " + printingWinningCard);
						
		
						
						
					}
					
					else {
						winningCard = findMaximumLevelCard(winningCardsInteger,winningCardsSuits);	
						maximumSuit  = findMaximumSuit(winningCardsInteger,winningCardsSuits);
						printingWinningCard = allLettersString(winningCard);
						System.out.println("Eighth  Trick WinningCard Card is : " + printingWinningCard);
						
						
					}
					winningCardIndex = winningCardsList.indexOf(winningCard);
					winner = placingOrder.get(winningCardIndex);
					if(winner.equals("User")) {
						pointsUser += 2;	
						System.out.println(name + " Get 2 Points");
					}
					else if(winner.equals("Robot1")) {
						pointsRobot1 += 2;	
						System.out.println("Robot1 Get 2 Points");
					}
					else if(winner.equals("Robot2")) {
						pointsRobot2 += 2;	
						System.out.println("Robot2 Get 2 Points");
					}
					else if(winner.equals("Robot3")) {
						pointsRobot3 += 2;	
						System.out.println("Robot3 Get 2 Points");
					}
			System.out.println();
			System.out.println(name + " Points : " + pointsUser);
			System.out.println("Robot1 Points : " + pointsRobot1);
			System.out.println("Robot2 Points : " + pointsRobot2);
			System.out.println("Robot3 Points : " + pointsRobot3);
			
			ArrayList<Integer> pointsList = new ArrayList<Integer>();
				pointsList.add(pointsUser);
				pointsList.add(pointsRobot1);
				pointsList.add(pointsRobot2);
				pointsList.add(pointsRobot3);
				System.out.println();
				int winnerIndex = pointsList.indexOf(Collections.max(pointsList));
			
			ArrayList<Integer> removeWinner = new ArrayList<>(pointsList);    //Creating a Array list without winning points(Only one will be removed)
				for (int i = 0; i < removeWinner.size(); i++) {
			    	  if(removeWinner.get(i) == removeWinner.get(winnerIndex)) {
			    		  removeWinner.remove(i);
			    		  break;
			    	  }
			    	  
			      }
				
				if(pointsList.get(winnerIndex) == removeWinner.get(0)) {  //Checking if the winning points exists in removed list, if it exists, match is tied.
					results = "Tied";
				}
				if(pointsList.get(winnerIndex) == removeWinner.get(1)) {
					results = "Tied";
				}
				if(pointsList.get(winnerIndex) == removeWinner.get(2)) {
					results = "Tied";
				}

				
				if(!results.equals("Tied")) {
					if(pointsList.get(winnerIndex) == pointsList.get(0)) {
						
						System.out.println("----Winner Is " + name+"----");
						finalWinner = name;
					}
					else if(pointsList.get(winnerIndex) == pointsList.get(1)) {
						System.out.println("----Winner Is Robot1----");
					}
					else if(pointsList.get(winnerIndex) == pointsList.get(2)) {
						System.out.println("----Winner Is Robot2----");
					}
					else if(pointsList.get(winnerIndex) == pointsList.get(3)) {
						System.out.println("----Winner Is Robot3----");
					}
				}
				else {
					System.out.println("Match Tied");
				}
				
				if(!finalWinner.equals(name)) {
					finalResults = "Lost";
				}
				else {
					finalResults = "Won";
				}
			    
				 try {
				      FileWriter myWriter = new FileWriter("scoreBoard.txt",true);
				      myWriter.write(name + " - " + finalResults +"\n");
				      myWriter.close();
				    } catch (IOException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				    }
				    

	}
	
}
		
		


