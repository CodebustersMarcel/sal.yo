angular.module('salyoApp', ['ngMaterial', 'ngMessages', 'ngRoute','ngCookies'])
    .config(function ($mdThemingProvider) {

        $mdThemingProvider.theme('default')
            .primaryPalette('green')

            .accentPalette('deep-purple');


    })
    .config(function ($routeProvider, $locationProvider) {


        $routeProvider.when("/login", {
            templateUrl: "html/login.html",
            controller: "LoginCtrl"
        }).when("/dashboard", {
            templateUrl: "html/dashboard.html",
            controller: "DashboardCtrl"
        }).when("/companies", {
            templateUrl: "html/companies.html",
            controller: "CompanyCtrl"
        }).when("/configuration", {
            templateUrl: "html/configuration.html",
            controller: "ConfigurationCtrl"
        }).otherwise({
            templateUrl: "html/login.html",
            controller: "LoginCtrl"
        });


    })
    .service('authService', function ($http, $location, $mdToast) {
        return {
            createToken: function (username, password) {


                $http({
                    method: 'POST', url: 'http://localhost:9998/auth/token',
                    headers: {'Content-Type': 'text/plain', 'username': username, 'password': password}
                })
                    .success(function (result) {
                        $location.path('/dashboard');
                    }).error(function (status) {
                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('username or password invalid!')
                            .position('bottom right')
                            .hideDelay(6000)
                    );

                });


            }
        }


    })
    .directive('sidenavleft', function () {
        return {
            templateUrl: 'html/widget/sidenavleft.html'
        };
    })
    .directive('config', function () {
        return {
            templateUrl: 'html/widget/config.html'
        };
    })
    .directive('companyList', function () {
        return {
            templateUrl: 'html/widget/companies.html'
        };
    })
    .directive('header', function () {
        return {
            templateUrl: 'html/widget/header.html'
        };
    })
    .directive('directive', function ($compile, $interpolate) {
        return {
            template: '',
            link: function ($scope, element, attributes) {
                element.html(attributes.directive);
                var el = $compile(element.contents())($scope);

                element.replaceWith(el);
            }
        };
    })
    .controller('AppCtrl', function ($scope, $timeout, $mdSidenav, $log, $location) {
$scope.search="";

        $scope.toggleLeft = buildToggler('leftnav');

        $scope.isOpenLeft = function () {
            return $mdSidenav('leftnav').isOpen();
        };

        function debounce(func, wait, context) {
            var timer;
            return function debounced() {
                var context = $scope,
                    args = Array.prototype.slice.call(arguments);
                $timeout.cancel(timer);
                timer = $timeout(function () {
                    timer = undefined;
                    func.apply(context, args);
                }, wait || 10);
            };
        }

        function buildDelayedToggler(navID) {
            return debounce(function () {
                $mdSidenav(navID)
                    .toggle()
                    .then(function () {

                    });
            }, 200);
        }

        function buildToggler(navID) {
            return function () {
                $mdSidenav(navID)
                    .toggle()
                    .then(function () {

                    });
            }
        }

        $scope.close = function (url) {

            $location.path(url);

        }
    })
    .controller('CompanyCtrl', function ($scope, $http) {
        $scope.companies = [{
            "api": "TimeTac",
            "id": "22b6e72f-ed3c-4b99-b201-b1aea6916536",
            "name": "Test Company",
            "password": "9dpG-RQC4-2kw7-sjxc-ApGm-jPDx",
            "userId": "dcfa70d6-97ed-4b90-978b-8944efc4e139",
            "username": "wolterskluwer"
        }];

        function getAllCompanies() {

            $http({
                method: 'GET',
                url: 'http://localhost:9998/companies/all'
            }).then(function successCallback(response) {
                return response;
            }, function errorCallback(response) {
                return response;

            });
        }
    })
    .controller('CookieCtrl',  ['$scope','$cookies', function($scope,$cookies){
        $scope.role="Admin";
    }])
    .controller('ConfigurationCtrl',  function($scope){
        $scope.fieldsA = [];
        $scope.fieldsB = [];
        $scope.api =[{name:'TimeTac'}]
    })
        .controller('LoginCtrl', ['$scope', '$location','$cookies' ,'authService', function ($scope, $location,$cookies, authService) {
        $scope.username = "";
        $scope.password = "";


        $scope.login = function (username, password) {

           $cookies.put('role',authService.createToken(username, password));
        }
    }])
    .controller('DashboardCtrl', function ($scope, $location,$interval) {
        $scope.api =2;
        $scope.customers=1;
        $scope.notifications=2;

        $interval(function(){
            $scope.notifications++;
        },5000)

    })
    .controller('sidenavleftCtrl', function ($scope, $mdSidenav, $location) {

        $scope.links = [
            {id: 'dashboard', name: 'Dashboard', url: '/dashboard', badge: 0},
            {id: 'company', name: 'Companies', url: '/companies', badge: 0},
            {id: 'configuration', name: 'Configuration', url: '/configuration', badge: 0}
        ];


        $scope.close = function (url) {


            $mdSidenav('leftnav').close()
                .then(function () {

                });
            $location.path(url);

        }
    })
;
