package modelo;

import views.ReclamoView;

public class Reclamo {

    private Integer idReclamo;
    private String documento;
    private Integer codigo;
    private String ubicacion;
    private String descripcion;
    private Integer identificador;

    public Reclamo() {

    }

    public Reclamo(Integer idReclamo, String documento, Integer codigo, String ubicacion, String descripcion, Integer identificador) {
        this.idReclamo = idReclamo;
        this.documento = documento;
        this.codigo = codigo;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.identificador = identificador;
    }

    public Reclamo(String documento, Integer codigo, String ubicacion, String descripcion, Integer identificador) {
        this.documento = documento;
        this.codigo = codigo;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.identificador = identificador;
    }

    public Integer getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(Integer idReclamo) {
        this.idReclamo = idReclamo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public ReclamoView toView() {
        return new ReclamoView(idReclamo,documento,codigo,ubicacion,descripcion,identificador);
    }
}
