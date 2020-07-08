package Veiculo;
import java.io.IOException;

public interface RegistroVeiculo {
    public int getId();
    public void setId(int idProduto);
    public byte[] toByteArray() throws IOException;
    public void fromByteArray(byte[] b) throws IOException;   
}