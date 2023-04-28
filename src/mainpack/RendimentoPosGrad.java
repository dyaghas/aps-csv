package mainpack;

import java.util.HashMap;

public class RendimentoPosGrad extends Rendimento {

    public RendimentoPosGrad() {
    }

    @Override
    public void validarMedia(double media) {
        if(media >= 5) {
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
