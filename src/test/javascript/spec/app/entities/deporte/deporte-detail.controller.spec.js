'use strict';

describe('Controller Tests', function() {

    describe('Deporte Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockDeporte, MockEvento, MockDeporteFavorito;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockDeporte = jasmine.createSpy('MockDeporte');
            MockEvento = jasmine.createSpy('MockEvento');
            MockDeporteFavorito = jasmine.createSpy('MockDeporteFavorito');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Deporte': MockDeporte,
                'Evento': MockEvento,
                'DeporteFavorito': MockDeporteFavorito
            };
            createController = function() {
                $injector.get('$controller')("DeporteDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'basketballOauth2Jhipster3App:deporteUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
