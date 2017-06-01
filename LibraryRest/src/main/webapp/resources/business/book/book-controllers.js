lmsApp.controller("bookController", function($scope, $http, $window, $location, bookService, $filter, Pagination, globalConstants){
	if($location.$$path === "/admin/books"){
		bookService.getAllBooksService().then(function(backendBooksList){
			$scope.books = backendBooksList;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.books.length / $scope.pagination.perPage);
		});
	}
	
	
	$scope.saveBook = function(){
		$http.put(globalConstants.HOST+"/library/books", $scope.book).success(function(){
			$scope.bookModal = false;
			bookService.getAllBooksService().then(function(backendBooksList){
				$scope.books = backendBooksList;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.books.length / $scope.pagination.perPage);
			});
		});
	}
	
	$scope.deleteBook = function(bookId){
		$http.delete(globalConstants.HOST+"/library/books/" + bookId).success(function(){
			bookService.getAllBooksService().then(function(backendBooksList){
				$scope.books = backendBooksList;
				$scope.pagination = Pagination.getNew(10);
				$scope.pagination.numPages = Math.ceil($scope.books.length / $scope.pagination.perPage);
			});
		});
	}
	
	$scope.sort = function(){
		$scope.books = $filter('orderBy')($scope.books, 'bookName');
	}
	
	$scope.showEditBookModal = function(bookId){
		initModals();
		bookService.getBookByPKService(bookId).then(function(data){
			$scope.book = data;
			$scope.bookModal = true;
		});
	}
	
	$scope.showAddBookModal = function(){
		initModals();
		bookService.getBookInit().then(function(data){
			$scope.book = data;
			$scope.bookModal = true;
		});
	}
	
	$scope.closeModal = function(){
		$scope.bookModal = false;
	}
	
	initModals = function(){
		bookService.getAllAuthors().then(function(dataA){
			$scope.allAuthors = dataA;
		});
		bookService.getAllPublishers().then(function(dataP){
			$scope.allPublishers = dataP;
		});
		bookService.getAllGenres().then(function(dataG){
			$scope.allGenres = dataG;
		});
	}
	
	$scope.searchBooks = function(){
		var req = ($scope.searchString == "") ? globalConstants.HOST+"/library/books/all" : globalConstants.HOST+"/library/books/search/"+$scope.searchString ;
		$http.get(req).success(function(data){
			$scope.books = data;
			$scope.pagination = Pagination.getNew(10);
			$scope.pagination.numPages = Math.ceil($scope.books.length / $scope.pagination.perPage);
		});
	}
})