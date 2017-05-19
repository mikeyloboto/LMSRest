lmsApp.factory("bookService", function($http, bookConstants){
	return{
		getAllBooksService: function(){
			var getBookData = {};
			return $http({
				url: bookConstants.GET_ALL_BOOKS_URL
			}).success(function(data){
				getBookData = data;
			}).then(function(){
				return getBookData;
			})
		},
	
		getBookByPKService: function(bookId){
			var getBookByPkData = {};
			return $http({
				url: bookConstants.GET_BOOK_BY_PK_URL+bookId
			}).success(function(data){
				getBookByPkData = data;
			}).then(function(){
				return getBookByPkData;
			})
		},
		
		getBookInit: function(){
			var bookInit = {};
			return $http({
				url: bookConstants.INIT_BOOK_URL
			}).success(function(data){
				bookInit = data;
			}).then(function(){
				return bookInit;
			})
		},
		
		getAllAuthors: function(){
			var authors = {};
			return $http({
				url: "http://localhost:8080/library/authors/all"
			}).success(function(data){
				authors = data;
			}).then(function(){
				return authors;
			})
		},
			
		getAllPublishers: function(){
			var pubs = {};
			return $http({
			url: "http://localhost:8080/library/publishers/all"
			}).success(function(data){
				pubs = data;
			}).then(function(){
				return pubs;
			})
		},
		
		getAllGenres: function(){
			var genres = {};
			return $http({
				url: "http://localhost:8080/library/genres/all"
			}).success(function(data){
				genres = data;
			}).then(function(){
				return genres;
			})
		}
	}
})