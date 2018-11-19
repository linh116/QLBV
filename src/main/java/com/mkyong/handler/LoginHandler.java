package com.mkyong.handler;

import com.mkyong.request.login.LoginRequest;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class LoginHandler  implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        new LoginRequest(httpExchange, "resources/template/login.html");
    }
}
