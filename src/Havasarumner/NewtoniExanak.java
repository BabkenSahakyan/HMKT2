package Havasarumner;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;
import org.mariuszgromada.math.mxparser.PrimitiveElement;

public class NewtoniExanak {
    private Expression ef;
    private Argument x;
    private Function f;

    public NewtoniExanak(String ef, double x){
        this.x = new Argument("x", x);
        f = new Function("f", ef, new String[]{"x"});
        this.ef = new Expression(ef, new PrimitiveElement[]{this.x});
    }

    public void iterations(int precision) {
        System.out.println();
        System.out.println(ef.getExpressionString() + " = " + ef.calculate() + ", x = " + x.getArgumentValue());
        Expression der = new Expression("der(f(x),x)", new PrimitiveElement[]{x});
        der.addDefinitions(new PrimitiveElement[]{f});
        do {
            x.setArgumentValue(x.getArgumentValue() - ef.calculate() / der.calculate());
            ef.calculate();
            System.out.println(ef.getExpressionString() + " = " + ef.calculate() + ", x = " + x.getArgumentValue());
        }
        while (ef.calculate() <= -Math.pow(10, -precision) || ef.calculate() >= Math.pow(10, -precision) || Math.round(ef.calculate()) != 0);
    }
}
