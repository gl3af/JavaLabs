public class Task {
    public static void main(String[] args) {
        int evenNumbersSum = 0;
        int oddNumbersSum = 0;
        for (String number: args) {
            if (Integer.parseInt(number) % 2 == 0) {
                evenNumbersSum += Integer.parseInt(number);
            } else {
                oddNumbersSum += Integer.parseInt(number);
            }
        }
        System.out.println("Сумма четных чисел: " + evenNumbersSum);
        System.out.println("Сумма нечетных чисел: " + oddNumbersSum);
    }
}