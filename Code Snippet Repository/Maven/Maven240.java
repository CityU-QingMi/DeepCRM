    private void injectSession( ArtifactResolutionRequest request )
    {
        MavenSession session = legacySupport.getSession();

        if ( session != null )
        {
            request.setOffline( session.isOffline() );
            request.setForceUpdate( session.getRequest().isUpdateSnapshots() );
            request.setServers( session.getRequest().getServers() );
            request.setMirrors( session.getRequest().getMirrors() );
            request.setProxies( session.getRequest().getProxies() );
        }
    }
