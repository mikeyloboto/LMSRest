<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Publisher"%>
<%@page import="com.gcit.library.service.AdminService"%>


<%
	AdminService service = new AdminService();
	Integer publisherCount = service.getPublisherCount();
	Integer numOfPages = 0;
	if (publisherCount % 10 > 0) {
		numOfPages = publisherCount / 10 + 1;
	} else {
		numOfPages = publisherCount / 10;
	}
	List<Publisher> publishers = new ArrayList<>();
	//System.out.println(request.getParameter("pageNo"));
	if (request.getParameter("pageNo") != null) {
		publishers = service.getAllPublishers(Integer.parseInt((String) request.getParameter("pageNo")));
	} else {
		publishers = service.getAllPublishers(1);
	}
%>
${message}
<script>
	function searchPublisher(page) {

		$.ajax({
			url : "searchPublishers",
			data : {
				searchString : $('#searchString').val(),
				pageNo : page
			}
		}).done(function(data) {
			//alert(data);
			var arr_data = String(data).split("\n");
			$('#tablePublisher').html(arr_data[0]);
			$('#pagination').html(arr_data[1]);
			
		})
	}
</script>
<script>
	function setPageNo(p) {
		//var but = document.getElementById('#pageNo')
		//but.value = p;
		searchPublisher(p);
	}
</script>
<input type="hidden" name="pageNo" id="pageNo" value="1">
<ol class="breadcrumb">
	<li><a href="index.jsp">Home</a></li>
	<li><a href="admin.jsp">Administrator</a></li>
	<li class="active">Publisher Management</li>
</ol>
<div class="container">
	<div>
		<button type="button" class="btn btn-success" data-toggle="modal"
			data-target="#addPublisherModal" href="adminPublisherAdd.jsp">Add
			New Publisher</button>
		<div class="page-header">
			<h1>List of Existing Publishers in LMS</h1>
		</div>
		<form action="searchPublishers">
			<input type="text" class="form-control" name="searchString"
				id="searchString" placeholder="Search" oninput="searchPublisher(1)">
		</form>
		<nav aria-label="Page navigation">
			<ul class="pagination" id="pagination">
			
			</ul>
		</nav>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>Publisher Name</th>
					<th>Address</th>
					<th>Phone</th>
					<th>Actions</th>
					<!-- <th>Delete</th> -->
				</tr>
			</thead>
			<tbody id="tablePublisher">

			</tbody>
		</table>
	</div>

	<div class="modal fade bs-example-modal-lg" id="editPublisherModal"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">....</div>
		</div>
	</div>
	<div class="modal fade bs-example-modal-lg" id="addPublisherModal"
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
		searchPublisher(1);
	});
</script>