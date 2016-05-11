'use strict';

describe('Controller Tests', function() {

    describe('Evento Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockEvento, MockDeporte, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockEvento = jasmine.createSpy('MockEvento');
            MockDeporte = jasmine.createSpy('MockDeporte');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Evento': MockEvento,
                'Deporte': MockDeporte,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("EventoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'basketballOauth2Jhipster3App:eventoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
