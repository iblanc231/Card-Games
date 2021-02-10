import javax.swing.*;

public class Card
{
   protected int       value;
   private   char      suit;
   private   String    name;
   private   ImageIcon img;
   
   public Card()
   {
      setValue(1);
      setSuit('S');
      setName();
   }
   
   public Card(int v,char s)
   {
      setValue(v);
      setSuit(s);
      setName();
      img = new ImageIcon("imgs/" + name + ".png");
   }
   
   public void setValue(int v)
   {
      value = v;
   }
   
   public void setSuit(char s)
   {
      suit = s;
   }
   
   private void setName()
   {
      switch (value)
      {
         case 1:
            name = "A-";
            break;
         case 2:
            name = "2-";
            break;
         case 3:
            name = "3-";
            break;
         case 4:
            name = "4-";
            break;
         case 5:
            name = "5-";
            break;
         case 6:
            name = "6-";
            break;
         case 7:
            name = "7-";
            break;
         case 8:
            name = "8-";
            break;
         case 9:
            name = "9-";
            break;
         case 10:
            name = "10-";
            break;
         case 11:
            name = "J-";
            break;
         case 12:
            name = "Q-";
            break;
         case 13:
            name = "K-";
            break;
         default:
            name = "ERROR";
            break;
      }
      name += suit;
   }
   
   public int getValue()
   {
      return value;
   }
   
   
   
   public char getSuit()
   {
      return suit;
   }
   
   public ImageIcon getCardIcon()
   {
      return img;
   }

}