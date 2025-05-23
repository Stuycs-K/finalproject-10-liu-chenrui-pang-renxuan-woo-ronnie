import java.util.Scanner;

public class encode{
  public static void main(String[] args){
    if (args.length == 0){
      System.out.println("Format: make encode ARGS=\'(insert BF code)\'");
    }
    else{
      String input = args[0];
      char[] array = new char[30000];
      int pointer = 0;
      execute(input, array, pointer);
    }
  }

  public static int execute(String input, char[] array, int pointer){
    for (int i = 0; i < input.length(); i++){
      char cmd = input.charAt(i);
      if (cmd == '>'){
        pointer++;
        if (pointer > array.length){
          System.out.println("RANGE ERROR");
          break;
        }
      }
      if (cmd == '<'){
        pointer--;
        if (pointer < 0){
          System.out.println("RANGE ERROR");
          break;
        }
      }
      if (cmd == '+'){
        array[pointer]++;
        if (array[pointer] == 256){
          array[pointer] = 0;
        }
      }
      if (cmd == '-'){
        array[pointer]--;
        if (array[pointer] == -1){
          array[pointer] = 255;
        }
      }
      if (cmd == '.'){
        System.out.print(array[pointer]);
      }
      if (cmd == ','){
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a character to store in the array: ");
        array[pointer] = s.next().charAt(0);
      }
      if (cmd == '['){
        String subInput = input.substring(i + 1);
        subInput = subInput.substring(0, subInput.indexOf(']'));
        while (array[pointer] != 0){
          pointer = execute(subInput, array, pointer);
        }
        i += subInput.length() + 1;
      }
    }
    System.out.println("");
    return pointer;
  }
}
//tests
//make encode ARGS="+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.+.+.+."
//output: "ABCD"
//make encode ARGS=",."
//output: "A"