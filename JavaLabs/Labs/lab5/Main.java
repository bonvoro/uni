package lab5;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Тестовий клас для демонстрації роботи з {@link MusicAlbum}.
 * <p>
 * Усі операції (додавання, сортування, пошук, збереження у CSV)
 * виконуються в захищених блоках {@code try-catch} для безпечного виконання.
 */
public class Main {
    /**
     * Точка входу програми. Демонструє основний сценарій роботи з альбомом.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        MusicAlbum album;
        try {
            album = new MusicAlbum("Greatest Hits");
        } catch (IllegalArgumentException e) {
            System.err.println("Помилка при створенні альбому: " + e.getMessage());
            return;
        }

        try {
            album.add(new RockSong("Bohemian Rhapsody", "Queen", 354, "Progressive"));
            album.add(new PopSong("Shape of You", "Ed Sheeran", 233, true));
            album.add(new JazzComposition("Take Five", "Brubeck", 324, "Paul Desmond"));
            album.add(new ElectronicTrack("Strobe", "Deadmau5", 645, 128));
        } catch (IllegalArgumentException e) {
            System.err.println("Неправильні дані композиції: " + e.getMessage());
        }

        try {
            System.out.println("1. Початковий альбом:");
            album.print();

            System.out.println("2. Після сортування:");
            album.sortByStyle();
            album.print();

            System.out.println("3. Композиції 3–5 хв:");
            album.findByDuration(180, 300).forEach(System.out::println);

            Path out = Path.of("greatest_hits.csv");
            album.saveToCsv(out);
            System.out.println("Альбом збережено у: " + out.toAbsolutePath());
        } catch (IllegalArgumentException e) {
            System.err.println("Помилка виконання: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Помилка запису файлу: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Невідома помилка: " + e.getMessage());
        }
    }
}
