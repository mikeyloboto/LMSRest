<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Genre"%>
<%@page import="com.gcit.library.service.AdminService"%>


<%
	AdminService service = new AdminService();
	Integer genreCount = service.getGenreCount();
	Integer numOfPages = 0;
	if (genreCount % 10 > 0) {
		numOfPages = genreCount / 10 + 1;
	} else {
		numOfPages = genreCount / 10;
	}
	List<Genre> genres = new ArrayList<>();
	//System.out.println(request.getParameter("pageNo"));
	if (request.getParameter("pageNo") != null) {
		genres = service.getAllGenres(Integer.parseInt((String) request.getParameter("pageNo")));
	} else {
		genres = service.getAllGenres(1);
	}
%>
${message}
<script>
	function searchGenre(page) {

		$.ajax({
			url : "searchGenres",
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
<script>
	function setPageNo(p) {
		//var but = document.getElementById('#pageNo')
		//but.value = p;
		searchGenre(p);
	}
</script>
<ol class="breadcrumb">
	<li><a href="index.jsp">Home</a></li>
	<li><a href="admin.jsp">Administrator</a></li>
	<li class="active">Genre Management</li>
</ol>
<div class="container">
	<div>
		<button type="button" class="btn btn-success" data-toggle="modal"
			data-target="#addGenreModal" href="adminGenreAdd.jsp">Add
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

	<div class="modal fade bs-example-modal-lg" id="editGenreModal"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">....</div>
		</div>
	</div>
	<div class="modal fade bs-example-modal-lg" id="addGenreModal"
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