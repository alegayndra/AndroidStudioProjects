package al.tareas.tareadb;

import android.net.wifi.WifiEnterpriseConfig;

public class Nota {

    private int _id;
    private String _nota;
    private long _fecha;

    public Nota() {

    }

    public Nota(int id, String nota, long fecha) {
        this._id = id;
        this._nota = nota;
        this._fecha = fecha;
    }

    public Nota(String nota, long fecha) {
        this._nota = nota;
        this._fecha = fecha;
    }

    // Setters ------------------------------------------------------------------------------

    public void setID(int id) {
        this._id = id;
    }

    public void setNota(String nota) {
        this._nota = nota;
    }

    public void setFecha(long fecha) {
        this._fecha = fecha;
    }

    // Getters ------------------------------------------------------------------------------

    public int getID() {
        return _id;
    }

    public String getNota() {
        return _nota;
    }

    public long getFecha() {
        return _fecha;
    }
}
