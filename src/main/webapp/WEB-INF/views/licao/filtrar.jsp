<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:url value="/assets" var="assets"></spring:url>
<c:url value="/licao/lista" var="filtrar"></c:url>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Filtrar</title>

	    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css.map"/>
	    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <link href="${assets }/css/principal.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <header>
			<c:import url="../menu/menu.jsp"></c:import>
		</header>

		<div class="title">
			<h3>Filtro - Lições Aprendidas</h3>
		</div>
  			
		<div class="dropdown-divider"></div>
		<main>
        	<form:form servletRelativeAction="${filtrar }" method="GET">
        		<div class="form-group">
        			<div class="row">
		        		<div class="col-md-6">
		        			<label for="codigoReferencia">Referência</label>
		        			<select name="codigoReferencia" id="codigoReferencia" class="form-control" >
		        				<option value="0">Selecione...</option>
		        				<c:forEach items="${referencias }" var="item">
			        				<option value="${item.codigoReferencia}" >${item.descricaoReferencia}</option>
		        				</c:forEach>
		        			</select>
		        		</div>
		        		<div class="col-md-6">
		        			<label for="codigoSubReferencia">Sub-Referência</label>
		        			<select name="codigoSubReferencia" id="codigoSubReferencia" class="form-control" >
		        				<option value="0">Selecione...</option>
		        				<c:forEach items="${subReferencias }" var="item">
			        				<option value="${item.codigoSubReferencia}" >${item.descricaoSubReferencia}</option>
		        				</c:forEach>
		        			</select>
		        		</div>
	        		</div>
        		</div>
        		<div class="form-group">
        			<div class="row">
		        		<div class="col-md-6">
		        			<label>Técnico</label>
		        			<select name="codigoUsuario" id="codigoUsuario" class="form-control">
		        				<option value="0">Selecione...</option>
		        				<c:forEach items="${usuarios }" var="item">
			        				<option value="${item.codigoUsuario}" >${item.usuario}</option>
		        				</c:forEach>
		        			</select>
		        		</div>
		        		<div class="col-md-5">
		        			<label>Situação</label>
		        			<select name="inativo" id="inativo" class="form-control">
		        				<option value="0">ATIVO</option>
		        				<option value="1">INATIVO</option>
		        				<option value="2">TODOS</option>
		        			</select>
		        		</div>
		        		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
		        		<div class="col-md-1">
							<button class="btn btn-primary btn-buscar" type="submit">Buscar</button>
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