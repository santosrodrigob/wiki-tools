<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<c:url value="/usuario/lista-usuario" var="gravar"></c:url>

<tags:pageTemplate titulo="Filtrar - Usuários">
		<div class="title">
			<h3>Cadastro - Usuário</h3>
		</div>
  			
		<div class="dropdown-divider"></div>
		<main>
        	<form:form action="${gravar }" method="POST" commandName="usuarioDTO">
        		<div class="form-group">
        			<div class="row">
		        		<div class="col-md-3">
		        			<label for="usuario">Código Usuario</label>
		        			<form:input path="codigoUsuario" class="form-control"/>
		        			<form:errors path="codigoUsuario" class="errors"/>
		        		</div>
		        		<div class="col-md-1 div-btn-proximo">
							<button class="btn btn-outline-secondary btn-sm btn-proximo" type="button" onclick="getProximo();">Próximo</button>
		        		</div>
		        		<div class="col-md-8">
		        			<label for="usuario">Nome Usuário</label>
		        			<form:input path="usuario" class="form-control"/>
		        			<form:errors path="usuario" class="errors"/>
		        		</div>
	        		</div>
        			<div class="row">
		        		<div class="col-md-6">
		        			<label for="senha">Senha</label>
		        			<form:password path="senha" class="form-control"/>
		        			<form:errors path="senha" class="errors"/>
		        		</div>
		        		<div class="col-md-5">
		        			<label for="codigoAcessoWiki">Acesso</label>
		        			<form:select path="codigoAcessoWiki" class="form-control" >
		        				<option value="0">Selecione...</option>
		        				<form:option value="1" selected="selected" >ADMIN(1)</form:option>
		        				<form:option value="2" >USER(2)</form:option>
		        			</form:select>
		        			<form:errors path="codigoAcessoWiki" class="errors"/>
		        		</div>
		        		<div class="col-md-1">
							<button class="btn btn-primary btn-buscar" type="submit">Enviar</button>
		        		</div>
	        		</div>
        		</div>
        	</form:form>
		</main>
</tags:pageTemplate>