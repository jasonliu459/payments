angular.module('userInfo',[]).controller('userController',function($scope,$http){
	var promise =$http({
		  method:'GET',
		  url:'../user/all.do',
		}).success(function(msg){
			$scope.users = msg;
		});
});