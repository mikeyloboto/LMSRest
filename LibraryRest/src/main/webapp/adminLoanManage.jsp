<%@include file="include.html"%>

<script>
	function searchLoan(page) {

		$.ajax({
			url : "searchLoan",
			data : {
				pageNo : page
			}
		}).done(function(data) {
			//alert(data);
			var arr_data = String(data).split("\n");
			$('#tableLoan').html(arr_data[0]);
			$('#pagination').html(arr_data[1]);

		})
	}
</script>
${message}
<ol class="breadcrumb">
	<li><a href="index">Home</a></li>
	<li><a href="admin">Administrator</a></li>
	<li class="active">Loan Management</li>
</ol>
<div class="container">
	<div>
		<div class="page-header">
			<h1>List of Active Loans in LMS</h1>
		</div>
		<nav aria-label="Page navigation">
			<ul class="pagination" id="pagination">
				
			</ul>
		</nav>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>Borrower</th>
					<th>Book</th>
					<th>Branch</th>
					<th>Date Out</th>
					<th>Due Date</th>
					<th>Actions</th>
					<!-- <th>Delete</th> -->
				</tr>
			</thead>
			<tbody id="tableLoan">
				
			</tbody>
		</table>
	</div>

	<div class="modal fade bs-example-modal-lg" id="loanModal"
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
		searchLoan(1);
	});
</script>