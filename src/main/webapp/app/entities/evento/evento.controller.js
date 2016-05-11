(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('EventoController', EventoController);

    EventoController.$inject = ['$scope', '$state', 'Evento'];

    function EventoController ($scope, $state, Evento) {
        var vm = this;
        vm.eventos = [];
        vm.loadAll = function() {
            Evento.query(function(result) {
                vm.eventos = result;
            });
        };

        vm.loadAll();
        
    }
})();
