package Veiculo;
import java.io.*;

/**
 *
 * @author Caio
 */

public class Veiculo implements RegistroVeiculo {
    
    protected int idProduto;
    protected int idCategoria;
    protected String marca;
    protected String modelo;
    protected String cor;
    protected String descricao;
    protected float preco;
    protected int quantidade;
      
    public Veiculo(String marca, String modelo, String cor, String descricao, float preco, int quantidade){
        this.idProduto = -1;
        this.idCategoria = -1;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade=quantidade;
    }
    
    public Veiculo(){
        this.idProduto = -1;
        this.idCategoria = -1;
        this.marca = "";
        this.modelo = "";
        this.cor = "";
        this.descricao = "";
        this.preco = 0F;
        this.quantidade=0;
    }
   
    public int getId() {
        return this.idProduto;
    }
    
    public void setId(int idProduto) {
        this.idProduto = idProduto;
    }
    
    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public String getModelo() {
        return this.modelo;
    }

    public void setNome(String nome) {
        this.modelo = modelo;
    }
    
    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    } 
    
    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    } 
    
   public float getPreco() {
        return this.preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    
    public int getIdCategoria(){
        return this.idCategoria;
    }
    
    public void setIdCategoria(int idCategoria){
        this.idCategoria = idCategoria;
    }

 /* 
   ///** Método para retorno de uma string que representa o objeto
    public String toString() {
        return "Id: " + this.idProduto +
               "\nNome: " + this.nome +
               "\nMarca: " + this.marca +
               "\nCor: " + this.cor +
               "\nDescrição: " + this.descricao +    
               "\nPreço: " + this.preco + " reais.\n";
    }
*/
    
    /** Método usado para a escrita dos objetos */    
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream dados = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream(dados);
        saida.writeInt(this.idProduto);
        saida.writeInt(this.idCategoria);
        saida.writeUTF(this.marca);
        saida.writeUTF(this.modelo);
        saida.writeUTF(this.cor);
        saida.writeUTF(this.descricao);        
        saida.writeFloat(this.preco);
        saida.writeInt(this.quantidade);
        return dados.toByteArray();
    }
   
   /** Método usado para a leitura dos objetos */    
    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream dados = new ByteArrayInputStream(b);
        DataInputStream entrada = new DataInputStream(dados);
        this.idProduto = entrada.readInt();
        this.idCategoria = entrada.readInt();
        this.marca = entrada.readUTF();
        this.modelo = entrada.readUTF();
        this.cor = entrada.readUTF();
        this.descricao = entrada.readUTF();
        this.preco = entrada.readFloat();
        this.quantidade = entrada.readInt();
    }    
}