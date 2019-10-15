package request;

public class ReclamoRequest {
    private String documento;
    private Integer idEdificio;
    private Integer idUnidad;
    private String ubicacion;
    private String descripcion;

    public ReclamoRequest() {}

    public ReclamoRequest(String documento, Integer idEdificio, Integer idUnidad, String ubicacion, String descripcion) {
        this.documento = documento;
        this.idEdificio = idEdificio;
        this.idUnidad = idUnidad;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(Integer idEdificio) {
        this.idEdificio = idEdificio;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
