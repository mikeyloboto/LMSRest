<%@include file="include.html"%>

${message}
<script>
	function searchGenre(page) {

		$.ajax({
			url : "searchGenre",
			data : {
				searchString : $('#searchString').val(),
				pageNo : page
			}
		}).done(function(data) {
			//alert(data);
			var arr_data = String(data).split("\n");
			$('#tableGenre').html(arr_data[0]);
			$('#pagination').html(arr_data[1]);
			
		})
	}
</script>
<ol class="breadcrumb">
	<li><a href="index">Home</a></li>
	<li><a href="admin">Administrator</a></li>
	<li class="active">Genre Management</li>
</ol>
<div class="container">
	<div>
		<button type="button" class="btn btn-success" data-toggle="modal"
			data-target="#genreModal" href="adminGenreAdd">Add
			New Genre</button>
		<div class="page-header">
			<h1>List of Existing Genres in LMS</h1>
		</div>
		<form action="searchGenres">
			<input type="text" class="form-control" name="searchString"
				id="searchString" placeholder="Search" oninput="searchGenre(1)">
		</form>
		<nav aria-label="Page navigation">
			<ul class="pagination" id="pagination">
				
			</ul>
		</nav>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>Genre Name</th>
					<th>Actions</th>
					<!-- <th>Delete</th> -->
				</tr>
			</thead>
			<tbody id="tableGenre">
				
			</tbody>
		</table>
	</div>

	<div class="modal fade bs-example-modal-lg" id="genreModal"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">....</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		// codes works on all bootstrap modal windows in application
		$('.modal').on('hidden.bs.modal', function(e) {
			$(this).removeData();
		});

	});
</script>
<script>
	$(document).ready ( function(){
		searchGenre(1);
	});
</script>