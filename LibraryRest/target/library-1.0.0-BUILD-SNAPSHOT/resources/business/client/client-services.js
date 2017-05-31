lmsApp.factory("clientService", function($http, clientConstants){
	
	var branchPers = {};
	var clientPers = {};
	
	return	{
		getAllBranches: function(){
			var getBranchData = {};
			return $http({
				url: clientConstants.GET_ALL_BRANCHES_URL
			}).success(function(data){
				getBranchData = data;
			}).then(function(){
				return getBranchData;
			})
		},
	
		getAvailableBooks: function(branchId){
			var bookx = {};
			return $http({
				url: clientConstants.GET_BOOKS_IN_BRANCH_URL+branchId+"/available"
			}).success(function(data){
				bookx = data;
			}).then(function(){
				return bookx;
			})
		},
		
		getBorrowerByPKService: function(borrowerId){
			var getBorrowerByPkData = {};
			return $http({
				url: clientConstants.GET_BORROWER_BY_PK_URL+borrowerId
			}).success(function(data){
				getBorrowerByPkData = data;
			}).then(function(){
				return getBorrowerByPkData;
			})
		},
		
		getBranchByPKService: function(branchId){
			var getBranchByPkData = {};
			return $http({
				url: clientConstants.GET_BRANCH_BY_PK_URL+branchId
			}).success(function(data){
				getBranchByPkData = data;
			}).then(function(){
				return getBranchByPkData;
			})
		},
		
		getLoansByCardNo: function(cardNo){
			var loans = {};
			return $http({
				url: clientConstants.GET_LOANS_BY_CARD_NO + cardNo
			}).success(function(data){
				loans = data;
			}).then(function(){
				return loans;
			})
		},
		
		getBorrowerInit: function(){
			var borrowerInit = {};
			return $http({
				url: borrowerConstants.INIT_BORROWER_URL
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
				url:clientConstants.INIT_LOAN_URL
			}).success(function(data){
				loanInit = data;
			}).then(function(){
				return loanInit;
			})
		}
	}
})