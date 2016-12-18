package Havasarumner;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;
import org.mariuszgromada.math.mxparser.PrimitiveElement;

public class NewtoniExanak {

    private String function;
    private double arg;

    public NewtoniExanak(String ef, double x){
        this.arg = x;
        this.function = ef;
    }

    public void iterations(int precision, int method) {
        Argument x = new Argument("x", arg);
        Expression ef = new Expression(function, new PrimitiveElement[]{x});
        Function f = new Function("f", function, new String[]{"x"});

        System.out.println("\nՆյուտոնի եղանակ հավասարումների համար");

        System.out.println(ef.checkSyntax());

        System.out.println(ef.getExpressionString() + " = " + ef.calculate() + ", x = " + x.getArgumentValue());
        Expression der = new Expression("der(f(x),x)", new PrimitiveElement[]{x});
        Expression der2 = new Expression("der(der(f(x),x),x)", new PrimitiveElement[]{x});
        Expression der3 = new Expression("der(der(der(f(x),x),x),x)", new PrimitiveElement[]{x});
        der.addDefinitions(new PrimitiveElement[]{f});
        der2.addDefinitions(new PrimitiveElement[]{f});
        do {
            switch (method) {
                case 1:
                    x.setArgumentValue(x.getArgumentValue() - ef.calculate() / der.calculate());
                    break;
                case 2:
                    x.setArgumentValue(x.getArgumentValue() - (ef.calculate() * der.calculate()) /
                            (Math.pow(der.calculate(), 2) - ef.calculate() * der2.calculate() *0.5));
                    break;
                case 3:
                    x.setArgumentValue(x.getArgumentValue() -
                            (ef.calculate() * Math.pow(der.calculate(), 2) - Math.pow(ef.calculate(), 2) * der2.calculate() * 0.5) /
                                    (Math.pow(der.calculate(), 3) - ef.calculate() * der.calculate() * der2.calculate() +
                                            Math.pow(ef.calculate(), 2) * der3.calculate() / 6));
                    break;
                case 4:
                    x.setArgumentValue(x.getArgumentValue() -
                            (ef.calculate() * Math.pow(der.calculate(), 2) - Math.pow(ef.calculate(), 2) * der.calculate()) /
                                    Math.pow(der.calculate(), 3));
                    break;
                case 5:
                    x.setArgumentValue(x.getArgumentValue() -
                            ((ef.calculate() * Math.pow(der.calculate(), 2) + Math.pow(ef.calculate(), 2) * der.calculate()) /
                                    Math.pow(der.calculate(), 3)) +
                            (Math.pow(ef.calculate(), 3) * ((der.calculate() * der3.calculate() - 3 * Math.pow(der2.calculate(), 2)) /
                            6 * Math.pow(der.calculate(), 5))));
                    break;

            }
            System.out.println(ef.getExpressionString() + " = " + ef.calculate() + ", x = " + x.getArgumentValue());
        }
        while (ef.calculate() <= -Math.pow(10, -precision) || ef.calculate() >= Math.pow(10, -precision) || Math.round(ef.calculate()) != 0);
    }
}
