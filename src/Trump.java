import java.util.ArrayList;
import java.util.List;

public class Trump {
  private String name; // private = restricted access
  private ArrayList<String> trumpSelectingCards;
  
  Trump(){
	  this.name = "Java";
	  this.trumpSelectingCards = methodsasd();
  }
  // Getter
  public String getName() {
    return this.name;
  }
  
  public ArrayList<String> getTrump() {
	  return this.trumpSelectingCards;}
	  
  public ArrayList<String> methodsasd(){
  List<String> trumpSelectingCardsList = List.of("Club - 8", "Club - J", "Club - K", "Club - 9");
  ArrayList<String> fenaz = new ArrayList<>(trumpSelectingCardsList);
  return fenaz;
  
}
}