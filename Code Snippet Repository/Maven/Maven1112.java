    @SuppressWarnings( "" )
    private static MavenSession createSession( PlexusContainer container, ArtifactRepository repo, Properties properties )
        throws CycleDetectedException, DuplicateProjectException
    {
        MavenExecutionRequest request = new DefaultMavenExecutionRequest()
            .setSystemProperties( properties )
            .setGoals( Collections.<String>emptyList() )
            .setBaseDirectory( new File( "" ) )
            .setLocalRepository( repo );

        return new MavenSession( container, request, new DefaultMavenExecutionResult(), Collections.<MavenProject>emptyList()  );
    }
