/*
 * Copyright (c) 2014, NTUU KPI, Computer systems department and/or its affiliates. All rights reserved.
 * NTUU KPI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 */

package ua.kpi.comsys.test2.implementation;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

import ua.kpi.comsys.test2.NumberList;

/**
 * Custom implementation of NumberList interface using circular singly linked list.
 * Each element represents a single digit of a number in decimal notation.
 * The list supports conversion to different number systems and arithmetic operations.
 *
 * @author Бондар Олександр Володимирович, IP-34, № заліковки 4204
 */
public class NumberListImpl implements NumberList {

    /**
     * Node class for circular singly linked list
     */
    private static class Node {
        Byte data;
        Node next;

        Node(Byte data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;      // Head of the circular list
    private int size;       // Number of elements
    private int scale;      // Number system base (2, 3, 8, 10, 16)

    /**
     * Default constructor. Returns empty <tt>NumberListImpl</tt>
     */
    public NumberListImpl() {
        head = null;
        size = 0;
        scale = getDefaultScale();
    }

    /**
     * Constructs new <tt>NumberListImpl</tt> by <b>decimal</b> number
     * from file, defined in string format.
     *
     * @param file - file where number is stored.
     */
    public NumberListImpl(File file) {
        this();
        if (file == null || !file.exists() || !file.canRead()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null && !line.trim().isEmpty()) {
                parseDecimalString(line.trim());
            }
        } catch (IOException e) {
            // List remains empty
        }
    }

    /**
     * Constructs new <tt>NumberListImpl</tt> by <b>decimal</b> number
     * in string notation.
     *
     * @param value - number in string notation.
     */
    public NumberListImpl(String value) {
        this();
        if (value != null && !value.isEmpty()) {
            parseDecimalString(value);
        }
    }

    /**
     * Helper method to parse decimal string and populate digits list
     */
    private void parseDecimalString(String value) {
        if (value == null || value.isEmpty()) {
            return;
        }

        // Check if valid decimal number (no negative, no invalid chars)
        if (value.startsWith("-") || !value.matches("\\d+")) {
            return;
        }

        // Remove leading zeros
        value = value.replaceFirst("^0+(?!$)", "");

        for (char c : value.toCharArray()) {
            add((byte) (c - '0'));
        }
    }

    /**
     * Get default number system scale based on record book number
     */
    private static int getDefaultScale() {
        int mod = getRecordBookNumber() % 5;
        switch (mod) {
            case 0: return 2;  // Binary
            case 1: return 3;  // Ternary
            case 2: return 8;  // Octal
            case 3: return 10; // Decimal
            case 4: return 16; // Hexadecimal
            default: return 10;
        }
    }

    /**
     * Get target scale for changeScale operation
     */
    private static int getTargetScale() {
        int mod = (getRecordBookNumber() + 1) % 5;
        switch (mod) {
            case 0: return 2;  // Binary
            case 1: return 3;  // Ternary
            case 2: return 8;  // Octal
            case 3: return 10; // Decimal
            case 4: return 16; // Hexadecimal
            default: return 10;
        }
    }

    /**
     * Saves the number, stored in the list, into specified file
     * in <b>decimal</b> scale of notation.
     *
     * @param file - file where number has to be stored.
     */
    public void saveList(File file) {
        if (file == null) {
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.print(toDecimalString());
        } catch (IOException e) {
            // Ignore
        }
    }

    /**
     * Returns student's record book number, which has 4 decimal digits.
     *
     * @return student's record book number.
     */
    public static int getRecordBookNumber() {
        return 4204;
    }

    /**
     * Returns new <tt>NumberListImpl</tt> which represents the same number
     * in other scale of notation, defined by personal test assignment.<p>
     *
     * Does not impact the original list.
     *
     * @return <tt>NumberListImpl</tt> in other scale of notation.
     */
    public NumberListImpl changeScale() {
        if (isEmpty()) {
            NumberListImpl result = new NumberListImpl();
            result.scale = getTargetScale();
            return result;
        }

        // Create new list with target scale
        NumberListImpl result = new NumberListImpl(toDecimalString());
        result.scale = getTargetScale();
        return result;
    }

    /**
     * Returns new <tt>NumberListImpl</tt> which represents the result of
     * additional operation, defined by personal test assignment.<p>
     *
     * Does not impact the original list.
     *
     * @param arg - second argument of additional operation
     *
     * @return result of additional operation.
     */
    public NumberListImpl additionalOperation(NumberList arg) {
        // Record book 4204 % 7 = 4
        // Operation: MOD (modulo)

        if (arg == null || arg.isEmpty() || this.isEmpty()) {
            return new NumberListImpl();
        }

        BigInteger a = new BigInteger(this.toDecimalString());
        BigInteger b = new BigInteger(((NumberListImpl) arg).toDecimalString());

        if (b.equals(BigInteger.ZERO)) {
            return new NumberListImpl();
        }

        BigInteger result = a.mod(b);
        return new NumberListImpl(result.toString());
    }

    /**
     * Returns string representation of number, stored in the list
     * in <b>decimal</b> scale of notation.
     *
     * @return string representation in <b>decimal</b> scale.
     */
    public String toDecimalString() {
        if (isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Node current = head;
        for (int i = 0; i < size; i++) {
            sb.append(current.data);
            current = current.next;
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "";
        }

        String decimal = toDecimalString();
        BigInteger num = new BigInteger(decimal);

        String result = num.toString(scale);

        // For hexadecimal, return uppercase
        if (scale == 16) {
            return result.toUpperCase();
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberListImpl that = (NumberListImpl) o;

        if (this.size != that.size) return false;
        if (this.isEmpty() && that.isEmpty()) return true;

        Node thisCurrent = this.head;
        Node thatCurrent = that.head;

        for (int i = 0; i < size; i++) {
            if (!thisCurrent.data.equals(thatCurrent.data)) {
                return false;
            }
            thisCurrent = thisCurrent.next;
            thatCurrent = thatCurrent.next;
        }

        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (isEmpty() || o == null) {
            return false;
        }

        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<Byte> iterator() {
        return new Iterator<Byte>() {
            private Node current = head;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public Byte next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Byte data = current.data;
                current = current.next;
                count++;
                return data;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.data;
            current = current.next;
        }
        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(
                a.getClass().getComponentType(), size);
        }

        Node current = head;
        for (int i = 0; i < size; i++) {
            a[i] = (T) current.data;
            current = current.next;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(Byte e) {
        if (e == null) {
            return false;
        }

        Node newNode = new Node(e);

        if (isEmpty()) {
            head = newNode;
            newNode.next = head; // Point to itself (circular)
        } else {
            // Find the last node
            Node last = head;
            for (int i = 0; i < size - 1; i++) {
                last = last.next;
            }
            last.next = newNode;
            newNode.next = head; // Make it circular
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty() || o == null) {
            return false;
        }

        // Special case: removing head
        if (head.data.equals(o)) {
            if (size == 1) {
                head = null;
            } else {
                // Find last node
                Node last = head;
                for (int i = 0; i < size - 1; i++) {
                    last = last.next;
                }
                head = head.next;
                last.next = head; // Maintain circular property
            }
            size--;
            return true;
        }

        // Search for the element
        Node current = head;
        for (int i = 0; i < size - 1; i++) {
            if (current.next.data.equals(o)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Byte> c) {
        boolean modified = false;
        for (Byte e : c) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Byte> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        for (Byte e : c) {
            add(index++, e);
        }
        return !c.isEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            while (remove(o)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Node current = head;

        for (int i = 0; i < size; ) {
            if (!c.contains(current.data)) {
                Byte toRemove = current.data;
                current = current.next;
                remove(toRemove);
                modified = true;
            } else {
                current = current.next;
                i++;
            }
        }

        return modified;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public Byte get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public Byte set(int index, Byte element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        Byte oldValue = current.data;
        current.data = element;
        return oldValue;
    }

    @Override
    public void add(int index, Byte element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            return;
        }

        Node newNode = new Node(element);

        if (index == 0) {
            if (isEmpty()) {
                head = newNode;
                newNode.next = head;
            } else {
                // Find last node
                Node last = head;
                for (int i = 0; i < size - 1; i++) {
                    last = last.next;
                }
                newNode.next = head;
                head = newNode;
                last.next = head; // Update last to point to new head
            }
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
    }

    @Override
    public Byte remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Byte removedData;

        if (index == 0) {
            removedData = head.data;
            if (size == 1) {
                head = null;
            } else {
                // Find last node
                Node last = head;
                for (int i = 0; i < size - 1; i++) {
                    last = last.next;
                }
                head = head.next;
                last.next = head;
            }
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removedData = current.next.data;
            current.next = current.next.next;
        }

        size--;
        return removedData;
    }

    @Override
    public int indexOf(Object o) {
        if (isEmpty() || o == null) {
            return -1;
        }

        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(o)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (isEmpty() || o == null) {
            return -1;
        }

        int lastIndex = -1;
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(o)) {
                lastIndex = i;
            }
            current = current.next;
        }
        return lastIndex;
    }

    @Override
    public ListIterator<Byte> listIterator() {
        return new NumberListIterator(0);
    }

    @Override
    public ListIterator<Byte> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return new NumberListIterator(index);
    }

    /**
     * ListIterator implementation for circular linked list
     */
    private class NumberListIterator implements ListIterator<Byte> {
        private int currentIndex;

        NumberListIterator(int index) {
            this.currentIndex = index;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public Byte next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return get(currentIndex++);
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        @Override
        public Byte previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            return get(--currentIndex);
        }

        @Override
        public int nextIndex() {
            return currentIndex;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(Byte e) {
            NumberListImpl.this.set(currentIndex - 1, e);
        }

        @Override
        public void add(Byte e) {
            NumberListImpl.this.add(currentIndex++, e);
        }
    }

    @Override
    public List<Byte> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }

        NumberListImpl sublist = new NumberListImpl();
        for (int i = fromIndex; i < toIndex; i++) {
            sublist.add(get(i));
        }
        return sublist;
    }

    @Override
    public boolean swap(int index1, int index2) {
        if (index1 < 0 || index1 >= size || index2 < 0 || index2 >= size) {
            return false;
        }

        if (index1 == index2) {
            return true;
        }

        Byte temp = get(index1);
        set(index1, get(index2));
        set(index2, temp);
        return true;
    }

    @Override
    public void sortAscending() {
        if (size <= 1) {
            return;
        }

        // Convert to array, sort, and rebuild list
        Byte[] array = new Byte[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.data;
            current = current.next;
        }

        Arrays.sort(array);

        current = head;
        for (int i = 0; i < size; i++) {
            current.data = array[i];
            current = current.next;
        }
    }

    @Override
    public void sortDescending() {
        if (size <= 1) {
            return;
        }

        // Convert to array, sort, and rebuild list
        Byte[] array = new Byte[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.data;
            current = current.next;
        }

        Arrays.sort(array, Collections.reverseOrder());

        current = head;
        for (int i = 0; i < size; i++) {
            current.data = array[i];
            current = current.next;
        }
    }

    @Override
    public void shiftLeft() {
        if (size <= 1) {
            return;
        }

        // In circular list, just move head pointer forward
        head = head.next;
    }

    @Override
    public void shiftRight() {
        if (size <= 1) {
            return;
        }

        // Find the node before head (last node)
        Node last = head;
        for (int i = 0; i < size - 1; i++) {
            last = last.next;
        }

        // Move head pointer backward
        head = last;
    }
}
