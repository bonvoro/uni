package lab4;

import java.util.ArrayList;

/**
 * Клас, який представляє одне речення.
 */
public class Sentence {

    private ArrayList<SentenceElement> elements = new ArrayList<>();

    /**
     * Створює речення та розбиває його на слова й пунктуацію.
     *
     * @param sentenceText текст речення
     */
    public Sentence(String sentenceText) {
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < sentenceText.length(); i++) {
            char c = sentenceText.charAt(i);

            if (Character.isLetter(c)) {
                word.append(c);
            } else {
                if (!word.isEmpty()) {
                    elements.add(new SentenceElement(new Word(word.toString())));
                    word.setLength(0);
                }

                if (".!?,".indexOf(c) != -1) {
                    elements.add(new SentenceElement(new Punctuation(c)));
                }
            }
        }

        if (!word.isEmpty()) {
            elements.add(new SentenceElement(new Word(word.toString())));
        }
    }

    /**
     * Міняє місцями перше і останнє слово в реченні.
     */
    public void swapFirstAndLast() {
        int first = -1, last = -1;

        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).isWord()) {
                if (first == -1) first = i;
                last = i;
            }
        }

        if (first != -1 && last != -1 && first != last) {
            SentenceElement temp = elements.get(first);
            elements.set(first, elements.get(last));
            elements.set(last, temp);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (SentenceElement e : elements) {
            if (e.isWord()) {
                if (!sb.isEmpty()) sb.append(" ");
                sb.append(e.getWord());
            } else {
                sb.append(e.toString());
            }
        }

        return sb.toString();
    }
}
