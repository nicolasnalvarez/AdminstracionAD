package views;


public class UsuarioView {

    private String nombre;
    private String password;
    private int tipo_usuario;
    private String dni;
    private String email;


    public UsuarioView(String nombre,String password, int tipo_usuario, String dni, String email) {
        this.nombre=nombre;
        this.password=password;
        this.tipo_usuario=tipo_usuario;
        this.dni = dni;
        this.email = email;
    }

    public UsuarioView() { }

    public String getNombre() {
        return nombre;
    }

    public String getPassword(){
        return password;
    }
    public String getDni(){
        return dni;
    }
    public String getEmail(){
        return email;
    }


    public int getTipoUsuario() {
        return tipo_usuario;
    }

    public String toString() {
        return nombre + " " + tipo_usuario;
    }

}