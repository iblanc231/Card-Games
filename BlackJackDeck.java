public class BlackJackDeck extends Deck
{
   public BlackJackDeck()
   {
      super("blackjack");
   }
   
   public void createDeck()
   {
      char[] suits = {'C','H','S','D'};
   	int count = 0;
     
      super.setNextCard(0);
      
      for ( int i = 0; i < 4; i++ )
      {
         for (int j = 1; j < 14; j++ )
         {
            deck[count] = new BlackJackCard(j,suits[i]);
            count++;
         }
      }

   }
   
}