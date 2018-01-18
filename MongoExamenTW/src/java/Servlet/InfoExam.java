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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://admin:admin@examendb-wge65.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        MongoDatabase database = mongoClient.getDatabase("examen");

        MongoCollection<Document> collection = database.getCollection("examenes");
        //Search document
        MongoCursor<Document> myDoc = collection.find().iterator();

        //Send document
        response.setContentType("application/json");
        try (PrintWriter pw = response.getWriter()) {
            String docAux = "[ ";
            while (myDoc.hasNext()) {
                //pw.println(myDoc.next().toJson());
                docAux += myDoc.next().toJson();
                docAux += ",";
            }
            docAux = docAux.substring(0, docAux.length() - 1);
            docAux += "]";
            pw.println(docAux);
        } finally {
            myDoc.close();
            mongoClient.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String examName = "A";//request.getParameter(dni);

        //Mas adelante meter comprobación de si ha hecho el examen o no.
        request.setAttribute("dni", dni);
        request.setAttribute("examName", examName);

        RequestDispatcher a = request.getRequestDispatcher("/index.html");
        a.forward(request, response);
    }

    @Override
    public void init() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://admin:admin@examendb-wge65.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        MongoDatabase database = mongoClient.getDatabase("examen");

        MongoCollection<Document> collection = database.getCollection("examenes");
        collection.drop();
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
        pregunta1.append("respCorrecta", "2");

        Document pregunta2 = new Document("id", "p2")
                .append("tipoPregunta", "text")
                .append("preguntaTexto", "Te la digo, y no la entiendes; te la vuelvo a repetir y por mucho que te la diga, no la sabes escribir. ¿Qué es?");

        pregunta2.append("respCorrecta", "tela");

        Document pregunta3 = new Document("id", "p3")
                .append("tipoPregunta", "radio")
                .append("preguntaTexto", "¿Qué es lo primero que hace una vaca cuando sale el Sol?");

        Document respuesta3 = new Document("0", "Ir a pastar")
                .append("1", "Sombra")
                .append("2", "Dormir");
        pregunta3.append("respOpciones", respuesta3);
        pregunta3.append("respCorrecta", "1");

        //Crear array preguntas
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

        mongoClient.close();
    }

}
