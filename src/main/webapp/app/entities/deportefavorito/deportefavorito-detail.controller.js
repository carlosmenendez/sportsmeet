(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('DeportefavoritoDetailController', DeportefavoritoDetailController);

    DeportefavoritoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Deportefavorito', 'User', 'Deporte'];

    function DeportefavoritoDetailController($scope, $rootScope, $stateParams, entity, Deportefavorito, User, Deporte) {
        var vm = this;
        vm.deportefavorito = entity;
        vm.load = function (id) {
            Deportefavorito.get({id: id}, function(result) {
                vm.deportefavorito = result;
            });
        };
        var unsubscribe = $rootScope.$on('basketballOauth2Jhipster3App:deportefavoritoUpdate', function(event, result) {
            vm.deportefavorito = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
