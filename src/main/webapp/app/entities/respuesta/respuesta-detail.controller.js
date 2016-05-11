(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('RespuestaDetailController', RespuestaDetailController);

    RespuestaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Respuesta', 'Pregunta', 'User'];

    function RespuestaDetailController($scope, $rootScope, $stateParams, entity, Respuesta, Pregunta, User) {
        var vm = this;
        vm.respuesta = entity;
        vm.load = function (id) {
            Respuesta.get({id: id}, function(result) {
                vm.respuesta = result;
            });
        };
        var unsubscribe = $rootScope.$on('basketballOauth2Jhipster3App:respuestaUpdate', function(event, result) {
            vm.respuesta = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
