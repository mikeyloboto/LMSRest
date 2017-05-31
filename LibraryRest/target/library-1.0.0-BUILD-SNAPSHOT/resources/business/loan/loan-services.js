lmsApp.factory("loanService", function($http, loanConstants){
	return{
		getAllLoansService: function(){
			var getLoanData = {};
			return $http({
				url: loanConstants.GET_ALL_LOANS_URL
			}).success(function(data){
				getLoanData = data;
			}).then(function(){
				return getLoanData;
			})
		}
	}
})