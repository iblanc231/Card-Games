import java.util.Random;

public class Deck
{
   protected final  int DECK_SIZE = 52;

   protected Card[] deck;
   private   int    nextCard;   
   
   public Deck() 
   {
      setNextCard(0);
      deck = new Card[DECK_SIZE];
   }
   
   //Alternate constructor allows deck to be made of different type of Cards, such as BlackJackCard instead of just Card
   public Deck(String cardType)
   {
      setNextCard(0);
      switch (cardType.toLowerCase())
      {
         case "blackjack":
            deck = new BlackJackCard[DECK_SIZE];
            break;
         default:
            deck = new Card[DECK_SIZE];
            break;
      }      
   }
   
   public void setNextCard(int n)
   {
      nextCard = n;
   }
   
   public void createDeck()
   {
      char[] suits = {'C','H','S','D'};
   	int count = 0;
     
      setNextCard(0);
      
      for ( int i = 0; i < 4; i++ )
      {
         for (int j = 1; j < 14; j++ )
         {
            deck[count] = new Card(j,suits[i]);
            count++;
         }
      }
   }
   
   public void shuffleDeck() 
   {
      Card temp;
      int j;
 
      for (int k = 0; k < (int)(Math.random() * 100); k++)
      {
         for (int i = 0; i < 52; i++)
         {
            j = (int)(Math.random() * 52);
            temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
         }
      }
   }
   
   public Card drawCard() 
   {
      nextCard++;
      return deck[nextCard - 1];
   }
}