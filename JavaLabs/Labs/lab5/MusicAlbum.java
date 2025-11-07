package lab5;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Клас, який представляє музичний альбом — масив композицій та операції над ним.
 */
public class MusicAlbum {
    private final String name;
    private final List<MusicComposition> list = new ArrayList<>();

    /**
     * Створює альбом із заданою назвою.
     *
     * @param name назва альбому, не може бути порожньою
     */
    public MusicAlbum(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Назва альбому не може бути порожньою");
        this.name = name;
    }

    /**
     * Додає композицію в альбом.
     *
     * @param c композиція, не може бути null
     */
    public void add(MusicComposition c) {
        if (c == null) throw new IllegalArgumentException("Композиція не може бути null");
        list.add(c);
    }

    /**
     * Повертає загальну тривалість альбому в секундах.
     *
     * @return тривалість у секундах
     */
    public int getTotalDuration() {
        return list.stream().mapToInt(MusicComposition::getDuration).sum();
    }

    /**
     * Форматує загальну тривалість у вигляді "H:MM:SS".
     *
     * @return форматований рядок
     */
    public String getFormattedTotal() {
        int t = getTotalDuration();
        return "%d:%02d:%02d".formatted(t / 3600, (t % 3600) / 60, t % 60);
    }

    /**
     * Сортує композиції за стилем (лексикографічно).
     */
    public void sortByStyle() {
        list.sort(Comparator.comparing(MusicComposition::getStyle));
    }

    /**
     * Повертає список композицій, що мають тривалість в заданому діапазоні [min, max].
     *
     * @param min мінімум у секундах (>= 0)
     * @param max максимум у секундах (>= min)
     * @return список композицій
     * @throws IllegalArgumentException при некоректному діапазоні
     */
    public List<MusicComposition> findByDuration(int min, int max) {
        if (min < 0 || max < 0 || min > max) throw new IllegalArgumentException("Некоректний діапазон");
        return list.stream()
                .filter(c -> c.getDuration() >= min && c.getDuration() <= max)
                .toList();
    }

    /**
     * Друк інформації про альбом на консоль.
     */
    public void print() {
        System.out.println("АЛЬБОМ: " + name);
        System.out.println("Кількість: " + list.size());
        System.out.println("Тривалість: " + getFormattedTotal());
        if (list.isEmpty()) {
            System.out.println("Альбом порожній");
        } else {
            for (int i = 0; i < list.size(); i++)
                System.out.printf("%2d. %s%n", i + 1, list.get(i));
        }
    }

    /**
     * Зберігає альбом у CSV-файл.
     * Формат CSV: type,title,artist,style,duration,extra
     * "extra" — додаткова інформація (наприклад: BPM, інструменталіст, піджанр, isRadio)
     *
     * @param path шлях до файлу (буде створено/перезаписано)
     * @throws IOException при помилці запису
     */
    public void saveToCsv(Path path) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            // Заголовок
            writer.write("type,title,artist,style,duration,extra");
            writer.newLine();
            for (MusicComposition c : list) {
                String type = csvEscape(c.getType());
                String title = csvEscape(c.getTitle());
                String artist = csvEscape(c.getArtist());
                String style = csvEscape(c.getStyle());
                String duration = String.valueOf(c.getDuration());
                String extra = deriveExtraField(c);
                writer.write(String.join(",", type, title, artist, style, duration, extra));
                writer.newLine();
            }
            writer.flush();
        }
    }

    // Допоміжний метод — повертає поле extra для CSV (вид залежно від конкретного типу)
    private String deriveExtraField(MusicComposition c) {
        if (c instanceof ElectronicTrack et) {
            return csvEscape(et.getType()); // або повернути просто bpm
        } else if (c instanceof JazzComposition jz) {
            return csvEscape(jz.getType());
        } else if (c instanceof RockSong rs) {
            return csvEscape(rs.getType());
        } else if (c instanceof PopSong ps) {
            return csvEscape(ps.getType());
        } else {
            return "";
        }
    }

    // Просте екранування полів CSV: замінюємо " на "" та обгортаємо у подвійні лапки
    private String csvEscape(String s) {
        if (s == null) return "\"\"";
        String escaped = s.replace("\"", "\"\"");
        return "\"" + escaped + "\"";
    }
}
