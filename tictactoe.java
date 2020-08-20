import java.util.Scanner;

public class tictactoe {

    static boolean winpattern(char c, char[][] arr) {

        boolean pattern = false;
        int across = 0;
        int down = 0;
        int diagonal = 0;

        if ((arr[0][0] == arr[1][1]) && (arr[1][1] == arr[2][2]) && (arr[1][1] == c)) {
            pattern = true;
            return pattern;
        }

        if ((arr[0][2] == arr[1][1]) && (arr[1][1] == arr[2][0]) && (arr[1][1] == c)) {
            pattern = true;
            return pattern;
        }

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (arr[i][j] == c) {
                    ++across;
                }

                if (arr[j][i] == c) {
                    ++down;
                }

            }

            if (across == 3 || down == 3 || diagonal == 3) {
                pattern = true;
                return pattern;
            }

            across = 0;
            down = 0;
        }
        return pattern;
    }

    static void printArr(char[][] array) {

        String line = "---------";
        String end = "| ";

        System.out.println(line);

        for (char[] arr: array) {

            System.out.print(end);

            for (char c: arr) {
                System.out.print(c + " ");
            }

            System.out.print(end);
            System.out.println();
        }

        System.out.println( line );
        System.out.println();

    }

    static int[] getPoints() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the coordinates: ");
        int x = sc.nextInt();
        int y = sc.nextInt();

        if ((x < 1 || x > 3) || (y < 1 || y > 3)) {

            System.out.println("Coordinates should be from 1 to 3!");
            return getPoints();

        } else {

            int[] result = new int[2];
            result[0] = x;
            result[1] = y;

            return result;

        }

    }

    static char[][] updateArr(char[][] arraycopy, char c){

        int[] values = getPoints();

        int x = values[0];
        int y = values[1];

        int i = 3 - y;
        int j = x - 1;

        if (!(arraycopy[i][j] == 'X' || arraycopy[i][j] == 'O')) {
            arraycopy[i][j] = c;
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            updateArr(arraycopy, c);
        }

        return arraycopy;

    }


    static void play(char[][] matrix) {

        String condition = "";

        for (int i = 0; i < 9; i++) {

            if (i % 2 == 0) {
                matrix = updateArr(matrix, 'X');
                printArr(matrix);
            } else {
                matrix = updateArr(matrix, 'O');
                printArr(matrix);
            }

            if (winpattern('X', matrix)) {
                condition = "X wins";
                break;
            } else if (winpattern('O',matrix)) {
                condition = "O wins";
                break;
            }

        }

        if (condition.equals("")) {
            condition = "Draw";
        }

        System.out.println(condition);

    }



    public static void main(String[] args) {
        // write your code here

        char[][] symbols = new char[3][3];

        Scanner sc = new Scanner(System.in);

        //System.out.println("Enter cells: ");
        String input = "         ";

        int Xcount = 0;
        int Ycount = 0;
        int count = 0;
        int emptyCells = 0;
        String condition = "";

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                symbols[i][j] = input.charAt(count);

                if (symbols[i][j] == 'X') {
                    ++Xcount;
                }

                if (symbols[i][j] == 'O') {
                    ++Ycount;
                }
                ++count;
            }

        }
        
        printArr(symbols);

        play(symbols);

    }
}
