package entities;

import modelo.Reclamo;

import javax.persistence.*;

@Entity
@Table(name = "reclamos")
public class ReclamoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reclamo")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="documento")
    private PersonaEntity persona;

    @ManyToOne
    @JoinColumn(name="id_edificio")
    private EdificioEntity edificio;

    @ManyToOne
    @JoinColumn(name="id_unidad")
    private UnidadEntity unidad;

    private String ubicacion;
    private String descripcion;

    public ReclamoEntity() {}

    public ReclamoEntity(PersonaEntity persona, EdificioEntity edificio, UnidadEntity unidad, String ubicacion, String descripcion) {
        this.persona = persona;
        this.edificio = edificio;
        this.unidad = unidad;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
    }

    public EdificioEntity getEdificio() {
        return edificio;
    }

    public void setEdificio(EdificioEntity edificio) {
        this.edificio = edificio;
    }

    public UnidadEntity getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadEntity unidad) {
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

    public Reclamo toNegocio() {
        return new Reclamo(id, persona.toNegocio(), edificio.toNegocio(), unidad.toNegocio(), ubicacion, descripcion);
    }
}
