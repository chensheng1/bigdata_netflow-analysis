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
	        //��ȡ�û���������
	        String username = request.getParameter("username");
	        
	        String password = request.getParameter("password");
	        //��ȡUserDaoʵ��
	        login cs=new login();

	        boolean result = cs.test(username,password);
	        // �ж�user�Ƿ�Ϊ��
	        if(result == true){
	        //  getRequestDispatcher()������ת��
	                request.getRequestDispatcher("index.html").forward(request, response);
	        }else{
	        // ��¼ʧ��
	        	response.sendRedirect("login.jsp?error=yes");
	       //     request.getRequestDispatcher("login.jsp").forward(request, response);
	                }
	        }

	    public void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }

	}

