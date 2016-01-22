/*
 * Datos de las carreras
 */
package turf;

import java.io.Serializable;

/**
 *
 * @author Leo
 */
public class Carrera implements Serializable{
    static final long serialVersionUID = 0xf3L;
    private int id;
    private String hipodromo;

    public Carrera(int id, String hipodromo) {
        this.id = id;
        this.hipodromo = hipodromo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHipodromo() {
        return hipodromo;
    }

    public void setHipodromo(String hipodromo) {
        this.hipodromo = hipodromo;
    }
    
}
