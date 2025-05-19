import java.util.*;
public class Visulizer{
  public static void main(String[] args){
    byte[] info = new byte[3000];
    int[] page = {1000, 1010};
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
    System.out.print("\u001B[48;5;255m");
    for (int i = 0; i < 100; i ++){
      for (int j = 0; j < 10; j ++){
        if (j == 0 || i == 0){
          System.out.print(" ");
        }
      }
    }
  }
}
