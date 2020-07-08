package Veiculo;
import Produto.Produto;
import Produto.ArquivoProduto;
import Venda.ArquivoVenda;
import Venda.Venda;
import Cliente.Cliente;
import Cliente.ArquivoCliente;
import Categoria.Categoria;
import Categoria.ArquivoCategoria;
import java.io.*;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Caio
 */


public class CRUD {
        private static ArquivoVeiculo<Veiculo> arqVeiculos;
        private static ArquivoCategoria<Categoria> arqCategorias;
        private static ArquivoCliente<Cliente> arqClientes;
        private static ArquivoVenda<Venda> arqVendas;
        private static ArquivoProduto<Produto> arqItemComprado;
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {

        File f;
        
        try {
            f = new File("veiculos.db");
            String str;
            arqVeiculos = new ArquivoVeiculo<>(Veiculo.class.getConstructor(), "veiculos.db", "veiculos.idx");
            arqCategorias = new ArquivoCategoria<>(Categoria.class.getConstructor(), "categorias.db", "categorias.idx");
            arqClientes = new ArquivoCliente<>(Cliente.class.getConstructor(), "clientes.db", "clientes.idx");
            arqVendas = new ArquivoVenda<>(Venda.class.getConstructor(), "vendas.db", "vendas.idx");
            arqItemComprado = new ArquivoProduto<>(Produto.class.getConstructor(), "produto.db", "produto.idx1", "produto.idx2", "produto.idx3");
            int menu = 1;
            while(menu != 0) {
                System.out.println("******************** CRUD CONCESSIONÁRIA DE VEÍCULOS ********************\n\nSelecione uma das opções abaixo:"+"\n1)Veículos"+"\n2)Categorias"+"\n3)Clientes"+
                               "\n4)Vendas"+"\n5)Visualizar resultados"+"\n0)Finalizar programa");
                menu = scan.nextInt();
                scan.nextLine();
                switch (menu) {
                    case 1:
                        menuVeiculo();
                        break;
                    case 2:
                        menuCategoria();
                        break;
                    case 3:
                        menuCliente();
                        break;
                    case 4:
                        menuVenda();
                        break;
                    case 5:
                        menuResultado();
                        break;    
                    case 0:
		       System.out.println("\nFim!");
		       break;
		     default:
		       System.out.println("\nOpção inválida!\n");
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
}

    public static void menuVeiculo()throws Exception{
            int opcao = 1;
	    while (opcao != 0){
                System.out.println("\n\nVeículos:"+"\n1)Incluir"+"\n2)Buscar"+"\n3)Excluir"+
                                "\n4)Editar"+"\n5)Listar"+"\n0)Voltar para o menu principal");
                opcao = scan.nextInt();
                scan.nextLine();
	        switch (opcao) {
	 	    case 1:
		       incluirVeiculo();
		       break;
		     case 2:
		       buscarVeiculo();
		       break;
		     case 3:
		       excluirVeiculo();
		       break;
		     case 4:
		       editarVeiculo();
		       break;
                     case 5:
		       listarVeiculo();
		       break;
		     case 0:
		       System.out.println("\n\n");
		       break;
		     default:
		       System.out.println("\nOpção inválida!\n");
		}
            } 		

    }
    
    public static void menuCategoria()throws Exception{    
            int opcao = 1;
	    while (opcao != 0){
                System.out.println("\n\nCategorias:"+"\n1)Incluir"+"\n2)Buscar"+"\n3)Excluir"+
                                "\n4)Editar"+"\n5)Listar"+"\n0)Voltar para o menu principal");
                opcao = scan.nextInt();
	        switch (opcao) {
	 	    case 1:
		       incluirCategoria();
		       break;
		     case 2:
		       buscarCategoria();
		       break;
		     case 3:
		       excluirCategoria();
		       break;
		     case 4:
                         editarCategoria();
		       break;
                     case 5:
                         listarCategoria();
                         break;
		     case 0:
		       System.out.println("\n\n");
		       break;
		     default:
		       System.out.println("\nOpção inválida!\n");
		}
            } 		

    }
    
    public static void menuCliente()throws Exception{    
            int opcao = 1;
	    while (opcao != 0){
                System.out.println("\n\nClientes:"+"\n1)Incluir"+"\n2)Buscar"+"\n3)Excluir"+
                                "\n4)Editar"+"\n5)Listar"+"\n0)Voltar para o menu principal");
                opcao = scan.nextInt();
	        switch (opcao) {
	 	    case 1:
		       incluirCliente();
		       break;
		     case 2:
		       buscarCliente();
		       break;
		     case 3:
		       excluirCliente();
		       break;
		     case 4:
		       editarCliente();
		       break;
                     case 5:
		       listarCliente();
		       break;
		     case 0:
		       System.out.println("\n\n");
		       break;
		     default:
		       System.out.println("\nOpção inválida!\n");
		}
            } 		

    }
    
    public static void menuVenda()throws Exception{    
            int opcao = 1;
	    while (opcao != 0){
                System.out.println("\n\nVendas:"+"\n1)Incluir"+"\n2)Buscar"+"\n3)Excluir"+
                                "\n4)Listar"+"\n0)Voltar para o menu principal");
                opcao = scan.nextInt();
	        switch (opcao) {
	 	    case 1:
		       incluirVenda();
		       break;
		    case 2:
		       buscarVenda();
		       break;
		    case 3:
		       excluirVenda();
		       break;
                    case 4:
		       listarVenda();
		       break;
		    case 0:
		       System.out.println("\n\n");
		       break;
		     default:
		       System.out.println("\nOpção inválida!\n");
		}
            } 		

    }
    
    public static void menuResultado()throws Exception{    
            int opcao = 1;
	    while (opcao != 0){
                System.out.println("\n\nResultados:"+"\n1)Listar veículos comprados por Cliente"+"\n2)Clientes que compraram um veículo"+
                    "\n3)Valor total de vendas"+"\n0)Voltar para o menu principal");
                opcao = scan.nextInt();
	        switch (opcao) {
	 	    case 1:
		       listarProdutosClientes();
		       break;
		     case 2:
		       listarClientesProdutos();
		       break;
                     case 3:
		       quantidadeDeVendas();
		       break;
		     case 0:
		       System.out.println("\n\n");
		       break;
		     default:
		       System.out.println("\nOpção inválida!\n");
		}
            } 		

    } 

    public static void incluirVeiculo()throws Exception{
        char resp;
        boolean condicao=true;
	System.out.println("\n\nCadastro de veículo:");
	Veiculo novo = new Veiculo();
        System.out.print("ID Categoria: ");
        novo.idCategoria = scan.nextInt();
	scan.nextLine();
        if(arqCategorias.busca(novo.idCategoria) != null){
            System.out.println(arqCategorias.nomeCategoria(novo.idCategoria));
        }else{
            System.out.println("\nCategoria inexistente, por favor crie uma nova categoria para este veículo."
                    + "\n\nDeseja criar uma nova categoria para este veículo? (s/n)");
            resp=scan.next().charAt(0);
            if(resp=='s'||resp=='S'){
                novo.idCategoria = incluirCategoria();
                System.out.println("\n\nCadastro de veículo:");
            }
            else{
                condicao=false;
            }
        }
        if(condicao){
            System.out.print("\nMarca: ");
            novo.marca = scan.nextLine();
            System.out.print("Modelo: ");
            novo.modelo = scan.nextLine();
            System.out.print("Cor: ");
            novo.cor = scan.nextLine();
            System.out.print("Descrição: ");
            novo.descricao = scan.nextLine();
            System.out.print("Preço: ");
            novo.preco = scan.nextFloat();
            System.out.print("Quantidade em estoque: ");
            novo.quantidade = scan.nextInt();
            arqVeiculos.incluir(novo);
            System.out.println("\nIncluiu veículo no ID: "+novo.getId());
        }
    }

    public static void buscarVeiculo()throws Exception{
        byte[] b;
	System.out.println("\n\nBusca:");
        System.out.print("Id do veículo a ser buscado: ");
        int id = scan.nextInt();
        if(arqVeiculos.busca(id) != null){
        System.out.println("\nCategoria: "+ arqCategorias.busca(arqVeiculos.busca(id).getIdCategoria()).getNomeCategoria()+
               "\nId: "+ arqVeiculos.busca(id).getId()+
               "\nMarca: " + arqVeiculos.busca(id).getMarca() +
                "\nModelo: " + arqVeiculos.busca(id).getModelo() +
               "\nCor: " + arqVeiculos.busca(id).getCor() +
               "\nDescrição: " + arqVeiculos.busca(id).getDescricao() +
               "\nPreço: R$" + arqVeiculos.busca(id).getPreco() +
                "\nQuantidade em estoque: " + arqVeiculos.busca(id).getQuantidade() +"\n");
        
        }else{
            System.out.println("\nVeículo não encontrado!");
        }
    }

    public static void excluirVeiculo()throws Exception{
        boolean conf = false;
        System.out.println("\n\nExcluir:");
        System.out.print("Id do veículo a ser excluido: ");	
        int id = scan.nextInt();
        if(arqVeiculos.buscaQuantidade(id) != 0){
            System.out.println("Não foi possível excluir este veículo. Pois há veículos em estoque");
            return;
        }
        System.out.println("Deseja realmente excluir este veículo? (S/N)");
        char resp = scan.next().charAt(0);
	while(resp != 'S' && resp != 'N' && resp != 's' && resp != 'n'){
	    System.out.println("\nResposta inválida!");
            resp = scan.next().charAt(0); 
        }
        if(resp == 's' || resp == 'S'){
            conf = arqVeiculos.excluir(id);
        }
	if(conf){ 
            System.out.println("\nVeículo excluido!");
	 }else{ 
            System.out.println("O veículo não foi excluido!"); }
    }

    public static void editarVeiculo()throws Exception{
        System.out.println("\n\nEditar Veículo:");
        int id;
        boolean condicao = true;
        char resp;
        System.out.print("\nID do veículo a ser alterado: ");
        id = scan.nextInt();
        scan.nextLine();
        Veiculo novo = new Veiculo();
        if((novo = (Veiculo)arqVeiculos.busca(id)) != null ){
            int idCategoria, quantidade;
            String marca, modelo, cor, descricao;
            float  preco;
            System.out.print("\nNova categoria(ID): ");
            idCategoria = scan.nextInt();
            scan.nextLine();
            if(arqCategorias.busca(idCategoria) == null){
                System.out.println("\nCategoria inexistente, por favor crie uma nova categoria para este veículo."
                    + "\n\nDeseja criar uma nova categoria para este veículo? (s/n)");
                resp=scan.next().charAt(0);
                if(resp=='s'||resp=='S'){
                    novo.idCategoria = incluirCategoria();
                    System.out.println("\n\nEditar Veículo:");
                }
                else{
                    condicao=false;
                }
            }
            if(condicao){
                System.out.print("Nova marca: ");
                marca = scan.nextLine();
                System.out.print("Novo modelo: ");
                modelo = scan.nextLine();
                System.out.print("Nova cor: ");
                cor = scan.nextLine();
                System.out.print("Nova descrição: ");
                descricao = scan.nextLine();
                System.out.print("Novo preço: ");
                preco = scan.nextFloat();
                System.out.print("Nova quantidade em estoque: ");
                quantidade = scan.nextInt();
                System.out.println("Confirmar alteração? (s/n)");
                char conf = scan.next().charAt(0);
                if(conf =='s' || conf =='S'){
                    novo.idProduto = id;
                    novo.idCategoria = idCategoria;
                    novo.marca = marca;
                    novo.modelo = modelo;
                    novo.cor = cor;
                    novo.descricao = descricao;
                    novo.preco = preco;
                    novo.quantidade = quantidade;
                    if(arqVeiculos.alteracao(novo)){ 
                        System.out.println("\n\nVeículo alterado.");
                    }else
                        System.out.println("\n\nVeículo não pôde ser alterado.");
           
                }
            }
        }
    }

    public static void listarVeiculo()throws Exception{ 
        Object[] obj = arqVeiculos.listar();
        System.out.println("\n\nListar Veículos: ");
        for(int i = 0; i < obj.length;i++){
            System.out.println("Categoria: "+ arqCategorias.busca(((Veiculo)obj[i]).getIdCategoria()).getNomeCategoria()+
               "\nId: "+ ((Veiculo)obj[i]).getId()+
               "\nMarca: " + ((Veiculo)obj[i]).getMarca() +
               "\nModelo: " + ((Veiculo)obj[i]).getModelo() +
               "\nCor: " + ((Veiculo)obj[i]).getCor() +
               "\nDescrição: " + ((Veiculo)obj[i]).getDescricao() +    
               "\nPreço: R$" + ((Veiculo)obj[i]).getPreco() + 
               "\nQuantidade em estoque: " + ((Veiculo)obj[i]).getQuantidade() + "\n");
        }
    }
    
    public static int incluirCategoria()throws Exception{
	System.out.println("\n\nIncluir Categoria:");
	Categoria nova = new Categoria();
        scan.nextLine();
        System.out.print("\nNome da categoria: ");
        nova.nomeCategoria = scan.nextLine();
        arqCategorias.incluir(nova);
        System.out.println("Categoria criada com ID: "+nova.getIdCategoria());
        return nova.getIdCategoria();
    }
    
    public static void buscarCategoria()throws Exception{
	System.out.println("\n\nBuscar Categoria:");
        System.out.print("\nID da categoria a ser buscada: ");
        int id = scan.nextInt();
        Categoria cat;
        cat = arqCategorias.busca(id);
        if(cat != null){
            System.out.println(arqCategorias.busca(id));
        }
        else{
            System.out.println("\nCategoria inexistente!");
        }
    }
    
    public static void excluirCategoria()throws Exception{
        System.out.println("\n\nExcluir Categoria:");
	System.out.print("\nId da categoria a ser excluida: ");
        int id = scan.nextInt();  
        int cont = 0;
        Object[] obj = arqVeiculos.ehVazia(id);
        if(obj.length == 0){ 
            if(arqCategorias.excluir(id)){
                System.out.println("\nCategoria excluida! ");
            }
	}else{ 
            System.out.println("Esta categoria não pôde ser excluída! Há veículos cadastrados nesta categoria."); 
        }
    }
    
    public static void editarCategoria()throws Exception{
        System.out.println("\n\nEditar Categoria:");
	Categoria cat= new Categoria();
        System.out.println("Id da categoria a ser editada: ");
        int id = scan.nextInt();
        cat = arqCategorias.busca(id);
        if(cat != null){
            System.out.println("Você selecionou a categoria: "+ cat.getNomeCategoria());
            String nomeCategoria;
            scan.nextLine();
            System.out.print("Digite o novo nome da Categoria: ");
            nomeCategoria = scan.nextLine();
            System.out.println("Confirmar alteração? (s/n)");
                char conf = scan.next().charAt(0);
                if(conf =='s' || conf =='S'){
                    cat.nomeCategoria = nomeCategoria;
                    if(arqCategorias.alteracao(cat)){ 
                        System.out.println("\n\nCategoria alterada.");
                    }else
                        System.out.println("\n\nCategoria não pôde ser alterada.");
                }
        }else{
            System.out.println("Categoria inexistente!");
        }
    }
    
    public static void listarCategoria()throws Exception{
        System.out.println("\n\nListar Categoria:");
        Categoria cat;
        Object[] obj = arqCategorias.listar();
        for(int i = 0; i < obj.length;i++){
        cat = (Categoria)obj[i];
        if(cat != null){
            System.out.println("\nId: "+ cat.getIdCategoria()+"\nCategoria: "+ cat.getNomeCategoria()+"\n");
        }
        }
    }

    public static int incluirCliente()throws Exception{ 
	System.out.println("\n\nIncluir Cliente:");
	Cliente novo = new Cliente();
        scan.nextLine();
        System.out.print("Nome: ");      
        novo.nome = scan.nextLine();
        System.out.print("CPF: ");      
        novo.cpf = scan.nextLine();
        System.out.print("Email: ");      
        novo.email = scan.nextLine();
        System.out.print("Telefone: ");      
        novo.telefone = scan.nextLine();
        arqClientes.incluir(novo);
        System.out.println("\nIncluiu cliente no ID: "+novo.getIdCliente());
        return novo.getIdCliente();
    }    
    
    public static void buscarCliente()throws Exception{
	System.out.println("\n\nBusca Cliente:");
        System.out.print("\nID do(a) cliente a ser buscado: ");
        int id = scan.nextByte();
        System.out.println(arqClientes.busca(id));	
    }
    
    public static void excluirCliente()throws Exception{
        System.out.println("\n\nExcluir Cliente:");
        boolean conf = false;
        System.out.print("\nId do cliente a ser excluido: ");	
        int id = scan.nextInt();
        System.out.println("\nDeseja realmente excluir o cliente? (S/N)");
        char resp = scan.next().charAt(0);
	while(resp != 'S' && resp != 'N' && resp != 's' && resp != 'n'){
	    System.out.println("Caractere invalido, responda com S ou N.");
            resp = scan.next().charAt(0); 
        }
        if(resp == 's' || resp == 'S'){
            conf = arqClientes.excluir(id);
        }
	if(conf){ 
            System.out.println("Cliente excluído!");
	 }else{ 
            System.out.println("Cliente não cadastrado!"); }
    }
    
    public static void editarCliente()throws Exception{
        System.out.println("\n\nEditar Cliente:");
	int id;
        Cliente cl=new Cliente();
        System.out.print("\nID do cliente a ser alterado: ");
        id = scan.nextInt();
        scan.nextLine();
        cl=arqClientes.busca(id);
        System.out.println("\nVocê selecionou o(a) cliente: "+ cl.getNomeCliente());
        System.out.println("\nProsseguir edição deste(a) cliente? (s/n)");
        char confirma = scan.next().charAt(0);
        scan.nextLine();
        if(confirma =='s' || confirma =='S'){
        if(cl!=null){
            String nome, cpf, email, telefone;
            System.out.print("\nInforme o nome do(a) cliente: ");
            nome = scan.nextLine();
            System.out.print("Informe o CPF do(a) cliente: ");
            cpf = scan.nextLine();
            System.out.print("Informe o email do(a) cliente: ");
            email = scan.nextLine();
            System.out.print("Informe o telefone do(a) cliente: ");
            telefone = scan.nextLine();
            System.out.println("Confirmar alteração? (s/n)");
            char conf = scan.next().charAt(0);
                if(conf =='s' || conf =='S'){
                    cl.id = id;
                    cl.nome = nome;
                    cl.cpf=cpf;
                    cl.email = email;
                    cl.telefone = telefone;
                    if(arqClientes.alteracao(cl)){ 
                        System.out.println("Informações alteradas com sucesso!");
                    }else
                        System.out.println("As informações não puderam ser alteradas.");
                }
        }
        else{
            System.out.println("Cliente inexistente!");
        }
        }
        else{
            System.out.println("Operação finalizada!");
        }
    }
   
    
    public static void listarCliente()throws Exception{ 
        Object[] obj = arqClientes.listar();
        System.out.println("\n\nListar Clientes:");
        for(int i = 0; i < obj.length;i++){
            System.out.println("\nId: "+ ((Cliente)obj[i]).getIdCliente()+
               "\nNome: " + ((Cliente)obj[i]).getNomeCliente() +
               "\nCPF: " + ((Cliente)obj[i]).getCpfCliente() +
               "\nEmail: " + ((Cliente)obj[i]).getEmailCliente() +
               "\nTelefone: " + ((Cliente)obj[i]).getTelefone() + "\n");
        }
    }
    
    public static void incluirVenda()throws Exception{
        Venda venda = new Venda();
        System.out.println("\n\nIncluir Venda:");
        System.out.print("\nID do cliente: ");
        venda.idCliente = scan.nextInt();
        if(arqClientes.busca(venda.idCliente) == null){
            System.out.println("\nCliente inexistente!");
        }
        else{
            scan.nextLine();
            System.out.print("\nData da venda: ");
            venda.Data = scan.nextLine();
            arqVendas.incluir(venda);
            int op = 1;
            while(op != 0){
                System.out.println("\nOpções de venda:\n1) Adicionar um veículo\n0) Concluir a venda");
                op = scan.nextInt();
                switch(op){
                    case 1:
                        Produto item = new Produto();
                        item.idCliente = venda.idCliente;
                        System.out.print("\nID do veículo comprado: ");
                        item.idProduto = scan.nextInt();
                        if(arqVeiculos.busca(item.idProduto) == null){
                            System.out.println("Veículo inexistente!");
                        }
                        else if(arqVeiculos.busca(item.idProduto).getQuantidade()==0){
                            System.out.println("\nNão há veículos em estoque!");
                        }
                        else{
                            System.out.println("Categoria: "+ arqCategorias.busca(arqVeiculos.busca(item.idProduto).getIdCategoria()).getNomeCategoria()+
                            "\nMarca: " + arqVeiculos.busca(item.idProduto).getMarca() +
                            "\nModelo: " + arqVeiculos.busca(item.idProduto).getModelo() +
                            "\nCor: " + arqVeiculos.busca(item.idProduto).getCor() +
                            "\nDescrição: " + arqVeiculos.busca(item.idProduto).getDescricao() +
                            "\nPreço: R$" + arqVeiculos.busca(item.idProduto).getPreco() + 
                            "\nQuantidade em estoque: " + arqVeiculos.busca(item.idProduto).getQuantidade() + "\n");
                            System.out.print("\nQuantidade a ser comprada: ");
                            item.quant = scan.nextInt();
                            if(item.quant<=arqVeiculos.busca(item.idProduto).getQuantidade()){
                                alterarQuantidadeVeiculo(item.idProduto,(arqVeiculos.busca(item.idProduto).getQuantidade()-item.quant));
                                item.precoUni = arqVeiculos.buscaPreco(item.idProduto);
                                item.idVenda = venda.getIdVenda();
                                venda.setValor(venda.getValor()+(item.quant*arqVeiculos.buscaPreco(item.idProduto)));
                                arqVendas.acumulaValor(venda,venda.getValor());
                                arqItemComprado.incluir(item);
                            }
                            else{
                                System.out.println("\nNão há veículos suficientes em estoque para finalizar a operação.");
                            }
                        }
                        break;
                    case 0:
                        System.out.println("Venda concluída!");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        }
    }
    
    public static void alterarQuantidadeVeiculo(int id, int quantidade)throws Exception{
        Veiculo novo = new Veiculo();
        novo = (Veiculo)arqVeiculos.busca(id);
        novo.quantidade = quantidade;
        arqVeiculos.alteracao(novo);
    }
    
    public static void buscarVenda()throws Exception{
	System.out.println("\n\nBuscar Compra:");
        System.out.print("\nID da compra a ser buscada: ");
        int id = scan.nextInt();
        System.out.println(arqVendas.busca(id));	
    }
    
    public static void excluirVenda()throws Exception{
        System.out.println("\n\nExcluir Venda:");
        System.out.print("\nId da venda a ser excluida: ");
        int id = scan.nextInt();
        boolean conf = false;
        if(arqItemComprado.lista(id).length != 0){
            System.out.println("\nEsta venda está registrada por um ou mais itens, impossível excluir.");
            return;
        }
        System.out.print("\nDeseja realmente excluir a venda? (S/N) ");
        char resp = scan.next().charAt(0);
	while(resp != 'S' && resp != 'N' && resp != 's' && resp != 'n'){
	    System.out.println("Opção inválida!");
            resp = scan.next().charAt(0); 
        }
        if(resp == 's' || resp == 'S'){
            conf = arqVendas.excluir(id);
        }
        if(conf){
            System.out.println("Venda excluída.");
        }else{
            System.out.println("Venda não excluída.");
        }        
    }
    
    public static void listarVenda() throws Exception {
        Object[] obj = arqVendas.listar();
        System.out.println("\n\nLista das Vendas: ");
        for(int i=0; i<obj.length; i++) {
            System.out.println("\nId da venda: "+ ((Venda)obj[i]).getIdVenda()+
               "\nId do cliente: " + ((Venda)obj[i]).getIdCliente() +
               "\nValor da compra: " + ((Venda)obj[i]).getValor() + "\n");
        }
    }
    
    public static void listarProdutosClientes() throws Exception {   
      Veiculo l;
      System.out.println("\n\nLista de Veículos por cliente:");
      int id;
      System.out.print("ID do Cliente: ");
      id = scan.nextInt();
      if(arqClientes.busca(id)==null){
          System.out.println("\nCliente inexistente.");
         return;
      }
      int[] idsProdutos = arqItemComprado.listaProduto(id);
      for(int i=0; i<idsProdutos.length; i++) {
         l = (Veiculo)arqVeiculos.busca(idsProdutos[i]);
         System.out.println( "\nID Veiculo: " + l.idProduto +
                                "\nMarca: " + l.marca +
                                "\nModelo: " + l.modelo +
                                "\nCor: " + l.cor +
                                "\nDescrição: " + l.descricao +
                                "\nValor unitário pago neste veículo: R$" + l.preco);
      }
    }

 public static void listarClientesProdutos() throws Exception {   
      Cliente c;
      System.out.println("\nLista de Clientes que compraram um veículo");// Cliente => Compra => itemcomprado
      int id;
      System.out.print("ID do Veículo: ");
      id = scan.nextInt();
      if(arqVeiculos.busca(id)==null){ 
         return;
      }
      int[] idsCompras = arqItemComprado.listaCliente(id);
      for(int i=0; i<idsCompras.length; i++) {          
         c = (Cliente)arqClientes.busca(idsCompras[i]);
         System.out.println( "\nID Cliente: " + c.id +
                             "\nNome: " + c.nome +
                             "\nCPF: " + c.cpf +
                             "\nEmail: " + c.email +
                             "\nTelefone: " + c.telefone + "\n");
      }
    }
    
 public static void quantidadeDeVendas() throws Exception {
     System.out.println("\n\nValor total de vendas do ano:\n");
     float valorTotal[] = new float[12];
     String str,mes;
     for(int i=0;i<12;i++){
         valorTotal[i]=0f;
     }
     Object[] obj = arqVendas.listar();
        for(int i=0; i<obj.length; i++) {
            str=((Venda)obj[i]).getData();
            mes=str.substring(3,5);
            if(mes.equals("01")){
                valorTotal[0] += ((Venda)obj[i]).getValor();
            }
            else if(mes.equals("02")){
                valorTotal[1] += ((Venda)obj[i]).getValor();
            }
            else if(mes.equals("03")){
                valorTotal[2] += ((Venda)obj[i]).getValor();
            }
            else if(mes.equals("04")){
                valorTotal[3] += ((Venda)obj[i]).getValor();
            }
            else if(mes.equals("05")){
                valorTotal[4] += ((Venda)obj[i]).getValor();
            }
            else if(mes.equals("06")){
                valorTotal[5] += ((Venda)obj[i]).getValor();
            }
            else if(mes.equals("07")){
                valorTotal[6] += ((Venda)obj[i]).getValor();
            }
            else if(mes.equals("08")){
                valorTotal[7] += ((Venda)obj[i]).getValor();
            }
            else if(mes.equals("09")){
                valorTotal[8] += ((Venda)obj[i]).getValor();
            }
            else if(mes.equals("10")){
                valorTotal[9] += ((Venda)obj[i]).getValor();
            }
            else if(mes.equals("11")){
                valorTotal[10] += ((Venda)obj[i]).getValor();
            }
            else if(mes.equals("12")){
                valorTotal[11] += ((Venda)obj[i]).getValor();
            }
        }
        System.out.println("Janeiro:\nR$ "+valorTotal[0]);
        System.out.println("Fevereiro:\nR$ "+valorTotal[1]);
        System.out.println("Março:\nR$ "+valorTotal[2]);
        System.out.println("Abril:\nR$ "+valorTotal[3]);
        System.out.println("Maio:\nR$ "+valorTotal[4]);
        System.out.println("Junho:\nR$ "+valorTotal[5]);
        System.out.println("Julho:\nR$ "+valorTotal[6]);
        System.out.println("Agosto:\nR$ "+valorTotal[7]);
        System.out.println("Setembro:\nR$ "+valorTotal[8]);
        System.out.println("Outubro:\nR$ "+valorTotal[9]);
        System.out.println("Novembro:\nR$ "+valorTotal[10]);
        System.out.println("Dezembro:\nR$ "+valorTotal[11]);
 }
}