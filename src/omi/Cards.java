package omi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
	public Cards(){
		 Collections.shuffle(allCards);
	}
	
	List<String> allCardsList = List.of("Club - A", "Club - K", "Club - Q", "Club - J", "Club - 10", "Club - 9", "Club - 8", "Club - 7", "Diamond - A", "Diamond - K", "Diamond - Q", "Diamond - J", "Diamond - 10", "Diamond - 9", "Diamond - 8","Diamond - 7","Heart - A", "Heart - K", "Heart - Q", "Heart - J", "Heart - 10", "Heart - 9", "Heart - 8", "Heart - 7", "Spade - A", "Spade - K", "Spade - Q", "Spade - J", "Spade - 10", "Spade - 9", "Spade - 8", "Spade - 7");
	ArrayList<String> allCards = new ArrayList<>(allCardsList);
	 
	public ArrayList<String> getallCards(){
		return allCards;
	}
	
	

	 
}
