<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<c:url value="/sub-referencia/lista-sub" var="gravar"></c:url>

<tags:pageTemplate titulo="Cadastrar - Sub-Referencias">

<jsp:attribute name="extraScripts">
	<script>
	    $("input[type=text]").first().focus();
	</script>
</jsp:attribute>
	
	<jsp:body>
		<div class="title">
			<h3>Cadastro - Sub-Referência</h3>
		</div>
  			
		<div class="dropdown-divider"></div>
		<main>
        	<form:form action="${gravar }" method="POST" commandName="subReferenciaDTO">
        		<div class="form-group">
        			<div class="row">
		        		<div class="col-md-2">
		        			<label for="codigo">Código Sub-Referência</label>
		        			<input name="codigo" class="form-control" disabled="disabled"/>
		        		</div>
		        		<div class="col-md-3">
		        			<label for="codigoReferencia">Referência</label>
		        			<select name="codigoReferencia" class="form-control" >
		        				<option value="0">Selecione...</option>
		        				<c:forEach items="${referencias }" var="item">
			        				<option value="${item.codigoReferencia}" >${item.descricaoReferencia}</option>
		        				</c:forEach>
		        			</select>
		        			<form:errors path="codigoReferencia" class="errors"/>
		        		</div>
		        		<div class="col-md-6">
		        			<label for="descricaoSubReferencia">Descrição</label>
		        			<form:input path="descricaoSubReferencia" class="form-control"/>
		        			<form:errors path="descricaoSubReferencia" class="errors"/>
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