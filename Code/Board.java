//Computer Science Summative - Board Class
//Friday, June 13, 2014
//Stanley Huang & Joesphine Mok
//Class with with methods and attributes for the board

import hsa.Console;
import java.awt.*;
public class Board
{  
  //constructor method - creates the board and the snakes and ladders
  //Console c - the console in which the board is to be printed
  public Board (Console c)
  {
    this.createBoard(c);
    this.createSnakesAndLadders(c);
  }//constructor method
  
  //createBoard method - Creates the actual board
  //Console c - where the board is to be created
  public void createBoard(Console c)
  {
    int[] index = new int[]{100,99,98,97,96,95,94,93,92,91,81,82,83,84,85,86,87,88,89,90,80,79,78,77,76,75,74,73,72,71,61,62,63,64,65,66,67,68,69,70,60,59,58,57,56,55,54,53,52,51,41,42,43,44,45,46,47,48,49,50,40,39,38,37,36,35,34,33,32,31,21,22,23,24,25,26,27,28,29,30,20,19,18,17,16,15,14,13,12,11,1,2,3,4,5,6,7,8,9,10};  //block numbers to be printed on square
    int indexX = 2;   //prints the number onto the square
    int indexY = 11;  //y position for tile number
    int x = 0;        //value at which the next tile in the same row is shown
    int y = 49;       //length of the square
    int i = 0;        //value to keep track of array
    
    for (int down = 0; down <442; down = down + 49)  //to display 10 tiles down
    {
      for (int across = 0; across <10; across++)     //to display 10 tiles accross
      {
        if (!(index[i]%2 == 0))
        {
          c.setColor(Color.red);
          c.fillRect(x,down,y,y);
          c.setColor(Color.black);
        }//if statement
        
          c.drawRect(x,down,y,y);
          x = x + 49;
        
        if (across < 10)  //to compensate so that the no extra tile numbers are printed
        {
          c.drawString(""+index[i], indexX, indexY);  //outputs the number for each tile
          indexX = indexX + 49;  //shifts the tile number to the right
          i++;
        }//if statement
      }//for statement to display 10 tiles accross
      
      x = 0;  //reset value for next run
      indexX = 2;  //reset value for next run
      indexY = indexY +49;
      
    }//for statement to display 10 tiles down
  }//createBoard method
  
  //createSnakesAndLadders Method - outputs the snakes and the ladders
  //Console c - the console in which the snakes and ladders will be printed to
  public void createSnakesAndLadders(Console c)
  {
    //green snake - 18 to 6
    c.setColor(Color.green);
    c.fillRect(115,420,5,45);   //tail
    c.fillRect(115,462,130,10); //body
    c.fillOval(240,458,30,20);  //head

    //black snake - 73 to 37
    c.setColor(Color.black);
    c.fillRect(380,120,5,45);   //tail
    c.fillRect(165,165,220,10); //body
    c.fillRect(165,165,10,120);
    c.fillOval(160,280,20,30);  //head

    //blue snake 98 to 90
    c.setColor(Color.blue);
    c.fillRect(120,30,5,45);    //tail
    c.fillRect(120,75,320,10);  //body
    c.fillOval(430,70,30,20);   //head

    //face features
    c.setColor(Color.black);
    c.fillOval(260,462,4,4);    //green snake's eyes
    c.fillOval(260,468,4,4);
    c.setColor(Color.white);
    c.fillOval(165,300,4,4);    //yellow snake's eyes
    c.fillOval(171,300,4,4);
    c.fillOval(448,73,4,4);     //blue snake's eyes
    c.fillOval(448,79,4,4);
    
    //ladders
    c.setColor(Color.black);
    
    //27 to 53 ladder
    int topLeft = 350;          //the pixel position of the top left
    int bottomLeft = 310;       //the pixel position of the bottom left
    int topRight = 365;         //the pixel position of the top right
    int bottomRight = 325;      //the pixel position of the bottom right
    
    for (int a = 0; a < 3; a++) //prints the sides of the ladder, in for loop to create a thicker output
    {
      c.drawLine(bottomLeft,380,topLeft,220);
      topLeft++;
      bottomLeft++;
      c.drawLine(bottomRight,380,topRight,220);
      topRight++;
      bottomRight++;
    }//for loop
    
    c.drawLine(345,240,360,240);  //printing the railings
    c.drawLine(335,280,350,280);
    c.drawLine(325,320,340,320);
    c.drawLine(315,360,330,360);
    
    //43 to 80 ladder
    topLeft = 110;
    bottomLeft = 20;
    topRight = 125;
    bottomRight = 35;
    
    for(int a = 0; a < 3; a++)        //printing the sides and making the output thicker by looping and changing pixels
    {
      c.drawLine(bottomLeft,120,topLeft,280);
      topLeft++;
      bottomLeft++;
      c.drawLine(bottomRight,120,topRight,280);
      topRight++;
      bottomRight++;
    }//for loop
    
    c.drawLine(33,140,48,140);   //outputting the railings
    c.drawLine(55,180,70,180);
    c.drawLine(78,220,93,220);
    c.drawLine(100,260,114,260);
    
    //9 to 31 ladder
    topLeft = 450;
    bottomLeft = 410;
    topRight = 465;
    bottomRight = 425;
    
    for (int a = 0; a < 3; a++)      //printing the sides and making the output thicker by looping and changing pixels
    {
      c.drawLine(bottomLeft,480,topLeft,320);
      topLeft++;
      bottomLeft++;
      c.drawLine(bottomRight,480,topRight,320);
      topRight++;
      bottomRight++;
    }//for loop
    c.drawLine(445,340,460,340);  //outputting the railings
    c.drawLine(435,380,450,380);
    c.drawLine(425,420,440,420);
    c.drawLine(415,460,430,460);
  }//createSnakesAndLadders method
}//Board class