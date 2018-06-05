<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="br.com.ebank.model.Cliente"
	import="br.com.ebank.dao.ClienteDAO"
	import="br.com.ebank.extras.Util"
%>

<% 
	
	Cliente c = null; 

	Object obj_id = request.getSession().getAttribute("idUs");
	
	if(obj_id != null){
	
		int idUs = Integer.parseInt(obj_id.toString());
		
		try{
			c = new ClienteDAO().findById(idUs);
		}catch(Exception e){
			e.printStackTrace();
			request.getRequestDispatcher("/login?ad=true").forward(request, response);
		}
		
	}else{
		request.getRequestDispatcher("/login?ad=true").forward(request, response);
	}

	
	
%>

<p>Olá <b><%=c!=null?c.getNome():""%></b> <a href="./login?logout=true">Sair</a></p>

<ul>
	<li><a href="./home.jsp">Página Inicial</a></li>
	<li><a href="./trans.jsp">Transferência</a></li>
</ul>

<p>SALDO:  <span id="saldo"><%=c!=null?Util.convertePraReais(c.getSaldo()):""%></span> </p>