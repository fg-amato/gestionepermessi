<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Ricerca Richiesta</title>
	
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			
		
			<div class='card'>
			    <div class='card-header'>
			        <h5>Ricerca tra le tue richieste</h5> 
			    </div>
			    <div class='card-body'>
		
						<form method="post" action="${pageContext.request.contextPath}/richieste_permesso/listPersonal" class="row g-3">
						  <input type="hidden" name="usernameUtente" value= <sec:authentication property = "principal.username"/> > 
							
							<div class="col-md-6">
								<label for="dataInizio" class="form-label">Data Inizio</label>
                        		<input class="form-control" id="dataInizio" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dataInizio" >
							</div>
							
							<div class="col-md-6">
								<label for="dataFine" class="form-label">Data Fine</label>
                        		<input class="form-control" id="dataFine" type="date" placeholder="dd/MM/yy"
                            		title="formato : gg/mm/aaaa"  name="dataFine" >
							</div>
							
							<div class="col-md-6">
								<label for="tipoPermesso" class="form-label">Tipo Permesso</label>
								    <select class="form-select " id="tipoPermesso" name="tipoPermesso" >
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="FERIE" >FERIE</option>
								    	<option value="MALATTIA">MALATTIA</option>
							    	</select>
							</div>
								
							<div class="col-md-6 d-none" id ="prova">
								<label for="codiceCertificato" class="form-label">Codice certificato</label>
								<input type="text" name="codiceCertificato" id="codiceCertificato" class="form-control" placeholder="Inserire il codice certificato" >
							</div>
							
							<script>
								$('.form-select').change(function(){
								
									if($('#tipoPermesso :selected').text()=== 'MALATTIA'){
										//console.log("MALATTIA");
										$("#prova").removeClass('d-none');
									}else{
										//console.log("FERIE");
										$("#prova").addClass('d-none');
									}
								});
							</script>
								
							<div class="col-md-6">
								<p> Stato richiesta </p>
 								<div class="form-check">
  									<input class="form-check-input" type="radio" value = "true" name="approvato" id="flexRadioDefault1">
  										<label class="form-check-label" for="flexRadioDefault1">
    										Approvate
  										</label>
								</div>
								<div class="form-check">
  									<input class="form-check-input" type="radio" value = "false" name="approvato" id="flexRadioDefault2">
  										<label class="form-check-label" for="flexRadioDefault2">
    										Non approvate
  										</label>
								</div>
								
								<div class="form-check">
  									<input class="form-check-input" type="radio" value ="" name="approvato" id="flexRadioDefault2">
  										<label class="form-check-label" for="flexRadioDefault3">
    										Entrambe
  										</label>
								</div>
							</div>
							
							<div class="col-12">	
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
								<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								<a class="btn btn-outline-primary ml-2" href="${pageContext.request.contextPath}/richieste_permesso/insert">Add New</a>
							</div>
	
							
						</form>
			    
				<!-- end card-body -->			   
			    </div>
			</div>	
	
		</div>
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>