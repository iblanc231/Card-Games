public class About
{
   public static void main(String[] args)
   {
      System.out.println("My assignment is currently a BlackJack game. The user will be presented with a GUI that displays their cards with ImageIcons\n" +
                         "as well as the dealers cards with buttons for Hit and Stand. Double Down and Split will not be available in this game.\n" +
                         "The program is set up with vegas rules for how the dealer plays and is also fully capable of monitoring the players hand and adjusting for Aces in the hand\n" +
                         "since Aces are able to be 1 or 11.\n" +
                         "The long term goal will be to add a second game to the program so the player might be able to choose which game they want to play between multiple options.\n" +
                         "Other games to be added have not yet been decided upon.");
                         
      System.out.println();
      System.out.println("Assignment 3 addition: Arrays\n" +
                         "Arrays added to program in the form of both the Deck class and the Hand class. The array added to the Deck class\n" +
                         "represents the card deck in that it is an array of Card objects. The Hand class uses arrays in much the same way in\n" +
                         "that it is an array of the Card objects that are currently in the players or whoevers hand"); 
                         
      System.out.println();
      System.out.println("Assignment 4 addition: Polymorphism/Exception Handling\n" +
                         "Polymorphism was added in the form of a BlackJackCard class that extends Card. In the BlackJackPanel class, \n" +
                         "a Deck is declared and then initialized as a BlackJackDeck which is the same except that it is an array of \n" +
                         "BlackJackCard objects instead of Cards. The createDeck method needed to be overwritten, else the BlackJackDeck \n" +
                         "might not have been necessary. The BlackJackCard class overwrites the getValue method in Card because it needs to \n" +
                         "function differently in BlackJack for logic reasons. The same was also done for Hand to overwrite adding a card.\n" +
                         "Polymorphism will also be added on the main GameTime class in the form of the JPanel added to the JFrame. A JPanel\n" +
                         "is declared (JPanel game). It is then instantiated as one of multiple types of panels dependent on user input.\n" +
                         "The JPanel game could become a BlackJackPanel or something else, depending on the game the player decides to play.\n" + 
                         "At this time, only BlackJack is full functional. The other games to be added are still being decided upon/developed.\n" +
                         "Exception handling was added to the initial prompt on the GameTime class to prevent exceptions popping up\n" +
                         "from the parseInt on the user input.");    
                         
      System.out.println();
      System.out.println("Assignment 5 addtion: New game(s) and minor changes to older classes\n" +
                         "Minor changes were made to the Hand and BlackJackHand classes. The instance variable value was completely removed from Hand and is solely located\n" + 
                         "in BlackJackHand now. This change was maded because value was completely unnecessary in the main Hand class. The\n" +
                         "Hand, Card, and Deck classes are intended to be standard for ANY card game, and features that are game specific are\n" +
                         "not to be put in these classes. Another small change made to the Hand class was to make the Hand an ArrayList of Card objects\n" +
                         "instead of just an array. The ability to remove cards was needed for War\n" +
                         "War was added as a new game option. The main game JPanel in the GameTime class can now polymorph into either a BlackJackPanel or \n" +
                         "a WarPanel. The WarPanel sets up the frame to display current Hand sizes for both players and the cards most recently played by each player.\n" +
                         "The Jbutton displayed on the screen changes to whatever is currently necessary at the time, whether the next card needs to be played,\n" +
                         "a war is to be declared, or the game is over and the frame is to be closed.");
   }
}