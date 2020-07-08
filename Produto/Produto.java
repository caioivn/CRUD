package Produto;
import java.io.*;

/**
 *
 * @author Caio
 */


public class Produto implements RegistroProduto {   

    public int Id;
    public int idVenda;
    public int idProduto;
    public int idCliente;
    public int quant;
    public float precoUni;

     
    public Produto(){
        this.Id = -1;
        this.idVenda = -1;
        this.idProduto = -1;
        this.idCliente = -1;
        this.quant = -1;
        this.precoUni = -1;
    }
    
   
    public int getId() {
        return this.Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    

    public int getIdVenda() {
        return this.idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }
    
  
    public int getIdProduto() {
        return this.idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    
  
    public int getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(int idProduto) {
        this.idCliente = idCliente;
    }
    

    public String toString() {
        return ("\nID: " + this.Id+
                "\nID da venda: " + this.idVenda+
                "\nID do produto: " + this.idProduto+
                "\nID do cliente: " + this.idCliente+
                "\nQuantidade: " + this.quant+
                "\nPreco unitario: " + this.precoUni);
    }
    

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream dados = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream(dados);
        saida.writeInt(this.Id);
        saida.writeInt(this.idVenda);
        saida.writeInt(this.idProduto);
        saida.writeInt(this.idCliente);
        saida.writeInt(this.quant);
        saida.writeFloat(this.precoUni);
        return dados.toByteArray();
    }
   

    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream dados = new ByteArrayInputStream(b);
        DataInputStream entrada = new DataInputStream(dados);
        this.Id = entrada.readInt();
        this.idVenda = entrada.readInt();
        this.idProduto = entrada.readInt();
        this.idCliente = entrada.readInt();
        this.quant = entrada.readInt();
        this.precoUni = entrada.readFloat();
    }    
}