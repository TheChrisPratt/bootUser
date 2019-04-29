var app = angular.module('userRegistrationSystem',['ngRoute','ngResource']);

app.config(function ($routeProvider) {
  $routeProvider.when('/',{
    templateUrl: '/templates/home.html',
    controller: 'homeController'
  }).when('/list-all-users',{
    templateUrl: '/templates/user-list.html',
    controller: 'listUserController'
  }).when('/register-new-user',{
    templateUrl: '/templates/user-registration.html',
    controller: 'registerUserController'
  }).when('/update-user/:id', {
    templateUrl: '/templates/user-update.html',
    controller: 'userDetailsController'
  }).when('/login',{
    templateUrl: '/templates/login.html',
    controller: 'loginController'
  }).when('/logout',{
    templateUrl: '/templates/login.html',
    controller: 'logoutController'
  }).otherwise({
    redirectTo: '/login'
  })
});

// app.config(['$httpProvider',function ($httpProvider) {
//   $httpProvider.interceptors.push('AuthInterceptor');
// }]);

app.config(['$httpProvider',function ($httpProvider) {
  $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}]);