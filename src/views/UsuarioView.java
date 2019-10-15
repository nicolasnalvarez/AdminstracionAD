package views;


public class UsuarioView {

    private String nombre;
    private String password;
    private int tipo_usuario;

    public UsuarioView(String nombre,String password, int tipo_usuario) {
        this.nombre=nombre;
        this.password=password;
        this.tipo_usuario=tipo_usuario;
    }

    public UsuarioView() { }

    public String getNombre() {
        return nombre;
    }

    public String getPassword(){
        return password;
    }

    public int getTipoUsuario() {
        return tipo_usuario;
    }

}