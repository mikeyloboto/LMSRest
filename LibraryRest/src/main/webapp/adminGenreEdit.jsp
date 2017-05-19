<div>
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Edit ${genreName }</h4>
	</div>
	<form action="editGenre" method="post">
		<div class="modal-body">
			<p>Enter new name for genre:</p>
			<input type="hidden" name="genreId" value="${genreId }">
			<input class="form-control" type="text" name="genreName"
				required="required" value="${genreName }"> <br />
			<!-- Put shite here -->

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save</button>
		</div>
	</form>
</div>