(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('DeporteDetailController', DeporteDetailController);

    DeporteDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Deporte', 'Evento', 'DeporteFavorito'];

    function DeporteDetailController($scope, $rootScope, $stateParams, entity, Deporte, Evento, DeporteFavorito) {
        var vm = this;
        vm.deporte = entity;
        vm.load = function (id) {
            Deporte.get({id: id}, function(result) {
                vm.deporte = result;
            });
        };
        var unsubscribe = $rootScope.$on('basketballOauth2Jhipster3App:deporteUpdate', function(event, result) {
            vm.deporte = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
