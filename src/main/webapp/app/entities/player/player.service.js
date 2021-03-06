(function() {
    'use strict';
    angular
        .module('basketballOauth2Jhipster3App')
        .factory('Player', Player);

    Player.$inject = ['$resource'];

    function Player ($resource) {
        var resourceUrl =  'api/players/:id';

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
