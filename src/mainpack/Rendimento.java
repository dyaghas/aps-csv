package mainpack;

public abstract class Rendimento {
    private double NP1;
    private double NP2;
    private double reposicao;
    private double exame;
    private double media;
    private boolean aprovado;

    // Construtor do rendimento
    public Rendimento() {

    }

    public Rendimento(double NP1, double NP2, double reposicao, double exame, double media, boolean aprovado) {
        this.NP1 = NP1;
        this.NP2 = NP2;
        this.reposicao = reposicao;
        this.exame = exame;
        this.media = media;
        this.aprovado = aprovado;
    }

    public double calcularMedia(double np1, double np2, double nRepo) {
        double res = 0;
        if(np1 < nRepo && np1 < np2) {
            //caso np1 seja a menor nota
            res = (np2 + nRepo) / 2;
        } else if(np2 < nRepo && np2 < np1) {
            //caso np2 seja a menor nota
            res = (np1 + nRepo) / 2;
        } else {
            //caso nRepo seja a menor nota
            res = (np1 + np2) / 2;
        }
        return res;
    }

    // Getters e setters
    public double getNP1() {
        return NP1;
    }

    public void setNP1(double NP1) {
        this.NP1 = NP1;
    }

    public double getNP2() {
        return NP2;
    }

    public void setNP2(double NP2) {
        this.NP2 = NP2;
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

    // Calcula a média do aluno
    public double calcularMedia() {
        return (NP1 + NP2) / 2;
    }
}
