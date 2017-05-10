<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Author"%>
<%@page import="com.gcit.library.entity.Book"%>
<%@page import="com.gcit.library.entity.Genre"%>
<%@page import="com.gcit.library.entity.Publisher"%>
<%@page import="com.gcit.library.service.AdminService"%>
<%
	AdminService service = new AdminService();
	Author author = service.getAuthorFromID(Integer.parseInt(request.getParameter("authorId"))); 
	//System.out.println("New Modal Init");
%>
<div>
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Edit <%=author.getAuthorName() %></h4>
	</div>
	<form action="editAuthor" method="post">
		<div class="modal-body">
			<p>Enter new name for author:</p>
			<input type="hidden" name="authorId" value="<%=author.getAuthorId() %>">
			<input class="form-control" type="text" name="authorName"
				required="required" value="<%=author.getAuthorName()%>"> <br />
			<!-- Put shite here -->

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save</button>
		</div>
	</form>
</div>