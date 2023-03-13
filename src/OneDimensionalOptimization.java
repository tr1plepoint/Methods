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

    public static double goldenRatio(double[] interval) {
        double precision = (interval[0] + interval[1]) / 10;
        int k = 0;
        double yk = interval[0] + ((3 - Math.sqrt(5)) / 2) * (interval[1] - interval[0]);
        double zk = interval[0] + interval[1] - yk;


        while (true) {
            double Fyk = calculateFunction(yk);
            double Fzk = calculateFunction(zk);

            if (Fyk <= Fzk) {
                interval[1] = zk;
                zk = yk;
                yk = interval[0] + interval[1] - yk;
            } else {
                interval[0] = yk;
                yk = zk;
                zk = interval[0] + interval[1] - zk;
            }

            if (Math.abs(interval[0] - interval[1]) <= precision) {
                System.out.println(interval[0] + " " + interval[1]);
                return (interval[0] + interval[1]) / 2;
            }
            k++;
        }
    }

    public static double quadraticInterpolation() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Задать некоторую точку: ");
        double x1 = sc.nextFloat();

        System.out.println("Введите величину шага: ");
        double deltaX = sc.nextFloat();

        double epsilon1 = 0.001;
        double epsilon2 = 0.01;

        while(true) {
            double x2 = x1 + deltaX;

            double Fx1 = calculateFunction(x1);
            double Fx2 = calculateFunction(x2);

            double x3;
            if (Fx1 > Fx2) {
                x3 = x1 + 2 * deltaX;
            } else {
                x3 = x1 - deltaX;
            }

            double Fx3 = calculateFunction(x3);

            while (true) {
                double Fmin = Math.min(Math.min(Fx1, Fx2), Fx3);
                double xmin;
                if (calculateFunction(x1) == Fmin)
                    xmin = x1;
                else if (calculateFunction(x2) == Fmin)
                    xmin = x2;
                else
                    xmin = x3;

                double linex = (((x2 * x2 - x3 * x3) * Fx1 + (x3 * x3 - x1 * x1) * Fx2 + (x1 * x1 - x2 * x2) * Fx3) /
                        ((x2 - x3) * Fx1 + (x3 - x1) * Fx2 + (x1 - x2) * Fx3)) / 2;
                double Flinex = calculateFunction(linex);

                boolean FirstCondition = ((Fmin - Flinex) / Flinex) < epsilon1;
                boolean SecondCondition = ((xmin - linex) / linex) < epsilon2;

                if (FirstCondition && SecondCondition) {
                    return linex;
                } else if (!(linex < x3 && linex > x1)) {
                    x1 = linex;
                    break;
                } else if (xmin < linex && xmin < x2) {
                    x2 = xmin;
                    x3 = x2;
                } else if (xmin > x2) {
                    x1 = x2;
                    x2 = xmin;
                } else if (xmin < linex && linex < x2) {
                    x2 = linex;
                    x3 = x2;
                } else if (linex > x2) {
                    x1 = x2;
                    x2 = linex;
                }
            }
        }
    }

    public static void main(String[] args) {
        /*double[] result = swannAlgorithm();
        System.out.println(result[0] + " " + result[1]);
        double goldenRatioResult = goldenRatio(result);
        System.out.println(goldenRatioResult);*/
        double quadraticInterpolationResult = quadraticInterpolation();
        System.out.println(quadraticInterpolationResult);
    }
}
