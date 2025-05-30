import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class decode{
  public static void main(String[] args){
    if (args.length != 2){
      System.out.println("Program Usage:");
      System.out.println("make decode ARGS='<mode> <arg>''");
      System.out.println("<mode> = '-c' (ciphertext mode) or '-f' (file mode)");
      System.out.println("<arg> = '<plaintext>' or '<file name>' ");
    }
    else{
      
      char[] array = new char[30000];
      int pointer = 0;
      //filemode
      if (args[0].equals("-f")){
        String filename = args[1];
        try{
          File inputFile = new File(filename);
          FileWriter fw = new FileWriter("output.txt", true);
          Scanner s = new Scanner(inputFile);
          String input = s.nextLine();
          execute(input, array, pointer, fw);
          fw.close();
          s.close();
        }catch (IOException e){
          System.out.println("File doesn't exist.");
        }
      }
      if (args[0].equals("-c")){
        String input = args[1];
        execute(input, array, pointer);
      }
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


  public static int execute(String input, char[] array, int pointer, FileWriter fw){
    for (int i = 0; i < input.length(); i++){
      char cmd = input.charAt(i);
      if (cmd == '>'){
        pointer++;
        if (pointer > array.length){
          try{
            fw.write("RANGE ERROR");
          }
          catch (IOException e){
            System.out.println("File doesn't exist.");
          }
          return -1;
        }
      }
      if (cmd == '<'){
        pointer--;
        if (pointer < 0){
          try{
            fw.write("RANGE ERROR");
          }
          catch (IOException e){
            System.out.println("File doesn't exist.");
          }
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
        try{
          fw.write(array[pointer]);
        }
        catch (IOException e){
          System.out.println("File doesn't exist.");
        }
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
//++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.
//make decode ARGS="-c ++++++++[\>++++[\>++\>+++\>+++\>+\<\<\<\<-]\>+\>+\>-\>\>+[\<]\<-]\>\>.\>---.+++++++..+++.\>\>.\<-.\<.+++.------.--------.\>\>+.\>++."
//output: "Hello World!"