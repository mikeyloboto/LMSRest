lmsApp.controller("loanController", function($scope, $http, $window, $location, loanService, $filter, Pagination){
	if($location.$$path === "/admin/loans"){
		loanService.getAllLoansService().then(function(backendLoansList){
			$scope.loans = backendLoansList;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.loans.length / $scope.pagination.perPage);
		});
	}
	
	
	$scope.saveLoan = function(){
		$scope.loan.dateDue = $scope.createdDate.toJSON();
		$http.put("http://localhost:8080/library/loans", $scope.loan).success(function(){
			$scope.loanModal = false;
			loanService.getAllLoansService().then(function(backendLoansList){
				$scope.loans = backendLoansList;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.loans.length / $scope.pagination.perPage);
			});
		});
	}
	
	$scope.close = function(loan){
		$http.delete("http://localhost:8080/library/loans", loan).success(function(){
			loanService.getAllLoansService().then(function(backendLoansList){
				$scope.loans = backendLoansList;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.loans.length / $scope.pagination.perPage);
			});
		});
	}
	
	$scope.dateToControl = function(){
		$scope.createdDate = new Date($scope.loan.dateDue);
		
	}
	
	$scope.formatMonth = function(input){
		var fl = input.slice(0, 1).toUpperCase();
		var ol = input.substring(1, input.length).toLowerCase();
		return fl + ol;
	}
	
	$scope.sort = function(){
		$scope.loans = $filter('orderBy')($scope.loans, 'loanName');
	}
	
	$scope.showUpdateLoanModal = function(loanInput){
		$scope.loan = loanInput;
		$scope.dateToControl();
		$scope.loanModal = true;
	}
	
	$scope.closeModal = function(){
		$scope.loanModal = false;
	}
})