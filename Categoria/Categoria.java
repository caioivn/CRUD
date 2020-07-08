package Categoria;
import java.io.*;

/**
 *
 * @author Caio
 */


public class Categoria implements RegistroCategoria {   

    public String nomeCategoria;
    public int idCategoria;

 
    public Categoria(String nomeCategoria){
        this.nomeCategoria = nomeCategoria; 
    }
    
    public Categoria(){
        this.idCategoria = -1;
        this.nomeCategoria = "";
    }
    

    public int getIdCategoria() {
        return this.idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
   
    public String getNomeCategoria() {
        return this.nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
    

    public String toString() {
        return ("\nCategoria: " + this.nomeCategoria);
    }
    
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream dados = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream(dados);
        saida.writeInt(this.idCategoria);
        saida.writeUTF(this.nomeCategoria);
        return dados.toByteArray();
    }
   
    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream dados = new ByteArrayInputStream(b);
        DataInputStream entrada = new DataInputStream(dados);
        this.idCategoria = entrada.readInt();
        this.nomeCategoria = entrada.readUTF();
    }    
    
    public void fromNome(byte[] b) throws IOException {
       ByteArrayInputStream dados = new ByteArrayInputStream(b);
       DataInputStream entrada = new DataInputStream(dados); 
       this.nomeCategoria = entrada.readUTF();
    }
}

