package entities;

import enums.Symbol;

public class Players {
  String name;
  Symbol symbol;
  Integer winCount;

  public Players() {}

  public Players(String name, Symbol symbol, Integer winCount) {
    this.name = name;
    this.symbol = symbol;
    this.winCount = winCount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Symbol getSymbol() {
    return symbol;
  }

  public void setSymbol(Symbol symbol) {
    this.symbol = symbol;
  }

  public Integer getWinCount() {
    return winCount;
  }

  public void setWinCount(Integer winCount) {
    this.winCount = winCount;
  }
}
