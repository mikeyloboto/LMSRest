lmsApp.controller("clientController", function($scope, $http, $window,
		$location, clientService, $filter, Pagination, $routeParams, globalConstants) {

	if ($location.$$path === "/borrower") {
		clientService.getAllBranches().then(function(backendBranchesList) {
			$scope.allBranches = backendBranchesList;
			$scope.branchSelect = $scope.allBranches[0];
		});
	} else {
		clientService.getBorrowerByPKService($routeParams.bid).then(
				function(data) {
					clientService.setClient(data);
					$scope.client = data;
					loadLoans();
				});
		clientService.getBranchByPKService($routeParams.brid).then(
				function(data) {
					clientService.setBranch(data);
					$scope.branch = data;
					clientService.getAvailableBooks($scope.branch.branchNo)
							.then(function(data) {
								$scope.books = data;
								$scope.chosenBook = {};// $scope.books[0];

							});
				});

	}

	loadBooks = function() {
		clientService.getAvailableBooks($scope.branch.branchNo).then(
				function(data) {
					var tbooks = Object.keys(data);
					var bks = {};
					for (var i = 0; i < tbooks.length; i++) {
						bks[i] = getBook(tbooks[i]);
					}
					$scope.books = bks;
				});
	}

	getBook = function(bookId) {
		$http.get(globalConstants.HOST+"/library/books/" + bookId).then(
				function(data) {
					alert(data);
					return data;
				});
	}

	loadLoans = function() {
		clientService.getLoansByCardNo($scope.client.cardNo).then(
				function(data) {
					$scope.loans = data;
					$scope.pagination = Pagination.getNew(10);
					$scope.pagination.numPages = Math.ceil($scope.loans.length
							/ $scope.pagination.perPage);
				});
	}

	$scope.authAttempt = function() {
		clientService.getBorrowerByPKService($scope.inputCard).then(
				function(data) {
					if (data != null && $scope.branchSelect != null) {
						$window.location.href = "#/borrower/" + data.cardNo
								+ "/" + $scope.branchSelect.branchNo
								+ "/controlpanel";
					}
				});
	}

	$scope.getBorrowerName = function() {
		return clientService.getClient().name;
	}

	$scope.closeModal = function() {
		$scope.borrowerModal = false;
	}

	$scope.formatDate = function(date) {
		return date.substring(0, 10);
	}

	$scope.borrow = function() {

		$http.post(globalConstants.HOST+"/library/loans/start", $scope.newLoan)
				.success(
						function() {
							loadLoans();
							clientService.getAvailableBooks(
									$scope.branch.branchNo).then(
									function(data) {
										$scope.books = data;
										$scope.chosenBook = {};// $scope.books[0];

									});
						});
		$scope.borrowModal = false;
	}

	$scope.closeLoan = function(loan) {
		$http.post(
				globalConstants.HOST+"/library/loans/close/"
						+ $scope.branch.branchNo, loan).success(
				function() {
					loadLoans();
					clientService.getAvailableBooks($scope.branch.branchNo)
							.then(function(data) {
								$scope.books = data;
								$scope.chosenBook = {};// $scope.books[0];

							});

				});
	}

	$scope.getClass = function(loan) {
		var due = new Date(loan.dateDue);
		var today = new Date();
		if (due < today) {
			return "btn btn-danger";
		} else {
			if (loan.branch.branchNo === $scope.branch.branchNo) {
				return "btn btn-success";
			} else {
				return "btn btn-warning";
			}
		}
	}

	$scope.borrowBookModal = function() {
		clientService.getLoanInit().then(function(data) {
			$scope.newLoan = data;
			$scope.newLoan.borrower = $scope.client;
			$scope.newLoan.branch = $scope.branch;
			$scope.borrowModal = true;
		});

	}

	$scope.closeModal = function() {
		$scope.borrowModal = false;
	}
})