package Categoria;
import java.io.IOException;

public interface RegistroCategoria {
    public int getIdCategoria();
    public void setIdCategoria(int id);
    public byte[] toByteArray() throws IOException;
    public void fromByteArray(byte[] b) throws IOException; 
    public void fromNome(byte[] b) throws IOException;
}