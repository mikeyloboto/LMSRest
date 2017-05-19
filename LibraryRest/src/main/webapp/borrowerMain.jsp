<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page import="java.time.LocalDate"%>
<%@page import="com.gcit.library.entity.Borrower"%>
<%@page import="com.gcit.library.entity.Branch"%>
<%@page import="com.gcit.library.entity.Loan"%>
<%@page import="com.gcit.library.service.BorrowerService"%>


<%
	BorrowerService service = new BorrowerService();
	Branch activeBranch = service.getBranchFromID(Integer.parseInt(request.getParameter("branchId")));
	Borrower activeBorrower = service
			.getBorrowerFromID(Integer.parseInt(request.getAttribute("authCardNo").toString()));

	Integer loanCount = service.getLoanCount(activeBorrower);
	//System.out.println(loanCount);
	Integer numOfPages = 1;
	if (loanCount % 10 > 0) {
		numOfPages = loanCount / 10 + 1;
	} else {
		numOfPages = loanCount / 10;
	}
	List<Loan> loans = new ArrayList<>();
	//System.out.println(request.getParameter("pageNo"));
	if (request.getParameter("pageNo") != null) {
		loans = service.getAllLoans(activeBorrower, Integer.parseInt((String) request.getParameter("pageNo")));
	} else {
		loans = service.getAllLoans(activeBorrower, 1);
	}
%>

${message}
<ol class="breadcrumb">
	<li><a href="index.jsp">Home</a></li>
	<li><a href="borrower.jsp">Borrower Login</a></li>
	<li class="active"><%=activeBorrower.getName()%>'s Homepage</li>
</ol>
<div class="container">
	<button type="button" class="btn btn-success" data-toggle="modal"
		data-target="#borrowBookModal"
		href="borrowerLoanAdd.jsp?branchNo=<%=activeBranch.getBranchNo()%>&cardNo=<%=activeBorrower.getCardNo() %>">Borrow a New Book from <%=activeBranch.getBranchName() %></button>
	<div>
		<div class="page-header">
			<h1>
				Welcome,
				<%=activeBorrower.getName()%></h1>
			<p>Your current active loans:</p>
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
							String butClass = "";
							if (p.getDateDue().isBefore(LocalDate.now())) {
								butClass = "btn btn-danger";
							} else if (activeBranch.getBranchNo().equals(p.getBranch().getBranchNo())) {
								butClass = "btn btn-success";
							} else
								butClass = "btn btn-warning";
					%>
					<td><%=id%></td>
					<td><%=p.getBook().getDescription()%></td>
					<td><%=p.getBranch().getBranchName()%></td>
					<td><%=p.getDateOut().toString().substring(0, 10)%></td>
					<td><%=p.getDateDue()%></td>

					<td><a type="button" class="<%=butClass%>"
						href="returnBook?cardNo=<%=activeBorrower.getCardNo()%>&bookId=<%=p.getBook().getBookId()%>&branchNo=<%=p.getBranch().getBranchNo()%>&dateOut=<%=p.getDateOut().toString()%>&retBranchNo=<%=activeBranch.getBranchNo()%>">Return</a></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>

	<div class="modal fade bs-example-modal-lg" id="borrowBookModal"
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