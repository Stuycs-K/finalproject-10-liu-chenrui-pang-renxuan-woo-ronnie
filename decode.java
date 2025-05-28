import java.util.Scanner;

public class decode{
  public static void main(String[] args){
    if (args.length == 0){
      System.out.println("Format: make decode ARGS=\'(insert BF code)\'");
    }
    else{
      String input = args[0];
      char[] array = new char[30000];
      int pointer = 0;
      execute(input, array, pointer);
      System.out.println("");
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
        //find the index of the corresponding right bracket
        int j = i;
        int nestedLoopCount = 0;
        while (nestedLoopCount >= 0){
          j++;
          if (input.charAt(j) == '['){
            nestedLoopCount++;
          }
          if (input.charAt(j) == ']'){
            nestedLoopCount--;
          }
        }
        String subInput = input.substring(i + 1, j);
        while (array[pointer] != 0){
          pointer = execute(subInput, array, pointer);
        }
        i += subInput.length() + 1;
      }
    }
    return pointer;
  }
}
//Test 1: Pass
//make decode ARGS="+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.+.+.+."
//output: "ABCD"
//Test 2: Pass
//make decode ARGS=",."
//output: "A"
//Test 3 (1D bracket looping): Pass
//make decode ARGS="+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.[-\>+\<]\>."
//output:"AA"
//Test 4 (Nested barcket looping): Pass
//java decode ARGS="++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++."