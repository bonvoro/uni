package lab6;

import java.util.*;

/**
 * Узагальнена реалізація множини {@link Set}, яка використовує звичайний масив
 * для зберігання елементів без дублювання.
 * <p>
 * Початкова місткість — 15 елементів, при заповненні збільшується на 30%.
 *
 * @param <T> тип елементів множини
 */
public class GenericArraySet<T> implements Set<T> {
    private static final int INITIAL_CAPACITY = 15;
    private static final double GROWTH_RATE = 1.3;

    /** Внутрішній масив для елементів */
    private Object[] elements;

    /** Поточна кількість елементів */
    private int size = 0;

    /** Створює порожню множину з початковою місткістю 15. */
    public GenericArraySet() {
        elements = new Object[INITIAL_CAPACITY];
    }

    /**
     * Створює множину з одним початковим елементом.
     *
     * @param element початковий елемент
     * @throws IllegalArgumentException якщо елемент дорівнює {@code null}
     */
    public GenericArraySet(T element) {
        this();
        if (element == null)
            throw new IllegalArgumentException("Елемент не може бути null");
        add(element);
    }

    /**
     * Створює множину на основі колекції.
     *
     * @param collection колекція елементів
     * @throws IllegalArgumentException якщо колекція містить {@code null} або дорівнює {@code null}
     */
    public GenericArraySet(Collection<? extends T> collection) {
        this();
        if (collection == null)
            throw new IllegalArgumentException("Колекція не може бути null");
        for (T e : collection) {
            if (e == null)
                throw new IllegalArgumentException("Колекція містить null-елемент");
            add(e);
        }
    }

    /** Перевіряє, чи потрібно розширити місткість масиву. */
    private void ensureCapacity() {
        if (size >= elements.length) {
            int newCapacity = (int) Math.ceil(elements.length * GROWTH_RATE);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean add(T e) {
        if (e == null)
            throw new IllegalArgumentException("Не можна додати null");
        if (contains(e))
            return false;
        ensureCapacity();
        elements[size++] = e;
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                int numMoved = size - i - 1;
                if (numMoved > 0)
                    System.arraycopy(elements, i + 1, elements, i, numMoved);
                elements[--size] = null;
                return true;
            }
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o))
                return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public int size() {
        return size;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** {@inheritDoc} */
    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    /** {@inheritDoc} */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                return (T) elements[index++];
            }
        };
    }

    /** {@inheritDoc} */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size)
            return (E[]) Arrays.copyOf(elements, size, a.getClass());
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T e : c)
            if (add(e))
                modified = true;
        return modified;
    }

    /** {@inheritDoc} */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(elements[i])) {
                remove(elements[i]);
                i--;
                modified = true;
            }
        }
        return modified;
    }

    /** {@inheritDoc} */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object e : c)
            if (remove(e))
                modified = true;
        return modified;
    }

    /** Повертає рядкове представлення множини. */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GenericArraySet [size=" + size + "]: ");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) sb.append(", ");
        }
        return sb.toString();
    }
}
