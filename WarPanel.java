/****************************
* Quick Rule Clarification:**
************************************************************
* If the two cards played are the same, a war is declared. *
* However, if that war ends in a tie again, that tie goes  *
* to the dealer. Also, Aces are lowest card, not highest.  *
***********************************************************/


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import javax.swing.ImageIcon;

public class WarPanel extends JPanel
{
   private Deck    deck;
   private Hand    player;
   private Hand    dealer;
   private JLabel  playerLabel;
   private JLabel  dealerLabel;
   private JPanel  playerPanel;
   private JPanel  dealerPanel;
   private JPanel  gameArea;
   private JPanel  gameTextArea;
   private JLabel  gameText;
   private JPanel  buttonPanel;
   private JButton play;
   private JButton war;
   private JButton close;
   
   public WarPanel()
   {
      //initialize variables
      deck         = new Deck();
      player       = new Hand();
      dealer       = new Hand();
      playerLabel  = new JLabel("Player: " + player.getHandSize());
      dealerLabel  = new JLabel("Dealer: " + dealer.getHandSize());
      playerPanel  = new JPanel();
      dealerPanel  = new JPanel();
      gameArea     = new JPanel();
      gameTextArea = new JPanel();
      gameText     = new JLabel();
      buttonPanel  = new JPanel();
      play         = new JButton("Play Card");
      war          = new JButton("Declare War");
      close        = new JButton("Close");
      
      //add panels to WarPanel
      this.add(playerPanel);
      this.add(gameArea);
      this.add(gameTextArea);
      this.add(buttonPanel);
      this.add(dealerPanel);
      
      //add labels and buttons to panels
      playerPanel.add(playerLabel);
      dealerPanel.add(dealerLabel);
      gameTextArea.add(gameText);
      buttonPanel.add(play);
      buttonPanel.add(war);
      buttonPanel.add(close);
      
      //set visibility on buttons
      play.setVisible(true);
      war.setVisible(false);
      close.setVisible(false);
      
      //add actionListeners
      play.addActionListener(new WarListener());
      war.addActionListener(new WarListener());
      close.addActionListener(new WarListener());
      
      //set dimensions
      this.setPreferredSize(new Dimension(300,240));
      playerPanel.setPreferredSize(new Dimension(300,25));
      gameArea.setPreferredSize(new Dimension(300,65));
      gameTextArea.setPreferredSize(new Dimension(300,25));
      buttonPanel.setPreferredSize(new Dimension(300,50));
      dealerPanel.setPreferredSize(new Dimension(300,25));
      
      //shuffle and create deck
      deck.createDeck();
      deck.shuffleDeck();
      
      //deal out full deck to player and dealer
      //2 hands, 26 cards each
      for (int i = 0; i < 26; i++)
      {
         player.addCard(deck.drawCard());
         dealer.addCard(deck.drawCard());
      }
      
      //update labels
      playerLabel.setText("Player: " + player.getHandSize());
      dealerLabel.setText("Dealer: " + dealer.getHandSize());
   }
   
   private class WarListener implements ActionListener
   {
      
      public void actionPerformed(ActionEvent event)
      {
         Card playerCard = new Card();
         Card dealerCard = new Card();
         Card tempCard;
         Hand playerCards = new Hand();  //holds the 3 facedown "war" cards and the fourth face up war
         Hand dealerCards = new Hand();  //same for dealer
         
         
         if (event.getSource() == play)
         {
            //clear board
            gameArea.removeAll();
            gameArea.revalidate();
            
            //get top card from each Hand, store it in the Card variables and remove from the Hands
            playerCard = player.getCard(0);
            dealerCard = dealer.getCard(0);
            player.removeCard(0);
            dealer.removeCard(0);
            
            //adds card graphics to gameArea
            gameArea.add(new JLabel(playerCard.getCardIcon()));
            gameArea.add(new JLabel(dealerCard.getCardIcon()));
            gameArea.revalidate();
            
            //if playerCard is higher than dealerCard
            if (playerCard.getValue() > dealerCard.getValue())
            {
               //gives player the cards s/he won
               player.addCard(playerCard);
               player.addCard(dealerCard);
               
               //updates labels
               gameText.setText("Player Higher");
               playerLabel.setText("Player: " + player.getHandSize());
               dealerLabel.setText("Dealer: " + dealer.getHandSize());
               
               //dealer lost a card this turn, checks if dealer loses game
               if (dealer.getHandSize() == 0)
               {
                  gameText.setText("Player Wins! Well Done!");
                  play.setVisible(false);
                  close.setVisible(true);
               }
               
            }
            //if dealerCard is higher than playerCard
            else if (playerCard.getValue() < dealerCard.getValue())
            {
               //gives dealer cards he won
               dealer.addCard(playerCard);
               dealer.addCard(dealerCard);
               
               //updates labels
               gameText.setText("Dealer Higher");
               playerLabel.setText("Player: " + player.getHandSize());
               dealerLabel.setText("Dealer: " + dealer.getHandSize());
               
               //player lost a card this turn, checks if player loses game
               if (player.getHandSize() == 0)
               {
                  gameText.setText("Player Loses! Try Again Next Time");
                  play.setVisible(false);
                  close.setVisible(true);
               }
            }
            //or if they are the same
            else
            {
               //changes buttons
               gameText.setText("This means WAR!");
               play.setVisible(false);
               war.setVisible(true);
               buttonPanel.revalidate();
            }
         }
         else if (event.getSource() == war)
         {
            
            //if both players have sufficient cards
            if (player.getHandSize() > 4 && dealer.getHandSize() > 4)
            {
               //draw four cards and store them in the temp playerCards hand
               playerCards.addCard(player.getCard(0));
               playerCards.addCard(player.getCard(1));
               playerCards.addCard(player.getCard(2));
               playerCards.addCard(player.getCard(3));
               
               //remove the cards from players hand. index does not change on purpose
               player.removeCard(0);
               player.removeCard(0);
               player.removeCard(0);
               player.removeCard(0);
               
               //draw four cards and store them in the temp dealerCards hand
               dealerCards.addCard(dealer.getCard(0));
               dealerCards.addCard(dealer.getCard(1));
               dealerCards.addCard(dealer.getCard(2));
               dealerCards.addCard(dealer.getCard(3));
               
               //remove the cards from dealers hand. index does not change on purpose
               dealer.removeCard(0);
               dealer.removeCard(0);
               dealer.removeCard(0);
               dealer.removeCard(0);
               
               //updates gameArea
               gameArea.removeAll();
               gameArea.revalidate();
               gameArea.add(new JLabel(playerCards.getCard(3).getCardIcon()));
               gameArea.add(new JLabel(dealerCards.getCard(3).getCardIcon()));
               
               //if player has higher card
               if (playerCards.getCard(3).getValue() > dealerCards.getCard(3).getValue())
               {
                  for (int i = 0; i < 4; i++)
                  {
                     player.addCard(playerCards.getCard(i));
                     player.addCard(dealerCards.getCard(i));
                  }
                  
                  //the two cards that started the war
                  player.addCard(playerCard);
                  player.addCard(dealerCard);
                  
                  gameText.setText("Player takes the War!");
                  playerLabel.setText("Player: " + player.getHandSize());
                  dealerLabel.setText("Dealer: " + dealer.getHandSize());
                  war.setVisible(false);
                  play.setVisible(true);
                  buttonPanel.revalidate();
               }
               //dealer wins ties in war, so just an else statement
               else
               {
                  for (int i = 0; i < 4; i++)
                  {
                     dealer.addCard(playerCards.getCard(i));
                     dealer.addCard(dealerCards.getCard(i));
                  }
                  
                  //the two cards that started the war
                  dealer.addCard(playerCard);
                  dealer.addCard(dealerCard);
                  
                  gameText.setText("Player lost the War!");
                  playerLabel.setText("Player: " + player.getHandSize());
                  dealerLabel.setText("Dealer: " + dealer.getHandSize());
                  war.setVisible(false);
                  play.setVisible(true);
                  buttonPanel.revalidate();

               }

            
            }
            //if player does not have enough cards, they play however many they have
            else if (player.getHandSize() <= 4)
            {
               tempCard = player.getCard(player.getHandSize() - 1); //players last card
            
               //draw four cards and store them in the temp dealerCards hand
               dealerCards.addCard(dealer.getCard(0));
               dealerCards.addCard(dealer.getCard(1));
               dealerCards.addCard(dealer.getCard(2));
               dealerCards.addCard(dealer.getCard(3));
               
               //remove the cards from dealers hand. index does not change on purpose
               dealer.removeCard(0);
               dealer.removeCard(0);
               dealer.removeCard(0);
               dealer.removeCard(0);
               
               //if player is higher
               if (tempCard.getValue() > dealerCards.getCard(3).getValue())
               {
                  for (int i = 0; i < 4; i++)
                  {
                     player.addCard(dealerCards.getCard(i));
                  }
                  player.addCard(playerCard);
                  player.addCard(dealerCard);
                  gameText.setText("Player takes the War!");
                  playerLabel.setText("Player: " + player.getHandSize());
                  dealerLabel.setText("Dealer: " + dealer.getHandSize());
                  war.setVisible(false);
                  play.setVisible(true);
                  buttonPanel.revalidate();
               }
               //this is a war, tie goes to the dealer, hence its just an ELSE statement
               else
               {
                  gameText.setText("Player Loses! Try Again Next Time");
                  playerLabel.setText("Player: " + player.getHandSize());
                  dealerLabel.setText("Dealer: " + dealer.getHandSize());
                  play.setVisible(false);
                  close.setVisible(true);
                  buttonPanel.revalidate();
               }
            }
            //if dealer does not have enough cards
            else if (dealer.getHandSize() <= 4)
            {
               tempCard = dealer.getCard(dealer.getHandSize() - 1); //players last card
            
               //draw four cards and store them in the temp dealerCards hand
               playerCards.addCard(player.getCard(0));
               playerCards.addCard(player.getCard(1));
               playerCards.addCard(player.getCard(2));
               playerCards.addCard(player.getCard(3));
               
               //remove the cards from dealers hand. index does not change on purpose
               player.removeCard(0);
               player.removeCard(0);
               player.removeCard(0);
               player.removeCard(0);
               
               //if player is higher, player wins game
               if (tempCard.getValue() > dealerCards.getCard(3).getValue())
               {
                  gameText.setText("Player Wins! Congratulations!");
                  playerLabel.setText("Player: " + player.getHandSize());
                  dealerLabel.setText("Dealer: " + dealer.getHandSize());
                  war.setVisible(false);
                  close.setVisible(true);
                  buttonPanel.revalidate();
               }
               //this is a war, tie goes to the dealer, hence its just an ELSE statement
               else
               {
                  for (int i = 0; i < 4; i++)
                  {
                     dealer.addCard(playerCards.getCard(i));
                  }
                  dealer.addCard(playerCard);
                  dealer.addCard(dealerCard);
                  gameText.setText("Player lost the War!");
                  playerLabel.setText("Player: " + player.getHandSize());
                  dealerLabel.setText("Dealer: " + dealer.getHandSize());
                  war.setVisible(false);
                  play.setVisible(true);
                  buttonPanel.revalidate();
               }
            }
         
         }
         else if (event.getSource() == close)
         {
            System.exit(0);
         }
      
      }
   }
}   