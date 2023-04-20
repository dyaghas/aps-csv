package mainpack;

import java.io.*;
import java.util.*;

public class Curso {
    private String nome;
    private String nivel;
    private int ano;

    // Construtor vazio pra não bugar no Main
    public Curso() {}

    // Construtor com as propriedades dos cursos
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

    public int getAno() {
        return ano;
    }

    // Escreve no arquivo CSV
    public void cadastrarCurso(String nome, String nivel, int ano) throws IOException {
        FileWriter writer = new FileWriter("./cursos.csv", true);
        writer.append(nome);
        writer.append(",");
        writer.append(nivel);
        writer.append(",");
        writer.append(String.valueOf(ano));
        writer.append("\n");
        writer.close();
    }

    // Exibe os cursos cadastrados
    public void exibirCsv() throws IOException {
        File cursoCsv = new File("./cursos.csv");
        Scanner scanner = new Scanner(cursoCsv);
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] dados = linha.split(",");
            System.out.println("Nome: " + dados[0]);
            System.out.println("Nível: " + dados[1]);
            System.out.println("Ano: " + dados[2]);
            System.out.println();
        }
        scanner.close();
    }
}
