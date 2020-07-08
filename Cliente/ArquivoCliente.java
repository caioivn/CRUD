package Cliente;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;


public class ArquivoCliente<C extends RegistroCliente> {
    
    private RandomAccessFile arquivo;
    private String nomeArquivo;
    private String nomeIndice;
    private Constructor<C> construtor;
    private IndiceCliente indice;
    
    public ArquivoCliente(Constructor<C> c, String nomeArq, String nomeIdx) throws IOException {
        this.nomeArquivo = nomeArq;
        this.nomeIndice = nomeIdx;
        this.construtor = c;
        arquivo = new RandomAccessFile(this.nomeArquivo, "rw");
        indice = new IndiceCliente(20, this.nomeIndice);
        if(arquivo.length()<4) {
            arquivo.writeInt(0);
            indice.apagar();
        }
    }
    
    public int incluir(C obj) throws IOException {
    
        int ultimoID;
        arquivo.seek(0);
        ultimoID = arquivo.readInt();
        arquivo.seek(0);
        arquivo.writeInt(ultimoID+1);
        obj.setIdCliente(ultimoID+1);
        
        arquivo.seek(arquivo.length());
        long endereco = arquivo.getFilePointer();
        byte[] b;
        b = obj.toByteArray();
        arquivo.writeByte(' ');
        arquivo.writeShort(b.length);
        arquivo.write(b);
        indice.inserir(obj.getIdCliente(), endereco);
        return obj.getIdCliente();
    }

     public C busca(int idCliente) throws Exception {
        C obj = construtor.newInstance();
        short tamanho;
        byte[] b;
        byte lapide;
        long endereco = indice.buscar(idCliente);
        if(endereco != -1) {
            arquivo.seek(endereco);
            lapide = arquivo.readByte();
            tamanho = arquivo.readShort();
            b = new byte[tamanho];
            arquivo.read(b);
            obj.fromByteArray(b);
            return obj;
        } 
        return null;
    }
     public boolean excluir(int idCliente) throws Exception {
        C obj = construtor.newInstance();
        short tamanho;
        byte[] b;
        byte lapide;
        long endereco;
        endereco = indice.buscar(idCliente);
        if(endereco != -1) {
            arquivo.seek(endereco);
            arquivo.writeByte('*');
            indice.excluir(idCliente);
            return true;
        }
        return false;
    }

     public boolean alteracao(C novoObj) throws Exception {
        C obj = construtor.newInstance();
        short tamanho;
        byte[] b;
        byte lapide;
        long endereco;

        endereco = indice.buscar(novoObj.getIdCliente());
        if(endereco != -1) {
            arquivo.seek(endereco);
            arquivo.writeByte('*');
            indice.excluir(novoObj.getIdCliente());
            int ultimoID;
            arquivo.seek(0);
            ultimoID = arquivo.readInt();
            arquivo.seek(0); 
            arquivo.writeInt(ultimoID+1);
            obj.setIdCliente(novoObj.getIdCliente());
            arquivo.seek(arquivo.length());
            long endereco2 = arquivo.getFilePointer();
            byte[] b2;
            b2 = novoObj.toByteArray();
            arquivo.writeByte(' ');
            arquivo.writeShort(b2.length);
            arquivo.write(b2);
            indice.inserir(novoObj.getIdCliente(), endereco2);
            return true;
        }
        return false;
    }
    
    public Object[] listar() throws Exception {
        ArrayList<C> lista = new ArrayList<>();
        C obj;
        short tamanho;
        byte[] b;
        byte lapide;
        arquivo.seek(4);
        while(arquivo.getFilePointer() < arquivo.length()) {
            lapide = arquivo.readByte();
            tamanho = arquivo.readShort();
            b = new byte[tamanho];
            arquivo.read(b);
            obj = construtor.newInstance();
            obj.fromByteArray(b);
            if(lapide==' ')
                lista.add(obj);
        }
        return lista.toArray();
    }  
    
    public C nomeCliente(int id) throws Exception{
        C obj = construtor.newInstance();
        short tamanho;
        byte[] b;
        byte lapide;
        long endereco = indice.buscar(id);
        if(endereco != -1) {
            arquivo.seek(endereco);
            lapide = arquivo.readByte();
            tamanho = arquivo.readShort();
            arquivo.seek(arquivo.getFilePointer() + 4);
            b = new byte[tamanho];
            arquivo.read(b);
            obj.fromNome(b);
            return obj;
        } 
        return null;
    }
    
}