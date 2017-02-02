/**A Deck of Cards
  * @author Isaac Nicholas
  */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class Deck extends ArrayList<Card>{
  //The Face values of the deck
  private Card.Face[] faces;
  //The Suit Values of the deck
  private Card.Suit[] suits;
  //The unique suits of the deck
  private Card.Suit[] uniqueSuits;
  /**Constructs a typical 52 card deck, using the other constructor
    */
  public Deck(){
    this(Card.Face.Ace, Card.Face.King, Card.Suit.Spades, Card.Suit.Hearts, Card.Suit.Diamonds, Card.Suit.Clubs);
  }
  /**Constructs a custom deck
    * @param minFace the minimum face value for each suit
    * @param maxFace the maximum face value for each suit
    * @param suits the suits(multiple if typed) to add to the deck
    */
  public Deck(Card.Face minFace, Card.Face maxFace, Card.Suit... suits){
    //the faces to use
    this.faces=new Card.Face[maxFace.ordinal()-minFace.ordinal()+1];
    Card.Face[] all=Card.Face.values();
    //creates the faces
    for (int i=0; i<this.faces.length; i++)
      this.faces[i]=all[i+minFace.ordinal()];
    //the suits to be used
    this.suits=suits;
    //copy suit data to remove duplicate
    List<Card.Suit> auxList=Arrays.asList(suits);
    TreeSet<Card.Suit> auxSet=new TreeSet<Card.Suit>(auxList);
    uniqueSuits=new Card.Suit[auxSet.size()];
    int auxIndex=0;
    for(Card.Suit suit:auxSet)
      uniqueSuits[auxIndex++]=suit;
    //add to the deck all the combinations
    for(Card.Face face:faces){
      for(Card.Suit suit:suits)
        this.add(new Card(face,suit));
    }
  }
  /**Returns the number of unique suits
    * @return the number of unique suits
    */
  public int getNumberSuites(){
    return  uniqueSuits.length;
  }
  /**Returns the minimum face value
    * @return the minimum face value
    */
  public Card.Face getMinFace(){
    return this.faces[0];
  }
  /**Returns the maximum face value
    * @return the maximum face value
    */
  public Card.Face getMaxFace(){
    return this.faces[faces.length-1];
  }
  
  /**shuffles the deck
    */
  public void shuffle(){
    Collections.shuffle(this);
  }
  /**Returns the top card
    * @return the top card
    */
  public Card drawCard(){
    Card drawnCard=this.get(this.size()-1);
    this.remove(this.size()-1);
    return drawnCard;
  }
  /**Place the input Card at the bottom
    * @param card the card to add
    */
  public void insertCard(Card card){
    this.add(0,card);}
  /**Returns the string of the deck
    * @return String version of the decl
    */
  public String toString(){
    StringBuilder sb=new StringBuilder();
    for (int i=0; i<this.size()-1;i++){
      sb.append(this.get(i).toString());
      sb.append(((i+1)%uniqueSuits.length==0)?"\n":" ");
    }
    sb.append(this.get(this.size()-1).toString());
    return sb.toString();
  }
  /**Makes all cards in a deck visible
    */
  public void showCards(){
    for (Card card: this){
      card.setUp(true);
    }
  }
  /**Makes all cards in a deck invisible
    */
  public void hideCards(){
    for(Card card:this){
      card.setUp(false);
    }
  }
  /**Returns the number of suits
    * @return the number of suits
    */
  public int getNumberSuits(){
    return uniqueSuits.length;
  }
  /**returns the unique suits
    * @return the unique suits
    */
  public Card.Suit[] getUniqueSuits(){
    return uniqueSuits;
  }
}