lmsApp.factory("loanService", function($http, globalConstants){
	return{
		getAllLoansService: function(){
			var getLoanData = {};
			return $http({
				url: globalConstants.HOST+"/library/loans/all"
			}).success(function(data){
				getLoanData = data;
			}).then(function(){
				return getLoanData;
			})
		}
	}
})