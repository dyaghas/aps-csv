package mainpack;

public class RendimentoGrad extends Rendimento {
    // O valor de media deve ser instanciado através de um getter da superclasse em Main
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
