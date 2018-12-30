package com.qlbv.handler;

import com.qlbv.common.Helper;
import com.qlbv.request.login.LoginRequest;
import com.qlbv.request.login.WelcomeRequest;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URI;

public class LoginHandler  implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        String action = Helper.getAction(httpExchange);
        switch (action.toLowerCase()){
            case "index":
                new LoginRequest(httpExchange, "login.html");
                break;
            case "welcome":
                new WelcomeRequest(httpExchange, "dashboard.html");
                break;
            default:
                new LoginRequest(httpExchange, "login.html");
                break;
        }
    }
}
