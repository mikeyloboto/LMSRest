lmsApp.controller("loanController", function($scope, $http, $window, $location,
		loanService, $filter, Pagination, globalConstants) {
	if ($location.$$path === "/admin/loans") {
		loanService.getAllLoansService().then(
				function(backendLoansList) {
					$scope.loans = backendLoansList;
					$scope.pagination = Pagination.getNew(10);
					$scope.pagination.numPages = Math.ceil($scope.loans.length
							/ $scope.pagination.perPage);
				});
	}

	$scope.saveLoan = function() {
		$http.put(globalConstants.HOST+"/library/loans", $scope.loan).success(
				function() {
					$scope.loanModal = false;
					loanService.getAllLoansService().then(
							function(backendLoansList) {
								$scope.loans = backendLoansList;
								$scope.pagination = Pagination.getNew(10);
								$scope.pagination.numPages = Math
										.ceil($scope.loans.length
												/ $scope.pagination.perPage);
							});
				});
	}

	$scope.closeLoan = function(loan) {
		$http.post(globalConstants.HOST+"/library/loans/close/", loan).success(
				function() {
					loanService.getAllLoansService().then(
							function(backendLoansList) {
								$scope.loans = backendLoansList;
								$scope.pagination = Pagination.getNew(10);
								$scope.pagination.numPages = Math
										.ceil($scope.loans.length
												/ $scope.pagination.perPage);
							});
					

				}).then(function(){
					loanService.getAllLoansService().then(
							function(backendLoansList) {
								$scope.loans = backendLoansList;
								$scope.pagination = Pagination.getNew(10);
								$scope.pagination.numPages = Math
										.ceil($scope.loans.length
												/ $scope.pagination.perPage);
							});
				});
		$scope.loanModal = false;
	}

	$scope.dateToControl = function(loan) {
		$scope.createdDate = {};
		var dat = new Date(loan.dateDue);
		dat.setDate(dat.getDate() + 1);
		$scope.createdDate = dat;
		$scope.loan = loan;

	}

	$scope.showUpdateLoanModal = function(loanInput) {
		
		$scope.loan = loanInput;
		$scope.loanModal = true;
	}

	$scope.closeModal = function() {
		$scope.loanModal = false;
	}
})