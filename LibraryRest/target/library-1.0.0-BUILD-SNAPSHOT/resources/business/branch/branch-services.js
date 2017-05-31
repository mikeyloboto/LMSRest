lmsApp.factory("branchService", function($http, branchConstants){
	return{
		getAllBranchesService: function(){
			var getBranchData = {};
			return $http({
				url: branchConstants.GET_ALL_BRANCHS_URL
			}).success(function(data){
				getBranchData = data;
			}).then(function(){
				return getBranchData;
			})
		},
	
		getBranchByPKService: function(branchId){
			var getBranchByPkData = {};
			return $http({
				url: branchConstants.GET_BRANCH_BY_PK_URL+branchId
			}).success(function(data){
				getBranchByPkData = data;
			}).then(function(){
				return getBranchByPkData;
			})
		},
		
		getBranchInit: function(){
			var branchInit = {};
			return $http({
				url: branchConstants.INIT_BRANCH_URL
			}).success(function(data){
				branchInit = data;
			}).then(function(){
				return branchInit;
			})
		}
	}
})