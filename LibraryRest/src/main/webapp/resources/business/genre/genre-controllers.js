lmsApp.controller("genreController", function($scope, $http, $window, $location, genreService, $filter, Pagination, globalConstants){
	if($location.$$path === "/admin/genres"){
		genreService.getAllGenresService().then(function(backendGenresList){
			$scope.genres = backendGenresList;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.genres.length / $scope.pagination.perPage);
		});
	}
	
	
	$scope.saveGenre = function(){
		$http.put(globalConstants.HOST+"/library/genres", $scope.genre).success(function(){
			$scope.genreModal = false;
			genreService.getAllGenresService().then(function(backendGenresList){
				$scope.genres = backendGenresList;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.genres.length / $scope.pagination.perPage);
			});
		});
	}
	
	$scope.deleteGenre = function(genreId){
		$http.delete(globalConstants.HOST+"/library/genres/" + genreId).success(function(){
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
		var req = ($scope.searchString == "") ? globalConstants.HOST+"/library/genres/all" : globalConstants.HOST+"/library/genres/search/"+$scope.searchString ;
		$http.get(req).success(function(data){
			$scope.genres = data;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.genres.length / $scope.pagination.perPage);
		});
	}
})