package SnakeLadder; // Ladder And Snake
//-------------------------------
//Assignment: Assignment 2  - 10%
//Written by: Karina de Vargas Pereira & Sophie Hsu
//JAC ID: 2300594 & 0754336
//------------------------------- 

import java.util.Scanner;

public class PlayLadderAndSnake
{

	public static void main(String[] args) throws InterruptedException // throws added due to delay
	{
		//display welcome message
		System.out.print("\t  =========================================================");
		System.out.print("\n\t        =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.print("\n\t        =                WELCOME TO                 =");
		System.out.print("\n\t        =        LADDER AND SNAKE JAVA GAME         =");
		System.out.print("\n\t        =            By Karina & Sophie             =");
		System.out.print("\n\t        =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.print("\n\t  =========================================================\n");

		int numOfPlayers = getNumberOfPlayers(); //goes to a method that interacts with user to get the input of numbers of players

		LadderAndSnake game1 = new LadderAndSnake(numOfPlayers); //goes to LadderAndSnake class where more information is collected, the players are created, the board is created, the players order is set.
		//game1.test();
		game1.play(); // after getting all information, and have everything carted and set, this line goes to the method inside the class LadderAndSnake, this method makes the game happen
	}

	//creating the methods
	public static int getNumberOfPlayers()
	{
		System.out.println();
		System.out.println("How many players will play LADDER AND SNAKE JAVA GAME?");
		System.out.print("==>");
		Scanner kb = new Scanner(System.in);
		int numOfPlayers = kb.nextInt();

		for (int i = 1; i < 5; i++)
		{
			if (numOfPlayers > 1 && numOfPlayers < 5)
			{
				System.out.println("===================================================");
				break;
			}
			else if(i == 4) // forth try of entering the number of players
			{
				System.out.println("Error: Invalid number of players. You have exhausted all your attempts.");
				System.out.print("Program terminated");
				System.out.print("\n================================================================================================================");
				System.out.print("\n\t\t    =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				System.out.print("\n\t\t    =     THANK YOU FOR PLAYING LADDER AND SNAKE JAVA GAME     =");
				System.out.print("\n\t\t    =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				System.out.print("\n================================================================================================================\n");
				System.exit(0);
			}
			else if(numOfPlayers < 2 || numOfPlayers > 4)
			{
				System.out.println("Error: Invalid number of players. You have " + (4-i) + " tries left. \nNumber of players must be between 2 and 4 inclusively. Enter number of players again:");
				System.out.print("==>");
				numOfPlayers = kb.nextInt();
			}
		}

		return numOfPlayers;
	}
}
