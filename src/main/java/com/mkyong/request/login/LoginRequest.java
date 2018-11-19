package com.mkyong.request.login;

import com.mkyong.bo.UserBo;
import com.mkyong.dao.UserDao;
import com.mkyong.model.User;
import com.mkyong.request.BaseRequest;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class LoginRequest extends BaseRequest {

    public LoginRequest(HttpExchange httpExchange, String templateFilePath) throws IOException {
        super(httpExchange, templateFilePath);
    }

    protected int doGet() {
        return 200;
    }

    protected int doPost() {


        UserBo userBo = (UserBo) appContext.getBean("userBo");
        User user = userBo.findByUserNameAndPassword("linhnd32", "admin");
        if(user != null){
            System.out.println(user);
            putToView("userId", user.getUserId());
            putToView("userName", user.getUsername());
        }else{
            putToView("errorMsg", "Không tồn tại user");
            return 404;
        }

        return 200;
    }
}
