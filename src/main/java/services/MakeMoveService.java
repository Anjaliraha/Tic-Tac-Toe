package services;

import entities.Board;
import entities.Players;
import enums.Symbol;
import exceptions.CellOccupiedException;
import exceptions.InvalidMoveException;
import utils.TicTaeToeUtils;

import java.util.Arrays;
import java.util.function.UnaryOperator;

public class MakeMoveService {
    Board board = new Board();
    Players player;

    public MakeMoveService(Board board, Players players) {
        this.board = board;
        this.player = players;
    }

    public MakeMoveService() {
    }

    public void placeMove(int row, int col, Players players)
            throws CellOccupiedException, InvalidMoveException {
        if (TicTaeToeUtils.IsValidMove(board, row, col)) {
            this.board.board[row][col] = players.getSymbol();
        }
    }

    public Boolean Winchecker() {
        Symbol[][] grid = board.getBoard();
        int n = grid.length;
        Boolean winCheck = Boolean.FALSE;
        Symbol symbol = player.getSymbol();

        for (int i = 0; i < n; i++) {
            int rowCount = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == symbol) {
                    rowCount++;
                }
            }
            if (rowCount == n) return Boolean.TRUE;
        }

        for (int i = 0; i < n; i++) {
            int colCount = 0;
            for (int j = 0; j < n; j++) {
                if (grid[j][i] == symbol) {
                    colCount++;
                }
            }
            if (colCount == n) return Boolean.TRUE;
        }
        int diagonalCount = 0;

        for (int i = 0; i < n; i++) {

            if (grid[i][i] == symbol) {
                diagonalCount++;
            }
            if (diagonalCount == n) return Boolean.TRUE;
        }

        int antiDiagonalCount = 0;
        for (int i = 0; i < n; i++) {
            if (grid[n - 1 - i][i] == symbol) {
                antiDiagonalCount++;
            }
            if (antiDiagonalCount == n) return Boolean.TRUE;
        }

        return winCheck;
    }

    public Boolean drawCheck() {
        Symbol[][] grid = board.getBoard();

        return Arrays.stream(grid)
                .flatMap(row -> Arrays.stream(row))
                .noneMatch(value -> value.equals(Symbol.EMPTY));
    }

    public Integer SwitchUser(int currentUserIndex) {
        UnaryOperator<Integer> togglePlayer = index -> (index + 1) % 2;
        // private Consumer<Players> announcer = p -> System.out.println("\n" + p.getName() + " [" +
        // p.getSymbol() + "], it's your turn!");
        return togglePlayer.apply(currentUserIndex);
    }
}
