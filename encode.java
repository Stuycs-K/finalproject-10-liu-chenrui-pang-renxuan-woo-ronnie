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
        String result = "";
        for (char c : text.toCharArray()) {
            int asciiValue = (int) c;
            for (int i = 0; i < asciiValue; i++) {
                result += "+";
            }
            result += ".";
            result += ">";
        }
        return result;
    }
}