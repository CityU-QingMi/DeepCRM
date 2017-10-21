    protected MavenProject getProjectFromRemoteRepository( final File pom )
        throws Exception
    {
        final ProjectBuildingRequest configuration = new DefaultProjectBuildingRequest();
        configuration.setLocalRepository( this.getLocalRepository() );
        configuration.setRemoteRepositories( Arrays.asList( this.repositorySystem.createDefaultRemoteRepository() ) );
        initRepoSession( configuration );

        return projectBuilder.build( pom, configuration ).getProject();
    }
