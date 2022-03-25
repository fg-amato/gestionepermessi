<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<style>
		    .error_field {
		        color: red; 
		    }
	</style>
	<jsp:include page="../header.jsp" />
	<title>Inserisci Richiesta</title>
	
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	
			<%-- se l'attributo in request ha errori --%>
		<spring:hasBindErrors  name="insert_richiesta_attr">
						<%-- alert errori --%>
			<div class="alert alert-danger " role="alert">
				Attenzione!! Sono presenti errori di validazione
			</div>
		</spring:hasBindErrors>

			
		
			<div class='card'>
			    <div class='card-header'>
			        <h5>Inserisci nuova richiesta</h5> 
			    </div>
			    
			    <div class='card-body'>
					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
						<form:form modelAttribute="insert_richiesta_attr" method="post" action="save" novalidate="novalidate" class="row g-3">
						  <input type="hidden" name="usernameUtente" value= <sec:authentication property = "principal.username"/> > 
						  
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${insert_richiesta_attr.dataInizio}' />
								<div class="col-md-6">
									<label for="dataInizio" class="form-label">Data Inizio<span class="text-danger">*</span></label>
                        			<spring:bind path="dataInizio">
	                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id="dataInizio" type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataInizio" required 
	                            		value="${parsedDate}" >
		                            </spring:bind>
	                            	<form:errors  path="dataInizio" cssClass="error_field" />
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${insert_richiesta_attr.dataFine}' />
								<div class="col-md-6" id ="dataFineDiv">
									<label for="dataFine" class="form-label">Data Fine<span class="text-danger">*</span></label>
                        			<spring:bind path="dataFine">
	                        		<input class="form-control ${status.error ? 'is-invalid' : ''}" id=dataFine type="date" placeholder="dd/MM/yy"
	                            		title="formato : gg/mm/aaaa"  name="dataFine" required 
	                            		value="${parsedDate}" >
		                            </spring:bind>
	                            	<form:errors  path="dataFine" cssClass="error_field" />
								</div>
								
							
								<div class="col-md-6">
									<label for="tipoPermesso" class="form-label">Tipo permesso<span class="text-danger">*</span></label>
								    <spring:bind path="tipoPermesso">
									    <select class="form-select ${status.error ? 'is-invalid' : ''}" id="tipoPermesso" name="tipoPermesso" required>
									    	<option value="" selected> - Selezionare - </option>
									      	<option value="FERIE" ${insert_richiesta_attr.tipoPermesso == 'FERIE'?'selected':''} >FERIE</option>
									      	<option value="MALATTIA" ${insert_richiesta_attr.tipoPermesso == 'MALATTIA'?'selected':''} >MALATTIA</option>
									    </select>
								    </spring:bind>
								    <form:errors  path="tipoPermesso" cssClass="error_field" />
								</div>
							
							<!-- <script>
								$('.form-select').click(function(){
								
									if($('#tipoPermesso :selected').text()=== 'MALATTIA'){
										//console.log("MALATTIA");
										$("#prova").removeClass('d-none');
									}else{
										//console.log("FERIE");
										$("#prova").addClass('d-none');
									}
								});
							</script>
							 -->
							
								
							
							<div class="col-md-6  d-none" id ="prova">
									<label for="codiceCertificato" class="form-label">Codice Certificato<span class="text-danger">*</span></label>
									<spring:bind path="codiceCertificato">
									<input type="text" class="form-control ${status.error ? 'is-invalid' : ''}" name="codiceCertificato" id="codiceCertificato" placeholder="Inserire il codice certificato" value="${insert_richiesta_attr.codiceCertificato }">
									</spring:bind>
									<form:errors  path="codiceCertificato" cssClass="error_field" />
							</div>	
							
									
							<div class="col-md-10">
									<label for="note" class="form-label">Note</label>
									<input type="text" class="form-control" name="note" id="note" placeholder="Inserire note" value="${edit_richiesta_attr.note }">
							</div>
							
							<div class="col-md-6 datiMalattia d-none" id="datiMalattia">
								<label for="allegato" class="form-label">Allegato</label>
								<input class="form-control" type="file" id="allegato" name="file" >
							</div>
							
							<div class="col-md-12 form-check">
									<div class="form-check">
										  <input class="form-check-input" name="check" type="checkbox" id="check">
										  <label class="form-check-label" for="check" >
										    Giorno Singolo
										  </label>
									</div>
							</div>
							
							<script>
								$(document).ready(function(){
									$('.form-select').click(function(){
										if($('#tipoPermesso :selected').text()=== 'MALATTIA'){
											$("#prova").removeClass('d-none');
											$("#datiMalattia").removeClass('d-none');
										}else{
											$("#prova").addClass('d-none');
											$("#datiMalattia").addClass('d-none');
										}
									});
					
									$('#check').click(function(){
									$("#dataFineDiv").toggle();
									});
								});
							</script>	
							
							<div class="col-12">	
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
								<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
							</div>
	
							
						</form:form>
			    
				<!-- end card-body -->			   
			    </div>
			</div>	
	
		</div>
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>