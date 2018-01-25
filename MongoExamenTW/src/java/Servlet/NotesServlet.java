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
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import com.mongodb.client.model.UpdateOptions;
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
        MongoCursor<Document> myDoc = collection.find().projection(fields(include("dni","nameExam","nota"), excludeId())).iterator();

        //Send document
        response.setContentType("application/json");
        try (PrintWriter pw = response.getWriter()) {
            String docAux = "[ ";
            while (myDoc.hasNext()) {
                docAux += this.enmascararDni(myDoc.next().toJson());
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
        
//        String dni = request.getParameter("dni");
//        String examName = request.getParameter("nameExam");
String examName = (String) request.getAttribute("examName");
String dni = (String) request.getAttribute("dni");
        String respUser = request.getParameter("respuestas");//ARRAY???????????????????????????????????
        
         MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://admin:admin@examendb-wge65.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        MongoDatabase database = mongoClient.getDatabase("examen");

        MongoCollection<Document> collection = database.getCollection("notas");

        int notaValor = calcularNota(respUser);
        
        //Crear notas
        Document nota = new Document("dni",dni )
                .append("nameExam", examName)
                .append("nota", "9");
     
        //Insert document
        MongoCursor<Document> myDocAux = collection.find(and(eq("dni", dni),eq("nameExam",examName))).iterator();
        if(myDocAux.hasNext()){
            collection.deleteOne(and(eq("dni", dni),eq("nameExam",examName)));
        }
        collection.insertOne(nota);

        mongoClient.close();
        
    }
    
    private int calcularNota(String s){
        return 0;
    }
    
    private String enmascararDni(String s){
        return s.replace(s.substring(11, 15),"****");
    }

}
