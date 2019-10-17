package entities;

import modelo.Usuario;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="identificador")
    private Integer id;
    private String nombre;
    private String password;
    private String habilitado;
    @Column(name="fechacreacion")
    private Date fechaCreacion;
    @Column(name="fecha_ultimo_cambio")
    private Date ultimaFechaCambio;
    @Column(name="passwords_anteriores")
    private String[] passwordsAnteriores;
    @Column(name="cantidad_passwords")
    private int cantidad;
    @Column(name="tipo_usuario")
    private int tipo_usuario;

    public UsuarioEntity(){
        this.passwordsAnteriores = new String[10];
        this.cantidad = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUltimaFechaCambio() {
        return ultimaFechaCambio;
    }

    public void setUltimaFechaCambio(Date ultimaFechaCambio) {
        this.ultimaFechaCambio = ultimaFechaCambio;
    }

    public String[] getPasswordsAnteriores() {
        return this.passwordsAnteriores;
    }

    public void addOldPassword(String password) {
        passwordsAnteriores[cantidad] = password;
        cantidad++;
    }

    public int getCantidad(){
        return this.cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;

    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public void setPasswordsAnteriores(String[] passwordsAnteriores) {
        this.passwordsAnteriores = passwordsAnteriores;
    }

    public int getTipoUsuario() {return tipo_usuario; }

    public void setTipoUsuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public UsuarioEntity(String nombre, String password, int tipo_usuario) {
        this.nombre = nombre;
        this.password = password;
        this.tipo_usuario=tipo_usuario;
    }

    public Usuario toNegocio() {
        return new Usuario(nombre,password,tipo_usuario);
    }
}