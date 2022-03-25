<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Visualizza Richiesta</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Visualizza richiesta</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${show_richiesta_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Tipo permesso:</dt>
							  <dd class="col-sm-9">${show_richiesta_attr.tipoPermesso}</dd>
					    	</dl>
					    	<c:if test=" ${show_richiesta_attr.tipoPermesso== TipoPermesso.MALATTIA} ">
						    	<dl class="row">
								  <dt class="col-sm-3 text-right">Codice Certificato:</dt>
								  <dd class="col-sm-9">${show_richiesta_attr.codiceCertificato}</dd>
						    	</dl>
					    	</c:if>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data Inizio:</dt>
							  <dd class="col-sm-9">${show_richiesta_attr.dataInizio==null? 'In attesa di aggiornamento' :''}<fmt:formatDate type = "date" value = "${show_richiesta_attr.dataInizio}" /></dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data Fine:</dt>
							  <dd class="col-sm-9">${show_richiesta_attr.dataFine==null? 'In attesa di aggiornamento' :''}<fmt:formatDate type = "date" value = "${show_richiesta_attr.dataFine}" /></dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Stato:</dt>
							  <dd class="col-sm-9">${show_richiesta_attr.approvato?'Approvata' : 'Non approvata' }</dd>
					    	</dl>
					    	
					    	<dl class="row">
								  <dt class="col-sm-3 text-right">Note:</dt>
								  <dd class="col-sm-9">${show_richiesta_attr.note==null? 'Nessuna nota' : '${show_richiesta_attr.note}'}</dd>
						    </dl>
						    
						    <!-- info Dipendente -->
			    	<p>
					  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
					    Info Dipendente
					  </a>
					</p>
					<div class="collapse" id="collapseExample">
					  <div class="card card-body">
					  	<dl class="row">
						  <dt class="col-sm-3 text-right">Nome:</dt>
						  <dd class="col-sm-9">${show_richiesta_attr.dipendente.nome}</dd>
					   	</dl>
					   	<dl class="row">
						  <dt class="col-sm-3 text-right">Cognome:</dt>
						  <dd class="col-sm-9">${show_richiesta_attr.dipendente.cognome}</dd>
					   	</dl>
					   	<dl class="row">
						  <dt class="col-sm-3 text-right">Email:</dt>
						  <dd class="col-sm-9">${show_richiesta_attr.dipendente.email}</dd>
					   	</dl>
					   	<dl class="row">
						  <dt class="col-sm-3 text-right">Sesso:</dt>
						  <dd class="col-sm-9">${show_richiesta_attr.dipendente.sesso==null? 'In attesa aggiornamento admin' : show_richiesta_attr.dipendente.sesso}</dd>
					   	</dl>
					    
					  </div>
					<!-- end info Dipendente -->
					</div>
							
					    <!-- end card body -->
					    </div>
					    
					    <div class='card-footer'>
					        <a href="${pageContext.request.contextPath}/home" class='btn btn-outline-secondary' style='width:80px'>
					            <i class='fa fa-chevron-left'></i> Home
					        </a>
					    </div>
					<!-- end card -->
					</div>	
			  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>