lmsApp.factory("borrowerService", function($http, borrowerConstants){
	return{
		getAllBorrowersService: function(){
			var getBorrowerData = {};
			return $http({
				url: borrowerConstants.GET_ALL_BORROWERS_URL
			}).success(function(data){
				getBorrowerData = data;
			}).then(function(){
				return getBorrowerData;
			})
		},
	
		getBorrowerByPKService: function(borrowerId){
			var getBorrowerByPkData = {};
			return $http({
				url: borrowerConstants.GET_BORROWER_BY_PK_URL+borrowerId
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
		}
	}
})