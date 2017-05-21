lmsApp.factory("clientService", function($http, clientConstants){
	
	var branchPers = {}
	var clientPers = {}

	function setBranch(branch) {
		branchPers = branch;
	}
	
	function getBranch() {
		return branchPers;
	}
	
	function setClient(client) {
		clientPers = client;
	}
	function getClient() {
		return clientPers;
	}
	
	return	
		getAllBranchesService: function(){
			var getBranchData = {};
			return $http({
				url: clientConstants.GET_ALL_BRANCHES_URL
			}).success(function(data){
				getBranchData = data;
			}).then(function(){
				return getBranchData;
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
		
		setBranch: setBranch,
		getBranch: getBranch,
		setClient: setClient,
		getClient: getClient
	}
})