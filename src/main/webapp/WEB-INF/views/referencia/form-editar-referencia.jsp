<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<c:url value="/referencia/lista-referencia" var="gravar"></c:url>

<tags:pageTemplate titulo="Alterar - Referências">
		<div id="div-alert" role="alert" >
			<span class="message">${message}</span>
		</div>
		<div class="title">
			<h3>Alteração - Referência</h3>
		</div>

		<div class="dropdown-divider"></div>
		<main>
        	<form:form servletRelativeAction="${gravar }" method="POST" acceptCharset="utf-8">
        		<div class="form-group">
        			<div class="row">
		        		<div class="col-md-2">
		        			<label for="codigoReferencia">Codigo Referência</label>
		        			<input name="codigoReferencia" id="codigoReferencia" type="number" class="form-control" value="${referencia.codigoReferencia}" readonly="readonly"/>
		        		</div>
						<div class="col-md-6">
		        			<label for="descricaoReferencia">Descrição Referência</label>
		        			<input name="descricaoReferencia" id="descricaoReferencia" class="form-control" value="${referencia.descricaoReferencia}" />
		        		</div>
						<div class="col-md-3">
		        			<label>Situação</label>
		        			<select name="inativo" id="inativo" class="form-control" >
		        				<option value="0" ${0 == referencia.inativo ? 'selected' : '' }>ATIVO</option>
		        				<option value="1" ${1 == referencia.inativo ? 'selected' : '' }>INATIVO</option>
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
		        			<input type="text" class="form-control" value="${referencia.usuarioCriacao}" readonly="readonly"/>
		        		</div>
						<div class="col-md-3">
		        			<label>Usuário de Alteração</label>
		        			<input type="text" class="form-control" value="${referencia.usuarioAlteracao}" readonly="readonly"/>
		        		</div>
						<div class="col-md-3">
		        			<label>Data Criação</label>
		        			<input type="text" class="form-control" value="${referencia.dataCriacaoFormatada}" readonly="readonly"/>
		        		</div>
						<div class="col-md-3">
		        			<label>Data Alteração</label>
		        			<input type="text" class="form-control" value="${referencia.dataAlteracaoFormatada}" readonly="readonly"/>
		        		</div>
	        		</div>
        		</div>
        	</form:form>
        </main>
</tags:pageTemplate>