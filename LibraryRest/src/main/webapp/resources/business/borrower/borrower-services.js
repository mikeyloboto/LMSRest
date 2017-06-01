lmsApp.factory("borrowerService", function($http, globalConstants){
	return{
		getAllBorrowersService: function(){
			var getBorrowerData = {};
			return $http({
				url: globalConstants.HOST+"/library/borrowers/all"
			}).success(function(data){
				getBorrowerData = data;
			}).then(function(){
				return getBorrowerData;
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
		
		getBorrowerInit: function(){
			var borrowerInit = {};
			return $http({
				url: globalConstants.HOST+"/library/borrowers/init"
			}).success(function(data){
				borrowerInit = data;
			}).then(function(){
				return borrowerInit;
			})
		}
	}
})