import Havasarumner.HasarakIteracianeriExanak;

public class MainClass {
    public static void main(String[] args) {
        HasarakIteracianeriExanak hasarakIteracianeriExanak = new HasarakIteracianeriExanak(0, "2*x+log10(2*x+3)-1", "-0.5*log10(2*x+3)+0.5");
        hasarakIteracianeriExanak.iterations(3);
    }
}
