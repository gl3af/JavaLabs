import java.util.*;
public class Task implements IConst {
    public static void main(String[] args) {
        System.out.format("Введите не менее %d чисел, модуль которых не превышает %d.\n",
                MIN_ARGUMENTS_AMOUNT, MAX_ABS_VALUE);
        System.out.print("Последовательность должна заканчиваться '!', разделитель - пробел.\n>");
        Scanner scanner = new Scanner(System.in);
        Worker worker = new Worker(scanner.nextLine());
        try {
            String[] splittedInput = worker.split();
            ArrayList<Integer> numbers = worker.convertToNumbers(splittedInput);
            worker.validateNumbers(numbers);
            int oddNumbersSum = worker.oddNumbersSum(numbers);
            int evenNumbersSum = worker.evenNumbersSum(numbers);
            System.out.println("Сумма четных чисел: " + evenNumbersSum);
            System.out.println("Сумма нечетных чисел: " + oddNumbersSum);
        } catch (MissingSignException | MinSizeException | MaxAbsValueException e) {
            System.out.println(e);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода целых чисел!");
        }
    }
}