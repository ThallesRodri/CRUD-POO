package morador;

public class AgendaMorador {
    private String nome;
    private Integer bloco;
    private Integer numApto;
    private String dia;

    public AgendaMorador(String nome, Integer bloco, Integer numApto, String dia) {
        this.nome = nome;
        this.bloco = bloco;
        this.numApto = numApto;
        this.dia = dia;
    }

    /* GETTERS SETTERS */

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getBloco() {
        return bloco;
    }

    public void setBloco(Integer bloco) {
        this.bloco = bloco;
    }

    public Integer getNumApto() {
        return numApto;
    }

    public void setNumApto(Integer numApto) {
        this.numApto = numApto;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
