package com.mkyong.request;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.rythmengine.Rythm;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseRequest implements HttpHandler {
    File template = null;

    protected Map<String, Object> params = null;
    protected ApplicationContext appContext =
            new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

    Map<String, Object> dataView = new HashMap<String, Object>();
    protected HttpExchange httpExchange;
    public BaseRequest(HttpExchange httpExchange, String templateFilePath) throws IOException {
        this.httpExchange = httpExchange;
        OutputStream os = httpExchange.getResponseBody();
        params =(Map<String, Object>)httpExchange.getAttribute("parameters");

        // use Map to store the configuration
        template = new File(templateFilePath);

        //Set content type
        httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");

        //do Logic business
        int responseCode = doProcess();
        putToView("error", responseCode);
        String response = "";
        if (getMethod().equalsIgnoreCase("GET")){
            response = renderGet();
        }else{
            response = renderPost();
        }

        httpExchange.sendResponseHeaders(responseCode, response.getBytes().length);
        System.out.println(response);

        os.write(response.getBytes("UTF-8"));
        os.close();
    }

    private String renderPost() {
        return new Gson().toJson(dataView);
    }

    private String renderGet() {
        String response = Rythm.render(template, dataView);
        return response;
    }


    protected int doProcess() {
        if (getMethod().equalsIgnoreCase("GET")){
            return doGet();
        }else{
            return doPost();
        }
    }

    protected String getMethod(){
        return httpExchange.getRequestMethod();
    }

    abstract protected int doGet();
    abstract protected int doPost();

    protected void putToView(String name, Object obj){
        dataView.put(name, obj);
    }

    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
