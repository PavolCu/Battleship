
class ArrayOperations {
    public static void reverseElements(int[][] twoDimArray) {
        // write your code here
        for (int[] row : twoDimArray) {
            reverseRow(row);
        }
    }

    private static void reverseRow(int[] row) {
        int length = row.length;

        for (int j = 0; j < length / 2; j++) {
            int temp = row[j];
            row[j] = row[length - 1 - j];
            row[length - 1 - j] = temp;
        }
    }
}


