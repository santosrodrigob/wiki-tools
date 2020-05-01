<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<c:url value="/login" var="login"></c:url>

<tags:pageTemplate titulo="Login">

<jsp:attribute name="extraScripts">
	<script>
	    $("input[type=text]").first().focus();
	</script>
</jsp:attribute>
	
	<jsp:body>
        <main>
        	<div class="div-login">
	        	<form:form servletRelativeAction="${login }" method="POST" acceptCharset="utf-8">
	        		<div class="form-group" id="login-input">
		        		<div class="row">
			        		<div class="col-md-6">
			        			<label class="text-center">Login</label>
			        			<input type="text" name="username" id="username" class="form-control"/>
			        		</div>
		        		</div>
		        		<div class="row">
			        		<div class="col-md-6">
			        			<label for="password" class="text-center" class="login-label">Senha</label>
			        			<input type="password" name="password" class="form-control"/>
			        		</div>
		        		</div>
	        		</div>
	        		<div class="form-group" id="login-button">
		        		<div class="row">
			        		<div class="col-md-6">
				        		<button class="btn btn-primary btn-block" type="submit">Entrar</button>
			        		</div>
		        		</div>
	        		</div>
	        		
	        	</form:form>
	        </div>
        </main>
	</jsp:body>
</tags:pageTemplate>