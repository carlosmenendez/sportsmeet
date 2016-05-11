(function() {
    'use strict';
    angular
        .module('basketballOauth2Jhipster3App')
        .factory('Deportefavorito', Deportefavorito);

    Deportefavorito.$inject = ['$resource'];

    function Deportefavorito ($resource) {
        var resourceUrl =  'api/deportefavoritos/:id';

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
