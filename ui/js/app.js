angular.module('salyoApp', ['ngMaterial', 'ngMessages', 'ngRoute'])
    .config(function ($mdThemingProvider) {

        $mdThemingProvider.theme('default')
            .primaryPalette('green')

            .accentPalette('amber');


    })
    .config(function ($routeProvider, $locationProvider) {


        $routeProvider.when("/login", {
            templateUrl: "html/login.html",
            controller: "LoginCtrl"
        }).when("/dashboard", {
            templateUrl: "html/dashboard.html",
            controller: "DashboardCtrl"
        }).otherwise({
            templateUrl: "html/login.html",
            controller: "LoginCtrl"
        });


    })
    .service('authService', function ($http,$location, $mdToast) {
        return {
            createToken: function (username, password) {




                $http({ method: 'POST', url: 'http://localhost:9998/auth/token',
                    headers: { 'Content-Type': 'text/plain','username':username,'password':password } })
                    .success(function(result){
                        $location.path('/dashboard');
                }).error(function(status){
                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('username or password invalid!')
                            .position('bottom right')
                            .hideDelay(3000)
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

        /**
         * Build handler to open/close a SideNav; when animation finishes
         * report completion in console
         */
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
    .controller('LoginCtrl', ['$scope', '$location', 'authService', function ($scope, $location, authService) {
        $scope.username = "";
        $scope.password = "";


        $scope.login = function (username, password) {
            authService.createToken(username, password);

        }
    }])
    .controller('DashboardCtrl', function ($scope, $location) {

    })
    .controller('sidenavleftCtrl', function ($scope, $mdSidenav, $location) {

        $scope.links = [
            {id: 'dashboard', name: 'Dashboard', url: '/dashboard', badge: 0},
            {id: 'company', name: 'Companies', url: '/companies', badge: 0}
        ];


        $scope.close = function (url) {


            $mdSidenav('leftnav').close()
                .then(function () {

                });
            $location.path(url);

        }
    })
;
