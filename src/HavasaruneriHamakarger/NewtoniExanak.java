package HavasaruneriHamakarger;

import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.inversion.GaussJordanInverter;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.vector.dense.BasicVector;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public class NewtoniExanak {
    private Argument[] x;
    private Expression[] ef;
    private Matrix F;

    public NewtoniExanak(String[] ef, double[] x) {
        this.x = new Argument[x.length];
        this.ef = new Expression[ef.length];
        this.F = new Basic2DMatrix(x.length, x.length);

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
        System.out.println("Նյուտոնի եղանակ համակարգերի համար");

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
        Vector rightMul;

        Expression exp;
        do {
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < x.length; j++) {
                    exp = new Expression("der(" + ef[i].getExpressionString() + ",x" + j + ")");
                    for (int k = 0; k < x.length; k++) {
                        exp.addArguments(this.x[k]);
                    }
                    F.set(i, j, exp.calculate());
                }
            }

            for (int i = 0; i < x.length; i++) {
                efValue.set(i, ef[i].calculate());
            }

            rightMul = new GaussJordanInverter(F).inverse().multiply(efValue);

            for (int i = 0; i < x.length; i++) {
                x[i].setArgumentValue(x[i].getArgumentValue() - rightMul.get(i));
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
}