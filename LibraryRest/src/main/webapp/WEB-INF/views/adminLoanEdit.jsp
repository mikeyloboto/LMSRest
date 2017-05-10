<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Book"%>
<%@page import="com.gcit.library.entity.Borrower"%>
<%@page import="com.gcit.library.entity.Branch"%>
<%@page import="com.gcit.library.entity.Loan"%>
<%@page import="com.gcit.library.service.AdminService"%>
<%
	AdminService service = new AdminService();
	Book b = new Book();
	b.setBookId(Integer.parseInt(request.getParameter("bookId")));
	Branch br = new Branch();
	br.setBranchNo(Integer.parseInt(request.getParameter("branchNo")));
	Borrower bor = new Borrower();
	bor.setCardNo(Integer.parseInt(request.getParameter("cardNo")));
	Loan tempLoan = new Loan();
	tempLoan.setBook(b);
	tempLoan.setBranch(br);
	tempLoan.setBorrower(bor);
	tempLoan.setDateOut(LocalDateTime.parse(request.getParameter("dateOut")));

	Loan loan = service.expandLoan(tempLoan);

	//System.out.println("Init edit");
	//System.out.println("New Modal Init");
%>
<div>

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Edit Loan</h4>
	</div>
	<form action="editLoan" method="post">
		<div class="modal-body">
			<p>Borrower:</p>
			<p>
				<input class="form-control" type="text"	value="<%=loan.getBorrower().getName()%>" disabled>
			</p>
			<p>Book:</p>
			<p>
				<input class="form-control" type="text"	value="<%=loan.getBook().getTitle()%>" disabled>
			</p>
			<p>Branch:</p>
			<p><input class="form-control" type="text"	value="<%=loan.getBranch().getBranchName()%>" disabled></p>
			<p>Date Out:</p>
			<p><input class="form-control" type="datetime"	value="<%=loan.getDateOut().toString()%>" disabled></p>
			<p>Date Due:</p>
			<p><input class="form-control" type="date" name="dueDate" value="<%=loan.getDateDue().toString()%>"></p>
			<p>Date In:</p>
			<p><input class="form-control" type="text"	value="---" disabled></p>
			<!-- Put shite here -->

			<input type="hidden" name="cardNo" value="<%=loan.getBorrower().getCardNo() %>">
			<input type="hidden" name="bookId" value="<%=loan.getBook().getBookId() %>">
			<input type="hidden" name="branchNo" value="<%=loan.getBranch().getBranchNo() %>">
			<input type="hidden" name="dateOut" value="<%=loan.getDateOut().toString() %>">
			
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save</button>
		</div>
	</form>
</div>