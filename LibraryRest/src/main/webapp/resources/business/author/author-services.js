lmsApp.factory("authorService", function($http, authorConstants){
	return{
		getAllAuthorsService: function(){
			var getAuthorData = {};
			return $http({
				url: authorConstants.GET_ALL_AUTHORS_URL
			}).success(function(data){
				getAuthorData = data;
			}).then(function(){
				return getAuthorData;
			})
		},
	
		getAuthorByPKService: function(authorId){
			var getAuthorByPkData = {};
			return $http({
				url: authorConstants.GET_AUTHOR_BY_PK_URL+authorId
			}).success(function(data){
				getAuthorByPkData = data;
			}).then(function(){
				return getAuthorByPkData;
			})
		},
		
		getAuthorInit: function(){
			var authorInit = {};
			return $http({
				url: authorConstants.INIT_AUTHOR_URL
			}).success(function(data){
				authorInit = data;
			}).then(function(){
				return authorInit;
			})
		}
	}
})