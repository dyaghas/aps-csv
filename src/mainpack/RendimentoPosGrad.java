package mainpack;

import java.util.HashMap;

public class RendimentoPosGrad extends Rendimento {
    private HashMap alunosLista;

    public RendimentoPosGrad(Aluno alunoObject) {
        alunosLista = alunoObject.getAlunosHashMap();
    }

    //o valor de media deve ser instanciado através de um getter da superclasse em Main
    @Override
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
