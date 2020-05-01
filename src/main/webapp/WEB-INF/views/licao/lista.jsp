<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<c:url value="/licao/lista" var="gravar"></c:url>

<tags:pageTemplate titulo="Listar - Lições">
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
</tags:pageTemplate>