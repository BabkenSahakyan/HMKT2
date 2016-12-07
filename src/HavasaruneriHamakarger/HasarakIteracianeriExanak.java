package HavasaruneriHamakarger;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public class HasarakIteracianeriExanak {
    private Argument[] x;
    private Expression[] ef;
    private Expression[] fi;

    public HasarakIteracianeriExanak(String[] ef, String[] fi, double[] x){
        this.x = new Argument[x.length];
        this.ef = new Expression[ef.length];
        this.fi = new Expression[fi.length];
        for (int i = 0; i < x.length; i++) {
            this.x[i] = new Argument("x" + i, x[i]);
            this.ef[i] = new Expression(ef[i]);
            this.fi[i] = new Expression(fi[i]);
        }
        
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++) {

                this.ef[i].addArguments(this.x[j]);

                if (j != i){
                    this.fi[i].addArguments(this.x[j]);
                }
            }
        }
    }

    public void iterations(int precision) {
        System.out.println();
        System.out.println("Հասարակ իտերացիաների եղանակ համակարգերի համար");

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

        double[] tmp = new double[x.length];
        do {
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < x.length; j++) {
                    tmp[j] = fi[j].calculate();
                }
                for (int j = 0; j < x.length; j++) {
                    x[j].setArgumentValue(tmp[j]);
                }
                ef[i].calculate();

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
        }
        while (!end(ef, precision));
    }

    private boolean end(Expression[] ef, int precision){
        for (int i = 0; i < ef.length; i++) {
            if(ef[i].calculate() <= -Math.pow(10, -precision) ||
                    ef[i].calculate() >= Math.pow(10, -precision) ||
                    Math.round(ef[i].calculate()) != 0)
                return false;
        }
        return true;
    }
}
