    @Override
    public ProjectBuildingRequest getProjectBuildingRequest()
    {
        if ( projectBuildingRequest == null )
        {
            projectBuildingRequest = new DefaultProjectBuildingRequest();
            projectBuildingRequest.setLocalRepository( getLocalRepository() );
            projectBuildingRequest.setSystemProperties( getSystemProperties() );
            projectBuildingRequest.setUserProperties( getUserProperties() );
            projectBuildingRequest.setRemoteRepositories( getRemoteRepositories() );
            projectBuildingRequest.setPluginArtifactRepositories( getPluginArtifactRepositories() );
            projectBuildingRequest.setActiveProfileIds( getActiveProfiles() );
            projectBuildingRequest.setInactiveProfileIds( getInactiveProfiles() );
            projectBuildingRequest.setProfiles( getProfiles() );
            projectBuildingRequest.setProcessPlugins( true );
            projectBuildingRequest.setBuildStartTime( getStartTime() );
        }

        return projectBuildingRequest;
    }
