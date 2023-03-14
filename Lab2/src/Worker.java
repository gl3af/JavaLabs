import java.util.ArrayList;

public class Worker implements IConst, IFunctions {
    private final String input;
    public Worker(String input) { this.input = input; }
    public int evenNumbersSum(ArrayList<Integer> list) {
        int sum = 0;
        for (Integer value : list) {
            if (value % 2 == 0) {
                sum += value;
            }
        }
        return sum;
    }
    public int oddNumbersSum(ArrayList<Integer> list) {
        int sum = 0;
        for (Integer value: list) {
            if (value % 2 != 0) {
                sum += value;
            }
        }
        return sum;
    }

    public String[] split() throws MissingSignException {
        if (input.lastIndexOf('!') != input.length() - 1) {
            throw new MissingSignException();
        }

        return input.substring(0, input.length() - 1).split(" ");
    }

    public ArrayList<Integer> convertToNumbers(String[] splittedInput) throws NumberFormatException {
        ArrayList<Integer> numbersList = new ArrayList<>();
        for (String x: splittedInput) {
            numbersList.add(Integer.parseInt(x));
        }
        return numbersList;
    }

    public void validateNumbers(ArrayList<Integer> list) throws MinSizeException, MaxAbsValueException {
        if (list.size() < MIN_ARGUMENTS_AMOUNT) {
            throw new MinSizeException();
        }
        for (Integer value : list) {
            if (Math.abs(value) > MAX_ABS_VALUE) {
                throw new MaxAbsValueException();
            }
        }
    }
}
