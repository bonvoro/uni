package lab2;

/**
 * Проста програма для обробки тексту, де в кожному реченні
 * міняються місцями перше й останнє слово.
 */
public class Main {

    /**
     * Головний метод, де запускаємо тестові приклади.
     */
    public static void main(String[] args) {
        try {
            StringBuilder[] testCases = {
                    new StringBuilder("Java."),
                    new StringBuilder("Hello world!"),
                    new StringBuilder("Java is great."),
                    new StringBuilder("Is KPI the best university?"),
                    new StringBuilder("  Multiple   spaces   here."),
                    new StringBuilder("OneWord!")
            };

            for (StringBuilder input : testCases) {
                System.out.println("Input text:     " + input);
                StringBuilder output = processText(new StringBuilder(input));
                System.out.println("Processed text: " + output);
                System.out.println();
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Помилка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Неочікувана помилка: " + e);
        }
    }

    /**
     * Метод обробляє текст: у кожному реченні міняє місцями
     * перше й останнє слово.
     *
     * @param text текст, який потрібно обробити
     * @return змінений текст
     */
    public static StringBuilder processText(StringBuilder text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Текст не може бути порожнім.");
        }

        ensureEndsWithPunctuation(text);

        StringBuilder result = new StringBuilder();
        int start = 0;

        while (start < text.length()) {
            int end = findSentenceEnd(text, start);
            if (end == -1) {
                end = text.length() - 1;
            }

            StringBuilder sentence = new StringBuilder();
            sentence.append(text, start, end + 1);
            trimBuilder(sentence);

            swapFirstAndLastWord(sentence);

            if (!result.isEmpty()) {
                result.append(' ');
            }
            result.append(sentence);

            start = end + 1;
        }

        return result;
    }

    /**
     * Перевіряє, чи закінчується текст розділовим знаком.
     * Якщо ні — додає крапку.
     */
    private static void ensureEndsWithPunctuation(StringBuilder text) {
        char last = text.charAt(text.length() - 1);
        if (last != '.' && last != '!' && last != '?') {
            text.append('.');
        }
    }

    /**
     * Шукає кінець речення — позицію розділового знака.
     */
    private static int findSentenceEnd(StringBuilder text, int start) {
        for (int i = start; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '.' || c == '!' || c == '?') {
                return i;
            }
        }
        return -1;
    }

    /**
     * Прибирає пробіли на початку та в кінці тексту.
     */
    private static void trimBuilder(StringBuilder sb) {
        while (!sb.isEmpty() && sb.charAt(0) == ' ') {
            sb.deleteCharAt(0);
        }
        while (!sb.isEmpty() && sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * Міняє місцями перше й останнє слово в реченні.
     */
    private static void swapFirstAndLastWord(StringBuilder sentence) {
        int firstSpace = findFirstSpace(sentence);
        int lastSpace = findLastSpace(sentence);

        if (firstSpace == -1) {
            return; // тільки одне слово
        }

        char punctuation = sentence.charAt(sentence.length() - 1);

        int lastWordStart = lastSpace + 1;
        int lastWordEnd = sentence.length() - 1;

        StringBuilder first = new StringBuilder();
        StringBuilder last = new StringBuilder();

        first.append(sentence, 0, firstSpace);
        last.append(sentence, lastWordStart, lastWordEnd);

        StringBuilder middle = new StringBuilder();
        if (firstSpace != lastSpace) {
            middle.append(sentence, firstSpace, lastSpace + 1);
        } else {
            middle.append(" ");
        }

        sentence.setLength(0);
        sentence.append(last).append(middle).append(first).append(punctuation);
    }

    /**
     * Повертає позицію першого пробілу.
     */
    private static int findFirstSpace(StringBuilder sb) {
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ' ') return i;
        }
        return -1;
    }

    /**
     * Повертає позицію останнього пробілу.
     */
    private static int findLastSpace(StringBuilder sb) {
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) == ' ') return i;
        }
        return -1;
    }
}
