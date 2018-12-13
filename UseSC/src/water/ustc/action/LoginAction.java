package water.ustc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import water.ustc.useMysql.LoginMysql;

public class LoginAction extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public String handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginMysql userLogin = new LoginMysql();
        HttpSession session = request.getSession();
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userLogin.select(username, password) != null && !userLogin.select(username, password).isEmpty()) {

            session.setAttribute("username", username);
            return "success";
        } else {
            session.setAttribute("loginMessage", "用户名与密码不匹配!");
            return "failure";
        }
	}

}
