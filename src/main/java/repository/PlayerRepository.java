package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    playersList = objectMapper.readValue(file, new TypeReference<>() {});
    return playersList;
  }

  public void SavePlayerToList(Players players) {
    Players players1=playersList.stream().filter(players2 -> players2.getName().equals(players.getName())).findFirst().orElse(null);
    players1.setWinCount(players.getWinCount());

    File file = new File(FILEPATH);
        try {
      objectMapper.writeValue(file, players);
    } catch (IOException e) {
      System.out.println("File does not exist");
    }
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
