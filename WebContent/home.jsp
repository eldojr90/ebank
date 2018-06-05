<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="br.com.ebank.extras.Util"
	import="br.com.ebank.model.Cliente"
	import="br.com.ebank.dao.ClienteDAO"
%>

<jsp:include page="./master/shl.jsp">
	<jsp:param value="Principal" name="titulo"/>
</jsp:include>

<% 
	Cliente c = null;
	
	Object id_obj = request.getSession().getAttribute("idUs");
	Object ts_obj = request.getAttribute("ts");
	Object te_obj = request.getAttribute("te");
	Object ms_obj = request.getAttribute("msg_sucess");
	Object me_obj = request.getAttribute("msg_error");
	
	if(id_obj != null){
		
		int idUs = Integer.parseInt(id_obj.toString());
		c = new ClienteDAO().findById(idUs);
		
	}
	
	String msg = "";
	
	if(ts_obj != null){
		if(Boolean.parseBoolean(ts_obj.toString())){
			msg = ms_obj != null? ms_obj.toString():msg; 
		}
	}
	
	if(te_obj != null){
		if(Boolean.parseBoolean(te_obj.toString())){
			msg = me_obj != null? me_obj.toString():msg; 
		}
	}
	
%>

<p class="erro"><b><%=msg%></b></p>


<jsp:include page="./master/footer.jsp"></jsp:include>