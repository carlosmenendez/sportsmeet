(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('RespuestaController', RespuestaController);

    RespuestaController.$inject = ['$scope', '$state', 'Respuesta'];

    function RespuestaController ($scope, $state, Respuesta) {
        var vm = this;
        vm.respuestas = [];
        vm.loadAll = function() {
            Respuesta.query(function(result) {
                vm.respuestas = result;
            });
        };

        vm.loadAll();
        
    }
})();
