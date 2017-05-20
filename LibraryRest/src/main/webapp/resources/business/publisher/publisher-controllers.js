lmsApp.controller("publisherController", function($scope, $http, $window, $location, publisherService, $filter, Pagination){
	if($location.$$path === "/admin/publishers"){
		publisherService.getAllPublishersService().then(function(backendPublishersList){
			$scope.publishers = backendPublishersList;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.publishers.length / $scope.pagination.perPage);
		});
	}
	
	
	$scope.savePublisher = function(){
		$http.put("http://localhost:8080/library/publishers", $scope.publisher).success(function(){
			$scope.publisherModal = false;
			publisherService.getAllPublishersService().then(function(backendPublishersList){
				$scope.publishers = backendPublishersList;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.publishers.length / $scope.pagination.perPage);
			});
		});
	}
	
	$scope.deletePublisher = function(publisherId){
		$http.delete("http://localhost:8080/library/publishers/" + publisherId).success(function(){
			publisherService.getAllPublishersService().then(function(backendPublishersList){
				$scope.publishers = backendPublishersList;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.publishers.length / $scope.pagination.perPage);
			});
		});
	}
	
	$scope.sort = function(){
		$scope.publishers = $filter('orderBy')($scope.publishers, 'publisherName');
	}
	
	$scope.showEditPublisherModal = function(publisherId){
		publisherService.getPublisherByPKService(publisherId).then(function(data){
			$scope.publisher = data;
			$scope.publisherModal = true;
		});
	}
	
	$scope.showAddPublisherModal = function(){
		publisherService.getPublisherInit().then(function(data){
			$scope.publisher = data;
			$scope.publisherModal = true;
		});
	}
	
	$scope.closeModal = function(){
		$scope.publisherModal = false;
	}
	
	$scope.searchPublishers = function(){
		var req = ($scope.searchString == "") ? "http://localhost:8080/library/publishers/all" : "http://localhost:8080/library/publishers/search/"+$scope.searchString ;
		$http.get(req).success(function(data){
			$scope.publishers = data;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.publishers.length / $scope.pagination.perPage);
		});
	}
})