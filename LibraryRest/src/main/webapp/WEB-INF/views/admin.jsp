<%@include file="include.html"%>

<ol class="breadcrumb">
	<li><a href="index">Home</a></li>
	<li class="active">Administrator</li>
</ol>

<div class="jumbotron">
	<div class="container">
		<h1>LMS Administrator</h1>
		<p>Welcome to Library Administration Panel.</p>
	</div>
</div>

<div class="container">
	<div class="row">
		<div class="col-sm-4">

			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Manage Books</h3>
				</div>
				<div class="panel-body">
					<p>Add / Delete / Modify Books</p>
					<p>
						<a type="button" class="btn btn-primary"
							href="adminBookManage">Book Management</a>
					</p>
				</div>
			</div>
		</div>
		<div class="col-sm-4">

			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Manage Authors</h3>
				</div>
				<div class="panel-body">
					<p>Add / Delete / Modify Authors</p>
					<p>
						<a type="button" class="btn btn-primary"
							href="adminAuthorManage">Author Management</a>
					</p>
				</div>
			</div>

		</div>
		<div class="col-sm-4">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Manage Genres</h3>
				</div>
				<div class="panel-body">
					<p>Add / Delete / Modify Genres</p>
					<p>
						<a type="button" class="btn btn-primary"
							href="adminGenreManage">Genre Management</a>
					</p>
				</div>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Manage Publishers</h3>
				</div>
				<div class="panel-body">
					<p>Add / Delete / Modify Publishers</p>
					<p>
						<a type="button" class="btn btn-primary"
							href="adminPublisherManage">Publisher Management</a>
					</p>
				</div>
			</div>
		</div>
		<div class="col-sm-4">

			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Manage Branches</h3>
				</div>
				<div class="panel-body">
					<p>Add / Delete / Modify Branches</p>
					<p>
						<a type="button" class="btn btn-primary"
							href="adminBranchManage">Branch Management</a>
					</p>
				</div>
			</div>
		</div>
			<div class="col-sm-4">

			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Manage Borrowers</h3>
				</div>
				<div class="panel-body">
					<p>Add / Delete / Modify Borrowers</p>
					<p>
						<a type="button" class="btn btn-primary"
							href="adminBorrowerManage">Borrower Management</a>
					</p>
				</div>
			</div>
		</div>
		<div class="col-sm-4">


			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Override Loans</h3>
				</div>
				<div class="panel-body">
					<p>Override Due Dates on Book Loans</p>
					<p>
						<a type="button" class="btn btn-primary"
							href="adminLoanManage">Loan Override</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>