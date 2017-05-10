<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Branch"%>
<%@page import="com.gcit.library.service.AdminService"%>


<%
	AdminService service = new AdminService();
	Integer branchCount = service.getBranchCount();
	Integer numOfPages = 0;
	if (branchCount % 10 > 0) {
		numOfPages = branchCount / 10 + 1;
	} else {
		numOfPages = branchCount / 10;
	}
	List<Branch> branches = new ArrayList<>();
	//System.out.println(request.getParameter("pageNo"));
	if (request.getParameter("pageNo") != null) {
		branches = service.getAllBranches(Integer.parseInt((String) request.getParameter("pageNo")));
	} else {
		branches = service.getAllBranches(1);
	}
%>
${message}
<script>
	function searchBranch(page) {

		$.ajax({
			url : "searchBranches",
			data : {
				searchString : $('#searchString').val(),
				pageNo : page
			}
		}).done(function(data) {
			//alert(data);
			var arr_data = String(data).split("\n");
			$('#tableBranch').html(arr_data[0]);
			$('#pagination').html(arr_data[1]);

		})
	}
</script>
<script>
	function setPageNo(p) {
		//var but = document.getElementById('#pageNo')
		//but.value = p;
		searchBranch(p);
	}
</script>
<ol class="breadcrumb">
	<li><a href="index.jsp">Home</a></li>
	<li><a href="admin.jsp">Administrator</a></li>
	<li class="active">Branch Management</li>
</ol>
<div class="container">
	<div>
		<button type="button" class="btn btn-success" data-toggle="modal"
			data-target="#addBranchModal" href="adminBranchAdd.jsp">Add
			New Branch</button>
		<div class="page-header">
			<h1>List of Existing Branches in LMS</h1>
		</div>
		<form action="searchBrances">
			<input type="text" class="form-control" name="searchString"
				id="searchString" placeholder="Search" oninput="searchBranch(1)">
		</form>
		<nav aria-label="Page navigation">
			<ul class="pagination" id="pagination">

			</ul>
		</nav>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>Branch Name</th>
					<th>Address</th>
					<th>Actions</th>
					<!-- <th>Delete</th> -->
				</tr>
			</thead>
			<tbody id="tableBranch">

			</tbody>
		</table>
	</div>

	<div class="modal fade bs-example-modal-lg" id="editBranchModal"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">....</div>
		</div>
	</div>
	<div class="modal fade bs-example-modal-lg" id="addBranchModal"
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
	$(document).ready(function() {
		searchBranch(1);
	});
</script>