import java.util.Scanner;

public class OneDimensionalOptimization {
    public static double calculateFunction(double x) {
        return 10 * x * Math.log(x) - (x * x / 2);
    }

    public static double[] swannAlgorithm() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Задать некоторую точку: ");
        double x0 = sc.nextFloat();

        System.out.println("Введите величину шага: ");
        double t = sc.nextFloat();
        int k = 0;

        double FXminust = calculateFunction(x0 - t);
        double FX = calculateFunction(x0);
        double FXplust = calculateFunction(x0 + t);

        if (FXminust >= FX && FX <= FXplust)
            return new double[]{x0 - t, x0 + t};
        if (FXminust <= FX && FX >= FXplust) {
            System.out.println("Нужно задать другую некоторую точку");
            swannAlgorithm();
        }

        double[] result = new double[2];
        double delta;

        if (FXminust >= FX && FX >= FXplust) {
            delta = t;
            result[0] = x0;
            x0 = x0 + t;
        } else {
            delta = -t;
            result[1] = x0;
            x0 = x0 - t;
        }
        k++;


        double xk = x0;
        double xknext = xk + Math.pow(2, k) * delta;

        while (true) {
            double Fxk = calculateFunction(xk);
            double Fxknext = calculateFunction(xknext);

            if (Fxknext >= Fxk) {
                if (delta == t)
                    result[1] = xknext;
                else
                    result[0] = xknext;
                return result;
            } else if (delta == t)
                result[0] = xk;
            else
                result[1] = xk;
            k++;
            xk = xknext;
            xknext = xk + Math.pow(2, k) * delta;
        }
    }

    public static void main(String[] args) {
        double[] result = swannAlgorithm();
        System.out.println(result[0] + " " + result[1]);
    }
}
