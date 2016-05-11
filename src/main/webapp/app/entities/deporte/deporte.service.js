(function() {
    'use strict';
    angular
        .module('basketballOauth2Jhipster3App')
        .factory('Deporte', Deporte);

    Deporte.$inject = ['$resource'];

    function Deporte ($resource) {
        var resourceUrl =  'api/deportes/:id';

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
