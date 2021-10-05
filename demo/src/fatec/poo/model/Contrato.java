
package fatec.poo.model;

/**
 *
 * @author Matheus
 */
public class Contrato {
    private int numero;
    private String data;
    private double valor;
    private int codigoCorretor;
    private int codigoProprietario;

    public Contrato(int numero, int codigoCorretor, int codigoProprietario, double valor, String data) {
        this.numero = numero;
        this.codigoCorretor = codigoCorretor;
        this.codigoProprietario = codigoProprietario;
        this.valor = valor;
        this.data = data;
    }

    public int getCodigoProprietario() {
        return codigoProprietario;
    }

    public void setCodigoProprietario(int codigoProprietario) {
        this.codigoProprietario = codigoProprietario;
    }

    public String getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getNumero() {
        return numero;
    }

    public int getCodigoCorretor() {
        return codigoCorretor;
    }

    public void setCodigoCorretor(int codigoCorretor) {
        this.codigoCorretor = codigoCorretor;
    }
    
    
}
