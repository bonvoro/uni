package lab5;

/**
 * Абстрактний клас, який описує музичну композицію.
 * Зберігає назву, виконавця, тривалість і стиль.
 */
public abstract class MusicComposition {
    private final String title;
    private final String artist;
    private final int duration;
    private final String style;

    /**
     * Конструктор композиції.
     *
     * @param title    назва композиції
     * @param artist   виконавець композиції
     * @param duration тривалість у секундах
     * @param style    стиль композиції
     */
    public MusicComposition(String title, String artist, int duration, String style) {
        if (isInvalid(title)) throw new IllegalArgumentException("Назва не може бути порожньою");
        if (isInvalid(artist)) throw new IllegalArgumentException("Виконавець не може бути порожнім");
        if (duration <= 0) throw new IllegalArgumentException("Тривалість має бути > 0");
        if (isInvalid(style)) throw new IllegalArgumentException("Стиль не може бути порожнім");
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.style = style;
    }

    private boolean isInvalid(String s) {
        return s == null || s.trim().isEmpty();
    }

    /**
     * Повертає назву композиції.
     *
     * @return назва композиції
     */
    public String getTitle() {
        return title;
    }

    /**
     * Повертає ім’я виконавця.
     *
     * @return ім'я виконавця
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Повертає тривалість композиції у секундах.
     *
     * @return тривалість у секундах
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Повертає стиль композиції.
     *
     * @return стиль композиції
     */
    public String getStyle() {
        return style;
    }

    /**
     * Форматує тривалість у вигляді М:СС.
     *
     * @return відформатована тривалість
     */
    public String getFormattedDuration() {
        return String.format("%d:%02d", duration / 60, duration % 60);
    }

    /**
     * Повертає тип композиції (наприклад, рок, поп тощо).
     *
     * @return тип композиції
     */
    public abstract String getType();

    @Override
    public String toString() {
        return "%s - %s [%s] (%s) - %s".formatted(
                getType(), title, artist, style, getFormattedDuration()
        );
    }
}
