package views;

public class ReclamoView {

    //Seguro esto haya que cambiarlo y agregar entidades, por que por ej idEdificio no sirve de nada para el frontend

    private Integer id;
    private String documento;
    private Integer idEdificio;
    private Integer idUnidad;
    private String ubicacion;
    private String descripcion;

    public ReclamoView() {}

    public ReclamoView(Integer id, String documento, Integer idEdificio, Integer idUnidad, String ubicacion, String descripcion) {
        this.id = id;
        this.documento = documento;
        this.idEdificio = idEdificio;
        this.idUnidad = idUnidad;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
