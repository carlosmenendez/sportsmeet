(function() {
    'use strict';

    angular
        .module('basketballOauth2Jhipster3App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('deportefavorito', {
            parent: 'entity',
            url: '/deportefavorito',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'basketballOauth2Jhipster3App.deportefavorito.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/deportefavorito/deportefavoritos.html',
                    controller: 'DeportefavoritoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('deportefavorito');
                    $translatePartialLoader.addPart('nivelHabilidad');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('deportefavorito-detail', {
            parent: 'entity',
            url: '/deportefavorito/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'basketballOauth2Jhipster3App.deportefavorito.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/deportefavorito/deportefavorito-detail.html',
                    controller: 'DeportefavoritoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('deportefavorito');
                    $translatePartialLoader.addPart('nivelHabilidad');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Deportefavorito', function($stateParams, Deportefavorito) {
                    return Deportefavorito.get({id : $stateParams.id});
                }]
            }
        })
        .state('deportefavorito.new', {
            parent: 'deportefavorito',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/deportefavorito/deportefavorito-dialog.html',
                    controller: 'DeportefavoritoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nivelDominio: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('deportefavorito', null, { reload: true });
                }, function() {
                    $state.go('deportefavorito');
                });
            }]
        })
        .state('deportefavorito.edit', {
            parent: 'deportefavorito',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/deportefavorito/deportefavorito-dialog.html',
                    controller: 'DeportefavoritoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Deportefavorito', function(Deportefavorito) {
                            return Deportefavorito.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('deportefavorito', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('deportefavorito.delete', {
            parent: 'deportefavorito',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/deportefavorito/deportefavorito-delete-dialog.html',
                    controller: 'DeportefavoritoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Deportefavorito', function(Deportefavorito) {
                            return Deportefavorito.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('deportefavorito', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
