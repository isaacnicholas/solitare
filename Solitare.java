/**Let's play a game of solitare
  * @author Isaac Nicholas
  */
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Scanner;
public class Solitare{
  //The Stock
  private static Deck stock;
  //The Tableau
  private static Tableau tableau=new Tableau();
  //The active piles
  private static ArrayList<Pile> piles;
  //The foundation
  private ArrayList<Foundation> foundation;
  /**Constructs a normal game of solitare, standard deck, 7 piles
    */
  public Solitare(){
    this(new Deck(), 7);}
  /**Constructs a special game of solitare
    * @param deck the speacial deck
    * @param activePileCount number of piles
    */
  public Solitare(Deck deck, int activePileCount){
    this.piles=new ArrayList<Pile>(activePileCount);
    this.foundation=new ArrayList<Foundation>(deck.getNumberSuites());
    this.stock=deck;
    stock.shuffle();
    stock.hideCards();
    //Adds empty foundations
    for(int i=0;i<deck.getNumberSuits();i++){
      Foundation newPile=new Foundation(stock.getUniqueSuits()[i]);
      foundation.add(newPile);
    }
    //Adds empty piles
    for(int i=0;i<activePileCount; i++){
      Pile newPile=new Pile();
      piles.add(newPile);
    }
    initializeGame();
  }
  /**Deals cards into the piles in the appropearte amount and flips the top card over
    */
  public void initializeGame(){
    for(int i=0; i<piles.size(); i++){
      for(int j=0;j<=i;j++){
        piles.get(piles.size()-i-1).add(stock.drawCard());}
      piles.get(piles.size()-i-1).getLast().setUp(true);
    }
  }
  /**Moves three cards or as many is left, to the tableau and flips them
    */
  public void moveStockToTableau() throws IllegalArgumentException{
    //Checks to see if the stock pile actually has cards
    if(stock.size()>0){
      //draws and flips over three cards
      for(int i=0;i<3&&stock.size()>0;i++){
        tableau.add(stock.drawCard());
        tableau.getLast().setUp(true);
      }
    }
    else
      throw new IllegalArgumentException("The Stock pile is empty, try calling moveTableauToStock()");
  }
  /**Moves the cards back from the tableau to the stock
    */
  public void moveTableauToStock()throws IllegalArgumentException{
    //Makes sure there are no more cards in the stock
    if(stock.size()==0){
      //moves each card one at a time
      for(Card card:tableau)
        stock.insertCard(card);
      stock.hideCards();
      tableau.clear();
    }
    else
      throw new IllegalArgumentException("There are still cards in the Stock, use those first");
  }
  /**Moves a card from a pile to the foundation
    * @param pile the pile to grab a card from
    */
  public void movePileToFoundation(Pile pile) throws IllegalArgumentException{
    //Checks to see if there are cards to move
    if (pile.size()!=0){
      //it has to go through each foundation until it finds one with the same suit. After this checks, the for loop stops
      boolean movedCard=false;
      //As explained, runs through each foundation until it finds one that matches
      for(int i=0;!movedCard&&i<foundation.size(); i++){
        //if the suits are the same
        if (pile.getLast().getSuit()==foundation.get(i).getSuit()){
          //if the foundation is empty, it checks to see if if the card is question is the min value. If it isn't empty, it checks to see if the card is the next in line
          if((foundation.get(i).size()==0&&pile.getLast().getFace()==stock.getMinFace())||(foundation.get(i).size() > 0 && pile.getLast().getFace().ordinal()==foundation.get(i).getLast().getFace().ordinal() + 1)) {
            foundation.get(i).add(pile.getLast());
            pile.removeLast();
            if (pile.size() > 0)
              pile.getLast().setUp(true);
            movedCard = true;
          }
        }
      }
      if(!movedCard)
        throw new IllegalArgumentException("That card won't play.");
    }
    else
      throw new IllegalArgumentException("The input pile is empty");
  }
  /**Moves the top card from the tableau and puts it in it's pile
    * @param p the pile to move the card to
    */
  public void moveTableauToActive(Pile p) throws IllegalArgumentException{
    //Makes sure that there are cards
    if(tableau.size()==0){
      throw new IllegalArgumentException("No cards there");}
    //Makes sure the card will play
    else if(p.getLast().getSuit().getColor()==tableau.getLast().getSuit().getColor()||p.getLast().getFace().ordinal() - 1 != tableau.getLast().getFace().ordinal()){
      throw new IllegalArgumentException("Invalid Card");}
    else{
      p.add(tableau.getLast());
      tableau.removeLast();
    }
  }
  /**moves a card from one pile to another
    * @param p1 the pile to move from
    * @param p2 the pile to move  to
    */
  public void moveActiveToActive(Pile p1, Pile p2) throws IllegalArgumentException{
    //Checks to see if there is cards
    if(p2.size()==0 || p1.size()==0)
      throw new IllegalArgumentException("No cards there");
    //Makes sure the colors alternate and the number is one less
    else if(p1.getLast().getSuit().getColor()==p2.getLast().getSuit().getColor() || p2.getLast().getFace().ordinal() - 1 != p1.getLast().getFace().ordinal())
      throw new IllegalArgumentException("Invalid Move");
    else{
      p2.add(p1.getLast());
      p1.removeLast();
    }
  }
  /**Builds a string representation of the current status of the game
    */
  public String toString(){
    //The string
    StringBuilder sb=new StringBuilder("\n");
    //The foundations
    for(Foundation pile:foundation){
      sb.append(pile.getSuit().toString()+": ");
      for (Card card:pile)
        sb.append(card.toString()+" ");
      sb.append("\n");
    }
    //The piles
    sb. append("\n");
    int pileSize=0;
    for(Pile pile:piles){
      sb.append(pileSize++ + ": ");
      for(Card card:pile)
        sb.append(card.toString()+" ");
      sb.append("\n");
    }
    //The stock
    sb.append("\nStock: ");
    for(Card card: stock)
      sb.append(card.toString()+" ");
    //The tableau
    sb.append("\nTableau: ");
    for(Card card: tableau)
      sb.append(card.toString()+" ");
    sb.append("\n\nYour Move\n");
    
    return sb.toString();
  }
  /**Checks to see if You're Winner
    * @return have you won yet?
    */
  public static boolean winner(){
    //true unless prooven false
    boolean b=true;
    //Checks to makes sure all the active piles are empty
    for(int i=0;i<piles.size()&&b==true;i++){
      if(piles.get(i).size()!=0){
        b=false;
      }
      //Are the stock and tableau empty?
      if(stock.size()!=0||tableau.size()!=0){
        b=false;
      }
    }
    return b;
  }
  /**The main string
    * @param args the arguments to start the game(not necessary)
    */
  public static void main(String[] args){
    Solitare game;
    Scanner scan;
    boolean win=false;
    //For a normal game
    if(args.length==0){
      game=new Solitare();}
    //For a custom game
    else{
      int numberofpiles= Integer.parseInt(args[0]);
      Card.Face min= Card.Face.getFaceByName(args[1]);
      Card.Face max= Card.Face.getFaceByName(args[2]);
      Card.Suit[] newsuit=new Card.Suit[args.length-3];
      for(int i=0;i<args.length-3;i++){
        newsuit[i]=Card.Suit.getSuitByName(args[i+3]);}
      Deck d=new Deck(min,max,newsuit);
      game=new Solitare(d,numberofpiles);
    }
    boolean playgame=true;
    //The loop that plays the game
    while (playgame==true){
      System.out.print(game.toString());
      scan=new Scanner(System.in);
      //pulls the input from the scan
      String input=scan.nextLine();
      //grabs first input as a character.
      char input1=input.charAt(0);
      //if you don't want to quit, go ahead and play the game
      if(input1!='q'){
        //The second input
        char input2=input.charAt(2);
        if(input1=='s'){
          if (input2=='t'){
            game.moveStockToTableau();}
        }
        else if(input1=='t'){
          if(input2=='s'){
            game.moveTableauToStock();}
          else if(input2=='f'){
            game.movePileToFoundation(game.tableau);
            //You can only win if you move to the foundation, like this time
            win=game.winner();}
          else if(input2>='0'&&input2<='9'){
            game.moveTableauToActive(piles.get(Character.getNumericValue(input2)));
          }
        }
        else if(input1>='0'&&input1<='9'){
          if(input2=='f'){
            game.movePileToFoundation(piles.get(Character.getNumericValue(input1)));
            win=game.winner();}
          else if(input2>='0'&&input2<='9'){
            game.moveActiveToActive(piles.get(Character.getNumericValue(input1)),piles.get(Character.getNumericValue(input2)));}
        }
        //if you won, stop the game and say you won
        if (win==true){
          System.out.print("Congratuations! You've Won!");
          playgame=false;}
      }
      else
        playgame=false;
    }
  }
}