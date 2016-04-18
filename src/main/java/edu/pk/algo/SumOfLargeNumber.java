package edu.pk.algo;

/**
 * Sum of array of Large number, without using BigInteger or BigDecimal
 */
public class SumOfLargeNumber {

    static String sumOfLargeNumbers(String[] numbers) {
        String sum = null;
        for (String s : numbers) {
            sum = add(sum, s);
        }
        return trim(sum);
    }

    private static String trim(String s) {
        int i = 0;
        while (i < s.length() && s.charAt(i) == 48) {
            i++;
        }
        return s.substring(i);
    }

    private static String add(String sum, String s) {
        StringBuilder res = new StringBuilder();
        if (sum == null)
            return s;
        int i = sum.length() - 1;
        int j = s.length() - 1;
        int car = 0;
        while (i >= 0 && j >= 0) {
            int sumch = sum.charAt(i) - 48;
            int sch = s.charAt(j) - 48;
            int t = sch + sumch + car;
            car = t / 10;
            res.insert(0, t % 10);
            i--;
            j--;
        }
        addRemaining(i, sum, car, res);
        addRemaining(j, s, car, res);
        if (car > 0)
            res.insert(0, car);
        return res.toString();
    }

    private static void addRemaining(int index, String s, int car, StringBuilder res) {
        while (index >= 0) {
            int t = s.charAt(index) - 48;
            t += car;
            car = t / 10;
            res.insert(0, t % 10);
            index--;
        }
    }

    public static void main(String[] args) {
        String sum = sumOfLargeNumbers(new String[]{"9999999999999", "999999999999999999999999"});
        System.out.println(sum);
    }

}