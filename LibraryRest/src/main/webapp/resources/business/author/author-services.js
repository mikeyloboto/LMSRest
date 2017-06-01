lmsApp.factory("authorService", function($http, globalConstants){
	return{
		getAllAuthorsService: function(){
			var getAuthorData = {};
			return $http({
				url: globalConstants.HOST+"/library/authors/all"
			}).success(function(data){
				getAuthorData = data;
			}).then(function(){
				return getAuthorData;
			})
		},
	
		getAuthorByPKService: function(authorId){
			var getAuthorByPkData = {};
			return $http({
				url: globalConstants.HOST+"/library/authors/"+authorId
			}).success(function(data){
				getAuthorByPkData = data;
			}).then(function(){
				return getAuthorByPkData;
			})
		},
		
		getAuthorInit: function(){
			var authorInit = {};
			return $http({
				url: globalConstants.HOST+"/library/authors"
			}).success(function(data){
				authorInit = data;
			}).then(function(){
				return authorInit;
			})
		}
	}
})