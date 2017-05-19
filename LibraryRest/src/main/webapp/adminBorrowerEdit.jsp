<div>
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Edit ${borrowerName }</h4>
	</div>
	<form action="editBorrower" method="post">
		<div class="modal-body">
			<p>Enter new name for borrower:</p>
			<input type="hidden" name="borrowerId" value="${borrowerId }">
			<input class="form-control" type="text" name="borrowerName"
				required="required" value="${borrowerName }"> <br />
				<p>Enter new address for borrower:</p>
				<input class="form-control" type="text" name="borrowerAddress" value="${borrowerAddress }"><br />
				<p>Enter new phone for borrower:</p>
				<input class="form-control" type="text" name="borrowerPhone" value="${borrowerPhone }">
			<!-- Put shite here -->

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save</button>
		</div>
	</form>
</div>