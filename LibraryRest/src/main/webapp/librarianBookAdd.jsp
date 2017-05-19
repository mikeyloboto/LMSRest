<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Author"%>
<%@page import="com.gcit.library.entity.Book"%>
<%@page import="com.gcit.library.entity.Genre"%>
<%@page import="com.gcit.library.entity.Publisher"%>
<%@page import="com.gcit.library.service.LibrarianService"%>
<%
	LibrarianService service = new LibrarianService();
	List<Book> books = service.getAllBooks(null);
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
		<h4 class="modal-title">Add New Book to Your Library</h4>
	</div>
	<form action="addBookStock" method="post">
		<div class="modal-body">
			<input type="hidden" name="branchId"
				value="<%=request.getParameter("branchId")%>">
			<p>Pick a Book:</p>

			<p>
				<select class="form-control" name="bookId">
					<%
						for (Book b : books) {
					%>
					<option value="<%=b.getBookId()%>"><%=b.getDescription()%></option>
					<%
						}
					%>
				</select>
			</p>
			<p>Enter number of available copies:</p>

			<p>
				<input class="form-control" type="number" name="noOfCopies"
					required="required" value="1">
			</p>


		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Add</button>
		</div>
	</form>
</div>