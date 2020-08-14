<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<c:url value="/gravar" var="gravar"></c:url>

<tags:pageTemplate titulo="Cadastrar - Clientes">

<jsp:attribute name="extraScripts">
	<script>
	    $("input[type=text]").first().focus();
	</script>
	<%@ include file="/WEB-INF/views/cliente-estrutura/scripts-cliente-estrutura.jsp" %>
</jsp:attribute>
	
	<jsp:body>
		<div class="title">
			<h3>Cadastro - Cliente-Estrutura</h3>
		</div>
  			
		<div class="dropdown-divider"></div>
		<main>
        	<form id="formClienteNovo" action="${gravar }" method="POST">
       			<div class="row">
	        		<div class="col-md-2">
		        		<div class="form-group">
		        			<label for="codigo">Id cliente</label>
		        			<input name="codigo" class="form-control" disabled="disabled"/>
		        		</div>
	        		</div>
	        		<div class="col-md-4">
		        		<div class="form-group">
		        			<label for="cnpj">CNPJ</label>
		        			<input name="cnpj" id="cnpj" class="form-control"/>
		        		</div>
	        		</div>
	        		<div class="col-md-6">
		        		<div class="form-group">
		        			<label for="nome">Nome Cliente</label>
		        			<input name="nomeCliente" id="nomeCliente" class="form-control"/>
		        		</div>
	        		</div>
       			</div>
       			<div class="row">
	        		<div class="col-md-4">
		        		<div class="form-group">
		        			<label for="equipamento">Equipamento</label>
		        			<select name="codigoEquipamento" id="codigoEquipamento" class="form-control" >
		        				<option value="0">Selecione...</option>
		        				<c:forEach items="${equipamentos }" var="item">
			        				<option value="${item.codigoEquipamento}" >${item.descricaoEquipamento}</option>
		        				</c:forEach>
		        			</select>
		        		</div>
	        		</div>
	        		<div class="col-md-2">
		        		<div class="form-group">
		        			<label for="ip">Ip - Endereço Ip</label>
		        			<input name="ip" id="ip" class="form-control"/>
		        		</div>
	        		</div>
	        		<div class="col-md-2">
		        		<div class="form-group">
		        			<label for="dnsPrimario">DNS Primário</label>
		        			<input name="dnsPrimario" id="dnsPrimario" class="form-control"/>
		        		</div>
	        		</div>
	        		<div class="col-md-2">
		        		<div class="form-group">
		        			<label for="dnsSecundario">DNS Secundário</label>
		        			<input name="dnsSecundario" id="dnsSecundario" class="form-control"/>
		        		</div>
	        		</div>
	        		<div class="col-md-2">
		        		<div class="form-group">
		        			<label for="mac">MAC</label>
		        			<input name="mac" id="mac" class="form-control"/>
		        		</div>
	        		</div>
       			</div>
       			<div class="row">
	        		<div class="col-md-5">
		        		<div class="form-group">
		        			<label for="local">local</label>
		        			<input name="local" id="local" class="form-control"/>
		        		</div>
	        		</div>
	        		<div class="col-md-3">
		        		<div class="form-group">
		        			<label for="usuario">Usuário</label>
		        			<input name="usuario" id="usuario" class="form-control"/>
		        		</div>
	        		</div>
	        		<div class="col-md-3">
		        		<div class="form-group">
		        			<label for="senha">Senha</label>
		        			<input name="senha" id="senha" class="form-control"/>
		        		</div>
	        		</div>
	        		<div class="col-md-1">
						<button class="btn btn-primary btn-buscar" type="button" onclick="gravar();" >Enviar</button>
	        		</div>
       			</div>
        	</form>
		</main>
	</jsp:body>
</tags:pageTemplate>