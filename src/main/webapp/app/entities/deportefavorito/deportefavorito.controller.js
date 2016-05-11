(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('DeportefavoritoController', DeportefavoritoController);

    DeportefavoritoController.$inject = ['$scope', '$state', 'Deportefavorito'];

    function DeportefavoritoController ($scope, $state, Deportefavorito) {
        var vm = this;
        vm.deportefavoritos = [];
        vm.loadAll = function() {
            Deportefavorito.query(function(result) {
                vm.deportefavoritos = result;
            });
        };

        vm.loadAll();
        
    }
})();
