    private void injectSession2( ArtifactResolutionRequest request, MavenSession session )
    {
        injectSession1( request, session );

        if ( session != null )
        {
            request.setServers( session.getRequest().getServers() );
            request.setMirrors( session.getRequest().getMirrors() );
            request.setProxies( session.getRequest().getProxies() );
        }
    }
