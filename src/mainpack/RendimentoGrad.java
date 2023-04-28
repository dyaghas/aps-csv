package mainpack;

import java.util.HashMap;

public class RendimentoGrad extends Rendimento {
    private HashMap alunosLista;

    public RendimentoGrad(Aluno alunoObject) {
        alunosLista = alunoObject.getAlunosHashMap();
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
        }
    }
}
