public class encode{
  public static void main(String[] args){
    if (args.length == 0){
      System.out.println("Format: make encode ARGS=\'(insert BF code)\'");
    }
    else{
      String input = args[0];
      char[] array = new char[30000];
      int pointer = 0;
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
      }
      System.out.println("");
    }
  }
}

//tests
//make encode ARGS="+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.+.+.+."
//output = "ABCD"