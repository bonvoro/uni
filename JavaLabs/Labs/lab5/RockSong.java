package lab5;

/**
 * Клас, який представляє рок-композицію.
 * Містить додаткове поле піджанру, наприклад "Progressive", "Hard", "Alternative".
 */
public class RockSong extends MusicComposition {
    private final String sub;

    /**
     * Створює нову рок-композицію.
     *
     * @param t   назва композиції
     * @param a   виконавець
     * @param d   тривалість у секундах
     * @param sub піджанр (не обов'язково, але бажано)
     */
    public RockSong(String t, String a, int d, String sub) {
        super(t, a, d, "Rock");
        this.sub = sub;
    }

    /**
     * Повертає тип композиції з уточненням піджанру.
     *
     * @return тип у форматі "Rock (Progressive)" або просто "Rock"
     */
    @Override
    public String getType() {
        return "Rock (" + sub + ")";
    }
}
