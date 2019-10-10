package modelo;

import daos.ReclamoDAO;
import entities.ReclamoEntity;
import views.Estado;
import views.ReclamoView;

import java.util.List;

public class Reclamo {

    //TODO analizar si conviene tener las entidades o solo recibir las keys de cada uno (por ej documento en Persona)
    private Integer idReclamo;
    private Persona persona;
    private Edificio edificio;
    private Unidad unidad;
    private String ubicacion;
    private String descripcion;
    private Estado estado;

    public Reclamo() {

    }

    public Reclamo(Integer idReclamo, Persona persona, Edificio edificio, Unidad unidad, String ubicacion, String descripcion) {
        this.idReclamo = idReclamo;
        this.persona = persona;
        this.edificio = edificio;
        this.unidad = unidad;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.estado = Estado.nuevo;
    }

    public Reclamo(Persona persona, Edificio edificio, Unidad unidad, String ubicacion, String descripcion) {
        this.persona = persona;
        this.edificio = edificio;
        this.unidad = unidad;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.estado = Estado.nuevo;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public ReclamoView toView() {
        return new ReclamoView(idReclamo,persona.getDocumento(),edificio.getId(),unidad.getId(), ubicacion, descripcion, estado);
    }

    public ReclamoEntity toEntity() {
        return new ReclamoEntity(persona.toEntity(), edificio.toEntity(), unidad.toEntity(), ubicacion, descripcion);
    }

    public int save(List<Imagen> imagenes) {
        return ReclamoDAO.getInstancia().save(this, imagenes);
    }
}
