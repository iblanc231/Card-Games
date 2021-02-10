import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import javax.swing.ImageIcon;

public class FiveCardDrawPanel extends JPanel
{
   private Deck      deck;
   private Hand      player;
   private Hand      dealer;
   private Card      tempCard;
   private JPanel    playerPanel;
   private JPanel    playerHandPanel;
   private JPanel    buttonPanel;
   private JPanel    dealerPanel;
   private JPanel    dealerHandPanel;
   private JLabel    playerLabel;
   private JLabel    dealerLabel;
   private JLabel    cardBack;
   private JButton[] hold;
   private JButton   draw;
   private JButton   close;
   private boolean[] holdCard;

   public FiveCardDrawPanel()
   {
      deck            = new Deck();
      player          = new Hand();
      dealer          = new Hand();
      tempCard        = new Card();
      playerPanel     = new JPanel();
      playerHandPanel = new JPanel();
      buttonPanel     = new JPanel();
      dealerPanel     = new JPanel();
      dealerHandPanel = new JPanel();
      playerLabel     = new JLabel("Player:");
      dealerLabel     = new JLabel("Dealer:");
      draw            = new JButton("Draw");
      hold            = new JButton[5];
      close           = new JButton("Close");
      holdCard        = new boolean[5];
         
      //add panels to PokerPanel
      this.add(playerPanel);
      this.add(playerHandPanel);
      this.add(buttonPanel);
      this.add(dealerPanel);
      this.add(dealerHandPanel);
      
      //add labels to panels
      playerPanel.add(playerLabel);
      dealerPanel.add(dealerLabel);
      
      //initialize buttons, add buttons to panel, and add listeners to buttons
      for (int i = 0; i < 5; i++)
      {
         hold[i] = new JButton("Hold " + (i+1));
         buttonPanel.add(hold[i]);
         hold[i].addActionListener(new FiveCardDrawListener());
      }
      buttonPanel.add(draw);
      buttonPanel.add(close);
      close.setVisible(false);      
      draw.addActionListener(new FiveCardDrawListener());
      close.addActionListener(new FiveCardDrawListener());
      
      //set dimensions
      this.setPreferredSize(new Dimension(550,250));
      playerPanel.setPreferredSize(new Dimension(550,25));
      playerHandPanel.setPreferredSize(new Dimension(550,60));
      buttonPanel.setPreferredSize(new Dimension(550,35));
      dealerPanel.setPreferredSize(new Dimension(550,25));
      dealerHandPanel.setPreferredSize(new Dimension(550,60));
      
      //create and shuffle deck
      deck.createDeck();
      deck.shuffleDeck();
      
      //deals opening hand
      for (int i = 0; i < 5; i++)
      {
         //players cards
         tempCard = deck.drawCard();
         player.addCard(tempCard);
         playerHandPanel.add(new JLabel(tempCard.getCardIcon()));
         
         //dealers cards, shown facedown on panel
         tempCard = deck.drawCard();
         dealer.addCard(tempCard);
         dealerHandPanel.add(new JLabel(new ImageIcon("imgs/Cardback.png")));
      }
   }
   
   private class FiveCardDrawListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         //if hold button is pressed, determines which button and toggles whether the player is holding that card or not
         if (event.getSource() == hold[0])
         {
            if (hold[0].getText() == "Hold 1")
            {
               hold[0].setText("Held");
               holdCard[0] = true;
            }
            else
            {
               hold[0].setText("Hold 1");
               holdCard[0] = false;
            }
         }
         else if (event.getSource() == hold[1])
         {
            if (hold[1].getText() == "Hold 2")
            {
               hold[1].setText("Held");
               holdCard[1] = true;
            }
            else
            {
               hold[1].setText("Hold 2");
               holdCard[1] = false;
            }
         }
         else if (event.getSource() == hold[2])
         {
            if (hold[2].getText() == "Hold 3")
            {
               hold[2].setText("Held");
               holdCard[2] = true;
            }
            else
            {
               hold[2].setText("Hold 3");
               holdCard[2] = false;
            }
         }
         else if (event.getSource() == hold[3])
         {
            if (hold[3].getText() == "Hold 4")
            {
               hold[3].setText("Held");
               holdCard[3] = true;
            }
            else
            {
               hold[3].setText("Hold 4");
               holdCard[3] = false;
            }
         }
         else if (event.getSource() == hold[4])
         {
            if (hold[4].getText() == "Hold 5")
            {
               hold[4].setText("Held");
               holdCard[4] = true;
            }
            else
            {
               hold[4].setText("Hold 5");
               holdCard[4] = false;
            }
         }
         else if (event.getSource() == draw)
         {
            //clears playerHandPanel and removes draw button and shows close button
            playerHandPanel.removeAll();
            draw.setVisible(false);
            close.setVisible(true);
            
            //replaces players cards that they did not wish to keep and hides the Held buttons
            for (int i = 0; i < 5; i++)
            {
               hold[i].setVisible(false);
               if (!holdCard[i])
               {
                  player.removeCard(i);
                  player.addCard(deck.drawCard(),i);
               }
               playerHandPanel.add(new JLabel(player.getCard(i).getCardIcon()));
               
            }
            
            buttonPanel.revalidate();
            playerHandPanel.revalidate();
            
            //reveals dealers hand
            dealerHandPanel.removeAll();
            
            for (int i = 0; i < 5; i++)
            {
               dealerHandPanel.add(new JLabel(dealer.getCard(i).getCardIcon()));
            }
            
            dealerHandPanel.revalidate();
         }
         else if (event.getSource() == close)
         {
            System.exit(0);
         }
      }
   }
}