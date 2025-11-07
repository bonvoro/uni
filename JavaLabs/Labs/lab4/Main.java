package lab4;

/**
 * Головний клас для ЛР4.
 * Тут тестуються різні речення та перевіряється робота алгоритму.
 */
public class Main {
    /**
     * Головний метод програми.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {

        try {
            String[] inputs = {
                    "Java.",
                    "Hello world!",
                    "Java is great.",
                    "Is KPI the best university?",
                    "  Multiple   spaces   here.",
                    "OneWord!"
            };

            for (String input : inputs) {
                Text text = new Text(input);

                for (Sentence s : text.getSentences()) {
                    s.swapFirstAndLast();
                }

                System.out.println("Вхід:  " + input);
                System.out.println("Вихід: " + text);
                System.out.println("-------------------------------------");
            }

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
