package lab2;

public class lab2 {
    public static StringBuilder processText(StringBuilder text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be empty or null.");
        }

        if (!text.toString().matches("[a-zA-Z\\s.!?]+")) {
            throw new IllegalArgumentException("Input must contain only letters, spaces, and .!? punctuation.");
        }

        if (text.charAt(text.length() - 1) != '.' && text.charAt(text.length() - 1) != '!' && text.charAt(text.length() - 1) != '?') {
            text.append(".");
        }

        StringBuilder result = new StringBuilder();
        int start = 0;

        while (start < text.length()) {
            int end = findSentenceEnd(text, start);
            if (end == -1) end = text.length() - 1;

            StringBuilder sentence = new StringBuilder(text.substring(start, end + 1).trim());

            swapFirstAndLastWord(sentence);

            if (!result.isEmpty()) {
                result.append(" ");
            }
            result.append(sentence);

            start = end + 1;
        }

        return result;
    }

    private static int findSentenceEnd(StringBuilder text, int start) {
        for (int i = start; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '.' || c == '!' || c == '?') {
                return i;
            }
        }
        return -1;
    }

    private static void swapFirstAndLastWord(StringBuilder sentence) {
        int firstSpace = sentence.indexOf(" ");
        int lastSpace = sentence.lastIndexOf(" ");

        // Якщо немає пробілів (одне слово) або тільки один пробіл (два слова)
        if (firstSpace == -1) {
            return; // одне слово - нічого міняти
        }

        char punctuation = sentence.charAt(sentence.length() - 1);

        StringBuilder firstWord = new StringBuilder(sentence.substring(0, firstSpace));
        StringBuilder lastWord;
        StringBuilder newSentence = new StringBuilder();
        if (firstSpace == lastSpace) {
            // Два слова - особливий випадок
            lastWord = new StringBuilder(sentence.substring(firstSpace + 1, sentence.length() - 1));

            newSentence.append(lastWord).append(" ").append(firstWord).append(punctuation);

        } else {
            // Три й більше слів
            lastWord = new StringBuilder(
                    sentence.substring(lastSpace + 1, sentence.length() - 1)
            );

            newSentence.append(lastWord).append(sentence, firstSpace, lastSpace + 1).append(firstWord).append(punctuation);

        }
        sentence.setLength(0);
        sentence.append(newSentence);
    }

    public static void main(String[] args) {
        try {
            StringBuilder[] testCases = {
                    new StringBuilder("Java."),
                    new StringBuilder("Hello World!"),
                    new StringBuilder("Java is great."),
                    new StringBuilder("Is KPI the best university?")
            };

            for (StringBuilder inputText : testCases) {
                StringBuilder outputText = processText(inputText);

                System.out.println("Input text: " + inputText);
                System.out.println("Processed text: " + outputText);
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e);
        }
    }
}
