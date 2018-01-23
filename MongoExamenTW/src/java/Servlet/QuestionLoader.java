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
public class QuestionLoader extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //String dni = (String) request.getAttribute("dni"); ESTO EN TEOIA NO LO NECESITO PARA CAGAR LAS PREGUNTAS DEL EXAMEN
        String examName = "A";//(String) request.getAttribute("examName"); CAMBIAR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://admin:admin@examendb-wge65.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        MongoDatabase database = mongoClient.getDatabase("examen");

        MongoCollection<Document> collection = database.getCollection("examenes");

        try (PrintWriter pw = response.getWriter()) {
            //Search document       
            Document myDoc = collection.find(eq("nameExam", examName)).projection(fields(include("preguntas"), excludeId())).first();
            String docAux = myDoc.toJson();

            docAux = docAux.substring(16, docAux.length() - 1);
            //Send document
            response.setContentType("application/json");

            pw.println(docAux);
        } finally {

            mongoClient.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String examName = request.getParameter("nameExam");
        String dni = request.getParameter("dni");

        request.setAttribute("dni", dni);
        request.setAttribute("examName", examName);

        RequestDispatcher a = request.getRequestDispatcher("/exam.jsp");
        a.forward(request, response);
    }

}
