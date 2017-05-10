<%@include file="include.html"%>

${message}
<script>
	function searchBook(page) {

		$.ajax({
			url : "searchBooks",
			data : {
				searchString : $('#searchString').val(),
				pageNo : page
			}
		}).done(function(data) {
			//alert(data);
			var arr_data = String(data).split("\n");
			$('#tableBook').html(arr_data[0]);
			$('#pagination').html(arr_data[1]);
			
		})
	}
</script>
<script>
	function setPageNo(p) {
		searchBook(p);
	}
</script>
<ol class="breadcrumb">
  <li><a href="index.jsp">Home</a></li>
  <li><a href="admin.jsp">Administrator</a></li>
  <li class="active">Book Management</li>
</ol>
<div class="container">
	<div>
		<button type="button" class="btn btn-success" data-toggle="modal"
			data-target="#editBookModal" href="adminBookAdd">Add New
			Book</button>
	<div class="page-header">
		<h1>List of Existing Books in LMS</h1>
	</div>
	<form action="searchBooks">
			<input type="text" class="form-control" name="searchString"
				id="searchString" placeholder="Search" oninput="searchBook(1)">
		</form>
	<nav aria-label="Page navigation">
		<ul class="pagination" id="pagination">
		
		</ul>
	</nav>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>#</th>
				<th>Book Name</th>
				<th>Genre(s)</th>
				<th>Publisher</th>
				<th>Actions</th>
				<!-- <th>Delete</th> -->
			</tr>
		</thead>
		<tbody id="tableBook">
			
		</tbody>
	</table>
</div>

<div class="modal fade bs-example-modal-lg" id="editBookModal"
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
		searchBook(1);
	});
</script>