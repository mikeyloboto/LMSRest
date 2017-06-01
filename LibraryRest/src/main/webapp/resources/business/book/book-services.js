lmsApp.factory("bookService", function($http, globalConstants){
	return{
		getAllBooksService: function(){
			var getBookData = {};
			return $http({
				url: globalConstants.HOST+"/library/books/all"
			}).success(function(data){
				getBookData = data;
			}).then(function(){
				return getBookData;
			})
		},
	
		getBookByPKService: function(bookId){
			var getBookByPkData = {};
			return $http({
				url: globalConstants.HOST+"/library/books/"+bookId
			}).success(function(data){
				getBookByPkData = data;
			}).then(function(){
				return getBookByPkData;
			})
		},
		
		getBookInit: function(){
			var bookInit = {};
			return $http({
				url: globalConstants.HOST+"/library/books/init"
			}).success(function(data){
				bookInit = data;
			}).then(function(){
				return bookInit;
			})
		},
		
		getAllAuthors: function(){
			var authors = {};
			return $http({
				url: globalConstants.HOST+"/library/authors/all"
			}).success(function(data){
				authors = data;
			}).then(function(){
				return authors;
			})
		},
			
		getAllPublishers: function(){
			var pubs = {};
			return $http({
			url: globalConstants.HOST+"/library/publishers/all"
			}).success(function(data){
				pubs = data;
			}).then(function(){
				return pubs;
			})
		},
		
		getAllGenres: function(){
			var genres = {};
			return $http({
				url: globalConstants.HOST+"/library/genres/all"
			}).success(function(data){
				genres = data;
			}).then(function(){
				return genres;
			})
		}
	}
})