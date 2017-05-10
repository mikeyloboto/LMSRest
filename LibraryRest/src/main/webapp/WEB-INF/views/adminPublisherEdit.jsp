<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Author"%>
<%@page import="com.gcit.library.entity.Book"%>
<%@page import="com.gcit.library.entity.Publisher"%>
<%@page import="com.gcit.library.service.AdminService"%>
<%
	AdminService service = new AdminService();
	Publisher publisher = service.getPublisherFromID(Integer.parseInt(request.getParameter("publisherId"))); 
	//System.out.println("Init edit");
	//System.out.println("New Modal Init");
%>
<div>
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Edit <%=publisher.getPublisherName() %></h4>
	</div>
	<form action="editPublisher" method="post">
		<div class="modal-body">
			<p>Enter new name for publisher:</p>
			<input type="hidden" name="publisherId" value="<%=publisher.getPublisherId()%>">
			<input class="form-control" type="text" name="publisherName"
				required="required" value="<%=publisher.getPublisherName()%>"> <br />
				<p>Enter new address for publisher:</p>
				<input class="form-control" type="text" name="publisherAddress" value="<%=publisher.getPublisherAddress()%>"><br />
				<p>Enter new phone for publisher:</p>
				<input class="form-control" type="text" name="publisherPhone" value="<%=publisher.getPublisherPhone()%>">
			<!-- Put shite here -->

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save</button>
		</div>
	</form>
</div>