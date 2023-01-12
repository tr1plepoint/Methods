import java.util.Scanner;

public class NonlinearSystems {
    public static void main(String[] args) {
        System.out.println("Введите начальное значение: ");
        double startingApproximation = new Scanner(System.in).nextDouble();
        System.out.println("Введите точность: ");
        double eps = new Scanner(System.in).nextDouble();
        double division = function(startingApproximation) / derivative(startingApproximation);
        double approximation = startingApproximation - division;

        while(function(approximation) >= eps) {
            System.out.println(approximation);
            division = function(approximation) / derivative(startingApproximation);
            approximation = approximation - division;
        }
        System.out.println(approximation);
    }

    public static double function(double x) {
        return x * x * x * x + x - 4;
    }
    public static double derivative(double x) {
        return 4 * x * x * x + 1;
    }
}
