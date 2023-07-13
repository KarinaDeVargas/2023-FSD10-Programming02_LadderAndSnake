package SnakeLadder;
//-------------------------------
//Assignment: Assignment 2  - 10%
//Written by: Karina de Vargas Pereira & Sophie Hsu
//JAC ID: 2300594 & 0754336
//------------------------------- 

import java.util.*;
import java.util.concurrent.TimeUnit;

public class LadderAndSnake {

  private int numOfPlayers; // number of players as input from user is passed in here by the constructor
  private ArrayList<Player> players; // creates a list of players based on the blueprint class: Players
  private static Map<Integer, Integer> gameBoard = new HashMap(); //

  public LadderAndSnake(int numOfPlayers) {
    this.numOfPlayers = numOfPlayers; // constructor by parameter, as requested in the assignment
  }

  public void play() // which actually initiate the core engine of the game where the players start
                     // to play the game.
  {
    this.players = new ArrayList<Player>(numOfPlayers);

    System.out.println("This game will be played by " + numOfPlayers + " players."); // as requested in the
                                                                                     // assignment

    setGameBoard();

    setNameOfPlayers();

    System.out.println("Now deciding which player will start playing:");
    this.players = setPlayersOrder(this.players);

    System.out.print(
        "\n================================================================================================================");
    System.out.print("\n\t\t    =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    System.out.print("\n\t\t    =                  STARTING THE GAME                       =");
    System.out.print("\n\t\t    =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    System.out.print(
        "\n================================================================================================================\n\n");

    printGameBoard();
    setLaddersAndSnakes();

    delay(2);

    Player winnerPlayer = new Player();

    while (winnerPlayer.getPlayerName() == null) {
      for (Player player : players) // "for each Player in (the list) players
      {
        System.out.print("\nPlayer " + player.getPlayerName() + " rolls the dice.");
        delayWithBar(100);
        int diceValue = flipDice();

        player.setCurrentPosition(player.getCurrentPosition() + diceValue); // gets player current position and
                                                                            // adds the dice value and stores it
                                                                            // in the "setCurrentPosition"

        if (player.getCurrentPosition() > 100) // to make player bounce back if not exactly the dice number to
                                               // get to the 100 position
        {
          player.setCurrentPosition(100 - (player.getCurrentPosition() - 100));
        }

        if (gameBoard.get(player.getCurrentPosition()) == player.getCurrentPosition()) {
          System.out.println("•	Player " + player.getPlayerName() + " got a dice value of " + diceValue
              + "; now in square " + player.getCurrentPosition());
          delay(1);
        } else {
          int previousPosition = player.getCurrentPosition(); // this int stores the position before a ladder
                                                              // or a snake is calculated
          player.setCurrentPosition(gameBoard.get(player.getCurrentPosition())); // new position when there is
                                                                                 // a ladder or snake

          if (player.getCurrentPosition() > previousPosition) // in case of a ladder
          {
            System.out.println("•	Player " + player.getPlayerName() + " got a dice value of " + diceValue
                + "; gone to square " + previousPosition + " then up to square "
                + player.getCurrentPosition());
            delay(1);
          } else // in case of a snake
          {
            System.out.println("•	Player " + player.getPlayerName() + " got a dice value of " + diceValue
                + "; gone to square " + previousPosition + " then down to square "
                + player.getCurrentPosition());
            delay(1);
          }

        }

        if (player.getCurrentPosition() == 100) {
          // shows winner message
          winnerPlayer = player;
          break; // end this loop
        }
      }

      if (winnerPlayer.getPlayerName() == null) {
        System.out.println("\n• Game not over; flipping dice again.");
        delay(1);
      }
    }

    System.out.println("\n=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
    System.out.println("There is a winner: ");
    System.out.println(".");
    delay(1);
    System.out.println(".");
    delay(1);
    System.out.println(".");
    delay(1);
    System.out.println(".");
    System.out.println("Congratulations: ==> " + winnerPlayer.getPlayerName() + "!!! <==");
    System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
    System.out.println("\n=*=*=*=*=*=*=*=*=*=*=*=*END OF GAME=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");

  }

  private void setLaddersAndSnakes() // Accordingly with given information from the assignment
  {
    gameBoard.put(1, 38); // when player reaches number 1, player is sent to number 38 as per ladder.
    gameBoard.put(4, 14);
    gameBoard.put(9, 31);
    gameBoard.put(16, 6);
    gameBoard.put(21, 42);
    gameBoard.put(28, 84);
    gameBoard.put(36, 44);
    gameBoard.put(48, 30);
    gameBoard.put(51, 67);
    gameBoard.put(62, 19);
    gameBoard.put(64, 60);
    gameBoard.put(71, 91);
    gameBoard.put(80, 100);
    gameBoard.put(93, 68);
    gameBoard.put(95, 24);
    gameBoard.put(97, 76);
    gameBoard.put(98, 78);
  }

  private void setGameBoard() // method that creates the game board
  {
    for (int i = 100; i > 0; i--) {
      gameBoard.put(i, i);
    }

  }

  private void setNameOfPlayers() // method interacts with user to get the name of the players
  {
    Scanner kb = new Scanner(System.in);
    ArrayList<String> nickNameArray = new ArrayList<String>(numOfPlayers);

    for (int i = 1; i <= numOfPlayers; i++) {
      System.out.println("\nPlease enter Player " + i + " Nick Name: ");
      System.out.print("==>");
      String NickName = kb.next();
      nickNameArray.add(NickName);
    }

    System.out.println("\n===================================================");
    System.out.println("All players Nick Names: " + nickNameArray);
    System.out.println("===================================================");
    System.out.println();

    for (String name : nickNameArray) // for each name in (the list) nickNameArray, then:
    {
      Player player = new Player(name); // initializes object class Player
      players.add(player); // add the player name to the list
    }
  }

  private ArrayList<Player> setPlayersOrder(ArrayList<Player> players) {

    // Flip dice among players
    for (Player player : players) {
      int diceValue = flipDice();
      System.out.printf("Player %s rolled %d%n", player.getPlayerName(), diceValue);
      player.setDiceValue(diceValue);
    }

    // Sort the dice values based on highest dice rolls
    players.sort(Comparator.comparing(Player::getDiceValue).reversed());

    ArrayList<Player> finalPlayerList = new ArrayList();
    int counter = 1;

    // Repeat until players list is empty, removing highest number at a time if no
    // re-roll is required.
    while (players.size() > 0) {
      if (players.size() == 1) {
        finalPlayerList.add(players.get(0));
        players.remove(players.get(0));
        break;
      }
      // Get the first player in the list
      Player currentPlayer = players.get(0);
      int currentDiceValue = currentPlayer.getDiceValue();
      int newRollCurrPlayer = flipDice();
      currentPlayer.setDiceValue(newRollCurrPlayer + (counter * 10));
      // Create another loop to determine duplicate rolls
      for (int j = 1; j < players.size(); j++) {
        Player nextPlayer = players.get(j);
        if (nextPlayer.getDiceValue() == currentDiceValue) {
          // Re-rolling dice for current player and adding multiplier in order to sort by
          // highest roll
          int diceRoll = flipDice();
          nextPlayer.setDiceValue(diceRoll + (counter * 10));
          System.out.printf("Re-rolling player %s : New roll -> %d%n", nextPlayer.getPlayerName(), diceRoll);
        } else {
          if (j == 1) {
            finalPlayerList.add(currentPlayer);
            players.remove(currentPlayer);
          }
          break;
        }
        System.out.printf("Re-rolling player %s : New roll -> %d%n", currentPlayer.getPlayerName(), newRollCurrPlayer);

      }
      counter++;
      delay(1);

      // Re-sorting the list with updated list of players
      players.sort(Comparator.comparing(Player::getDiceValue).reversed());
    }

    System.out.println("Final order of players:");

    for (int i = 0; i < finalPlayerList.size(); i++) {
      System.out.printf("Rank #%d : Player %s%n", i + 1, finalPlayerList.get(i).getPlayerName());
    }

    return finalPlayerList;
  }

  private static int flipDice() // which should return a random value between 1 and 6 inclusively.
  {
    Random r = new Random();
    int diceValue = r.nextInt(6) + 1;
    return diceValue;
  }

  private void printGameBoard() {
    int previousJ = 0;

    for (int i = 100; i > 0; i--) {
      if (i < 100 && i % 10 == 0) {
        System.out.println();
        i = i - 9;
        for (int j = i; j < i + 10; j++) {
          System.out.print(gameBoard.get(j) + "\t ");
          previousJ = j;
        }
        i = previousJ - 10;
        delay(1);
        System.out.println();
      }

      if (i > 0) {
        System.out.print(gameBoard.get(i) + "\t ");
      }
    }

  }

  public static void delay(int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);

    } catch (InterruptedException e) {
      System.out.println();
    }
  }

  public static void delayWithBar(int miliseconds) {
    try {

      TimeUnit.MILLISECONDS.sleep(miliseconds);
      System.out.print("   || \\");
      TimeUnit.MILLISECONDS.sleep(miliseconds);
      System.out.print(" \\");
      TimeUnit.MILLISECONDS.sleep(miliseconds);
      System.out.print(" \\");
      TimeUnit.MILLISECONDS.sleep(miliseconds);
      System.out.print(" \\");
      TimeUnit.MILLISECONDS.sleep(miliseconds);
      System.out.print(" \\");
      TimeUnit.MILLISECONDS.sleep(miliseconds);
      System.out.print(" \\   Dice ");
      TimeUnit.MILLISECONDS.sleep(miliseconds);
      System.out.print("Rolling  ");
      TimeUnit.MILLISECONDS.sleep(miliseconds);
      System.out.print(" \\");
      TimeUnit.MILLISECONDS.sleep(miliseconds);
      System.out.print(" \\");
      TimeUnit.MILLISECONDS.sleep(miliseconds);
      System.out.print(" \\");
      TimeUnit.MILLISECONDS.sleep(miliseconds);
      System.out.print(" \\");
      TimeUnit.MILLISECONDS.sleep(miliseconds);
      System.out.print(" \\");
      TimeUnit.MILLISECONDS.sleep(miliseconds);
      System.out.print(" \\");
      TimeUnit.MILLISECONDS.sleep(miliseconds);
      System.out.print(" \\");
      TimeUnit.MILLISECONDS.sleep(miliseconds);
      System.out.println(" \\ ||");
    } catch (InterruptedException e) {
      System.out.println();
    }
  }

  public static void Exit() // display a closing message and end the driver.
  {
    System.out.print(
        "\n================================================================================================================");
    System.out.print("\n\t\t    =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    System.out.print("\n\t\t    =     THANK YOU FOR PLAYING LADDER AND SNAKE JAVA GAME     =");
    System.out.print("\n\t\t    =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    System.out.print(
        "\n================================================================================================================\n");
  }
}
