import java.util.*;
import java.util.stream.IntStream;

public class Calculator {
    public static Integer convertRomToArab(String number) {
        Map<String, Integer> romToArab= new HashMap<>();
        romToArab.put("I", 1);
        romToArab.put("II", 2);
        romToArab.put("III", 3);
        romToArab.put("IV", 4);
        romToArab.put("V", 5);
        romToArab.put("VI", 6);
        romToArab.put("VII", 7);
        romToArab.put("VIII", 8);
        romToArab.put("IX", 9);
        romToArab.put("X", 10);

        return (romToArab.get(number));
    }

    public static StringBuilder convertArabToRom(int number) throws ArithmeticException{
        Map<Integer, String> romToArab= new LinkedHashMap<>();
        romToArab.put(100, "C");
        romToArab.put(90, "XC");
        romToArab.put(50, "L");
        romToArab.put(40, "XL");
        romToArab.put(30, "XXX");
        romToArab.put(10, "X");
        romToArab.put(9, "IX");
        romToArab.put(8, "VIII");
        romToArab.put(7, "VII");
        romToArab.put(6, "VI");
        romToArab.put(5, "V");
        romToArab.put(4, "IV");
        romToArab.put(3, "III");
        romToArab.put(2, "II");
        romToArab.put(1, "I");

        StringBuilder romNumber = new StringBuilder();
        for (Map.Entry<Integer, String> entry : romToArab.entrySet()) {
            while (number >= entry.getKey()) {
                number -= entry.getKey();
                romNumber.append(entry.getValue());
            }
        }
        if (romNumber.length() == 0) {
            throw new ArithmeticException("Roman number cannot be less or equal to zero.");
        }
        return romNumber;
    }

    public static Integer calculate(int num1, Character sign, int num2) throws ArithmeticException{
        return switch (sign) {
            case '+' -> (num1 + num2);
            case '-' -> (num1 - num2);
            case '*' -> (num1 * num2);
            case '/' -> (num1 / num2);
            default -> throw new ArithmeticException("Unknown operation sign.");
        };
    }
    public static void main(String[] args) throws Exception {
        String[] romanNumeric = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int [] arabNumeric = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        System.out.print("Input arithmetical expression: ");
        Scanner in = new Scanner(System.in);
        String strExpression = in.nextLine();
        String[] arrExpression = strExpression.split(" ");
        if (arrExpression.length != 3) throw new ArrayIndexOutOfBoundsException("Incorrect expression.");

        try { // Проверка на допустимость чисел (1-10)
            if ((IntStream.of(arabNumeric).anyMatch(x -> x == Integer.parseInt(arrExpression[0]))) &
                    (IntStream.of(arabNumeric).anyMatch(x -> x == Integer.parseInt(arrExpression[2])))) {
                System.out.println(calculate(Integer.parseInt(arrExpression[0]), arrExpression[1].charAt(0), Integer.parseInt(arrExpression[2])));
            }
            else throw new Exception("Acceptable numbers: 1 - 10.");
        } catch (NumberFormatException e) { // Проверка на ввод римских чисел, и, допустимы ли они
            if ((Arrays.stream(romanNumeric).anyMatch(arrExpression[0]::equals)) &
                    (Arrays.stream(romanNumeric).anyMatch(arrExpression[2]::equals))) {
                System.out.println(convertArabToRom(calculate(convertRomToArab(arrExpression[0]), arrExpression[1].charAt(0),
                        convertRomToArab(arrExpression[2]))));
            }
            else throw new NumberFormatException("Incorrect expression.");
        }
    }
}