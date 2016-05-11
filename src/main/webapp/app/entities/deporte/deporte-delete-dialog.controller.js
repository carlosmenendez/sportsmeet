(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('DeporteDeleteController',DeporteDeleteController);

    DeporteDeleteController.$inject = ['$uibModalInstance', 'entity', 'Deporte'];

    function DeporteDeleteController($uibModalInstance, entity, Deporte) {
        var vm = this;
        vm.deporte = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Deporte.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
