package Venda;
import java.io.IOException;

public interface RegistroVenda {
    public int getIdVenda();
    public void setIdVenda(int idProduto);
    public byte[] toByteArray() throws IOException;
    public void fromByteArray(byte[] b) throws IOException;   
}