var nsApp = angular.module('nsApp',['ngRoute']);  

nsApp.config(['$routeProvider',function ($routeProvider) {  
    $routeProvider
        .when('/welcome', {templateUrl: 'welcome.html',controller: 'DefaultController'})
        .when('/music', {templateUrl: 'modules/music/list.html',controller: 'MusicController'})
        .when('/music/add', {templateUrl: 'modules/music/add.html',controller: 'MusicAddController'})
        .when('/music/update', {templateUrl: 'modules/music/update.html',controller: 'MusicUpdateController'})
		.otherwise({redirectTo: '/welcome'})
		;
}]);
 
nsApp.controller('DefaultController',function($scope,$routeParams) {  
	$scope.id = $routeParams.id;
}); 

$(function(){
	
});
  