lmsApp.factory("librarianService", function($http, librarianConstants){
	
	var branchPers = {};
	var clientPers = {};
	
	return	{
		getAllBranches: function(){
			var getBranchData = {};
			return $http({
				url: librarianConstants.GET_ALL_BRANCHES_URL
			}).success(function(data){
				getBranchData = data;
			}).then(function(){
				return getBranchData;
			})
		},
	
		getAvailableBooks: function(branchId){
			var bookx = {};
			return $http({
				url: librarianConstants.GET_BOOKS_IN_BRANCH_URL+branchId+"/available"
			}).success(function(data){
				bookx = data;
			}).then(function(){
				return bookx;
			})
		},
		
		getBookCopiesInBranch: function(book, branch){
			var books = {};
			return $http({
				url: "http://localhost:8080/library/books/branch/"+branch+"/"+book
			})
		},
		
		getBorrowerByPKService: function(borrowerId){
			var getBorrowerByPkData = {};
			return $http({
				url: librarianConstants.GET_BORROWER_BY_PK_URL+borrowerId
			}).success(function(data){
				getBorrowerByPkData = data;
			}).then(function(){
				return getBorrowerByPkData;
			})
		},
		
		getBranchByPKService: function(branchId){
			var getBranchByPkData = {};
			return $http({
				url: librarianConstants.GET_BRANCH_BY_PK_URL+branchId
			}).success(function(data){
				getBranchByPkData = data;
			}).then(function(){
				return getBranchByPkData;
			})
		},
		
		getLoansByCardNo: function(cardNo){
			var loans = {};
			return $http({
				url: librarianConstants.GET_LOANS_BY_CARD_NO + cardNo
			}).success(function(data){
				loans = data;
			}).then(function(){
				return loans;
			})
		}
	}
})