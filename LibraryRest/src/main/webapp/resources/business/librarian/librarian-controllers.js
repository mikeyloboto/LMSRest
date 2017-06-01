lmsApp.controller("librarianLoginController", function($scope, $http, $window,
		$location, librarianService, $filter, Pagination, $routeParams, globalConstants) {

	if ($location.$$path === "/librarian") {
		librarianService.getAllBranches().then(function(backendBranchesList) {
			$scope.allBranches = backendBranchesList;
			$scope.branchSelect = $scope.allBranches[0];
		});
	}

	$scope.authAttempt = function() {
		$window.location.href = "#/librarian/" + $scope.branchSelect.branchNo
				+ "/controlpanel";

	}
})

lmsApp.controller("librarianController", function($scope, $http, $window,
		$location, librarianService, $filter, Pagination, $routeParams) {

	if ($location.$$path != "/librarian") {
		librarianService.getBranchByPKService($routeParams.brid).then(
				function(data) {
					$scope.branch = data;
				})
	}

	$scope.saveBranch = function() {
		$http.put(globalConstants.HOST+"/library/branches", $scope.branch);
	}

	$scope.stockManage = function() {
		$window.location.href = "#/librarian/" + $routeParams.brid + "/stock";

	}
})

lmsApp
		.controller(
				"librarianStockController",
				function($scope, $http, $window, $location, librarianService,
						bookService, $filter, Pagination, $routeParams) {

					if ($location.$$path != "/librarian") {
						librarianService
								.getBranchByPKService($routeParams.brid)
								.then(function(data) {
									$scope.branch = data;

								})
								.then(
										function() {
											librarianService
													.getAvailableBooks(
															$scope.branch.branchNo)
													.then(
															function(data) {
																$scope.books = data;
																$scope.pagination = Pagination
																		.getNew(10);
																$scope.pagination.numPages = Math
																		.ceil($scope.books.length
																				/ $scope.pagination.perPage);
																$scope.copies = {};
																$scope.copies.copies = 0;
															})
													.then(
															function() {
																bookService
																		.getAllBooksService()
																		.then(
																				function(
																						backendBooksList) {
																					$scope.allBooks = backendBooksList;
																					$scope.copies.book = $scope.allBooks[0];
																				});

															});
										});

					}

					getCopies = function(book) {
						librarianService.getBookCopiesInBranch(book.bookId,
								$scope.branch.branchNo).then(function(data) {
							return data;
						})
					}

					$scope.updateCopies = function() {
						librarianService
								.getAvailableBooks($scope.branch.branchNo)
								.then(
										function(data) {
											$scope.books = data;
											$scope.pagination = Pagination
													.getNew(10);
											$scope.pagination.numPages = Math
													.ceil($scope.books.length
															/ $scope.pagination.perPage);
										});
					}

					$scope.showManageModal = function(book) {
						librarianService.getBookCopiesInBranch(book.bookId,
								$scope.branch.branchNo).then(function(data) {
							$scope.copies.copies = data.data;
							$scope.editBook = book;
							$scope.stockModal = true;
						})
					}

					$scope.addBookModalShow = function() {
						$scope.copies.copies = 0;
						$scope.addStockModal = true;
					}

					$scope.closeModal = function() {
						$scope.stockModal = false;
						$scope.addStockModal = false;
					}

					$scope.updateCopies = function(cop) {
						$http
								.post(
										globalConstants.HOST+"/library/books/branch/"
												+ $scope.branch.branchNo + "/"
												+ $scope.editBook.bookId, cop)
								.success(
										function() {
											librarianService
													.getAvailableBooks(
															$scope.branch.branchNo)
													.then(
															function(data) {
																$scope.books = data;
																$scope.pagination = Pagination
																		.getNew(10);
																$scope.pagination.numPages = Math
																		.ceil($scope.books.length
																				/ $scope.pagination.perPage);

															});
										});
						$scope.stockModal = false;
					}

					$scope.addBooks = function() {
						$http
								.post(
										globalConstants.HOST+"/library/books/branch/"
												+ $scope.branch.branchNo + "/"
												+ $scope.copies.book.bookId
												+ "/increment",
										$scope.copies.copies)
								.success(
										function() {
											librarianService
													.getAvailableBooks(
															$scope.branch.branchNo)
													.then(
															function(data) {
																$scope.books = data;
																$scope.pagination = Pagination
																		.getNew(10);
																$scope.pagination.numPages = Math
																		.ceil($scope.books.length
																				/ $scope.pagination.perPage);

															});
										});
						$scope.addStockModal = false;
					}
				})