<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<c:url value="/licao/lista" var="gravar"></c:url>

<tags:pageTemplate titulo="Cadastrar - Lições">

<jsp:attribute name="extraScripts">
	<script>
	    $("input[type=text]").first().focus();
	</script>
</jsp:attribute>
	
	<jsp:body>
		<div class="title">
			<h3>Cadastro - Lição Aprendida</h3>
		</div>
  			
		<div class="dropdown-divider"></div>
		<main>
        	<form:form action="${gravar }" method="POST" commandName="licaoAprendidaDTO">
        		<div class="form-group">
        			<div class="row">
		        		<div class="col-md-3">
		        			<label id="cliente">Nome do Cliente</label>
		        			<form:input path="cliente" class="form-control"/>
		        		</div>
		        		<div class="col-md-3">
		        			<label>Técnico</label>
		        			<select name="codigoUsuario" id="codigoUsuario" class="form-control">
		        				<option value="0">Selecione...</option>
		        				<c:forEach items="${usuarios }" var="item">
			        				<option value="${item.codigoUsuario}" ${item.codigoUsuario == usuario.codigoUsuario ? 'selected' : '' } >${item.usuario}</option>
		        				</c:forEach>
		        			</select>
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
	</jsp:body>
</tags:pageTemplate>