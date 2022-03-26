<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100">
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

					<dl
						class="row ${show_richiesta_attr.tipoPermesso == 'MALATTIA'? '':'d-none'} ">
						<dt class="col-sm-3 text-right">Codice Certificato:</dt>
						<dd class="col-sm-9">${show_richiesta_attr.codiceCertificato}</dd>
					</dl>

					<dl class="row">
						<dt class="col-sm-3 text-right">Data Inizio:</dt>
						<dd class="col-sm-9">${show_richiesta_attr.dataInizio==null? 'In attesa di aggiornamento' :''}<fmt:formatDate
								type="date" value="${show_richiesta_attr.dataInizio}" />
						</dd>
					</dl>

					<dl class="row">
						<dt class="col-sm-3 text-right">Data Fine:</dt>
						<dd class="col-sm-9">${show_richiesta_attr.dataFine==null? 'In attesa di aggiornamento' :''}<fmt:formatDate
								type="date" value="${show_richiesta_attr.dataFine}" />
						</dd>
					</dl>

					<dl class="row">
						<dt class="col-sm-3 text-right">Stato:</dt>
						<dd class="col-sm-9">${ show_richiesta_attr.isApprovato()? 'Approvato' : 'Non approvato'}</dd>
					</dl>

					<dl class="row">
						<dt class="col-sm-3 text-right">Note:</dt>
						<dd class="col-sm-9">${show_richiesta_attr.note.isEmpty()? 'Nessuna nota' : show_richiesta_attr.note}</dd>
					</dl>

					<dl
						class="row ${show_richiesta_attr.attachment==null? 'd-none' : ''}">
						<dt class="col-sm-3 text-right">Allegato:</dt>
						<dd class="col-sm-9">
							<a href="${pageContext.request.contextPath}/attachment/showAttachment/${show_richiesta_attr.attachment.id }"> ${show_richiesta_attr.attachment.nomeFile}</a>
						</dd>
					</dl>

					<!-- info Dipendente -->
					<p>
						<a class="btn btn-primary btn-sm" data-bs-toggle="collapse"
							href="#collapseExample" role="button" aria-expanded="false"
							aria-controls="collapseExample"> Info Dipendente </a>
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
					<sec:authorize access="hasRole('BO_USER')">
						<c:if test="${ show_richiesta_attr.isNotStarted() }">
							<a id="changeApprovazioneLink_#_${show_richiesta_attr.id }"
								class="btn btn-outline-${show_richiesta_attr.isApprovato()?'danger':'success'} link-for-modal"
								data-bs-toggle="modal" data-bs-target="#confirmOperationModal">${show_richiesta_attr.isApprovato()?'Disapprova':'Approva'}</a>
						</c:if>
					</sec:authorize>
					<a href="${pageContext.request.contextPath}/home"
						class='btn btn-outline-secondary'> <i
						class='fa fa-chevron-left'></i> Home
					</a>
				</div>
				<!-- end card -->
			</div>


			<!-- end container -->
		</div>

	</main>

	<!-- Footer -->
	<jsp:include page="../footer.jsp" />

	<!-- Modal -->
	<div class="modal fade" id="confirmOperationModal" tabindex="-1"
		aria-labelledby="confirmOperationModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="confirmOperationModalLabel">Conferma
						Operazione</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">Continuare con l'operazione?</div>
				<form method="post"
					action="${pageContext.request.contextPath}/richieste_permesso/cambiaApprovazione">
					<div class="modal-footer">
						<input type="hidden" name="idRichiestaForChangingApprovazione"
							id="idRichiestaForChangingApprovazione">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Chiudi</button>
						<input type="submit" value="Continua" class="btn btn-primary">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- end Modal -->
	<script type="text/javascript">
		<!-- aggancio evento click al conferma del modal  -->
		$(".link-for-modal").click(function(){
			<!-- mi prendo il numero che poi sarà l'id. Il 18 è perché 'changeStatoLink_#_' è appunto lungo 18  -->
			var callerId = $(this).attr('id').substring(25);
			<!-- imposto nell'hidden del modal l'id da postare alla servlet -->
			$('#idRichiestaForChangingApprovazione').val(callerId);
		});
	</script>
</body>
</html>