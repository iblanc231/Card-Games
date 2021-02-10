import javax.swing.*;
import java.util.*;

public class Hand
{
   protected ArrayList<Card> hand;
   
   public Hand()
   {
      hand = new ArrayList<Card>();
   }
   
   public void addCard(Card c)
   {
      hand.add(c);
   }
   
   public void addCard(Card c,int index)
   {
      hand.add(index,c);
   }
   
   public void removeCard(int i)
   {
      hand.remove(i);
   }
   
   public int getHandSize()
   {
      return hand.size();
   }
   
   public Card getCard(int i)
   {
      return  hand.get(i);
   }
   
   public void resetHand()
   {
      hand.clear();
   }
}