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

    $scope.refresh = function() {
        $http.get('/getAll').then(function(response) {
            $scope.students = response.data;
        });
    };

    $scope.refresh();

    $scope.create = function() {
        $state.go('create');
    };

    $scope.delete = function(student) {
        $http.delete('/delete', {
            params: {
                email: student.email,
                name: student.name,
                surname: student.surname
            }
        }).then(function() {
            $scope.refresh();
        })
    }
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
        })
            .then(function() {
                $state.go('home');
            });
    };
});
