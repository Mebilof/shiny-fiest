import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static final ArrayList <Integer> arabicNum = new ArrayList();

    enum RomanNums{
        I, II, III, IV, V, VI, VII, VIII, IX, X;
    }

    public static void main(String[] args) {
        System.out.println(calc("1 + 2"));
    }

    public static String calc(String inputString) {
        String[] nums = inputString.split("\\s");
        if (nums.length != 2) throw new IllegalStateException("Sum of values exception");
        if (isRomanNum(nums[0])) {
            if (isRomanNum(nums[2])) {
                int temp = computation(arabicNum.get(0), nums[1], arabicNum.get(1));
                if (temp>0) return intToRoman(temp);
                else throw new IllegalArgumentException("Roman num <= 0");
            }
        } else
            try {
                int a = Integer.parseInt(nums[0]);
                int b = Integer.parseInt(nums[2]);
                if (a > 0 && b > 0) {
                    return Integer.toString(computation(a, nums[1], b));
                } else throw new IllegalArgumentException();
            } catch (IllegalStateException e) {
                throw new IllegalStateException("Unknown operator");
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Format num exception");
            }
            throw new IllegalStateException("Format num exception");
    }

    private static boolean isRomanNum(String num) {
        RomanNums[] roman = RomanNums.values();
        for (int i=0; i<roman.length; i++) {
            if (num.equals(roman[i].toString())) {
                arabicNum.add(i+1);
                return true;
            }
        }
        return false;
    }

    private static int computation(int a, String b, int c) {
        return switch (b) {
            case "+" -> a + c;
            case "-" -> a - c;
            case "*" -> a * c;
            case "/" -> a / c;
            default -> throw new IllegalStateException();
        };
    }

    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int times = 0;
        String[] romans = new String[] { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        int[] ints = new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100};
        for (int i = ints.length - 1; i >= 0; i--) {
            times = num / ints[i];
            num %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }
}
