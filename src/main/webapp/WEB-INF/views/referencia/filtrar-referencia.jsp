<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<c:url value="/referencia/lista-referencia" var="filtrar"></c:url>

<tags:pageTemplate titulo="Filtrar - Referências">
		<div class="title">
			<h3>Filtro - Referências</h3>
		</div>
  			
		<div class="dropdown-divider"></div>
		<main>
        	<form action="${filtrar }" method="GET">
        		<div class="form-group">
        			<div class="row">
		        		<div class="col-md-6">
		        			<label>Descrição</label>
		        			<input type="text" name="descricaoReferencia" id="descricaoReferencia" class="form-control" />
		        		</div>
		        		<div class="col-md-5">
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
