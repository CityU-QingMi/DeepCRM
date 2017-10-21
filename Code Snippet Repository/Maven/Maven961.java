    private void injectSession( MetadataResolutionRequest request )
    {
        RepositorySystemSession session = legacySupport.getRepositorySession();

        if ( session != null )
        {
            request.setOffline( session.isOffline() );
            request.setForceUpdate( RepositoryPolicy.UPDATE_POLICY_ALWAYS.equals( session.getUpdatePolicy() ) );
        }
    }
