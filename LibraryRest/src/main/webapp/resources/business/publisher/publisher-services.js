lmsApp.factory("publisherService", function($http, globalConstants){
	return{
		getAllPublishersService: function(){
			var getPublisherData = {};
			return $http({
				url: globalConstants.HOST+"/library/publishers/all"
			}).success(function(data){
				getPublisherData = data;
			}).then(function(){
				return getPublisherData;
			})
		},
	
		getPublisherByPKService: function(publisherId){
			var getPublisherByPkData = {};
			return $http({
				url: globalConstants.HOST+"/library/publishers/"+publisherId
			}).success(function(data){
				getPublisherByPkData = data;
			}).then(function(){
				return getPublisherByPkData;
			})
		},
		
		getPublisherInit: function(){
			var publisherInit = {};
			return $http({
				url: globalConstants.HOST+"/library/publishers/init"
			}).success(function(data){
				publisherInit = data;
			}).then(function(){
				return publisherInit;
			})
		}
	}
})