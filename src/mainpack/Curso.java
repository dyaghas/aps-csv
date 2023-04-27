package mainpack;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Curso {

    // Construtor vazio pra não bugar no Main
    public Curso() {}


    // Cadastra o curso em "cursos.csv"
    public void cadastrarCurso(Scanner scan) throws IOException {
        System.out.print("Digite o nome do curso: ");
        String nomeCurso = scan.nextLine().toUpperCase();

        String nivelCurso = "";
        while (!nivelCurso.equals("1") && !nivelCurso.equals("2")) {
            System.out.print("Digite o nível do curso ([1] graduação ou [2] pós-graduação): ");
            nivelCurso = scan.nextLine();
        }
        if (nivelCurso.equals("1")) {
            nivelCurso = "GRADUACAO";
        } else {
            nivelCurso = "POS_GRADUACAO";
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

            criarArquivoCsv(nomeCurso, nivelCurso, anoCurso);
        } else {
            System.out.println("Curso já cadastrado!");
        }
    }

    // Cria o arquivo CSV para cada curso
    public void criarArquivoCsv(String nomeCurso, String nivelCurso, int anoCurso) throws IOException {
        String nomeArquivo = nomeCurso.replaceAll(" ", "-") + "_" + nivelCurso + "_" + anoCurso + ".csv";
        FileWriter writer = new FileWriter(nomeArquivo);
        writer.close();
    }

    // Deleta o curso do arquivo CSV e seu arquivo CSV
    public void deletarCurso(Scanner scan) throws IOException {
        System.out.print("Digite o nome do curso: ");
        String nomeCurso = scan.nextLine().toUpperCase();
    
        String nivelCurso = "";
        while (!nivelCurso.equals("1") && !nivelCurso.equals("2")) {
            System.out.print("Digite o nível do curso ([1] graduação ou [2] pós-graduação): ");
            nivelCurso = scan.nextLine();
        }
        if (nivelCurso.equals("1")) {
            nivelCurso = "GRADUACAO";
        } else {
            nivelCurso = "POS_GRADUACAO";
        }
    
        System.out.print("Digite o ano do curso: ");
        int anoCurso = scan.nextInt();
        scan.nextLine();
    
        File cursoCsv = new File("./cursos.csv");
        Scanner scanner = new Scanner(cursoCsv);
        boolean encontrado = false;
        String linhaRemover = null;
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] dados = linha.split(",");
            if (dados[0].equals(nomeCurso) && dados[1].equals(nivelCurso) && dados[2].equals(String.valueOf(anoCurso))) {
                encontrado = true;
                linhaRemover = linha;
                break;
            }
        }
        scanner.close();
    
        if (encontrado) {
            String cursos = new String(Files.readAllBytes(Paths.get("./cursos.csv")));
            cursos = cursos.replaceAll(linhaRemover + "\n", "");
            Files.write(Paths.get("./cursos.csv"), cursos.getBytes());
    
            String nomeArquivo = nomeCurso.replaceAll(" ", "-") + "_" + nivelCurso + "_" + anoCurso + ".csv";
            File arquivoCsv = new File(nomeArquivo);
            if (arquivoCsv.delete()) {
                System.out.println("Curso e arquivo CSV removidos com sucesso.");
            } else {
                System.out.println("Erro ao remover o arquivo CSV.");
            }
        } else {
            System.out.println("Curso não encontrado.");
        }
    }

    // Exibe os cursos cadastrados
    public void exibirCurso() throws IOException {
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

    // Exibe os cursos cadastrados de um ano específico
    public void exibirCursoAno(Scanner scan) throws IOException {
        System.out.print("Digite o ano do curso: ");
        int anoCurso = scan.nextInt();
        scan.nextLine();

        File cursoCsv = new File("./cursos.csv");
        Scanner scanner = new Scanner(cursoCsv);
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] dados = linha.split(",");
            if (dados[2].equals(String.valueOf(anoCurso))) {
                System.out.println("Nome: " + dados[0]);
                System.out.println("Nível: " + dados[1]);
                System.out.println("Ano: " + dados[2]);
                System.out.println();
            }
        }
        scanner.close();
    }
}
