<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="titulo" required="true" %>
<%@ attribute name="extraScripts" fragment="true" %>
<spring:url value="/assets" var="assets"></spring:url>

<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>${titulo }</title>

    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css.map"/>
    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${assets }/css/principal.css" rel="stylesheet" type="text/css" />	    

</head>
	<body>
		<%@ include file="/WEB-INF/views/menu/cabecalho.jsp" %>

		<jsp:doBody />

		<%@ include file="/WEB-INF/views/menu/rodape.jsp" %>	
        
	    <script src="${assets }/js/jquery-3.5.0.min.js" type="text/javascript" ></script>
	    <script src="${assets }/plugins/bootstrap-4.4.1-dist/js/bootstrap.min.js" type="text/javascript" ></script>
	    <script src="${assets }/js/main.js" type="text/javascript" ></script>
        <jsp:invoke fragment="extraScripts" />
    </body>
</html>
