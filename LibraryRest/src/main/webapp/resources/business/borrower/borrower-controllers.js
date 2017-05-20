lmsApp.controller("borrowerController", function($scope, $http, $window, $location, borrowerService, $filter, Pagination){
	if($location.$$path === "/admin/borrowers"){
		borrowerService.getAllBorrowersService().then(function(backendBorrowersList){
			$scope.borrowers = backendBorrowersList;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.borrowers.length / $scope.pagination.perPage);
		});
	}
	
	
	$scope.saveBorrower = function(){
		$http.put("http://localhost:8080/library/borrowers", $scope.borrower).success(function(){
			$scope.borrowerModal = false;
			borrowerService.getAllBorrowersService().then(function(backendBorrowersList){
				$scope.borrowers = backendBorrowersList;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.borrowers.length / $scope.pagination.perPage);
			});
		});
	}
	
	$scope.deleteBorrower = function(borrowerId){
		$http.delete("http://localhost:8080/library/borrowers/" + borrowerId).success(function(){
			borrowerService.getAllBorrowersService().then(function(backendBorrowersList){
				$scope.borrowers = backendBorrowersList;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.borrowers.length / $scope.pagination.perPage);
			});
		});
	}
	
	$scope.sort = function(){
		$scope.borrowers = $filter('orderBy')($scope.borrowers, 'borrowerName');
	}
	
	$scope.showEditBorrowerModal = function(borrowerId){
		borrowerService.getBorrowerByPKService(borrowerId).then(function(data){
			$scope.borrower = data;
			$scope.borrowerModal = true;
		});
	}
	
	$scope.showAddBorrowerModal = function(){
		borrowerService.getBorrowerInit().then(function(data){
			$scope.borrower = data;
			$scope.borrowerModal = true;
		});
	}
	
	$scope.closeModal = function(){
		$scope.borrowerModal = false;
	}
	
	$scope.searchBorrowers = function(){
		var req = ($scope.searchString == "") ? "http://localhost:8080/library/borrowers/all" : "http://localhost:8080/library/borrowers/search/"+$scope.searchString ;
		$http.get(req).success(function(data){
			$scope.borrowers = data;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.borrowers.length / $scope.pagination.perPage);
		});
	}
})