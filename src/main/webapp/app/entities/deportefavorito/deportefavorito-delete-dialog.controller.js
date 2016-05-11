(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('DeportefavoritoDeleteController',DeportefavoritoDeleteController);

    DeportefavoritoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Deportefavorito'];

    function DeportefavoritoDeleteController($uibModalInstance, entity, Deportefavorito) {
        var vm = this;
        vm.deportefavorito = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Deportefavorito.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
