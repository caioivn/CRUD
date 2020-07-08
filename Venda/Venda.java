package Venda;
import java.io.*;

/**
 *
 * @author Caio
 */


public class Venda implements RegistroVenda {   

    public int idVenda;
    public int idCliente;
    public float Valor;
    public String Data;
    
    public Venda(){
        this.idVenda = -1;
        this.idCliente = -1;
        this.Valor = 0;
        this.Data = " ";
    }
       
    public int getIdVenda() {
        return this.idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }
    
  
    public int getIdCliente() {
        return this.idCliente;
    }
  
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
   
    public float getValor() {
        return this.Valor;
    }
 
    public void setValor(float Valor) {
        this.Valor = Valor;
    }
        
    public String getData() {
        return this.Data;
    }
    
    public void setData(String Data) {
        this.Data = Data;
    }
    
    
    public String toString() {
        return ("\nData de compra: " + this.Data+
                "\nID da Venda: " + this.idVenda+
                "\nID do Cliente: " + this.idCliente+
                "\nValor da compra: " + this.Valor);
    }
    
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream dados = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream(dados);
        saida.writeUTF(this.Data);
        saida.writeInt(this.idVenda);
        saida.writeInt(this.idCliente);
        saida.writeFloat(this.Valor);
        return dados.toByteArray();
    }
    
    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream dados = new ByteArrayInputStream(b);
        DataInputStream entrada = new DataInputStream(dados);
        this.Data = entrada.readUTF();
        this.idVenda = entrada.readInt();
        this.idCliente = entrada.readInt();
        this.Valor = entrada.readFloat();  
    }
}