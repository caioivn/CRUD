package Veiculo;
import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;


public class ArquivoVeiculo<T extends RegistroVeiculo> {
    
    private RandomAccessFile arquivo;
    private String nomeArquivo;
    private String nomeIndice;
    private Constructor<T> construtor;
    private IndiceVeiculo indice;
    
    public ArquivoVeiculo(Constructor<T> c, String nomeArq, String nomeIdx) throws IOException {
        this.nomeArquivo = nomeArq;
        this.nomeIndice = nomeIdx;
        this.construtor = c;
        arquivo = new RandomAccessFile(this.nomeArquivo, "rw");
        indice = new IndiceVeiculo(20, this.nomeIndice);
        if(arquivo.length()<4) {
            arquivo.writeInt(0);
            indice.apagar();
        }
    }
    
    public int incluir(T obj) throws IOException {
        
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
        indice.inserir(obj.getId(), endereco);
        return obj.getId();
    }

    public T busca(int id) throws Exception { 
	T obj = construtor.newInstance();
        short tamanho;
        byte[] b;
        byte lapide;
        long endereco = indice.buscar(id);
        if(endereco != -1) {
            arquivo.seek(endereco);
            lapide = arquivo.readByte();
            tamanho = arquivo.readShort();
            b = new byte[tamanho];
            arquivo.read(b);
            if(lapide==' '){    
                obj.fromByteArray(b);
            }
            return obj;
        }
        return null;
    }
    
     public boolean excluir(int id) throws Exception {
	T obj = construtor.newInstance();   
        short tamanho;
        byte[] b;
        byte lapide;
	char resp;
        long endereco = indice.buscar(id);
        if(endereco != -1) {
            arquivo.seek(endereco);
            lapide = arquivo.readByte();
            tamanho = arquivo.readShort();
            b = new byte[tamanho];
            arquivo.read(b);
            if(lapide==' '){
               obj.fromByteArray(b);
            } 
        }	    
        endereco = indice.buscar(id);
        if(endereco != -1) {
            arquivo.seek(endereco);
            arquivo.writeByte('*');
            indice.excluir(id);
            return true;
        }
        return false;
    }

     public boolean alteracao(T novoObj)throws Exception {
        T obj = construtor.newInstance();
        short tamanho; 
        byte[] b;
        byte lapide;
        long endereco;     
        endereco = indice.buscar(novoObj.getId());
        if(endereco != -1) {
            arquivo.seek(endereco);
            arquivo.writeByte('*');
            indice.excluir(novoObj.getId());
        }    
        int ultimoID;
        arquivo.seek(0);
        ultimoID = arquivo.readInt();
        arquivo.seek(0); 
        arquivo.writeInt(ultimoID+1);
        obj.setId(novoObj.getId());
        arquivo.seek(arquivo.length());
        long endereco2 = arquivo.getFilePointer();
        byte[] b2;
        b2 = novoObj.toByteArray();
        arquivo.writeByte(' ');
        arquivo.writeShort(b2.length);
        arquivo.write(b2);
        indice.inserir(novoObj.getId(), endereco2);
        return true;
     }

    public Object[] listar() throws Exception {
        ArrayList<T> lista = new ArrayList<>();
        T obj;
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

    public Object[] ehVazia(int id)throws Exception{
        ArrayList<T> lista = new ArrayList<>();
        T obj;
        short tamanho;
        byte[] b;
        byte lapide;
        int idC;
        arquivo.seek(4);
        while(arquivo.getFilePointer() < arquivo.length()) {
            lapide = arquivo.readByte();
            tamanho = arquivo.readShort();
            arquivo.seek(arquivo.getFilePointer()+4);
            idC = arquivo.readInt();
            arquivo.seek(arquivo.getFilePointer()-8);
            b = new byte[tamanho];
            arquivo.read(b);
            obj = construtor.newInstance();
            obj.fromByteArray(b);
            if(lapide==' ' && idC == id)
                lista.add(obj);
        }
        return lista.toArray();
    }
    
    public float buscaPreco(int idProduto) throws Exception{
        float preco = 0;
        short tamanho;
        byte[] b;
        byte lapide;
        long endereco = indice.buscar(idProduto);
        if(endereco != -1) {
            arquivo.seek(endereco);
            lapide = arquivo.readByte();
            tamanho = arquivo.readShort();
            b = new byte[tamanho];
            arquivo.read(b);
            if(lapide==' '){    
                arquivo.seek(arquivo.getFilePointer()-8);
                preco = arquivo.readFloat();
            }
            return preco;
        }
        return -1;
    }
    
    public int buscaQuantidade(int idProduto) throws Exception{
        int quantidade = 0;
        short tamanho;
        byte[] b;
        byte lapide;
        long endereco = indice.buscar(idProduto);
        if(endereco != -1) {
            arquivo.seek(endereco);
            lapide = arquivo.readByte();
            tamanho = arquivo.readShort();
            b = new byte[tamanho];
            arquivo.read(b);
            if(lapide==' '){    
                arquivo.seek(arquivo.getFilePointer()-4);
                quantidade = arquivo.readInt();
            }
            return quantidade;
        }
        return 0;
    }
    
}