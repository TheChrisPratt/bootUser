app.factory('AuthInterceptor',[function () {
  return {
    'request': function (config) {
      config.headers = config.headers || {};
      config.headers.Authorization = 'Basic ' + btoa('admin:password');
      return config;
    } //request
  } //*AuthInterceptor
}]);