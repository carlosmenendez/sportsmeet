(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('deporte', {
            parent: 'entity',
            url: '/deporte',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'basketballOauth2Jhipster3App.deporte.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/deporte/deportes.html',
                    controller: 'DeporteController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('deporte');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('deporte-detail', {
            parent: 'entity',
            url: '/deporte/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'basketballOauth2Jhipster3App.deporte.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/deporte/deporte-detail.html',
                    controller: 'DeporteDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('deporte');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Deporte', function($stateParams, Deporte) {
                    return Deporte.get({id : $stateParams.id});
                }]
            }
        })
        .state('deporte.new', {
            parent: 'deporte',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/deporte/deporte-dialog.html',
                    controller: 'DeporteDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nombre: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('deporte', null, { reload: true });
                }, function() {
                    $state.go('deporte');
                });
            }]
        })
        .state('deporte.edit', {
            parent: 'deporte',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/deporte/deporte-dialog.html',
                    controller: 'DeporteDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Deporte', function(Deporte) {
                            return Deporte.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('deporte', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('deporte.delete', {
            parent: 'deporte',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/deporte/deporte-delete-dialog.html',
                    controller: 'DeporteDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Deporte', function(Deporte) {
                            return Deporte.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('deporte', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
