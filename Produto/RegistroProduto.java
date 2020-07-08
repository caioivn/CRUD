package Produto;
import java.io.IOException;

public interface RegistroProduto {
    public int getId();
    public void setId(int Id);
    public int getIdVenda();
    public void setIdVenda(int idVenda);
    public int getIdProduto();
    public void setIdProduto(int idProduto);
    public int getIdCliente();
    public void setIdCliente(int idCliente);
    public byte[] toByteArray() throws IOException;
    public void fromByteArray(byte[] b) throws IOException;   
}