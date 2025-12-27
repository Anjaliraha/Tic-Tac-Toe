import entities.Board;
import entities.Players;
import exceptions.CellOccupiedException;
import exceptions.InvalidMoveException;
import repository.PlayerRepository;
import services.GameSession;
import services.MakeMoveService;
import utils.TicTaeToeUtils;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Players players;
        PlayerRepository playerRepository = null;
        try {
            playerRepository = new PlayerRepository();
        } catch (Exception e) {
            System.out.println("something went wrong");
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Who will go first,please provide the name");
        assert playerRepository != null;
        String name = sc.next();
        Integer currentPlayerIndex = playerRepository.findPlayer(name);
        players = playerRepository.findOrCreatePlayer(name);

        MakeMoveService makeMoveService = new MakeMoveService(board, players);
        GameSession gameSession = new GameSession(players);
        while (!makeMoveService.Winchecker() && !makeMoveService.drawCheck()) {
            Players currentPlayer = playerRepository.getPlayersList().get(currentPlayerIndex);
            Boolean movePlaced = false;
            while (!movePlaced) {
                board.displayBoard();
                try {
                    System.out.println("please " + currentPlayer.getName() + " play your move");
                    int row = sc.nextInt();
                    int col = sc.nextInt();
                    if (TicTaeToeUtils.IsValidMove(board, row, col)) {
                        makeMoveService.placeMove(row, col, currentPlayer);
                        movePlaced=true;
                    }
                } catch (CellOccupiedException e) {
                    System.out.println(e);
                } catch (InvalidMoveException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter numbers only.");
                    sc.next();
                }
                currentPlayerIndex = gameSession.switchPlayer(currentPlayerIndex);
                System.out.println(currentPlayerIndex);
            }
        }
        board.displayBoard();
        if (makeMoveService.Winchecker()) {
            System.out.println("Game Over! We have a winner.");
        } else {
            System.out.println("It's a draw!");
        }
    }
}
