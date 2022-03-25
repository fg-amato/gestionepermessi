<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	<jsp:include page="../header.jsp" />
	<title>Ricerca</title>
	
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
			        <h5>Ricerca elementi</h5> 
			    </div>
			    <div class='card-body'>
		
						<form method="post" action="${pageContext.request.contextPath}/messaggio/list" class="row g-3">
						<!-- tipoPermesso, dataInizio, dataFine, letto, codiceCertificato -->
							
							
							<div class="col-md-6">
								<label for="oggetto" class="form-label">Oggetto</label>
								<input type="text" name="oggetto" id="oggetto" class="form-control" placeholder="Inserire oggetto" >
							</div>
							
							<div class="col-md-6">
								<label for="testo" class="form-label">Testo</label>
								<input type="text" name="testo" id="testo" class="form-control" placeholder="Inserire testo" >
							</div>
								
							<div class="col-md-6">
								<p> Stato messaggio </p>
 								<div class="form-check">
  									<input class="form-check-input" type="radio" value = "true" name="letto" id="flexRadioDefault1">
  										<label class="form-check-label" for="flexRadioDefault1">
    										Letto
  										</label>
								</div>
								<div class="form-check">
  									<input class="form-check-input" type="radio" value = "false" name="letto" id="flexRadioDefault2">
  										<label class="form-check-label" for="flexRadioDefault2">
    										Non letto
  										</label>
								</div>
								
								<div class="form-check">
  									<input class="form-check-input" type="radio" value ="" name="letto" id="flexRadioDefault2">
  										<label class="form-check-label" for="flexRadioDefault3">
    										Letti e non
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