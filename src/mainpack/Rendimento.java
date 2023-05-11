package mainpack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class Rendimento {
    private double np1;
    private double np2;
    private double reposicao;
    private double exame;
    private double media;
    private boolean aprovado;
    private final String numberFormatErrorMessage = "Número inválido";

    // Construtor do rendimento
    public Rendimento() {
    }

    public double calcularMedia(double np1, double np2, double reposicao) {
        if(np1 < reposicao && np1 < np2) {
            //caso np1 seja a menor nota
            media = (np2 + reposicao) / 2;
        } else if(np2 < reposicao && np2 < np1) {
            //caso np2 seja a menor nota
            media = (np1 + reposicao) / 2;
        } else {
            //caso reposicao seja a menor nota
            media = (np1 + np2) / 2;
        }
        return media;
    }

    public abstract void validarMedia(double media, double exame);

    //cadastra as notas de um aluno em um curso
    public void cadastrarRendimento(Scanner scan, Aluno aluno, String nivelCurso) {
        int idAluno = lerIdAluno(scan);
        if(getAluno(aluno, idAluno)) {
            np1 = lerNotaAluno(scan, "Digite a nota da NP1: ");
            np2 = lerNotaAluno(scan, "Digite a nota da NP2: ");
            reposicao = lerNotaAluno(scan, "Digite a nota da reposição: ");
            media = calcularMedia(np1, np2, reposicao);
            validarMedia(media, lerNotaAluno(scan, "Digite a nota do exame: "));
            String nomeCurso = lerNomeCurso(scan);
            int anoCurso = lerAnoCurso(scan);
            String nomeArquivo = formatarCurso(nomeCurso, nivelCurso, anoCurso);
            escreverRendimentoCsv(nomeArquivo, idAluno);
        }
    }

    //escreve as informações do respectivo rendimento em um arquivo csv correspondente
    private void escreverRendimentoCsv(String nomeArquivo, int idAluno) {
        try {
            if (new File(nomeArquivo).isFile()) {
                FileWriter fr = new FileWriter(nomeArquivo, true);
                BufferedWriter br = new BufferedWriter(fr);
                br.write(idAluno+";"+np1+";"+np2+";"+reposicao+";"+exame+"\n");
                br.close();
            } else {
                System.out.println("Curso não encontrado");
            }
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar o rendimento: " + e.getMessage());
        }
    }

    private int lerIdAluno(Scanner scan) {
        while(true) {
            try {
                System.out.print("Digite o id do aluno: ");
                return Integer.parseInt(scan.nextLine());
            } catch(NumberFormatException e) {
                System.out.println(numberFormatErrorMessage);
            }
        }
    }

    private double lerNotaAluno(Scanner scan, String tituloNota) {
        while(true) {
            try {
                System.out.print(tituloNota);
                double nota = Double.parseDouble(scan.nextLine());
                if(nota >= 0 && nota <= 10) {
                    return nota;
                } else {
                    System.out.println("A nota deve ter um valor entre 0 e 10");
                }
            } catch(NumberFormatException e) {
                System.out.println(numberFormatErrorMessage);
            }
        }
    }

    private String lerNomeCurso(Scanner scan) {
        System.out.print("Digite o nome do curso: ");
        return scan.nextLine();
    }

    private int lerAnoCurso(Scanner scan) {
        while(true) {
            try {
                System.out.println("Digite o ano do curso: ");
                return Integer.parseInt(scan.nextLine());
            } catch(NumberFormatException e) {
                System.out.println(numberFormatErrorMessage);
            }
        }
    }

    //formata uma 'string' para acessar o arquivo csv do respectivo curso
    private String formatarCurso(String nomeCurso, String cursoNivel, int anoCurso) {
        if(cursoNivel.equals("1")) {
            cursoNivel = "GRADUACAO";
        } else {
            cursoNivel = "POS-GRADUACAO";
        }
        String nomeArquivo = "./"+nomeCurso+"_"+cursoNivel+"_"+anoCurso;
        nomeArquivo = formatarRegexArquivo(nomeArquivo);
        System.out.println(nomeArquivo);
        return nomeArquivo;
    }

    private String formatarRegexArquivo(String nomeArquivo) {
        nomeArquivo = nomeArquivo.toUpperCase();
        //substituição de caracteres especiais
        nomeArquivo = nomeArquivo.replaceAll(" ", "-");
        nomeArquivo = nomeArquivo.replaceAll("Ç", "C");
        nomeArquivo = nomeArquivo.replaceAll("Á", "A");
        nomeArquivo = nomeArquivo.replaceAll("Ã", "A");
        nomeArquivo = nomeArquivo.replaceAll("Í", "I");
        nomeArquivo = nomeArquivo.replaceAll("Ê", "E");
        nomeArquivo = nomeArquivo.replaceAll("É", "E");
        nomeArquivo = nomeArquivo.replaceAll("Ô", "O");
        nomeArquivo = nomeArquivo.replaceAll("Ú", "U");
        //transforma em uppercase e adiciona a extensão .csv
        String csvExtensao = ".csv";
        nomeArquivo = nomeArquivo.toUpperCase() + csvExtensao;
        return nomeArquivo;
    }

    public boolean getAluno(Aluno aluno, int idAluno) {
        //faz referência ao HashMap 'alunos' da classe 'Aluno'
        HashMap alunos = aluno.getAlunosHashMap();
        //verifica se o HashMap possui a chave com o valor de 'idAluno'
        if(alunos.containsKey(idAluno)) {
            System.out.println("nome: " + alunos.get(idAluno));
            return true;
        } else {
            System.out.println("\nAluno não encontrado\n");
            return false;
        }
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    //Exibe alunos de um curso específico, mostrando os seus 'ids', médias
    //(considerando graduação ou pós-graduação) e se estão aprovados ou não
    public void exibirRendimento(Scanner scan, String nivelCurso) {
        String nomeCurso = lerNomeCurso(scan);
        int anoCurso = lerAnoCurso(scan);
        String nomeArquivo = formatarCurso(nomeCurso, nivelCurso, anoCurso);
        lerRendimentoCsv(nomeArquivo);
    }

    //acessa as informações de um csv de rendimento
    public void lerRendimentoCsv(String nomeArquivo) {
        try {
            if (new File(nomeArquivo).isFile()) {
                Scanner scanArquivo = new Scanner(new File(nomeArquivo));
                while(scanArquivo.hasNextLine()) {
                    String linha = scanArquivo.nextLine();
                    String[] dados = linha.split(";");
                    int idAluno = Integer.parseInt(dados[0]);
                    np1 = Double.parseDouble(dados[1]);
                    np2 = Double.parseDouble(dados[2]);
                    reposicao = Double.parseDouble(dados[3]);
                    exame = Double.parseDouble(dados[4]);
                    media = calcularMedia(np1, np2, reposicao);
                    validarMedia(media, exame);
                    System.out.println("id: " + idAluno + " | media: " + media + " | aprovado: " + aprovado);
                }
                scanArquivo.close();
            } else {
                System.out.println("Curso não encontrado");
            }
        } catch (IOException e) {
            System.out.println("Erro ao exibir o rendimento: " + e.getMessage());
        }
    }

    // Mostra na tela a média geral de um curso específico
    public void exibirMediaGeral(Scanner scan, String nivelCurso) {
        String nomeCurso = lerNomeCurso(scan);
        int anoCurso = lerAnoCurso(scan);
        String nomeArquivo = formatarCurso(nomeCurso, nivelCurso, anoCurso);
        lerMediaCsv(nomeArquivo);
    }

    //lê todas as notas de todos os alunos num curso específico
    public void lerMediaCsv(String nomeArquivo) {
        try {
            if (new File(nomeArquivo).isFile()) {
                Scanner scanArquivo = new Scanner(new File(nomeArquivo));
                double mediaGeral = 0;
                int cont = 0;
                while(scanArquivo.hasNextLine()) {
                    String linha = scanArquivo.nextLine();
                    String[] dados = linha.split(";");
                    np1 = Double.parseDouble(dados[1]);
                    np2 = Double.parseDouble(dados[2]);
                    reposicao = Double.parseDouble(dados[3]);
                    exame = Double.parseDouble(dados[4]);
                    media = calcularMedia(np1, np2, reposicao);
                    validarMedia(media, exame);
                    mediaGeral += media;
                    cont++;
                }
                scanArquivo.close();
                mediaGeral = mediaGeral/cont;
                System.out.println("media geral: " + mediaGeral);
            } else {
                System.out.println("Curso não encontrado");
            }
        } catch (IOException e) {
            System.out.println("Erro ao exibir a média geral: " + e.getMessage());
        }
    }

    //Exibe as notas, média e estado de aprovação de um aluno em todos os cursos que ele está
    public void exibirRendimentoAluno(Scanner scan) {
        int idAluno = lerIdAluno(scan);
        lerRendimentoAlunoCsv(idAluno);
    }

    //percorre todos os arquivos csv de rendimento procurando os dados do aluno especificado
    private void lerRendimentoAlunoCsv(int idAluno) {
        try {
            for (File file : Objects.requireNonNull(new File(".").listFiles())) {
                if (file.getName().endsWith(".csv") &&
                        !file.getName().equals("alunos.csv") &&
                        !file.getName().equals("cursos.csv")) {
                    String nomeArquivo = file.getName();
                    Scanner scanArquivo = new Scanner(file);
                    while(scanArquivo.hasNextLine()) {
                        String linha = scanArquivo.nextLine();
                        String[] dados = linha.split(";");
                        int idAlunoArquivo;
                        try {
                            idAlunoArquivo = Integer.parseInt(dados[0]);
                        } catch (NumberFormatException e) {
                            System.out.println("Erro ao converter a string em número inteiro: " + e.getMessage());
                            System.out.println("String com erro: " + linha);
                            continue; // pula para a próxima iteração
                        }
                        if(idAluno == idAlunoArquivo) {
                            np1 = Double.parseDouble(dados[1]);
                            np2 = Double.parseDouble(dados[2]);
                            reposicao = Double.parseDouble(dados[3]);
                            exame = Double.parseDouble(dados[4]);
                            media = calcularMedia(np1, np2, reposicao);
                            validarMedia(media, exame);
                            System.out.println("id: "+idAluno+" | media: "+media+" | aprovado: "
                                    +aprovado+" | curso: "+nomeArquivo);
                        }
                    }
                    scanArquivo.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao exibir o rendimento do aluno: " + e.getMessage());
        }
    }

    // Pega todas as médias de um aluno, em todos os cursos, anos e níveis, e mostra a média das médias
    public void exibirMediaGeralAluno(Scanner scan) {
        int idAluno = lerIdAluno(scan);
        List<Double> notas = new ArrayList<>();
        try {
            //percorre todos os arquivos csv
            for (File file : new File(".").listFiles()) {
                if (file.getName().endsWith(".csv") && 
                    !file.getName().equals("alunos.csv") && 
                    !file.getName().equals("cursos.csv")) {
                    Scanner scanArquivo = new Scanner(file);
                    while(scanArquivo.hasNextLine()) {
                        String linha = scanArquivo.nextLine();
                        String[] dados = linha.split(";");
                        int idAlunoArquivo;
                        try {
                            idAlunoArquivo = Integer.parseInt(dados[0]);
                        } catch (NumberFormatException e) {
                            System.out.println("Erro ao converter a string em número inteiro: " + e.getMessage());
                            System.out.println("String com erro: " + linha);
                            continue; // pula para a próxima iteração
                        }
                        if(idAluno == idAlunoArquivo) {
                            double np1 = Double.parseDouble(dados[1]);
                            double np2 = Double.parseDouble(dados[2]);
                            double reposicao = Double.parseDouble(dados[3]);
                            double exame = Double.parseDouble(dados[4]);
                            double media = calcularMedia(np1, np2, reposicao);
                            validarMedia(media, exame);
                            notas.add(media);
                        }
                    }
                    scanArquivo.close();
                }
            }
            double mediaGeral = calcularMediaGeral(notas);
            System.out.println("media geral: " + mediaGeral);
        } catch (IOException e) {
            System.out.println("Erro ao exibir a média geral do aluno: " + e.getMessage());
        }
    }

    //calcula a média das notas de um aluno em todos os cursos
    private double calcularMediaGeral(List<Double> notas) {
        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }
        return soma / notas.size();
    }
}