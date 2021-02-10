//Overwrites methods in class Card that needed to be modified for BlackJack

public class BlackJackCard extends Card
{
   public BlackJackCard()
   {
      super();
   }
   
   public BlackJackCard(int v,char s)
   {
      super(v,s);
   }      

   //values for cards work very differently in blackjack compared to other card games. namely, ace has one of two values
   public int getValue()
   {
      int v;
      switch (value)
      {
         case 1:
            v = 11;
            break;
         case 11:
         case 12:
         case 13:
            v = 10;
            break;
         default:
            v = value;
            break;
      }
      
      return v;
   }
}      