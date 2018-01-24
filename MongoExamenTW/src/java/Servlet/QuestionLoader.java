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
import javax.servlet.http.Cookie;
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

        String cookieExam = "examName";
        Cookie[] cookies = request.getCookies();
        String examC = null;

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookieExam.equals(cookie.getName())) {
                    examC = cookie.getValue();
                }
            }
            if (examC == null) {
                examC="A";
            } 
        }
   
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://admin:admin@examendb-wge65.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        MongoDatabase database = mongoClient.getDatabase("examen");

        MongoCollection<Document> collection = database.getCollection("examenes");

       try (PrintWriter pw = response.getWriter()) {
            //Search document       
            Document myDoc = collection.find(eq("nameExam", examC)).projection(fields(include("preguntas"), excludeId())).first();
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

        Cookie dniCookie = new Cookie("dni", dni);
        Cookie examCookie = new Cookie("examName", examName);
        dniCookie.setMaxAge(5);
        examCookie.setMaxAge(5);
        dniCookie.setPath("/");
        examCookie.setPath("/");
        response.addCookie(dniCookie);
        response.addCookie(examCookie);
    }
}
