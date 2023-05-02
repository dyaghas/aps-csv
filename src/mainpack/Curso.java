package mainpack;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Curso implements CsvInterface {
    private final File cursoCsv = new File("./cursos.csv");

    //lista que guarda as linhas do arquivo cursos.csv
    ArrayList<String> cursos = new ArrayList<>();

    public Curso() {
        lerCsv();
        System.out.println(cursos);
    }

    //instancia o arquivo csv na lista
    public void lerCsv() {
        try (BufferedReader br = new BufferedReader(new FileReader(cursoCsv))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                processaLinhaCsv(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Realiza as operações de leitura em cada linha do csv
    private void processaLinhaCsv(String linha) {
        String[] parte = linha.split(";");
        String nome = parte[0];
        String nivel = parte[1];
        int ano = Integer.parseInt(parte[2]);
        cursos.add(nome+"/"+nivel+"/"+ano);
    }

    // Cadastra o curso em "cursos.csv"
    public void cadastrarDadoCsv(Scanner scan) {
        System.out.print("Digite o nome do curso: ");
        String nomeCurso = scan.nextLine().toUpperCase();
        //verifica se o curso é de graduação ou pós-graduação
        String nivelCurso = verificarNivelCurso(scan);
        System.out.print("Digite o ano do curso: ");
        int anoCurso = scan.nextInt();
        scan.nextLine();
        try {
            File cursoCsv = new File("./cursos.csv");
            Scanner scanner = new Scanner(cursoCsv);
            boolean existe = false;
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] dados = linha.split(";");
                if (dados[0].equals(nomeCurso) && dados[1].equals(nivelCurso) && dados[2].equals(String.valueOf(anoCurso))) {
                    existe = true;
                    break;
                }
            }
            scanner.close();
            //caso o curso em questão não exista
            if (!existe) {
                FileWriter writer = new FileWriter("./cursos.csv", true);
                writer.append(nomeCurso);
                writer.append(";");
                writer.append(nivelCurso);
                writer.append(";");
                writer.append(String.valueOf(anoCurso));
                writer.append("\n");
                writer.close();
                //cria o arquivo CSV do curso
                criarArquivoCsv(nomeCurso, nivelCurso, anoCurso);
            } else {
                System.out.println("Curso já cadastrado!");
            }
        } catch(IOException e) {
            System.out.println("Ocorreu um erro.");
        }
    }

    //cria o arquivo CSV do curso
    public void criarArquivoCsv(String nomeCurso, String nivelCurso, int anoCurso) throws IOException {
        String nomeArquivo = nomeCurso.replaceAll(" ", "-") + "_" + nivelCurso + "_" + anoCurso + ".csv";
        FileWriter writer = new FileWriter(nomeArquivo);
        writer.close();
    }

    //verifica se o curso é de graduação ou pós-graduação
    public String verificarNivelCurso(Scanner scan) {
        String nivelCurso = "";
        while (!nivelCurso.equals("1") && !nivelCurso.equals("2")) {
            System.out.print("Digite o nível do curso ([1] graduação ou [2] pós-graduação): ");
            nivelCurso = scan.nextLine();
        }
        if (nivelCurso.equals("1")) {
            nivelCurso = "GRADUACAO";
        } else {
            nivelCurso = "POS-GRADUACAO";
        }
        return nivelCurso;
    }

    // Deleta o curso do arquivo CSV e seu arquivo CSV
    public void deletarCurso(Scanner scan) {
        try {
            System.out.print("Digite o nome do curso: ");
            String nomeCurso = scan.nextLine().toUpperCase();
            //verifica se o curso é de graduação ou pós-graduação
            String nivelCurso = verificarNivelCurso(scan);
            System.out.print("Digite o ano do curso: ");
            int anoCurso = scan.nextInt();
            scan.nextLine();
            File cursoCsv = new File("./cursos.csv");
            Scanner scanner = new Scanner(cursoCsv);
            boolean encontrado = false;
            String linhaRemover = null;
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] dados = linha.split(";");
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
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    // Exibe os cursos cadastrados
    public void listarCsv() {
        try {
            File cursoCsv = new File("./cursos.csv");
            Scanner scanner = new Scanner(cursoCsv);
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] dados = linha.split(";");
                System.out.println("Nome: " + dados[0]);
                System.out.println("Nível: " + dados[1]);
                System.out.println("Ano: " + dados[2]);
                System.out.println();
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("Não foi possível encontrar o arquivo /cursos.csv");
        }
    }

    // Exibe os cursos cadastrados de um ano específico
    public void exibirCursoAno(Scanner scan) {
        System.out.print("Digite o ano do curso: ");
        int anoCurso = scan.nextInt();
        filtrarCursoAno(scan, anoCurso);
    }

    public void filtrarCursoAno(Scanner scan, int anoCurso) {
        try {
            scan.nextLine();
            File cursoCsv = new File("./cursos.csv");
            Scanner scanner = new Scanner(cursoCsv);
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] dados = linha.split(";");
                if (dados[2].equals(String.valueOf(anoCurso))) {
                    System.out.println("Nome: " + dados[0]);
                    System.out.println("Nível: " + dados[1]);
                    System.out.println("Ano: " + dados[2]);
                    System.out.println();
                }
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("Não foi possível encontrar o arquivo /cursos.csv");
        }
    }

    // Exibe os alunos e suas notas de um curso (nome,nivel,ano) específico
    public void exibirAlunosCurso(Scanner scan) {
        System.out.print("Digite o nome do curso: ");
        String nomeCurso = scan.nextLine().toUpperCase();
        //verifica se o curso é de graduação ou pós-graduação
        String nivelCurso = verificarNivelCurso(scan);
        System.out.print("Digite o ano do curso: ");
        int anoCurso = scan.nextInt();
        scan.nextLine();
        String nomeArquivo = nomeCurso.replaceAll(" ", "-") + "_" + nivelCurso + "_" + anoCurso + ".csv";
        try {
            File cursoCsv = new File(nomeArquivo);
            Scanner scanner = new Scanner(cursoCsv);
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] dados = linha.split(",");
                System.out.println("Nome: " + dados[0]);
                System.out.println("NP1: " + dados[1]);
                System.out.println("NP2: " + dados[2]);
                System.out.println("Reposicao: " + dados[3]);
                System.out.println("Exame: " + dados[4]);
                System.out.println();
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("Não foi possível encontrar o arquivo " + nomeArquivo);
        }
    }
}
