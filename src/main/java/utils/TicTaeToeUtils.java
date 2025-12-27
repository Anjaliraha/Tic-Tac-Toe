package utils;

import entities.Board;
import enums.Symbol;
import exceptions.CellOccupiedException;
import exceptions.InvalidMoveException;

public class TicTaeToeUtils {

  public static Boolean IsValidMove(Board board, int row, int col) {

    Symbol[][] grid = board.getBoard();
    if (row < 0 || row >= 3 || col < 0 || col >= 3) {
      throw new InvalidMoveException();
    }

    if (grid[row][col] != Symbol.EMPTY) {
      throw new CellOccupiedException();
    }
    return Boolean.TRUE;
  }
}
