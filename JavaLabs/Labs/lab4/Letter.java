package lab4;

/**
 * Клас, який представляє одну букву.
 */
public class Letter {

    private char value;

    /**
     * Створює об'єкт з певною буквою.
     *
     * @param value символ, який представляє літера
     */
    public Letter(char value) {
        this.value = value;
    }

    /**
     * Повертає значення букви.
     *
     * @return символ букви
     */
    public char getValue() {
        return value;
    }

    /**
     * Змінює значення букви.
     *
     * @param v новий символ
     */
    public void setValue(char v) {
        this.value = v;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
