import Havasarumner.HasarakIteracianeriExanak;
import Havasarumner.NewtoniExanak;

public class MainClass {
    public static void main(String[] args) {
        HasarakIteracianeriExanak hasarakIteracianeriExanak = new HasarakIteracianeriExanak("2*x+log10(2*x+3)-1", "-0.5*log10(2*x+3)+0.5", 0);
        hasarakIteracianeriExanak.iterations(3);

        NewtoniExanak newtoniExanak = new NewtoniExanak("2*x+log10(2*x+3)-1", 0);
        newtoniExanak.iterations(3);
    }
}
