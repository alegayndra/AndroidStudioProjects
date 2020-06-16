package al.tareas.tareafragments;

import java.lang.reflect.Array;

public class QuestionLoader {
    private String[][] questions;
    private Boolean[][] answers;

    public QuestionLoader() {
        questions = new String[2][5];
        answers = new Boolean[2][5];
        createQuestions();
    }

    private void createQuestions() {
        // Encuesta A - tecnologica
        questions[0][0] = "Javascript está bien hecho";
        questions[0][1] = "NodeJS es hermoso";
        questions[0][2] = "C++ es innecesarimente complicado";
        questions[0][3] = "Python es fácil de programar";
        questions[0][4] = "JavaScript tiene apuntadores";

        answers[0][0] = false;
        answers[0][1] = true;
        answers[0][2] = true;
        answers[0][3] = true;
        answers[0][4] = false;

        // Encuesta B - deportes
        questions[1][0] = "Messi juega basket";
        questions[1][1] = "mkleo es el mejor jugador de smash";
        questions[1][2] = "ESPN está colaborando con Psyonix";
        questions[1][3] = "El americano es un deporte muy suave";
        questions[1][4] = "El tennis es divertido";

        answers[1][0] = false;
        answers[1][1] = true;
        answers[1][2] = true;
        answers[1][3] = false;
        answers[1][4] = true;
    }

    public String getQuestion(int i, int j) {
        return questions[i][j];
    }

    public Boolean getAnswer(int i, int j) {
        return answers[i][j];
    }
}
