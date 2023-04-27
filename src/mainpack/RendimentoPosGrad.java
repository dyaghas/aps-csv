package mainpack;

public class RendimentoPosGrad extends Rendimento {
    public void validarMedia(double media) {
        if(media >= 5) {
            setAprovado(true);
        } else {
            media = (media + getExame()) / 2;
            if(media >= 5) {
                setAprovado(true);
            }
        }
    }
}
