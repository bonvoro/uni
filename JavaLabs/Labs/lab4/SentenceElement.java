package lab4;

/**
 * Один елемент речення — або слово, або пунктуація.
 */
public class SentenceElement {

    private Word word;
    private Punctuation punctuation;

    /**
     * Створює елемент зі словом.
     *
     * @param word слово
     */
    public SentenceElement(Word word) {
        this.word = word;
    }

    /**
     * Створює елемент з пунктуаційним знаком.
     *
     * @param punctuation пунктуація
     */
    public SentenceElement(Punctuation punctuation) {
        this.punctuation = punctuation;
    }

    /**
     * Перевіряє, чи елемент є словом.
     *
     * @return true, якщо це слово; false — якщо пунктуація
     */
    public boolean isWord() {
        return word != null;
    }

    /**
     * Повертає слово, якщо елемент — слово.
     *
     * @return об'єкт Word або null
     */
    public Word getWord() {
        return word;
    }

    @Override
    public String toString() {
        if (word != null) return word.toString();
        return punctuation.toString();
    }
}
