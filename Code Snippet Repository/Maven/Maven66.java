    public void testSnapshotRangeBoundsCanContainSnapshots()
        throws InvalidVersionSpecificationException
    {
        VersionRange range = VersionRange.createFromVersionSpec( "[1.0,1.2-SNAPSHOT]" );

        assertTrue( range.containsVersion( new DefaultArtifactVersion( "1.1-SNAPSHOT" ) ) );
        assertTrue( range.containsVersion( new DefaultArtifactVersion( "1.2-SNAPSHOT" ) ) );

        range = VersionRange.createFromVersionSpec( "[1.0-SNAPSHOT,1.2]" );

        assertTrue( range.containsVersion( new DefaultArtifactVersion( "1.0-SNAPSHOT" ) ) );
        assertTrue( range.containsVersion( new DefaultArtifactVersion( "1.1-SNAPSHOT" ) ) );
    }
