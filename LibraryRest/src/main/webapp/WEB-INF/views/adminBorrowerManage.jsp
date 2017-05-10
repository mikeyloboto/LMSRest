<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Borrower"%>
<%@page import="com.gcit.library.service.AdminService"%>


<%
	AdminService service = new AdminService();
	Integer borrowerCount = service.getBorrowerCount();
	Integer numOfPages = 0;
	if (borrowerCount % 10 > 0) {
		numOfPages = borrowerCount / 10 + 1;
	} else {
		numOfPages = borrowerCount / 10;
	}
	List<Borrower> borrowers = new ArrayList<>();
	//System.out.println(request.getParameter("pageNo"));
	if (request.getParameter("pageNo") != null) {
		borrowers = service.getAllBorrowers(Integer.parseInt((String) request.getParameter("pageNo")));
	} else {
		borrowers = service.getAllBorrowers(1);
	}
%>
${message}
<script>
	function searchBorrower(page) {

		$.ajax({
			url : "searchBorrowers",
			data : {
				searchString : $('#searchString').val(),
				pageNo : page
			}
		}).done(function(data) {
			//alert(data);
			var arr_data = String(data).split("\n");
			$('#tableBorrower').html(arr_data[0]);
			$('#pagination').html(arr_data[1]);
			
		})
	}
</script>
<script>
	function setPageNo(p) {
		//var but = document.getElementById('#pageNo')
		//but.value = p;
		searchBorrower(p);
	}
</script>
<ol class="breadcrumb">
	<li><a href="index.jsp">Home</a></li>
	<li><a href="admin.jsp">Administrator</a></li>
	<li class="active">Borrower Management</li>
</ol>
<div class="container">
	<div>
		<button type="button" class="btn btn-success" data-toggle="modal"
			data-target="#addBorrowerModal" href="adminBorrowerAdd.jsp">Add
			New Borrower</button>
		<div class="page-header">
			<h1>List of Existing Borrowers in LMS</h1>
		</div>
		<form action="searchPublishers">
			<input type="text" class="form-control" name="searchString"
				id="searchString" placeholder="Search" oninput="searchBorrower(1)">
		</form>
		<nav aria-label="Page navigation">
			<ul class="pagination" id="pagination">
				
		
			</ul>
		</nav>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Card Number</th>
					<th>Borrower Name</th>
					<th>Address</th>
					<th>Phone</th>
					<th>Actions</th>
					<!-- <th>Delete</th> -->
				</tr>
			</thead>
			<tbody id="tableBorrower">
				
			</tbody>
		</table>
	</div>

	<div class="modal fade bs-example-modal-lg" id="editBorrowerModal"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">....</div>
		</div>
	</div>
	<div class="modal fade bs-example-modal-lg" id="addBorrowerModal"
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
		searchBorrower(1);
	});
</script>