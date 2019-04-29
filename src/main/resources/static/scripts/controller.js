var USER_API_URL = 'http://localhost:8888/api/user/'

app.controller('registerUserController',function ($scope,$http,$location,$route) {
  
  $scope.submitUserForm = function () {
    $http({
      method: 'POST',
      url: USER_API_URL,
      data: $scope.user,
      headers: {
        'Content-Type': 'application/json'
      }
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
      data: $scope.user,
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(function (response) {
      $location.path('/list-all-users');
      $route.reload();
    },function (errResponse) {
      $scope.errorMessage = 'Error while updating User - Error Message: ' + errResponse.data.errorMessage;
    });
  }; //submitUserForm

}); //*userDetailsController

app.controller('homeController', function ($rootScope,$scope,$http,$location,$route) {
  if($rootScope.authenticated) {
    $location.path('/');
    $scope.loginerror = false;
  } else {
    $location.path('/login');
    $scope.loginerror = true;
  }
}); //*homeController

app.controller('loginController', function ($rootScope,$scope,$http,$location,$route) {
  $scope.credentials = {};
  $scope.resetForm = function() {
    $scope.credentials = null;
  }; //resetForm

  var authenticate = function (credentials, callback) {
    var headers = $scope.credentials ? {
      authorization : 'Basic ' + btoa($scope.credentials.username + ':' + $scope.credentials.password)
    } : {};

    $http.get('user',{headers : headers}).then(function (response) {
      $rootScope.authenticated = !!response.data.name;
      callback && callback();
    }, function () {
      $rootScope.authenticated = false;
      callback && callback();
    });
  }; //authenticate

  authenticate();

  $scope.loginUser = function() {
    authenticate($scope.credentials,function () {
      if($rootScope.authenticated) {
        $location.path('/');
        $scope.loginerror = false;
      } else {
        $location.path('/login');
        $scope.loginerror = true;
      }
    });
  }; //loginUser
}); //*loginController

app.controller('logoutController',function ($rootScope,$scope,$http,$location,$route) {
  $http.post('logout',{}).finally(function() {
    $rootScope.authenticated = false;
    $location.path('/');
  });
}); //*logoutController