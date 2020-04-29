<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:url value="/assets" var="assets"></spring:url>
<c:url value="/licao/lista" var="gravar"></c:url>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Alteração - Lições Aprendidas</title>

	    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css.map"/>
	    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <link href="${assets }/css/principal.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <header>
			<c:import url="../menu/menu.jsp"></c:import>
        </header>

		<div class="title">
			<h3>Alteração - Lições Aprendidas</h3>
		</div>

		<div class="dropdown-divider"></div>
		<main>
        	<form:form action="${gravar }" method="POST">
        		<div class="form-group">
        			<div class="row">
		        		<div class="col-md-3">
		        			<label>Id</label>
		        			<input name="id" id="id" type="number" class="form-control" value="${licao.id}" readonly="readonly"/>
		        		</div>
		        		<div class="col-md-9">
							<div id="div-alert" role="alert" >
								<span class="message">${message}</span>
							</div>
		        		</div>
	        		</div>
        		</div>
        		<div class="form-group">
        			<div class="row">
						<div class="col-md-12">
		        			<label>Erro / Problema</label>
		        			<textarea name="erro" id="erro" class="form-control" >${licao.erro}</textarea>
		        		</div>
	        		</div>
        			<div class="row">
						<div class="col-md-12">
		        			<label>Ajuste/Solução</label>
		        			<textarea name="solucao" id="solucao" class="form-control" >${licao.solucao}</textarea>
		        		</div>
	        		</div>
        		</div>
				<div class="form-group">
        			<div class="row">
						<div class="col-md-6">
		        			<label>Referência</label>
		        			<select name="codigoReferencia" id="codigoReferencia" class="form-control" >
		        				<option value="0">Selecione...</option>
		        				<c:forEach items="${referencias}" var="item">
			        				<option value="${item.codigoReferencia}" ${item.codigoReferencia == licao.codigoReferencia ? 'selected' : '' } >${item.descricaoReferencia}</option>
		        				</c:forEach>
		        			</select>
		        		</div>
						<div class="col-md-6">
		        			<label>Sub-Referência</label>
		        			<select name="codigoSubReferencia" id="codigoSubReferencia" class="form-control" >
		        				<option value="0">Selecione...</option>
		        				<c:forEach items="${subReferencias}" var="item">
			        				<option value="${item.codigoSubReferencia}" ${item.codigoSubReferencia == licao.codigoSubReferencia ? 'selected' : '' } >${item.descricaoSubReferencia}</option>
		        				</c:forEach>
		        			</select>
		        		</div>
	        		</div>
        			<div class="row">
						<div class="col-md-4">
		        			<label>Cliente</label>
		        			<input type="text" min="15" max="15" name="cliente" id="cliente" class="form-control" value="${licao.cliente}" />
		        		</div>
						<div class="col-md-4">
		        			<label>Técnico</label>
		        			<select name="codigoUsuario" id="codigoUsuario" class="form-control" >
		        				<option value="0">Selecione...</option>
		        				<c:forEach items="${usuarios}" var="item">
			        				<option value="${item.codigoUsuario}" ${item.codigoUsuario == licao.codigoUsuario ? 'selected' : '' } >${item.usuario}</option>
		        				</c:forEach>
		        			</select>
		        		</div>
						<div class="col-md-3">
		        			<label>Situação</label>
		        			<select name="inativo" id="inativo" class="form-control" >
		        				<option value="0" ${0 == licao.inativo ? 'selected' : '' }>ATIVO</option>
		        				<option value="1" ${1 == licao.inativo ? 'selected' : '' }>INATIVO</option>
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
		        			<input type="text" class="form-control" value="${licao.usuarioCriacao}" readonly="readonly"/>
		        		</div>
						<div class="col-md-3">
		        			<label>Usuário de Alteração</label>
		        			<input type="text" class="form-control" value="${licao.usuarioAlteracao}" readonly="readonly"/>
		        		</div>
						<div class="col-md-3">
		        			<label>Data Criação</label>
		        			<input type="text" class="form-control" value="${licao.dataCriacaoFormatada}" readonly="readonly"/>
		        		</div>
						<div class="col-md-3">
		        			<label>Data Alteração</label>
		        			<input type="text" class="form-control" value="${licao.dataAlteracaoFormatada}" readonly="readonly"/>
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