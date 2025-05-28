import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;

public class encode {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Program Usage:");
            System.out.println("make encode ARGS='<flag1> <flag2> <arg>'");
            System.out.println("<flag1> = '-p' (plaintext input) or '-f' (file input)");
            System.out.println("<flag2> = '-s' (shorthand output) or '-n' (standard output)");
            System.out.println("<arg> = '<plaintext>' or '<file name>' ");
        }
        else {
            String inputMode = args[0];
            String outputMode = args[1];
            String input = args[2];

            String plaintext = null;
            int[] byteValues = null;

            if(inputMode.equals("-f")){

                if(Files.exists(Paths.get(input))){
                  File output = new File("output.txt");

                    try{
                        byte[] fileBytes = Files.readAllBytes(Paths.get(input));
                        byteValues = new int[fileBytes.length];
                        for (int i = 0; i < fileBytes.length; i++){
                            byteValues[i] = fileBytes[i] & 0xFF;
                        }

                    }
                    catch (IOException e){
                        System.out.println("Error reading file: " + input);
                        System.out.println(e.getMessage());
                        return;
                    }

                    if(outputMode.equals("-s")){
                      try{
                        FileWriter outputWriter = new FileWriter("output.txt");
                        outputWriter.write(shorthand(byteValues));
                        outputWriter.close();
                      }
                      catch (IOException e){
                        System.out.println("Error with writing file");
                        e.printStackTrace();
                      }
                    }
                    else if(outputMode.equals("-n")){
                      try{
                        FileWriter outputWriter = new FileWriter("output.txt");
                        outputWriter.write(converter(byteValues));
                        outputWriter.close();
                      }
                      catch (IOException e){
                        System.out.println("Error with writing file");
                        e.printStackTrace();
                      }
                    }
                    else{
                        System.out.println("Invalid <flag2>");
                        return;
                    }

                }

                else{
                    System.out.println("Error: File not found.");
                    return;
                }

            }

            else if (inputMode.equals("-p")){
                plaintext = input;

                if(outputMode.equals("-s")){
                    System.out.println(shorthand(plaintext));
                 }
                else if(outputMode.equals("-n")){
                    System.out.println(converter(plaintext));
                }
                else{
                    System.out.println("Invalid <flag2>");
                    return;
                }

            }

            else{
                System.out.println("Invalid <flag1>");
                return;
            }

        }
    }

    public static String converter(int[] byteValues){
        String result = "++++++++++[";

        for (int byteValue : byteValues) {
            result += ">";
            for (int i = 0; i < byteValue / 10; i++) {
                result += "+";
            }
        }

        for (int i = 0; i < byteValues.length; i++) {
            result += "<";
        }
        result += "-]";

        for (int byteValue : byteValues) {
            int remainder = byteValue % 10;

            result += ">";
            for (int i = 0; i < remainder; i++) {
                result += "+";
            }
            result += ".";
        }

        return result;
    }

    public static String shorthand(int[] byteValues){
        String result = "10+[";

        for (int byteValue : byteValues) {
            result += ">";
            int count = 0;

            for (int i = 0; i < byteValue / 10; i++) {
                count++;
            }

            result += count + "+";
        }

        for (int i = 0; i < byteValues.length; i++) {
            result += "<";
        }
        result += "-]";

        for (int byteValue : byteValues) {
            int remainder = byteValue % 10;
            int count = 0;

            result += ">";

            for (int i = 0; i < remainder; i++) {
                count++;
            }

            result += count + "+";

            result += ".";
        }

        return result;
    }

    public static String converter(String text) {
        String result = "++++++++++[";

        for (char c : text.toCharArray()) {
            int asciiValue = (int) c;
            result += ">";
            for (int i = 0; i < asciiValue / 10; i++) {
                result += "+";
            }
        }

        // go back "<" depending on length of text to cell 0 to decrement.

        for(int i = 0; i < text.length(); i++){
            result += "<";
        }
        result += "-]";

        // increment the remainders of the ascii values and then output.

        for (char c : text.toCharArray()){
            int asciiValue = (int) c;
            int increment = asciiValue % 10;

            result += ">";
            for (int i = 0; i < increment; i++){
                result += "+";
            }
            result += ".";
        }

        return result;
    }

    public static String shorthand(String text){
        String result = "10+[";

        for (char c : text.toCharArray()) {
            int asciiValue = (int) c;
            int count = 0;

            result += ">";

            for (int i = 0; i < asciiValue / 10; i++) {
                count++;
            }

            result += count + "+";
        }

        for(int i = 0; i < text.length(); i++){
            result += "<";
        }

        result += "-]";

        for (char c : text.toCharArray()){
            int asciiValue = (int) c;
            int increment = asciiValue % 10;
            int count = 0;

            result += ">";

            for (int i = 0; i < increment; i++){
                count++;
            }

            result += count + "+";

            result += ".";
        }

        return result;

    }
}

// Note to self:
// "Hi" becomes ++++++++++ [> +++++++ > ++++++++++ << - ] > ++ . > +++++ .
// Use this algorithm

// Can set common denominator to 10 since normal plaintext should go from 32 to 126 and then increment the last digit.

// So the brainfuck will always start with ++++++++++ and then loop depending on the plaintext
