<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<c:url value="/sub-referencia/lista-sub" var="filtrar"></c:url>

<tags:pageTemplate titulo="Filtrar - Sub-Referencias">
		<div class="title">
			<h3>Filtro - Sub-Referências</h3>
		</div>
  			
		<div class="dropdown-divider"></div>
		<main>
        	<form action="${filtrar }" method="GET">
        		<div class="form-group">
        			<div class="row">
		        		<div class="col-md-5">
		        			<label>Descrição</label>
		        			<input type="text" name="descricaoSubReferencia" id="descricaoSubReferencia" class="form-control" />
		        		</div>
		        		<div class="col-md-4">
		        			<label for="codigoReferencia">Referência</label>
		        			<select name="codigoReferencia" class="form-control" >
		        				<option value="0">Selecione...</option>
		        				<c:forEach items="${referencias }" var="item">
			        				<option value="${item.codigoReferencia}" >${item.descricaoReferencia}</option>
		        				</c:forEach>
		        			</select>
		        		</div>
		        		<div class="col-md-2">
		        			<label>Situação</label>
		        			<select name="inativo" id="inativo" class="form-control">
		        				<option value="0" selected="selected">ATIVO</option>
		        				<option value="1">INATIVO</option>
		        				<option value="2">TODOS</option>
		        			</select>
		        		</div>
		        		<div class="col-md-1">
							<button class="btn btn-primary btn-buscar" type="submit">Buscar</button>
		        		</div>
	        		</div>
        		</div>
        	</form>
		</main>
</tags:pageTemplate>