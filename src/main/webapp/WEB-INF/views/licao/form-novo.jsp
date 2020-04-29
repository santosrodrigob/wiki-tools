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
        <title>Cadastro - Wiki</title>

	    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css.map"/>
	    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <link href="${assets }/css/principal.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <header>
			<c:import url="../menu/menu.jsp"></c:import>
		</header>

		<div class="title">
			<h3>Cadastro - Lição Aprendida</h3>
		</div>
  			
		<div class="dropdown-divider"></div>
		<main>
        	<form:form action="${gravar }" method="POST" commandName="licaoAprendidaDTO">
        		<div class="form-group">
        			<div class="row">
		        		<div class="col-md-3">
		        			<label id="cliente">Cliente</label>
		        			<form:input path="cliente" class="form-control"/>
		        		</div>
		        		<div class="col-md-3">
		        			<label>Técnico</label>
		        			<form:select path="codigoUsuario" id="codigoUsuario" class="form-control">
		        				<option value="0">Selecione...</option>
		        				<c:forEach items="${usuarios }" var="item">
			        				<form:option value="${item.codigoUsuario}" >${item.usuario}</form:option>
		        				</c:forEach>
		        			</form:select>
		        			<form:errors path="codigoUsuario" class="errors"/>
		        		</div>
		        		<div class="col-md-3">
		        			<label for="codigoReferencia">Referência</label>
		        			<form:select path="codigoReferencia" id="codigoReferencia" class="form-control" >
		        				<option value="0">Selecione...</option>
		        				<c:forEach items="${referencias }" var="item">
			        				<form:option value="${item.codigoReferencia}" >${item.descricaoReferencia}</form:option>
		        				</c:forEach>
		        			</form:select>
		        			<form:errors path="codigoReferencia" class="errors"/>
		        		</div>
		        		<div class="col-md-3">
		        			<label for="codigoSubReferencia">Sub-Referência</label>
		        			<form:select path="codigoSubReferencia" id="codigoSubReferencia" class="form-control" >
		        				<option value="0">Selecione...</option>
		        				<c:forEach items="${subReferencias }" var="item">
			        				<form:option value="${item.codigoSubReferencia}" >${item.descricaoSubReferencia}</form:option>
		        				</c:forEach>
		        			</form:select>
		        			<form:errors path="codigoSubReferencia" class="errors"/>
		        		</div>
	        		</div>
        		</div>
        		<div class="form-group">
        			<div class="row">
		        		<div class="col-md-12">
		        			<label>Erro / Problema</label>
		        			<form:textarea path="erro" class="form-control" />
		        			<form:errors path="erro" class="errors"/>
		        		</div>
	        		</div>
        			<div class="row">
		        		<div class="col-md-11">
		        			<label>Ajuste/Solução</label>
		        			<form:textarea path="solucao" id="solucao" class="form-control" />
		        			<form:errors path="solucao" class="errors"/>
		        		</div>
		        		<div class="col-md-1">
							<button class="btn btn-primary btn-enviar" type="submit">Enviar</button>
		        		</div>
	        		</div>
        		</div>
        	</form:form>
		</main>

        <footer class="caixa">
            <p class="copyright"><!--&copy; Copyright Wiki Tools - 2020  --></p>
        </footer>
    </body>
    <script src="${assets }/js/jquery-3.5.0.min.js" type="text/javascript" ></script>
    <script src="${assets }/plugins/bootstrap-4.4.1-dist/js/bootstrap.min.js" type="text/javascript" ></script>
    <script src="${assets }/js/main.js" type="text/javascript" ></script>
</html>