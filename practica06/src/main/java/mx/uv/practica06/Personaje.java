package mx.uv.practica06;
public class Personaje {
    int id;
    String nombre;
    String ciudad;
    String elemento;
    public Personaje(){
        
    }
    public Personaje(int id, String nombre, String ciudad, String elemento) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.elemento = elemento;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getElemento() {
        return elemento;
    }
    public void setElemento(String elemento) {
        this.elemento = elemento;
    }
}
