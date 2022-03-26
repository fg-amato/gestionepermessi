<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header>
  <!-- Fixed navbar -->
 <nav class="navbar navbar-expand-lg navbar-dark bg-primary" aria-label="Eighth navbar example">
    <div class="container">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample07">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/home">Home</a>
          </li>
          <!-- admin -->
          <sec:authorize access="hasRole('ADMIN')">
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-expanded="false">Gestione Utenze</a>
		        <div class="dropdown-menu" aria-labelledby="dropdown01">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/utente/search">Ricerca Utenti</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/utente/insert">Inserisci Utente</a>
		        </div>
		      </li>
		      <li class="nav-item">
            	<a class="nav-link" href="${pageContext.request.contextPath}/dipendente/search">Ricerca Dipendenti</a>
          	</li>
		</sec:authorize>
		 <!-- bo_user -->
          <sec:authorize access="hasRole('BO_USER')">
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-expanded="false">Gestione Dipendenti</a>
		        <div class="dropdown-menu" aria-labelledby="dropdown01">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/dipendente/search">Ricerca Dipendenti</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/dipendente/insert">Inserisci Dipendenti</a>
		        </div>
		      </li>
		      <li class="nav-item">
            	<a class="nav-link" href="${pageContext.request.contextPath}/richieste_permesso/search">Ricerca Richieste </a>
          	</li>
          	<li class="nav-item">
            	<a class="nav-link" href="${pageContext.request.contextPath}/messaggio/search">Ricerca messaggi</a>
          	</li>
		</sec:authorize>
		
		<!-- dipendente user -->
          <sec:authorize access="hasRole('DIPENDENTE_USER')">
		      <li class="nav-item">
            	<a class="nav-link" href="${pageContext.request.contextPath}/richieste_permesso/search_personal">Ricerca Richieste </a>
          	</li>
		</sec:authorize>
		
        </ul>
      </div>
	  
       <sec:authorize access="isAuthenticated()">
       	
	      <div class="dropdown">
  			<button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
   			 Utente: <sec:authentication property="name"/> (${userInfo.nome } ${userInfo.cognome })
  			</button>
 			 <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
    			<li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a></li>
   				 <li><a class="dropdown-item" href="${pageContext.request.contextPath}/utente/resetPassword/<sec:authentication property = "principal.username"/>">Reset Password</a></li>
  			</ul>
		</div>
      </sec:authorize>
      
      <sec:authorize access="!isAuthenticated()">
      	<a href="${pageContext.request.contextPath}/logout">Logout</a>
      </sec:authorize>
    </div>
  </nav>
  
  
</header>
