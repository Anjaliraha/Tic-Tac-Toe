import entities.Board;
import entities.Players;
import exceptions.CellOccupiedException;
import exceptions.InvalidMoveException;
import java.util.Scanner;
import java.util.function.Consumer;
import repository.PlayerRepository;
import services.GameSession;
import services.MakeMoveService;
import utils.TicTaeToeUtils;

public class Main {
  public static void main(String[] args) {
    Board board = new Board();
    Players currentPlayer;
    PlayerRepository playerRepository = null;
    try {
      playerRepository = new PlayerRepository();
    } catch (Exception e) {
      System.out.println("something went wrong");
    }

    Scanner sc = new Scanner(System.in);
    System.out.print("Who will go first,please provide the name : ");
    assert playerRepository != null;
    String name = sc.next();
    Integer currentPlayerIndex = playerRepository.findPlayer(name);
    currentPlayer = playerRepository.findOrCreatePlayer(name);

    MakeMoveService makeMoveService = new MakeMoveService(board, currentPlayer);
    GameSession gameSession = new GameSession();
    while (!isGameOver(makeMoveService)) {
      currentPlayer = playerRepository.getPlayersList().get(currentPlayerIndex);
      makeMoveService.setCurrentPlayer(currentPlayer);
      Consumer<Players> announcer =
          p -> System.out.println("\n" + p.getName() + " [" + p.getSymbol() + "], it's your turn!");
      announcer.accept(currentPlayer);
      Boolean movePlaced = false;
      while (!movePlaced) {
        board.displayBoard();
        try {
          System.out.println("please " + currentPlayer.getName() + " play your move");
          int row = sc.nextInt();
          int col = sc.nextInt();
          if (TicTaeToeUtils.IsValidMove(board, row, col)) {
            makeMoveService.placeMove(row, col, currentPlayer);
            if (makeMoveService.Winchecker()) {
              board.displayBoard();
              System.out.println("Game Over! We have a winner. " + currentPlayer.getName());
              playerRepository.updateWinCount(currentPlayer);
              return;
            }
            if (makeMoveService.drawCheck()) {
              board.displayBoard();
              System.out.println("It's a draw!");
              return;
            }
            movePlaced = true;
          }
        } catch (CellOccupiedException e) {
          System.out.println(e.getMessage());
        } catch (InvalidMoveException e) {
          System.out.println(e.getMessage());
        } catch (Exception e) {
          System.out.println("Invalid input. Please enter numbers only.");
          sc.next();
        }
      }
      currentPlayerIndex = gameSession.switchPlayer(currentPlayerIndex);
    }
  }

  public static boolean isGameOver(MakeMoveService makeMoveService) {
    return makeMoveService.Winchecker() || makeMoveService.drawCheck();
  }
}
