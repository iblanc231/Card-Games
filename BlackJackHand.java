public class BlackJackHand extends Hand
{
   private int     value;
   private boolean hasConverted;

   public BlackJackHand()
   {
      super();
      hasConverted = false;
   }
   
   public int getValue()
   {
      return value;
   }
   
   public boolean aceInHand()
   {
      boolean ace = false;
      
      for (int i = 0; i < hand.size(); i++)
      {
         if (hand.get(i).getValue() == 11)
            ace = true;
      }
      
      return ace;
   }
   
   public void addCard(Card c)
   {
      hand.add(c);
      value += c.getValue();
      if (value > 21 && aceInHand() && !hasConverted)
      {
         value -= 10;
         hasConverted = true;
      }
   
   }
   
   public void resetHand()
   {
      super.resetHand();
      value = 0;
      hasConverted = false;
   }
}      