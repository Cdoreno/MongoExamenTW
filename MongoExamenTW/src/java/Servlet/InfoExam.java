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

        Document respuesta1 = new Document("0", "Por la puerta de emergencia")
                .append("1", "Por una ventanilla")
                .append("2", "Por las noticias");
        pregunta1.append("respOpciones", respuesta1);
        

        Document pregunta2 = new Document("id", "p2")
                .append("tipoPregunta", "text")
                .append("preguntaTexto", "Te la digo, y no la entiendes; te la vuelvo a repetir y por mucho que te la diga, no la sabes escribir. ¿Qué es?");

        
        Document pregunta3 = new Document("id", "p3")
                .append("tipoPregunta", "radio")
                .append("preguntaTexto", "¿Qué es lo primero que hace una vaca cuando sale el Sol?");

        Document respuesta3 = new Document("0", "Ir a pastar")
                .append("1", "Sombra")
                .append("2", "Dormir");
        pregunta3.append("respOpciones", respuesta3);
        

        //Crear array preguntas !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        List<Document> preguntasA = new ArrayList<Document>();
        preguntasA.add(pregunta1);

        List<Document> preguntasB = new ArrayList<Document>();
        preguntasB.add(pregunta2);

        List<Document> preguntasC = new ArrayList<Document>();
        preguntasC.add(pregunta3);
        preguntasC.add(pregunta2);
        preguntasC.add(pregunta1);    

        //Añadir array preguntas a los examenes
        examenA.append("preguntas", preguntasA);

        examenB.append("preguntas", preguntasB);

        examenC.append("preguntas", preguntasC);
        
        List<Document> docs = new ArrayList<Document>();
        docs.add(examenA);
        docs.add(examenB);
        docs.add(examenC);

        //Insert document
        collection.insertMany(docs);
        
        //Añadir respuestas
        pregunta1.append("respCorrecta", "2");
        pregunta2.append("respCorrecta", "tela");
        pregunta3.append("respCorrecta", "1");
        
        List<Document> docsResp = new ArrayList<Document>();
        docsResp.add(pregunta1);
        docsResp.add(pregunta2);
        docsResp.add(pregunta3);

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

            String docAux = "[ ";

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
