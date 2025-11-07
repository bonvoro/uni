package lab1;

/**
 * Головний клас лабораторної роботи №1.
 * Демонструє операції над матрицями: множення та
 * обчислення спеціальної суми елементів.
 */
public class Main {

    /**
     * Конструктор за замовчуванням.
     * Викликається автоматично перед виконанням основного коду.
     */
    public Main() {
        // порожній конструктор
    }

    /**
     * Головний метод програми.
     * Створює дві матриці, перемножує їх
     * та виводить спеціальну суму елементів.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        long[][] matrixA = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        long[][] matrixB = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        long[][] matrixC = multiplyMatrices(matrixA, matrixB);

        System.out.println("Матриця A:");
        printMatrix(matrixA);

        System.out.println("Матриця B:");
        printMatrix(matrixB);

        System.out.println("Матриця C (A * B):");
        printMatrix(matrixC);

        long result = calculateSpecialSum(matrixC);

        System.out.println(
                "Сума найбільших елементів у парних рядках і найменших у непарних: "
                        + result
        );
    }

    /**
     * Перемножує дві матриці типу long.
     *
     * @param matrixA перша матриця
     * @param matrixB друга матриця
     * @return результат множення матриць
     * @throws IllegalArgumentException якщо вхідні матриці некоректні
     */
    public static long[][] multiplyMatrices(long[][] matrixA, long[][] matrixB) {
        if (matrixA == null || matrixB == null) {
            throw new IllegalArgumentException("Матриці не можуть бути null.");
        }
        if (matrixA.length == 0 || matrixB.length == 0) {
            throw new IllegalArgumentException("Матриці не можуть бути порожні.");
        }
        if (matrixA[0].length != matrixB.length) {
            throw new IllegalArgumentException(
                    "Множення неможливе: кількість стовпців A (" +
                            matrixA[0].length + ") != кількості рядків B (" + matrixB.length + ")."
            );
        }

        int rowsA = matrixA.length;
        int colsB = matrixB[0].length;
        int common = matrixA[0].length;

        long[][] result = new long[rowsA][colsB];

        for (int row = 0; row < rowsA; row++) {
            for (int col = 0; col < colsB; col++) {
                long sum = 0;
                for (int i = 0; i < common; i++) {
                    sum += matrixA[row][i] * matrixB[i][col];
                }
                result[row][col] = sum;
            }
        }

        return result;
    }

    /**
     * Рахує спеціальну суму:
     * <ul>
     *   <li>у непарних рядках шукається мінімум;</li>
     *   <li>у парних рядках шукається максимум.</li>
     * </ul>
     *
     * @param matrix матриця, для якої рахуємо суму
     * @return значення спеціальної суми
     */
    public static long calculateSpecialSum(long[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("Матриця не може бути null або порожньою.");
        }

        long sum = 0;

        for (int row = 0; row < matrix.length; row++) {
            boolean findMax = (row % 2 == 1); // парний індекс → беремо максимум
            sum += findMinOrMax(matrix[row], findMax);
        }
        return sum;
    }

    /**
     * Повертає мінімум або максимум у рядку.
     *
     * @param row     масив значень
     * @param findMax якщо true — шукаємо максимум, інакше — мінімум
     * @return знайдене значення
     */
    private static long findMinOrMax(long[] row, boolean findMax) {
        if (row == null || row.length == 0) {
            throw new IllegalArgumentException("Рядок не може бути null або порожнім.");
        }

        long result = row[0];

        for (long value : row) {
            if (findMax && value > result) {
                result = value;
            }
            if (!findMax && value < result) {
                result = value;
            }
        }

        return result;
    }

    /**
     * Виводить матрицю у консоль.
     *
     * @param matrix матриця, яку треба надрукувати
     */
    public static void printMatrix(long[][] matrix) {
        if (matrix == null) {
            System.out.println("Матриця відсутня (null).");
            return;
        }

        for (long[] row : matrix) {
            for (long value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
