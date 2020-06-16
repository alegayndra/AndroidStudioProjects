package al.tareas.tarea2_quizz;

public class AdminDePreguntas {
    public String arrPreguntas[];
    public int idPregunta;
    public boolean respuestas[];
    public int cantPreguntas;

    public AdminDePreguntas() {
        arrPreguntas = new String[5];
        idPregunta = 0;
        respuestas = new boolean[5];
        cantPreguntas = 0;
    }

    // getters

    public int getIdPregunta() {
        return idPregunta;
    }

    public int getCantPreguntas() {
        return cantPreguntas;
    }

    public boolean getRespuesta(int pos) {
        return respuestas[pos];
    }

    public String getPregunta(int pos) {
        return arrPreguntas[pos];
    }

    // setters

    public void setIdPregunta(int idNuevo) {
        idPregunta = idNuevo;
    }

    public void setCantPreguntas(int nuevaCantPreguntas) {
        cantPreguntas = nuevaCantPreguntas;
    }

    public void setRespuesta(int pos, boolean respuesta) {
        respuestas[pos] = respuesta;
    }

    public void setPregunta(int pos, String pregunta) {
        arrPreguntas[pos] = pregunta;
    }

    // metodos

    public void agregarPregunta(String pregunta) {
        if (getCantPreguntas() < 5) {
            arrPreguntas[getCantPreguntas()] = pregunta;
            setCantPreguntas(getCantPreguntas() + 1);
        }
    }
}
