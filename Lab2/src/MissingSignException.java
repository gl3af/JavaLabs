public class MissingSignException extends Exception {
    @Override
    public String toString() {
        return "Отсутствует '!' в конце последовательности";
    }
}
