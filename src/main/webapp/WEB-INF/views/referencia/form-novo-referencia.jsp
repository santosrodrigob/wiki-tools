<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<c:url value="/referencia/lista-referencia" var="gravar"></c:url>

<tags:pageTemplate titulo="Cadastrar - Referências">

<jsp:attribute name="extraScripts">
	<script>
	    $("input[type=text]").first().focus();
	</script>
</jsp:attribute>
	
	<jsp:body>
		<div class="title">
			<h3>Cadastro - Referência</h3>
		</div>
  			
		<div class="dropdown-divider"></div>
		<main>
        	<form:form servletRelativeAction="${gravar }" method="POST" commandName="referenciaDTO">
        		<div class="form-group">
        			<div class="row">
		        		<div class="col-md-2">
		        			<label for="codigo">Código Referência</label>
		        			<input name="codigo" class="form-control" disabled="disabled"/>
		        		</div>
		        		<div class="col-md-9">
		        			<label for="descricaoReferencia">Descrição</label>
		        			<form:input path="descricaoReferencia" class="form-control"/>
		        			<form:errors path="descricaoReferencia" class="errors"/>
		        		</div>
		        		<div class="col-md-1">
							<button class="btn btn-primary btn-buscar" type="submit">Enviar</button>
		        		</div>
	        		</div>
        		</div>
        	</form:form>
		</main>
	</jsp:body>
</tags:pageTemplate>