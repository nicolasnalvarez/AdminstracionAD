package modelo;

import daos.UsuarioDAO;
import entities.UsuarioEntity;
import exceptions.CambioPasswordException;
import exceptions.UsuarioException;
import views.UsuarioView;

import java.util.Calendar;
import java.util.Date;

public class Usuario {

    private String nombre;
    private String password;
    private Date ultimaFechaCambio;
    private boolean habilitado;
    private String[] ultimasPasswords;
    private int cantidadPasswords;
    private int tipo_usuario;
    private String dni;
    private String email;

    public Usuario(String nombre, String password, int tipo_usuario, String dni, String email) {
        this.nombre = nombre;
        this.password = password;
        this.ultimaFechaCambio = new Date(Calendar.getInstance().getTimeInMillis() - 3500000000l);
        this.ultimasPasswords = new String[10];
        this.cantidadPasswords = 0;
        this.tipo_usuario = tipo_usuario;
        this.dni = dni;
        this.email = email;
    }

    public Usuario(String nombre, String password, boolean habilitado, Date ultimaFechaCambio, String[] ultimasPasswords, int cantidadPasswords) {
        this.nombre = nombre;
        this.password = password;
        this.habilitado = habilitado;
        this.ultimaFechaCambio = ultimaFechaCambio;
        this.ultimasPasswords = ultimasPasswords;
        this.cantidadPasswords = cantidadPasswords;
    }

    public Usuario(String nombre, String password, boolean habilitado, Date ultimaFechaCambio, String[] ultimasPasswords, int cantidadPasswords, int tipo_usuario, String dni, String email) {
        this.nombre = nombre;
        this.password = password;
        this.habilitado = habilitado;
        this.ultimaFechaCambio = ultimaFechaCambio;
        this.ultimasPasswords = ultimasPasswords;
        this.cantidadPasswords = cantidadPasswords;
        this.tipo_usuario = tipo_usuario;
        this.dni = dni;
        this.email = email;
    }

    public void deshabilitarUsario() throws UsuarioException{
        if(this.habilitado){
            this.habilitado = false;
            UsuarioDAO.getInstancia().update(this);
        }
    }

    public void habilitarUsuario() throws UsuarioException{
        if(!this.habilitado){
            this.habilitado = true;
            UsuarioDAO.getInstancia().update(this);
        }
    }

    public boolean debeCambiar(){
        return Calendar.getInstance().getTimeInMillis() - ultimaFechaCambio.getTime() > 3499999999l;
    }

    public void actualizoPassword(String password) throws CambioPasswordException, UsuarioException, UsuarioException {
        if(password.length() < 8   ||
                password.contains("=")  ||
                password.contains("'")  ||
                password.contains("\"") ||
                password.contains(".")) {
            throw new CambioPasswordException("El password no contepla los requisitos basicos de seguridad");
        }
        if(passwordDistintoAnteriores(password)){
            this.agregoHistorialPassword(password);
            this.password = password;
            UsuarioDAO.getInstancia().updatePassword(this);
        }
        else
        {
            throw new CambioPasswordException("El password debe ser distinto de los 10 anteriores.");
        }
    }

    private boolean passwordDistintoAnteriores(String password) {
        for(int i=0; i<this.cantidadPasswords; i++)
            if(this.ultimasPasswords[i].equals(password))
                return false;
        return true;
    }

    private void agregoHistorialPassword(String password) {
        if(cantidadPasswords == 0)
            this.ultimasPasswords = new String[10];
        if(cantidadPasswords < 10){
            this.ultimasPasswords[cantidadPasswords] = password;
            cantidadPasswords++;
        }
        else{
            for(int i = 0; i < cantidadPasswords - 1; i++)
                this.ultimasPasswords[i] = this.ultimasPasswords[i+1];
            this.ultimasPasswords[cantidadPasswords - 1] = password;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public Date getUltimaFechaCambio() {
        return ultimaFechaCambio;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public String[] getUltimasPasswords() {
        return ultimasPasswords;
    }

    public int getCantidadPasswords() {
        return cantidadPasswords;
    }

    public int getTipoUsuario() { return tipo_usuario;}

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public UsuarioView toView() {
        return new UsuarioView(nombre,password,tipo_usuario,dni,email);
    }

    public UsuarioEntity toEntity() {
        return new UsuarioEntity(nombre,password,tipo_usuario,dni,email);
    }
}