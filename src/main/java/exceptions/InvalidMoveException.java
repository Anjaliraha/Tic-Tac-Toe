package exceptions;

public class InvalidMoveException extends RuntimeException {
  public InvalidMoveException() {

    System.out.println("Incorrect Move,postion does not exist");
  }
}
