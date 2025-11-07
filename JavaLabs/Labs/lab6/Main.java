package lab6;

import lab5.*;
import java.util.List;

/**
 * Демонстраційний клас для перевірки роботи {@link GenericArraySet}.
 * <p>
 * Використовує об’єкти {@link lab5.MusicComposition} та її нащадків
 * для тестування операцій множини.
 */
public class Main {
    /**
     * Точка входу в програму. Демонструє використання множини {@link GenericArraySet}
     * з різними конструкторами та методами.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        GenericArraySet<MusicComposition> set1 = new GenericArraySet<>();

        GenericArraySet<MusicComposition> set2 = new GenericArraySet<>(
                new RockSong("Nothing Else Matters", "Metallica", 388, "Ballad")
        );

        List<MusicComposition> list = List.of(
                new PopSong("Blinding Lights", "The Weeknd", 200, true),
                new JazzComposition("Blue in Green", "Miles Davis", 310, "Bill Evans")
        );
        GenericArraySet<MusicComposition> set3 = new GenericArraySet<>(list);

        set1.add(new ElectronicTrack("Ghosts 'n' Stuff", "Deadmau5", 320, 128));
        set1.add(new PopSong("Shape of You", "Ed Sheeran", 233, true));
        set1.add(new PopSong("Shape of You", "Ed Sheeran", 233, true)); // дубль не додається

        System.out.println("=== Набір 1 ===");
        System.out.println(set1);
        System.out.println("Розмір: " + set1.size());

        System.out.println("\n=== Набір 2 ===");
        System.out.println(set2);

        System.out.println("\n=== Набір 3 ===");
        System.out.println(set3);

        System.out.println("\nЧи містить Jazz композицію? " +
                set3.contains(new JazzComposition("Blue in Green", "Miles Davis", 310, "Bill Evans")));
    }
}
