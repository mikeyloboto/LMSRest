lmsApp.factory("genreService", function($http, globalConstants){
	return{
		getAllGenresService: function(){
			var getGenreData = {};
			return $http({
				url: globalConstants.HOST+"/library/genres/all"
			}).success(function(data){
				getGenreData = data;
			}).then(function(){
				return getGenreData;
			})
		},
	
		getGenreByPKService: function(genreId){
			var getGenreByPkData = {};
			return $http({
				url: globalConstants.HOST+"/library/genres/"+genreId
			}).success(function(data){
				getGenreByPkData = data;
			}).then(function(){
				return getGenreByPkData;
			})
		},
		
		getGenreInit: function(){
			var genreInit = {};
			return $http({
				url: globalConstants.HOST+"/library/genres/init"
			}).success(function(data){
				genreInit = data;
			}).then(function(){
				return genreInit;
			})
		}
	}
})