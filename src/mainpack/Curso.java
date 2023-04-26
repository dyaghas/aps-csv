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
    public Curso(int id, String nome, String nivel, int ano) {
        this.nome = nome;
        this.nivel = nivel;
        this.ano = ano;
    }

    // Cadastra o curso no arquivo CSV

    public void cadastrarCurso(Scanner scan) throws IOException {
        System.out.print("Digite o nome do curso: ");
        String nomeCurso = scan.nextLine().toLowerCase();

        String nivelCurso = "";
        while (!nivelCurso.equals("1") && !nivelCurso.equals("2")) {
            System.out.print("Digite o nível do curso ([1] graduação ou [2] pós-graduação): ");
            nivelCurso = scan.nextLine().toLowerCase();
        }
        if (nivelCurso.equals("1")) {
            nivelCurso = "graduação";
        } else {
            nivelCurso = "pós-graduação";
        }

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

    // Deleta o curso do arquivo CSV
    public void deletarCurso(Scanner scan) throws IOException {
        System.out.print("Digite o nome do curso: ");
        String nomeCurso = scan.nextLine().toLowerCase();

        String nivelCurso = "";
        while (!nivelCurso.equals("1") && !nivelCurso.equals("2")) {
            System.out.print("Digite o nível do curso ([1] graduação ou [2] pós-graduação): ");
            nivelCurso = scan.nextLine().toLowerCase();
        }
        if (nivelCurso.equals("1")) {
            nivelCurso = "graduação";
        } else {
            nivelCurso = "pós-graduação";
        }

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

        if (existe) {
            File tempFile = new File("./temp.csv");
            Scanner tempScanner = new Scanner(cursoCsv);
            FileWriter writer = new FileWriter("./temp.csv", true);
            while (tempScanner.hasNextLine()) {
                String linha = tempScanner.nextLine();
                String[] dados = linha.split(",");
                if (!dados[0].equals(nomeCurso) || !dados[1].equals(nivelCurso) || !dados[2].equals(String.valueOf(anoCurso))) {
                    writer.append(linha);
                    writer.append("\n");
                }
            }
            writer.close();
            tempScanner.close();
            cursoCsv.delete();
            tempFile.renameTo(cursoCsv);
        } else {
            System.out.println("Curso não cadastrado!");
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
