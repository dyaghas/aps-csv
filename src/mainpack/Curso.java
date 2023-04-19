package mainpack;

import java.io.FileWriter;
import java.io.IOException;

public class Curso {
    private String nome;
    private String nivel;
    private int ano;

    public Curso(String nome, String nivel, int ano) {
        this.nome = nome;
        this.nivel = nivel;
        this.ano = ano;
    }

    public String getNome() {
        return nome;
    }

    public String getNivel() {
        return nivel;
    }

    public int getAnoDeEntradaDoCurso() {
        return ano;
    }

    public void cadastrarCurso() throws IOException {
        FileWriter writer = new FileWriter("./cursos.csv", true);
        writer.append(nome);
        writer.append(",");
        writer.append(nivel);
        writer.append(",");
        writer.append(String.valueOf(ano));
        writer.append("\n");
        writer.close();
    }
}
