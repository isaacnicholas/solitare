/**Testing HW5
  * @author Isaac Nicholas
  */
import static org.junit.Assert.*;
import org.junit.Test;
public class HW5Test{
  @Test
  public void cardConstructor(){
    Card.Face[] allFace=Card.Face.values();
    Card.Suit[] allSuit=Card.Suit.values();
    String[] s=new String[allFace.length*allSuit.length];
    int i=0;
    for(Card.Face face: allFace){
      for(Card.Suit suit: allSuit)
        s[i++]=new Card(face, suit).toString();
    }
    String[] expected= {"A_S","A_H","A_D","A_C","2_S","2_H","2_D","2_C","3_S","3_H","3_D","3_C","4_S","4_H","4_D","4_C","5_S","5_H","5_D","5_C","6_S","6_H","6_D","6_C","7_S","7_H","7_D","7_C","8_S","8_H","8_D","8_C","9_S","9_H","9_D","9_C","10S","10H","10D","10C","J_S","J_H","J_D","J_C","Q_S","Q_H","Q_D","Q_C","K_S","K_H","K_D","K_C"};
    assertArrayEquals("Face Up",expected,s);
    i=0;
    for(Card.Face face:allFace){
      for(Card.Suit suit:allSuit)
        s[i++]=new Card(face, suit, false).toString();
      expected=new String[]{"X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X","X_X"};
      
    }
    assertArrayEquals("Face Down",expected,s);
  }
  @Test
  public void cardGetterSetter(){
    Card c1=new Card("Ace", "Spades");
    Card c2=new Card("Ace", "Hearts");
    Card c3=new Card("King", "Hearts");
    assertTrue("getFace True", c1.getFace().equals(c2.getFace()));
    assertFalse("getFace False",c2.getFace().equals(c3.getFace()));
    assertFalse("getSuit False", c1.getSuit().equals(c2.getSuit()));
    assertTrue("getSuit True", c2.getSuit().equals(c3.getSuit()));
    //flips the card upside down
    c1.setUp(false);
    assertFalse("face down",c1.getFaceUp());
    //then flips it again
    c1.setUp(true);
    assertTrue("face up",c1.getFaceUp());
  }
  @Test
  public void deckConstructor(){
    Deck normal=new Deck();
    String expected=("A_S A_H A_D A_C\n2_S 2_H 2_D 2_C\n3_S 3_H 3_D 3_C\n4_S 4_H 4_D 4_C\n5_S 5_H 5_D 5_C\n6_S 6_H 6_D 6_C\n7_S 7_H 7_D 7_C\n8_S 8_H 8_D 8_C\n9_S 9_H 9_D 9_C\n10S 10H 10D 10C\nJ_S J_H J_D J_C\nQ_S Q_H Q_D Q_C\nK_S K_H K_D K_C");
    assertEquals("Default deck", expected, normal.toString());
    Deck custom=new Deck(Card.Face.Ace, Card.Face.Three, Card.Suit.Hearts, Card.Suit.Spades);
    expected=("A_H A_S\n2_H 2_S\n3_H 3_S");
    assertEquals("Custom deck", expected, custom.toString());
  }
  @Test
  public void deckGetter(){
            // default
        Deck deck = new Deck();
        assertEquals("default suits", 4, deck.getNumberSuits());
        assertEquals("default min face", Card.Face.Ace, deck.getMinFace());
        assertEquals("default max face", Card.Face.King, deck.getMaxFace());
        // duplicate Suits and one Face
        deck = new Deck(Card.Face.Ace, Card.Face.Ace, Card.Suit.Spades, Card.Suit.Spades);
        assertEquals("custom suits", 1, deck.getNumberSuits());
        assertEquals("custom min face", Card.Face.Ace, deck.getMinFace());
        assertEquals("custom max face", Card.Face.Ace, deck.getMaxFace());
  }
}