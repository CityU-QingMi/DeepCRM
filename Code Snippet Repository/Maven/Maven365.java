    private void checkGroupIdScope( MavenProject project, String scopeValue, String groupId )
    {
        Artifact artifact;
        artifact = getArtifact( project, groupId, "scope-compile" );
        assertEquals( "Check scope", scopeValue, artifact.getScope() );
        artifact = getArtifact( project, groupId, "scope-test" );
        assertNull( "Check test dependency is not transitive", artifact );
        artifact = getArtifact( project, groupId, "scope-provided" );
        assertNull( "Check provided dependency is not transitive", artifact );
        artifact = getArtifact( project, groupId, "scope-default" );
        assertEquals( "Check scope", scopeValue, artifact.getScope() );
    }
