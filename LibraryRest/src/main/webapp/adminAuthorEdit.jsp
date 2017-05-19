<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Edit ${authorName}</h4>
	</div>
	<form action="editAuthor" method="post">
		<div class="modal-body">
			<p>Enter new name for author:</p>
			<input type="hidden" name="authorId" value="${authorId}">
			<input class="form-control" type="text" name="authorName"
				required="required" value="${authorName}"> <br />

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save</button>
		</div>
	</form>
</div>