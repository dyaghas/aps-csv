package mainpack;

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

    public void calcularMedia(double np1, double np2, double nRepo) {
        if(np1 < nRepo && np1 < np2) {
            //caso np1 seja a menor nota
            media = (np2 + nRepo) / 2;
        } else if(np2 < nRepo && np2 < np1) {
            //caso np2 seja a menor nota
            media = (np1 + nRepo) / 2;
        } else {
            //caso nRepo seja a menor nota
            media = (np1 + np2) / 2;
        }
    }

    public abstract void validarMedia(double media);

    // Getters e setters
    public double getNP1() {
        return np1;
    }

    public void setNP1(double np2) {
        this.np1 = np2;
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