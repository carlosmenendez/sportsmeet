(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('EventoDetailController', EventoDetailController);

    EventoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Evento', 'Deporte', 'User'];

    function EventoDetailController($scope, $rootScope, $stateParams, entity, Evento, Deporte, User) {
        var vm = this;
        vm.evento = entity;
        vm.load = function (id) {
            Evento.get({id: id}, function(result) {
                vm.evento = result;
            });
        };
        var unsubscribe = $rootScope.$on('basketballOauth2Jhipster3App:eventoUpdate', function(event, result) {
            vm.evento = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
