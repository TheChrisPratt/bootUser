var USER_API_URL = 'http://localhost:8888/api/user/'

app.controller('registerUserController',function ($scope,$http,$location,$route) {
  
  $scope.submitUserForm = function () {
    $http({
      method: 'POST',
      url: USER_API_URL,
      data: $scope.user
    }).then(function (response) {
      $location.path('/list-all-users');
      $route.reload();
    },function (errResponse) {
      $scope.errorMessage = errResponse.data.errorMessage;
    });
  }; //submitUserForm

  $scope.resetForm = function () {
    $scope.user = null;
  }; //resetForm

}); //*registerUserController

app.controller('listUserController',function ($scope,$http,$location,$route) {
  
  $http({
    method: 'GET',
    url: USER_API_URL
  }).then(function (response) {
    $scope.users = response.data;
  });

  $scope.editUser = function (userId) {
    $location.path('/update-user/' + userId);
  }; //editUser

  $scope.deleteUser = function (userId) {
    $http({
      method: 'DELETE',
      url: USER_API_URL + userId
    }).then(function (response) {
      $location.path('/list-all-users');
      $route.reload();
    });
  } //deleteUser

}); //*listUserController

app.controller('userDetailsController',function ($scope,$http,$location,$route) {

  $scope.userId = $routeParams.id;

  $http({
    method: 'GET',
    url: USER_API_URL + $scope.userId
  });

  $scope.submitUserForm = function () {
    $http({
      method: 'POST',
      url: USER_API_URL,
      data: $scope.user
    }).then(function (response) {
      $location.path('/list-all-users');
      $route.reload();
    },function (errResponse) {
      $scope.errorMessage = 'Error while updating User - Error Message: ' + errResponse.data.errorMessage;
    });
  }; //submitUserForm

}); //*userDetailsController