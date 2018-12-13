package sc.ustc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sc.ustc.analyzeXML.analyzeXML;


public class SimpleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	response.setContentType("text/html;charset=utf-8");
    	response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String getUrl = request.getServletPath().toString();
        String[] splitUrl = getUrl.split("/");
        String actionName = splitUrl[splitUrl.length - 1].substring(0,splitUrl[splitUrl.length - 1].indexOf("."));
        String path = this.getServletContext().getRealPath("WEB-INF/classes/controller.xml");
        analyzeXML hzx = new analyzeXML();
        Map<String, String> xmlMap = hzx.readXML(actionName,path);
        if (!xmlMap.isEmpty()) {
            String className = xmlMap.get("class");
            String methodName = xmlMap.get("method");
            try {
            	
                Class cl = Class.forName(className);
                Method m = cl.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                String result = (String) m.invoke(cl.newInstance(), request, response);

                String resName = xmlMap.get("result" + result);
                String resType = resName.substring(0, resName.indexOf("+"));
                String resValue = resName.substring(resName.indexOf("+") + 1);

                if (resType.equals("foward")) {
                    request.getRequestDispatcher(resValue).forward(request, response);
                } else if (resType.equals("redirect")) {
                    response.sendRedirect(resValue);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
				e.printStackTrace();
			}
        } else {
            response.sendRedirect("/UseSC/Login.jsp");
        }
    }
}
