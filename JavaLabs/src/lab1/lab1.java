package lab1;

public class lab1 {
    public static void main(String[] args) {
        //  Ініціалізація матриць
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

        //  Обчислення матриці C
        long[][] matrixC = multiplyMatrices(matrixA, matrixB);

        // Виведення матриць
        System.out.println("Matrix A:");
        printMatrix(matrixA);

        System.out.println("Matrix B:");
        printMatrix(matrixB);

        System.out.println("Matrix C:");
        printMatrix(matrixC);

        //  Виведення суми найбільших елементів в рядках матриці з парними номерами
        //  та найменших елементів в рядках матриці з непарними номерами
        System.out.println("The sum of the largest elements in the even-numbered rows of the matrix" +
                "and the smallest elements in the odd-numbered rows of the matrix : " + calculateSum(matrixC));

    }


    public static long[][] multiplyMatrices(long[][] matrixA, long[][] matrixB) {
        //  Валідація матриць
        if (matrixA == null || matrixB == null) {
            throw new IllegalArgumentException("Matrices cannot be null");
        }

        if (matrixA.length == 0 || matrixB.length == 0) {
            throw new IllegalArgumentException("Matrices cannot be empty");
        }

        if (matrixA[0].length != matrixB.length) {
            throw new IllegalArgumentException("Impossible to multiply the matrices. " +
                    "The number of columns of the first matrix (" + matrixA[0].length + ") " +
                    "is not equal to the number of rows of the second matrix (" + matrixB.length + ").");
        }

        //  Ініціалізація змінних
        int rowsNumberFirstMatrix = matrixA.length;
        int columnsNumberSecondMatrix = matrixB[0].length;
        int commonDimension = matrixB.length;

        long[][] result = new long[rowsNumberFirstMatrix][columnsNumberSecondMatrix];

        //  Матричний добуток
        for(int row = 0; row < rowsNumberFirstMatrix; row++) {
            for(int column = 0; column < columnsNumberSecondMatrix; column++) {
                long sum = 0;
                for(int i = 0; i < commonDimension; i++) {
                    sum += matrixA[row][i] * matrixB[column][i];
                }
                result[row][column] = sum;
            }
        }

        return result;
    }

    public static long calculateSum(long[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("Matrix cannot be null or empty");
        }

        long sum = 0;
        boolean isOdd;

        //  Обрахування суми найбільших елементів в рядках матриці з парними номерами
        //  та найменших елементів в рядках матриці з непарними номерами
        for(int row = 0; row < matrix.length; row++) {
            isOdd = row % 2 != 0;
            sum += findMinOrMaxInRow(matrix[row], isOdd);
        }
        return sum;
    }


    private static long findMinOrMaxInRow(long[] row, boolean isOdd) {
        if (row == null || row.length == 0) {
            throw new IllegalArgumentException("Row cannot be null or empty");
        }
        long result = row[0];
        if(isOdd) {
            for (long value : row) {
                if (value > result) {
                    result = value;
                }
            }
        }

        if(!isOdd) {
            for (long value : row) {
                if (value < result) {
                    result = value;
                }
            }
        }
        return result;
    }


    public static void printMatrix(long[][] matrix){
        if (matrix == null) {
            System.out.println("Null matrix");
            return;
        }

        for (long[] longs : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(longs[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

