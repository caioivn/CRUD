package Produto;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;


public class ArquivoProduto<C extends RegistroProduto> {
    
    private RandomAccessFile arquivo;
    private String nomeArquivo;
    private String nomeIndice1;
    private String nomeIndice2;
    private String nomeIndice3;
    private Constructor<C> construtor;
    private IndiceProduto1 indice1;
    private IndiceProduto2 indice2;
    private IndiceProduto3 indice3;
    
    public ArquivoProduto(Constructor<C> c, String nomeArq, String nomeIdx1, String nomeIdx2, String nomeIdx3) throws IOException {
        this.nomeArquivo = nomeArq;
        this.nomeIndice1 = nomeIdx1;
        this.nomeIndice2 = nomeIdx2;
        this.nomeIndice3 = nomeIdx3;
        this.construtor = c;
        arquivo = new RandomAccessFile(this.nomeArquivo, "rw");
        indice1 = new IndiceProduto1(20, this.nomeIndice1);
        indice2 = new IndiceProduto2(20, this.nomeIndice2);
        indice3 = new IndiceProduto3(20, this.nomeIndice3);
        if(arquivo.length()<4) {
            arquivo.writeInt(0);
            indice1.apagar();
            indice2.apagar();
        }
    }
    
    public int incluir(C obj) throws IOException {
    
        int ultimoID;
        arquivo.seek(0);
        ultimoID = arquivo.readInt();
        arquivo.seek(0);
        arquivo.writeInt(ultimoID+1);
        obj.setId(ultimoID+1);
        
        arquivo.seek(arquivo.length());
        long endereco = arquivo.getFilePointer();
        byte[] b;
        b = obj.toByteArray();
        arquivo.writeByte(' ');
        arquivo.writeShort(b.length);
        arquivo.write(b);
        indice1.inserir(obj.getIdVenda(), obj.getIdProduto());
        indice2.inserir(obj.getIdProduto(), obj.getIdVenda());
        indice3.inserir(obj.getIdProduto(), obj.getIdCliente());
        return obj.getId();
    }
    
    public int[] lista(int idVenda) throws Exception {
        int[] ids = indice1.lista(idVenda);
        return ids;
    }
    
    public int[] lista2(int idProduto) throws Exception {
        int[] ids = indice2.lista(idProduto);
        return ids;
    }
    
    public int[] listaProduto(int idCliente) throws Exception {
        int[] ids = indice3.listaP(idCliente);
        return ids;
    }
    
    public int[] listaCliente(int idProduto) throws Exception {
        int[] ids = indice3.listaC(idProduto);
        return ids;
    }
    
    /*
     public boolean excluir(int idCompra) throws Exception {
        C obj = construtor.newInstance();
        short tamanho;
        byte[] b;
        byte lapide;
        long endereco;
        endereco = indice.buscar(idCompra);
        if(endereco != -1) {
            arquivo.seek(endereco);
            arquivo.writeByte('*');
            indice.excluir(idCompra);
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

        endereco = indice.buscar(novoObj.getIdCompra());
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
            indice.atualizar(novoObj.getIdCompra(), endereco);
            return true;
        }
        return false;
    }
    
    public void listar(int id)throws Exception{
        
    }  
*/
}