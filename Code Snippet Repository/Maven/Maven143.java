    private ProjectBuildingRequest toRequest( ProjectBuilderConfiguration configuration )
    {
        DefaultProjectBuildingRequest request = new DefaultProjectBuildingRequest();

        request.setValidationLevel( ModelBuildingRequest.VALIDATION_LEVEL_MAVEN_2_0 );
        request.setResolveDependencies( false );

        request.setLocalRepository( configuration.getLocalRepository() );
        request.setBuildStartTime( configuration.getBuildStartTime() );
        request.setUserProperties( configuration.getUserProperties() );
        request.setSystemProperties( configuration.getExecutionProperties() );

        ProfileManager profileManager = configuration.getGlobalProfileManager();
        if ( profileManager != null )
        {
            request.setActiveProfileIds( profileManager.getExplicitlyActivatedIds() );
            request.setInactiveProfileIds( profileManager.getExplicitlyDeactivatedIds() );
        }
        else
        {
/**/
/**/
/**/
/**/
/**/
/**/
/**/
            MavenSession session = legacySupport.getSession();
            if ( session != null )
            {
                MavenExecutionRequest req = session.getRequest();
                if ( req != null )
                {
                    request.setActiveProfileIds( req.getActiveProfiles() );
                    request.setInactiveProfileIds( req.getInactiveProfiles() );
                }
            }
        }

        return request;
    }
