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
        <title>Alteração - Sub-Referências</title>

	    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css.map"/>
	    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <link href="${assets }/css/principal.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <header>
			<c:import url="../menu/menu.jsp"></c:import>
        </header>

		<div id="div-alert" role="alert" >
			<span class="message">${message}</span>
		</div>
		<div class="title">
			<h3>Alteração - Referência</h3>
		</div>

		<div class="dropdown-divider"></div>
		<main>
        	<form:form action="${gravar }" method="POST">
        		<div class="form-group">
        			<div class="row">
		        		<div class="col-md-4">
		        			<label>Codigo Referência</label>
		        			<input name="codigoSubReferencia" id="codigoSubReferencia" type="number" class="form-control" value="${subReferencia.codigoSubReferencia}" readonly="readonly"/>
		        		</div>
						<div class="col-md-8">
		        			<label>Descrição Referência</label>
		        			<input name="descricaoSubReferencia" id="descricaoSubReferencia" class="form-control" value="${subReferencia.descricaoSubReferencia}" />
		        		</div>
	        		</div>
        			<div class="row">
		        		<div class="col-md-8">
		        			<label for="codigoReferencia">Referência</label>
		        			<select name="codigoReferencia" class="form-control" >
		        				<option value="0">Selecione...</option>
		        				<c:forEach items="${referencias }" var="item">
			        				<option value="${item.codigoReferencia}" ${subReferencia.codigoReferencia==item.codigoReferencia ? 'selected' : '' }>${item.descricaoReferencia}</option>
		        				</c:forEach>
		        			</select>
		        		</div>
						<div class="col-md-3">
		        			<label>Situação</label>
		        			<select name="inativo" id="inativo" class="form-control" >
		        				<option value="0" ${0 == subReferencia.inativo ? 'selected' : '' }>ATIVO</option>
		        				<option value="1" ${1 == subReferencia.inativo ? 'selected' : '' }>INATIVO</option>
		        			</select>
		        		</div>
		        		<div class="col-md-1">
							<button class="btn btn-primary btn-buscar" type="submit">Enviar</button>
		        		</div>
	        		</div>	        		
        		</div>
        		<div class="form-group">
        			<div class="row">
						<div class="col-md-3">
		        			<label>Usuário de Criação</label>
		        			<input type="text" class="form-control" value="${subReferencia.usuarioCriacao}" readonly="readonly"/>
		        		</div>
						<div class="col-md-3">
		        			<label>Usuário de Alteração</label>
		        			<input type="text" class="form-control" value="${subReferencia.usuarioAlteracao}" readonly="readonly"/>
		        		</div>
						<div class="col-md-3">
		        			<label>Data Criação</label>
		        			<input type="text" class="form-control" value="${subReferencia.dataCriacaoFormatada}" readonly="readonly"/>
		        		</div>
						<div class="col-md-3">
		        			<label>Data Alteração</label>
		        			<input type="text" class="form-control" value="${subReferencia.dataAlteracaoFormatada}" readonly="readonly"/>
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