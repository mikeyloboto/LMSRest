<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Add New Book</h4>
	</div>
	<form action="addBook" method="post">
		<div class="modal-body">
			<p>Enter the Title of Book:</p>
			<input class="form-control" type="text" name="bookName"
				required="required"> <br />
			<!-- Put shite here -->
			<p>Select Author(s):</p>
			<select multiple class="form-control" name="authors">
				${authors}
			</select> <br />
			<p>Select Genre(s):</p>
			<select multiple class="form-control" name="genres">
				${genres}
			</select> <br />
			<p>Select Publisher:</p>
			<select class="form-control" name="publisher"> ${publishers}
			</select> <br /> <br>
			<div class="alert alert-info" role="alert">
				<strong>Hint,</strong> you can use "Ctrl" button to select multiple
				authors and genres.
			</div>


		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Create</button>
		</div>
	</form>
</div>