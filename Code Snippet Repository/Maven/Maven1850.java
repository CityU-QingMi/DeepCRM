    private void checkUtSimpleArtifactDependencies( Dependency dep1, Dependency dep2 )
    {
        assertEquals( "compile", dep1.getScope() );
        assertFalse( dep1.isOptional() );
        assertEquals( 0, dep1.getExclusions().size() );
        Artifact depArtifact = dep1.getArtifact();
        assertEquals( "ut.simple", depArtifact.getGroupId() );
        assertEquals( "dependency", depArtifact.getArtifactId() );
        assertEquals( "1.0", depArtifact.getVersion() );
        assertEquals( "1.0", depArtifact.getBaseVersion() );
        assertNull( depArtifact.getFile() );
        assertFalse( depArtifact.isSnapshot() );
        assertEquals( "", depArtifact.getClassifier() );
        assertEquals( "jar", depArtifact.getExtension() );
        assertEquals( "java", depArtifact.getProperty( "language", null ) );
        assertEquals( "jar", depArtifact.getProperty( "type", null ) );
        assertEquals( "true", depArtifact.getProperty( "constitutesBuildPath", null ) );
        assertEquals( "false", depArtifact.getProperty( "includesDependencies", null ) );
        assertEquals( 4, depArtifact.getProperties().size() );

        assertEquals( "compile", dep2.getScope() );
        assertFalse( dep2.isOptional() );
        assertEquals( 0, dep2.getExclusions().size() );
        depArtifact = dep2.getArtifact();
        assertEquals( "ut.simple", depArtifact.getGroupId() );
        assertEquals( "dependency", depArtifact.getArtifactId() );
        assertEquals( "1.0", depArtifact.getVersion() );
        assertEquals( "1.0", depArtifact.getBaseVersion() );
        assertNull( depArtifact.getFile() );
        assertFalse( depArtifact.isSnapshot() );
        assertEquals( "sources", depArtifact.getClassifier() );
        assertEquals( "jar", depArtifact.getExtension() );
        assertEquals( "java", depArtifact.getProperty( "language", null ) );
        assertEquals( "jar", depArtifact.getProperty( "type", null ) ); // shouldn't it be java-sources given the classifier?
        assertEquals( "true", depArtifact.getProperty( "constitutesBuildPath", null ) ); // shouldn't it be false given the classifier?
        assertEquals( "false", depArtifact.getProperty( "includesDependencies", null ) );
        assertEquals( 4, depArtifact.getProperties().size() );
    }
