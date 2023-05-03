package mainpack;

public class RendimentoGrad extends Rendimento {

    public RendimentoGrad() {
    }

    @Override
    public void validarMedia(double media, double exame) {
        if(media >= 7) {
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
