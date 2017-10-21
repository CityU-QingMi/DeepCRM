    public Artifact createDependencyArtifact( Dependency dependency )
    {
        Artifact artifact =
            new DefaultArtifact( dependency.getGroupId(), dependency.getArtifactId(), dependency.getVersion(),
                                 dependency.getScope(), dependency.getType(), dependency.getClassifier(),
                                 new TestArtifactHandler( dependency.getType() ) );

        if ( Artifact.SCOPE_SYSTEM.equals( dependency.getScope() ) )
        {
            artifact.setFile( new File( dependency.getSystemPath() ) );
            artifact.setResolved( true );
        }

        return artifact;
    }
