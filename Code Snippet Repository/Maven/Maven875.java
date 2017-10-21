    private ModelBuildingRequest getModelBuildingRequest( InternalConfig config )
    {
        ProjectBuildingRequest configuration = config.request;

        ModelBuildingRequest request = new DefaultModelBuildingRequest();

        RequestTrace trace = RequestTrace.newChild( null, configuration ).newChild( request );

        ModelResolver resolver =
            new ProjectModelResolver( config.session, trace, repoSystem, repositoryManager, config.repositories,
                                      configuration.getRepositoryMerging(), config.modelPool );

        request.setValidationLevel( configuration.getValidationLevel() );
        request.setProcessPlugins( configuration.isProcessPlugins() );
        request.setProfiles( configuration.getProfiles() );
        request.setActiveProfileIds( configuration.getActiveProfileIds() );
        request.setInactiveProfileIds( configuration.getInactiveProfileIds() );
        request.setSystemProperties( configuration.getSystemProperties() );
        request.setUserProperties( configuration.getUserProperties() );
        request.setBuildStartTime( configuration.getBuildStartTime() );
        request.setModelResolver( resolver );
        request.setModelCache( config.modelCache );

        return request;
    }
