'use strict';

describe('Controller Tests', function() {

    describe('Deportefavorito Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockDeportefavorito, MockUser, MockDeporte;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockDeportefavorito = jasmine.createSpy('MockDeportefavorito');
            MockUser = jasmine.createSpy('MockUser');
            MockDeporte = jasmine.createSpy('MockDeporte');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Deportefavorito': MockDeportefavorito,
                'User': MockUser,
                'Deporte': MockDeporte
            };
            createController = function() {
                $injector.get('$controller')("DeportefavoritoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'basketballOauth2Jhipster3App:deportefavoritoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
