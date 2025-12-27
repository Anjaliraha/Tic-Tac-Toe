package exceptions;

public class CellOccupiedException extends RuntimeException {
  public CellOccupiedException() {

    System.out.println("Cell is already occupied,choose some other position");
  }
}
