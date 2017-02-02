/**represents the piles of foundations in a game
  * @author Isaac Nicholas
  */
public class FoundationPile extends Pile{
  Card.Suit suit;
  public FoundationPile(Card.Suit suit){
    this.suit=suit;
  }
  public Card.Suit getSuit(){
    return suit;
  }
}