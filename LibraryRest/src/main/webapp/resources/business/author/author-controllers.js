lmsApp.controller("authorController", function($scope, $http, $window, $location, authorService, $filter, Pagination){
	if($location.$$path === "/admin/authors"){
		authorService.getAllAuthorsService().then(function(backendAuthorsList){
			$scope.authors = backendAuthorsList;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.authors.length / $scope.pagination.perPage);
		});
	}
	
	
	$scope.saveAuthor = function(){
		$http.put("http://localhost:8080/library/authors", $scope.author).success(function(){
			$scope.authorModal = false;
			authorService.getAllAuthorsService().then(function(backendAuthorsList){
				$scope.authors = backendAuthorsList;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.authors.length / $scope.pagination.perPage);
			});
		});
	}
	
	$scope.deleteAuthor = function(authorId){
		$http.delete("http://localhost:8080/library/authors/" + authorId, $scope.author).success(function(){
			authorService.getAllAuthorsService().then(function(backendAuthorsList){
				$scope.authors = backendAuthorsList;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.authors.length / $scope.pagination.perPage);
			});
		});
	}
	
	$scope.sort = function(){
		$scope.authors = $filter('orderBy')($scope.authors, 'authorName');
	}
	
	$scope.showEditAuthorModal = function(authorId){
		authorService.getAuthorByPKService(authorId).then(function(data){
			$scope.author = data;
			$scope.authorModal = true;
		});
	}
	
	$scope.showAddAuthorModal = function(){
		authorService.getAuthorInit().then(function(data){
			$scope.author = data;
			$scope.authorModal = true;
		});
	}
	
	$scope.closeModal = function(){
		$scope.authorModal = false;
	}
	
	$scope.searchAuthors = function(){
		var req = ($scope.searchString == "") ? "http://localhost:8080/library/authors/all" : "http://localhost:8080/library/authors/search/"+$scope.searchString ;
		$http.get(req).success(function(data){
			$scope.authors = data;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.authors.length / $scope.pagination.perPage);
		});
	}
})