(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('DeportefavoritoDialogController', DeportefavoritoDialogController);

    DeportefavoritoDialogController.$inject = ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Deportefavorito', 'User', 'Deporte'];

    function DeportefavoritoDialogController ($scope, $stateParams, $uibModalInstance, entity, Deportefavorito, User, Deporte) {
        var vm = this;
        vm.deportefavorito = entity;
        vm.users = User.query();
        vm.deportes = Deporte.query();
        vm.load = function(id) {
            Deportefavorito.get({id : id}, function(result) {
                vm.deportefavorito = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('basketballOauth2Jhipster3App:deportefavoritoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.deportefavorito.id !== null) {
                Deportefavorito.update(vm.deportefavorito, onSaveSuccess, onSaveError);
            } else {
                Deportefavorito.save(vm.deportefavorito, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
    }
})();
