<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Author"%>
<%@page import="com.gcit.library.entity.Book"%>
<%@page import="com.gcit.library.entity.Genre"%>
<%@page import="com.gcit.library.entity.Publisher"%>
<%@page import="com.gcit.library.service.AdminService"%>
<%
	AdminService service = new AdminService();
	Genre genre = service.getGenreFromID(Integer.parseInt(request.getParameter("genreId"))); 
	//System.out.println("New Modal Init");
%>
<div>
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Edit <%=genre.getGenreName() %></h4>
	</div>
	<form action="editGenre" method="post">
		<div class="modal-body">
			<p>Enter new name for genre:</p>
			<input type="hidden" name="genreId" value="<%=genre.getGenreId()%>">
			<input class="form-control" type="text" name="genreName"
				required="required" value="<%=genre.getGenreName()%>"> <br />
			<!-- Put shite here -->

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save</button>
		</div>
	</form>
</div>