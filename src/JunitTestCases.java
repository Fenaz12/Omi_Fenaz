import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JunitTestCases {

	@Test	
	public void allNumbersListTest() {
		TrumpSelector jTester = new TrumpSelector();
		List<String> cardList = List.of("Club","A","Diamond","K","Club","10","Diamond","Q");
		List<String> exceptedList = List.of("Club","14","Diamond","13","Club","10","Diamond","12");
		ArrayList<String> exceptedList2 = new ArrayList<>(exceptedList);
		ArrayList<String> cardList2 = new ArrayList<>(cardList);
		
		ArrayList<String> changedList = jTester.allNumbersList(cardList2);
		assertEquals(exceptedList2,changedList);
	}
	@Test	
	public void allLettersStringTest() {
		TrumpSelector jTester = new TrumpSelector();
		String cardToChange = "14";
		String excepted = "A";
		
		String changed = jTester.allLettersString(cardToChange);
		assertEquals(excepted,changed);
	}
	
	@Test	
	public void createListOfLevelsTest() {
		TrumpSelector jTester = new TrumpSelector();
		List<String> cardList = List.of("Club","14","Diamond","13","Club","10","Diamond","12");
		List<Integer> exceptedList = List.of(14,13,10,12);
		ArrayList<Integer> exceptedList2 = new ArrayList<>(exceptedList);
		ArrayList<String> cardList2 = new ArrayList<>(cardList);
		
		ArrayList<Integer> changedList = jTester.createListOfLevels(cardList2);
		assertEquals(exceptedList2,changedList);
	}
	
	@Test	
	public void createListOfSuitsTest() {
		TrumpSelector jTester = new TrumpSelector();
		List<String> cardList = List.of("Club","14","Diamond","13","Club","10","Diamond","12");
		List<String> exceptedList = List.of("Club","Diamond","Club","Diamond");
		ArrayList<String> exceptedList2 = new ArrayList<>(exceptedList);
		ArrayList<String> cardList2 = new ArrayList<>(cardList);
		
		ArrayList<String> changedList = jTester.createListOfSuits(cardList2);
		assertEquals(exceptedList2,changedList);
	}
	@Test	
	public void findMaximumLevelCardTest() {
		TrumpSelector jTester = new TrumpSelector();
		List<Integer> cardListInteger = List.of(14,13,10,12);
		List<String> cardListSuit = List.of("Club","Diamond","Club","Diamond");
		ArrayList<String> cardListSuit2 = new ArrayList<>(cardListSuit);
		ArrayList<Integer> cardListInteger2 = new ArrayList<>(cardListInteger);
		
		String exceptedCard = "Club - 14";
		String card = jTester.findMaximumLevelCard(cardListInteger2,cardListSuit2);
		assertEquals(exceptedCard,card);
	}
	@Test	
	public void findMaximumSuitTest() {
		TrumpSelector jTester = new TrumpSelector();
		List<Integer> cardListInteger = List.of(14,13,10,12);
		List<String> cardListSuit = List.of("Club","Diamond","Club","Diamond");
		ArrayList<String> cardListSuit2 = new ArrayList<>(cardListSuit);
		ArrayList<Integer> cardListInteger2 = new ArrayList<>(cardListInteger);
		
		String exceptedCard = "Club";
		String suit = jTester.findMaximumSuit(cardListInteger2,cardListSuit2);
		assertEquals(exceptedCard,suit);
	}
	@Test
	public void removeLastTrickCardTest() {
		TrumpSelector jTester = new TrumpSelector();
		
		List<String> cardList = List.of("Club - A","Diamond - K","Club - 10","Diamond - Q");
		List<String> exceptedList = List.of("Diamond - K","Club - 10","Diamond - Q");
		String removingCard = "Club - A";
		ArrayList<String> exceptedList2 = new ArrayList<>(exceptedList);
		ArrayList<String> cardList2 = new ArrayList<>(cardList);
		
		ArrayList<String> changedList = jTester.removeLastTrickCard(cardList2,removingCard);
		assertEquals(exceptedList2,changedList);
	}
	@Test
	public void isSameSuitExistTest() {
		TrumpSelector jTester = new TrumpSelector();
		
		List<String> cardList = List.of("Club - A","Diamond - K","Club - 10","Diamond - Q");
		boolean exceptedResults = true;
		String maximumSuit = "Club";
		ArrayList<String> cardList2 = new ArrayList<>(cardList);
		
		boolean results = jTester.isSameSuitExist(cardList2,maximumSuit);
		assertEquals(exceptedResults,results);
	}
	
	@Test
	public void createSameSuitListTest() {
		TrumpSelector jTester = new TrumpSelector();
		
		List<String> cardList = List.of("Club - A","Diamond - K","Club - 10","Diamond - Q");
		List<String> exceptedList = List.of("Club - A", "Club - 10");
		String maximumSuit = "Club";
		ArrayList<String> cardList2 = new ArrayList<>(cardList);
		ArrayList<String> exceptedList2 = new ArrayList<>(exceptedList);

		ArrayList<String> results = jTester.createSameSuitList(cardList2,maximumSuit);
		assertEquals(exceptedList2,results);
	}
	
	@Test
	public void makeTrumpList() {
		TrumpSelector jTester = new TrumpSelector();
		
		List<String> cardList = List.of("Club - A","Diamond - K","Club - 10","Diamond - Q");
		List<String> exceptedList = List.of("Club - A", "Club - 10");
		String trump = "Club";
		ArrayList<String> cardList2 = new ArrayList<>(cardList);
		ArrayList<String> exceptedList2 = new ArrayList<>(exceptedList);

		ArrayList<String> results = jTester.makeTrumpList(cardList2,trump);
		assertEquals(exceptedList2,results);
	}
}
