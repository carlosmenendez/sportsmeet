(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('DeporteController', DeporteController);

    DeporteController.$inject = ['$scope', '$state', 'Deporte'];

    function DeporteController ($scope, $state, Deporte) {
        var vm = this;
        vm.deportes = [];
        vm.loadAll = function() {
            Deporte.query(function(result) {
                vm.deportes = result;
            });
        };

        vm.loadAll();
        
    }
})();
