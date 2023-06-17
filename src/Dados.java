import java.io.Serializable;
import java.util.List;

public class Dados implements Serializable {
    private List<Veiculo> veiculosRegistrados;
    private List<Locacao> veiculosLocados;

    public Dados(List<Veiculo> veiculosRegistrados, List<Locacao> veiculosLocados) {
        this.veiculosRegistrados = veiculosRegistrados;
        this.veiculosLocados = veiculosLocados;
    }

    public List<Veiculo> getVeiculosRegistrados() {
        return veiculosRegistrados;
    }

    public void setVeiculosRegistrados(List<Veiculo> veiculosRegistrados) {
        this.veiculosRegistrados = veiculosRegistrados;
    }

    public List<Locacao> getVeiculosLocados() {
        return veiculosLocados;
    }

    public void setVeiculosLocados(List<Locacao> veiculosLocados) {
        this.veiculosLocados = veiculosLocados;
    }

}
