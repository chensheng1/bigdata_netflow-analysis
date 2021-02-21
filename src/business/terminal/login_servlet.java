package business.terminal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class login_servlet extends HttpServlet {

		public void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setCharacterEncoding("utf-8");
	        //获取用户名和密码
	        String username = request.getParameter("username");
	        
	        String password = request.getParameter("password");
	        //获取UserDao实例
	        login cs=new login();

	        boolean result = cs.test(username,password);
	        // 判断user是否为空
	        if(result == true){
	        //  getRequestDispatcher()是请求转发
	                request.getRequestDispatcher("index.html").forward(request, response);
	        }else{
	        // 登录失败
	        	response.sendRedirect("login.jsp?error=yes");
	       //     request.getRequestDispatcher("login.jsp").forward(request, response);
	                }
	        }

	    public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }

	}

