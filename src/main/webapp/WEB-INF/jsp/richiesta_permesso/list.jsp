<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<jsp:include page="../header.jsp" />
	<title>Pagina dei risultati</title>
	
</head>
<body class="d-flex flex-column h-100">
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	
			<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
			  ${successMessage}
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
			</div>
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
			  ${errorMessage}
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
			</div>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Lista dei risultati</h5> 
			    </div>
			    <div class='card-body'>
			    <sec:authorize access="hasRole('DIPENDENTE_USER')">
			    	<a class="btn btn-primary " href="${pageContext.request.contextPath}/richieste_permesso/insert">Add New</a>
			    </sec:authorize>
			    <sec:authorize access="hasRole('DIPENDENTE_USER')">
			    	<a href="${pageContext.request.contextPath}/richieste_permesso/search_personal" class='btn btn-outline-secondary' >
				            <i class='fa fa-chevron-left'></i> Torna alla Ricerca
				        </a>
			    </sec:authorize>
			    
			     <sec:authorize access="hasRole('BO_USER')">
			    	<a href="${pageContext.request.contextPath}/richieste_permesso/search" class='btn btn-outline-secondary' >
				            <i class='fa fa-chevron-left'></i> Torna alla Ricerca
				        </a>
			    </sec:authorize>	
			    	
			    
			        <div class='table-responsive'>
			            <table class='table table-striped ' >
			                <thead>
			                    <tr>
			                        <th>Tipo Permesso</th>
			                        <th>Data Inizio</th>
			                        <th>Data Fine</th>
			                        <th>Stato</th>
			                        <th>Azioni</th>
			                    </tr>
			                </thead>
			                <tbody>
			                	<c:forEach items="${richieste_list_attribute }" var="richiestaItem">
									<tr>
										<td>${richiestaItem.tipoPermesso }</td>
										<td><fmt:formatDate type = "date" value = "${richiestaItem.dataInizio }" /></td>
										<td><fmt:formatDate type = "date" value = "${richiestaItem.dataFine }" /></td>
										<td>${richiestaItem.isApprovato()? 'Approvato' : 'Non approvato'}</td>
										<td>
											<a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/richieste_permesso/show/${richiestaItem.id }">Visualizza</a>
											<sec:authorize access="hasRole('DIPENDENTE_USER')">
												<c:if test = "${ richiestaItem.isNotStarted() }">
													<a class="btn  btn-sm btn-outline-warning ml-2 mr-2" href="${pageContext.request.contextPath}/richieste_permesso/edit/${richiestaItem.id }">Edit</a>
													<a id="deleteRichiestaLink_#_${richiestaItem.id }" class="btn btn-outline-danger btn-sm link-for-modal" data-bs-toggle="modal" data-bs-target="#confirmOperationModal1">Delete</a>
												</c:if>
											</sec:authorize>
										</td>			
									</tr>
								</c:forEach>
			                </tbody>
			            </table>
			        </div>
			   
				<!-- end card-body -->			   
			    </div>
			</div>	
	
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
	
	
	
	
	<!-- Modal -->
	<div class="modal fade" id="confirmOperationModal1" tabindex="-1"  aria-labelledby="confirmOperationModalLabel"
	    aria-hidden="true">
	    <div class="modal-dialog" >
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="confirmOperationModalLabel1">Conferma Operazione</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">
	                Continuare con l'operazione? 
	            </div>
	            <form method="post" action="${pageContext.request.contextPath}/richieste_permesso/delete" >
		            <div class="modal-footer">
		            	<input type="hidden" name="idRichiestaToDelete" id="idRichiestaToDelete">
		                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
		                <input type="submit" value="Continua"  class="btn btn-danger">
		            </div>
	            </form>
	        </div>
	    </div>
	</div>
	
		<script type="text/javascript">
		<!-- aggancio evento click al conferma del modal  -->
		$(".link-for-modal").click(function(){
			<!-- mi prendo il numero che poi sarà l'id. Il 18 è perché 'changeStatoLink_#_' è appunto lungo 18  -->
			var callerId = $(this).attr('id').substring(22);
			<!-- imposto nell'hidden del modal l'id da postare alla servlet -->
			$('#idRichiestaToDelete').val(callerId);
		});
	</script>
	
	
</body>
</html>