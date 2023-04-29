package mainpack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Rendimento {
    private double np1;
    private double np2;
    private double reposicao;
    private double exame;
    private double media;
    private boolean aprovado;

    // Construtor do rendimento
    public Rendimento() {
    }

    public void calcularMedia(double np1, double np2, double reposicao) {
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
        validarMedia(media);
    }

    public void cadastrarRendimento(Scanner scan, Aluno aluno, Curso curso, String nivelCurso) {
        int idAluno = lerIdAluno(scan);
        scan.nextLine();
        getAluno(aluno, idAluno);
        System.out.println("Digite a nota da NP1: ");
        np1 = lerNotaAluno(scan);
        System.out.println("Digite a nota da NP2: ");
        np2 = lerNotaAluno(scan);
        System.out.println("Digite a nota da reposição: ");
        reposicao = lerNotaAluno(scan);
        calcularMedia(np1, np2, reposicao);
        String nomeCurso = lerNomeCurso(scan);
        int anoCurso = lerAnoCurso(scan);
        String nomeArquivo = formatarCurso(nomeCurso, nivelCurso, anoCurso);
        try {
            FileWriter fr = new FileWriter(nomeArquivo, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(idAluno+","+np1+","+np2+","+reposicao+","+exame);
            br.close();
        } catch(IOException e) {
            System.out.println("Erro ao cadastrar o rendimento: " + e.getMessage());
        }
    }

    private int lerIdAluno(Scanner scan) {
        System.out.print("Digite o id do aluno: ");
        return scan.nextInt();
    }

    private double lerNotaAluno(Scanner scan) {
        double nota = scan.nextDouble();
        scan.nextLine();
        return nota;
    }

    private String lerNomeCurso(Scanner scan) {
        System.out.print("Digite o nome do curso: ");
        return scan.nextLine();
    }

    private int lerAnoCurso(Scanner scan) {
        System.out.print("Digite o ano do curso: ");
        return scan.nextInt();
    }

    //formata uma string para acessar o arquivo csv do respectivo curso
    private String formatarCurso(String nomeCurso, String cursoNivel, int anoCurso) {
        if(cursoNivel.equals("1")) {
            cursoNivel = "GRADUACAO";
        } else {
            cursoNivel = "POS-GRADUACAO";
        }
        String nomeArquivo = "./"+nomeCurso+"_"+cursoNivel+"_"+anoCurso;
        nomeArquivo = nomeArquivo.replaceAll(" ", "-");
        //transforma em uppercase e adiciona a extensão .csv
        nomeArquivo = nomeArquivo.toUpperCase() + ".csv";
        System.out.println(nomeArquivo);
        return nomeArquivo;
    }

    public abstract void validarMedia(double media);

    // Getters e setters
    public void getAluno(Aluno aluno, int idAluno) {
        //faz referência ao HashMap 'alunos' da classe 'Aluno'
        HashMap alunos = aluno.getAlunosHashMap();
        //verifica se o HashMap possui a chave com o valor de 'idAluno'
        if(alunos.containsKey(idAluno)) {
            System.out.println(alunos.get(idAluno));
        } else {
            System.out.println("Aluno não encontrado\n");
        }
    }

    public double lerExame() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite a nota do exame: ");
        exame = scan.nextDouble();
        return exame;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
        if(aprovado) {
            System.out.println("O aluno está aprovado");
        } else {
            System.out.println("O aluno não está aprovado");
        }
    }
}