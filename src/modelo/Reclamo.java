package modelo;

import views.ReclamoView;

public class Reclamo {

    private Integer idReclamo;
    private Persona persona;
    private Edificio edificio;
    private Unidad unidad;
    private String ubicacion;
    private String descripcion;

    public Reclamo() {

    }

    public Reclamo(Integer idReclamo, Persona persona, Edificio edificio, Unidad unidad, String ubicacion, String descripcion) {
        this.idReclamo = idReclamo;
        this.persona = persona;
        this.edificio = edificio;
        this.unidad = unidad;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }

    public Reclamo(Persona persona, Edificio edificio, Unidad unidad, String ubicacion, String descripcion) {
        this.persona = persona;
        this.edificio = edificio;
        this.unidad = unidad;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }

    public Integer getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(Integer idReclamo) {
        this.idReclamo = idReclamo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
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

    public ReclamoView toView() {
        return new ReclamoView(idReclamo,persona.getDocumento(),edificio.getId(),unidad.getId(), ubicacion,descripcion);
    }
}
