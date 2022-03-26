package omi;
import java.util.ArrayList;
import java.util.List;

public class RobotCards {
	
	private ArrayList<String> robot1Cards;
	private ArrayList<String> robot2Cards;
	private ArrayList<String> robot3Cards;
	private ArrayList<String> userCards;
	
	
	RobotCards(){
		this.robot1Cards = robot1ShareCards();
		this.robot2Cards = robot2ShareCards();
		this.robot3Cards = robot3ShareCards();
		this.userCards = userShareCards();
	}
	
	public ArrayList<String> getRobot1(){
		return this.robot1Cards;
	}
	public ArrayList<String> getRobot2(){
		return this.robot2Cards;
	}
	public ArrayList<String> getRobot3(){
		return this.robot3Cards;
	}
	public ArrayList<String> getUser(){
		return this.userCards;
	}
	
	
	Cards bot = new Cards();
	ArrayList<String> shuffledCards = bot.getallCards();
	
	public ArrayList<String> robot1ShareCards() {
		List<String> robot1CardsList = shuffledCards.subList(0, 8);
		ArrayList<String> robot1Cards = new ArrayList<>(robot1CardsList);
		return robot1Cards;
	}
	
	public ArrayList<String> robot2ShareCards() {
		List<String> robot2CardsList = shuffledCards.subList(8, 16);
		ArrayList<String> robot2Cards = new ArrayList<>(robot2CardsList);
		return robot2Cards;
	}
	public ArrayList<String> robot3ShareCards() {
		List<String> robot3CardsList = shuffledCards.subList(16, 24);
		ArrayList<String> robot3Cards = new ArrayList<>(robot3CardsList);
		return robot3Cards;
	}
	public ArrayList<String> userShareCards() {
		List<String> userCardsList = shuffledCards.subList(24, 32);
		ArrayList<String> userCards = new ArrayList<>(userCardsList);
		return userCards;
	}
	
	
	
	
}
