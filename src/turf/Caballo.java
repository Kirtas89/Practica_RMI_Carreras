/*
 * Datos de un caballo
 */
package turf;

import java.io.Serializable;

/**
 *
 * @author Leo
 */
public class Caballo implements Serializable{
    static final long serialVersionUID = 0xf3L;
    private int id;
    private String nombre;

    public Caballo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
    
}
