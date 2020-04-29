<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:url value="/assets" var="assets"></spring:url>
<c:url value="/usuario/lista-usuario" var="gravar"></c:url>

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
		        		<div class="col-md-2">
		        			<label for="id">Id</label>
		        			<input name="id" id="id" type="number" class="form-control" readonly="readonly" value="${usuario.id}"/>
		        		</div>
		        		<div class="col-md-2">
		        			<label for="codigoUsuario">Codigo Usuário</label>
		        			<input name="codigoUsuario" id="codigoUsuario" type="number" class="form-control"  readonly="readonly" value="${usuario.codigoUsuario}"/>
		        		</div>
						<div class="col-md-6">
		        			<label for="usuario">Nome Usuário</label>
		        			<input name="usuario" id="usuario" class="form-control" value="${usuario.usuario}" />
		        		</div>
						<div class="col-md-2">
		        			<label for="senha">Senha</label>
		        			<input name="senha" id="senha" class="form-control" value="${usuario.senha}" />
		        		</div>
	        		</div>
        		</div>
        		<div class="form-group">
        			<div class="row">
						<div class="col-md-4">
		        			<label for="codigoAcessoWiki">Acesso</label>
		        			<select name="codigoAcessoWiki" id="codigoAcessoWiki" class="form-control" >
		        				<option value="0" ${0 == usuario.codigoAcesso ? 'selected' : '' }>Selecione...</option>
		        				<option value="1" ${1 == usuario.codigoAcesso ? 'selected' : '' }>ADMIN</option>
		        			</select>
		        		</div>
						<div class="col-md-3">
		        			<label for="ativo">Situação</label>
		        			<select name="ativo" id="ativo" class="form-control" >
		        				<option value="1" ${1 == usuario.ativo ? 'selected' : '' }>ATIVO</option>
		        				<option value="0" ${0 == usuario.ativo ? 'selected' : '' }>INATIVO</option>
		        			</select>
		        		</div>
						<div class="col-md-2">
		        			<label>Data Criação</label>
		        			<input type="text" class="form-control" value="${usuario.dataCriacaoFormatada}" readonly="readonly"/>
		        		</div>
						<div class="col-md-2">
		        			<label>Data Alteração</label>
		        			<input type="text" class="form-control" value="${usuario.dataAlteracaoFormatada}" readonly="readonly"/>
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