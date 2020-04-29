<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:url value="/assets" var="assets"></spring:url>
<c:url value="/sub-referencia/lista-sub" var="gravar"></c:url>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Cadastro - Referencia</title>

	    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css.map"/>
	    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <link href="${assets }/css/principal.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <header>
			<c:import url="../menu/menu.jsp"></c:import>
		</header>

		<div class="title">
			<h3>Cadastro - Sub-Referência</h3>
		</div>
  			
		<div class="dropdown-divider"></div>
		<main>
        	<form:form action="${gravar }" method="POST" commandName="subReferenciaDTO">
        		<div class="form-group">
        			<div class="row">
		        		<div class="col-md-2">
		        			<label for="codigo">Código Sub-Referência</label>
		        			<input name="codigo" class="form-control" disabled="disabled"/>
		        		</div>
		        		<div class="col-md-3">
		        			<label for="codigoReferencia">Referência</label>
		        			<select name="codigoReferencia" class="form-control" >
		        				<option value="0">Selecione...</option>
		        				<c:forEach items="${referencias }" var="item">
			        				<option value="${item.codigoReferencia}" >${item.descricaoReferencia}</option>
		        				</c:forEach>
		        			</select>
		        			<form:errors path="codigoReferencia" class="errors"/>
		        		</div>
		        		<div class="col-md-6">
		        			<label for="descricaoSubReferencia">Descrição</label>
		        			<form:input path="descricaoSubReferencia" class="form-control"/>
		        			<form:errors path="descricaoSubReferencia" class="errors"/>
		        		</div>
		        		<div class="col-md-1">
							<button class="btn btn-primary btn-buscar" type="submit">Enviar</button>
		        		</div>
	        		</div>
        		</div>
        	</form:form>
		</main>

        <footer>
            <p class="copyright"><!--&copy; Copyright Wiki Tools - 2020  --></p>
        </footer>
    </body>
    <script src="${assets }/js/jquery-3.5.0.min.js" type="text/javascript" ></script>
    <script src="${assets }/plugins/bootstrap-4.4.1-dist/js/bootstrap.min.js" type="text/javascript" ></script>
    <script src="${assets }/js/main.js" type="text/javascript" ></script>
</html>