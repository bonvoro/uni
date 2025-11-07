package lab3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Демонструє сортування та пошук у масиві об’єктів Clothing.
 * Показує, як можна сортувати за кількома полями та шукати однаковий об’єкт.
 */
public class Main {

    /**
     * Головний метод програми.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {

        // Створюємо масив одягу
        Clothing[] clothingArray = {
                new Clothing("Pants", "L", "White", "Cotton", 499.99),
                new Clothing("Shirt", "M", "Blue", "Cotton", 299.99),
                new Clothing("Pants", "S", "Black", "Wool", 599.99),
                new Clothing("Dress", "M", "Red", "Silk", 799.99),
                new Clothing("Shirt", "L", "White", "Cotton", 349.99),
                new Clothing("Pants", "M", "Blue", "Denim", 449.99),
                new Clothing("Jacket", "L", "Black", "Leather", 1299.99)
        };

        System.out.println("Початковий масив:");
        for (Clothing item : clothingArray) {
            System.out.println(item);
        }
        System.out.println();

        // Сортування:
        // 1) За типом (зростання)
        // 2) За ціною (спадання)
        Arrays.sort(clothingArray,
                Comparator.comparing(Clothing::getType)
                        .thenComparing(Comparator.comparing(Clothing::getPrice).reversed())
        );

        System.out.println("Відсортований масив (тип за зростанням, потім ціна за спаданням):");
        for (Clothing item : clothingArray) {
            System.out.println(item);
        }
        System.out.println();

        // Об’єкт для пошуку
        Clothing searchItem =
                new Clothing("Pants", "L", "White", "Cotton", 499.99);

        System.out.println("Шукаємо об’єкт:");
        System.out.println(searchItem);
        System.out.println();

        // Лінійний пошук повністю однакового об’єкта
        int foundIndex = -1;
        for (int i = 0; i < clothingArray.length; i++) {
            if (searchItem.equals(clothingArray[i])) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex != -1) {
            System.out.println("Знайдено на індексі: " + foundIndex);
            System.out.println("Елемент: " + clothingArray[foundIndex]);
        } else {
            System.out.println("Елемент не знайдено.");
        }
    }
}
