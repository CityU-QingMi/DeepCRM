    protected MavenExecutionRequest createMavenExecutionRequest( File pom )
        throws Exception
    {
        MavenExecutionRequest request = new DefaultMavenExecutionRequest()
            .setPom( pom )
            .setProjectPresent( true )
            .setShowErrors( true )
            .setPluginGroups( Arrays.asList( "org.apache.maven.plugins" ) )
            .setLocalRepository( getLocalRepository() )
            .setRemoteRepositories( getRemoteRepositories() )
            .setPluginArtifactRepositories( getPluginArtifactRepositories() )
            .setGoals( Arrays.asList( "package" ) );

        return request;
    }
