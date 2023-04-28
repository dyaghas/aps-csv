package mainpack;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Curso implements CsvInterface {
    private String nome;
    private String nivel;
    private int ano;
    private File cursoCsv = new File("./cursos.csv");

    //hashmap que guarda as linhas do arquivo cursos.csv
    ArrayList<String> cursos = new ArrayList<>();

    public Curso() {
        lerCsv();
        System.out.println(cursos);
    }

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

    private void processaLinhaCsv(String linha) {
        String[] parte = linha.split(",");
        String nome = parte[0];
        String nivel = parte[1];
        int ano = Integer.parseInt(parte[2]);
        cursos.add(nome+"/"+nivel+"/"+ano);
    }

    // Cadastra o curso em "cursos.csv"
    public void cadastrarDadoCsv(Scanner scan) {
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

        try {
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
        } catch(IOException e) {
            System.out.println("arquivo não encontrado");
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
    public void listarCsv() {
        //try catch muito abrangente
        try {
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
        } catch(IOException e) {
            System.out.println("Ocorreu um erro");
        }
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

    //getters e setters
    public String getNome() {
        return nome;
    }

    public String getNivel() {
        return nivel;
    }

    public int getAno() {
        return ano;
    }

    public File getCursoCsv() {
        return this.cursoCsv;
    }
}
