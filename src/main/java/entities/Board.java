package entities;

import enums.Symbol;
import java.util.Arrays;

public class Board {
  public Symbol[][] board;

  public Board() {
    this.board = new Symbol[3][3];
    initializeboard();
  }

  public Board(Symbol[][] board) {
    this.board = board;
  }

  public Symbol[][] getBoard() {
    return board;
  }

  private void initializeboard() {
    for (Symbol[] symbols : board) {
      Arrays.fill(symbols, Symbol.EMPTY);
    }
  }

  public void displayBoard() {
    System.out.println("\n    0   1   2 "); // Column headers
    System.out.println("   -----------");
    for (int i = 0; i < 3; i++) {
      System.out.print(i + " |"); // Row header
      for (int j = 0; j < 3; j++) {
        // Assuming board logic stores 'X', 'O', or is empty
        String marker = (board[i][j] == Symbol.EMPTY) ? "" : board[i][j].toString();
        System.out.print(" " + marker + " ");
        if (j < 2) System.out.print("|");
      }
      System.out.println("|");
      if (i < 2) System.out.println("  |---+---+---|");
    }
    System.out.println("   -----------\n");
  }

  public void resetBoard() {
    initializeboard();
  }
}
