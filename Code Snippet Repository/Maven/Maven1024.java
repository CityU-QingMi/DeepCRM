    protected MavenSession createMavenSession( File pom, Properties executionProperties )
        throws Exception
    {
        MavenExecutionRequest request = createMavenExecutionRequest( pom );

        ProjectBuildingRequest configuration = new DefaultProjectBuildingRequest()
            .setLocalRepository( request.getLocalRepository() )
            .setRemoteRepositories( request.getRemoteRepositories() )
            .setPluginArtifactRepositories( request.getPluginArtifactRepositories() )
            .setSystemProperties( executionProperties );

        MavenProject project = null;

        if ( pom != null )
        {
            project = projectBuilder.build( pom, configuration ).getProject();
        }
        else
        {
            project = createStubMavenProject();
            project.setRemoteArtifactRepositories( request.getRemoteRepositories() );
            project.setPluginArtifactRepositories( request.getPluginArtifactRepositories() );
        }

        initRepoSession( configuration );

        MavenSession session =
            new MavenSession( getContainer(), configuration.getRepositorySession(), request,
                              new DefaultMavenExecutionResult() );
        session.setProjects( Arrays.asList( project ) );
        session.setAllProjects( session.getProjects() );

        return session;
    }
