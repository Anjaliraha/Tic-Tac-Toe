package services;

import java.util.function.UnaryOperator;

public class GameSession {

    public GameSession() {
    }

    private UnaryOperator<Integer> toggle = i -> (i + 1) % 2;

    public Integer switchPlayer(Integer currentPlayerIndex) {

        return toggle.apply(currentPlayerIndex);
    }
}
