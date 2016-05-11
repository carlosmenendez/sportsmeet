(function() {
    'use strict';
    angular
        .module('basketballOauth2Jhipster3App')
        .factory('Pregunta', Pregunta);

    Pregunta.$inject = ['$resource'];

    function Pregunta ($resource) {
        var resourceUrl =  'api/preguntas/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
