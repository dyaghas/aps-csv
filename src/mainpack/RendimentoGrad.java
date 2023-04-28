package mainpack;

import java.util.HashMap;

public class RendimentoGrad extends Rendimento {

    public RendimentoGrad() {
    }

    @Override
    public void validarMedia(double media) {
        if(media >= 7) {
            setAprovado(true);
        } else {
            media = (media + getExame()) / 2;
            if(media >= 5) {
                setAprovado(true);
            }
            else {
                setAprovado(false);
            }
        }
    }
}
