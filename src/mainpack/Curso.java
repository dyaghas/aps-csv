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

    // Cadastra o curso no arquivo CSV, mas se já existir, não cadastra. (Acentuação e cedilha ficam assim -> �)
    public void cadastrarCurso(Scanner scan) throws IOException {
        System.out.print("Digite o nome do curso: ");
        String nomeCurso = scan.nextLine().toLowerCase();
        System.out.print("Digite o nível do curso: ");
        String nivelCurso = scan.nextLine().toLowerCase();
        System.out.print("Digite o ano do curso: ");
        int anoCurso = scan.nextInt();
        scan.nextLine();

        File cursoCsv = new File("./cursos.csv");
        Scanner scanner = new Scanner(cursoCsv);
        boolean existe = false;
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] dados = linha.split(",");
            if (dados[0].equals(nomeCurso) && dados[1].equals(nivelCurso) && dados[2].equals(String.valueOf(anoCurso))) {
                existe = true;
                break;
            }
        }
        scanner.close();

        if (!existe) {
            FileWriter writer = new FileWriter("./cursos.csv", true);
            writer.append(nomeCurso);
            writer.append(",");
            writer.append(nivelCurso);
            writer.append(",");
            writer.append(String.valueOf(anoCurso));
            writer.append("\n");
            writer.close();
        } else {
            System.out.println("Curso já cadastrado!");
        }
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
