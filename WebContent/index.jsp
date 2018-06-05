<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<jsp:include page="./master/header-login.jsp">
	<jsp:param value="Login" name="titulo"/>
</jsp:include>
    	
<h2>eBank</h2>

<h1>O seu banco na internet</h1>

<p></p>
   
   <form action="./login" method="POST">
    <fieldset style="padding:1em">
    	<input type="number" name="ag" placeholder="Agência" required autofocus> <br/><br/>
    	<input type="number" name="cc" placeholder="Conta Corrente" required autofocus> <br/><br/>
        <input type="password" name="pwd" placeholder="Senha" required> <br/><br/>
        <input type="submit" value="Entrar"> <br/>
    </fieldset>
</form>

<p><a href="novo.jsp">Registre-se</a></p>

<% 
  	String msg = "";
  	
  	Object idUs = request.getSession().getAttribute("idUs");
	Object login_error = request.getAttribute("le");
  	Object data_error = request.getAttribute("di");
  	Object insert_error = request.getAttribute("ei");
  	Object insert_success = request.getAttribute("si");
  	Object msg_error = request.getAttribute("msg");
  	Object msg_success = request.getAttribute("msg");
  	
  	if(idUs != null){
  		request.getRequestDispatcher("./home.jsp").forward(request, response);	
  	}
  	
  	if(login_error != null){
  		
  		if(Boolean.parseBoolean(login_error.toString())){
   		msg = (msg_error != null)?msg_error.toString():"";
   	}
  		
  	}
  	
  	if(data_error != null){
  		
  		if(Boolean.parseBoolean(data_error.toString())){
  			msg = "Informe os dados corretamente";
  		}
  		
  	}
  	
  	if(insert_error != null){
  		
  		if(Boolean.parseBoolean(insert_error.toString())){
  			msg = (msg_error != null)?msg_error.toString():"";
  		}
  		
  	}
  	
  	if(insert_success != null){
  		
  		if(Boolean.parseBoolean(insert_success.toString())){
  			msg = (msg_success != null)?msg_success.toString():"";
  		}
  		
  	}
  	
%>		
	
<p class="erro"><b><%=msg%></b></p>

<jsp:include page="./master/footer.jsp"></jsp:include>