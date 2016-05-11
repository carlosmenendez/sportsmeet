(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('PreguntaDialogController', PreguntaDialogController);

    PreguntaDialogController.$inject = ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Pregunta', 'Respuesta', 'User'];

    function PreguntaDialogController ($scope, $stateParams, $uibModalInstance, entity, Pregunta, Respuesta, User) {
        var vm = this;
        vm.pregunta = entity;
        vm.respuestas = Respuesta.query();
        vm.users = User.query();
        vm.load = function(id) {
            Pregunta.get({id : id}, function(result) {
                vm.pregunta = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('basketballOauth2Jhipster3App:preguntaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.pregunta.id !== null) {
                Pregunta.update(vm.pregunta, onSaveSuccess, onSaveError);
            } else {
                Pregunta.save(vm.pregunta, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
    }
})();
