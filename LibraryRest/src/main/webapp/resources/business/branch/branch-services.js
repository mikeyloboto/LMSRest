lmsApp.factory("branchService", function($http, globalConstants){
	return{
		getAllBranchesService: function(){
			var getBranchData = {};
			return $http({
				url: globalConstants.HOST+"/library/branches/all"
			}).success(function(data){
				getBranchData = data;
			}).then(function(){
				return getBranchData;
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
		
		getBranchInit: function(){
			var branchInit = {};
			return $http({
				url: globalConstants.HOST+"/library/branches/init"
			}).success(function(data){
				branchInit = data;
			}).then(function(){
				return branchInit;
			})
		}
	}
})