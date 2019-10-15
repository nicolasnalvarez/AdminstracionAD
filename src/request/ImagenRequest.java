package request;

public class ImagenRequest {

    private String path;
    private String tipo;

    public ImagenRequest() {}

    public ImagenRequest(String path, String tipo) {
        this.path = path;
        this.tipo = tipo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
