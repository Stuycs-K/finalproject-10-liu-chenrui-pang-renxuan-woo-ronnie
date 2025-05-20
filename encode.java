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
        }
        if (cmd == '<'){
          pointer--;
        }
        if (cmd == '+'){
          array[pointer]++;
        }
        if (cmd == '-'){
          array[pointer]--;
        }
        if (cmd == '.'){
          System.out.println(array[pointer]);
        }
      }
    }
  }
}

//tests
//make encode ARGS="[->+<]"