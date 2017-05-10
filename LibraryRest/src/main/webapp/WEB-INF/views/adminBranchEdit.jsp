<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Author"%>
<%@page import="com.gcit.library.entity.Book"%>
<%@page import="com.gcit.library.entity.Branch"%>
<%@page import="com.gcit.library.service.AdminService"%>
<%
	AdminService service = new AdminService();
	Branch branch = service.getBranchFromID(Integer.parseInt(request.getParameter("branchId"))); 
	//System.out.println("Init edit");
	//System.out.println("New Modal Init");
%>
<div>
	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Edit <%=branch.getBranchName() %></h4>
	</div>
	<form action="editBranch" method="post">
		<div class="modal-body">
			<p>Enter new name for branch:</p>
			<input type="hidden" name="branchId" value="<%=branch.getBranchNo()%>">
			<input class="form-control" type="text" name="branchName"
				required="required" value="<%=branch.getBranchName()%>"> <br />
				<p>Enter new address for branch:</p>
				<input class="form-control" type="text" name="branchAddress" value="<%=branch.getBranchAddress()%>">
			<!-- Put shite here -->

		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="submit" class="btn btn-primary">Save</button>
		</div>
	</form>
</div>