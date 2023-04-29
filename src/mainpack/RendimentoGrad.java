package mainpack;

import java.util.Scanner;

public class RendimentoGrad extends Rendimento {

    public RendimentoGrad() {
    }

    @Override
    public void validarMedia(double media) {
        if(media >= 7) {
            setAprovado(true);
        } else {
            media = (media + lerExame()) / 2;
            if(media >= 5) {
                setAprovado(true);
            }
            else {
                setAprovado(false);
            }
        }
    }
}
