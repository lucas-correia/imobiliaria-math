
import fatec.poo.model.Contrato;
import fatec.poo.model.Corretor;
import fatec.poo.model.Proprietario;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Aplic {

    List<Proprietario> proprietarios = new ArrayList<>();
    List<Corretor> corretores = new ArrayList<>();

    public static void main(String[] args) {
        Aplic app = new Aplic();

        app.inicializaPrograma();
        app.exibirMenu();
    }

    private void inicializaPrograma() {

        //cria proprietarios
        proprietarios.add(new Proprietario(111, "Amelia Souza"));
        proprietarios.add(new Proprietario(222, "Antonio Vieira"));
        proprietarios.add(new Proprietario(333, "Diogo Silva"));

        //cria corretor
        System.out.print("Digite a taxa de locação(%): ");
        Scanner entrada = new Scanner(System.in);
        double taxaLocacao=entrada.nextDouble();
        corretores.add(new Corretor(100, "Carlos Silva", taxaLocacao));

        //cria contratos
        corretores.get(0).getContratos().add(new Contrato(1010, 100, 111, 800, "12/03/2021"));
        corretores.get(0).getContratos().add(new Contrato(2020, 100, 222, 1500, "15/03/2021"));
        corretores.get(0).getContratos().add(new Contrato(3030, 100, 333, 600, "20/03/2021"));

        //set total por corretor
        for (Corretor corretor : corretores) {
            for (Contrato contrato : corretor.getContratos()) {
                corretor.setTotalLocacao(corretor.getTotalLocacao()+contrato.getValor());
            }
        }

        //set aluguel
        for (Corretor corretor : corretores) {
            for (Proprietario proprietario : proprietarios) {
                for (Contrato contrato : corretor.getContratos()) {
                    if(contrato.getCodigoProprietario() == proprietario.getCodigo()) {
                        proprietario.setValorAluguel(contrato.getValor()-((corretor.getTaxaLocacao()/100)*contrato.getValor()));
                    }
                }
            }
        }
    }

    private void adicionarContrato() {
        System.out.print("!!! Para adicionar um novo contrato certifique-se de ja ter cadastrado o proprietário e o corretor do contrato !!!\n\n\n");
        Proprietario proprietarioSelecionado = null;
        Corretor corretorSelecionado = null;

        //Seleção do corretor
        System.out.print("Codigo\t\tNome\n");
        for (Corretor corretor : corretores) {
            System.out.print(corretor.getCodigo()+"\t\t"+corretor.getNome()+"\n");
        }
        System.out.print("Entre com o código do corretor de acordo com a lista acima: ");
        Scanner entrada = new Scanner(System.in);
        int codigoCorretor=entrada.nextInt();
        for (Corretor corretor : corretores) {
           if(corretor.getCodigo() == codigoCorretor) {
               corretorSelecionado = corretor;
               break;
           }
        }
        if(corretorSelecionado == null) {
            System.out.print("Corretor não encontrado!!\nFaça o cadastro do corretor no Menu!");
            exibirMenu();
        }


        //Seleção do proprietario
        System.out.print("\n\nCodigo\t\tNome\n");
        for (Proprietario proprietario : proprietarios) {
            if(proprietario.getValorAluguel() == 0) {
                System.out.print(proprietario.getCodigo()+"\t\t"+proprietario.getNome()+"\n");
            }   
        }
        System.out.print("Entre com o código do proprietario de acordo com a lista acima: ");
        entrada = new Scanner(System.in);
        int codigoProprietario=entrada.nextInt();
        for (Proprietario proprietario : proprietarios) {
            if(proprietario.getCodigo() == codigoProprietario) {
                proprietarioSelecionado = proprietario;
                break;
            }   
        }
        if(proprietarioSelecionado == null) {
            System.out.print("Propritario não encontrado!!\nFaça o cadastro do proprietario no Menu!");
            exibirMenu();
        }

        //Dados do contrato
        int numero;
        double valor=0;
        String data="";
        do {
            System.out.print("Digite o numero do contrato: ");
            numero=entrada.nextInt();
            if(numero==0) {
                System.out.print("Numero invalido!! Entre com um valor inteiro maior do que 0!\n");
            }
        } while(numero==0);

        do {
            System.out.print("Digite o valor do contrato: ");
            valor=entrada.nextDouble();
            if(valor==0) {
                System.out.print("Valor invalido!! Entre com um valor numérico maior do que 0!\n");
            }
        } while(valor==0);

        do {
            System.out.print("Digite a data de inicio do contrato (DD/MM/AAAA): ");
            data = entrada.next();
            if(data==""){
                System.out.print("Data invalida!! Data não pode ser vazia");
            }
        } while (data=="");

        for (Corretor corretor : corretores) {
            if(corretor.equals(corretorSelecionado)) {
                corretor.getContratos().add(new Contrato(numero, corretor.getCodigo(), proprietarioSelecionado.getCodigo(), valor, data));
                corretor.setTotalLocacao(corretor.getTotalLocacao()+valor);
                break;
            }
        }
        
        for (Proprietario proprietario : proprietarios) {
            if(proprietario.equals(proprietarioSelecionado)) {
                proprietario.setValorAluguel(valor-((corretorSelecionado.getTaxaLocacao()/100)*valor));
                break;
            }
        }

        System.out.print("\n*** CADASTRO FINALIZADO COM SUCESSO ***\n\n\n");
        exibirMenu();
    }

    private void adicionarCorretor() {
        int codigo = 0;
        String nome = "";
        double taxa = 0.0;
        Scanner entrada = new Scanner(System.in);
        boolean existente = false;
        do {
            System.out.print("Digite o numero do corretor: ");
            codigo=entrada.nextInt();
            for (Corretor corretor : corretores) {
                if(corretor.getCodigo() == codigo) {
                    existente = true;
                }
            }
            if(codigo==0 || existente) {
                System.out.print("Codigo invalido!! Entre com um valor inteiro maior do que 0 e não utilizado!\n");
            }
        } while(codigo==0 || existente);

        do {
            System.out.print("Digite o nome do corretor: ");
            nome = entrada.next();
            if(nome==""){
                System.out.print("Nome invalida!! Nome não pode ser vazia");
            }
        } while (nome=="");

        do {
            System.out.print("Digite a taxa de locação(%): ");
            taxa=entrada.nextDouble();
            if(taxa==0) {
                System.out.print("Taxa invalida!! Entre com um valor numérico maior do que 0!\n");
            }
        } while(taxa==0);

        corretores.add(new Corretor(codigo, nome, taxa));
        System.out.print("\n*** CADASTRO FINALIZADO COM SUCESSO ***\n\n\n");
        exibirMenu();
    }

    private void adicionarProprietario() {
        int codigo = 0;
        String nome = "";
        Scanner entrada = new Scanner(System.in);
        boolean existente = false;
        do {
            System.out.print("Digite o numero do proprietario: ");
            codigo=entrada.nextInt();
            for (Proprietario proprietario : proprietarios) {
                if(proprietario.getCodigo() == codigo) {
                    existente = true;
                }
            }
            if(codigo==0 || existente) {
                System.out.print("Codigo invalido!! Entre com um valor inteiro maior do que 0 e não utilizado!\n");
            }
        } while(codigo==0 || existente);

        do {
            System.out.print("Digite o nome do proprietario: ");
            nome = entrada.next();
            if(nome==""){
                System.out.print("Nome invalido!! Nome não pode ser vazia");
            }
        } while (nome=="");

        proprietarios.add(new Proprietario(codigo, nome));
        System.out.print("\n*** CADASTRO FINALIZADO COM SUCESSO ***\n\n\n");
        exibirMenu();
    }

    private void exibirAlugueis() {
        System.out.print("Codigo\t\tNome\t\tValor Aluguel\n");
        for (Proprietario proprietario : proprietarios) {
            System.out.println(proprietario.getCodigo()+"\t\t"+proprietario.getNome()+"\t"+proprietario.getValorAluguel());
        }
        exibirMenu();
    }

    private void listarContratosDoCorretor() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\ncódigo do corretor: ");
        int codigo = entrada.nextInt();
        for (Corretor corretor : corretores) {
            if(corretor.getCodigo() == codigo) {
                corretor.listarContratos();
                break;
            }
        }
        exibirMenu();
    }

    private void exibirMenu() {
        int opcao;
        
        System.out.println("\n1 Exibir Aluguéis (sem taxa do corretor)");
        System.out.println("2 Listar contratos do corretor");
        System.out.println("----------------------------------");
        System.out.println("3 Cadastrar novo corretor");
        System.out.println("4 Cadastrar novo proprietario");
        System.out.println("5 Cadastrar novo contrato");
        System.out.println("6 Sair");
        System.out.print("\nDigite a opção: ");
        Scanner entrada = new Scanner(System.in);
        opcao=entrada.nextInt();
        
        switch(opcao){
            case 1:
                exibirAlugueis();
                break;
            case 2: 
                listarContratosDoCorretor();
                break;
            case 3: 
                adicionarCorretor();
                break;
            case 4: 
                adicionarProprietario();
                break;
            case 5:
                adicionarContrato();
                break;
            case 6: 
                System.exit(0);
                break;
            
            default:
                System.out.println("Opção inválida!!\n");
                exibirMenu();
                break;
        }
        return;
    }
}
