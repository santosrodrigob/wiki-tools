<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<c:url value="/usuario/lista-usuario" var="gravar"></c:url>

<tags:pageTemplate titulo="Alterar - Usuários">
		<div id="div-alert" role="alert" >
			<span class="message">${message}</span>
		</div>
		<div class="title">
			<h3>Alteração - Usuário</h3>
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
		        			<input type="text" name="usuario" id="usuario" class="form-control" value="${usuario.usuario}" />
		        		</div>
						<div class="col-md-2">
		        			<label for="senha">Senha</label>
		        			<input type="password" name="senha" id="senha" class="form-control" value="${usuario.senha}" />
		        		</div>
	        		</div>
        		</div>
        		<div class="form-group">
        			<div class="row">
						<div class="col-md-4">
		        			<label for="codigoAcessoWiki">Acesso</label>
		        			<select name="codigoAcessoWiki" id="codigoAcessoWiki" class="form-control" >
		        				<option value="0" ${0 == usuario.codigoAcesso ? 'selected' : '' }>Selecione...</option>
		        				<option value="1" ${1 == usuario.codigoAcesso ? 'selected' : '' }>ADMIN(1)</option>
		        				<option value="2" ${2 == usuario.codigoAcesso ? 'selected' : '' }>USER(2)</option>
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
</tags:pageTemplate>