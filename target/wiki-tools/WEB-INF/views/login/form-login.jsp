<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<spring:url value="/assets" var="assets"></spring:url>
<c:url value="/login" var="login"></c:url>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Login</title>

	    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css.map"/>
	    <link href="${assets }/plugins/bootstrap-4.4.1-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <link href="${assets }/css/principal.css" rel="stylesheet" type="text/css" />	    

    </head>
    <body>
        <header>
			<c:import url="../menu/menu.jsp"></c:import>
		</header>

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
    <script src="${assets }/js/jquery-3.5.0.min.js" type="text/javascript" ></script>
    <script src="${assets }/plugins/bootstrap-4.4.1-dist/js/bootstrap.min.js" type="text/javascript" ></script>
    <script src="${assets }/js/main.js" type="text/javascript" ></script>
    
    <script type="text/javascript">
        $(document).ready(function () {
            $("input[type=text]").first().focus();
        });
	</script>
    </body>
</html>