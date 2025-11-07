package lab3;

import java.util.Objects;

/**
 * Клас описує елемент одягу.
 * Має тип, розмір, колір, матеріал і ціну.
 */
public class Clothing {

    private final String type;
    private final String size;
    private final String color;
    private final String material;
    private final double price;

    /**
     * Створює новий об’єкт одягу з вказаними параметрами.
     *
     * @param type     тип одягу (наприклад, "Pants")
     * @param size     розмір ("S", "M", "L")
     * @param color    колір
     * @param material матеріал виготовлення
     * @param price    ціна у гривнях
     */
    public Clothing(String type, String size, String color, String material, double price) {
        this.type = type;
        this.size = size;
        this.color = color;
        this.material = material;
        this.price = price;
    }

    /**
     * Повертає тип одягу.
     *
     * @return тип (наприклад, "Pants")
     */
    public String getType() {
        return type;
    }

    /**
     * Повертає розмір одягу.
     *
     * @return розмір ("S", "M", "L" тощо)
     */
    public String getSize() {
        return size;
    }

    /**
     * Повертає колір одягу.
     *
     * @return колір (наприклад, "Blue")
     */
    public String getColor() {
        return color;
    }

    /**
     * Повертає матеріал, з якого виготовлено одяг.
     *
     * @return матеріал (наприклад, "Cotton")
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Повертає ціну одягу у гривнях.
     *
     * @return ціна в гривнях
     */
    public double getPrice() {
        return price;
    }

    /**
     * Повертає рядок з описом об’єкта одягу.
     *
     * @return відформатований опис
     */
    @Override
    public String toString() {
        return String.format(
                "Clothing{type='%s', size='%s', color='%s', material='%s', price=%.2f}",
                type, size, color, material, price
        );
    }

    /**
     * Порівнює два об’єкти Clothing.
     * Вважаються однаковими, якщо всі поля збігаються.
     *
     * @param obj об’єкт для порівняння
     * @return true, якщо об’єкти повністю однакові
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Clothing other)) {
            return false;
        }
        return Double.compare(other.price, price) == 0
                && Objects.equals(type, other.type)
                && Objects.equals(size, other.size)
                && Objects.equals(color, other.color)
                && Objects.equals(material, other.material);
    }

    /**
     * Генерує хеш-код на основі полів.
     * Використовується для коректної роботи у хеш-структурах.
     *
     * @return хеш-код об’єкта
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, size, color, material, price);
    }
}
