    public DefaultProjectBuildingRequest( ProjectBuildingRequest request )
    {
        this();
        setProcessPlugins( request.isProcessPlugins() );
        setProfiles( request.getProfiles() );
        setActiveProfileIds( request.getActiveProfileIds() );
        setInactiveProfileIds( request.getInactiveProfileIds() );
        setSystemProperties( request.getSystemProperties() );
        setUserProperties( request.getUserProperties() );
        setRemoteRepositories( request.getRemoteRepositories() );
        setPluginArtifactRepositories( request.getPluginArtifactRepositories() );
        setRepositorySession( request.getRepositorySession() );
        setLocalRepository( request.getLocalRepository() );
        setBuildStartTime( request.getBuildStartTime() );
        setProject( request.getProject() );
        setResolveDependencies( request.isResolveDependencies() );
        setValidationLevel( request.getValidationLevel() );
    }
