package Categoria;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;


public class ArquivoCategoria<C extends RegistroCategoria> {
    
    private RandomAccessFile arquivo;
    private String nomeArquivo;
    private String nomeIndice;
    private Constructor<C> construtor;
    private IndiceCategoria indice;
    
    public ArquivoCategoria(Constructor<C> c, String nomeArq, String nomeIdx) throws IOException {
        this.nomeArquivo = nomeArq;
        this.nomeIndice = nomeIdx;
        this.construtor = c;
        arquivo = new RandomAccessFile(this.nomeArquivo, "rw");
        indice = new IndiceCategoria(20, this.nomeIndice);
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
        obj.setIdCategoria(ultimoID+1);
        
        arquivo.seek(arquivo.length());
        long endereco = arquivo.getFilePointer();
        byte[] b;
        b = obj.toByteArray();
        arquivo.writeByte(' ');
        arquivo.writeShort(b.length);
        arquivo.write(b);
        indice.inserir(obj.getIdCategoria(), endereco);
        return obj.getIdCategoria();
    }

     public C busca(int idCategoria) throws Exception {
        C obj = construtor.newInstance();
        short tamanho;
        byte[] b;
        byte lapide;
        long endereco = indice.buscar(idCategoria);
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
     public boolean excluir(int idCategoria) throws Exception {
        C obj = construtor.newInstance();
        short tamanho;
        byte[] b;
        byte lapide;
        long endereco;
        endereco = indice.buscar(idCategoria);
        if(endereco != -1) {
            arquivo.seek(endereco);
            arquivo.writeByte('*');
            indice.excluir(idCategoria);
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

        endereco = indice.buscar(novoObj.getIdCategoria());
        if(endereco != -1) {
            arquivo.seek(endereco);
            arquivo.writeByte('*');
            indice.excluir(novoObj.getIdCategoria());
            
            int ultimoID;
        arquivo.seek(0);
        ultimoID = arquivo.readInt();
        arquivo.seek(0); 
        arquivo.writeInt(ultimoID+1);
        obj.setIdCategoria(novoObj.getIdCategoria());
        arquivo.seek(arquivo.length());
        long endereco2 = arquivo.getFilePointer();
        byte[] b2;
        b2 = novoObj.toByteArray();
        arquivo.writeByte(' ');
        arquivo.writeShort(b2.length);
        arquivo.write(b2);
        indice.inserir(novoObj.getIdCategoria(), endereco2);
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
    
    public C nomeCategoria(int id) throws Exception{
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