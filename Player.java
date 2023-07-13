package SnakeLadder;
//-------------------------------
//Assignment: Assignment 2  - 10%
//Written by: Karina de Vargas Pereira & Sophie Hsu
//JAC ID: 2300594 & 0754336
//------------------------------- 

public class Player {
  private String playerName;
  private int playerTurn;
  private int diceValue;
  private int currentPosition = 0;
  public boolean hasDuplicatedDiceValue = false;

  public Player() {
  }

  public Player(String playerName) {
    this.playerName = playerName;
  }

  public int getPlayerTurn() {
    return playerTurn;
  }

  public void setPlayerTurn(int playerTurn) {
    this.playerTurn = playerTurn;
  }

  public String getPlayerName() {
    return playerName;
  }

  public int getDiceValue() {
    return diceValue;
  }

  public void setDiceValue(int diceValue) {
    this.diceValue = diceValue;
  }

  public int getCurrentPosition() {
    return currentPosition;
  }

  public void setCurrentPosition(int currentPosition) {
    this.currentPosition = currentPosition;
  }

}
