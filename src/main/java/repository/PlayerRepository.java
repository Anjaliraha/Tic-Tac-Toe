package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import entities.Players;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class PlayerRepository {
    Players players;
    List<Players> playersList;
    public final String FILEPATH = "src/main/java/jsons/Players.json";
    public final ObjectMapper objectMapper = new ObjectMapper();

    public List<Players> getPlayersList() {
        return playersList;
    }

    public PlayerRepository() throws IOException {
        this.playersList = loadPlayers();
    }

    public PlayerRepository(Players players, List<Players> playersList) throws IOException {
        this.players = players;
        this.playersList = loadPlayers();
    }

    public List<Players> loadPlayers() throws IOException {
        File file = new File(FILEPATH);
        playersList = objectMapper.readValue(file, new TypeReference<>() {
        });
        return playersList;
    }

    public void savePlayerToList() {

        File file = new File(FILEPATH);
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(file, this.playersList);
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
    }


    public void updateWinCount(Players players) {
        playersList.stream().filter(players2 -> players2.getName().equals(players.getName())).findFirst().ifPresent(p->p.setWinCount(players.getWinCount()+1));

        savePlayerToList();
    }

    public Integer findPlayer(String name) {
        return IntStream.range(0, playersList.size())
                .filter(i -> playersList.get(i).getName().equals(name))
                .findFirst()
                .orElse(-1);
    }

    public Players findOrCreatePlayer(String name) {
        return playersList.stream()
                .filter(players -> players.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
