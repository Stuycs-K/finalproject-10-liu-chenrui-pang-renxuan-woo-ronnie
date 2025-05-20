import java.util.*;
public class Visulizer{
  private static int[] page = {0, 25};
  private static byte[] info = new byte[30000];
  public static void main(String[] args){
    for (int i = page[0]; i < page[1]; i ++){
      info[i]+=1;
    }
    for (int i = page[0]; i < page[1]; i ++){
      System.out.println(info[i]);
    }
    Scanner sc = new Scanner(System.in);
    String input = "";
    while (!input.equals("q")){
      generateTextbox();
      input = sc.next();
      System.out.println(input);
    }
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
    //Pointer
    System.out.print("\u001B[26;3;H");
    System.out.print("^");
    //Title boxes
    System.out.print("\u001B[2;19;H");
    System.out.print("Input History");
    System.out.print("\u001B[2;70;H");
    System.out.print("Output History");
    System.out.print("\u001B[5;2;H");
    //User Input Section
    System.out.print("\u001B[30;80;H");
    System.out.print("Cell Number: ");
    System.out.print("" + page[0] + " - " + page[1]);
    System.out.print("\u001B[29;2;H");
    System.out.print("User Input: ");
  }

  public static void viewArray(){

  }

}
