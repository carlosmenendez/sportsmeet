(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('RespuestaDialogController', RespuestaDialogController);

    RespuestaDialogController.$inject = ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Respuesta', 'Pregunta', 'User'];

    function RespuestaDialogController ($scope, $stateParams, $uibModalInstance, entity, Respuesta, Pregunta, User) {
        var vm = this;
        vm.respuesta = entity;
        vm.preguntas = Pregunta.query();
        vm.users = User.query();
        vm.load = function(id) {
            Respuesta.get({id : id}, function(result) {
                vm.respuesta = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('basketballOauth2Jhipster3App:respuestaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.respuesta.id !== null) {
                Respuesta.update(vm.respuesta, onSaveSuccess, onSaveError);
            } else {
                Respuesta.save(vm.respuesta, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
    }
})();
