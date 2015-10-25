//Computer Science Summative: Snakes & Ladders - Main Game
//June 13/2014
//Stanley Huang & Josephine Mok
//Runs the actual game.

import hsa.Console;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
public class SnakesAndLadders
{
  //Class-wide variables
  static Console c;
  static int round = 0;                     //round number
  static int players = 0;                   //number of players
  static Player[] users = new Player [2];   //Stores the actual player objects
  static Image bg;                          //Stores the background image
  static String computerChoice = null;      //Stores the what the computer picked
  
  //main method - runs board and player classes to play snakes and ladders
  public static void main(String[] args)
  {
    //Variable Decleration
    c = new Console(32, 63);  //creates console with 32 rows and 63 collumns
    char continueGame = 'N';  //user input whether or not they want to continue
    Image main, main2;        //the image to draw
    int dieRoll;              //stores the number the user rolled
    char replayGame = 'Y';    //user input whether or not they want to replay the game at the end
    
    //Try to import the image from the file
    try
    {
      main = ImageIO.read(new File("Graphics\\main.jpg"));
      main2 = ImageIO.read(new File("Graphics\\main2.png"));
      bg = ImageIO.read(new File("Graphics\\screen.png"));
    }
    catch(IOException e)      //File not found
    {
      main = null;
      main2 = null;
      bg = null;
    }//try catch statement
    try
    {
      c.drawImage(main,0,0,null);
      Thread.sleep(3000);
    }
    catch(Exception e)
    {
    }//try catch statement
    
    //Draw the image (Image, x location, y location, null)
    do  //plays the first round, then only repeats if the user wants to restart the game
    {
      c.clear();
      c.drawImage(main2,0,0,null);  //start screen
      c.setCursor(22,24);
      c.println("Enter \"Y\" to start.");
      continueGame = c.getChar();
      
      if (continueGame == 'Y' ^ continueGame == 'y')  //if the user wants to continue, input information to start game
      {
        c.print("\n\n -> Enter the amount of players (1-6): ");
        players = c.readInt();
        if (players == 1) //sets up 1 player vs. computer
        {
          users [0] = new Player(c, 0);      //player
          users [1] = new Player(c);         //computer
          players = 2;    //two players in total
        }
        else              //sets up multiplayer without computer 
        {
          users = new Player[players];
          for (int i = 0; i<players; i++)
          {
            users[i] = new Player(c, i);
          }
        }//if statement
        
        c.clear();
        //creates a board
        c.drawImage(bg,0,0,null);
        Board a = new Board(c);
        for (int i = 0; i < players; i++)                            //draws the initial markers
          users[i].drawMarker(c, users[i].getX(), users[i].getY());
        round++;                                                     //the user(s) have started a round
        
        for (int b = 0; b < players; b++)
        {
          if (users[b].getPosition() != 100)   //while no one is on block 100
          {
            c.setCursor(28,2);
            if (continueGame == 'Y' ^ continueGame == 'y')
            {
              do   //repeats only if the user(s) want to continue onto the next turn
              {
                for (int j = 0; j < players; j++)  //go through every players' turn to advance to another block
                {
                  dieRoll = die();
                  users[j].move(c, dieRoll);      //moves the markers to the next location
                  redraw();
                  c.setCursor(28,2);
                  c.println(" " + users[j].getName() + " rolled a " + dieRoll);
                  c.println(" Enter \"Y\" to continue and \"N\" to stop.");
                  continueGame = c.getChar();
                  
                  //checks for special cases where markers jump to designated block
                  if (users[j].getPosition() == 18)      //lands the tail of green snake
                  {
                    //stores new location of 6
                    users[j].move(c,-12);         //moves back 12 blocks
                    c.println(" " + users[j].getName() + " landed on a snake");
                    c.println(" Enter \"Y\" to continue and \"N\" to stop.");
                    continueGame = c.getChar();
                  }
                  else if (users[j].getPosition() == 73)  //lands the tail of black snake
                  {
                    //stores new location of 37
                    users[j].move(c,-36);         //moves back 36 blocks
                    c.println(" " + users[j].getName() + " landed on a snake");
                    c.println(" Enter \"Y\" to continue and \"N\" to stop.");
                    continueGame = c.getChar();
                  }
                  else if (users[j].getPosition() == 98) //lands tail of blue snake
                  {
                    //stores new location of 90
                    users[j].move(c,-8);          //moves back 8 blocks
                    c.println(" " + users[j].getName() + " landed on a snake");
                    c.println(" Enter \"Y\" to continue and \"N\" to stop.");
                    continueGame = c.getChar();
                  }
                  else if (users[j].getPosition() == 9)  //lands at the bottom of a ladder
                  {
                    //stores new location of 31
                    users[j].move(c,22);          //moves up 22 blocks
                    c.println(" " + users[j].getName() + " went up a ladder!");
                    c.println(" Enter \"Y\" to continue and \"N\" to stop.");
                    continueGame = c.getChar();
                  }
                  else if (users[j].getPosition() == 27) //lands at the bottom of a ladder
                  {
                    //stores new location of 53
                    users[j].move(c,26);          //moves up 26 blocks
                    c.println(" " + users[j].getName() + " went up a ladder!");
                    c.println(" Enter \"Y\" to continue and \"N\" to stop.");
                    continueGame = c.getChar();
                  }
                  else if (users[j].getPosition() == 43) //lands at the bottom of a ladder
                  {
                    //stores new location of 80
                    users[j].move(c,37);          //moves up 37 blocks
                    c.println(" " + users[j].getName() + " went up a ladder!");
                    c.println(" Enter \"Y\" to continue and \"N\" to stop.");
                    continueGame = c.getChar();
                  }
                  else if (users[j].getPosition() == 13) //lands on the the unlucky block 13 block
                  {
                    //stores new location of 1
                    users[j].move(c,-12);         //moves back 12 blocks;
                    c.println(" " + users[j].getName() + " landed on unlucky block 13 and died");
                    c.println(" Enter \"Y\" to continue and \"N\" to stop.");
                    continueGame = c.getChar();
                  }//if statement
                  else if (users[j].getPosition() == 100)  //lands on the final block
                  {
                    c.println("Congratulations! " + users[j].getName() + " has won!");
                    continueGame = 'N';
                    c.println("Enter \"Y\" to play again.");
                    replayGame = c.getChar();
                    if (replayGame != 'Y' ^ replayGame !='y')
                      break;
                  }//if statement for special cases of movement
                  for (int i = 0; i < players; i++)  //to check if more than 1 player is on the same block, and if they are, will determine who gets to stay
                  {
                    if (!(i==j))  //if statemet to determine that it is not the same user
                    {
                      if (users[j].getPosition() == users[i].getPosition())
                      {
                        if (!(users[j].getPosition()==1))  //Does not run if they are at 1
                        {
                          int move;   //store the amount the block needs to move
                          move = ((((int)(users[j].getPosition()/10.0))*10)-users[j].getPosition())+1;
                          if (move == 0)
                            move = -10;
                          if (rockPaperScissors(users[j], users[i]))
                            users[i].move(c, move);
                          else
                            users[j].move(c, move);
                          if (!(computerChoice == null))    //prints what the computer chose for RPS
                              c.println(" The computer chose " + computerChoice);
                          c.println(" " + users[j].getName() + " lost and will be moved to the beginning of the row");
                        }//if statement
                        c.println(" Enter \"Y\" to continue and \"N\" to stop.");
                        continueGame = c.getChar();
                      }//if statement when 2 users are on the same block
                    }//if statement it is not the same user
                  }//for statment to check if more than one player is on the same block
                }//for statement to go through ever players turn
              }while (continueGame == 'Y' ^ continueGame == 'y');  //loop statement
            }//if statement
          }//if statement to determine that no user is on block 100
        }//for loop that loops through all the players
      }//if statement to determine if the user(s) want to continue
    }while (replayGame == 'Y' ^ replayGame == 'y');
    c.println("**THANKS FOR PLAYING**");  //once the user(s) do not want play anymore
  }//main method
  
  //rockPaperScissors method - Mini game that allows user to play rock, paper, scissors against another player that is on the same block, returns true if the first player wins
  //Player x - the player that just landed on the same block
  //Player y - the player initially on the block
  public static boolean rockPaperScissors(Player x, Player y)
  {
    char xRPS = 'A';        //first player's option
    char yRPS = 'A';        //second player's option
    int random = 0;         //random number to determine computers choice
    random = (int)(Math.random()*3+1);

    boolean result = false; //default setting for result, only changes if first player (x) wins
    c.setCursor(28,2);
    do   //while both users have the same option chosen
    {
      if (x.isComputer())   //if one of the users on the same block is a computer
      {
        switch (random)     //assigns plays for random numbers
        {
          case 1:
            xRPS = 'R';
            computerChoice = "Rock";
            break;
          case 2:
            xRPS = 'P';
            computerChoice = "Paper";
            break;
          case 3:
            xRPS = 'S';
            computerChoice = "Scissors";
            break;
        }//switch case statement to convert choices
        
        c.println(" " + y.getName() + " input your choice\n(R for rock, P for paper and S for scissors): ");
        yRPS = c.getChar();
      }
      else if (y.isComputer())  //if second player is the computer
      {
        switch (random)
        {
          case 1:
            yRPS = 'R';
            computerChoice = "Rock";
            break;
          case 2:
            yRPS = 'P';
            computerChoice = "Paper";
            break;
          case 3:
            yRPS = 'S';
            computerChoice = "Scissors";
            break;
        }//switch case statement
        c.println(" " + x.getName() + " , input your choice\n (R for rock, P for paper and S for scissors): ");
        xRPS = c.getChar();
      }
      else   //if both users are actual players
      {
        c.println(" " + x.getName() + ",   input your choice\n (R for rock, P for paper and S for scissors): ");
        xRPS = c.getChar();
        c.println(" " + y.getName() + ",   input your choice\n (R for rock, P for paper and S for scissors): ");
        yRPS = c.getChar();
      }//if statement
      if ((xRPS == 'R' && yRPS == 'S') ^ (xRPS == 'P' && yRPS == 'R') ^ (xRPS == 'S' && yRPS == 'P'))  //conditions where xRPS wins
      {
        result = true;
      }//if statement
      if (xRPS == yRPS)    //if they tied
        c.println(" -> Tie, try again!");
    }while (xRPS == yRPS);
    return result;
  }//rockPaperScissors method
  
  
  //die method - Generates a random number between 1 - 6 to simulate rolling a die
  //No parameters
  public static int die()
  {
    int randomDie = 0;  //random number for die roll
    randomDie = (int)(Math.random()*6+1);
    return randomDie;
  }//die method
  
  //redraw method - Reprints everyting after every turn
  //No parameters
  public static void redraw()
  {
    c.clear();
    c.drawImage(bg,0,0,null);
    Board a = new Board(c);
    for(int z = 0; z < users.length; z++)  //reprints all of the blocks
    {
      users[z].drawMarker(c, users[z].getX(), users[z].getY());
    }//for loop
  }//redraw method
}