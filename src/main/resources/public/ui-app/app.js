angular.module('load-balancer-sample-app-ui', [])
    .controller('homeController', function ($scope, $http) {
        $scope.hello = "HelloMsg!";
    });