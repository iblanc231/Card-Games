import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import javax.swing.ImageIcon;

public class BlackJackPanel extends JPanel
{
   private Deck          deck;
   private BlackJackHand player;
   private BlackJackHand dealer;
   private JLabel        cardBackLabel;
   private JLabel        playerLabel;
   private JLabel        dealerLabel;
   private JPanel        playerPanel;
   private JPanel        playerHandPanel;
   private JPanel        buttonPanel;
   private JPanel        dealerPanel;
   private JPanel        dealerHandPanel;
   private JButton       hit;
   private JButton       stand;
   private JButton       close;
   
   public BlackJackPanel()
   {
      //Initialize Variables
      deck            = new BlackJackDeck();
      player          = new BlackJackHand();
      dealer          = new BlackJackHand();
      cardBackLabel   = new JLabel(new ImageIcon("imgs/Cardback.png"));
      playerPanel     = new JPanel();
      playerHandPanel = new JPanel();
      buttonPanel     = new JPanel();
      dealerPanel     = new JPanel();
      dealerHandPanel = new JPanel();
      playerLabel     = new JLabel("Player: " + player.getValue());
      dealerLabel     = new JLabel("Dealer: " + dealer.getValue());
      hit             = new JButton("Hit");
      stand           = new JButton("Stand");
      close           = new JButton("Game Over - Close Window");
      
      
      //Attach ActionListeners to Hit/Stand buttons
      hit.addActionListener(new BlackJackListener());
      stand.addActionListener(new BlackJackListener());
      close.addActionListener(new BlackJackListener());
      
      //Add panels to BJPanel
      this.add(playerPanel);
      this.add(playerHandPanel);
      this.add(buttonPanel);
      this.add(dealerPanel);
      this.add(dealerHandPanel);
      
      //Add Labels and Buttons to the 3 Panels
      playerPanel.add(playerLabel);
      buttonPanel.add(hit);
      buttonPanel.add(stand);
      dealerPanel.add(dealerLabel);
      
      //Set Panel Dimensions
      this.setPreferredSize(new Dimension(300,270));
      playerPanel.setPreferredSize(new Dimension(300,25));
      playerHandPanel.setPreferredSize(new Dimension(300,60));
      buttonPanel.setPreferredSize(new Dimension(300,50));
      dealerPanel.setPreferredSize(new Dimension(300,25));
      dealerHandPanel.setPreferredSize(new Dimension(300,60));
      
      //Set Panel Colors
      this.setBackground(Color.WHITE);
      playerPanel.setBackground(Color.WHITE);
      playerHandPanel.setBackground(Color.WHITE);
      buttonPanel.setBackground(Color.WHITE);
      dealerPanel.setBackground(Color.WHITE);
      dealerHandPanel.setBackground(Color.WHITE);
      
      //Create and shuffle the Deck of Cards
      deck.createDeck();
      deck.shuffleDeck();
      
      //Deal starting hands to player and dealer
      player.addCard(deck.drawCard());
      player.addCard(deck.drawCard());
      dealer.addCard(deck.drawCard());
      dealer.addCard(deck.drawCard());
      
      //Modify Player Label and Hand Panel to show opening hand
      playerLabel.setText("Player: " + player.getValue());
      for (int i = 0; i < player.getHandSize(); i++)
         playerHandPanel.add(new JLabel(player.getCard(i).getCardIcon()));
         
      //Modify Dealer Label and Hand Panel to show opening hand (Less the Dealers top card, which remains hidden from Player
      dealerLabel.setText("Dealer: " + dealer.getCard(1).getValue());
      dealerHandPanel.add(cardBackLabel);                               //Top Card Hidden
      for (int i=1; i < dealer.getHandSize(); i++)
         dealerHandPanel.add(new JLabel(dealer.getCard(i).getCardIcon()));
      
   }
   
   
   private class BlackJackListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         //If player clicked "Hit"
         if (event.getSource() == hit)
         {
            //draws a card and adds new card to players hand and the HandLabel
            Card newCard = deck.drawCard();
            player.addCard(newCard);
            playerHandPanel.add(new JLabel(newCard.getCardIcon()));
            
            //alters playerLabel
            //If Player busts, end game immediately. Otherwise, continue
            if (player.getValue() > 21)
            {
               hit.setVisible(false);
               stand.setVisible(false);
               buttonPanel.add(close);
               buttonPanel.revalidate();
               
               playerLabel.setText("Player: " + player.getValue() + " Player Busts");
               close.setText("Player Loses - Close Window");
            }
            else
            {
               playerLabel.setText("Player: " + player.getValue());
            }
         }
         //if player clicked "Stand"
         else if (event.getSource() == stand)
         {
            //updates buttons on UI
            hit.setVisible(false);
            stand.setVisible(false);
            buttonPanel.add(close);
            buttonPanel.revalidate();
            
            //removes both cards from dealerHandPanel, then readds with the actual ImageIcons for them instead of hiding the first one
            //with the cardBackLabel
            dealerHandPanel.removeAll();
            dealerHandPanel.add(new JLabel(dealer.getCard(0).getCardIcon()));
            dealerHandPanel.add(new JLabel(dealer.getCard(1).getCardIcon()));
            
            
            
            boolean stand = false;
            Card drawCard;
            
            //Reveal dealer's hand
            dealerLabel.setText("Dealer: " + dealer.getValue());
            
            do
            {
               if (dealer.aceInHand())  //If Dealer has soft hand, plays by these rules
               {
                  if (dealer.getValue() <= 17)
                  {
                     drawCard = deck.drawCard();
                     dealer.addCard(drawCard);
                     dealerLabel.setText("Dealer: " + dealer.getValue());
                     dealerHandPanel.add(new JLabel(drawCard.getCardIcon())); //adds the new card to the hand label
                  }
                  else
                     stand = true;
               }
               else                    //If dealer has hard hand, plays by these rules
               {
                  if (dealer.getValue() <= 16)
                  {
                     drawCard = deck.drawCard();
                     dealer.addCard(drawCard);
                     dealerLabel.setText("Dealer: " + dealer.getValue());
                     dealerHandPanel.add(new JLabel(drawCard.getCardIcon())); //adds the new card to the hand label
                  }
                  else
                     stand = true;
               }
            } while (!stand); //continue until stand is changed to true
            
            
            //Determine winner
            if ((player.getValue() > dealer.getValue()) || (dealer.getValue() > 21)) //Player has higher score OR dealer bust
            {
               close.setText("Player Wins! - Close Window");
               if (dealer.getValue() > 21)
                  dealerLabel.setText("Dealer " + dealer.getValue() + " Dealer Busts");
            }
            else
            {
               close.setText("Player Loses - Close Window");
            }
         }
         //If player hit "Close"
         else
         {
            System.exit(0);
         }
      }
   }

}