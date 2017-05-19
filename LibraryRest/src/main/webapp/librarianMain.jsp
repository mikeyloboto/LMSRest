<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.library.entity.Branch"%>
<%@page import="com.gcit.library.service.LibrarianService"%>


<%
	LibrarianService service = new LibrarianService();
	Branch activeBranch = service.getBranchFromID(Integer.parseInt(request.getParameter("branchId")));
%>

${message}
<ol class="breadcrumb">
	<li><a href="index.jsp">Home</a></li>
	<li><a href="librarian.jsp">Librarian Login</a></li>
	<li class="active"><%=activeBranch.getBranchName()%> Management</li>
</ol>
<div class="jumbotron">
	<div class="container">
		<h1>
			LMS
			<%=activeBranch.getBranchName()%>
			Librarian
		</h1>
		<p>Welcome to Library Management Panel.</p>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="col-sm-4">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Update Your Branch Details:</h3>
				</div>
				<div class="panel-body">
					<form action="editBranchLibrarian" method="post">
						<input type="hidden" name="branchId"
							value="<%=activeBranch.getBranchNo()%>">
						<p>Branch Name:</p>
						<p>
							<input class="form-control" type="text" name="branchName"
								value="<%=activeBranch.getBranchName()%>" required="required">
						</p>
						<p>Branch Address:</p>
						<p>
							<input class="form-control" type="text" name="branchAddress"
								value="<%=activeBranch.getBranchAddress()%>"
								required="required">
						</p>
						<p>
							<button type="submit" class="btn btn-warning">Update</button>
						</p>

					</form>
				</div>
			</div>
		</div>
		
		<div class="col-sm-4">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Manage Books in Your Library:</h3>
				</div>
				<div class="panel-body">
					<form action="librarianStockManage.jsp" method="post">
						<input type="hidden" name="branchId"
							value="<%=activeBranch.getBranchNo()%>">
						<p>
							<button type="submit" class="btn btn-primary">Go to Stock Management</button>
						</p>

					</form>
				</div>
			</div>
		</div>
		
	</div>
</div>