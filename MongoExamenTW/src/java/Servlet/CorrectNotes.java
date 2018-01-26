package Servlet;

import Model.Respuesta;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.bulk.WriteRequest.Type;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.Document;

public class CorrectNotes extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        Gson gson = new Gson ();
        @SuppressWarnings("serial")
        java.lang.reflect.Type collectionType = new TypeToken<List<Respuesta>>() {
        }.getType();
        List<Respuesta> navigation = gson.fromJson(request.getParameter("respuesta"), collectionType);


//        Gson gson = new Gson();
//        Respuesta[] respuesta = gson.fromJson(request.getParameter("respuestas"), Respuesta[].class);
//        System.out.println(respuesta.toString());
//        System.out.println(respuesta.toString());
//        MongoClientURI uri = new MongoClientURI(
//                "mongodb+srv://admin:admin@examendb-wge65.mongodb.net/test");
//        MongoClient mongoClient = new MongoClient(uri);
//        MongoDatabase database = mongoClient.getDatabase("examen");
//        MongoCollection<Document> collection = database.getCollection("respuestas");
//
//        List<Document> respuestas = (List<Document>) collection.find().into(new ArrayList<>());
    }
}
