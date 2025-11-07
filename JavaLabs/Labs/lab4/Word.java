package lab4;

/**
 * Клас, який представляє слово як набір букв.
 */
public class Word {

    private Letter[] letters;

    /**
     * Створює слово з текстового рядка.
     *
     * @param text рядок, з якого створюється слово
     */
    public Word(String text) {
        letters = new Letter[text.length()];
        for (int i = 0; i < text.length(); i++) {
            letters[i] = new Letter(text.charAt(i));
        }
    }

    /**
     * Повертає слово у вигляді рядка.
     *
     * @return текст слова
     */
    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (Letter l : letters) {
            sb.append(l.getValue());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getText();
    }
}
