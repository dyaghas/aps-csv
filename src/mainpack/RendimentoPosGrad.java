package mainpack;

public class RendimentoPosGrad extends Rendimento {

    public RendimentoPosGrad() {
    }

    @Override
    public void validarMedia(double media, double exame) {
        if(media >= 5) {
            setAprovado(true);
        } else {
            media = (media + exame) / 2;
            if(media >= 5) {
                setAprovado(true);
            }
            else {
                setAprovado(false);
            }
        }
    }
}
