<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<spring:url value="/assets" var="assets"></spring:url>
<c:url value="/licao/lista" var="gravar"></c:url>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Lista - Wiki</title>
        
	    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css.map"/>
	    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <link href="${assets }/css/principal.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <header>
			<c:import url="../menu/menu.jsp"></c:import>
        </header>

		<div role="alert" id="div-info">
			<span class="message">${message}</span>
		</div>
		<div class="title">
			<h3>Lista - Lições Aprendidas</h3>
		</div>

        <main>
        	<div class="table-responsive">
				<div class="div-table-pesquisar col-md-12">
					<label for="pesquisa"></label>
					<input id="pesquisa" name="pesquisa" placeholder="Pesquisar" class="form-control right"/>
				</div>
	        	<table class="table table-striped">
	        		<thead class="thead-dark text-center">
		        		<tr>
		        			<th scope="col">Refs</th>
		        			<th scope="col">Erro</th>
		        			<th scope="col">Cliente/Técnico</th>
		        			<th scope="col">Solução</th>
		        			<th scope="col">Data Alteração</th>
		        			<security:authorize access="isAuthenticated()">
		        			<th scope="col" colspan="2">Ações</th>
		        			</security:authorize>
		        		</tr>
	        		</thead>
	        		<tbody>
	        			<c:forEach items="${licoes}" var="licao">
	        			<tr class="trs">
		        			<td class="subReferencia">${licao.descricaoSubReferencia}</td>
		        			<td class="erro">${licao.erro}</td>
		        			<td class="usuario">${licao.cliente}  ${licao.usuario }</td>
		        			<td class="solucao">${licao.solucao}</td>
		        			<td class="dataAlteracao">${licao.dataAlteracaoFormatada}</td>
		        			<security:authorize access="isAuthenticated()">
		        			<td>
			        			<a href="/wiki-tools/licao/form-editar/${licao.id}">
									<img src="/wiki-tools/assets/plugins/bootstrap-4.4.1-dist/icons/pencil.svg" class="pencil" alt="" width="21" height="21" title="Bootstrap">
			        			</a>
		        			</td>
		        			<td>
		        				<c:if test="${licao.inativo!=0 }">
			        				<a href="/wiki-tools/licao/lista/${licao.id}/${licao.inativo}">
										<img src="/wiki-tools/assets/plugins/bootstrap-4.4.1-dist/icons/check.svg" alt="" width="25" height="25" title="Bootstrap">
			        				</a>
		        				</c:if>
		        				<c:if test="${licao.inativo==0 }">
			        				<a href="/wiki-tools/licao/lista/${licao.id}/${licao.inativo}">
										<img src="/wiki-tools/assets/plugins/bootstrap-4.4.1-dist/icons/x.svg" class="text-success" alt="" width="25" height="25" title="Bootstrap">
			        				</a>
		        				</c:if>
		        			</td>
		        			</security:authorize>
		        		</tr>
	        			</c:forEach>
	        		</tbody>
	        	</table>
        	</div>
        </main>
        <footer>
            <p class="copyright"><!--&copy; Copyright Wiki Tools - 2020  --></p>
        </footer>
    </body>
    <script src="${assets }/js/jquery-3.5.0.min.js" type="text/javascript" ></script>
    <script src="${assets }/plugins/bootstrap-4.4.1-dist/js/bootstrap.min.js" type="text/javascript" ></script>
    <script src="${assets }/js/main.js" type="text/javascript" ></script>
</html>