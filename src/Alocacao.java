public class Alocacao {
    Veiculo veiculo;
    String nomeCliente;
    //Rebanho de corno

    public Alocacao(Veiculo veiculo, String nomeCliente) {
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

    
}
