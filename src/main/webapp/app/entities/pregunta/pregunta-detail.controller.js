(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .controller('PreguntaDetailController', PreguntaDetailController);

    PreguntaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Pregunta', 'Respuesta', 'User'];

    function PreguntaDetailController($scope, $rootScope, $stateParams, entity, Pregunta, Respuesta, User) {
        var vm = this;
        vm.pregunta = entity;
        vm.load = function (id) {
            Pregunta.get({id: id}, function(result) {
                vm.pregunta = result;
            });
        };
        var unsubscribe = $rootScope.$on('basketballOauth2Jhipster3App:preguntaUpdate', function(event, result) {
            vm.pregunta = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
