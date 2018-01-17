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
        try (PrintWriter out = response.getWriter()){
            while (myDoc.hasNext()) {
                out.println(myDoc.next().toJson());
            }
        } finally {
            myDoc.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

     @Override
    public void init() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://admin:admin@examendb-wge65.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        MongoDatabase database = mongoClient.getDatabase("examen");

        MongoCollection<Document> collection = database.getCollection("examenes");

        //Crear documentos 
        Document examenA = new Document("nameExam", "A");
        Document examenB = new Document("nameExam", "B");

        Document pregunta = new Document("pregunta", "select-1")
                .append("tipo", "select")
                .append("titulo", "¿Cuántos centímetros tiene un metro?");

        Document respuesta = new Document("h", 28)
                .append("0", 10)
                .append("1", 100)
                .append("2", 100);
        pregunta.put("respuesta", respuesta);
        examenA.put("pregunta", pregunta);
        examenB.put("pregunta", pregunta);
        
        List <Document> docs = new ArrayList<Document>();
        docs.add(examenA);
        docs.add(examenB);

        //Insert document
        collection.insertMany(docs);
    }

}
