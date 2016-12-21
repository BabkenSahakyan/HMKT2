package HavasaruneriHamakarger;

import org.la4j.Vector;
import org.la4j.vector.dense.BasicVector;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public class DiferencialHavasarumneriMetod {
    private Argument[] x;
    private Expression[] ef;
    private Vector lyamna;
    private Vector k;
    private double h;
    private double delta;

    public DiferencialHavasarumneriMetod(String[] ef, double[] x, double[] k, double h, double delta) {
        this.x = new Argument[x.length];
        this.ef = new Expression[ef.length];
        this.lyamna = new BasicVector(x.length);
        this.k = new BasicVector(k);
        this.h = h;
        this.delta = delta;

        for (int i = 0; i < x.length; i++) {
            this.x[i] = new Argument("x" + i, x[i]);
            this.ef[i] = new Expression(ef[i]);
        }

        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++) {
                this.ef[i].addArguments(this.x[j]);
            }
        }
    }

    public void iterations(int precision) {
        System.out.println();
        System.out.println("Դիֆերենցյալ հավասարումների մեթոդ համակարգերի համար");

        for (int i = 0; i < x.length; i++) {
            System.out.println(ef[i].checkSyntax());
        }

        for (int j = 0; j < x.length; j++) {
            System.out.print(ef[j].getExpressionString() +
                    " = " + ef[j].calculate() + ", ");
        }

        System.out.println();

        for (int j = 0; j < x.length; j++) {
            System.out.print("x" + j + " = " + x[j].getArgumentValue() + ", ");
        }

        System.out.println();

        Vector efValue = new BasicVector(x.length);

        Vector previousX = new BasicVector(x.length);
        Vector tmpX = new BasicVector(x.length);

        lyamna = getLyamna();

        for (int i = 0; i < x.length; i++) {
            previousX.set(i, (4 * x[i].getArgumentValue() - 2 * Math.pow(h, 2) * lyamna.get(i)) /
                    (2 + delta * h));
        }

        do {
            for (int i = 0; i < x.length; i++) {
                efValue.set(i, ef[i].calculate());
            }

            for (int i = 0; i < x.length; i++) {
                tmpX.set(i, (4 * x[i].getArgumentValue() - (2 - delta * h) * previousX.get(i) -
                2 * Math.pow(h, 2) * lyamna.get(i)) / (2 + delta * h));

                previousX.set(i, x[i].getArgumentValue());

                x[i].setArgumentValue(tmpX.get(i));
            }

            lyamna = getLyamna();

            for (int j = 0; j < x.length; j++) {
                System.out.print(ef[j].getExpressionString() +
                        " = " + ef[j].calculate() + ", ");
            }

            System.out.println();

            for (int j = 0; j < x.length; j++) {
                System.out.print("x" + j + " = " + x[j].getArgumentValue() + ", ");
            }

            System.out.println();

        }
        while (!end(ef, precision));
    }

    private boolean end(Expression[] ef, int precision) {
        for (int i = 0; i < ef.length; i++) {
            if (ef[i].calculate() <= -Math.pow(10, -precision) ||
                    ef[i].calculate() >= Math.pow(10, -precision) ||
                    Math.round(ef[i].calculate()) != 0)
                return false;
        }
        return true;
    }

    private Vector getLyamna(){
        double partOfLyamna = 0;
        Expression exp;
        Vector lyamna = new BasicVector(x.length);

        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++) {
                exp = new Expression("der(" + k.get(j) + "*(" + ef[j].getExpressionString() + ")^2,x" + i + ")");
                for (int k = 0; k < x.length; k++) {
                    exp.addArguments(x[k]);
                }
                partOfLyamna += exp.calculate();
            }
            lyamna.set(i, partOfLyamna);
        }

        return lyamna;
    }
}
