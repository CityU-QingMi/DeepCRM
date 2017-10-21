    protected Artifact createArtifact( String groupId, String artifactId, String version )
        throws Exception
    {
        Dependency dependency = new Dependency();
        dependency.setGroupId( groupId );
        dependency.setArtifactId( artifactId );
        dependency.setVersion( version );
        dependency.setType( "jar" );
        dependency.setScope( "compile" );

        return factory.createDependencyArtifact( dependency );
    }
