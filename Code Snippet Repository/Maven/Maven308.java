    protected void setUp()
        throws Exception
    {
        super.setUp();
        artifactHandler = new ArtifactHandlerMock();
        versionRange = VersionRange.createFromVersion( version );
        artifact = new DefaultArtifact( groupId, artifactId, versionRange, scope, type, classifier, artifactHandler );

        snapshotVersionRange = VersionRange.createFromVersion( snapshotResolvedVersion );
        snapshotArtifact = new DefaultArtifact( groupId, artifactId, snapshotVersionRange, scope, type, classifier, artifactHandler );
    }
