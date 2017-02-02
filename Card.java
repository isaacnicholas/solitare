/**A playing Card
  * @author Isaac Nicholas
  */
public class Card{
  //the Face Value of the card
  private Face face;
  //The Suit Value of the Card
  private Suit suit;
  //If the card is face up, then true, else false
  public boolean faceUp;
  //Creating the symbols, names, and colors for the different kind of suits
  public enum Suit{
    //Names and their coorespoiding Strings
    Spades("Spades"), Hearts("Hearts"), Diamonds("Diamonds"), Clubs("Clubs");
    private String name;
    private String symbol;
    private String color;
    //Assigning a suit based off a name
    private Suit(String name){
      this.name=name;
      if (this.name=="Spades"){
        this.symbol = "S";
        this.color = "black";
      }
      if (this.name=="Hearts"){
        this.symbol = "H";
        this.color = "red";
      }
      if(this.name=="Diamonds"){
        this.symbol = "D";
        this.color = "red";
      }
      if(this.name=="Clubs"){
        this.symbol = "C";
        this.color = "black";
      }
      
    }
    /**Returns the color of the suit
      * @return color of the suit
      */
    public String getColor(){
      return this.color;
    }
    /**Give a suit based off a string input
      * @param name the name of the suit
      * @return The suit you asked for
      */
    public static Suit getSuitByName(String name){
      return Suit.valueOf(name);}
    /**Gives the symbol of the suit
      * @return the symbol of that suit
      */
    public String toString(){
      return this.symbol;
    }
  };
  //Creating the symbol and names for different face values
  public enum Face{
    //Names and cooresponding strings
    Ace("Ace"), Two("Two"), Three("Three"), Four("Four"), Five("Five"), Six("Six"), Seven("Seven"), Eight("Eight"), Nine("Nine"), Ten("Ten"), Jack("Jack"), Queen("Queen"), King("King");
    private String name;
    private String symbol;
    //Gives the symbol associated with the name
    private Face (String name){
      this.name=name;
      if(name=="Ace")
        symbol="A";
      if(name=="One")
        symbol="1";
      if(name=="Two")
        symbol="2";
      if(name=="Three")
        symbol="3";
      if(name=="Four")
        symbol="4";
      if(name=="Five")
        symbol="5";
      if(name=="Six")
        symbol="6";
      if(name=="Seven")
        symbol="7";
      if(name=="Eight")
        symbol="8";
      if(name=="Nine")
        symbol="9";
      if(name=="Ten")
        symbol="10";
      if(name=="Jack")
        symbol="J";
      if(name=="Queen")
        symbol="Q";
      if(name=="King")
        symbol="K";
    }
    /*Creates a face from a name
     * @param name the name of the suit
     * @return a face value asked for
     */
    public static Face getFaceByName(String name){
      return Face.valueOf(name);}
    /*Converts a face value to a string
     * @return symbol asked for
     */
    public String toString(){
      return this.symbol;}
  };
  /**The card constructor when only a face and suit are given
    * @param face the face value
    * @param suit the suit value
    */
  public Card(Face face, Suit suit){
    this(face,suit,true);
  }
  /**The card constructor when you give two strings form the face and suit
    * @param face a string with that face value
    * @param suit a string with that suit value
    */
  public Card(String face, String suit){
    this(face,suit,true);}
  /**The card constuctor when you give a face, suit, and if it's face up
    * @param face the face value
    * @param suit the suit value
    * @param faceUp is the card face up?
    */
  public Card(Face face, Suit suit, Boolean faceUp){
    this.face=face;
    this.suit=suit;
    this.faceUp=faceUp;
  }
  /**The card constructor when you give a face and suit as strings and if it's face up
    * @param face a string with that face value
    * @param suit a string with that suit value
    * @param faceUp is the card face up?
    */
  public Card(String face, String suit, Boolean faceUp){
    this.face=Face.getFaceByName(face);
    this.suit=Suit.getSuitByName(suit);
    this.faceUp=faceUp;
  }
  /**Changes the faceUp value of the card
    * @param b is the card face up?
    */
  public void setUp(boolean b){
    this.faceUp=b;}
  /**gets the face vaule of the card
    * @return the face value
    */
  public Face getFace(){
    return this.face;}
  /**gets the suit value of the card
    * @return the suit value
    */
  public Suit getSuit(){
    return this.suit;
  }
  /**returns if the card is face up or not
    * @return is the card face up?
    */
  public boolean getFaceUp(){
    return this.faceUp;
  }
  /**returns a string value of the card
    * @return a string
    */
  public String toString(){
    if (faceUp==true){
      //if it equals 10, I don't want a underscore to keep everything linear
      if(this.face.toString()=="10"){
        return this.face.toString()+this.suit.toString();}
      else{
        return this.face.toString() + "_" + this.suit.toString();}
    }
    return "X_X";
  }
}
