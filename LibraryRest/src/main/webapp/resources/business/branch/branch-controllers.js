lmsApp.controller("branchController", function($scope, $http, $window, $location, branchService, $filter, Pagination, globalConstants){
	if($location.$$path === "/admin/branches"){
		branchService.getAllBranchesService().then(function(backendBranchesList){
			$scope.branches = backendBranchesList;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.branches.length / $scope.pagination.perPage);
		});
	}
	
	
	$scope.saveBranch = function(){
		$http.put(globalConstants.HOST+"/library/branches", $scope.branch).success(function(){
			$scope.branchModal = false;
			branchService.getAllBranchesService().then(function(backendBranchesList){
				$scope.branches = backendBranchesList;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.branches.length / $scope.pagination.perPage);
			});
		});
	}
	
	$scope.deleteBranch = function(branchId){
		$http.delete(globalConstants.HOST+"/library/branches/" + branchId).success(function(){
			branchService.getAllBranchesService().then(function(backendBranchesList){
				$scope.branches = backendBranchesList;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.branches.length / $scope.pagination.perPage);
			});
		});
	}
	
	$scope.sort = function(){
		$scope.branches = $filter('orderBy')($scope.branches, 'branchName');
	}
	
	$scope.showEditBranchModal = function(branchId){
		branchService.getBranchByPKService(branchId).then(function(data){
			$scope.branch = data;
			$scope.branchModal = true;
		});
	}
	
	$scope.showAddBranchModal = function(){
		branchService.getBranchInit().then(function(data){
			$scope.branch = data;
			$scope.branchModal = true;
		});
	}
	
	$scope.closeModal = function(){
		$scope.branchModal = false;
	}
	
	$scope.searchBranches = function(){
		var req = ($scope.searchString == "") ? globalConstants.HOST+"/library/branches/all" : globalConstants.HOST+"/library/branches/search/"+$scope.searchString ;
		$http.get(req).success(function(data){
			$scope.branches = data;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.branches.length / $scope.pagination.perPage);
		});
	}
})