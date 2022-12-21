import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int arg = 4;
        Scanner sc = new Scanner(System.in);

        float arrX[] = new float[arg];

        System.out.println("Аргументы");
        for (int i = 0; i < arg; i++) {
            arrX[i] = sc.nextFloat();
        }

        int choice;
        double predict;
        while (true) {
            System.out.println("Таблица: 1\nФункция: 2");
            choice = sc.nextInt();
            System.out.println("Введите аргумент");
            predict = sc.nextFloat();
            if (choice == 1)
                table(arrX, arg, predict);
            else if (choice == 2)
                function(arrX, arg, predict);
            else System.out.println("Неверный ввод");
        }
    }

    public static void table(float[] arrX, int arg, double predict) {
        float result = 0;
        Scanner sc = new Scanner(System.in);


        float arrY[][] = new float[arg][arg];

        System.out.println("Значения: ");
        for (int i = 0; i < arg; i++) {
            arrY[i][0] = sc.nextFloat();
        }

        for (int i = 0; i < arg; i++) {
            System.out.println(arrY[i][0]);
        }
        // i - колонна, j - ряд
        for (int i = 1, z = 1; i < arg; i++, z++) {
            for (int j = 0; j < arg - z; j++) {
                arrY[j][i] = (arrY[j + 1][i - 1] - arrY[j][i - 1]) / (arrX[j + i] - arrX[j]);
            }

        }

        System.out.println("Конечные разности: ");
        for (int i = 0; i < arg; i++) {
            for (int j = 0; j < arg; j++) {
                System.out.format("%5f ", arrY[i][j]);
            }
            System.out.println();
        }

        float temp;

        for (int i = 0; i < arg; i++) {
            temp = arrY[0][i];
            for (int j = 0; j < i; j++) {
                temp *= (predict - arrX[j]);
            }
            result += temp;
        }
        System.out.println("При x = " + predict + " y = " + result);
    }

    public static void function(float[] arrX, int arg, double predict) {
        float result = 0;
        Scanner sc = new Scanner(System.in);

        float arrY[][] = new float[arg][arg];

        for (int i = 0; i < arg; i++) {
            arrY[i][0] = arrX[i] * arrX[i] * arrX[i] - 2 * arrX[i];
        }

        for (int i = 0; i < arg; i++) {
            for (int j = 0; j < arg; j++) {
                System.out.print(arrY[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 1, z = 1; i < arg; i++, z++) {
            for (int j = 0; j < arg - z; j++) {
                arrY[j][i] = (arrY[j + 1][i - 1] - arrY[j][i - 1]) / (arrX[j + i] - arrX[j]);
            }

        }

        for (int i = 0; i < arg; i++) {
            for (int j = 0; j < arg; j++) {
                System.out.format("%7f ", arrY[i][j]);
            }
            System.out.println();
        }


        float temp;

        for (int i = 0; i < arg; i++) {
            temp = arrY[0][i];
            for (int j = 0; j < i; j++) {
                temp *= (predict - arrX[j]);
            }
            result += temp;
        }
        System.out.println("При x = " + predict + " y = " + result);
    }
}