import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class decode{
  public static void main(String[] args){
    if (args.length == 0){
      System.out.println("Format: make decode ARGS=\'(insert BF code)\'");
    }
    else{
      String input = args[1];
      char[] array = new char[30000];
      int pointer = 0;
      //filemode
      if (args[0].equals("-f")){
        try(FileWriter fw = new FileWriter("myfile.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw))
        {
          execute(pw, array, pointer);
        } catch (IOException e) {
          System.out.println("File doesn't exist.");
        }
      }
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
          return -1;
        }
      }
      if (cmd == '<'){
        pointer--;
        if (pointer < 0){
          System.out.println("RANGE ERROR");
          return -1;
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
          if (pointer == -1){
            return -1;
          }
        }
        i += subInput.length() + 1;
      }
    }
    return pointer;
  }
}

public static int execute(PrintWriter pw, char[] array, int pointer){
  for (int i = 0; i < input.length(); i++){
    char cmd = input.charAt(i);
    if (cmd == '>'){
      pointer++;
      if (pointer > array.length){
        pw.println("RANGE ERROR");
        return -1;
      }
    }
    if (cmd == '<'){
      pointer--;
      if (pointer < 0){
        pw.println("RANGE ERROR");
        return -1;
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
      pw.print(array[pointer]);
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
        if (pointer == -1){
          return -1;
        }
      }
      i += subInput.length() + 1;
    }
  }
  return pointer;
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
//output: "Hello World!"