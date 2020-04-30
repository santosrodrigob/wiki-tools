<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<a class="navbar-brand" href="#">
					Wiki Tools
				</a>
				<!-- botão searchbutton class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button -->
	
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="nav nav-pills mr-auto">
						<!--li class="nav-item active">
							<a class="nav-link" href="#">Home
								<span class="sr-only">(current)</span>
							</a>
						</li -->
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
								<strong>Lições Apren.</strong>
							</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
		        			<security:authorize access="!isAuthenticated()">
								<a class="dropdown-item" href="/wiki-tools/licao/open/2">Filtrar</a>
								<div class="dropdown-divider"></div>
							</security:authorize>
		        			<security:authorize access="isAuthenticated()">
								<a class="dropdown-item" href="/wiki-tools/licao/open/1">Filtrar</a>
								<div class="dropdown-divider"></div>
							</security:authorize>
		        			<security:authorize access="isAuthenticated()">
								<a class="dropdown-item" href="/wiki-tools/licao/open/0">Novo</a>
							</security:authorize>
								<a class="dropdown-item" href="/wiki-tools/licao/lista">Listar</a>
							</div>
						</li>
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
								<strong>Referências</strong>
							</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="/wiki-tools/referencia/open/1">Filtrar</a>
								<div class="dropdown-divider"></div>
		        			<security:authorize access="isAuthenticated()">
								<a class="dropdown-item" href="/wiki-tools/referencia/open/0">Novo</a>
							</security:authorize>
								<a class="dropdown-item" href="/wiki-tools/referencia/lista-referencia">Listar</a>
							</div>
						</li>
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
								<strong>Sub-Referências</strong>
							</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="/wiki-tools/sub-referencia/open/1">Filtrar</a>
								<div class="dropdown-divider"></div>
		        			<security:authorize access="isAuthenticated()">
								<a class="dropdown-item" href="/wiki-tools/sub-referencia/open/0">Novo</a>
							</security:authorize>
								<a class="dropdown-item" href="/wiki-tools/sub-referencia/lista-sub">Listar</a>
							</div>
						</li>
						<!-- li class="nav-item">
							<a class="nav-link" href="#" tabindex="-1" aria-disabled="true">
								Disabled
							</a>
						</li-->
					</ul>
					<form class="form-inline my-2 my-lg-0">
						<c:if test="${null != usuario.usuario }">
							<p>Bem Vindo, <strong>${usuario.usuario }</strong></p>
						</c:if>
						<ul class="nav nav-pills mr-auto">
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
									role="button" data-toggle="dropdown" aria-disabled="true" aria-expanded="false"> 
									<strong>
										<img src="/wiki-tools/assets/plugins/bootstrap-4.4.1-dist/icons/gear.svg" alt="" width="21" height="21" title="Bootstrap">
									</strong>
								</a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
									<a class="dropdown-item" href="#">Dados</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="/wiki-tools/usuario/open">Usuário - Novo</a>
									<a class="dropdown-item" href="/wiki-tools/usuario/lista-usuario">Listar Usuários</a>
								</div>
							</li>
						</ul>
	        			<security:authorize access="!isAuthenticated()">
						<a class="btn btn-outline-secondary" href="/wiki-tools/login" tabindex="-1" aria-disabled="true">
							<strong>Entrar</strong>
						</a>
						</security:authorize>
	        			<security:authorize access="isAuthenticated()">
						<a class="btn btn-outline-secondary" href="/wiki-tools/logout" tabindex="-1" aria-disabled="true">
							<strong>Sair</strong>
						</a>
						</security:authorize>
					</form>
				</div>
			</nav>
