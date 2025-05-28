import java.util.*;

public class Visualizer {
  // private editable fields
  private static int[] page = { 0, 25 };
  private static int[] info = new int[30000];
  private static int pointerCol = 3;
  private static int pointerRow = 26;
  private static LinkedList<String> inputHistory = new LinkedList<String>();
  private static int maxHistory = 48 * 17;
  private static LinkedList<String> outputHistory = new LinkedList<String>();
  private static String flavorText = "Type q to quit, otherwise enter Brainfuck instructions.";
  private static String resetBar = "                                           ";

  public static void main(String[] args) {
    // debugging texts
    // for (int i = page[0]; i < page[1]; i++) {
    // if (i != 4)
    // info[i] += 2;
    // }
    // scanner setup
    Scanner sc = new Scanner(System.in);
    String input = "";
    // main loop
    while (!input.equals("q")) {
      // Initilization
      initialization();
      input = sc.next();
      // Greater than
      if (input.equals(">")) {
        if (pointerCol < 99) {
          pointerCol += 4;
        } else {
          if (page[1] < 30000) {
            page[0] += 1;
            page[1] += 1;
          } else {
            System.out.print("Array index error (needs catching) ");
          }
        }
        appendInput(input);
      }
      // Less than
      if (input.equals("<")) {
        if (pointerCol > 3) {
          pointerCol -= 4;
        } else {
          if (page[0] > 0) {
            page[0] -= 1;
            page[1] -= 1;
          } else {
            System.out.print("Array index error (needs catching) ");
          }
        }
        appendInput(input);
      }
      // period
      if (input.equals(".")) {
        int index = pointerCol / 3 - 1;
        outputHistory.add("" + (char) info[index]);
        if (outputHistory.size() > maxHistory) {
          outputHistory.removeFirst();
        }
        appendInput(input);
      }
      // plus sign
      if (input.equals("+")) {
        int index = (pointerCol - 3) / 4;
        info[index] += 1;
        if (info[index] > 255) {
          info[index] = 0;
        }
        appendInput(input);
      }
      // minus sign
      if (input.equals("-")) {
        int index = (pointerCol - 3) / 4;
        info[index] -= 1;
        if (info[index] < 0) {
          info[index] = 255;
        }
        appendInput(input);
      }
      // comma
      if (input.equals(",")) {
        flavorText = "Awaiting input (Considers only first character)";
        awaitingCursorReset();
        String insert = sc.next();
        int index = (pointerCol - 3) / 4;
        info[index] = (int) insert.charAt(0);
        appendInput(input);
      }
      // brackets
      if (input.equals("[")) {
        appendInput(input);
        int recallPoint = inputHistory.size();
        int index = (pointerCol - 3) / 4;
        leftBracket(index, recallPoint);
      }
    }
    System.out.print("\u001B[33;1;H");
    sc.close();
    System.exit(0);
  }

  public static void initialization() {
    generateTextbox();
    viewArray();
    viewInputs();
    viewOutputs();
    printPointer();
    resetCursor();
  }

  public static void generateTextbox() {
    System.out.print("\033[H\033[2J");
    for (int i = 0; i < 31; i++) {
      for (int j = 0; j < 101; j++) {
        if (j == 0 || i == 0 || i == 30 || j == 100 || i == 21 || (j == 50 && i < 21) || i == 3 || (i == 24)
            || (i > 21 && j % 4 == 0 && i < 24) || (i == 27)) {
          System.out.print("\u001B[44;5;255m");
          System.out.print(" ");
        } else {
          System.out.print("\u001B[39;5;m");
          System.out.print(" ");
        }
      }
      System.out.println();
    }
    // clearing color
    System.out.print("\u001B[39;5;m");
    // Title boxes
    System.out.print("\u001B[2;19;H");
    System.out.print("Input History");
    System.out.print("\u001B[2;70;H");
    System.out.print("Output History");
    // Page number Section
    System.out.print("\u001B[30;80;H");
    System.out.print("Cell Number: ");
    System.out.print("" + page[0] + " - " + page[1]);
  }

  public static void viewArray() {
    int row = 24;
    int col = 2;
    for (int i = page[0]; i < page[1]; i++) {
      System.out.print("\u001B[" + row + ";" + col + ";H");
      System.out.print(info[i]);
      col += 4;
    }
  }

  public static void resetCursor() {
    System.out.print("\u001B[30;2;H");
    System.out.print(flavorText);
    System.out.print("\u001B[29;2;H");
    System.out.print("User Input: ");
  }

  public static void awaitingCursorReset() {
    System.out.print("\u001B[30;2;H");
    System.out.print(flavorText);
    System.out.print(resetBar);
    System.out.print("\u001B[29;2;H");
    System.out.print(resetBar);
    System.out.print("\u001B[29;2;H");
    System.out.print("User Input: ");
  }

  public static void printPointer() {
    System.out.print("\u001B[" + pointerRow + ";" + pointerCol + ";H");
    System.out.print("^");
  }

  public static void viewInputs() {
    int row = 5;
    int col = 3;
    for (int i = 0; i < inputHistory.size(); i++) {
      System.out.print("\u001B[" + row + ";" + col + ";H");
      System.out.print(inputHistory.get(i));
      col += 1;
      if (col > 49) {
        col = 3;
        row += 1;
      }
    }
  }

  public static void viewOutputs() {
    int row = 5;
    int col = 3 + 50;
    for (int i = 0; i < outputHistory.size(); i++) {
      System.out.print("\u001B[" + row + ";" + col + ";H");
      System.out.print(outputHistory.get(i));
      col += 1;
      if (col > 49 + 50) {
        col = 3 + 50;
        row += 1;
      }
    }
  }

  public static void appendInput(String input) {
    inputHistory.add(input);
    if (inputHistory.size() > maxHistory) {
      inputHistory.removeFirst();
    }
  }

  public static boolean validInput(String input) {
    return (input.equals("+") ||
        input.equals("-") ||
        input.equals(",") ||
        input.equals(".") ||
        input.equals(">") ||
        input.equals("<") ||
        input.equals("[") ||
        input.equals("]"));
  }

  public static void leftBracket(int index, int recallPoint) {
    Scanner brackets = new Scanner(System.in);
    String input = "";
    int pointer = index;
    if (info[index] == 0) {
      while (!input.equals("]")) {
        initialization();
        input = brackets.next();
        if (input.equals("[")) {
          appendInput(input);
          leftBracket(pointer, recallPoint);
        } else {
          if (validInput(input)) {
            appendInput(input);
          }
        }
      }
    } else {
      while (!input.equals("]")) {
        initialization();
        input = brackets.next();
        // greater than
        if (input.equals(">")) {
          pointer++;
          if (pointerCol < 99) {
            pointerCol += 4;
          } else {
            if (page[1] < 30000) {
              page[0] += 1;
              page[1] += 1;
            } else {
              System.out.print("Array index error (needs catching)");
            }
          }
          appendInput(input);
        }
        // less than
        if (input.equals("<")) {
          pointer--;
          if (pointerCol > 3) {
            pointerCol -= 4;
          } else {
            if (page[0] > 0) {
              page[0] -= 1;
              page[1] -= 1;
            } else {
              System.out.print("Array index error (needs catching)");
            }
          }
          appendInput(input);
        }
        // period
        if (input.equals(".")) {
          outputHistory.add("" + (char) info[pointer]);
          if (outputHistory.size() > maxHistory) {
            outputHistory.removeFirst();
          }
          appendInput(input);
        }
        // plus
        if (input.equals("+")) {
          info[pointer]++;
          appendInput(input);
        }
        // minus
        if (input.equals("-")) {
          info[pointer]--;
          appendInput(input);
        }
        // comma
        if (input.equals(",")) {
          flavorText = "Awaiting input (Considers only first character)";
          awaitingCursorReset();
          String insert = brackets.next();
          info[pointer] = (int) insert.charAt(0);
          appendInput(input);
        }
        // recycle brackets
        if (input.equals("[")) {
          appendInput(input);
          int instanceRecall = inputHistory.size();
          leftBracket(pointer, instanceRecall);
        }
      }
    }
    if (info[pointer] != 0) {
      appendInput(input);
      // outputHistory.add("" + recallPoint);
      initialization();
      rightBracket(pointer, recallPoint, inputHistory.size());
    }
    initialization();
  }

  public static void rightBracket(int pointer, int start, int terminate) {
    Scanner comma = new Scanner(System.in);
    for (int i = start; i <= terminate; i++) {
      if (i != terminate) {
        if (inputHistory.get(i).equals(">")) {
          pointer++;
          if (pointerCol < 99) {
            pointerCol += 4;
          } else {
            if (page[1] < 30000) {
              page[0] += 1;
              page[1] += 1;
            } else {
              System.out.print("Array index error (needs catching)");
            }
          }
        }
        if (inputHistory.get(i).equals("<")) {
          pointer--;
          if (pointerCol > 3) {
            pointerCol -= 4;
          } else {
            if (page[0] > 0) {
              page[0] -= 1;
              page[1] -= 1;
            } else {
              System.out.print("Array index error (needs catching)");
            }
          }
        }
        if (inputHistory.get(i).equals("+")) {
          info[pointer]++;
        }
        if (inputHistory.get(i).equals("-")) {
          info[pointer]--;
        }
        if (inputHistory.get(i).equals(",")) {
          flavorText = "Awaiting input (Considers only first character)";
          awaitingCursorReset();
          String insert = comma.next();
          info[pointer] = (int) insert.charAt(0);
          initialization();
        }
        if (inputHistory.get(i).equals(".")) {
          outputHistory.add("" + (char) info[pointer]);
          if (outputHistory.size() > maxHistory) {
            outputHistory.removeFirst();
          }
          initialization();
        }
        if (inputHistory.get(i).equals("[")) {
          int instanceEnd = -1;
          int count = 0;
          for (int j = i; j < terminate; j++) {
            if (inputHistory.get(j).equals("[")) {
              count++;
            }
            if (inputHistory.get(j).equals("]")) {
              count--;
            }
            if (count == 0) {
              instanceEnd = j + 1;
              break;
            }
          }
          int instanceStart = i + 1;
          if (info[pointer] == 0){
            i = instanceEnd;
          }
        }
        if (inputHistory.get(i).equals("]")){
          int count = 0;
          int instanceEnd = -1;
          if (info[pointer] != 0){
            for (int j = i; j >= start-1; j--) {
              if (inputHistory.get(j).equals("]")) {
                count++;
              }
              if (inputHistory.get(j).equals("[")) {
                count--;
              }
              if (count == 0) {
                instanceEnd = j + 1;
                break;
              }
            }
            rightBracket(pointer, instanceEnd, i+1);
          }
        }
      }
      if (i == terminate) {
        if (info[pointer] != 0) {
          i = start - 1;
        }
      }
      // initialization();
    }
  }
}
