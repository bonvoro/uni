package lab4;

/**
 * Клас для зберігання пунктуаційного знака.
 */
public class Punctuation {

    private char mark;

    /**
     * Створює пунктуаційний знак.
     *
     * @param mark символ пунктуації
     */
    public Punctuation(char mark) {
        this.mark = mark;
    }

    /**
     * Повертає пунктуаційний символ.
     *
     * @return символ пунктуації
     */
    public char getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return String.valueOf(mark);
    }
}
