import java.util.*;
public class Visulizer{
  //private editable fields
  private static int[] page = {0, 25};
  private static byte[] info = new byte[30000];
  private static int pointerCol = 3;
  private static int pointerRow = 26;
  private static LinkedList<String> inputHistory = new LinkedList();
  private static int maxHistory = 48 * 17;

  public static void main(String[] args){
    //debugging texts
    for (int i = page[0]; i < page[1]; i ++){
      info[i]+=1;
    }
    //scanner setup
    Scanner sc = new Scanner(System.in);
    String input = "";
    //main loop
    while (!input.equals("q")){
      //Initilization
      generateTextbox();
      viewArray();
      viewInputs();
      printPointer();
      resetCursor();
      input = sc.next();
      //Greater than
      if (input.equals(">")){
        if (pointerCol < 99){
          pointerCol += 4;
        }
        else{
          if (page[1] < 30000){
            page[0] += 1;
            page[1] += 1;
          }
          else{
            System.out.print("Array index error (needs catching)");
          }
        }
        inputHistory.add(input);
        if (inputHistory.size() > maxHistory){
          inputHistory.removeFirst();
        }
      }
      //Less than
      if (input.equals("<")){
        if (pointerCol > 3){
          pointerCol -= 4;
        }
        else{
          if (page[0] > 0){
            page[0] -= 1;
            page[1] -= 1;
          }
          else{
            System.out.print("Array index error (needs catching)");
          }
        }
        inputHistory.add(input);
        if (inputHistory.size() > maxHistory){
          inputHistory.removeFirst();
        }
      }
    }
      System.out.print("\u001B[33;1;H");
      sc.close();
      System.exit(0);
  }

  public static void generateTextbox(){
    System.out.print("\033[H\033[2J");
    for (int i = 0; i < 31; i ++){
      for (int j = 0; j < 101; j ++){
        if (j == 0 || i == 0 || i == 30 || j == 100 || i == 21 || (j == 50 && i < 21) || i == 3 || (i == 24) || (i > 21 && j % 4 == 0 && i < 24) || (i == 27)){
          System.out.print("\u001B[44;5;255m");
          System.out.print(" ");
        }
        else {
          System.out.print("\u001B[39;5;m");
          System.out.print(" ");
        }
      }
      System.out.println();
    }
    //clearing color
    System.out.print("\u001B[39;5;m");
    //Title boxes
    System.out.print("\u001B[2;19;H");
    System.out.print("Input History");
    System.out.print("\u001B[2;70;H");
    System.out.print("Output History");
    //Page number Section
    System.out.print("\u001B[30;80;H");
    System.out.print("Cell Number: ");
    System.out.print("" + page[0] + " - " + page[1]);
  }

  public static void viewArray(){
    int row = 24;
    int col = 4;
    for (int i = page[0]; i < page[1]; i ++){
      System.out.print("\u001B[" + row + ";" + col + ";H");
      System.out.print(info[i]);
      col += 4;
    }
  }

  public static void resetCursor(){
    System.out.print("\u001B[30;2;H");
    System.out.print("Type q to quit, otherwise enter Brainfuck instructions.");
    System.out.print("\u001B[29;2;H");
    System.out.print("User Input: ");
  }

  public static void printPointer(){
    System.out.print("\u001B[" + pointerRow + ";" + pointerCol + ";H");
    System.out.print("^");
  }

  public static void viewInputs(){
    int row = 5;
    int col = 3;
    for (int i = 0; i < inputHistory.size(); i ++){
      System.out.print("\u001B[" + row + ";" + col + ";H");
      System.out.print(inputHistory.get(i));
      col += 1;
      if (col > 49){
        col = 3;
        row += 1;
      }
    }
  }
}
