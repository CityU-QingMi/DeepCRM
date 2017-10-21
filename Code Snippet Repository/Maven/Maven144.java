    private ProjectBuildingRequest injectSession( ProjectBuildingRequest request )
    {
        MavenSession session = legacySupport.getSession();
        if ( session != null )
        {
            request.setRepositorySession( session.getRepositorySession() );
            request.setSystemProperties( session.getSystemProperties() );
            if ( request.getUserProperties().isEmpty() )
            {
                request.setUserProperties( session.getUserProperties() );
            }

            MavenExecutionRequest req = session.getRequest();
            if ( req != null )
            {
                request.setRemoteRepositories( req.getRemoteRepositories() );
            }
        }
        else
        {
            Properties props = new Properties();
            EnvironmentUtils.addEnvVars( props );
            props.putAll( System.getProperties() );
            request.setSystemProperties( props );
        }

        return request;
    }
