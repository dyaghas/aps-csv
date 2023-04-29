package mainpack;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
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

    public Rendimento(double np1, double np2, double reposicao, double exame, double media, boolean aprovado) {
        this.np1 = np1;
        this.np2 = np2;
        this.reposicao = reposicao;
        this.exame = exame;
        this.media = media;
        this.aprovado = aprovado;
    }

    public void calcularMedia(double np1, double np2, double reposicao) {
        if(np1 < reposicao && np1 < np2) {
            //caso np1 seja a menor nota
            media = (np2 + reposicao) / 2;
        } else if(np2 < reposicao && np2 < np1) {
            //caso np2 seja a menor nota
            media = (np1 + reposicao) / 2;
        } else {
            //caso nRepo seja a menor nota
            media = (np1 + np2) / 2;
        }
        validarMedia(media);
    }

    public void cadastrarRendimento(Scanner scan, Aluno aluno, Curso curso, String nivelCurso) {
        int idAluno = lerIdAluno(scan);
        scan.nextLine();
        String nomeAluno = getNomeAluno(aluno, idAluno);
        String nomeCurso = lerNomeCurso(scan);
        int anoCurso = lerAnoCurso(scan);
        String nomeArquivo = formatarCurso(nomeCurso, nivelCurso, anoCurso);
        try {
            FileWriter fr = new FileWriter(nomeArquivo, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(idAluno+",");
            br.close();
        } catch(IOException e) {
            System.out.println("Erro ao cadastrar o rendimento: " + e.getMessage());
        }
    }

    private int lerIdAluno(Scanner scan) {
        System.out.print("Digite o id do aluno: ");
        return scan.nextInt();
    }

    private String lerNomeCurso(Scanner scan) {
        System.out.print("Digite o nome do curso:");
        return scan.nextLine();
    }

    private int lerAnoCurso(Scanner scan) {
        System.out.print("Digite o ano do curso: ");
        return scan.nextInt();
    }

    //formata uma string para acessar o arquivo csv do respectivo curso
    private String formatarCurso(String nomeCurso, String cursoNivel, int anoCurso) {
        if(cursoNivel == "1") {
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
    public String getNomeAluno(Aluno aluno, int idAluno) {
        //faz referência ao HashMap 'alunos' da classe 'Aluno'
        HashMap alunos = aluno.getAlunosHashMap();
        //verifica se o HashMap possui a chave com o valor de 'idAluno'
        if(alunos.containsKey(idAluno)) {
            System.out.println(alunos.get(idAluno));
            return (String) alunos.get(idAluno);
        } else {
            System.out.println("Aluno não encontrado\n");
            return null;
        }
    }

    public double getNP1() {
        return np1;
    }

    public void setNP1(double np1) {
        this.np1 = np1;
    }

    public double getNP2() {
        return np2;
    }

    public void setNP2(double np2) {
        this.np2 = np2;
    }

    public double getReposicao() {
        return reposicao;
    }

    public void setReposicao(double reposicao) {
        this.reposicao = reposicao;
    }

    public double getExame() {
        return exame;
    }

    public void setExame(double exame) {
        this.exame = exame;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public boolean getAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }
}