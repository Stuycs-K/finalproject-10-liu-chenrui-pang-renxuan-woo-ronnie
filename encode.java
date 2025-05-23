import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class encode {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Program Usage:");
            System.out.println("java encode -f <filename>");
            System.out.println("java encode -p '<plaintext>'");
        }
        else {
            String mode = args[0];
            String input = args[1];

            String plaintext = null;
            int[] byteValues = null;

            if(mode.equals("-f")){

                if(Files.exists(Paths.get(input))){

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

                    System.out.println(converter(byteValues));
                }

            else{
                System.out.println("Error: File not found.");
                return;
            }

            }

            else if (mode.equals("-p")){
                plaintext = input;

                System.out.println(converter(plaintext));
            }

            else{
                System.out.println("Unknown flag");
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
}

// Note to self:
// "Hi" becomes ++++++++++ [> +++++++ > ++++++++++ << - ] > ++ . > +++++ .
// Use this algorithm

// Can set common denominator to 10 since normal plaintext should go from 32 to 126 and then increment the last digit.

// So the brainfuck will always start with ++++++++++ and then loop depending on the plaintext
