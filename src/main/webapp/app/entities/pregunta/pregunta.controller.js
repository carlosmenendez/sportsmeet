(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('PreguntaController', PreguntaController);

    PreguntaController.$inject = ['$scope', '$state', 'Pregunta'];

    function PreguntaController ($scope, $state, Pregunta) {
        var vm = this;
        vm.preguntas = [];
        vm.loadAll = function() {
            Pregunta.query(function(result) {
                vm.preguntas = result;
            });
        };

        vm.loadAll();
        
    }
})();
