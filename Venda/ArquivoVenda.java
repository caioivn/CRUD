package Venda;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;


public class ArquivoVenda<C extends RegistroVenda> {
    
    private RandomAccessFile arquivo;
    private String nomeArquivo;
    private String nomeIndice;
    private Constructor<C> construtor;
    private IndiceVenda indice;
    
    public ArquivoVenda(Constructor<C> c, String nomeArq, String nomeIdx) throws IOException {
        this.nomeArquivo = nomeArq;
        this.nomeIndice = nomeIdx;
        this.construtor = c;
        arquivo = new RandomAccessFile(this.nomeArquivo, "rw");
        indice = new IndiceVenda(20, this.nomeIndice);
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
        obj.setIdVenda(ultimoID+1);
        
        arquivo.seek(arquivo.length());
        long endereco = arquivo.getFilePointer();
        byte[] b;
        b = obj.toByteArray();
        arquivo.writeByte(' ');
        arquivo.writeShort(b.length);
        arquivo.write(b);
        indice.inserir(obj.getIdVenda(), endereco);
        return obj.getIdVenda();
    }

     public C busca(int idVenda) throws Exception {
        C obj = construtor.newInstance();
        short tamanho;
        byte[] b;
        byte lapide;
        long endereco = indice.buscar(idVenda);
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
     public boolean excluir(int idVenda) throws Exception {
        C obj = construtor.newInstance();
        short tamanho;
        byte[] b;
        byte lapide;
        long endereco;
        endereco = indice.buscar(idVenda);
        if(endereco != -1) {
            arquivo.seek(endereco);
            arquivo.writeByte('*');
            indice.excluir(idVenda);
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

        endereco = indice.buscar(novoObj.getIdVenda());
        if(endereco != -1) {
            // apaga o registro anterior
            arquivo.seek(endereco);
            arquivo.writeByte('*');
            
            // cria o novo registro
            arquivo.seek(arquivo.length());
            endereco = arquivo.getFilePointer();
            arquivo.writeByte(' ');
            b = novoObj.toByteArray();
            arquivo.writeInt(b.length);
            arquivo.write(b);
            indice.atualizar(novoObj.getIdVenda(), endereco);
            return true;
        }
        return false;
    }
     
    public void acumulaValor(C Obj,float Valor) throws Exception {
        short tamanho;
        byte[] b;
        byte lapide;
        long endereco = indice.buscar(Obj.getIdVenda());
        if(endereco != -1) {
            arquivo.seek(endereco);
            lapide = arquivo.readByte();
            tamanho = arquivo.readShort();
            b = new byte[tamanho];
            arquivo.read(b);
            if(lapide==' '){    
                arquivo.seek(arquivo.getFilePointer()-4);
                arquivo.writeFloat(Valor);
            }
        }
    }
    
    public Object[] listar()throws Exception{
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
}