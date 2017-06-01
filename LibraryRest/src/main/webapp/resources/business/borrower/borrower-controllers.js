lmsApp.controller("borrowerController", function($scope, $http, $window, $location, borrowerService, $filter, Pagination, globalConstants){
	if($location.$$path === "/admin/borrowers"){
		borrowerService.getAllBorrowersService().then(function(backendBorrowersList){
			$scope.borrowers = backendBorrowersList;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.borrowers.length / $scope.pagination.perPage);
		});
	}
	
	
	$scope.saveBorrower = function(){
		$http.put(globalConstants.HOST+"/library/borrowers", $scope.borrower, "application/json").success(function(){
			$scope.borrowerModal = false;
			borrowerService.getAllBorrowersService().then(function(backendBorrowersList){
				$scope.borrowers = backendBorrowersList;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.borrowers.length / $scope.pagination.perPage);
			});
		});
	}
	
	$scope.deleteBorrower = function(borrowerId){
		$http.delete(globalConstants.HOST+"/library/borrowers/" + borrowerId).success(function(){
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
		var req = ($scope.searchString == "") ? globalConstants.HOST+"/library/borrowers/all" : globalConstants.HOST+"/library/borrowers/search/"+$scope.searchString ;
		$http.get(req).success(function(data){
			$scope.borrowers = data;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.borrowers.length / $scope.pagination.perPage);
		});
	}
})