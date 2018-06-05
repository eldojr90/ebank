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

@WebServlet("/trans")
public class Transferencia extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	
    	Object ob_idUs = request.getSession().getAttribute("idUs");
		String page = "/login?ad=true";
    	
		if(ob_idUs != null)
			page = "/trans.jsp";
			
		request.getRequestDispatcher(page).forward(request, response);
		
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		Object ob_idUs = request.getSession().getAttribute("idUs");
		
		if(ob_idUs == null)
			request.getRequestDispatcher("/login?ad=true").forward(request, response);
		
		String str_valor = request.getParameter("valor");
		String str_ag = request.getParameter("ag");
		String str_cc = request.getParameter("cc");
		String cpf = request.getParameter("cpf");
		
		ClienteDAO cd = new ClienteDAO();
		
		Cliente cliOn = null;
		
		String page = "trans.jsp";
		
		if(ob_idUs != null) {
			if(Util.validaParametros(new String[] {str_valor,str_ag,str_cc,cpf})) {
				
				cliOn = cd.findById(Integer.parseInt(ob_idUs.toString()));
				
				double valor = Double.parseDouble(str_valor);
				int ag = Integer.parseInt(str_ag);
				int cc = Integer.parseInt(str_cc);
				
				if(!cliOn.getCpf().equals(cpf)) {
					if((cliOn.getAgencia() == ag  && cliOn.getConta() != cc) 
					 ||(cliOn.getAgencia() != ag  && cliOn.getConta() == cc)
					 ||(cliOn.getAgencia() != ag  && cliOn.getConta() != cc)) {
						if(cd.existeCpf(cpf)) {
							if(cd.existeConta(cc, ag)) {
								if(cd.saldoDisponivel(cliOn.getConta(), valor)) {
									
									request.setAttribute("nmt", cd.getNome(cpf));
									request.setAttribute("agt", ag);
									request.setAttribute("cct", cc);
									request.setAttribute("vt", valor);
									request.setAttribute("cpft", cpf);
									
									page = "ctrans.jsp";
									
								}else {
									
									request.setAttribute("et", true);
									request.setAttribute("msg_error", "Saldo indisponível!");
									
								}
								
							}else {
							
								request.setAttribute("et", true);
								request.setAttribute("msg_error", "Conta inexistente!");
								
							}
							
						}else {
							
							request.setAttribute("et", true);
							request.setAttribute("msg_error", "CPF inexistente!");
							
						}
					
					}else {
						
						request.setAttribute("et", true);
						request.setAttribute("msg_error", "Operação Inválida. Você informou SUA conta!");
						
					}
					
				}else {
					
					request.setAttribute("et", true);
					request.setAttribute("msg_error", "Operação Inválida. Você informou SEU CPF!");
					
				}
				
			}else {
				
				request.setAttribute("et", true);
				request.setAttribute("msg_error", "Dados incompletos!");
				
			}
				
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	
	}

}
