package com.qlbv.request.login;

import com.qlbv.bo.BoManager;
import com.qlbv.bo.MedicalRecordBo;
import com.qlbv.bo.UserBo;
import com.qlbv.common.Constant;
import com.qlbv.common.Logger;
import com.qlbv.framework.SessionManager;
import com.qlbv.model.User;
import com.qlbv.request.BaseRequest;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class LoginRequest extends BaseRequest {
    static Logger _logger = new Logger();

    public LoginRequest(HttpExchange httpExchange, String templateFilePath) throws IOException {
        super(httpExchange, templateFilePath);
    }

    protected int doGet() {
        String sessionUUID = getCookie(Constant.SESSIONID_COOKIENAME);
        if (SessionManager.getSession(sessionUUID) != null){
            //TODO redirect to page welcome
            _logger.info("has session, so need to redirect to welcome page");
            respondRedirect(httpExchange, "/login/welcome");
        }
        return 200;
    }

    protected int doPost() {
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        UserBo userBo = (UserBo) BoManager.getAppContext().getBean("userBo");
        User user = userBo.findByUserNameAndPassword(username, password);
        if(user != null){
            putToView("user", user);
            setCookieSession(user);

        }else{
            putErrorToView("Không tồn tại user");
        }
        return 200;
    }
}
