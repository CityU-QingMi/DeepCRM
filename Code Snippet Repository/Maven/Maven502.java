    public static RepositoryRequest getRepositoryRequest( MavenSession session, MavenProject project )
    {
        RepositoryRequest request = new DefaultRepositoryRequest();

        request.setLocalRepository( session.getLocalRepository() );
        if ( project != null )
        {
            request.setRemoteRepositories( project.getPluginArtifactRepositories() );
        }
        request.setOffline( session.isOffline() );
        request.setForceUpdate( session.getRequest().isUpdateSnapshots() );

        return request;
    }
