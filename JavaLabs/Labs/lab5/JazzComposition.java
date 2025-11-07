package lab5;

/**
 * Клас, який описує джазову композицію.
 * Має додаткове поле — ім’я інструменталіста або соліста, який виконує партію.
 */
public class JazzComposition extends MusicComposition {
    private final String instr;

    /**
     * Створює джазову композицію з вказаним інструменталістом.
     *
     * @param t     назва композиції
     * @param a     виконавець
     * @param d     тривалість у секундах
     * @param instr ім’я інструменталіста (не може бути порожнім)
     * @throws IllegalArgumentException якщо ім’я інструменталіста некоректне
     */
    public JazzComposition(String t, String a, int d, String instr) {
        super(t, a, d, "Jazz");
        if (isInvalid(instr))
            throw new IllegalArgumentException("Інструменталіст не може бути порожнім");
        this.instr = instr;
    }

    /**
     * Перевіряє, чи є рядок некоректним (null або порожній).
     */
    private boolean isInvalid(String s) {
        return s == null || s.trim().isEmpty();
    }

    /**
     * Повертає тип композиції з зазначенням інструменталіста.
     *
     * @return рядок на кшталт "Jazz (feat. Paul Desmond)"
     */
    @Override
    public String getType() {
        return "Jazz (feat. " + instr + ")";
    }
}
