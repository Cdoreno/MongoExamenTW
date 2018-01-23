/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.Document;

/**
 *
 * @author Ramon
 */
public class InfoExam extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");

        String resp = checkExamsAvailables(dni);

        response.setContentType("application/json");
        try (PrintWriter pw = response.getWriter()) {
            pw.println(resp);
        } catch (Exception e) {
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"error\":\"Error al cargar los examenes\"}");
        }

    }

    @Override
    public void init() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://admin:admin@examendb-wge65.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        MongoDatabase database = mongoClient.getDatabase("examen");

        MongoCollection<Document> collection = database.getCollection("examenes");
        MongoCollection<Document> collectionResp = database.getCollection("respuestas");
        collection.drop();
        collectionResp.drop();

        //Crear documentos 
        Document examenA = new Document("nameExam", "A");
        Document examenB = new Document("nameExam", "B");
        Document examenC = new Document("nameExam", "C");

        //Crear preguntas
        Document pregunta1 = new Document("id", "p1")
                .append("tipoPregunta", "select")
                .append("preguntaTexto", "Si explota un avión, ¿por dónde saldrías?");

        Document respuesta1 = new Document("0", "a) Por la puerta de emergencia")
                .append("1", "b) Por una ventanilla")
                .append("2", "c) Por las noticias");
        pregunta1.append("respOpciones", respuesta1);

        Document pregunta2 = new Document("id", "p2")
                .append("tipoPregunta", "text")
                .append("preguntaTexto", "Te la digo, y no la entiendes; te la vuelvo a repetir y por mucho que te la diga, no la sabes escribir. ¿Qué es?");

        Document pregunta3 = new Document("id", "p3")
                .append("tipoPregunta", "radio")
                .append("preguntaTexto", "¿Qué es lo primero que hace una vaca cuando sale el Sol?");

        Document respuesta3 = new Document("0", "a) Ir a pastar")
                .append("1", "b) Sombra")
                .append("2", "c) Dormir");
        pregunta3.append("respOpciones", respuesta3);

        Document pregunta4 = new Document("id", "p4")
                .append("tipoPregunta", "select multiple")
                .append("preguntaTexto", "¿Cómo se llaman los tres chinos más pobres?");

        Document respuesta4 = new Document("0", "a) Chin lu")
                .append("1", "b) Chin gas")
                .append("2", "c) Chin pon")
                .append("3", "d) Chin agua");
        pregunta4.append("respOpciones", respuesta4);

        Document pregunta5 = new Document("id", "p5")
                .append("tipoPregunta", "checkbox")
                .append("preguntaTexto", "Laura, la madre de Lola, tiene 5 hijas. Cuatro de ellas se llaman Lala, Lele, Lili, Lolo. ¿Cuáles son los nombres de las 5 hermanas?");

        Document respuesta5 = new Document("0", "a) Laura")
                .append("1", "b) Lola")
                .append("2", "c) Lala")
                .append("3", "d) Lele")
                .append("4", "e) Lili")
                .append("5", "f) Lolo")
                .append("6", "g) Lulu");
        pregunta5.append("respOpciones", respuesta5);

        Document pregunta6 = new Document("id", "p6")
                .append("tipoPregunta", "select")
                .append("preguntaTexto", "Si hay un incendio en el zoo, ¿quién tiene la culpa?");

        Document respuesta6 = new Document("0", "a) Los leones")
                .append("1", "b) Las llamas")
                .append("2", "c) Los osos polares");
        pregunta6.append("respOpciones", respuesta6);

        Document pregunta7 = new Document("id", "p7")
                .append("tipoPregunta", "text")
                .append("preguntaTexto", "Si lo nombras, desaparece. Hablamos del ...");

        Document pregunta8 = new Document("id", "p8")
                .append("tipoPregunta", "radio")
                .append("preguntaTexto", "¿Qué es más rápido en la sabana?");

        Document respuesta8 = new Document("0", "a) Un guepardo")
                .append("1", "b) Un jaguar")
                .append("2", "c) El correcaminos")
                .append("3", "d) Una jirafa en un ferrari");
        pregunta8.append("respOpciones", respuesta8);

        Document pregunta9 = new Document("id", "p9")
                .append("tipoPregunta", "select multiple")
                .append("preguntaTexto", "Este banco está ocupado, por un padre y un hijo, el padre se llama Juan y el hijo ya te lo he dicho. ¿Quiénes están sentados en el banco?");

        Document respuesta9 = new Document("0", "a) Juan")
                .append("1", "b) El banco está pintado, no hay nadie sentado")
                .append("2", "c) Una señora alimentando palomas")
                .append("3", "d) Esteban")
                .append("4", "e) Carlos");
        pregunta9.append("respOpciones", respuesta9);

        Document pregunta10 = new Document("id", "p10")
                .append("tipoPregunta", "checkbox")
                .append("preguntaTexto", "Valore este examen (sabiendo que una mala valoración podría bajar su nota...)");

        Document respuesta10 = new Document("0", "a) Es genial")
                .append("1", "b) Increíble")
                .append("2", "c) Malísimo (PISTA: Esta no la marques...)");

        pregunta10.append("respOpciones", respuesta10);

        //Crear array preguntas !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        List<Document> preguntasA = new ArrayList<>();
        preguntasA.add(pregunta1);
        preguntasA.add(pregunta2);
        preguntasA.add(pregunta3);
        preguntasA.add(pregunta4);
        preguntasA.add(pregunta5);

        List<Document> preguntasB = new ArrayList<>();
        preguntasB.add(pregunta2);

        List<Document> preguntasC = new ArrayList<>();
        preguntasC.add(pregunta3);

        //Añadir array preguntas a los examenes
        examenA.append("preguntas", preguntasA);

        examenB.append("preguntas", preguntasB);

        examenC.append("preguntas", preguntasC);

        List<Document> docs = new ArrayList<>();
        docs.add(examenA);
        docs.add(examenB);
        docs.add(examenC);

        //Insert document
        collection.insertMany(docs);

        //Añadir respuestas
        pregunta1.append("respCorrecta", "2");
        pregunta2.append("respCorrecta", "tela");
        pregunta3.append("respCorrecta", "1");
        pregunta4.append("respCorrecta", Arrays.asList("0", "1", "3"));
        pregunta5.append("respCorrecta", Arrays.asList("1", "2", "3", "4", "5"));
        pregunta6.append("respCorrecta", "1");
        pregunta7.append("respCorrecta", "silencio");
        pregunta8.append("respCorrecta", "3");
        pregunta9.append("respCorrecta", Arrays.asList("0", "3"));
        pregunta10.append("respCorrecta", Arrays.asList("0", "1"));

        List<Document> docsResp = new ArrayList<>();
        docsResp.add(pregunta1);
        docsResp.add(pregunta2);
        docsResp.add(pregunta3);
        docsResp.add(pregunta4);
        docsResp.add(pregunta5);
        docsResp.add(pregunta6);
        docsResp.add(pregunta7);
        docsResp.add(pregunta8);
        docsResp.add(pregunta9);
        docsResp.add(pregunta10);

        //Insert document
        collectionResp.insertMany(docsResp);

        mongoClient.close();
    }

    private String checkExamsAvailables(String dni) {
        try {
            MongoClientURI uri = new MongoClientURI(
                    "mongodb+srv://admin:admin@examendb-wge65.mongodb.net/test");
            MongoClient mongoClient = new MongoClient(uri);

            MongoDatabase database = mongoClient.getDatabase("examen");

            MongoCollection<Document> collection = database.getCollection("notas");

            //Search document
            MongoCursor<Document> myDoc = collection.find(eq("dni", dni)).projection(fields(include("nameExam"), excludeId())).iterator();
            ArrayList<String> examsName = new ArrayList();
            examsName.add("A");
            examsName.add("B");
            examsName.add("C");

            while (myDoc.hasNext()) {
                switch (myDoc.next().toJson()) {
                    case "{ \"nameExam\" : \"A\" }":
                       examsName.remove(examsName.indexOf("A"));
                        break;
                    case "{ \"nameExam\" : \"B\" }":
                        examsName.remove(examsName.indexOf("B"));
                        break;
                    case "{ \"nameExam\" : \"C\" }":
                        examsName.remove(examsName.indexOf("C"));
                        break;
                    default:
                        break;
                }
            }
            if (examsName.isEmpty()) {
                return "{\"mess\":\"No tienes examenes disponibles.\"}";
            }

            String docAux = "[ ";

            for (int i = 0; i < examsName.size(); i++) {
                docAux += "{ \"nameExam\" : \"" + examsName.get(i) + "\" },";
            }

            docAux = docAux.substring(0, docAux.length() - 1);
            docAux += "]";

            myDoc.close();
            mongoClient.close();
            return docAux;
        } catch (Exception e) {
            return "{\"error\":\"Error al cargar los examenes\"}";
        }
    }

}
