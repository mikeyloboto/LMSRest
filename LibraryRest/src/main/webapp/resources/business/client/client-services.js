lmsApp.factory("clientService", function($http, globalConstants){
	
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
		},
		
		getBorrowerInit: function(){
			var borrowerInit = {};
			return $http({
				url: globalConstants.HOST+"/library/borrowers/init"
			}).success(function(data){
				borrowerInit = data;
			}).then(function(){
				return borrowerInit;
			})
		},
		
		setBranch: function(branch) {
			branchPers = branch;
		},
		
		getBranch: function() {
			return branchPers;
		},
		
		setClient: function(client) {
			clientPers = client;
		},
		getClient: function() {
			return clientPers;
		}, 
		
		getLoanInit: function(){
			var loanInit = {};
			return $http({
				url:globalConstants.HOST+"/library/loans/init"
			}).success(function(data){
				loanInit = data;
			}).then(function(){
				return loanInit;
			})
		}
	}
})