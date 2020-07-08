package Cliente;
import java.io.IOException;

public interface RegistroCliente {
    public int getIdCliente();
    public void setIdCliente(int id);
    public byte[] toByteArray() throws IOException;
    public void fromByteArray(byte[] b) throws IOException; 
    public void fromNome(byte[] b) throws IOException;
}