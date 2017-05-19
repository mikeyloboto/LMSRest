<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.gcit.library.entity.Author"%>
<%@page import="com.gcit.library.entity.Book"%>
<%@page import="com.gcit.library.entity.Branch"%>
<%@page import="com.gcit.library.entity.Genre"%>
<%@page import="com.gcit.library.entity.Publisher"%>
<%@page import="com.gcit.library.service.BorrowerService"%>
<%
	Integer branchNo = Integer.parseInt(request.getParameter("branchNo"));
	Branch branch = new Branch();
	branch.setBranchNo(branchNo);
	BorrowerService service = new BorrowerService();
	Map<Book, Integer> books = service.getAllBooksInBranch(branch, null);
	//Integer bookId = Integer.parseInt(request.getParameter("bookId"));
	//Book book = service.getBookFromID(bookId);
	//System.out.println("New Modal Init");
%>
<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Borrow a Book</h4>
	</div>
	<form action="borrowBook" method="post">
		<div class="modal-body">
			<input type="hidden" name="branchNo"
				value="<%=request.getParameter("branchNo")%>">
			<input type="hidden" name="cardNo"
				value="<%=request.getParameter("cardNo")%>">
					
			<p>Pick a Book:</p>

			<p>
				<select class="form-control" name="bookId" required="required">
					<%
						for (Book b : books.keySet()) {
					%>
					<option value="<%=b.getBookId()%>"><%=b.getDescription()%></option>
					<%
						}
					%>
				</select>
			</p>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Borrow</button>
		</div>
	</form>
</div>