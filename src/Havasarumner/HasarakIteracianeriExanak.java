package Havasarumner;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.PrimitiveElement;

public class HasarakIteracianeriExanak {
    private Expression ef;
    private Expression fi;
    private Argument x;

    public HasarakIteracianeriExanak(String ef, String fi, double x) {
        this.x = new Argument("x", x);
        this.ef = new Expression(ef, new PrimitiveElement[]{this.x});
        this.fi = new Expression(fi, new PrimitiveElement[]{this.x});
    }

    public void iterations(int precision) {
        System.out.println();
        System.out.println(ef.getExpressionString() + " = " + ef.calculate() + ", x = " + x.getArgumentValue());
        do {
            x.setArgumentValue(fi.calculate());
            ef.calculate();
            System.out.println(ef.getExpressionString() + " = " + ef.calculate() + ", x = " + x.getArgumentValue());
        }
        while (ef.calculate() <= -Math.pow(10, -precision) || ef.calculate() >= Math.pow(10, -precision) || Math.round(ef.calculate()) != 0);
    }
}
