import java.io.Serializable;

public class Locacao implements Serializable {
    private Veiculo veiculo;
    private String nomeCliente;

    public Locacao(Veiculo veiculo, String nomeCliente) {
        this.veiculo = veiculo;
        this.nomeCliente = nomeCliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }
    
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void printFormatado() {
        System.out.printf("| %20s | %-10s | %-12s | %-5d | %-15s | %-13s |\n", getNomeCliente(), veiculo.getPlaca(), veiculo.getModelo(), veiculo.getAno(), veiculo.getCor(), veiculo.getQuilometragem() + "km");
    }

}
