<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<jsp:include page="./header.jsp">
	<jsp:param value="${param.titulo}" name="titulo"/>
</jsp:include>
<jsp:include page="./exibicao.jsp"></jsp:include>