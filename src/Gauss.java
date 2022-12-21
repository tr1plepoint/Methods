import java.util.Scanner;


public class Gauss {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Интерграл для синуса: 1\nИнтеграл для функции: 2\nРунге функция: 3\nРунге синус: 4 \nНастоящее значение функции: 5\nНастоящее значение синуса: 6");
            int choice = sc.nextInt();
            if (choice == 1)
                System.out.println("Интерграл для синуса: " + integralPi(2));
            else if (choice == 2)
                System.out.println("Интеграл для функции: " + integral(2));
            else if (choice == 3) {
                System.out.println("Рунге функция: " + runge(2));
            } else if (choice == 4) {
                System.out.println("Рунге синус: " + rungeSinus(2));
            } else if (choice == 5) {
                System.out.println("Настоящее значение функции: " + realintegral());
            } else if (choice == 6) {
                System.out.println("Настоящее значение синуса: " + realintegralpi());
            }
            System.out.print("-----------------------------------------------------------------------\n");
        }
    }

    public static double realintegral() {
        return Math.pow(2, 4) + Math.pow(2, 3) / 3;
    }

    public static double realintegralpi() {
        return -(Math.cos(Math.PI) - Math.cos(0));
    }

    public static double integral(int n) {

        double upperbound = 2;
        double bottombound = 0;

        double[][] Ti = {{0, 0, 0}, {-0.57735, 0.57735, 0}, {-0.77460, 0, 0.77460}};
        double[][] Ci = {{2, 0, 0}, {1, 1, 0}, {1, 8 / 9, 5 / 9}};

        double[] Xi = new double[n];


        for (int i = 0; i < n; i++) {
            Xi[i] = ((bottombound + upperbound) / 2) + (((upperbound - bottombound) / 2) * Ti[n - 1][i]);
        }

        double integral = 0;
        for (int i = 0; i < n; i++) {
            integral += function(Xi[i]) * Ci[n - 1][i];
        }
        integral *= (upperbound - bottombound) / 2;
        return integral;
    }

    public static double integralPi(int n) {

        double upperbound = Math.PI;
        double bottombound = 0;

        double[][] Ti = {{0, 0, 0}, {-0.57735, 0.57735, 0}, {-0.77460, 0, 0.77460}};
        double[][] Ci = {{2, 0, 0}, {1, 1, 0}, {1, 8 / 9, 5 / 9}};


        for (int j = 0; j < n; j++) {
            System.out.println(Ti[n - 1][j]);
        }

        for (int j = 0; j < n; j++) {
            System.out.println(Ci[n - 1][j]);
        }

        double[] Xi = new double[n];

        for (int i = 0; i < n; i++) {
            Xi[i] = ((bottombound + upperbound) / 2) + (((upperbound - bottombound) / 2) * Ti[n - 1][i]);
        }

        double integral = 0;
        for (int i = 0; i < n; i++) {
            integral += sinus(Xi[i]) * Ci[n - 1][i];
        }
        integral *= (upperbound - bottombound) / 2;
        return integral;
    }

    public static double runge(int n) {
        return Math.abs(integral(2) - integral(1)) / (Math.pow(2, 4) - 1);
    }

    public static double rungeSinus(int n) {
        return Math.abs(integralPi(2) - integralPi(1)) / (Math.pow(2, 4) - 1);
    }

    public static double function(double x) {
        return (4 * x * x * x + x * x);
    }

    public static double sinus(double x) {
        return Math.sin(x);
    }
}
