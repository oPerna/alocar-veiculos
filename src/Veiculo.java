import java.io.Serializable;

public class Veiculo implements Serializable {
    
    private String placa;
    private String modelo;
    private int ano;
    private String cor;
    private long quilometragem;
    
    public Veiculo(String placa, String modelo, int ano, String cor, long quilometragem) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.quilometragem = quilometragem;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public long getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(long quilometragem) {
        this.quilometragem = quilometragem;
    }

    public void printFormatado() {
        System.out.printf("| %10s | %-12s | %-5d | %-15s | %-13s |\n", getPlaca(), getModelo(), getAno(), getCor(), getQuilometragem() + "km");
    }
}
