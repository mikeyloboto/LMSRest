lmsApp.controller("genreController", function($scope, $http, $window, $location, genreService, $filter, Pagination){
	if($location.$$path === "/admin/genres"){
		genreService.getAllGenresService().then(function(backendGenresList){
			$scope.genres = backendGenresList;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.genres.length / $scope.pagination.perPage);
		});
	}
	
	
	$scope.saveGenre = function(){
		$http.put("http://localhost:8080/library/genres", $scope.genre).success(function(){
			$scope.genreModal = false;
			genreService.getAllGenresService().then(function(backendGenresList){
				$scope.genres = backendGenresList;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.genres.length / $scope.pagination.perPage);
			});
		});
	}
	
	$scope.deleteGenre = function(genreId){
		$http.delete("http://localhost:8080/library/genres/" + genreId).success(function(){
			genreService.getAllGenresService().then(function(backendGenresList){
				$scope.genres = backendGenresList;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.genres.length / $scope.pagination.perPage);
			});
		});
	}
	
	$scope.sort = function(){
		$scope.genres = $filter('orderBy')($scope.genres, 'genreName');
	}
	
	$scope.showEditGenreModal = function(genreId){
		genreService.getGenreByPKService(genreId).then(function(data){
			$scope.genre = data;
			$scope.genreModal = true;
		});
	}
	
	$scope.showAddGenreModal = function(){
		genreService.getGenreInit().then(function(data){
			$scope.genre = data;
			$scope.genreModal = true;
		});
	}
	
	$scope.closeModal = function(){
		$scope.genreModal = false;
	}
	
	$scope.searchGenres = function(){
		var req = ($scope.searchString == "") ? "http://localhost:8080/library/genres/all" : "http://localhost:8080/library/genres/search/"+$scope.searchString ;
		$http.get(req).success(function(data){
			$scope.genres = data;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.genres.length / $scope.pagination.perPage);
		});
	}
})