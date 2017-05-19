<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Branch"%>
<%@page import="com.gcit.library.service.BorrowerService"%>


<%
	BorrowerService service = new BorrowerService();
	List<Branch> branches = service.getAllBranches(null);
%>
${message}
<ol class="breadcrumb">
	<li><a href="index.jsp">Home</a></li>
	<li class="active">Borrower Login</li>
</ol>
<div class="jumbotron">
	<div class="container">
		<h1>LMS Borrower</h1>
		<p>Welcome to Our Library.</p>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="col-sm-4">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Sign In to The System:</h3>
				</div>
				<div class="panel-body">
					<form action="authenticateBorrower" method="post">
						<p>Enter your card number:</p>
						<p>
							<input class="form-control" type="number" name="cardNo"
								required="required">
						</p>
						<p>Select your branch</p>
						<p>
						<select class="form-control" name="branchId">
								<%
									for (Branch b : branches) {
								%>
								<option value="<%=b.getBranchNo()%>"><%=b.getBranchName()%></option>
								<%
									}
								%>
							</select>
						
						<p>
							<button type="submit" class="btn btn-success">Sign In</button>
						</p>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>