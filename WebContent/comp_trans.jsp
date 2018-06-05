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
	Object obj_valor = request.getAttribute("vc");
	Object obj_ag = request.getAttribute("agc");
	Object obj_cc = request.getAttribute("ccc");
	Object obj_cpf = request.getAttribute("cpfc");
	Object obj_nome = request.getAttribute("nmc");
	Object obj_st = request.getAttribute("st");
	Object obj_msg = request.getAttribute("msg_success");
	
	String msg = "";
	
	if(obj_valor == null || obj_ag == null || obj_cc == null 
	|| obj_cpf == null || obj_nome == null){
		request.getRequestDispatcher("trans.jsp").forward(request, response);
	}
	
	if(Boolean.parseBoolean(obj_st!=null?obj_st.toString():"false")){
		msg = obj_msg!=null?obj_msg.toString():msg;
	}
%> 		

<h3>COMPROVANTE DE TRANSFERÊNCIA</h3>

<fieldset>

	<label><b>Nome:</b></label>&nbsp<i><%= obj_nome != null?obj_nome.toString():""%></i><br/><br/>
	
	<label><b>Agência:</b></label>&nbsp<i><%=obj_ag!=null?obj_ag.toString():""%></i><br/>
	
	<label><b>Conta:</b></label>&nbsp<i><%=obj_cc!=null?obj_cc.toString():""%></i><br/>
	
	<label><b>CPF:</b></label><i>&nbsp<%=obj_cpf!=null?obj_cpf.toString():""%></i><br/>
	
	<label><b>Valor:</b><i>
	<%=obj_valor!=null?Util.convertePraReais(Double.parseDouble(obj_valor.toString())):""%></i>		
	
</fieldset>

<br/>

<button><a href="/login">Ok</a></button>	
    	
<p class="success"><b><%=msg%></b></p>
    	
<jsp:include page="./master/footer.jsp"></jsp:include>