(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('DeporteDialogController', DeporteDialogController);

    DeporteDialogController.$inject = ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Deporte', 'Evento', 'Deportefavorito'];

    function DeporteDialogController ($scope, $stateParams, $uibModalInstance, entity, Deporte, Evento, Deportefavorito) {
        var vm = this;
        vm.deporte = entity;
        vm.eventos = Evento.query();
        vm.deportefavoritos = Deportefavorito.query();
        vm.load = function(id) {
            Deporte.get({id : id}, function(result) {
                vm.deporte = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('basketballOauth2Jhipster3App:deporteUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.deporte.id !== null) {
                Deporte.update(vm.deporte, onSaveSuccess, onSaveError);
            } else {
                Deporte.save(vm.deporte, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
    }
})();
