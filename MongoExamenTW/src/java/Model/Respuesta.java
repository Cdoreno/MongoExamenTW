package Model;

import java.util.ArrayList;

public class Respuesta {

    private String id;
    private ArrayList<String> respCorrectas;

    public Respuesta(String id, ArrayList<String> respCorrectas) {
        this.id = id;
        this.respCorrectas = respCorrectas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getRespCorrectas() {
        return respCorrectas;
    }

    public void setRespCorrectas(ArrayList<String> respCorrectas) {
        this.respCorrectas = respCorrectas;
    }
}
