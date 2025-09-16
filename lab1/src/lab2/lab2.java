package lab2;

public class lab2 {
    public static StringBuilder processText(StringBuilder text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Text cannot be empty or null.");
        }

        String[] sentences = text.toString().split("(?<=[.!?])\\s*");
        StringBuilder result = new StringBuilder();

        for (String sentence : sentences) {
            StringBuilder sbSentence = new StringBuilder(sentence.trim());

            String[] words = sbSentence.toString().split("\\s+");
            if (words.length > 1) {
                // міняємо перше й останнє слово
                String temp = words[0];
                words[0] = words[words.length - 1];
                words[words.length - 1] = temp;
            }

            // будуємо нове речення через StringBuilder
            StringBuilder newSentence = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                newSentence.append(words[i]);
                if (i < words.length - 1) {
                    newSentence.append(" ");
                }
            }

            // додаємо речення назад до результату
            result.append(newSentence);
            if (!sentence.endsWith(".") && !sentence.endsWith("!") && !sentence.endsWith("?")) {
                result.append(".");
            } else {
                result.append(sentence.charAt(sentence.length() - 1));
            }
            result.append(" ");
        }

        return result;
    }

    public static void main(String[] args) {
        try {
            StringBuilder inputText = new StringBuilder(
                    "Java is great. I love programming! This task is interesting?"
            );

            StringBuilder outputText = processText(inputText);

            System.out.println("Input text: " + inputText);
            System.out.println("Processed text: " + outputText);

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e);
        }
    }
}
