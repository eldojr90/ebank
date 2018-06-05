package br.com.ebank.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ebank.dao.ClienteDAO;
import br.com.ebank.extras.Util;
import br.com.ebank.model.Cliente;

@WebServlet("/saldo")
public class Saldo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/login").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object ob_idus = request.getSession().getAttribute("idUs"); 
		Cliente cliOn = null;
		
		if(ob_idus == null) 
			request.getRequestDispatcher("/login").forward(request, response);
		
		ClienteDAO cd = new ClienteDAO();
		cliOn = cd.findById(Integer.parseInt(ob_idus.toString()));
		
		String str_saldo = request.getParameter("verificarSaldo");
		
		if(str_saldo != null) {
			response.getWriter().write(Boolean.parseBoolean(str_saldo.toString())?
					Util.convertePraReais(cd.getSaldo(cliOn.getConta())):
					"Informar parâmetro \"verificar saldo\" com o valor booleano");

		}
		
									
	}

}
