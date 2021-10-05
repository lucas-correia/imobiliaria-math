
package fatec.poo.model;

/**
 *
 * @author Matheus
 */
public class Proprietario extends Pessoa{
    private double valorAluguel;

    public Proprietario(int codigo, String nome) {
        super(codigo, nome);
    }

    public double getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }
}
