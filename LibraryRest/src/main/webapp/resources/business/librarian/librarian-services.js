lmsApp.factory("librarianService", function($http, globalConstants){
	
	var branchPers = {};
	var clientPers = {};
	
	return	{
		getAllBranches: function(){
			var getBranchData = {};
			return $http({
				url: globalConstants.HOST+"/library/branches/all"
			}).success(function(data){
				getBranchData = data;
			}).then(function(){
				return getBranchData;
			})
		},
	
		getAvailableBooks: function(branchId){
			var bookx = {};
			return $http({
				url: globalConstants.HOST+"/library/books/branch/"+branchId+"/available"
			}).success(function(data){
				bookx = data;
			}).then(function(){
				return bookx;
			})
		},
		
		getBookCopiesInBranch: function(book, branch){
			var books = {};
			return $http({
				url: globalConstants.HOST+"/library/books/branch/"+branch+"/"+book
			})
		},
		
		getBorrowerByPKService: function(borrowerId){
			var getBorrowerByPkData = {};
			return $http({
				url: globalConstants.HOST+"/library/borrowers/"+borrowerId
			}).success(function(data){
				getBorrowerByPkData = data;
			}).then(function(){
				return getBorrowerByPkData;
			})
		},
		
		getBranchByPKService: function(branchId){
			var getBranchByPkData = {};
			return $http({
				url: globalConstants.HOST+"/library/branches/"+branchId
			}).success(function(data){
				getBranchByPkData = data;
			}).then(function(){
				return getBranchByPkData;
			})
		},
		
		getLoansByCardNo: function(cardNo){
			var loans = {};
			return $http({
				url: globalConstants.HOST+"/library/loans/client/" + cardNo
			}).success(function(data){
				loans = data;
			}).then(function(){
				return loans;
			})
		}
	}
})