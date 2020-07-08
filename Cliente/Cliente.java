package Cliente;
import java.io.*;

/**
 *
 * @author Caio
 */


public class Cliente implements RegistroCliente {   

    public int id;
    public String nome;
    public String cpf;
    public String email;
    public String telefone;
 
    public Cliente(String nomeCliente, String cpf, String emailCliente, String telefone){
        this.id = -1;
        this.nome = nomeCliente;
        this.cpf=cpf;
        this.email = emailCliente;
        this.telefone = telefone;
    }
    
    public Cliente(){
        this.id = -1;
        this.nome = "";
        this.cpf = "";
        this.email = "";
        this.telefone= "";
    }
    

    public int getIdCliente() {
        return this.id;
    }

    public void setIdCliente(int idCliente) {
        this.id = idCliente;
    }
   
    public String getNomeCliente() {
        return this.nome;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nome = nomeCliente;
    }
    
    public String getCpfCliente() {
        return this.cpf;
    }

    public void setCpfCliente(String cpf) {
        this.cpf = cpf;
    }
    
    public String getEmailCliente() {
        return this.email;
    }

    public void setEmailCliente(String emailCliente) {
        this.email = emailCliente;
    }
    
    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    

    public String toString() {
        return ("\nID do Cliente: " + this.id+
                "\nNome do Cliente: " + this.nome+
                "\nCPF do Cliente: " + this.cpf+
                "\nEmail do Cliente: " + this.email+
                "\nTelefone do Cliente: " + this.telefone + "\n");
    }
    
  
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream dados = new ByteArrayOutputStream();
        DataOutputStream saida = new DataOutputStream(dados);
        saida.writeInt(this.id);
        saida.writeUTF(this.nome);
        saida.writeUTF(this.cpf);
        saida.writeUTF(this.email);
        saida.writeUTF(this.telefone);
        return dados.toByteArray();
    }
   

    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream dados = new ByteArrayInputStream(b);
        DataInputStream entrada = new DataInputStream(dados);
        this.id = entrada.readInt();
        this.nome = entrada.readUTF();
        this.cpf = entrada.readUTF();
        this.email = entrada.readUTF();
        this.telefone = entrada.readUTF();
    }
    
    public void fromNome(byte[] b) throws IOException {
       ByteArrayInputStream dados = new ByteArrayInputStream(b);
       DataInputStream entrada = new DataInputStream(dados); 
       this.nome = entrada.readUTF();
    }
}