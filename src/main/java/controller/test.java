/*
package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


@WebServlet("/statement/statement-money")
public class test extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }
}

class test2 {

    //логика получения соединения и отправки запроса

    public void sendRequest() throws IOException {
        String query = String.format("from=%s&to=%s",
                URLEncoder.encode("fromValue", "UTF-8"),
                URLEncoder.encode("toValue", "UTF-8"));
        String servletUrl = "http://localhost:8080";
        URL url = new URL(servletUrl + "?" + query);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");

    }
}*/
