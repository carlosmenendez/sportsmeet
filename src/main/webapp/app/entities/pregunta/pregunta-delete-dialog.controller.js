(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('PreguntaDeleteController',PreguntaDeleteController);

    PreguntaDeleteController.$inject = ['$uibModalInstance', 'entity', 'Pregunta'];

    function PreguntaDeleteController($uibModalInstance, entity, Pregunta) {
        var vm = this;
        vm.pregunta = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Pregunta.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
