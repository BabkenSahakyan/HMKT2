import Havasarumner.HasarakIteracianeriExanak;
import Havasarumner.NewtoniExanak;

public class MainClass {
    public static void main(String[] args) {
        HasarakIteracianeriExanak hasarakIteracianeriExanak =
                new HasarakIteracianeriExanak(
                        "2*x+log10(2*x+3)-1",
                        "-0.5*log10(2*x+3)+0.5",
                        0);
        hasarakIteracianeriExanak.iterations(3);

        NewtoniExanak newtoniExanak = new NewtoniExanak("x^3-2*x^2+1", 0.5);
        newtoniExanak.iterations(3, 1);
        newtoniExanak.iterations(3, 2);
        newtoniExanak.iterations(3, 3);
        newtoniExanak.iterations(3, 4);
        newtoniExanak.iterations(3, 5);

        HavasaruneriHamakarger.HasarakIteracianeriExanak hasarakIteracianeriExanak1 =
                new HavasaruneriHamakarger.HasarakIteracianeriExanak(
                        new String[]{"3*x0-cos(x1)-0.9", "sin(x0-0.6)-x1-1.6"},
                        new String[]{"cos(x1)/3+0.3", "sin(x0-0.6)-1.6"},
                        new double[]{-1, -1});
        hasarakIteracianeriExanak1.iterations(3);

//        HavasaruneriHamakarger.HasarakIteracianeriExanak hasarakIteracianeriExanak2 =
//                new HavasaruneriHamakarger.HasarakIteracianeriExanak(
//                        new String[]{"x0+2*x1-5*x2^2+6", "8*x0-x1+x2^3-4", "x0^2-x1^3+x2+5"},
//                        new String[]{"-2*x1+5*x2^2-6", "8*x0+x2^3-4", "-x0^2+x1^3-5"},
//                        new double[]{0, 0, 0});
//        hasarakIteracianeriExanak2.iterations(3);


    }
}
