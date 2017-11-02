'use strict';

var app = angular.module('load-balancer-sample-app-ui', ['ngRoute', 'ui.router']);

app.config(['$stateProvider', '$urlRouterProvider',function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('home');

    $stateProvider
        .state('home', {
            url: '',
            controller: 'homeController',
            templateUrl: 'views/home.html'
        })
        .state('create', {
            url: '',
            controller: 'createController',
            templateUrl: 'views/create.html'
        });
}]);

app.controller('homeController', function ($scope, $http, $state) {

    $http.get('/getAll').then(function(response) {
        $scope.students = response.data;
        console.log('Data ready');
    });

    $scope.create = function() {
        $state.go('create');
    };
});

app.controller('createController', function ($scope, $http, $state) {
    $scope.email = '';
    $scope.name = '';
    $scope.surname = '';

    $scope.submit = function () {
        $http.post('/save', {
            email: $scope.email,
            name: $scope.name,
            surname: $scope.surname
        });

        $state.go('home');
    };
});
