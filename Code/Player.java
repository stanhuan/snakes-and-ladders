//Computer Science Summmative - Snakes & Ladders - Player Class
//Friday, June 13, 2014
//Josephine Mok and Stanley Huang
//Purpose - The actual player object with the corresponding attributes and services

//Imports
import hsa.Console;
import java.awt.*;

public class Player
{
  //Attributes
  private String colour = "blue";  //Stores the user's custom colour
  private String name;             //Stores the user's name
  private int xPos;                //Stores the user's horizontal position in pixels
  private int yPos;                //Stores the user's vertical position in pixels
  private String type;             //Stores the type of player (eg. Computer, Player 1)
  
  //Constructor Method - initializes a user-controlled player with user defined colour and name
  //int num - number used to store player number
  //Console c - using previous console to print out question
  public Player(Console c, int num)
  {
    int choice;                     //Stores the user's choice of colour
    
    type = "Player " + num;
    
    c.print("\n -> Please enter Player " + (num+1) + "'s name: ");
    name = c.readLine();
    
    c.println(" -> Please enter " + name + "'s desired colour:");
    c.print("\tCOLOUR OPTIONS\n\t1. Blue\n\t2. Orange\n\t3. Green\n\t4. Magenta\n\t5. Yellow\n\t6. Cyan\n\t-> ");
    choice = c.readInt();

    //determines user's colour choice
    switch (choice)
    {
      case 1:
        colour = "blue";
        break;
      case 2:
        colour = "orange";
        break;
      case 3:
        colour = "green";
        break;
      case 4:
        colour = "magenta";
        break;
      case 5:
        colour = "yellow";
        break;
      case 6:
        colour = "cyan";
        break;
      default:
        c.println("Invalid Number");
        break;
    }//switch/case statement
    
    this.changeXY(1);                //Changes the xPos and yPos so the player is at the first block
  }//constructor method
  
  //Constructor Method - Used when creating a computer controlled player with "CPU" as the name, black as the colour and computer as the type
  //No parameters necessary
  public Player(Console c)
  {
    name = "CPU";
    colour = "black";
    type = "Computer";
    this.changeXY(1);
  }//constructor method
  
  //getPosition Method - used to convert pixel value of position into a block number
  //No Parameteres Necessary - just accesses the attributes
  public int getPosition()
  {
    int result;                          //Stores the tile number for return later
    
    if (((int)(yPos/49)) == 0)           //finds which row the marker is in then the collumn and returns the position accordingly
      result = (100-((int)(xPos/49)));
    else if (((int)(yPos/49)) == 1)
      result = (81+((int)(xPos/49)));
    else if (((int)(yPos/49)) == 2)
      result = (80-((int)(xPos/49)));
    else if (((int)(yPos/49)) == 3)
      result = (61+((int)(xPos/49)));
    else if (((int)(yPos/49)) == 4)
      result = (60-((int)(xPos/49)));
    else if (((int)(yPos/49)) == 5)
      result = (41+((int)(xPos/49)));
    else if (((int)(yPos/49)) == 6)
      result = (40-((int)(xPos/49)));
    else if (((int)(yPos/49)) == 7)
      result = (21+((int)(xPos/49)));
    else if (((int)(yPos/49)) == 8)
      result = (20-((int)(xPos/49)));
    else
      result = (1+((int)(xPos/49)));
    
    return result;
  }//getPosition method
  
  //changeXY Method - Used to convert from block position to pixel position
  //int location - The block position that will be converted to a pixel position
  public void changeXY(int location)
  {
    yPos = 450 - ((int)((location-1)/10.0))*49;  //converting the row to the yPos value
    
    //determining x position for an assending row
    if (((location>=1)&&(location<=10))||((location>=21)&&(location<=30))||((location>=41)&&(location<=50))||((location>=61)&&(location<=70))||((location>=81)&&(location<=90)))
      xPos = ((((location - (((int)((location-1)/10.0)))*10))-1)*49)+9;
    
    //determining x position for a descending row
    else
      xPos = (Math.abs(9-  ((location - ((int)((location-1)/10.0))*10)-1))  *49)+9;
  }//changeXY method
  
  //drawMarker Method - Used to create the player marker
  //console c - Using the main console to print the marker
  //int x - the horizontal position of the marker
  //int y - the vertical position of the marker
  public void drawMarker(Console c, int x, int y)
  {
    this.setColour(c);   //uses other method to change colour to the attribute accordingly
    c.fillOval(x, y, 30, 30);
    c.setColor(Color.black);
    c.drawOval(x, y, 30, 30);
  }//drawMarker method
  
  //setColour method - sets the colour of the graphical output to correspond with the user's colour attribute
  //Console c - Uses the previous console to set the colour of the output
  public void setColour(Console c)
  {
    if (colour.equals("blue"))
      c.setColor(Color.blue);
    else if (colour.equals("orange"))
      c.setColor(Color.orange);
    else if (colour.equals("green"))
      c.setColor(Color.green);
    else if (colour.equals("yellow"))
      c.setColor(Color.yellow);
    else if (colour.equals("magenta"))
      c.setColor(Color.magenta);
    else if (colour.equals("cyan"))
      c.setColor(Color.cyan);
    else
      c.setColor(Color.black);
  }//setColour method
  
  //move method - Moves the player marker to new position
  //Console c - Where the marker will be printed
  //int roll - What the player rolled on the die
  public void move (Console c, int roll)
  {
    int newLocation;     //Stores the new location (original position) + die roll) to use in other if statement
    
    newLocation = this.getPosition() + roll;
    if (newLocation >= 100)
      newLocation = 100;                       //does not allow marker to go past 100
    this.changeXY(newLocation);
  }//move Method
  
  //getName method - returns the player's name
  public String getName()
  {
    return name;
  }//getName method
  
  //getX method - returns the player's horizontal position
  public int getX()
  {
    return xPos;
  }//getX method
  
  //getY method - returns the player's vertical position
  public int getY()
  {
    return yPos;
  }//get Y method
  
  //isComputer method - returns true if the player in question is a computer
  public boolean isComputer()
  {
    boolean result = false;   //Stores the result to be returned by the method
    if (type.equals("Computer"))
      result = true;
    return result;
  }//isComputer method
}//Player class