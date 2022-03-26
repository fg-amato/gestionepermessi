<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Ricerca Richiesta</title>
	
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
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
			        <h5>Ricerca richieste</h5> 
			    </div>
			    <div class='card-body'>
		
						<form method="post" action="${pageContext.request.contextPath}/richieste_permesso/list" class="row g-3">
						<!-- tipoPermesso, dataInizio, dataFine, approvato, codiceCertificato -->
							
							
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
							
							<div class="col-md-6">
										<label for="dipendenteSearchInput" class="form-label">Dipendente:</label>
											<input class="form-control ${status.error ? 'is-invalid' : ''}" type="text" id="dipendenteSearchInput"
												name="dipendenteInput" value="${insert_richiesta_attr.dipendente.nome}${empty insert_richiesta_attr.dipendente.nome?'':' '}${insert_richiesta_attr.dipendente.cognome}">
										<input type="hidden" name="dipendente.id" id="dipendenteId" value="${insert_richiesta_attr.dipendente.id}">
							</div>
							
							
							<%-- FUNZIONE JQUERY UI PER AUTOCOMPLETE --%>
								<script>
									$("#dipendenteSearchInput").autocomplete({
										 source: function(request, response) {
											 	//quando parte la richiesta ajax devo ripulire dipendenteId
											 	//altrimenti quando modifico il campo, cancellando
											 	//qualche carattere, mi rimarrebbe comunque valorizzato il 
											 	//'vecchio id'
											 	$('#dipendenteId').val('');
											 	
										        $.ajax({
										            url: "${pageContext.request.contextPath}/dipendente/searchDipendentiAjax",
										            datatype: "json",
										            data: {
										                term: request.term,   
										            },
										            success: function(data) {
										                response($.map(data, function(item) {
										                    return {
											                    label: item.label,
											                    value: item.value
										                    }
										                }))
										            }
										        });
										    },
										//quando seleziono la voce nel campo deve valorizzarsi la descrizione
									    focus: function(event, ui) {
									        $("#dipendenteSearchInput").val(ui.item.label);
									        return false;
									    },
									    minLength: 2,
									    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
									    select: function( event, ui ) {
									    	$('#dipendenteId').val(ui.item.value);
									    	//console.log($('#dipendenteId').val())
									        return false;
									    }
									});
								</script>
								<!-- end script autocomplete -->
								
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