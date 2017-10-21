    public DefaultPluginPrefixRequest( String prefix, MavenSession session )
    {
        setPrefix( prefix );

        setRepositorySession( session.getRepositorySession() );

        MavenProject project = session.getCurrentProject();
        if ( project != null )
        {
            setRepositories( project.getRemotePluginRepositories() );
            setPom( project.getModel() );
        }

        setPluginGroups( session.getPluginGroups() );
    }
