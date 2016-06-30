angular.module('userInfo',[]).controller('userController',function($scope){
	$.ajax({
		   type: "GET",
		   url: "../user/all.do",
		   dataType:'json',
		   success: function(msg){
	     		$scope.users = msg;
	     		console.log($scope.users);
	     		$scope.$apply();
	   		}
		});
});