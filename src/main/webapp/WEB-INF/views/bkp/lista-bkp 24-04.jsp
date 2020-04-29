<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:url value="/resources" var="resources"></spring:url>
<c:url value="/licao/lista" var="gravar"></c:url>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Lição Aprendida</title>
        
	    <link href="${resources }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css.map"/>
	    <link href="${resources }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <link href="${resources }/css/principal.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <header>
            <div>
                <h1>Lista</h1>
            </div>
			<div class="alert alert-info" role="alert" >
				<span>${message}</span>
			</div>
        </header>
        <main>
        	<div class="table-responsive">
	        	<table class="table table-striped">
	        		<thead class="thead-light text-center">
		        		<tr>
		        			<th scope="col">#</th>
		        			<th scope="col">Cliente</th>
		        			<th scope="col">Erro</th>
		        			<th scope="col">Referência</th>
		        			<th scope="col">Sub-Referência</th>
		        			<th scope="col">Técnico</th>
		        			<th scope="col">Solução</th>
		        			<th scope="col">Data Alteração</th>
		        			<th scope="col" colspan="3">Ações</th>
		        		</tr>
	        		</thead>
	        		<tbody>
	        			<c:forEach items="${licoes}" var="licao">
	        			<tr>
		        			<th scope="row">${licao.id}</th>
		        			<td>${licao.cliente}</td>
		        			<td>${licao.erro}</td>
		        			<td>${licao.descricaoReferencia}</td>
		        			<td>${licao.descricaoSubReferencia}</td>
		        			<td>${licao.usuario}</td>
		        			<td>${licao.solucao}</td>
		        			<td>${licao.dataAlteracaoFormatada}</td>
		        			<td>${licao.inativo}</td>
		        			<td><a href="/wiki-tools/licao/form-editar/${licao.id}">Editar</a></td>
		        			<td><a href="/wiki-tools/licao/lista/${licao.id}/${licao.inativo}">Remover</a></td>
		        		</tr>
	        			</c:forEach>
	        		</tbody>
	        	</table>
        	</div>
        </main>
        <footer>
            <p class="copyright">&copy; Copyright Tools - 2020</p>
        </footer>
    </body>
    <script src="${resources }/js/jquery-3.5.0.min.js" type="text/javascript" ></script>
    <script src="${resources }/plugins/bootstrap-4.4.1-dist/js/bootstrap.min.js" type="text/javascript" ></script>
    <script src="${resources }/js/main.js" type="text/javascript" ></script>
</html>