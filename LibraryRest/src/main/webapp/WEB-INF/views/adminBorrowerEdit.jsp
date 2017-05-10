<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Author"%>
<%@page import="com.gcit.library.entity.Book"%>
<%@page import="com.gcit.library.entity.Borrower"%>
<%@page import="com.gcit.library.service.AdminService"%>
<%
	AdminService service = new AdminService();
	Borrower borrower = service.getBorrowerFromID(Integer.parseInt(request.getParameter("borrowerId"))); 
	//System.out.println("Init edit");
	//System.out.println("New Modal Init");
%>
<div>
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Edit <%=borrower.getName() %></h4>
	</div>
	<form action="editBorrower" method="post">
		<div class="modal-body">
			<p>Enter new name for borrower:</p>
			<input type="hidden" name="borrowerId" value="<%=borrower.getCardNo()%>">
			<input class="form-control" type="text" name="borrowerName"
				required="required" value="<%=borrower.getName()%>"> <br />
				<p>Enter new address for borrower:</p>
				<input class="form-control" type="text" name="borrowerAddress" value="<%=borrower.getAddress()%>"><br />
				<p>Enter new phone for borrower:</p>
				<input class="form-control" type="text" name="borrowerPhone" value="<%=borrower.getPhone()%>">
			<!-- Put shite here -->

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save</button>
		</div>
	</form>
</div>