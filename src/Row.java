import java.util.Arrays;
import java.util.Scanner;

public class Row {
    public static void main(String[] args) {

        int n = 4;
        int m = 3;

        double[][] matrice = {{0, 1, 2}, {2, 1, 1}, {1, -1, 0}, {3, -1, 2}};
        double[] matriceRes = {0, 0, 0};
        double[] matriceB = {2, -2, 2};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        for (int step = 0; step < m - 1; step++) {

            double absMax = matrice[1][0];
            int index = 0;
            for (int i = 1; i < m; i++) {
                if (Math.abs(matrice[1][i]) > Math.abs(absMax)) {
                    absMax = matrice[1][i];
                    index = i;
                }
            }

            double temp;
            if (index != 0) {
                for (int i = 0; i < n; i++) {
                    temp = matrice[i][0];
                    matrice[i][0] = matrice[i][index];
                    matrice[i][index] = temp;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(matrice[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();

            double difference;
            for (int i = step + 2; i < n; i++) {
                difference = matrice[i][step] / matrice[step + 1][step];
                for (int j = 0; j < m; j++) {
                    matrice[i][j] -= matrice[step + 1][j] * difference;
                }
                matriceB[i - 1] -= matriceB[step] * difference;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(matrice[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();


        }

        for (int i = n - 1; i > 0; i--) {
            for (int j = i; j < m; j++) {
                matriceB[i - 1] -= matrice[i][j] * matriceRes[(int) matrice[0][j]];
            }
            System.out.println(Arrays.toString(matriceB));
            matriceRes[(int) matrice[0][i - 1]] = matriceB[i - 1] / matrice[i][i - 1];
        }

        for(int i = 0; i < m; i++) {
            System.out.print(matriceRes[i] + " ");
        }
    }
}
