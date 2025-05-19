public class encode{
  public static void main(String[] args){
    if (args.length == 0){
      System.out.println("Please enter Brainfuck text.");
    }
    if (args.length != 0){
      String input = args[0];
      System.out.println(input);
    }
  }
}
