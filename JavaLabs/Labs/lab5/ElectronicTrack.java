package lab5;

/**
 * Клас, який описує електронну композицію (техно, хаус, EDM тощо).
 * Має додаткове поле {@code bpm} — кількість ударів за хвилину.
 */
public class ElectronicTrack extends MusicComposition {
    private final int bpm;

    /**
     * Створює новий електронний трек.
     *
     * @param t   назва композиції
     * @param a   виконавець
     * @param d   тривалість у секундах
     * @param bpm кількість ударів за хвилину (beats per minute)
     * @throws IllegalArgumentException якщо {@code bpm <= 0}
     */
    public ElectronicTrack(String t, String a, int d, int bpm) {
        super(t, a, d, "Electronic");
        if (bpm <= 0)
            throw new IllegalArgumentException("BPM має бути > 0");
        this.bpm = bpm;
    }

    /**
     * Повертає тип композиції з уточненням швидкості ритму.
     *
     * @return рядок у форматі "Electronic (128 BPM)"
     */
    @Override
    public String getType() {
        return "Electronic (" + bpm + " BPM)";
    }
}
