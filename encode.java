public class Decode {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please enter plaintext.");
        }
        else {
            String input = args[0];
            System.out.println(converter(input));
        }
    }

    public static String converter(String text) {
        String result = "++++++++++";
        for (char c : text.toCharArray()) {
            int asciiValue = (int) c;
            for (int i = 0; i < asciiValue / 10; i++) {
                result += "+";
            }
            result += ">";
        }
        // go back "<" depending on length of text to cell 0 to decrement.
        // increment the remainders of the ascii values and then output.
        return result;
    }
}

// Note to self:
// "Hi" becomes ++++++++++ [> +++++++ > ++++++++++ << - ] > ++ . > +++++ .
// Use this algorithm

// Can set common denominator to 10 since normal plaintext should go from 32 to 126 and then increment the last digit.

// So the brainfuck will always start with ++++++++++ and then loop depending on the plaintext
