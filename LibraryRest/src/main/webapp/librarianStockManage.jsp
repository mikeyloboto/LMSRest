<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.gcit.library.entity.Book"%>
<%@page import="com.gcit.library.entity.Branch"%>
<%@page import="com.gcit.library.service.LibrarianService"%>


<%
	LibrarianService service = new LibrarianService();
	Branch activeBranch = service.getBranchFromID(Integer.parseInt(request.getParameter("branchId")));
	Integer bookCount = service.getBookCount(activeBranch);
	Integer numOfPages = 0;
	if (bookCount % 10 > 0) {
		numOfPages = bookCount / 10 + 1;
	} else {
		numOfPages = bookCount / 10;
	}
	Map<Book, Integer> books = new HashMap<>();
	//System.out.println(request.getParameter("pageNo"));
	if (request.getParameter("pageNo") != null) {
		books = service.getAllBooksInBranch(activeBranch,
				Integer.parseInt((String) request.getParameter("pageNo")));
	} else {
		books = service.getAllBooksInBranch(activeBranch, 1);
	}
%>

${message}
<ol class="breadcrumb">
	<li><a href="index.jsp">Home</a></li>
	<li><a href="librarian.jsp">Librarian Login</a></li>
	<li><a
		href="librarianMain.jsp?branchId=<%=activeBranch.getBranchNo()%>"><%=activeBranch.getBranchName()%>
			Management</a></li>
	<li class="active"><%=activeBranch.getBranchName()%> Stock</li>
</ol>
<div class="container">
	<div>
		<button type="button" class="btn btn-success" data-toggle="modal"
			data-target="#addStockModal" href="librarianBookAdd.jsp?branchId=<%=activeBranch.getBranchNo() %>">Add New
			Book to Branch</button>
		<div class="page-header">
			<h1>List of Book in <%=activeBranch.getBranchName() %></h1>
		</div>
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<%
					for (int i = 1; i <= numOfPages; i++) {
				%>
				<li><a href="librarianStockManage.jsp?pageNo=<%=i%>&branchId=<%=activeBranch.getBranchNo() %>"><%=i%></a></li>
				<%
					}
				%>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>Book</th>
					<th>Copies</th>
					<th>Actions</th>
					<!-- <th>Delete</th> -->
				</tr>
			</thead>
			<tbody>
				<%
					int counter = 0;
					for (Book b : books.keySet()) {

						Integer mod = 1;
						if (request.getParameter("pageNo") != null) {
							mod = Integer.parseInt(request.getParameter("pageNo"));
						}
						Integer pageNo = 1;
						if (request.getParameter("pageNo") != null) {
							pageNo = Integer.parseInt(request.getParameter("pageNo"));
						}
				%>
				<tr>
					<%
						Integer id = counter + 1 + ((mod - 1) * 10);
					%>
					<td><%=id%></td>
					<td><%=b.getDescription()%></td>
					<td><%=books.get(b)%></td>
					<td><button type="button" class="btn btn-primary"
							data-toggle="modal" data-target="#editStockModal"
							href="librarianStockEdit.jsp?bookId=<%=b.getBookId()%>&branchId=<%=activeBranch.getBranchNo()%>">Add
							/ Remove Copies</button> <!-- <a type="button" class="btn btn-danger"
						href="removeBook?bookId=<%=b.getBookId()%>">Delete</a>--></td>
				</tr>
				<%
					counter++;
					}
				%>
			</tbody>
		</table>
	</div>

	<div class="modal fade bs-example-modal-lg" id="editStockModal"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">....</div>
		</div>
	</div>
	<div class="modal fade bs-example-modal-lg" id="addStockModal"
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