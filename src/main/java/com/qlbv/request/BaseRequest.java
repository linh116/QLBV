package com.qlbv.request;

import com.google.gson.Gson;
import com.qlbv.bo.BoManager;
import com.qlbv.common.Constant;
import com.qlbv.common.Logger;
import com.qlbv.framework.Session;
import com.qlbv.framework.SessionManager;
import com.qlbv.handler.filter.ParameterFilter;
import com.qlbv.model.User;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.lang3.StringUtils;
import org.rythmengine.Rythm;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BaseRequest implements HttpHandler {
    String template;
    protected Map<String, Object> params = null;
    protected User user;
    Map<String, Object> dataView = new HashMap<String, Object>();
    Map<String, Object> baseRequest = new HashMap<String, Object>();
    Logger LOGGER = new Logger();
    protected HttpExchange httpExchange;

    public BaseRequest(HttpExchange httpExchange, String templateFilePath) throws IOException {
        this.httpExchange = httpExchange;
        OutputStream os = httpExchange.getResponseBody();

        //setting rythm
        settingRythmEngine();

        //check session
        if (!hasSession() && !httpExchange.getRequestURI().getPath().equals("/login")) {
            respondRedirect(httpExchange, "/login");
        }

        //filter, get parameter
        ParameterFilter.doFilter(httpExchange);
        params = (Map<String, Object>) httpExchange.getAttribute("parameters");

        //init templateFile;
        this.template = templateFilePath;
        //Set content type
        httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");

        //do Logic business
        int responseCode = doProcess();
        baseRequest.put("responseCode", responseCode);
        String response = "";
        if (getMethod().equalsIgnoreCase("GET")) {
            response = renderGet();
        } else {
            response = renderPost();
        }

        httpExchange.sendResponseHeaders(responseCode, response.getBytes().length);

        os.write(response.getBytes("UTF-8"));
        os.close();
    }

    private void settingRythmEngine() {
    }

    private boolean hasSession() {
        Headers reqHeaders = httpExchange.getRequestHeaders();
        List<String> cookies = reqHeaders.get("Cookie");
        if (cookies != null) {
            for (String cookie : cookies) {
                if (cookie.contains(Constant.SESSIONID_COOKIENAME)) {
                    if (SessionManager.getAttribute("user") == null) {
                        String session = getCookie(Constant.SESSIONID_COOKIENAME);
                        Long userId = SessionManager.getSession(session);
                        User currentUser = BoManager.userBo.findUserById(userId);
                        if (currentUser == null) return false;
                        user = currentUser;
                        SessionManager.setAttribute("user", user);
                    } else {
                        user = (User) SessionManager.getAttribute("user");
                        putToView("user", user);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private void writeFile(String data){
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(""));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected String getCookie(String name) {
        Headers headers = httpExchange.getRequestHeaders();
        if (headers != null) {
            List<String> cookies = headers.get("Cookie");
            if (cookies != null) {
                for (String cookieString : cookies) {
                    String[] tokens = cookieString.split("\\s*;\\s*");
                    for (String token : tokens) {
                        if (token.startsWith(name) && token.charAt(name.length()) == '=') {
                            return token.substring(name.length() + 1);
                        }
                    }
                }
            }
        }
        return null;
    }

    private String renderPost() {
        baseRequest.put("data", dataView);
        return new Gson().toJson(baseRequest);
    }

    private static String getResourceFileAsString(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (Stream stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> stringBuilder.append(s));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return stringBuilder.toString();

//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
//            if (is == null) return null;
//            try (InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
//                 BufferedReader reader = new BufferedReader(isr)) {
//                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
//            }
//        }
    }

    private String renderGet() {
        try {
            LOGGER.info("resources/rythm/" + template);

            String response;
            String resources = getResourceFileAsString("resources/rythm/" + template);
            if (StringUtils.isNotEmpty(resources)){
                response = Rythm.renderString(resources, dataView);
            }else{
                LOGGER.info("Cannot found template file " + "resources/rythm/" + template);
                response = "Cannot found template file";
            }


            return response;
        } catch (Exception e) {
            LOGGER.info("Exception" + e);
            e.printStackTrace();
            return "null";
        }
    }


    protected int doProcess() {
        if (getMethod().equalsIgnoreCase("GET")) {
            return doGet();
        } else {
            return doPost();
        }
    }

    protected String getMethod() {
        return httpExchange.getRequestMethod();
    }

    abstract protected int doGet();

    abstract protected int doPost();

    protected void putErrorToView(Object error) {
        putToView("errorMsg", error);
    }

    protected void putToView(String name, Object obj) {
        dataView.put(name, obj);
    }

    public static void respondRedirect(HttpExchange exchange, String whereTo) {
        String hostPort = exchange.getRequestHeaders().get("HOST").get(0);
        exchange.getResponseHeaders().set("Location", "http://" + hostPort + whereTo);
        try {
            exchange.sendResponseHeaders(303, -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        exchange.close();
    }

    public void handle(HttpExchange httpExchange) {

    }

    protected void setCookieSession(User user) {
        Headers responseHeaders = httpExchange.getResponseHeaders();
        Session session = new Session(user);
        responseHeaders.set("Set-Cookie", String.format("%s=%s; path=/", Constant.SESSIONID_COOKIENAME, session.getId()));
        SessionManager.putSession(session.getId(), user.getUserId());
        SessionManager.setAttribute("user", user);
        this.user = user;
    }
}
