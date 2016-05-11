(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('RespuestaDeleteController',RespuestaDeleteController);

    RespuestaDeleteController.$inject = ['$uibModalInstance', 'entity', 'Respuesta'];

    function RespuestaDeleteController($uibModalInstance, entity, Respuesta) {
        var vm = this;
        vm.respuesta = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Respuesta.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
