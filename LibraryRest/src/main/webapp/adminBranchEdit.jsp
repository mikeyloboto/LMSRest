<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Edit ${branchName }</h4>
	</div>
	<form action="editBranch" method="post">
		<div class="modal-body">
			<p>Enter new name for branch:</p>
			<input type="hidden" name="branchId" value="${branchId }">
			<input class="form-control" type="text" name="branchName"
				required="required" value="${branchName }"> <br />
				<p>Enter new address for branch:</p>
				<input class="form-control" type="text" name="branchAddress" value="${branchAddress }">
			<!-- Put shite here -->

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save</button>
		</div>
	</form>
</div>