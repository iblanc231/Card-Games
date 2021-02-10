import javax.swing.*;

public class GameTime
{
   public static void main(String[] args)
   {      
      JFrame frame = new JFrame("BlackJack");
      JPanel game = null;
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      boolean valid = false;
      
      do
      {
         String choice = JOptionPane.showInputDialog("What game do you wish to play today?\n" +
                                                     "(Enter the number next to your choice)\n" +
                                                     "1 - BlackJack\n" +
                                                     "2 - War\n" +
                                                     "3 - 5 Card Draw (in development)\n" +
                                                     "4 - Texas Hold'em (in development)\n" +
                                                     "5 - Go Fish (in development)\n" +
                                                     "0 - Quit");
         try
         {                                            
            //Go fish not yet implemented                                            
            switch (Integer.parseInt(choice))
            {
               case 1:
                  game = new BlackJackPanel();
                  break;
               case 2:
                  game = new WarPanel();
                  break;
               case 3:
                  game = new FiveCardDrawPanel();
                  break;
               case 4:
                  game = new TexasHoldEmPanel();
                  break;
               case 5:
                  game = new GoFishPanel();
                  break;
               default:
                  System.exit(0);
                  break;
            }
            valid = true;
         }
         catch (NumberFormatException e)
         {
            JOptionPane.showMessageDialog(null,"Please enter a valid number from the list provided","Invalid Input",JOptionPane.ERROR_MESSAGE);
         }
         
      } while (!valid);
         
      frame.getContentPane().add(game);   
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
}