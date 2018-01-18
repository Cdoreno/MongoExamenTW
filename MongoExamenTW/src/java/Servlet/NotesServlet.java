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
public class NotesServlet extends HttpServlet { 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://admin:admin@examendb-wge65.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        MongoDatabase database = mongoClient.getDatabase("examen");

        MongoCollection<Document> collection = database.getCollection("notas");
        //Search document
        MongoCursor<Document> myDoc = collection.find().iterator();

        //Send document
        response.setContentType("application/json");
        try (PrintWriter pw = response.getWriter()) {
            String docAux = "[ ";
            while (myDoc.hasNext()) {
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
        String examName = request.getParameter("examName");
        String notaValor = request.getParameter("nota");
        
         MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://admin:admin@examendb-wge65.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        MongoDatabase database = mongoClient.getDatabase("examen");

        MongoCollection<Document> collection = database.getCollection("notas");

        //Crear notas
        Document nota = new Document("dni", dni)
                .append("examName", examName)
                .append("nota", notaValor);
     
        //Insert document
        collection.insertOne(nota);

        mongoClient.close();
        
    }

}
