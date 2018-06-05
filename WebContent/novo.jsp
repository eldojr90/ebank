<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<jsp:include page="./master/header.jsp">
	<jsp:param value="Nova Conta" name="titulo"/>
</jsp:include>
<h2>Cadastro</h2>
   <form action="./novo" method="POST">
    <fieldset  style="padding:0.5em">
    	<legend>Dados Pessoais</legend>
    	<input type="text" name="nm" placeholder="Nome" autofocus> <br/><br/>
        <input type="text" name="cpf" placeholder="CPF"> <br/><br/>
        <input type="password" name="senha" placeholder="Senha"> <br/><br/>
       </fieldset>
       <fieldset  style="padding:0.5em">
    	<legend>Dados Bancários</legend>
    	<input type="number" name="ag" placeholder="Agência"><br/><br/>
    	<input type="number" name="cc" placeholder="Conta"><br/><br/>
    	<input type="number" name="saldo" placeholder="Saldo Inicial"><br/><br/>
       </fieldset>
       <input style="margin-top:1em;" type="submit" value="Salvar">
</form>
<jsp:include page="./master/footer.jsp"></jsp:include>