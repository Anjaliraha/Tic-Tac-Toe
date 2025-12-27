package services;

import entities.Players;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class GameSession {
    Players players;

    public GameSession(Players players) {
        this.players = players;
    }

    public GameSession() {
    }

    private UnaryOperator<Integer> toggle = i -> (i + 1) % 2;
    Consumer<Players> announcer =
            p -> System.out.println("\n" + p.getName() + " [" + p.getSymbol() + "], it's your turn!");

    public Integer switchPlayer(Integer currentPlayerIndex) {

        announcer.accept(players);
        return toggle.apply(currentPlayerIndex);
    }
}
