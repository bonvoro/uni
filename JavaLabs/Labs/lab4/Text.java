package lab4;

import java.util.ArrayList;

/**
 * Клас, що представляє весь текст.
 */
public class Text {

    private ArrayList<Sentence> sentences = new ArrayList<>();

    /**
     * Створює текст і розбиває його на речення.
     *
     * @param rawText початковий текст
     */
    public Text(String rawText) {
        rawText = rawText.replaceAll("[ \t]+", " ");

        String[] parts = rawText.split("(?<=[.!?])");

        for (String s : parts) {
            String trimmed = s.trim();
            if (!trimmed.isEmpty()) {
                sentences.add(new Sentence(trimmed));
            }
        }
    }

    /**
     * Повертає список речень.
     *
     * @return список Sentence
     */
    public ArrayList<Sentence> getSentences() {
        return sentences;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Sentence s : sentences) {
            sb.append(s).append(" ");
        }
        return sb.toString().trim();
    }
}
