package com.qlbv.handler;

import com.qlbv.common.Helper;
import com.qlbv.request.login.LoginRequest;
import com.qlbv.request.login.WelcomeRequest;
import com.qlbv.request.medical.MedicalRequest;
import com.qlbv.request.medical.RegistPatientRequest;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class MedicalHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        String action = Helper.getAction(httpExchange);
        switch (action.toLowerCase()){
            case "index":
                new MedicalRequest(httpExchange, "medical/index.html");
                break;
            case "regist":
                new RegistPatientRequest(httpExchange, "medical/regist.html");
                break;
            default:
                new MedicalRequest(httpExchange, "medical/index.html");
                break;
        }
    }
}
