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

@WebServlet("/ctrans")
public class ConfTrans extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	
    	Object obj_id = request.getSession().getAttribute("idUs"); 
    	
    	if(obj_id == null)
    		request.getRequestDispatcher("/login?ad=true").forward(request, response);
    	
    	request.getRequestDispatcher("/trans.jsp").forward(request, response);
    	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		Object obj_id = request.getSession().getAttribute("idUs"); 
		Cliente cliOn = null;
		ClienteDAO cd = new ClienteDAO();
    	
    	if(obj_id == null)
    		request.getRequestDispatcher("/login?ad=true").forward(request, response);
    	else
    		cliOn = cd.findById(Integer.parseInt(obj_id.toString())); 
		
		String str_valor = request.getParameter("valor");
		String str_ag = request.getParameter("ag");
		String str_cc = request.getParameter("cc");
		String cpf = request.getParameter("cpf");
		String senha = Util.md5(request.getParameter("pwd"));
		
		String page = "trans.jsp";
		
		if(Util.validaParametros(new String[] {
				str_valor,
				str_ag,
				str_cc,
				cpf,
				senha
		})) {
			
			double valor = Double.parseDouble(str_valor);
			int ag = Integer.parseInt(str_ag);
			int cc = Integer.parseInt(str_cc);
			
			if(senha.equals(cliOn.getSenha())) {
				if(cd.debitar(cliOn.getConta(), valor)
				&& cd.creditar(cc, valor)) {
					
					request.setAttribute("st", true);
					request.setAttribute("msg_success", "Transação concluída com sucesso!");
					
					//dados 
					request.setAttribute("vc", valor);
					request.setAttribute("agc", ag);
					request.setAttribute("ccc", cc);
					request.setAttribute("cpfc", cpf);
					request.setAttribute("nmc", cd.getNome(cpf));
					
					page = "comp_trans.jsp";
					
				}else {
					
					request.setAttribute("et", true);
					request.setAttribute("msg_error", "Erro interno. A transação não pode ser concluída.!");
					
				}
				
				
			}else {
				
				request.setAttribute("et", true);
				request.setAttribute("msg_error", "Senha incorreta!");
				
			}
			
		}else {
			
			request.setAttribute("et", true);
			request.setAttribute("msg_error", "Erro interno. Transação não concluída.");
			
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}

}
