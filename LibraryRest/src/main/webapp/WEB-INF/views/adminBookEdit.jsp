<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Author"%>
<%@page import="com.gcit.library.entity.Book"%>
<%@page import="com.gcit.library.entity.Genre"%>
<%@page import="com.gcit.library.entity.Publisher"%>
<%@page import="com.gcit.library.service.AdminService"%>
<%
	Book book = (Book) request.getAttribute("book");
	//System.out.println("New Modal Init");
%>
<div>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">
			Edit
			<%=book.getTitle()%></h4>
	</div>
	<form action="editBook" method="post">
		<div class="modal-body">

			<p>Enter the updated title of Book:</p>
			<input class="form-control" type="text" name="bookName"
				required="required" value="<%=book.getTitle()%>"> <br />
			<input type="hidden" name="bookId" value="<%=book.getBookId()%>">
			<p>Select Author(s):</p>
			<select multiple class="form-control" name="authors">
				${authors}
			</select> <br />
			<p>Select Genre(s):</p>
			<select multiple class="form-control" name="genres">
				${genres}
			</select> <br />
			<p>Select Publisher:</p>
			<select class="form-control" name="publisher"> 
				${publishers}
			</select> <br /> <br>
			<div class="alert alert-info" role="alert">
				<strong>Hint,</strong> you can use "Ctrl" button to select multiple
				authors and genres.
			</div>


		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save</button>
		</div>
	</form>
</div>