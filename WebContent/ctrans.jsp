<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="br.com.ebank.extras.Util"
	import="br.com.ebank.model.Cliente"
%>

<jsp:include page="./master/shl.jsp">
	<jsp:param value="Confirmação" name="titulo"/>
</jsp:include>

<%
	Object obj_valor = request.getAttribute("vt");
	Object obj_ag = request.getAttribute("agt");
	Object obj_cc = request.getAttribute("cct");
	Object obj_cpf = request.getAttribute("cpft");
	Object obj_nome = request.getAttribute("nmt");
	
	if(obj_valor == null || obj_ag == null || obj_cc == null 
	|| obj_cpf == null || obj_nome == null){
		request.getRequestDispatcher("trans.jsp").forward(request, response);
	}	
	
%> 		

<h3>CONFIRMAR TRANSFERÊNCIA</h3>

<form action="./ctrans" method="POST">
	
	<fieldset>
		<legend>Valor</legend>
		<i><%=obj_valor!=null?Util.convertePraReais(Double.parseDouble(obj_valor.toString())):""%></i>		
		<input 	type="number" name="valor" 
				value="<%=obj_valor!=null?obj_valor.toString():""%>" 
				readonly hidden><br/><br/>
	</fieldset>
	
	<br/>
	
	<fieldset>
		
		<legend>Dados da conta de destino</legend>
		
		<label><b>Nome:</b></label>&nbsp<i><%= obj_nome != null?obj_nome.toString():""%></i><br/><br/>
		
		<label><b>Agência:</b></label>&nbsp<i><%=obj_ag!=null?obj_ag.toString():""%></i><br/>
		
		<input 	type="number" name="ag"
				value="<%=obj_ag!=null?obj_ag.toString():""%>"
				readonly hidden><br/>
		
		<label><b>Conta:</b></label>&nbsp<i><%=obj_cc!=null?obj_cc.toString():""%></i><br/>
		
		<input 	type="number" name="cc" 
				value="<%=obj_cc!=null?obj_cc.toString():""%>"
				readonly hidden><br/>
		
		<label><b>CPF:</b></label><i>&nbsp<%=obj_cpf!=null?obj_cpf.toString():""%></i><br/>
		
		<input 	type="text" name="cpf" 
				value="<%=obj_cpf!=null?obj_cpf.toString():""%>"
				readonly hidden><br/>
				
	</fieldset>
	
	<br/>
	
	<fieldset>
		<legend>Senha</legend>
		<input 	type="password" name="pwd" autofocus><br/><br/>
	</fieldset>
	
	<br/>
	
	<input type="submit" value="Prosseguir">
	
</form>
    	
<jsp:include page="./master/footer.jsp"></jsp:include>