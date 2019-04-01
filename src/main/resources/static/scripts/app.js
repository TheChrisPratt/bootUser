var app = angular.module('userRegistrationSystem',['ngRoute','ngResource']);

app.config(function ($routeProvider) {
  $routeProvider.when('/list-all-users',{
    templateUrl: '/templates/user-list.html',
    controller: 'listUserController'
  }).when('/register-new-user',{
    templateUrl: '/templates/user-registration.html',
    controller: 'registerUserController'
  }).when('/update-user/:id',{
    templateUrl: '/templates/user-update.html',
    controller: 'userDetailsController'
  }).otherwise({
    templateUrl: '/templates/home.html',
    redirectTo: '/home'
  })
});