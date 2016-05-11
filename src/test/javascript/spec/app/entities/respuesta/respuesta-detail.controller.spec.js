'use strict';

describe('Controller Tests', function() {

    describe('Respuesta Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockRespuesta, MockPregunta, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockRespuesta = jasmine.createSpy('MockRespuesta');
            MockPregunta = jasmine.createSpy('MockPregunta');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Respuesta': MockRespuesta,
                'Pregunta': MockPregunta,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("RespuestaDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'basketballOauth2Jhipster3App:respuestaUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
