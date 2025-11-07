package lab5;

/**
 * Клас, який описує поп-композицію.
 * Має додатковий атрибут, що визначає, чи є пісня радіохітом.
 */
public class PopSong extends MusicComposition {
    private final boolean radio;

    /**
     * Створює поп-пісню.
     *
     * @param t     назва
     * @param a     виконавець
     * @param d     тривалість у секундах
     * @param radio {@code true}, якщо пісня є радіохітом
     */
    public PopSong(String t, String a, int d, boolean radio) {
        super(t, a, d, "Pop");
        this.radio = radio;
    }

    /**
     * Повертає тип композиції, враховуючи, чи є вона радіохітом.
     *
     * @return рядок "Pop (Radio Hit)" або просто "Pop"
     */
    @Override
    public String getType() {
        return radio ? "Pop (Radio Hit)" : "Pop";
    }
}
