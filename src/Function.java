import java.util.Scanner;

public class Function {
    public static void main(String[] args) {
        float result = 0;
        int n = 4;
        int m = 4;
        Scanner sc = new Scanner(System.in);


        float arrX[] = new float[m];
        float arrY[][] = new float[m][m];

        System.out.println("Arguments " + n);
        for (int i = 0; i < m; i++) {
            arrX[i] = sc.nextFloat();
        }

        for (int i = 0; i < m; i++){
            arrY[i][0] = arrX[i] * arrX[i] * arrX[i] - 2 * arrX[i];
        }

        for (int i = 0; i < m; i++) {
            System.out.println(arrX[i]);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arrY[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 1, z = 1; i < m; i++, z++) {
            for (int j = 0; j < m - z; j++) {
                arrY[j][i] = (arrY[j + 1][i - 1] - arrY[j][i - 1]) / (arrX[j + i] - arrX[j]);
            }

        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                System.out.format("%7f ", arrY[i][j]);
            }
            System.out.println();
        }

        while(true) {
            float predict = sc.nextFloat();
            float temp;

            for (int i = 0; i < m; i++) {
                temp = arrY[0][i];
                for (int j = 0; j < i; j++) {
                    temp *= (predict - arrX[j]);
                }
                result += temp;
            }
            System.out.println(result);
            result = 0;
        }
    }
}
