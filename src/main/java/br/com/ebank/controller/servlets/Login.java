package br.com.ebank.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.ebank.dao.ClienteDAO;
import br.com.ebank.extras.Util;

@WebServlet("/login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	
    	String logout_str = request.getParameter("logout");
    	String ad_str = request.getParameter("ad"); 
    	Object idUs_obj = request.getSession().getAttribute("idUs");
    	String page = "./";
    	
    	if(logout_str != "") {
    		if(Boolean.parseBoolean(logout_str)) {
    			request.getSession().invalidate();
    		}
     	}
    	
    	if(ad_str != null) {
    		if(Boolean.parseBoolean(ad_str)) {
    			request.setAttribute("le", true);
    			request.setAttribute("msg", "Acesso Restrito. Faça o login!");
    		}
    	}
    	
    	if(idUs_obj != null) {
    		page = "./home.jsp";
    	}
    	
    	request.getRequestDispatcher(page).forward(request, response);
    	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String ag_str = request.getParameter("ag");
		String cc_str = request.getParameter("cc");
		String pwd = request.getParameter("pwd");
		String page = "index.jsp";
		
		ClienteDAO cd = new ClienteDAO();
		
		if(Util.validaParametros(new String[] {ag_str,cc_str,pwd})) {
		
			int idUs = cd.login(Integer.parseInt(ag_str), Integer.parseInt(cc_str), pwd);
			
			if(idUs != -1) {
				
				HttpSession s = request.getSession();
				s.setAttribute("idUs", idUs);
				
				page = "home.jsp";
				
			}else {
				
				request.setAttribute("le", true);
				request.setAttribute("msg", "Agência/Conta/Senha inválidos!");
				
			}
			
			
		}else {
			
			request.setAttribute("di", true);
			request.setAttribute("msg", "Dados incompletos!");
			
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	
	}

}
