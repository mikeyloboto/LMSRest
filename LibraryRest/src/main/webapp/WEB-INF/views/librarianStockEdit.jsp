<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Book"%>
<%@page import="com.gcit.library.entity.Branch"%>
<%@page import="com.gcit.library.service.LibrarianService"%>
<%
	LibrarianService service = new LibrarianService();
	Book book = service.getBookFromID(Integer.parseInt(request.getParameter("bookId")));
	Branch branch = new Branch();
	branch.setBranchNo(Integer.parseInt(request.getParameter("branchId")));
	Integer stock = service.getBookCountInBranch(book, branch);
	//Borrower borrower = service.getBorrowerFromID(Integer.parseInt(request.getParameter("borrowerId"))); 
	//System.out.println("Init edit");
	//System.out.println("New Modal Init");
%>
<div>
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Edit Stock for<%=book.getTitle() %></h4>
	</div>
	<form action="updateStock" method="post">
		<div class="modal-body">
			<p>Enter number of available copies:</p>
			<input type="hidden" name="branchId" value="<%=branch.getBranchNo()%>">
			<input type="hidden" name="bookId" value="<%=book.getBookId()%>">
			
			<p><input class="form-control" type="number" name="noOfCopies"
				required="required" value="<%=stock%>"> </p>

			<!-- Put shite here -->

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Update</button>
		</div>
	</form>
</div>