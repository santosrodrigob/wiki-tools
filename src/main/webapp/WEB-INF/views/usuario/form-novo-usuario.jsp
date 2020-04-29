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
        <title>Cadastro - Usuário</title>

	    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css.map"/>
	    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <link href="${assets }/css/principal.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <header>
			<c:import url="../menu/menu.jsp"></c:import>
		</header>

		<div class="title">
			<h3>Cadastro Usuário</h3>
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
		        			<form:input path="senha" class="form-control"/>
		        			<form:errors path="senha" class="errors"/>
		        		</div>
		        		<div class="col-md-5">
		        			<label for="codigoAcessoWiki">Acesso</label>
		        			<form:select path="codigoAcessoWiki" class="form-control" >
		        				<option value="0">Selecione...</option>
		        				<form:option value="1" selected="selected" >ADMIN(1)</form:option>
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

        <footer>
            <p class="copyright"><!--&copy; Copyright Wiki Tools - 2020  --></p>
        </footer>
    </body>
    <script src="${assets }/js/jquery-3.5.0.min.js" type="text/javascript" ></script>
    <script src="${assets }/plugins/bootstrap-4.4.1-dist/js/bootstrap.min.js" type="text/javascript" ></script>
    <script src="${assets }/js/main.js" type="text/javascript" ></script>
</html>