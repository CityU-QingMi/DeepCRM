    public void testBuildStubModelForMissingRemotePom()
        throws Exception
    {
        Artifact pom = repositorySystem.createProjectArtifact( "org.apache.maven.its", "missing", "0.1" );
        MavenProject project = getProject( pom, true );

        assertNotNull( project.getArtifactId() );

        assertNotNull( project.getRemoteArtifactRepositories() );
        assertFalse( project.getRemoteArtifactRepositories().isEmpty() );

        assertNotNull( project.getPluginArtifactRepositories() );
        assertFalse( project.getPluginArtifactRepositories().isEmpty() );

        assertNull( project.getParent() );
        assertNull( project.getParentArtifact() );

        assertFalse( project.isExecutionRoot() );
    }
