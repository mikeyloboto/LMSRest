<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Author"%>
<%@page import="com.gcit.library.entity.Book"%>
<%@page import="com.gcit.library.entity.Branch"%>
<%@page import="com.gcit.library.entity.Branch"%>
<%@page import="com.gcit.library.service.AdminService"%>
<%
	AdminService service = new AdminService();
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
		<h4 class="modal-title">Add New Branch</h4>
	</div>
	<form action="addBranch" method="post">
		<div class="modal-body">
			<p>Enter the name of branch:</p>
			<input class="form-control" type="text" name="branchName"
				required="required"> <br />
			<p>Enter the address of branch:</p>
			<input class="form-control" type="text" name="branchAddress">
			<!-- Put shite here -->

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Create</button>
		</div>
	</form>
</div>