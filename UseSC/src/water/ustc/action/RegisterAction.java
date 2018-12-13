package water.ustc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import water.ustc.useMysql.LoginMysql;

public class RegisterAction extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public String handleRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginMysql userLogin = new LoginMysql();
        HttpSession session = request.getSession();
        if (userLogin.select(username, password) != null && !userLogin.select(username, password).isEmpty()) {
            session.setAttribute("registMessage", "用户已存在,请重新登录！");
            return "failure";
        } else {
        	userLogin.insert(username, password);
            session.setAttribute("username", username);
            return "success";
        }
    }
}