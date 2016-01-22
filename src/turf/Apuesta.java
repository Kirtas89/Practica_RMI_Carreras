/*
 * Datos de la apuesta
 */
package turf;

import java.io.Serializable;

/**
 *
 * @author Leo
 */
public class Apuesta implements Serializable {
    static final long serialVersionUID = 0xf3L;
    public static final int LONGCLAVE = 12;
    private int id;
    private String clave;
    private int idcaballo;
    private int idCarrera;
    private float importe;

    public Apuesta(int idcaballo, int idCarrera, float importe) {
        this.idcaballo = idcaballo;
        this.idCarrera = idCarrera;
        this.importe = importe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getIdcaballo() {
        return idcaballo;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public float getImporte() {
        return importe;
    }
    
}
