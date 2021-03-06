(function() {
    'use strict';
    angular
        .module('basketballOauth2Jhipster3App')
        .factory('Evento', Evento);

    Evento.$inject = ['$resource', 'DateUtils'];

    function Evento ($resource, DateUtils) {
        var resourceUrl =  'api/eventos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.hora = DateUtils.convertDateTimeFromServer(data.hora);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
