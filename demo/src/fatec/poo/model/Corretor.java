
package fatec.poo.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class Corretor extends Pessoa{
    private double taxaLocacao;
    private double totalLocacao;
    private List<Contrato> contratos;
    
    public Corretor(int codigo, String nome, double taxaLocacao) {
        super(codigo, nome);
        this.taxaLocacao = taxaLocacao;
        this.contratos = new ArrayList<>();
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public double getTaxaLocacao() {
        return taxaLocacao;
    }

    public void setTotalLocacao(double totalLocacao) {
        this.totalLocacao = totalLocacao;
    }

    public double getTotalLocacao() {
        return totalLocacao;
    }
    
    public void listarContratos(){
        System.out.println("\n\nCódigo: " + this.getCodigo());
        System.out.println("Nome: " + this.getNome());
        System.out.println("Taxa: " + this.getTaxaLocacao()+"%");
        System.out.println("Total locação: " + this.getTotalLocacao());
        System.out.print("\nNúmero contrato\t\tValor");
        double comissao = 0.0;
        for (Contrato contrato : contratos) {
            comissao+=((this.getTaxaLocacao())/100)*contrato.getValor(); 
            System.out.print("\n"+contrato.getNumero()+"\t\t\t"+contrato.getValor()); 
        }
        System.out.println("\nTotal comissão: "+comissao);
    }  
}
