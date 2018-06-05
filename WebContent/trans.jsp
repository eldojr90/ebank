<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="br.com.ebank.model.Cliente"
    import="br.com.ebank.dao.ClienteDAO"
%>

<jsp:include page="./master/shl.jsp">
	<jsp:param value="Transferência" name="titulo"/>
</jsp:include>

<% 
	Object id_obj = request.getSession().getAttribute("idUs");
	Object error_t = request.getAttribute("et");
	Object msg_obj = request.getAttribute("msg_error");
	
	String msg = "";
	
	Cliente c = null;
	
	if(id_obj != null){
		c = new ClienteDAO().findById(Integer.parseInt(id_obj.toString()));
	}
	
	if(error_t != null){
		if(Boolean.parseBoolean(error_t.toString())){
			if(msg_obj != null){
				msg = msg_obj.toString();
			}
		}
	}
%>

<h3>TRANSFERÊNCIA</h3>

<form action="./trans" method="POST">
	
	<fieldset>
		<legend>Valor</legend>
		<input type="number" name="valor" min="5" 
			   max="<%= c!=null?(int) c.getSaldo():""%>" step=".01" autofocus><br/><br/>
	</fieldset>
	
	<fieldset>
		<legend>Dados da conta de destino</legend>
		<input type="number" name="ag" placeholder="Agência"><br/><br/>
		<input type="number" name="cc" placeholder="Conta Corrente"><br/><br/>
		<input type="text" name="cpf" placeholder="CPF"><br/><br/>
	</fieldset>
	<br/>
	<input type="submit" value="Prosseguir">
	
</form>
<br/>
<br/>
<p class="erro"><b><%=msg%></b></p>
<jsp:include page="./master/footer.jsp"></jsp:include>
