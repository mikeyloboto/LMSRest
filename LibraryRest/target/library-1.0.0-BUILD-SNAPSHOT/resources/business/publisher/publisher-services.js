lmsApp.factory("publisherService", function($http, publisherConstants){
	return{
		getAllPublishersService: function(){
			var getPublisherData = {};
			return $http({
				url: publisherConstants.GET_ALL_PUBLISHERS_URL
			}).success(function(data){
				getPublisherData = data;
			}).then(function(){
				return getPublisherData;
			})
		},
	
		getPublisherByPKService: function(publisherId){
			var getPublisherByPkData = {};
			return $http({
				url: publisherConstants.GET_PUBLISHER_BY_PK_URL+publisherId
			}).success(function(data){
				getPublisherByPkData = data;
			}).then(function(){
				return getPublisherByPkData;
			})
		},
		
		getPublisherInit: function(){
			var publisherInit = {};
			return $http({
				url: publisherConstants.INIT_PUBLISHER_URL
			}).success(function(data){
				publisherInit = data;
			}).then(function(){
				return publisherInit;
			})
		}
	}
})