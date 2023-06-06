import java.io.Serializable;

public class Veiculo implements Serializable {
    
    private String placa;
    private String modelo;
    private int ano;
    private String cor;
    private int quilometragem;
    
    
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
    
    public int getQuilometragem() {
        return quilometragem;
    }
    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    
}