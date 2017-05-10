<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Loan"%>
<%@page import="com.gcit.library.service.AdminService"%>


<%
	AdminService service = new AdminService();
	Integer loanCount = service.getLoanCount();
	Integer numOfPages = 1;
	if (loanCount % 10 > 0) {
		numOfPages = loanCount / 10 + 1;
	} else {
		numOfPages = loanCount / 10;
	}
	List<Loan> loans = new ArrayList<>();
	//System.out.println(request.getParameter("pageNo"));
	if (request.getParameter("pageNo") != null) {
		loans = service.getAllLoans(Integer.parseInt((String) request.getParameter("pageNo")));
	} else {
		loans = service.getAllLoans(1);
	}
%>
${message}
<ol class="breadcrumb">
	<li><a href="index.jsp">Home</a></li>
	<li><a href="admin.jsp">Administrator</a></li>
	<li class="active">Loan Management</li>
</ol>
<div class="container">
	<div>
		<div class="page-header">
			<h1>List of Active Loans in LMS</h1>
		</div>
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<%
					for (int i = 1; i <= numOfPages; i++) {
				%>
				<li><a href="adminLoanManage.jsp?pageNo=<%=i%>"><%=i%></a></li>
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
					<th>Borrower</th>
					<th>Book</th>
					<th>Branch</th>
					<th>Date Out</th>
					<th>Due Date</th>
					<th>Actions</th>
					<!-- <th>Delete</th> -->
				</tr>
			</thead>
			<tbody>
				<%
					for (Loan p : loans) {
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
						Integer id = loans.indexOf(p) + 1 + ((mod - 1) * 10);
					%>
					<td><%=id%></td>
					<td><%=p.getBorrower().getName()%></td>
					<td><%=p.getBook().getDescription()%></td>
					<td><%=p.getBranch().getBranchName()%></td>
					<td><%=p.getDateOut().toString().substring(0, 10)%></td>
					<td><%=p.getDateDue()%></td>

					<td><button type="button" class="btn btn-primary"
							data-toggle="modal" data-target="#editLoanModal"
							href="adminLoanEdit.jsp?cardNo=<%=p.getBorrower().getCardNo()%>&bookId=<%=p.getBook().getBookId()%>&branchNo=<%=p.getBranch().getBranchNo()%>&dateOut=<%=p.getDateOut().toString()%>">Override Due Date</button>
						<a type="button" class="btn btn-danger"
						href="closeLoan?cardNo=<%=p.getBorrower().getCardNo()%>&bookId=<%=p.getBook().getBookId()%>&branchNo=<%=p.getBranch().getBranchNo()%>&dateOut=<%=p.getDateOut().toString()%>&dueDate=<%=p.getDateDue().toString()%>">Close</a></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>

	<div class="modal fade bs-example-modal-lg" id="editLoanModal"
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