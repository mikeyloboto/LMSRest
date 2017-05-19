<div>
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Edit ${publisherName }</h4>
	</div>
	<form action="editPublisher" method="post">
		<div class="modal-body">
			<p>Enter new name for publisher:</p>
			<input type="hidden" name="publisherId" value="${publisherId }">
			<input class="form-control" type="text" name="publisherName"
				required="required" value="${publisherName }"> <br />
				<p>Enter new address for publisher:</p>
				<input class="form-control" type="text" name="publisherAddress" value="${publisherAddress }"><br />
				<p>Enter new phone for publisher:</p>
				<input class="form-control" type="text" name="publisherPhone" value="${publisherPhone }">
			<!-- Put shite here -->

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save</button>
		</div>
	</form>
</div>