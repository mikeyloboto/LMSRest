<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Edit Loan</h4>
	</div>
	<form action="editLoan" method="post">
		<div class="modal-body">
			<p>Borrower:</p>
			<p>
				<input class="form-control" type="text"	value="${borrowerName }" disabled>
			</p>
			<p>Book:</p>
			<p>
				<input class="form-control" type="text"	value="${bookTitle }" disabled>
			</p>
			<p>Branch:</p>
			<p><input class="form-control" type="text"	value="${branchName }" disabled></p>
			<p>Date Out:</p>
			<p><input class="form-control" type="datetime"	value="${dateOut }" disabled></p>
			<p>Date Due:</p>
			<p><input class="form-control" type="date" name="dueDate" value="${dueDate }"></p>
			<p>Date In:</p>
			<p><input class="form-control" type="text"	value="---" disabled></p>
			<!-- Put shite here -->

			<input type="hidden" name="cardNo" value="${cardNo }">
			<input type="hidden" name="bookId" value="${bookId }">
			<input type="hidden" name="branchId" value="${branchId }">
			<input type="hidden" name="dateOut" value="${dateOut }">
			
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save</button>
		</div>
	</form>
</div>