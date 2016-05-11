(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('EventoDialogController', EventoDialogController);

    EventoDialogController.$inject = ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Evento', 'Deporte', 'User'];

    function EventoDialogController ($scope, $stateParams, $uibModalInstance, entity, Evento, Deporte, User) {
        var vm = this;
        vm.evento = entity;
        vm.deportes = Deporte.query();
        vm.users = User.query();
        vm.load = function(id) {
            Evento.get({id : id}, function(result) {
                vm.evento = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('basketballOauth2Jhipster3App:eventoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.evento.id !== null) {
                Evento.update(vm.evento, onSaveSuccess, onSaveError);
            } else {
                Evento.save(vm.evento, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };

        vm.datePickerOpenStatus = {};
        vm.datePickerOpenStatus.hora = false;

        vm.openCalendar = function(date) {
            vm.datePickerOpenStatus[date] = true;
        };
    }
})();
