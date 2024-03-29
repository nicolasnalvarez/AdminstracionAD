package views;

import java.util.List;

public class ReclamoView {

    private Integer id;
    private String documento;
    private Integer idEdificio;
    private String nombreEdificio;
    private String direccionEdificio;
    private Integer idUnidad;
    private String numeroUnidad;
    private String pisoUnidad;
    private String ubicacion;
    private String descripcion;
    private String estado;
    private List<String> imagesPaths;

    public ReclamoView() {}

    public ReclamoView(Integer id, String documento, Integer idEdificio, String nombreEdificio, String direccionEdificio, Integer idUnidad, String numeroUnidad, String pisoUnidad, String ubicacion, String descripcion, Estado estado, List<String> imagesPaths) {
        this.id = id;
        this.documento = documento;
        this.idEdificio = idEdificio;
        this.nombreEdificio = nombreEdificio;
        this.direccionEdificio = direccionEdificio;
        this.idUnidad = idUnidad;
        this.numeroUnidad = numeroUnidad;
        this.pisoUnidad = pisoUnidad;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.estado = estado.name();
        this.imagesPaths = imagesPaths;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreEdificio() { return nombreEdificio; }

    public void setNombreEdificio(String nombreEdificio) { this.nombreEdificio = nombreEdificio; }

    public String getDireccionEdificio() { return direccionEdificio; }

    public void setDireccionEdificio(String direccionEdificio) { this.direccionEdificio = direccionEdificio; }

    public String getNumeroUnidad() { return numeroUnidad; }

    public void setNumeroUnidad(String numeroUnidad) { this.numeroUnidad = numeroUnidad; }

    public String getPisoUnidad() { return pisoUnidad; }

    public void setPisoUnidad(String pisoUnidad) { this.pisoUnidad = pisoUnidad; }

    public List<String> getImagesPaths() {
        return imagesPaths;
    }

    public void setImagesPaths(List<String> imagesPaths) {
        this.imagesPaths = imagesPaths;
    }
}
